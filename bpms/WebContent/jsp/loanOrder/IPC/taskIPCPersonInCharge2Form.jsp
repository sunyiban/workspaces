<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- IPC负责人，贷审委审核后-->
<style type="text/css">
	#acceptTaskForm table input{border: none;}
	.table th{ text-align: right;}
	.table td{ text-align: left;}
</style>
<script type="text/javascript">
var $row;
var $datagrid;
var faad;//最终填写金额DLG
$(function(){
	// 查看申请状态
	$row = $grid.datagrid('getSelected');
	$datagrid = $("#lookLoanOrderdg").datagrid({
		url : "loanOrderHis/loanOrderHisAction!findAllLoanOrderHis.action",
		fit : true,
		fitColumns : true,
		pagination:false,
		rownumbers:true,
		border:true,
		singleSelect:true,
		nowrap:true,
		queryParams:{"loanOrderId":$row.loanOrderId},
		multiSort:false,
		fitColumns:true,
		columns : [ [ 
		              {field : 'agentTime',title : '受理时间',width : parseInt($(this).width()*0.1),align : 'center'},
		              {field : 'roleName',title : '受理角色',width : parseInt($(this).width()*0.1),align : 'center'},
		              {field : 'assigneeName',title : '受理人',width : parseInt($(this).width()*0.1),align : 'center'},
		              {field : 'title',title : '审批简述',width :parseInt($(this).width()*0.1),align : 'center'},
		              {field : 'comment',title : '审批详情',width :parseInt($(this).width()*0.1),align : 'center'},
		              {field : 'id',title : '查看附件',width :parseInt($(this).width()*0.08),align : 'center',
			            	formatter:function(value,row,index){
			            		return "<a href='javascript:void(0);' onclick='lookAttachment("+index+");'>查看附件</a>　　" ;
			            	}  
		              }
		              ] ]
	});
	// 渲染姓名
	$("#acceptTaskForm input[name='name']").val($row.name);
	// 渲染身份证号
	$("#acceptTaskForm input[name='idNo']").val($row.idNo);


	$("#attType").combobox({
		valueField : 'code',
		textField : 'text',
		url:'common/commonAction!findTextArr.action?codeMyid=attachment_type',
		onLoadSuccess : function(){
			var val = $(this).combobox("getData");
			if(!$.isEmptyObject(val)){
				$attempData = val;
                $(this).combobox("select", $attempData[0]["code"]);
			}
		},
		editable:false
    });
	
	//查看附件
	$("#checkAttachment").click(function(){
		checkAttachementDetail('noauditId',$row.loanOrderId,'');
	});
	
	//上传附件
	$("#upploadAttachment").click(function(){
		var attType = $("#attType").combobox("getValue");
		fileUploadsDlg(attType,$row.loanOrderId,'');
	});	
	
   // 信贷方式
   $("input[name='auditWay']").combobox({
		width:171,
		url:"common/commonAction!findTextArr.action?codeMyid=audit_way",
		valueField: 'code',
		textField: 'text',
		onLoadSuccess : function(){
			var val = $(this).combobox("getData");
			if(!$.isEmptyObject(val)){
                $(this).combobox("select", val[0]["code"]);
			}
		},
		editable:false 
	});
   
   //查看是微贷还是终审
   $.ajax({
		url:"microcreditOpinion/microcreditOpinionAction!findMicrocreditOpinionDetailByOid.action",
		type:"post",
		data:{"loanOrderId":$row.loanOrderId},
		success:function(data){
			if(data.mcbrId){
				$("#pmicDiv").css("display","block");
				$("#pfinDiv").css("display","none");
			}else{
				$("#pfinDiv").css("display","block");
				$("#pmicDiv").css("display","none");
			}
		}
	});
	
});
	
	// 提交表单信息
	function  submitTask(result) {
		var auditWay = $("#auditWay").combobox("getValue");
		// 验证备注信息是否已经填写
		if(result=="IPCPersonInCharge2Refuse") {
			if($("#comment").val()=="" || $("#title").val()==""){
				$.messager.alert("提示","请填写完备注信息后再进行提交!","warning")
				return false;
			}
		}
		// 确认是否提交
		$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){
			if (r){
				$.ajax({
					url : "microcreditOpinion/microcreditOpinionAction!saveFinalAuditWay.action",
					type:"post",
					data:{"auditWay":auditWay,"loanOrderId" : $row.loanOrderId},
					success:function(datas){
						var data = {
							"comment" : $("#comment").val(),
							"title":$("#title").val(),
							"result" :result,
							"loanOrderId" : $row.loanOrderId,
							"taskId": $row.taskId,
							"processingResult":result=="IPCPersonInCharge2Through"?"A":"B"
						}
						$.ajax({
							type : "POST",
							url : "loanOrder/loanOrderAction!submitTask.action",
							data : data,
							success : function(msg) {
								$grid.datagrid('reload');
								$taskFormDialog.dialog('close');
							}
						});
					}
				});
			}
		});
	}
	
	// 驳回申请,如果是订单的状态为贷审委完成,则驳回到IPC数据节点,如果订单的状态为终审通过,则驳回到审核专员
	function rejectSubmitTask(){
		if($row.orderStatus.statusCode=='LoanOrderIPC_IPCLoanSupervisorThrough'){// 审贷部主管通过的请求,则驳回到审贷部主管
			submitTask("IPCPersonInCharge2Reject2");
		}else{// 贷审委完成驳回到IPC数据
			submitTask("IPCPersonInCharge2Reject")
		}
	}
	
	// 查看附件
	function lookAttachment(index){
		var row = getRowData($datagrid,index);
		// 附件信息
		checkAttachementDetail('noauditId',$row.loanOrderId,row.assignee,'2');
	}
	
	//查看稽核信息
	function checkAuditReportDetail(){
		window.open("jsp/loanOrder/IPC/ipcAuditInfoRecordDetail.jsp?loanOrderId="+$row.loanOrderId,
				"稽核信息详情", "height="+($(window).height()*0.8)+", width=900, top=100, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no")
	}
	
	//查看信审报告 
	function checkApplicationReportDetail(){
		window.open("jsp/loanOrder/IPC/ipcApplicationReportDetail.jsp?loanOrderId="+$row.loanOrderId+"&loanerId="+$row.loanerId+"&isFinalShow=1",
				"信审报告信息详情", "height="+($(window).height()*0.8)+", width=900, top=100, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no")
	}

	//查看呈报意见表
	function ipcMicrocreditOpinionDailog(){
		window.open("jsp/loanOrder/IPC/ipcMicrocreditOpinionInfo.jsp?loanOrderId="+$row.loanOrderId+"&loanerName="+$row.name+"&loanerIdNo="+$row.idNo+"&purpose="+$row.purpose,
				"呈报意见详情", "height="+($(window).height()*0.8)+", width=1000, top=100, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no")
	}	
	//填写最终金额/期限
	function editFinalAmtAndDate(){
		faad = $("<div></div>").dialog({
			title : "最终金额/期限",
			modal : true,
			width : 900,
			height : $(window).height()*0.8,
			closed : false,
			closeable : true,
			href : "jsp/loanOrder/IPC/ipcFinalAmtAndDate.jsp",
			onClose : function (){
				$(this).dialog("destroy");
			}
		});
	}
	
	//查看外访调查报告及总结
	function checkOurSurveyReport(){
		$("<div></div>").dialog({
			title:"外访调查报告及总结",
			width:900,
			height : $(window).height()*0.8,
			closed:false,
			closeable:true,
			modal:true,
			href:"jsp/loanOrder/IPC/ipcOutboundSurveyReportDetail.jsp",
			onClose:function(){
				$(this).dialog("destroy");
			}
		});
	}
</script>
<!-- 受理任务 S -->
<div data-options="region:'north',title:'North Title',split:true">
	<div style="width: 900px;height: 190px;overflow: auto;">
		<form id="acceptTaskForm" method="post">
				<input name="id" id="id"  type="hidden"/>
				<input name="auditId" type="hidden" value="noauditId"/>
			 <table cellpadding="4">
				<tr>
			    	<th>客户姓名:</th>
					<td><input name="name" readonly="readonly" type="text" class="easyui-textbox easyui-validatebox" data-options="required:true"/></td>
				
					<th>身份证号:</th>
					<td><input name="idNo" readonly="readonly" type="text" class="easyui-textbox easyui-validatebox" data-options="validType:'idcard'"/></td>
				</tr>
				<tr>
					<th>信贷方式:</th>
					<td>
						<input id="auditWay" name="auditWay" class="easyui-combobox" />
					</td>
					
					<th>备注简述:</th>
					<td>
						<input id="title" name="title" class="easyui-validatebox easyui-textbox" style="border: 1px solid #DDDDDD;">
					</td>
				</tr>
				<tr>
				 	<th>备注详情</th>
					<td colspan="3">
						<textarea id="comment" name="comment" class="easyui-validatebox easyui-textbox" style="width:100%;height:70px;resize:none;"></textarea>
					</td>
				</tr>
				<tr>
					<th>
						附件类型:
					</th>
					<td>
						<input id="attType" class="easyui-textbox easyui-combobox" />
					</td>
					<td colspan="2">
						<a id="checkAttachment" href="javascript:void(0);" class="easyui-linkbutton">查看附件</a>	
						<a id="upploadAttachment" href="javascript:void(0);" class="easyui-linkbutton" >上传附件</a>	
					</td>
				</tr>
			 </table>
		</form>
	</div>

	<div id="pmicDiv" style="width:900px;height:30px;">
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkApplicationReportDetail();">查看信审报告</a>
		<!-- <a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkAuditReportDetail();">查看稽核信息</a> -->
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkOurSurveyReport();">查看外访调查报告及总结</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="ipcMicrocreditOpinionDailog();">填写微贷呈报意见</a>
	</div>
	
	<div id="pfinDiv" style="width:900px;height:30px;">
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkApplicationReportDetail();">查看信审报告</a>
		<!-- <a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkAuditReportDetail();">查看稽核信息</a> -->
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkOurSurveyReport();">查看外访调查报告及总结</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="editFinalAmtAndDate();">填写最终金额/期限</a>
	</div>
	
	<div style="width:900px;height:30px;">
		<!-- <a href="javascript:void(0);" class="easyui-linkbutton" onclick="editFinalAmtAndDate();">填写最终金额/期限</a> -->
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="rejectSubmitTask();" >IPC负责人驳回</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('IPCPersonInCharge2Through');" >IPC负责人通过</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('IPCPersonInCharge2Refuse');">IPC负责人拒贷</a>
	</div>
	
	<div id="lookInfo" class="easyui-accordion" style="height: 340px;width: 900px;overflow: hidden;">
		<div title="备注信息" data-options="iconCls:'icon-cstbase',selected:true" >   
			<table id="lookLoanOrderdg" title="申请备注的信息"></table>
		</div>
	</div>
</div>   
<!-- 受理任务 E -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- IPC负责人,车贷后-->
<style type="text/css">
	#acceptTaskForm table input{border: none;}
	.table th{ text-align: right;}
	.table td{ text-align: left;}
</style>
<script type="text/javascript">
var $row;
var $datagrid;
var carPledgeAmtDlg;
var carInfo;
$(function(){
	// 查看申请状态
	$row = $grid.datagrid('getSelected');
	$datagrid = $("#lookLoanOrderdg").datagrid({
		url : "loanOrderHis/loanOrderHisAction!findAllLoanOrderHis.action",
		width : 'auto',
		height : 340,
		pagination:false,
		rownumbers:true,
		border:true,
		singleSelect:true,
		nowrap:true,
		queryParams:{"loanOrderId":$row.loanOrderId},
		multiSort:false,
		fitColumns:true,
		columns : [ [ 
		              {field : 'agentTime',title : '受理时间',width : parseInt($(this).width()*0.1),sortable:true},
		              {field : 'roleName',title : '受理角色',width : parseInt($(this).width()*0.1)},
		              {field : 'assigneeName',title : '受理人',width : parseInt($(this).width()*0.1),align : 'left'},
		              {field : 'title',title : '审批简述',width :parseInt($(this).width()*0.1),align : 'left'},
		              {field : 'comment',title : '审批详情',width :parseInt($(this).width()*0.1),align : 'left'},
		              {field : 'id',title : '查看附件',width :parseInt($(this).width()*0.08),align : 'left',
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
	
});
	
	// 提交表单信息
	function  submitTask(result) {
		// 验证备注信息是否已经填写
		/* if($("#comment").val()=="" || $("#title").val()==""){
			$.messager.alert("提示","请填写完备注信息后再进行提交!","warning")
			return false;
		} */
		// 确认是否提交
		$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){
			if (r){
				var data = {
					"comment" : $("#comment").val(),
					"title":$("#title").val(),
					"result" :result,
					"loanOrderId" : $row.loanOrderId,
					"taskId": $row.taskId,
					"processingResult":"A"
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
	
	// 查看附件
	function lookAttachment(index){
		var row = getRowData($datagrid,index);
		// 附件信息
		checkAttachementDetail('noauditId',$row.loanOrderId,row.assignee,'2');
	}
	
	//查看稽核信息
	function checkAuditReportDetail(){
		window.open("jsp/loanOrder/IPC/ipcAuditInfoRecordDetail.jsp?loanOrderId="+$row.loanOrderId,
				"稽核信息详情", 'height=650, width=1000, top=200, left=400, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')
	}
	
	//查看信审报告 
	function checkApplicationReportDetail(){
		window.open("jsp/loanOrder/IPC/ipcApplicationReportDetail.jsp?loanOrderId="+$row.loanOrderId+"&loanerId="+$row.loanerId+"&isFinalShow=1",
				"稽核信息详情", 'height=650, width=1000, top=200, left=400, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')
	}
	
	//增加车抵金额
	function addCarPledgeAmt(){
		carPledgeAmtDlg = $("<div></div>").dialog({
			title:"增加车抵金额",
			width:390,
			height:240,
			closed:false,
			closeable:true,
			modal:true,
			href:"jsp/loanOrder/IPC/ipcCarPledgeAmt.jsp",
			onClose : function (){
				$(this).dialog("destroy");
			}
		});
	}
	
	//查看车辆信息
	function checkCarInfo(){
		carInfo = $("<div></div>").dialog({
			title:"车辆信息",
			width:530,
			height:150,
			closed:false,
			closeable:true,
			modal:true,
			href:"jsp/loanOrder/IPC/ipcCarInfoDetail.jsp",
			onClose : function (){
				$(this).dialog("destroy");
			}
		});
	}
	
	//查看呈报意见表
	function ipcMicrocreditOpinionDailog(){
		window.open("jsp/loanOrder/IPC/ipcMicrocreditOpinionInfo.jsp?loanOrderId="+$row.loanOrderId+"&loanerName="+$row.name+"&loanerIdNo="+$row.idNo+"&purpose="+$row.purpose,
				"呈报意见详情", 'height=650, width=1000, top=200, left=400, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')
	}	
	
	//查看外访调查报告及总结
	function checkOurSurveyReport(){
		$("<div></div>").dialog({
			title:"外访调查报告及总结",
			width:1180,
			height:680,
			closed:false,
			closeable:true,
			modal:true,
			href:"jsp/loanOrder/IPC/ipcOutboundSurveyReportDetail.jsp",
			onClose:function(){
				$(this).dialog("destroy");
			}
		});
	}
	
	//填写最终金额/期限
	function editFinalAmtAndDate(){
		faad = $("<div></div>").dialog({
			title : "最终金额/期限",
			modal : true,
			width : 1000,
			height : 650,
			closed : false,
			closeable : true,
			href : "jsp/loanOrder/IPC/ipcFinalAmtAndDate.jsp",
			onClose : function (){
				$(this).dialog("destroy");
			}
		});
	}
	
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
</script>
<!-- 受理任务 S -->
<div data-options="region:'north',title:'North Title',split:true">
	<div style="width: 980px;height: 190px;overflow: auto;">
		<form id="acceptTaskForm" method="post">
				<input name="id" id="id"  type="hidden"/>
				<input name="auditId" type="hidden" value="noauditId"/>
				 <table cellpadding="5px;">
					<tr>
					    <th>客户姓名:</th>
						<td><input name="name" readonly="readonly" type="text"/></td>
						<th>身份证号:</th>
						<td><input name="idNo" readonly="readonly" type="text"/></td>
					</tr>
					<tr>
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

	<!-- <div style="width:980px;height:30px;">
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkApplicationReportDetail();">查看信审报告</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkCarInfo();">查看车辆信息</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="ipcMicrocreditOpinionDailog();">填写微贷呈报意见</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('IPCPersonInCharge3Through');" >IPC负责人通过</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="addCarPledgeAmt();">增加车抵金额</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkAuditReportDetail();">查看稽核信息</a>
	</div> -->
	<div id="pmicDiv" style="width:980px;height:30px;">
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkApplicationReportDetail();">查看信审报告</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkCarInfo();">查看车辆信息</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkOurSurveyReport();">查看外访调查报告及总结</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="ipcMicrocreditOpinionDailog();">填写微贷呈报意见</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('IPCPersonInCharge3Through');" >IPC负责人通过</a>
	</div>
	
	<div id="pfinDiv" style="width:980px;height:30px;">
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkApplicationReportDetail();">查看信审报告</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkCarInfo();">查看车辆信息</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkOurSurveyReport();">查看外访调查报告及总结</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="editFinalAmtAndDate();">填写最终金额/期限</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('IPCPersonInCharge3Through');" >IPC负责人通过</a>
	</div>
	
	
	<div id="lookInfo" class="easyui-accordion" style="height: 380px;width: 980px;overflow: hidden;">
		<div title="备注信息" data-options="iconCls:'icon-cstbase',selected:true" >   
			<table id="lookLoanOrderdg" title="申请备注的信息"></table>
		</div>
	</div>
</div>   
<!-- 受理任务 E -->
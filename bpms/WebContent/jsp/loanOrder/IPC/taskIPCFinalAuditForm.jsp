<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- 终审（终审的审批金额要小于等于初审部门给出的意见） -->
<style type="text/css">
	table {border-radius: 5px;}
	.linkSpan{
	  padding:5px;
	  display:-moz-inline-box;
	  display:inline-block;
	  width:40%; 
	  text-align: center;
	}
	.linkSpanS{
	  padding:5px;
	  display:-moz-inline-box;
	  display:inline-block;
	  width:10%; 
	  text-align: center;
	}
	a{text-decoration: none;}
	a:hover {
	 color: #FF0000;
	}
	.table th{
		text-align: right;
	}
	.table td{
		text-align: left;
	}	
</style>
<script type="text/javascript">
	var $row;
	var $datagrid;
	var applicationReportDialog;
	$(function(){
		// 查看申请状态
		$row = $grid.datagrid('getSelected');
		// 渲染备注
		$datagrid =  $("#lookLoanOrderdg").datagrid({
			url : "loanOrderHis/loanOrderHisAction!findAllLoanOrderHis.action",
			width : 'auto',
			height : 360,
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

		//查看附件
		$("#checkAttachment").click(function(){
			checkAttachementDetail('noauditId',$row.loanOrderId,'');
		});
		
		//上传附件
		$("#upploadAttachment").click(function(){
			var attType = $("#attType").combobox("getValue");
			fileUploadsDlg(attType,$row.loanOrderId);
		});
		
		//获取附件类型combobox,并将数据保存 
		$("#attType").combobox({
			valueField : 'code',
			textField : 'text',
			url:'common/commonAction!findTextArr.action?codeMyid=attachment_type',
			onLoadSuccess : function(){
				var val = $(this).combobox("getData");
				if(!$.isEmptyObject(val)){
                    $(this).combobox("select", val[0]["code"]);
				}
			},
			editable:false 
	    });
	});
 
	// 查看详情附件
	function lookAttachment(index){
		var row = getRowData($datagrid,index);
		// 附件信息
		checkAttachementDetail('noauditId',$row.loanOrderId,row.assignee,'2');
	}
	
	// 提交表单信息
	function  submitTask(result) {
		if(applicationReportDialog!=null){
			applicationReportDialog.dialog("close");
		}
		var data = {
			"comment" : $("#comment").val(),
			"title":$("#title").val(),
			"result" :result,
			"loanOrderId" : $row.loanOrderId,
			"taskId": $row.taskId,
			"processingResult":result=="IPCFinalAuditThrough"?"A":"B"
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
	
	//查看稽核信息
	function checkAuditReportDetail(){
		window.open("jsp/loanOrder/IPC/ipcAuditInfoRecordDetail.jsp?loanOrderId="+$row.loanOrderId,
				"稽核信息详情", 'height=650, width=1000, top=200, left=400, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')
	}

	//查看信审报告 
	function checkApplicationReportDetail(){
		window.open("jsp/loanOrder/IPC/ipcApplicationReportDetail.jsp?loanOrderId="+$row.loanOrderId+"&loanerId="+$row.loanerId+"&isFinalShow=0",
				"稽核信息详情", 'height=650, width=1000, top=200, left=400, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')
	}
	
	//填写终审报告
	function saveFinalAudit(){
		// 验证备注信息是否已经填写
		if($("#comment").val()=="" || $("#title").val()==""){
			$.messager.alert("提示","请填写完备注信息后再进行提交!","warning")
			return false;
		}
		applicationReportDialog = $("<div></div>").dialog({
			title : "终审资质分析",
			width : 1000,
			height : 680,
			closed : false,
			closeable : true,
			modal : true,
			onClose : function(){
				$(this).dialog("destroy");
			},
			href : "jsp/loanOrder/IPC/finalAuditAnaly.jsp"
		});
	}
	
	// 加载信息
	function loadFinalAuditReport(data){
		if(!$.isEmptyObject(data)){
			$("#finalAuditReport-form").form("load",data);
		}
	}
	
	// 提示信息
	function alertMsg(data) {
		if (data.status) {
			$.messager.show({
				title : data.title,
				msg : data.message,
				timeout : 5000,
				showType : 'slide'
			})
		} else {
			$.messager.alert(data.title, data.message, 'error');
		}

	}
	
	// 终审拒贷
	function finalAuditRefuse(){
		// 验证备注信息是否已经填写
		if($("#comment").val()=="" || $("#title").val()==""){
			$.messager.alert("提示","请填写完备注信息后再进行提交!","warning")
			return false;
		}
		// 驳回申请
		submitTask("IPCFinalAuditRefuse");
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
			href:"jsp/loanOrder/IPC/ipcOutboundSurveyReportDetail.jsp"
		});
	}
</script>
<!-- 受理任务 S -->
<div data-options="region:'north',title:'North Title',split:true" style="overflow: hidden;">
	<div style="width: 980px;height: 190px;overflow: auto;">
		<form id="acceptTaskForm" method="post">
				<input name="id" id="id"  type="hidden"/>
				<input name="auditId" type="hidden" value="noauditId"/>
				 <table class="table" cellpadding="5px;">
					 <tr>
					    <th>客户姓名:</th>
						<td><input name="name" readonly="readonly" type="text" class="easyui-textbox easyui-validatebox" data-options="required:true"/></td>
						
						<th>身份证号:</th>
						<td><input name="idNo" readonly="readonly" type="text" class="easyui-textbox easyui-validatebox" data-options="validType:'idcard'"/></td>
					</tr>
					<tr>
					 	<th>备注简述:</th>
						<td colspan="3">
							<textarea id="title" name="title" class="easyui-validatebox easyui-textbox" style="width:100%;height:15px;"></textarea>
						</td>
					</tr>
					<tr>
					 	<th>备注详情:</th>
						<td colspan="3">
							<textarea id="comment" name="comment" class="easyui-validatebox easyui-textbox" style="width:100%;height:70px;"></textarea>
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
	
	<div style="width: 980px;height:30px;">
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkApplicationReportDetail();">查看信审报告</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkAuditReportDetail();">查看稽核信息</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkOurSurveyReport();">查看外访调查报告及总结</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="saveFinalAudit();" >填写终审报告并通过</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="finalAuditRefuse('IPCFinalAuditRefuse');">终审拒贷</a>
	</div>
	
	<!-- 附件和备注信息列表 -->
	<div id="lookInfo" class="easyui-accordion" style="height: 390px;width: 980px;overflow: hidden;">
	    <div title="备注信息" data-options="iconCls:'icon-cstbase',selected:true">   
			<table id="lookLoanOrderdg" title="申请备注的信息" style="overflow: auto;"></table>
		</div>
	</div>
</div>
<!-- 受理任务E -->		
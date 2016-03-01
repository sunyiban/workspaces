<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<style type="text/css">
	#acceptTaskForm table input{border: none;}
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
var applicationReportDialog;//信审报告DLG
$(function(){
	// 查看申请状态
	$row = $grid.datagrid('getSelected');
	$datagrid = $("#lookLoanOrderdg").datagrid({
		url : "loanOrderHis/loanOrderHisAction!findAllLoanOrderHis.action",
		fit:true,
		fitColumns:true,
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
		              {field : 'id',title : '查看附件',width :parseInt($(this).width()*0.09),align : 'center',
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
	
	//loadAttachmentList('attachmentList','noauditId',$row.loanOrderId);
	//查看附件
	$("#checkAttachment").click(function(){
		checkAttachementDetail('noauditId',$row.loanOrderId,'');
	});
	
	//上传附件
	$("#upploadAttachment").click(function(){
		var attType = $("#attType").combobox("getValue");
		fileUploadsDlg(attType,$row.loanOrderId);
	});
	
});

	// 提交表单信息
	function  submitTask(result) {
		if(result=='FinalAuditThrough'){
			applicationReportDialog.dialog("close");
		}
		var data = {
			"comment" : $("#comment").val(),
			"title": $("#title").val(),
			"result" :result,
			"loanOrderId" : $row.loanOrderId,
			"taskId": $row.taskId,
			"processingResult":result=="FinalAuditThrough"?"A":"B"
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
	
	// 终审资质分析
	function applicationReport(){
		// 验证备注信息是否已经填写
		// 验证备注信息是否已经填写
		if($("#comment").val()==""||$("#title").val()==""){
			$.messager.alert("提示","请填写完备注信息后再进行提交!","warning")
			return false;
		}
		applicationReportDialog = $("<div></div>").dialog({
			title : '终审资质分析',
			width : 900,
			height : $(window).height()*0.8,
			modal:true,
			href : "jsp/loanOrder/letter/finalQualificationAnalysis.jsp?t="+new Date(),
			onClose:function(){
				$(this).dialog("destroy");
			}
		}); 
	}
	// 加载信息
	function loadFinalAuditReport(data){
		if(!$.isEmptyObject(data)){
			$("#finalAuditReport-form").form("load",data);
		}
	}

	//驳回
	function finalAuditRefuse(result){
		// 验证备注信息是否已经填写
		if($("#comment").val()==""||$("#title").val()==""){
			$.messager.alert("提示","请填写完备注信息后再进行提交!","warning")
			return false;
		}
		
		$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){
		if (r) {
				// 确认是否提交
				submitTask(result);
			}
		});
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

	// 查看附件
	function lookAttachment(index) {
		var row = getRowData($datagrid,index);
		checkAttachementDetail('noauditId',$row.loanOrderId,row.assignee,'2');
		// 附件信息
		/* $("#lookAttachmentList").datagrid({
				url : "attachment/attachmentAction!findAttachmentListByUserIdAndOrderId.action",
				width : 'auto',
				height : 340,
				pagination : true,
				rownumbers : true,
				border : true,
				singleSelect : true,
				nowrap : true,
				queryParams : {
					"loanOrderId" : row.loanOrderId,
					"userId" : row.assignee
				},
				multiSort : false,
				columns : [ [
						{field : 'attName',title : '附件名称',width : parseInt($(this).width() * 0.1),sortable : true},
						{field : 'attTypeName',title : '附件类型',width : parseInt($(this).width() * 0.1)},
						{field : 'creatorName',title : '创建者',width : parseInt($(this).width() * 0.1),align : 'left'},
			            {field : 'id',title : '查看附件',width :parseInt($(this).width()*0.1),align : 'left',
			            	formatter:function(value,row,index){
			            		var result = "<a target='_blank' href='jsp/openoffice/documentView.jsp?attId="+row.attId+"'>在线预览</a>　　" ;
			            			result += "<a target='_blank' href='javascript:void(0);' onclick=\"downloadAttachment('"+row.attId+"');\">下载</a>　　" ;
			            		return result;
			            	}  
		                }
						] ]
		});
		$('#lookInfo').accordion("select", "附件信息"); */
	}
	
	//查看稽核信息
	function checkAuditReportDetail(){
		window.open("jsp/loanOrder/letter/auditInfoRecordDetail.jsp?loanOrderId="+$row.loanOrderId,
				"稽核信息详情", "height="+($(window).height()*0.8)+", width=900, top=100, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no")
	}
	
	//查看信审报告 
	function checkApplicationReportDetail(){
		/* var params = "{'name':"+$row.name+",'idNo':"+$row.idNo+",'loanAmount':"+$row.loanAmount+","+
			"'purpose':"+$row.purpose+",'repayMethod':"+$row.repayMethod+",'loanOrderId':"+$row.loanOrderId+"}"; */
		window.open("jsp/loanOrder/letter/applicationReportDetail.jsp?loanOrderId="+$row.loanOrderId+"&loanerId="+$row.loanerId,
				"稽核信息详情", "height="+($(window).height()*0.8)+", width=900, top=100, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no")
	}
</script>
<!-- 受理任务 S -->
<div data-options="region:'north',title:'North Title',split:true">
	<div style="width: 900px;height: 190px;overflow: auto;">
		<form id="acceptTaskForm" method="post">
			 <input name="id" id="id"  type="hidden"/>
			 <input name="auditId" type="hidden" value="noauditId"/>
			 <table class="table" cellpadding="5px;">
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
				 	<th>备注详情:</th>
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
			<!-- <div id="attachmentList" style="width:100%;display:block;float:left;">
			</div>
			<div id="upload_form_div_add">
				<div id="upload_form_father_idDiv" style="width:100%;">
					<div id="upload_form_div">
						<font size="2" style="font-weight: bold;">　上传附件:&nbsp;</font>
						<input class="easyui-textbox easyui-combobox" type="text" />
						<input name="fileName" type="text" placeholder="请输入附件名">
						<input id="file" name="file" type="file"  onchange="fileChange(this);" > 
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addACredential(this);">添加</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeACredential(this);">删除</a> 
					</div>
				</div>
			</div>
			<div id="upload_form" style="width: 100%; height: 30px; text-align: right;">
				<a href="javascript:void(0);" class="easyui-linkbutton" onclick="fileUploads(this)">上传附件</a>
			</div> --> 
		</form>
	</div>
	
	<div style="width: 900px;height:30px;">
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkAuditReportDetail();">查看稽核信息</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkApplicationReportDetail();">查看信审报告</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="applicationReport()">填写终审报告并通过</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="finalAuditRefuse('FinalAuditRefuse');">终审拒贷</a>
	</div>
	
	<div id="lookInfo" class="easyui-accordion" style="height: 390px;width: 900px;overflow: hidden;">
	    <div title="备注信息" data-options="iconCls:'icon-cstbase',selected:true" >   
			<table id="lookLoanOrderdg" title="申请备注的信息"></table>
		</div>
	    <!-- <div title="附件信息" data-options="iconCls:'icon-cstbase'" >   
			<table id="lookAttachmentList" title="申请附件的信息"></table>
		</div> -->
	</div>
</div>   
<!-- 受理任务 E -->	


			

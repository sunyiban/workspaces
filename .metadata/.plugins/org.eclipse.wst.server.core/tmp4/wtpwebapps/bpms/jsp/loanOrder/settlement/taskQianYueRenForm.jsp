<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<style type="text/css">
	#acceptTaskForm table input{border: none;}
	.table th{ text-align: right;}
	.table td{ text-align: left;}
</style>
<script type="text/javascript">
var $row;
var $datagrid;
var $lcId="";
var $saveContractDialog;
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
		              {field : 'comment',title : '审批意见',width :parseInt($(this).width()*0.1),align : 'center'},
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
                $(this).combobox("select", val[0]["code"]);
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
		fileUploadsDlg(attType,$row.loanOrderId);
	});
	
	//查询此订单是否存在合同信息，如果有，保存合同ID
	$.ajax({
		url:'loanContract/loanContractAction!checkIsContractExist.action',
		data:{"loanOrderId":$row.loanOrderId},
		type:"post",
		success:function(data){
			if(data){
				$lcId = data.lcId;
				$("#contractNo").val(data.contractNo);
			}
		}
	});
	
	$.ajax({
		url : 'accountInfo/accountInfoAction!hasICBCAccount.action',
		data:{"loanerId":$row.loanerId,"loanOrderId":$row.loanOrderId},
		type:"post",
		success:function(data) {
			if(null==data) {
				$("#isICBC").hide();
			} else {
				$("#isICBC").show();
			}
		}
	});
});

	// 提交表单信息
	function  submitTask(result) {
			var data = {
				"comment" : $("#comment").val(),
				"title":$("#title").val(),
				"result" :result,
				"loanOrderId" : $row.loanOrderId,
				"taskId": $row.taskId,
				"processingResult":result=="QianYueRenThrough"?"A":"B"
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
	
	// 查看附件
	function lookAttachment(index){
		var row = getRowData($datagrid,index);
		// 附件信息
		checkAttachementDetail('noauditId',$row.loanOrderId,row.assignee,'2');
	}
	
	//生成合同，向合同表插入数据
	function createContract(){
		if($lcId==""){
			var contractNo = $("#contractNo").val();
			$.ajax({
				url:"loanContract/loanContractAction!saveLoanContract.action",
				type:"post",
				data:{"loanOrderId":$row.loanOrderId,"lcId":$lcId,"contractNo":contractNo},
				success:function(data){
					data = $.parseJSON(data);
					if(data.status){
						$.messager.show({
							title:'提示',
							msg:data.message,
							timeout:5000,
							showType:'slide'
						});
						exportContract();
						$lcId = data.data.lcId;
						$("#contractNo").val(data.data.contractNo);
					}else{
						$.messager.alert("提示",data.message,"error");
					}
				}
			});
		}else{
			exportContract();
		}
	}
	
	// 导出合同
	function exportContract(){
		downFileByFormPost("loanContract/loanContractAction!downloadContract.action", 
				{"loanOrderId":$row.loanOrderId}
		);
	}
	
	// 完善合同信息弹窗框
	function saveContract(){
		console.info($lcId)
		if($lcId==""){
			$.messager.alert("提示","请先生成合同信息!","info");
			return false;
		}
		
		// 弹出完善合同的弹出窗
		$saveContractDialog = $("<div></div>").dialog({
			title:"编辑",
			iconCls:'icon-edit',
			resizable:true,
			modal:true,
			width: 900,
			height:$(window).height()*0.8,
			href: "jsp/loanOrder/settlement/saveContractDialog.jsp",
			onClose:function(){
				$(this).dialog("destroy");
				$grid.datagrid('reload');
			}
		});
	}

	// 客户拒绝签
	function sinatoryRefuseFun(result){
		if(!isNotEmptyComment()) return;
		$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){
			if(r){	
					submitTask(result);
				}
			});
			
	}
	
	// 验证备注信息不为空时
	function isNotEmptyComment(){
		// 验证备注信息是否已经填写
		if($("#comment").val()==""||$("#title").val()==""){
			$.messager.alert("提示","请填写完备注信息后再进行提交!","warning")
			return false;
		}
		return true;
	}
	
	//导出还款管理服务说明书
	function exportRepaymentSpecification() {
		if($lcId==""){
			$.messager.alert("提示","请先生成合同信息!","info");
			return false;
		}
		downFileByFormPost("loanContract/loanContractAction!exportRepaymentSpecification.action", 
				{"loanOrderId":$row.loanOrderId}
		);
	}
	
	//导出借款人委托划款授权书
	function exportCreditAuthorization() {
		downFileByFormPost("loanContract/loanContractAction!exportCreditAuthorization.action", 
				{"loanOrderId":$row.loanOrderId}
		);
	}
	
	//导出上海资信授权书
	function exportBorrowerCommissionedAuthorizationLetter() {
		downFileByFormPost("loanContract/loanContractAction!exportBorrowerCommissionedAuthorizationLetter.action", 
				{"loanOrderId":$row.loanOrderId}
		);
	}
	
	//导出工商银行授权书
	function exportICBCAuthorizationLetter() {
		downFileByFormPost("loanContract/loanContractAction!exportICBCAuthorizationLetter.action", 
				{"loanerId":$row.loanerId,"loanOrderId":$row.loanOrderId}
		);
	}
	
	//导出信用咨询及管理服务协议
	function exportCreditCounselingAndManagementServices() {
		if($lcId==""){
			$.messager.alert("提示","请先生成合同信息!","info");
			return false;
		}
		downFileByFormPost("loanContract/loanContractAction!exportCreditCounselingAndManagementServices.action", 
				{"loanOrderId":$row.loanOrderId}
		);
	}
</script>
<!-- 受理任务 S -->
<div data-options="region:'north',title:'North Title',split:true">
	<div style="width: 900px;height: 160px;overflow: auto;">
		<form id="acceptTaskForm" method="post">
			 <input name="id" id="id"  type="hidden"/>
			 <input name="auditId" type="hidden" value="noauditId"/>
			 <table class="table">
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
		</form>
	</div>
	
	<div style="width:900px;height:30px;">
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="createContract();">生成并导出合同</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="saveContract();">完善合同并签约</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="sinatoryRefuseFun('QianYueRenRefuse');">客户拒签</a>
	</div>
	
	<div style="width:100%;height:30px;display: inline;">
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="exportRepaymentSpecification();">导出还款管理服务说明书</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="exportCreditAuthorization();">导出资信授权书</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="exportBorrowerCommissionedAuthorizationLetter();">导出借款人委托划款授权书</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="exportCreditCounselingAndManagementServices();">导出信用咨询及管理服务协议</a>
	</div>
	
	<div id="isICBC" style="width:100%;height:30px;display: inline;">
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="exportICBCAuthorizationLetter();">导出工商银行授权书</a>
	</div>
	
	<div id="lookInfo" class="easyui-accordion" style="height: 390px;width: 900px;overflow: hidden;">
	    <div title="备注信息" data-options="iconCls:'icon-cstbase',selected:true" >   
			<table id="lookLoanOrderdg" title="申请备注的信息"></table>
		</div>
	</div>
</div>   
<!-- 受理任务 E -->		

<%@page import="com.bpms.util.Constants"%>
<%@page import="com.bpms.shiro.ShiroUser"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
 ShiroUser shiroUser = Constants.getCurrendUser();
%>
<style type="text/css">
	
</style>
<script type="text/javascript">
	var $creditAuditReport = {};
	$(function(){
		   // 渲染经营状态的下拉框
		   $("input[name='comOperStatus']").combobox({
				width:171,
				url:"common/commonAction!findTextArr.action?codeMyid=com_oper_status",
				valueField: 'code',
				textField: 'text',
				editable : false,
				required : true
			});
		   
		   // 渲染信访情况的下拉框
		   $("input[name='visitStatus']").combobox({
				width:171,
				url:"common/commonAction!findTextArr.action?codeMyid=visit_status",
				valueField: 'code',
				textField: 'text',
				editable : false,
				required : true
			});
		   
		   // 贷款期限下拉框
		   $("#loanPeriodType").combobox({
				width:171,
				url:"common/commonAction!findTextArr.action?codeMyid=loan_period_type",
				valueField: 'code',
				textField: 'text',
				editable : false,
				required : true
			});
		   
			// 获取申请报告的信息
			$.getJSON("creditAuditReport/creditAuditReportAction!findCreditAuditReportByLoanOrderId.action",
				{"loanOrderId":$row.loanOrderId},
				function(data){
					$creditAuditReport = data;
					loadFirstAuditReport($creditAuditReport.firstAuditReport);
			});
	});

	//保存资质分析
	function saveFirstAuditReport(obj) {
		$(obj).parents("form:first").form('submit',{
			url : "firstAuditReport/firstAuditReportAction!saveFirstAuditReport.action",
			onSubmit : function(param) {
				var isValid = $(this).form('validate');
				if (isValid){
					param.carId = $creditAuditReport.carId;
					param.loanOrderId = $row.loanOrderId;
				}
				return isValid; // 返回false终止表单提交
			},
			success : function(data) {
				data = $.parseJSON(data);
				loadFirstAuditReport(data.data);
				alertMsg(data);
				$.messager.progress('close'); // 如果提交成功则隐藏进度条
			}
		});
	}
	
	// 渲染资质分析
	function loadFirstAuditReport(data){
		if(!$.isEmptyObject(data)){
				$("#firstAuditReport-form").form("load",data);
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
</script>
<div class="easyui-tabs" style="fit:true;">
	<!-- 资质分析 -->
	<div title="资质分析">
		<form id="firstAuditReport-form" method="post" style="height:580px;">
			<input name="firsId" hidden="true">
			<table class="table" style="margin-top: 10px;width:100%;" cellpadding="5px;">
				<tr>
					<td colspan="6"><span style="font-weight: bold;font-size: 14px;width:60px;">[初审资质分析详情]</span></td>
				</tr>
				<tr>
					<th>实地经营状态:</th>
					<td><input name="comOperStatus" style="width: 100px;"class="easyui-validatebox easyui-combobox"/></td>
					<th>实地信访情况:</th>
					<td><input name="visitStatus" style="width: 100px;" class="easyui-validatebox easyui-combobox"/></td>
				</tr>
				<tr>
					<th>建议金额(万元):</th>
					<td><input name="suggestAmt" type="text" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'mDouble'"/></td>
					<th>贷款期限(个月):</th>
					<td colspan="2"><input id="loanPeriodType" name="loanPeriodType" /></td>
				</tr>
				<tr>
					<th>电核人员:</th>
					<td>
						<input class="easyui-validatebox easyui-textbox"  value="<%=shiroUser.getUser().getName() %>"  disabled="disabled"/>
						<input id="firsPersonnel" name="firsPersonnel" hidden="true"  class="easyui-validatebox easyui-textbox" value="<%=shiroUser.getUserId() %>"/>
					</td>
					<th>外访人:</th>
					<td>
						<input name="outSurver" class="easyui-textbox easyui-validatebox" data-options="validType:'length[0,20]',required:true" />
					</td>
					<th>审核日期</th>
					<td><input id="finaDate" name="firsDate"  class="easyui-validatebox easyui-datebox" data-options="editable:false" /></td>
				</tr>
				<tr>
					<th>电核部门意见:</th>
					<td colspan="6"><textarea name="firsDepSugg" style="width:95%;height:70px;" class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'length[0,512]'"></textarea></td>
				</tr>
				<tr>
					<td style="text-align: right;" colspan="6" align="right">
						<a href="javascript:void(0);" onclick="saveFirstAuditReport(this)" class="easyui-linkbutton" iconCls="icon-save">保存</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
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
			// 渲染是否本地的下拉框
		   $("input[name='isLocalRes']").combobox({
				width:171,
				url:"common/commonAction!findTextArr.action?codeMyid=is_local_resident",
				valueField: 'code',
				textField: 'text',
				editable : false,
				required : true
			});
		   
		   // 渲染婚姻状况的下拉框
		   $("input[name='marriageType']").combobox({
				width:171,
				url:"common/commonAction!findTextArr.action?codeMyid=marriage_type",
				valueField: 'code',
				textField: 'text',
				editable : false,
				required : true
			});
		   
		   // 渲染经营状态的下拉框
		   $("input[name='comOperStatus']").combobox({
				width:171,
				url:"common/commonAction!findTextArr.action?codeMyid=com_oper_status",
				valueField: 'code',
				textField: 'text',
				editable : false,
				required : true
			});
		   
		   // 渲染信用情况的下拉框
		   $("input[name='creditStatus']").combobox({
				width:171,
				url:"common/commonAction!findTextArr.action?codeMyid=credit_status",
				valueField: 'code',
				textField: 'text',
				editable : false,
				required : true
			});
		   
		   // 渲染电核情况的下拉框
		   $("input[name='phoneCheckStatus']").combobox({
				width:171,
				url:"common/commonAction!findTextArr.action?codeMyid=phone_check_status",
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
		   
		   // 渲染资质从平的下拉框
		   $("input[name='qulificationStatus']").combobox({
				width:171,
				url:"common/commonAction!findTextArr.action?codeMyid=qulification_status",
				valueField: 'code',
				textField: 'text',
				editable : false,
				required : true
			});
		   
		   // 信贷方式
		  /*  $("input[name='auditWay']").combobox({
				width:171,
				url:"common/commonAction!findTextArr.action?codeMyid=audit_way",
				valueField: 'code',
				textField: 'text',
				editable : false,
				required : true
			}); */
			
			// 渲染贷款期限
		   $("input[name='loanPeriodType']").combobox({
				url : "common/commonAction!findTextArr.action?codeMyid=loan_period_type",
				valueField : 'code',
				textField : 'text',
				editable : false,
				required : true
		   });
		   
			// 获取申请报告的信息
			$.getJSON("creditAuditReport/creditAuditReportAction!findCreditAuditReportByLoanOrderId.action",
				{"loanOrderId":$row.loanOrderId},
				function(data){
					$creditAuditReport = data;
					loadAssets($creditAuditReport.assets);
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
	
	// 保存资产情况的信息
	function saveAssets(obj) {
		$(obj).parents("form:first").form('submit',{
			url : "assets/assetsAction!saveAssets.action",
			onSubmit : function(param) {
				var isValid = $(this).form('validate');
				if (isValid){
					param.carId = $creditAuditReport.carId;
				}
				return isValid; // 返回false终止表单提交
			},
			success : function(data) {
				data = $.parseJSON(data);
				loadAssets(data.data);
				alertMsg(data);
				$.messager.progress('close'); // 如果提交成功则隐藏进度条
			}
		});
	}
	
	// 渲染资产信息
	function loadAssets(data){
		if(!$.isEmptyObject(data)){
 			$("#assets-form").form("load",data);
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
	<!-- 资产 -->
	<div title="资产">
		<form id="assets-form" method="post" style="height:580px;">
			<input name="assetId" class="easyui-validatebox" hidden="true">
			<table class="table" style="margin-top: 10px;width:100%;" cellpadding="5px;">
				<tr>
					<td colspan="4"><span style="font-weight: bold;font-size: 14px;width:60px;">[资产详情]</span></td>
				</tr>
				<tr>
					<th>房产:</th>
					<td><input name="realEstate" class="easyui-textbox easyui-validatebox" data-options='required:true'/></td>
					<th>车产:</th>
					<td><input name="vehicle" class="easyui-textbox easyui-validatebox" data-options='required:true'/></td>
				</tr>
				<tr>
					<th>同行业:</th>
					<td><input name="theSameIndustry" class="easyui-textbox easyui-validatebox" data-options='required:true'/></td>
				</tr>
				<tr>
					<td  style="text-align: right;" colspan="4" align="right">
						<a href="javascript:void(0);" onclick="saveAssets(this)" class="easyui-linkbutton" iconCls="icon-save">保存</a>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<!-- 资质分析 -->
	<div title="资质分析">
		<form id="firstAuditReport-form" method="post" style="height:580px;">
			<input name="firsId" hidden="true" class="easyui-validatebox">
			<table class="table" style="margin-top: 10px;width:100%;" cellpadding="5px;">
				<tr>
					<td colspan="6"><span style="font-weight: bold;font-size: 14px;width:60px;">[初审资质分析详情]</span></td>
				</tr>
				<tr>
					<th>行业类型:</th>
					<td><input name="industryType" style="width:100px;" class="easyui-validatebox" data-options="required:true"/></td>
					<th>经营年限:</th>
					<td><input name="comOperDuration" style="width:100px;" class="easyui-validatebox easyui-numberbox" data-options="min:0,required:true"/>年</td>
					<th>经营状态:</th>
					<td><input name="comOperStatus" style="width: 100px;"class="easyui-validatebox easyui-combobox"/></td>
				</tr>
				<tr>
					<th>婚姻情况:</th>
					<td><input name="marriageType" style="width: 100px;" class="easyui-validatebox easyui-combobox"/></td>
					<th>是否本地:</th>
					<td style="width: 100px;"><input name="isLocalRes" style="width: 100px;" class="easyui-validatebox easyui-combobox"/></td>
					<th>征信情况:</th>
					<td><input name="creditStatus" style="width: 100px;" class="easyui-validatebox easyui-combobox" /></td>
				</tr>
				<tr>
					<th>电核情况:</th>
					<td><input name="phoneCheckStatus" style="width: 100px;" class="easyui-validatebox easyui-combobox"/></td>
					<th>信访情况:</th>
					<td><input name="visitStatus" style="width: 100px;" class="easyui-validatebox easyui-combobox"/></td>
					<th>资质总评:</th>
					<td><input name="qulificationStatus" style="width: 100px;" class="easyui-validatebox easyui-combobox"/><!-- <a href="#">查看细则</a></td> -->
				</tr>
				<tr>
					<th>建议金额:</th>
					<td><input name="suggestAmt" type="text" class="easyui-textbox easyui-validatebox" data-options="required:true"/></td>
					<th>贷款期限:</th>
					<td colspan="2"><input name="loanPeriodType"  class="easyui-validatebox easyui-textbox easyui-combobox" />月</td>
				</tr>
				<tr>
					<th>备注:</th>
					<td colspan="6"><textarea name="note" style="width:95%;height:70px;" class="easyui-validatebox easyui-validatebox" data-options="required:true,validType:'length[0,300]'"></textarea></td>
				</tr>
				<tr>
					<th>初审人:</th>
					<td>
						<input class="easyui-validatebox easyui-textbox"  value="<%=shiroUser.getUser().getName() %>"  disabled="disabled"/>
						<input id="firsPersonnel" name="firsPersonnel" hidden="true"  class="easyui-validatebox easyui-textbox" value="<%=shiroUser.getUserId() %>"/>
					</td>
					<th>外访人:</th>
					<td>
						<input name="outSurver" class="easyui-textbox"  />
					</td>
					<th>审核日期</th>
					<td><input id="finaDate" name="firsDate"  class="easyui-validatebox easyui-datebox" data-options="editable:false" /></td>
				</tr>
				<tr>
					<th>初审人员意见:</th>
					<td colspan="6"><textarea name="firsPersSugg" style="width:95%;height:70px;" class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'length[0,10]'"></textarea></td>
				</tr>
				<tr>
					<th>初审部门意见:</th>
					<td colspan="6"><textarea name="firsDepSugg" style="width:95%;height:70px;" class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'length[0,512]'"></textarea></td>
				</tr>
				
				<tr>
					<th>初审资质分析说明:</th>
					<td colspan="6"><textarea name="description" style="width:95%;height:70px;" class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'length[0,1024]'"></textarea></td>
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
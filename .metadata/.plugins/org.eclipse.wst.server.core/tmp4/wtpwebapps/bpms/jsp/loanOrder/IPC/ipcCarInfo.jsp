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
	$(function(){
		$("#carInfoForm input[name='loanOrderId']").val($row.loanOrderId);
		
		$.ajax({
			url:"carInfo/carInfoAction!findCarInfoByOrderId.action",
			type:"post",
			data:{"loanOrderId":$row.loanOrderId},
			success:function(data){
				if(data){
					$("#carInfoForm").form("load",data);
				}
			}
		});
		
		$.ajax({
			url:"loaner/loanerAction!queryLoaner.action",
			type:"post",
			data:{"loanerId":$row.loanerId},
			success:function(data){
				$("#carInfoForm input[name='loanerPostalAddr']").val(data.curAddress);
				$("#carInfoForm input[name='loaner']").val(data.name);
				$("#carInfoForm input[name='loanerIdno']").val(data.idNo);
				$("#carInfoForm input[name='loanerTel']").val(data.mobileTel);
			}
		});
		
	});

	
	function saveCarInformation(){
		$("#carInfoForm").form("submit",{
			url:"carInfo/carInfoAction!saveCarInfo.action",
			onSubmit:function(){
				var validate = $(this).form("validate");
				return validate;
			},
			success:function(data){
				data = $.parseJSON(data);
				$("#carinfoId").val(data.data.carinfoId);
				carInfoDlg.dialog("close");
				$.messager.show({
					title:"提示",
					msg:data.message,
					showType:"slide",
					timeout:1000
				});
			}
		});
	}
</script>
	<div>
		<form id="carInfoForm" method="post">
			<input id="carinfoId" name="carinfoId" type="hidden">
			<input name="loanOrderId" type="hidden">
			<input name="loaner" type="hidden">
			<input name="loanerIdno" type="hidden">
			<input name="loanerPostalAddr" type="hidden">
			<input name="loanerTel" type="hidden">
			<table cellpadding="5">
				<tr>
					<th>
						品牌型号
					</th>
					<td>
						<input name="carName" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,128]'">
					</td>
					<th>
						车牌号
					</th>
					<td>
						<input name="carLicense" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,10]'">
					</td>
				</tr>
				
				<tr>
					<th>
						发动机号
					</th>
					<td>
						<input name="engineNo" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,15]'">
					</td>
					<th>
						车架号
					</th>
					<td>
						<input name="carFrameNo" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[0,20]'">
					</td>
				</tr>
				
				<tr>
					<th>
						车抵金额
					</th>
					<td>
						<input name="vehicleMortgageAmt" class="easyui-numberbox easyui-validatebox" data-options="min:0,max:999999999999,required:true,precision:2,groupSeparator:','">元
					</td>
				</tr>
				
				<tr>
					<td colspan="6" style="text-align: right;">
						<a href="javascript:void(0);" onclick="saveCarInformation();"	class="easyui-linkbutton" iconCls="icon-save">保存</a>
					</td>
				</tr>
			</table>
		</form>
	</div>

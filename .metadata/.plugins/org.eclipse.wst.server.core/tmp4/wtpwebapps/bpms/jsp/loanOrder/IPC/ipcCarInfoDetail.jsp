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
		$.ajax({
			url:"carInfo/carInfoAction!findCarInfoByOrderId.action",
			type:"post",
			data:{"loanOrderId":$row.loanOrderId},
			success:function(data){
				if(data){
					$("#noCarInfo").hide();
					$("#carInfoForm").form("load",data);
					$("#carInfoForm input").attr("disabled","disabled");
				}else{
					$("#carInfoForm").hide();
				}
			}
		});
	});
</script>
	<div>
		<div id="noCarInfo" style="padding:40px 0 0 140px;">
			<font size="6" style="text-align: center;font-weight: bold;box-shadow:3px 3px 5px 3px;">暂无车辆信息</font>
		</div>
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
						<input name="carName" class="easyui-textbox easyui-validatebox" data-options="required:true">
					</td>
					<th>
						车牌号
					</th>
					<td>
						<input name="carLicense" class="easyui-textbox easyui-validatebox" data-options="required:true">
					</td>
				</tr>
				
				<tr>
					<th>
						发动机号
					</th>
					<td>
						<input name="engineNo" class="easyui-textbox easyui-validatebox" data-options="required:true">
					</td>
					<th>
						车架号
					</th>
					<td>
						<input name="carFrameNo" class="easyui-textbox easyui-validatebox" data-options="required:true">
					</td>
				</tr>
				
				<tr>
					<th>
						车抵金额
					</th>
					<td>
						<input name="vehicleMortgageAmt" class="easyui-numberbox" data-options="precision:2,groupSeparator:','">元
					</td>
				</tr>
			</table>
		</form>
	</div>

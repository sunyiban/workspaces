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
    		url:"loanContract/loanContractAction!findLoanContractRelativeInfo.action",
    		type:"post",
    		data:{"loanOrderId":$row.loanOrderId},
    		success:function(data){
    			if(data.status){
    				$("#contractDetailForm").form("load",data.data);
    			}else{
    				$.messager.alert('提示',data.message,'error');
    			}
    		}
    	});
		
		$.ajax({
			type : "POST",
			url : "loanContract/loanContractAction!findBankAccountInfo.action",
			data : {"loanOrderId":$row.loanOrderId},
			success : function(data) {
				if (data) {
					var htmlC = "";
					$.each(data,function(i,item) {
						if (item.primaryBackup=="A") {
							htmlC += "<tr><th>主卡</th></tr>";
						} else {
							htmlC += "<tr><th>副卡</th></tr>";
						}
						htmlC += "<tr><th>开户银行全称</th>";
						htmlC += "<td><input name='bankNames' class='easyui-textbox' disabled='disabled' value='"+item.bankName+"'/></td>";
						htmlC += "<th>开户行户名</th>";
						htmlC += "<td><input name='accountNames' class='easyui-textbox' disabled='disabled' value='"+item.accountName+"'/></td>";
						htmlC += "<th>开户行账号</th>";
						htmlC += "<td><input name='accountNums' class='easyui-textbox' disabled='disabled' value='"+item.accountNum+"'/></td>";
						htmlC += "</tr>";
					});
				
					$("#contractDetailForm table").append(htmlC);
					$.parser.parse($("#contractDetailForm"));
				}
			}
		});
		
		$("#contractDetailForm input").attr("disabled","disabled");
	});
	
	function saveBankInfo() {
		$("#contractDetailForm").form('submit', {
			url : "loanContract/loanContractAction!modifyBankInfo.action",
			onSubmit : function(param) {
				var isValid = $(this).form('validate');
				return isValid; // 返回false终止表单提交
			},
			success : function(data) {
				data = $.parseJSON(data);
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
<div >
	<form id="contractDetailForm" method="post">
			<input name="lcId" type="hidden">
			<table cellpadding="8px;" style="width:100%;height:100%;">
				<tr>
					<th>姓名</th>
					<td><input name="loaner" class="easyui-textbox" /></td>
					<th>身份证号</th>
					<td><input name="loanerIdno" class="easyui-textbox" /></td>
					<th>贷款额度(元)</th>
					<td><input name="loanEdu" class="easyui-numberbox" data-options="precision:2,groupSeparator:','"  /></td>
				</tr>
				
				<tr>
					<th>联系电话</th>
					<td><input name="loanerTel" class="easyui-textbox"  /></td>
					<th>签字日期</th>
					<td><input name="loanerSignDate" class="easyui-textbox"  /></td>
					<th>贷款期数</th>
					<td><input name="loanPeriods" class="easyui-textbox"  /></td>
				</tr>
				
				<tr>
					<th>出借人</th>
					<td><input name="lender" class="easyui-textbox"  /></td>
					<th>出借人身份证号</th>
					<td><input name="lenderIdno" class="easyui-textbox"  /></td>
				</tr>
				
				<tr>
					<th>贷款用途</th>
					<td><input name="purpose" class="easyui-textbox"  /></td>
					<th>通讯地址</th>
					<td colspan="3"><input name="loanerPostalAddr" class="easyui-textbox"  style="width:100%;"/></td>
				</tr>
				
				<tr>
					<th>月利率</th>
					<td><input name="monthlyRate" class="easyui-textbox"  /></td>
					<th>贷款利息(元)</th>
					<td><input name="loanInterest" class="easyui-numberbox" data-options="precision:2,groupSeparator:','" /></td>
					<th>月还款额度(元)</th>
					<td><input name="monthlyRepayment" class="easyui-numberbox" data-options="precision:2,groupSeparator:','" /></td>
				</tr>
				
				<!-- <tr>
					<th>贷款人开户银行全称</th>
					<td><input name="loanerBankName" class="easyui-textbox" /></td>
					<th>贷款人开户行户名</th>
					<td><input name="loanerActName" class="easyui-textbox" /></td>
					<th>贷款人开户行账号</th>
					<td><input name="loanerActNum" class="easyui-textbox" /></td>
				</tr> -->
			</table>
		</form>
	<!-- <div title="还款管理服务说明书">
		
	</div>
	
	<div title="借款协议">
		
	</div>
	
	<div title="上海资信授权书">
	
	</div>
	
	<div title="委托划款授权书">
	
	</div>
	
	<div title="信用咨询及管理服务协议">
	
	</div> -->
</div>

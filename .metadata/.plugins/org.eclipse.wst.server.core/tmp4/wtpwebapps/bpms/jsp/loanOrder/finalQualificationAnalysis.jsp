<%@page import="com.bpms.util.Constants"%>
<%@page import="com.bpms.shiro.ShiroUser"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
 ShiroUser shiroUser = Constants.getCurrendUser();
%>
<script type="text/javascript">
	   // 渲染贷款期限
	   $("input[name='loanPeriodType']").combobox({
			url : "common/commonAction!findTextArr.action?codeMyid=loan_period_type",
			valueField : 'code',
			textField : 'text',
			onSelect:function(){
				 calculate();
			},
			onLoadSuccess : function(){
	            var val = $(this).combobox("getData");
	            for (var item in val[0]) {
	                if (item == "code") {
	                    $(this).combobox("select", val[0][item]);
	                }
	            }
				},
	   });

		// 渲染月服务费率
	   $("input[name='monthServiceFeeRate']").combobox({
			url : "common/commonAction!findTextArr.action?codeMyid=month_service_fee_rate",
			valueField : 'code',
			textField : 'text',
			onSelect:function(){
				 calculate();
			},
			onLoadSuccess : function(){
	            var val = $(this).combobox("getData");
	            for (var item in val[0]) {
	                if (item == "code") {
	                    $(this).combobox("select", val[0][item]);
	                }
	            }
				},
	   });
		
	 //查询系统参数
		function checkSysParameter(paramCode){
			var datas = "";
			$.ajax({
				url:"sysParameter/sysParameterAction!findSysParameter.action",
				type:"post",
				async:false,
				data:{"parmCode":paramCode},
				success:function(data){
					datas = data.parmValue;
				},
				error:function(){
					
				}
			});
			return datas;
		}
		$("input[name='loanInterestRate']").numberbox({    
		    value:checkSysParameter('loan_rate'),
		    disabled:true
		}); 
		
		
		// 保存资质分析的信息
		function saveFinalAuditReport(result,object){
			// 验证备注信息是否已经填写
			if($("#comment").val()==""){
				$.messager.alert("提示","请填写备注信息后再进行提交!","warning")
				return false;
			}
			// 确认是否提交
			$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){
				if (r){
						$(object).parents("form:first").form('submit', {
							url : "finalAuditReportAction/finalAuditReportAction!saveFinalAuditReport.action",
							onSubmit : function(param) {
								var isValid = $(this).form('validate');
								if (isValid){
									param.loanOrderId = $row.loanOrderId;
								}
								return isValid; // 返回false终止表单提交
							},
							success : function(data) {
								data = $.parseJSON(data);
								loadFinalAuditReport(data.data);
								alertMsg(data);
								$.messager.progress('close'); // 如果提交成功则隐藏进度条
								// 确认是否提交
								submitTask(result);
							}
					});
				}
			});
		}
		
		// 计算金额
		function calculate() {
			// 合同金额
			var contractLoanAmount = Number($("#contractLoanAmount").val());
			// 贷款期限
			var loanPeriodType = Number($("#loanPeriodType").combobox("getText"));
			// 月服务汇率
			var monthServiceFeeRate = Number(parseFloat($("#monthServiceFeeRate")
					.combobox("getText")) / 100.0);
			// 利息
			/* var loanInterestRate = Number($("#loanInterestRate")
					.combobox("getText")); */
			var loanInterestRate = Number($("#loanInterestRate").numberbox(
					'getValue'));
			// 信访费用
			var visitFee = Number($("#visitFee").val());
			// 计算金额
			if (contractLoanAmount != "" && loanPeriodType != ""
					&& monthServiceFeeRate != "" && loanInterestRate != ""
					&& visitFee != "") {
				// 实放金额 = 合同金额-(合同金额*(服务汇率*贷款期限)+信访费用)
				var actualLoanAmount = contractLoanAmount
						- (contractLoanAmount
								* (monthServiceFeeRate * loanPeriodType) + visitFee);
				// 月还款额 = (合同金额/贷款期限)+合同金额*利息
				var monthRepay = (contractLoanAmount / loanPeriodType)
						+ contractLoanAmount * loanInterestRate;
				$("#actualLoanAmount").numberbox("setValue", actualLoanAmount);
				$("#monthRepay").numberbox("setValue", monthRepay);
			}
		}
</script>
<!-- 终审资质分析 -->	
	<div class="easyui-tabs" style="fit:true;">
		<div title="资质分析">
			<form id="finalAuditReport-form" method="post">
				<input name="finaId" hidden="true" class="easyui-validatebox">
				<table class="table" style="margin-top: 10px;width:100%;" cellpadding="5px;">
					<tr>
						<td colspan="6"><span style="font-weight: bold;font-size: 14px;width:60px;">[终审资质分析详情]</span></td>
					</tr>
					<tr>
						<th>合同金额:</th>
						<td><input id="contractLoanAmount" name="contractLoanAmount"  class="easyui-validatebox easyui-numberbox" data-options="min:0,precision:2,required:true"  onblur="calculate();"/>元</td>
						<th>贷款期限:</th>
						<td><input id="loanPeriodType" name="loanPeriodType"  class="easyui-validatebox easyui-textbox easyui-combobox"  onchange="calculate();" />月</td>
						<th>月服务汇率:</th>
						<td><input id="monthServiceFeeRate" name="monthServiceFeeRate" class="easyui-validatebox easyui-combobox"  onchange="calculate();"/></td>
					</tr>
					<tr>
						<th>利息:</th>
						<td><input id="loanInterestRate" name="loanInterestRate"  class="easyui-validatebox easyui-numberbox" data-options="min:0,precision:2,required:true" onchange="calculate();"/></td>
						<th>信访费用:</th>
						<td ><input id="visitFee" name="visitFee"  class="easyui-validatebox easyui-numberbox"  data-options="required:true,min:0,precision:2,max:999.99,required:true" onblur="calculate();"/>元</td>
					</tr>
					<tr>
						<th>实放金额:</th>
						<td><input id="actualLoanAmount" name="actualLoanAmount"  class="easyui-validatebox easyui-textbox easyui-numberbox" data-options="disabled:true,min:0,precision:2" />元</td>
						<th>月还款额:</th>
						<td><input id="monthRepay" name="monthRepay"  class="easyui-validatebox easyui-textbox easyui-numberbox" data-options="disabled:true,min:0,precision:2" /></td>
					</tr>
					<tr>
						<th>终审人:</th>
						<td>
							<input class="easyui-validatebox easyui-textbox" value="<%=shiroUser.getUser().getName() %>" disabled="disabled"/>
							<input id="finaPersonnel" name="finaPersonnel" hidden="true" value="<%=shiroUser.getUserId() %>" class="easyui-validatebox easyui-textbox"/>
						</td>
						<th>终审日期</th>
						<td><input id="finaDate" name="finaDate"  class="easyui-validatebox easyui-datebox" data-options="editable:false"/></td>
					</tr>
					<tr>
						<th>终审人员意见:</th>
						<td colspan="6"><textarea id="finaPersSugg" name="finaPersSugg" style="width:600px;height:70px;" class="easyui-validatebox easyui-textbox" data-options="required:true"></textarea></td>
					</tr>
					<tr>
						<th>终审资质分析说明:</th>
						<td colspan="6"><textarea id="description" name="description" style="width:600px;height:70px;" class="easyui-validatebox easyui-textbox" data-options="required:true"></textarea></td>
					</tr>
					<tr>
						<td colspan="6" style="text-align: right;">
							<a href="javascript:void(0);" onclick="saveFinalAuditReport('FinalAuditThrough',this);"	class="easyui-linkbutton" iconCls="icon-save">保存</a>
						</td>
					</tr>
				</table>
			</form>
		</div>	
	</div>	

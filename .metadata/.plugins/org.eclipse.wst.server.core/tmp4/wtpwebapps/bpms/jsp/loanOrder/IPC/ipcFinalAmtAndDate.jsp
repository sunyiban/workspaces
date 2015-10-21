<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
	th {
		text-align: left;
	}
	textarea {
		width:100%;
	}
</style>
<script type="text/javascript">
	
	$(function(){
	   // 渲染贷款期限
	   $("input[name='loanPeriodType']").combobox({
			url : "common/commonAction!findTextArr.action?codeMyid=loan_period_type",
			valueField : 'code',
			textField : 'text',
			required:true,
			editable:false
	   });
	   
	   //查询贷款额度
	   $.ajax({
		   url : "microcreditOpinion/microcreditOpinionAction!findFinalLoanAmt.action",
		   type:"post",
		   data:{"loanOrderId":$row.loanOrderId},
		   success:function(data){
			  console.info(data);
			  $("#oralAmt").val(data);
		   }
	   });
	});
	
	function saveFinalAmtDate(){
		var loanPeriodType = $("#finalLoanPeriodType").combobox("getValue");
		var finalAmt = $("#finalAmt").val();
		$("#saveFinalAmtDateForm").form("submit",{
			url : "microcreditOpinion/microcreditOpinionAction!saveFinalAmtDate.action",
			onSubmit : function(param) {
				var isValid = $(this).form('validate');
				if (isValid){
					param.loanOrderId = $row.loanOrderId;
				}
				return isValid; // 返回false终止表单提交
			},
			success : function(data) {
				faad.dialog("close");
				data = $.parseJSON(data);
				$.messager.show({
					title:"提示",
					msg:data.message,
					showType:"slide",
					tiemout:1000
				});
			}
		});
	}
	
</script>

	<div id="" >
		<form id="saveFinalAmtDateForm" method="post">
			<div style="width:92%;text-align: center;padding-left:20px;">
				<table cellpadding="8">
					<tr>
						<th>
							原贷款额度
						</th>
						<td  colspan="2">
							<input id="oralAmt" class="easyui-textbox" readonly="readonly"/>元
						</td>
					</tr>
				
					<tr>
						<th>
							修改贷款额度
						</th>
						<td  colspan="2">
							<input id="finalAmt" class="easyui-validatebox easyui-textbox" name="finalAmt" data-options="validType:'mDouble',required:true" />
						</td>
					</tr>
					
					<tr>
						<th>
							期限
						</th>
						<td colspan="2">
							<input id="finalLoanPeriodType" class="easyui-textbox easyui-validatebox" name="loanPeriodType" />
						</td>
					</tr>
				</table>
			</div>
		</form>
		<div id="upload_form" style="width: 90%; height: 30px; text-align: right;">
			<a href="javascript:void(0);" class="easyui-linkbutton" onclick="saveFinalAmtDate();">保存</a>
		</div> 
	</div>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="text/javascript">
var userData;
$(function(){
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
	
   $("#microcreditOpinionDlg input[name='operatorA']").combobox({
		valueField : 'code',
		textField : 'text',
		required:true,
		url:'users/usersAction!findUsers.action',
		editable:false ,
    }); 
   
   $("#microcreditOpinionDlg input[name='operatorB']").combobox({
		valueField : 'code',
		textField : 'text',
		required:true,
		width:250,
		url:'users/usersAction!findUsers.action',
		editable:false ,
		multiple:true
   }); 
	
	//还款期限
	$("#microcreditOpinionDlg input[name='adviceLoanPeriod']").combobox({
		valueField : 'code',
		textField : 'text',
		required:true,
		url:'common/commonAction!findTextArr.action?codeMyid=loan_period_type',
		editable:false ,
		onLoadSuccess : function(){
			var val = $(this).combobox("getData");
			if(!$.isEmptyObject(val)){
				//userData = val;
                $(this).combobox("select", val[0]["code"]);
			}
		},
    });
	
	// 信贷方式
   $("input[name='auditWay']").combobox({
		width:171,
		url:"common/commonAction!findTextArr.action?codeMyid=audit_way",
		valueField: 'code',
		textField: 'text',
		onLoadSuccess : function(){
        	var val = $(this).combobox("getData");
			if(!$.isEmptyObject(val)){
              	$(this).combobox("select", val[0]["code"]);
            }
		}
	});
	
 	//婚姻状况
	$("input[name='marriageType']").combobox({
	   	url:"common/commonAction!findTextArr.action?codeMyid=marriage_type",
		valueField: 'code',
		textField: 'text',
		editable:false,
		onLoadSuccess:function(){
			$(this).combobox("select",$row.marriageType);
		},
	});
	
	// 微带呈报意见表信息
	loadData();
})

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
		}
	});
	return datas;
}

// 保存微保意见
function saveMicrocreditOpinion(formId,dlgId){
	/* var isCheck = false;
	$("#checkBoxDiv input[type='checkbox']").each(function() {
		if($(this).attr("checked")){
			isCheck = true;
		}
	});
	if(isCheck==false){
		$.messager.alert("提示","至少选择一条风险控制措施","info");
		return false;
	} */
	// 确认是否提交
	$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){
		if (r){
			$("#"+formId).form('submit', {
				url : "microcreditOpinion/microcreditOpinionAction!saveMicrocreditOpinion.action",
				onSubmit : function(param) {
					var isValid = $("#"+formId).form('validate');
					return isValid; // 返回false终止表单提交
				},
				success : function(data) {
					data = $.parseJSON(data);
					$("#"+formId+" input[name='mcbrId']").val(data.data);
					submitTask("IPCCensorThrough");
				}
			});
		}
	});
}

// 获取微贷承包意见表的信息
function loadData(){
	//$("#microcreditOpinionForm").form("clear");
	//加载微贷业务表
	//$("#microcreditOpinionForm").form("load","microcreditOpinion/microcreditOpinionAction!findMicrocreditOpinionByOid.action?loanOrderId="+$row.loanOrderId);
	$.ajax({
		url:"microcreditOpinion/microcreditOpinionAction!findMicrocreditOpinionByOid.action",
		type:"post",
		data:{"loanOrderId":$row.loanOrderId},
		success:function(data){
			if(data) {
				data.operatorB = data.operatorB.replace(/\s/g,"").split(",");
				$("#microcreditOpinionForm").form("load",data);
			}
		}
	});
	$("#microcreditOpinionForm :input").removeAttr("disabled");
	
	// 组织机构的信息--进件城市
	$.ajax({
		type : "POST",
		url : "loanOrder/loanOrderAction!findLoanCityByOrderId.action",
		data : {"loanOrderId":$row.loanOrderId},
		success : function(data) {
			if(data){
				$("#microcreditOpinionDlg input[name='loanCtiy']").val("IPC项目组-"+data.fullName);
			}
		}
	});
	$("#microcreditOpinionDlg input[name='name']").val($row.name);
	$("#microcreditOpinionDlg input[name='loanOrderId']").val($row.loanOrderId);
	$("#microcreditOpinionDlg input[name='idNo']").val($row.idNo);
	$("#microcreditOpinionDlg input[name='purpose']").val($row.purpose);
	//checkSysParameter
	$("#microcreditOpinionDlg input[name='loanMthd']").val(checkSysParameter('loan_mthd'));
	$("#microcreditOpinionDlg input[name='adviceRepayMthd']").val(checkSysParameter('repay_mthd'));
	$("#microcreditOpinionDlg input[name='loanRate']").val(checkSysParameter('loan_rate'));
	$("#microcreditOpinionDlg input[name='loanRateText']").val(Number(checkSysParameter('loan_rate'))*100+"%");
	$("#microcreditOpinionDlg input[name='counselingRate']").val(checkSysParameter('counseling_rate'));
	$("#microcreditOpinionDlg input[name='counselingRateText']").val(Number(checkSysParameter('counseling_rate'))*100+"%");
	$("#microcreditOpinionDlg input[name='collectionMthd']").val(checkSysParameter('collection_mthd'));
	
	//根据订单ID 共同贷款人 名称 ID
	$.ajax({
		url : "loanerJoint/loanerJointAction!findLoanerJointByOrderId.action",
		data : {
			loanOrderId : $row.loanOrderId
		},
		async: false,
		type : "post",
		success : function(data) {
			if(data){
				$("#microcreditOpinionDlg input[name='coborrowerName']").val(data.name);
				$("#microcreditOpinionDlg input[name='coborrowerIdno']").val(data.idNo);
			}
		}
	});
}


</script>
<!-- 微贷业务呈报意见表E -->		
<div id="microcreditOpinionDlg">
	<form id="microcreditOpinionForm" method="post">
		<input name="mcbrId" type="hidden" />
		<div>
			<table cellpadding="8px;" style="width:100%;">
				<tr>
					<th>
						借款人
					</th>
					<td>
						<input readonly="readonly" style="background-color: #EBEBE4;" class="easyui-validatebox easyui-textbox" name="name"  type="text" />
						<input name="loanOrderId" type="hidden" />
					</td>
					<th>
						身份证号
					</th>
					<td>
						<input readonly="readonly" style="background-color: #EBEBE4;" class="easyui-validatebox easyui-textbox" name="idNo"  type="text" />
					</td>
					
					<th>
						婚姻状况
					</th>
					<td><input name="marriageType" class="easyui-combobox" disabled="disabled"/></td>
				</tr>
				
				<tr>
					<th>
						共同借款人
					</th>
					<td>
						<input readonly="readonly" style="background-color: #EBEBE4;" class="easyui-validatebox easyui-textbox" name="coborrowerName"  type="text" />
					</td>
					<th>
						身份证号
					</th>
					<td>
						<input readonly="readonly" style="background-color: #EBEBE4;" class="easyui-validatebox easyui-textbox" data-options="validType:'idcard'" name="coborrowerIdno"  type="text" />
					</td>
					<th>
						利率(年)
					</th>
					<td>
						<input name="loanRate"  type="hidden" />
						<input readonly="readonly" style="background-color: #EBEBE4;" class="easyui-validatebox easyui-textbox" name="loanRateText"/>
					</td>
				</tr>
				
				<tr>
					<th>
						放款方式
					</th>
					<td>
						<input readonly="readonly" style="background-color: #EBEBE4;" class="easyui-validatebox easyui-textbox" name="loanMthd"  type="text" />
					</td>
					<th>
						收取方式
					</th>
					<td>
						<input readonly="readonly" style="background-color: #EBEBE4;" class="easyui-validatebox easyui-textbox" name="collectionMthd"  type="text" />
					</td>
					
					<th>
						还款方式
					</th>
					<td>
						<input readonly="readonly" style="background-color: #EBEBE4;" class="easyui-validatebox easyui-textbox" name="adviceRepayMthd"  type="text" />
					</td>
				</tr>
				
				<tr>
					<th>
						咨询服务费
					</th>
					<td>
						<input name="counselingRate"  type="hidden" />
						<input readonly="readonly" style="background-color: #EBEBE4;" class="easyui-validatebox easyui-textbox" name="counselingRateText"  type="text" />
					</td>
					
					<th>
						经办机构/部门
					</th>
					<td><input readonly="readonly" style="background-color: #EBEBE4;" class="easyui-validatebox easyui-textbox" name="loanCtiy"  type="text"/></td>
				
					<th>
						贷款用途
					</th>
					<td>
						<input readonly="readonly" style="background-color: #EBEBE4;" class="easyui-validatebox easyui-textbox" name="purpose"  type="text" value=""/>
					</td>
				</tr>
				
				<tr>
					<th>
						建议金额(元)
					</th>
					<td>
						<input class="easyui-validatebox easyui-numberbox" data-options="required:true,min:0,max:999999999999,precision:2,groupSeparator:','" name="adviceLoanAmt" />
					</td>
					<th>
						期限(月)
					</th>
					<td>
						<input class="easyui-validatebox easyui-textbox easyui-combobox" name="adviceLoanPeriod" />
					</td>
					<th>信贷方式:</th>
					<td>
						<input name="auditWay" class="easyui-combobox"/>
					</td>
				</tr>
				
				<tr>
					<th>
						经办人
					</th>
					<td colspan="4">
						A:<input id="operatorA" class="easyui-validatebox easyui-textbox easyui-combobox" name="operatorA"  />&nbsp;&nbsp;&nbsp;
						B:<input id="operatorB" class="easyui-validatebox easyui-textbox easyui-combobox" name="operatorB" /> 
					</td>
				</tr>
				<tr>
					<th>
						具体措施如下:
					</th>
				</tr>
				<tr>
					<td colspan="6">
						<textarea class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'length[0,1024]'" name="specificMeasures" style="width:99%;height:270px;resize: none;"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<div id="upload_form" style="width: 100%; height: 30px; text-align: right;">
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="saveMicrocreditOpinion('microcreditOpinionForm');">提交</a>
						</div> 
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<!-- 微贷业务呈报意见表E -->	
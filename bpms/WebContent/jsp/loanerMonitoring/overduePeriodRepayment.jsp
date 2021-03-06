<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>">
    <title>逾期还款</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="../../layout/script.jsp"></jsp:include>
 
<style type="text/css">
	#customerRepaymentDetailForm input{
		border: none;
	}
</style>
<script type="text/javascript">
var $lcid;
$(function(){
	$(window).resize(function(){  
        $("#customerRepaymentDetailTab").datagrid("resize",{  
			height : $(window).height()-160,
        	width : 'auto'
        });                
    });
	
	$("#customerRepaymentDetailTab").datagrid({
		url : "loanCustRepaymentDetail/loanCustRepaymentDetailAction!findOverduePeriodCustRepaymentDetails.action",
		width : 'auto',
		height : $(window).height()-160, 
		pagination:true,
		rownumbers:true,
		border:true,
		singleSelect:true,
		nowrap:true,
		multiSort:false,
		queryParams:{"date":new Date()},
		columns : [ 
					  [
						  {title : '<strong>客户信息</strong>',width : 900,align : 'center',rowspan:1,colspan:8},
						  {title : '<strong>贷款期数详情</strong>',width :1400,align : 'center',rowspan:1,colspan:15},
						  {field : 'operat',title : '<strong>操作</strong>',width :80,align : 'center',rowspan:2,
				            	formatter:function(value,row,index){
				            		if(row.rpmtStatus=="已还款"){
				            			return "<span style='color:green;'>本期已还</span>";
				            		}else if(row.needMonthFee==0){
				            			return "<a href='javascript:void(0);' onclick='repayment("+index+");'>还款</a>　　" +
				            			   "<a href='javascript:void(0);' onclick='interestFree("+index+");'>免息</a>";
				            		}else{
				            			return "<a href='javascript:void(0);' onclick='repayment("+index+");'>还款</a>";
				            		}
				            	}  
			              }
					  ],
		              [ 
						  {field : 'loanCity',title : '进件城市',width : 60,align : 'center'},
						  {field : 'contractSignSite',title : '签署地',width : 60,align : 'center'},
						  {field : 'contractNo',title : '合同编号',width :280,align : 'center',
						  	formatter:function(value,row,index){
						  		return "<a href='javascript:void(0);' onclick='checkContractDetail("+index+");'>"+value+"</a>　　" ;
						  	}   
						  },
						  {field : 'contractSignDate',title : '合同签署日期',width : 100,align : 'center'},
						  {field : 'loaner',title : '客户姓名',width : 100,align : 'center',
							  formatter:function(value,row,index){
									return "<a href='javascript:void(0);' onclick=\"checkLoanerDetail('"+row.loanerId+"');\">"+value+"</a>";	  
								  }
						  },
						  {field : 'loanerIdno',title : '身份证号码',width : 150,align : 'center'},
						  {field : 'loanType',title : '贷款类型',width : 100,align : 'center'},
						  {field : 'loanEdu',title : '贷款金额',width : 100,align : 'center'},
						  {field : 'loanPeriods',title : '贷款期数(期)',width : 80,align : 'center'},
						  {field : 'monthlyRepayment',title : '月还金额(元)',width : 100,align : 'center'},
						  {field : 'loanBgDate',title : '放款日期',width : 100,align : 'center'},
						 
			              {field : 'periods',title : '期数',width : 30,align : 'center'},
			              {field : 'planRepmtDate',title : '应还款日期',width :100,align : 'center'},
			              {field : 'planRepmtAmt',title : '应还款金额(元)',width : 100,align : 'center'},
			              {field : 'realRepmtDate',title : '最后一次还款日期',width : 120,align : 'center'},
			              {field : 'realRepmtAmt',title : '实际还款金额(元)',width : 100,align : 'center'},
			              {field : 'repmtAct',title : '还款账号',width : 120,align : 'center'},
			              {field : 'overdueDays',title : '逾期天数(天)',width : 80,align : 'center'},
			              {field : 'lateFee',title : '滞纳金(元)',width : 100,align : 'center'},
			              {field : 'defaultInterest',title : '罚息(元)',width : 100,align : 'center'},
			              {field : 'freeInterestFee',title : '免息金额(元)',width : 100,align : 'center'},
			              {field : 'rpmtStatus',title : '还款状态',width : 80,align : 'center'},
			              {field : 'operator',title : '操作人员',width : 80,align : 'center'}
		              ] ]
		,onLoadSuccess:function(data){
			 	var rows = data.rows;
	            var mergeMap = {};
	            if(rows){
	            	for(var i=0;i<rows.length;i++){
	            		var contractNo = rows[i].contractNo
	            		if( contractNo in mergeMap ){
	            			mergeMap[contractNo].rowspan++;
	            		}else{
	            			mergeMap[contractNo]={"index":i,"rowspan":1}
	            		}
	            	}
	            }
	            for(var i in mergeMap){
	            	$(this).datagrid('mergeCells',{
	                    index: mergeMap[i].index,
	                    field: 'loanCity',
	                    rowspan: mergeMap[i].rowspan
	                });
	            	$(this).datagrid('mergeCells',{
	                    index: mergeMap[i].index,
	                    field: 'contractSignSite',
	                    rowspan: mergeMap[i].rowspan
	                });
	                $(this).datagrid('mergeCells',{
	                    index: mergeMap[i].index,
	                    field: 'contractNo',
	                    rowspan: mergeMap[i].rowspan
	                });
	                $(this).datagrid('mergeCells',{
	                    index: mergeMap[i].index,
	                    field: 'contractSignDate',
	                    rowspan: mergeMap[i].rowspan
	                });
	                $(this).datagrid('mergeCells',{
	                    index: mergeMap[i].index,
	                    field: 'loaner',
	                    rowspan: mergeMap[i].rowspan
	                });
	                $(this).datagrid('mergeCells',{
	                    index: mergeMap[i].index,
	                    field: 'loanerIdno',
	                    rowspan: mergeMap[i].rowspan
	                });
	                $(this).datagrid('mergeCells',{
	                    index: mergeMap[i].index,
	                    field: 'loanType',
	                    rowspan: mergeMap[i].rowspan
	                });
	                $(this).datagrid('mergeCells',{
	                    index: mergeMap[i].index,
	                    field: 'loanEdu',
	                    rowspan: mergeMap[i].rowspan
	                });
	                $(this).datagrid('mergeCells',{
	                    index: mergeMap[i].index,
	                    field: 'loanPeriods',
	                    rowspan: mergeMap[i].rowspan
	                });
	                $(this).datagrid('mergeCells',{
	                    index: mergeMap[i].index,
	                    field: 'monthlyRepayment',
	                    rowspan: mergeMap[i].rowspan
	                });
	                $(this).datagrid('mergeCells',{
	                    index: mergeMap[i].index,
	                    field: 'loanBgDate',
	                    rowspan: mergeMap[i].rowspan
	                });
	            }
		}
		  
	});
	
	//获取所属地区
	//loadOrganization();
	
	$("#isOverdue").combobox({
			valueField: 'value',   
	        textField: 'label',   
	        data: [{
				label: '全部',
				value: '0'
			},{
				label: '未逾期',
				value: '1'
			},{
				label: '逾期',
				value: '2'
			}],
			editable:false ,
			onLoadSuccess : function(){
			userData = $(this).combobox("getData");
			for (var item in userData[0]) {
	                if (item == "value") {
	                    $(this).combobox("select", userData[0][item]);
	                }
	            }
			}
	})
});

//获取所属地区
function loadOrganization(){
	$("#organizationId").combobox({
		valueField : 'code',
		textField : 'text',
		url:"orgz/organizationAction!findOrganizationCombo.action",
		editable:false,
	});
}

//重置查询表单
function resetCustomerRepaymentForm(){
	$("#overdueRepaymentForm").form("clear");
	$('#customerRepaymentDetailTab').datagrid('load',{});
}

//执行查询
function subCustomerRepaymentForm(){
	$('#customerRepaymentDetailTab').datagrid('load',{
		//"organizationId":$("#organizationId").combobox("getValue"),
		"contractNo":$("#contractNo").val(),
		"loanCity":$("#loanCity").val(),
		"loanName":$("#loanName").val(),
		"planRepmtDate":$("#planRepmtDate").datebox("getValue"),
		"contractSignDateS":$("#contractSignDateS").datebox("getValue"),
		"contractSignDateE":$("#contractSignDateE").datebox("getValue"),
	});
}


//还款
function repayment(index){
	var rows = $("#customerRepaymentDetailTab").datagrid("getRows");
	var row = rows[index];
	$("#repaymentDlg").css("display","block").dialog({
		title:"输入还款金额",
		modal:true,
		width:340,
		height:450,
		closed:false,
		closable:true,
		onOpen:function(){
			$("#rdId").val(row.rdId);
			$("#lateFee").val(row.lateFee);
			$("#defaultInterest").val(row.defaultInterest);
			$("#freeInterestFee").val(row.freeInterestFee);
			$("#needMonthFee").val(row.needMonthFee);
			$("#needPay").val(row.needPay);
			$("#repmtAct").val(row.repmtAct);
		}
	});
	
}

//确认还款
function repaymentFormSave(){
	var isValid = $("#repaymentForm").form('validate');
	if(isValid){
		$.messager.confirm("提示","是否确认还款?",function(b){
			if(b){
				var repayAmt = $("#repayAmt").val();
				var repmtAct = $("#repmtAct").val();
				var rdId = $("#rdId").val();
				$.ajax({
					url:"loanCustRepaymentDetail/loanCustRepaymentDetailAction!repaymentSave.action",
					type:"post",
					data:{"repayAmt":repayAmt,"repmtAct":repmtAct,"rdId":rdId,"repaymentType":"1"},
					success:function(data){
						$.messager.show({
							title:'提示',
							msg:data.message,
							timeout:1000,
							showType:'slide'
						});
						$("#repaymentForm").form("clear");
						$("#repaymentDlg").dialog("close");
						$("#customerRepaymentDetailTab").datagrid("reload");
					},
					error:function(){
						
					}
				});
			}
		});
	}
}

//取消还款
function repaymentFormCancel(){
	$("#repaymentDlg").dialog("close");
}

//免息
function interestFree(index){
	var rows = $("#customerRepaymentDetailTab").datagrid("getRows");
	var row = rows[index];
	$("#interestFreeDlg").css("display","block").dialog({
		title:"输入免息金额",
		modal:true,
		width:340,
		height:300,
		closed:false,
		closable:true,
		onOpen:function(){
			$("#interestFreerdId").val(row.rdId);
			$("#needLateFee").val(row.lateFee);
			$("#needDefaultInterest").val(row.needDefaultInterest);
			$("#totalInterestFee").val(row.lateFee+row.needDefaultInterest);
		}
	});
}

//确认免息
function interestFreeFormSave(){
	var isvalid = $("#interestFreeForm").form("validate");
	if(isvalid){
		$.messager.confirm("提示","确认免息?",function(b){
			if(b){
				var rdId = $("#interestFreerdId").val();
				var interestFreeAmt = $("#interestFreeAmt").val();
				var isClear = $("#isClear").attr("checked");
				if(isClear == "checked"){
					isClear = "checked";
				}else{
					isClear = "unchecked";
				}
				$.ajax({
					url:"loanCustRepaymentDetail/loanCustRepaymentDetailAction!freeInterestFeeSave.action",
					type:"post",
					data:{"freeInterestFee":interestFreeAmt,"rdId":rdId,"isClear":isClear},
					success:function(data){
						$.messager.show({
							title:"提示",
							msg:data.message,
							timeout:1000,
							showType:"slide"
						});
						$("#interestFreeAmt").val(0);
						$("#interestFreeDlg").dialog("close");
						$("#customerRepaymentDetailTab").datagrid("reload");
					}
				});
			}
		});
	}
}

//取消免息
function interestFreeFormCancel(){
	$("#interestFreeDlg").dialog("close");
}

/* function checkContractDetail(index){
	var rows = $("#customerRepaymentDetailTab").datagrid("getRows");
	var contractNo = rows[index].contractNo;
	$("#contractDetailForm input").attr("disabled","disabled");
	$("#contractDetailDlg").css("display","block").dialog({   
	    title: '合同详情',   
	    width: 1000,   
	    height: 500,  
	    closed: false,   
	    cache: false,   
	    closable:true,
	    modal: true,
	    onBeforeOpen:function(){
	    	$.ajax({
	    		url:"loanContract/loanContractAction!findLoanContractInfo.action",
	    		type:"post",
	    		data:{"contractNo":contractNo,"page":0,"rows":1},
	    		success:function(data){
	    			console.info(data.rows[0]);
	    			$("#contractDetailForm").form("load",data.rows[0]);
	    		}
	    	});
	    }
	});   
} */

//查看合同详情
function checkContractDetail(index){
	var rows = $("#customerRepaymentDetailTab").datagrid("getRows");
	$lcid = rows[index].lcId;
	$("<div></div>").dialog({
		title:"合同详情",
		width: 1050,   
	    height: 600,   
	    closed: false,   
	    cache: false,   
	    closable:true,
	    modal: true,
	    href:"jsp/loanerMonitoring/loanContractDetail.jsp",
	    onClose:function(){
	    	$(this).dialog("destroy");
	    }
	});
}

//查看客户详情
function checkLoanerDetail(loanerId){
	$.ajax({
		url : 'loaner/loanerAction!queryLoaner.action',
		type : 'POST',
		data : {"loanerId":loanerId},
		dataType : 'JSON',
		success : function(data) {
			loanerDetailDlg(data);
		}
	});
}

//客户详情DLG
function loanerDetailDlg(data){
	$("#loanerDetailForm input").attr("disabled","disabled");
	$("#loanerDetailDlg").css("display","block").dialog({
		title:"客户详情",
		width:1000,
		height:500,
		closed: false,   
		cache: false,   
		closable:true,
		modal: true,
		onBeforeOpen:function(){
			$("#loanerDetailForm").form("load",data);
		}
	});
}

</script>
 </head>
	<body>
		<div>
			<div style="margin-left: 5px;margin-top: 5px">
				业务管理-->财务监控管理-->贷款客户监管-->逾期还款
			</div>
			<div style="padding-top:5px;">
				<font size="3em">[查询条件]</font>
				<form id="overdueRepaymentForm" method="post">
					<table cellpadding="10px;">
						<tr>
							<th>
								进件地区
							</th>
							<td>
								<!-- <input id="organizationId" name="organizationId" class="easyui-textbox easyui-combobox" placeholder="请选择省份/直辖市/自治区"/> -->
								<input id="loanCity" name="loanCity" class="easyui-textbox" placeholder="请输入进件地区"/>
							</td>
							<th>
								合同编号
							</th>
							<td>
								<input id="contractNo" name="contractNo" class="easyui-textbox"  placeholder="请输入合同编号"/>
							</td>
							<th>
								客户姓名
							</th>
							<td>
								<input id="loanName" name="loanName" class="easyui-textbox" placeholder="请输入客户姓名"/>
							</td>
							<th>
								月还款日期
							</th>
							<td> 
								<input id="planRepmtDate" name="planRepmtDate" class="easyui-textbox easyui-datebox" data-options="editable:false" />
							</td>
						</tr>
						
						<tr>
							<th>
								合同签署日期
							</th>
							<td colspan="3"> 
								<input id="contractSignDateS" name="contractSignDateS" placeholder="请选择起始日期" class="easyui-textbox easyui-datebox" data-options="editable:false" />
							　　至　　
								<input id="contractSignDateE" name="contractSignDateE" placeholder="请选择截止日期" class="easyui-textbox easyui-datebox" data-options="editable:false"/>
							</td>
						</tr>
					</table>
				</form>
				<div style="text-align:right;">
					<span style="color:red;float:left;padding-top:6px;">
						注:不输入任何查询条件，默认查询逾期客户还款详情
					</span>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="subCustomerRepaymentForm();">执行查询</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" onclick="resetCustomerRepaymentForm();">重置条件</a>
				</div>
			</div>
			
			<div>
				<table id="customerRepaymentDetailTab"></table>
			</div>
		</div>
		
		<div id="customerRepaymentDetailDlg" style="display:none;">
			<div style="height:120px;width:100%">
				<form id="customerRepaymentDetailForm">
					<table cellpadding="8px;"> 
						<tr>
							<th>合同编号</th>
							<td><input name="contractNo" readonly="readonly"/></td>
							
							<th>所属地区</th>
							<td><input name="organizationName" readonly="readonly"/></td>
							
							<th>合同签署日期</th>
							<td><input name="contractSignDate" readonly="readonly"/></td>
							
							<th>客户姓名</th>
							<td><input name="loanName" readonly="readonly"/></td>
						</tr>
						
						<tr>
							<th>身份证号</th>
							<td><input name="loanIdNo" readonly="readonly"/></td>
							
							<th>贷款类型</th>
							<td><input name="loanTypeName" readonly="readonly"/></td>
							
							<th>贷款金额</th>
							<td><input name="loanEdu" readonly="readonly"/></td>
							
							<th>贷款期数</th>
							<td><input name="loanPeriods" readonly="readonly"/></td>
						</tr>
						
						<tr>
							<th>月还金额</th>
							<td><input name="monthlyRepayment" readonly="readonly"/></td>
						
							<th>放款日期</th>
							<td><input name="loanBgDate" readonly="readonly"/></td>
							
							<th>当前日期</th>
							<td><input name="now" readonly="readonly"/></td>
						</tr>
					</table>
				</form>
			</div>
			<div style="height:520px;width:100%">
				
			</div>
		</div>
		
		<div id="repaymentDlg" style="display:none;"> 
			<form id="repaymentForm">
				<input id="rdId" type="hidden" />
				<table cellpadding="8px;">
					<tr>
						<th>滞纳金</th>
						<td><input id="lateFee" name="lateFee" class="easyui-numberbox" style="border:none;" readonly="readonly"/>元</td>
					</tr>
					
					<tr>
						<th>罚息</th>
						<td><input id="defaultInterest" name="defaultInterest" class="easyui-numberbox" style="border:none;" readonly="readonly"/>元</td>
					</tr>
					
					<tr>
						<th>免息</th>
						<td><input id="freeInterestFee" name="freeInterestFee" class="easyui-numberbox" style="border:none;" readonly="readonly"/>元</td>
					</tr>
					
					<tr>
						<th>待还月还金额</th>
						<td><input id="needMonthFee" name="needMonthFee" class="easyui-numberbox" style="border:none;" readonly="readonly"/>元</td>
					</tr>
					
					<tr>
						<th>待还总金额</th>
						<td><input id="needPay" name="needPay" class="easyui-numberbox" style="border:none;" readonly="readonly"/>元</td>
					</tr>
					
					<tr>
						<th>还款账号</th>
						<td><input id="repmtAct" name="repmtAct" class="easyui-textbox easyui-validatebox" data-options="required:true" /></td>
					</tr>
					
					<tr>
						<th>请输入还款金额</th>
						<td><input id="repayAmt" name="repayAmt" class="easyui-numberbox easyui-validatebox" data-options="validType:'intOrFloat',required:true"/>元</td>
					</tr>
				</table>
			</form>
			<div style="text-align: right;padding-right: 20px;padding-top: 20px;">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" onclick="repaymentFormSave();">确定</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="repaymentFormCancel();">取消</a>
			</div>
		</div>
		
		<div id="interestFreeDlg" style="display:none;"> 
			<form id="interestFreeForm">
				<input id="interestFreerdId" type="hidden" />
				<table cellpadding="8px;">
					<tr>
						<th>滞纳金</th>
						<td><input id="needLateFee" name="needLateFee" style="border:none;" readonly="readonly"/>元</td>
					</tr>
					
					<tr>
						<th>罚息</th>
						<td><input id="needDefaultInterest" name="needDefaultInterest" style="border:none;" readonly="readonly" />元</td>
					</tr>
					
					<tr>
						<th>总计</th>
						<td><input id="totalInterestFee" name=""totalInterestFee"" style="border:none;" readonly="readonly" />元</td>
					</tr>
					
					<tr>
						<th>请输入免息金额</th>
						<td><input id="interestFreeAmt" name="freeInterestFee" class="easyui-textbox easyui-validatebox" data-options="validType:'intOrFloat',required:true"/>元</td>
					</tr>
				</table>
			</form>
			<div style="text-align: right;padding-right: 20px;padding-top: 20px;">
				<input type="checkbox" id="isClear" /><label for="isClear">是否已经还清　　　 </label>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" onclick="interestFreeFormSave();">确定</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="interestFreeFormCancel();">取消</a>
			</div>
		</div>
		
		<div id="loanerDetailDlg" style="display:none;">
			<form id="loanerDetailForm">
				<table cellpadding="8px;" style="width:100%;height:100%;">
					<tr>
						<th>
							姓名
						</th>
						<td>
							<input name="name" class="easyui-textbox" />
						</td>
						
						<th>
							年龄
						</th>
						<td>
							<input name="age" class="easyui-textbox" />
						</td>
						
						<th>
							身份证号
						</th>
						<td>
							<input name="idNo" class="easyui-textbox" />
						</td>
					</tr>
					
					<tr>
						<th>
							固定电话
						</th>
						<td>
							<input name="fixedTel" class="easyui-textbox" />
						</td>
						
						<th>
							移动电话
						</th>
						<td>
							<input name="mobileTel" class="easyui-textbox" />
						</td>
						
						<th>
							家庭电话
						</th>
						<td>
							<input name="familyTel" class="easyui-textbox" />
						</td>
					</tr>
					
					<tr>
						<th>
							性别
						</th>
						<td>
							<input name="genderTypeName" class="easyui-textbox" />
						</td>
					
						<th>
							婚姻状况
						</th>
						<td>
							<input name="marriageTypeName" class="easyui-textbox" />
						</td>
					
						<th>
							是否有子女
						</th>
						<td>
							<input name="hasChildName" class="easyui-textbox" />
						</td>
					</tr>
					
					<tr>
						<th>
							年收入
						</th>
						<td>
							<input name="annualSalary" class="easyui-textbox" />
						</td>
						
						<th>
							收入来源
						</th>
						<td>
							<input name="incomeSrc" class="easyui-textbox" />
						</td>
						
						<th>
							月供
						</th>
						<td>
							<input name="houseInstallPay" class="easyui-textbox" />
						</td>
					</tr>
					
					<tr>
						<th>
							房屋居住状况
						</th>
						<td>
							<input name="mortgageStatusName" class="easyui-textbox" />
						</td>
						
						<th>
							E-mail
						</th>
						<td>
							<input name="email" class="easyui-textbox" />
						</td>
						
						<th>
							QQ
						</th>
						<td>
							<input name="qqNo" class="easyui-textbox" />
						</td>
					</tr>
					
					<tr>
						<th>
							房租
						</th>
						<td>
							<input name="rent" class="easyui-textbox" />
						</td>
						
						<th>
							创建时间
						</th>
						<td>
							<input name="createDate" class="easyui-textbox" />
						</td>
					</tr>
					
					<tr>
						<th>
							现居地址
						</th>
						<td colspan="3">
							<input name="curAddress" class="easyui-textbox" style="width:100%;"/>
						</td>
					</tr>
					<tr>
						<th>
							户口地址
						</th>
						<td colspan="3">
							<input name="hukouAddress" class="easyui-textbox" style="width:100%;"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>				

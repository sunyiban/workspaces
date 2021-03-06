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
    <title>信贷合同信息</title>
	<jsp:include page="../../layout/script.jsp"></jsp:include>

<style type="text/css">

</style>
<script type="text/javascript">
$(function(){
	$(window).resize(function(){  
        $("#loanContractInfoTab").datagrid("resize",{  
			height : $(window).height()-120,
        	width : 'auto'
        });                
    });
	// 查看申请状态
	$datagrid = $("#loanContractInfoTab").datagrid({
		url : "loanContract/loanContractAction!findLoanContractInfo.action",
		width : 'auto',
		height :$(window).height()-120,
		pagination:true,
		rownumbers:true,
		border:true,
		singleSelect:true,
		nowrap:true,
		multiSort:false,
		columns : [ [
		             {title : '<strong>合同信息</strong>',width : 520,align : 'center',rowspan:1,colspan:3},
		             {title : '<strong>银行卡信息</strong>',width : 400,align : 'center',rowspan:1,colspan:3},
		             {title : '<strong>客户信息</strong>',width : 840,align : 'center',rowspan:1,colspan:5},
		             {title : '<strong>贷款信息</strong>',width : 1380,align : 'center',rowspan:1,colspan:14},
		             {title : '<strong>IPC相关</strong>',width : 420,align : 'center',rowspan:1,colspan:5},
		             {title : '<strong>业务员信息</strong>',width : 180,align : 'center',rowspan:1,colspan:2},
		             {field : 'remark',title : '<strong>备注信息</strong>',width : 220,align : 'center',rowspan:2}
		            ],
		            [ 
		              {field : 'loanCity',title : '进件城市',width : 80,align : 'center'},
		              {field : 'contractNo',title : '合同编号',width :300,align : 'center', 
			            	formatter:function(value,row,index){
			            		return "<a href='javascript:void(0);' onclick='contractInfoDetail("+index+");'>"+value+"</a>　　" ;
			            	}   
		              },
		              {field : 'contractSignDate',title : '合同签署日期',width : 120,align : 'center'},
		              {field : 'loanerActName',title : '开户人姓名',width : 100,align : 'center'}, 
		              {field : 'loanerBankName',title : '开户行名称',width : 140,align : 'center'},
		              {field : 'loanerActNum',title : '开户行账号',width : 140,align : 'center'},
		              {field : 'loanName',title : '客户姓名',width : 80,align : 'center'},
		              {field : 'loanIdNo',title : '身份证号码',width : 140,align : 'center'},
		              {field : 'loanHukouAddr',title : '户籍地址',width : 250,align : 'center'},
		              {field : 'loanCurAddr',title : '通讯地址',width : 250,align : 'center'},
		              {field : 'loanMobileTel',title : '联系方式',width : 100,align : 'center'},
		              {field : 'organizationId',title : '贷款所属地区',width : 80,align : 'center'},
		              {field : 'loanTypeName',title : '贷款类型',width : 80,align : 'center'},
		              {field : 'loanEdu',title : '贷款金额(元)',width : 100,align : 'right'},
		              {field : 'loanPeriod',title : '贷款期数(期)',width : 80,align : 'center'},
		              {field : 'monthlyFee',title : '服务费(元)',width : 100,align : 'right'},
		              {field : 'visitFee',title : '外访费(元)',width : 100,align : 'right'},
		              {field : 'loanEdu',title : '放款金额(元)',width : 100,align : 'right'},
		              {field : 'monthlyRepayment',title : '月还金额(元)',width : 100,align : 'right'},
		              {field : 'monthlyRepaymentDate',title : '月还款日',width : 80,align : 'center'},
		              {field : 'repaymentBgDate',title : '还款开始日期',width : 120,align : 'center'},
		              {field : 'repaymentEndDate',title : '还款截止日期',width : 120,align : 'center'},
		              {field : 'drawPlatform',title : '划扣平台',width : 100,align : 'center'},
		              {field : 'loanBgDate',title : '放款日期',width : 120,align : 'center'},
		              {field : 'drawDate',title : '划扣日期',width : 120,align : 'center'},
		              {field : 'operatorAName',title : '业务经办人A',width : 80,align : 'center'},
		              {field : 'operatorBName',title : '业务经办人B',width : 80,align : 'center'},
		              {field : 'loanReviewRommitteeName1',title : '贷审委A',width : 80,align : 'center'},
		              {field : 'loanReviewRommitteeName2',title : '贷审委B',width : 80,align : 'center'},
		              {field : 'loanReviewRommitteeName3',title : '贷审委C',width : 80,align : 'center'},
		              {field : 'salesMan',title : '业务员',width : 80,align : 'center'},
		              {field : 'teamManger',title : '团队经理',width : 80,align : 'center'}
		              ] 
		]
	});
	
	//loadOrganization();
});
	
	//获取所属地区
	/* function loadOrganization(){
		$("#organizationId").combobox({
			valueField : 'code',
			textField : 'text',
			url:"orgz/organizationAction!findOrganizationCombo.action",
			editable:false,
		});
	} */

	//提交查询表单
	function subLoanCractForm(){
		$("#loanContractInfoTab").datagrid("load",{
			//"organizationId":$("#organizationId").combobox("getValue"),
			 "contractNo":$("#contractNo").val(),
			 "loanName":$("#loanName").val(),
			 "loanCity":$("#loanCity").val(),
			 "loanBgDateS":$("#loanBgDateS").datebox('getValue'),
			 "loanBgDateE":$("#loanBgDateE").datebox('getValue')
		});
	}
	
	//重置查询表单
	function resetLoanCractForm(){
		$("#loanCractForm").form("clear");
		$("#loanContractInfoTab").datagrid("load",{});
	}
	
	//导出信贷合同明细
	function uploadLoanContract(){
		downFileByFormPost("loanContract/loanContractAction!exprotLoanConractInfoModel.action",{
			 "contractNo":$("#contractNo").val(),
			 "loanName":$("#loanName").val(),
			 "loanCity":$("#loanCity").val(),
			 "loanBgDateS":$("#loanBgDateS").datebox('getValue'),
			 "loanBgDateE":$("#loanBgDateE").datebox('getValue')
		});
	}
	
	//查看合同详细
	function contractInfoDetail(index){
		var row = $("#loanContractInfoTab").datagrid("getRows");
		$("#contractInfoDetailForm input").attr("disabled","disabled");
		$("#contractInfoDetailForm").form('load',row[index]);
		$("#contractInfoDetailForm input").attr("readonly","readonly");
		$("#contractInfoDetail").css("display","block").dialog({
			title:'信贷合同信息详情',
			width:1000,
			height:650,
			closed:false,
			modal:true,
			cache:false,
			closable:true,
		});
	}
</script>
	</head>
<body>
	<div>
		<div style="margin-left: 5px;margin-top: 5px">
			业务管理-->财务监控管理-->贷款客户监管-->信贷合同信息
		</div>
		<div style="padding-top:5px;">
			<font size="3em">[查询条件]</font>
			<form id="loanCractForm" method="post">
				<table cellpadding="10px;">
					<tr>
						<th>
							进件城市
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
							合同签署日期
						</th>
						<td> 
							<input id="loanBgDateS" name="loanBgDateS" placeholder="请选择起始日期" class="easyui-textbox easyui-datebox" data-options="editable:false" />
							　　至　　
							<input id="loanBgDateE" name="loanBgDateE" placeholder="请选择截止日期" class="easyui-textbox easyui-datebox" data-options="editable:false"/>
						</td>
					</tr>
				</table>
			</form>
			<div style="text-align:right;">
				<span style="color:red;float:left;padding-top:6px;">
					<!-- 注:不输入任何查询条件，默认查询所有客户还款详情 -->
				</span>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="subLoanCractForm();">执行查询</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" onclick="resetLoanCractForm();">重置条件</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" onclick="uploadLoanContract();">导出信贷合同明细</a>
			</div>
		</div>
		<div>
			<table id="loanContractInfoTab"></table>
		</div>
	</div>
	
	<div id="contractInfoDetail" style="display:none;">
		<form id="contractInfoDetailForm">
			<table cellpadding="8px;" style="width:100%;height:100%;">
				<tr>
					<th>所属地区</th>
					<td><input name="organizationId" class="easyui-textbox" /></td>
					<th>合同编号</th>
					<td><input name="contractNo" class="easyui-textbox" /></td>
					<th>合同签署日期</th>
					<td><input name="contractSignDate" class="easyui-textbox" /></td>
				</tr>
				
				<tr>
					<th>通讯地址</th>
					<td><input name="loanCurAddr" class="easyui-textbox" /></td>
					<th>联系方式</th>
					<td><input name="loanMobileTel" class="easyui-textbox" /></td>
					<th>业务员</th>
					<td><input name="salesMan" class="easyui-textbox" /></td>
				</tr>
				
				<tr>
					<th>开户人姓名</th>
					<td><input name="loanerActName" class="easyui-textbox" /></td>
					<th>开户行名称</th>
					<td><input name="loanerBankName" class="easyui-textbox" /></td>
					<th>开户行账号</th>
					<td><input name="loanerActNum" class="easyui-textbox" /></td>
				</tr>
				
				<tr>
					<th>客户姓名</th>
					<td><input name="loanName" class="easyui-textbox" /></td>
					<th>身份证</th>
					<td><input name="loanIdNo" class="easyui-textbox" /></td>
					<th>户籍地址</th>
					<td><input name="loanHukouAddr" class="easyui-textbox" /></td>
				</tr>
				
				<tr>
					<th>贷款类型</th>
					<td><input name="loanTypeName" class="easyui-textbox" /></td>
					<th>贷款金额(元)</th>
					<td><input name="loanEdu" class="easyui-numberbox" /></td>
					<th>贷款期数(期)</th>
					<td><input name="loanPeriod" class="easyui-textbox" /></td>
				</tr>
				
				<tr>
					<th>服务费(元)</th>
					<td><input name="monthlyFee" class="easyui-numberbox" /></td>
					<th>外访费(元)</th>
					<td><input name="visitFee" class="easyui-numberbox" /></td>
					<th>放款金额(元)</th>
					<td><input name="loanEdu" class="easyui-numberbox" /></td>
				</tr>
				
				<tr>
					<th>月还金额(元)</th>
					<td><input name="monthlyRepayment" class="easyui-numberbox" /></td>
					<th>月还款日</th>
					<td><input name="monthlyRepaymentDate" class="easyui-textbox" /></td>
					<th>划扣平台</th>
					<td><input name="drawPlatform" class="easyui-textbox" /></td>
				</tr>
				
				<tr>
					<th>放款日期</th>
					<td><input name="loanBgDate" class="easyui-textbox" /></td>
					<th>还款开始日期</th>
					<td><input name="repaymentBgDate" class="easyui-textbox" /></td>
					<th>还款截止日期</th>
					<td><input name="repaymentEndDate" class="easyui-textbox" /></td>
				</tr>
				
				<tr>
					<th>划扣日期</th>
					<td><input name="drawDate" class="easyui-textbox" /></td>
				</tr>
				
				<tr>
					<th>贷审委A</th>
					<td><input name="loanReviewRommitteeName1" class="easyui-textbox" /></td>
					<th>贷审委B</th>
					<td><input name="loanReviewRommitteeName2" class="easyui-textbox" /></td>
					<th>贷审委C</th>
					<td><input name="loanReviewRommitteeName3" class="easyui-textbox" /></td>
				</tr>
				
				<tr>
					<th>业务经办人A</th>
					<td><input name="operatorAName" class="easyui-textbox" /></td>
					<th>业务经办人B</th>
					<td><input name="operatorBName" class="easyui-textbox" /></td>
					<th>团队经理</th>
					<td><input name="teamManger" class="easyui-textbox" /></td>
				</tr>
			</table>
		</form>
	</div>	
	</body>
	</html>
					

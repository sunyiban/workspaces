<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>信审报告</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="../../../layout/script.jsp"></jsp:include>
</head>

<style type="text/css">
	#acceptTaskForm table input{border: none;}
	table {border-radius: 5px;}
	.linkSpan{
	  padding:5px;
	  display:-moz-inline-box;
	  display:inline-block;
	  width:40%; 
	  text-align: center;
	}
	.linkSpanS{
	  padding:5px;
	  display:-moz-inline-box;
	  display:inline-block;
	  width:10%; 
	  text-align: center;
	}
	a{text-decoration: none;}
	a:hover {
	 color: #FF0000;
	}
	.table th{
		text-align: right;
	}
	.table td{
		text-align: left;
	}	
	textarea{resize: none;}
</style>
<script type="text/javascript">
	<%
		// 将传过来的参数转化为utf-8的字符串类型
		String name = request.getParameter("loanerName");
		String loanerName = new  String (name.getBytes("iso8859-1"),"utf-8");
		String pose =  request.getParameter("purpose");
		String purpose = new  String (pose.getBytes("iso8859-1"),"utf-8");
	%>
	var loanOrderId = '<%=request.getParameter("loanOrderId")%>';
	var loanerName = '<%=loanerName%>';
	var loanerIdNo = '<%=request.getParameter("loanerIdNo")%>';
	var purpose = '<%=purpose%>';
	
	$(function(){
		$("#microcreditOpinionForm").form("load","microcreditOpinion/microcreditOpinionAction!findMicrocreditOpinionByOid.action?loanOrderId="+loanOrderId);
		$("#microcreditOpinionForm input[name='name']").val(loanerName);
		$("#microcreditOpinionForm input[name='idNo']").val(loanerIdNo);
		$("#microcreditOpinionForm input[name='purpose']").val(purpose);
		$("#microcreditOpinionForm input").attr("readonly","readonly").css("background-color","#EBEBE4");
		$("#microcreditOpinionForm textarea").attr("readonly","readonly").css("background-color","#F5F5F5");

		//渲染贷审委信息
		$.ajax({
				type : "POST",
				url : "loanOrderHis/loanOrderHisAction!findLoanOrderHis.action",
				data : {"loanOrderId":loanOrderId},
				success : function(data) {
					if(data && data.length >0){
						// 贷审委是否通过
						$("#processingResult1").val(data[0].processingResult);
						$("#processingResult2").val(data[1].processingResult);
						$("#processingResult3").val(data[2].processingResult);

						// 贷审委制定的信贷方式
						$("#auditWay1").val(data[0].auditWayValue);
						$("#auditWay2").val(data[1].auditWayValue);
						$("#auditWay3").val(data[2].auditWayValue);

						// 贷审委给出意见
						$("#comment1").val(data[0].comment);
						$("#comment2").val(data[1].comment);
						$("#comment3").val(data[2].comment);
					}
				}
			});
		
			setTimeout(loadAudtiWayText,300);
		});
	
		function loadAudtiWayText(){
			$.ajax({
				url:"common/commonAction!findTextArr.action?codeMyid=audit_way",
				type:"post",
				async:false,
				success:function(data){
					var auditWay = $("#auditWay").val();
					$.each(data,function(i,item){
						if(item.code==auditWay){
							$("#auditWayText").val(item.text);
						}
					});
				}
			});
		}
</script>
<body>
	<form id="microcreditOpinionForm" method="post">
		<input name="mcbrId" type="hidden" />
		<div style="text-align:center;">
			<font size="4" style="font-weight: bold;">微贷业务呈报意见表</font>
		</div>
		<div>
			<table cellpadding="8px;">
				<tr>
					<th>
						借款人
					</th>
					<td>
						<input readonly="readonly" class="easyui-validatebox easyui-textbox" name="name"  type="text" />
						<input name="loanOrderId" type="hidden" />
					</td>
					<th>
						身份证号
					</th>
					<td>
						<input readonly="readonly" class="easyui-validatebox easyui-textbox" name="idNo"  type="text" />
					</td>
					<th>
						咨询服务费
					</th>
					<td>
						<input readonly="readonly" class="easyui-validatebox easyui-textbox" name="counselingRate"  type="text" />%
					</td>
				</tr>
				
				<tr>
					<th>
						共同借款人
					</th>
					<td>
						<input class="easyui-validatebox easyui-textbox" name="coborrowerName"  type="text" />
					</td>
					<th>
						身份证号
					</th>
					<td>
						<input class="easyui-validatebox easyui-textbox" name="coborrowerIdno"  type="text" />
					</td>
					<th>
						收取方式
					</th>
					<td>
						<input readonly="readonly" class="easyui-validatebox easyui-textbox" name="collectionMthd"  type="text" />
					</td>
				</tr>
				
				<tr>
					<th>
						建议金额(元)
					</th>
					<td>
						<input class="easyui-validatebox easyui-textbox" name="adviceLoanAmt"  type="text"/>
					</td>
					<th>
						期限(月)
					</th>
					<td>
						<input class="easyui-validatebox easyui-textbox" name="adviceLoanPeriod"  type="text" />
					</td>
					<th>
						放款方式
					</th>
					<td>
						<input readonly="readonly" class="easyui-validatebox easyui-textbox" name="loanMthd"  type="text" />
					</td>
				</tr>
				
				<tr>
					<th>
						贷款用途
					</th>
					<td>
						<input class="easyui-validatebox easyui-textbox" name="purpose"  type="text" value=""/>
					</td>
					<th>
						利率(年)
					</th>
					<td>
						<input readonly="readonly" class="easyui-validatebox easyui-textbox" name="loanRate"  type="text" />%
					</td>
					<th>
						还款方式
					</th>
					<td>
						<input readonly="readonly" class="easyui-validatebox easyui-textbox" name="adviceRepayMthd"  type="text" />
					</td>
				</tr>

				<tr>
					<th>信贷方式:</th>
					<td>
						<input id="auditWay" name="auditWay" type="hidden"/>
						<input id="auditWayText" name="auditWayText" class="easyui-textbox"/>
					</td>
				</tr>

				<tr>
					<th>
						经办机构/部门
					</th>
					<td>
						<input readonly="readonly" class="easyui-validatebox easyui-textbox" name=""  type="text" value="IPC项目组-保定"/>
					</td>
					<th>
						经办人
					</th>
					<td colspan="3">
						A:<input id="useridA" class="easyui-validatebox easyui-textbox" name="operatorA"  type="text" />&nbsp;&nbsp;&nbsp;
						B:<input id="useridB" class="easyui-validatebox easyui-textbox" name="operatorB"  type="text" /> 
					</td>
				</tr>
			</table>
			<div style="width:99.5%;height:270px;">
				<div style="padding-left:10px;height:30px;">
						<span style="font-weight:bold;">风险控制措施:&nbsp;&nbsp;&nbsp;</span>
						<input class="easyui-textbox" name="riskCtrlMeasures" />
						
						<span style="padding-left:20px;font-weight:bold;">具体措施如下:</span>
				</div>
				<div style="height:230px;overflow:auto;">
						<textarea class="easyui-validatebox easyui-textbox" name="specificMeasures" style="width:99%;height:220px;resize: none;">
						
						</textarea>
				</div>
			</div>
			<div>
				<table cellpadding="8px;">
					<tr>
						<th rowspan="2">
							业务经办人
						</th>
						<td>
							<input class="easyui-validatebox easyui-textbox" name="operatorA" type="text" />
						</td>
						<th rowspan="2">
							后台人员
						</th>
						<th>
							初次上会
						</th>
						<td>
							<input class="easyui-validatebox easyui-textbox" name="firstMeeting" type="text" />
						</td>
						<th rowspan="2">
							部门负责人
						</th>
						<td rowspan="2">
							<input class="easyui-validatebox easyui-textbox" name="deptPrincipal" type="text" />
						</td>
					</tr>
					<tr>
						<td>
							<input class="easyui-validatebox easyui-textbox" name="operatorB" type="text" />
						</td>
						<th>
							补调核实
						</th>
						<td>
							<input class="easyui-validatebox easyui-textbox" name="verification" type="text" />
						</td>
					</tr>
					<tr>
						<th >贷审委1</th>
						<td colspan="7">
							是否通过:<input id="processingResult1"  name="processingResult" class="easyui-textbox" readonly="readonly"/>
							信贷方式:<input id="auditWay1"  name="auditWay" class="easyui-textbox" readonly="readonly"/>
							<textarea class="easyui-textbox" id="comment1" style="width:99%;height:70px;resize: none;" type="text" readonly="readonly">
						</textarea></td>
					</tr>
					<tr>
						<th >贷审委2</th>
						<td colspan="7">
							是否通过:<input id="processingResult2" name="processingResult" class="easyui-textbox" type="text" readonly="readonly"/>
							信贷方式:<input id="auditWay2" name="auditWay" class="easyui-textbox" type="text" readonly="readonly"/>
							<textarea class="easyui-textbox" id="comment2"  style="width:99%;height:70px;resize: none;" readonly="readonly"></textarea>
						</td>
					</tr>
					<tr>
						<th>贷审委3</th>
						<td colspan="7">
							是否通过:<input id="processingResult3" name="processingResult" class="easyui-textbox" type="text" readonly="readonly"/>
							信贷方式:<input id="auditWay3" name="auditWay" class="easyui-textbox" type="text" readonly="readonly"/>
							<textarea class="easyui-textbox" id="comment3" style="width:99%;height:70px;resize: none;" readonly="readonly"></textarea>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</body>
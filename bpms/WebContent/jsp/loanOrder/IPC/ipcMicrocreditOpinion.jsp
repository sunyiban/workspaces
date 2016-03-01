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
<title>微贷呈报意见表</title>
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
	var loanOrderId = '<%=request.getParameter("loanOrderId")%>';
	<%
		// 将传过来的参数转化为utf-8的字符串类型
		String name = request.getParameter("loanerName");
		String loanerName = new  String (name.getBytes("iso8859-1"),"utf-8");
		String pose =  request.getParameter("purpose");
		String purpose = new  String (pose.getBytes("iso8859-1"),"utf-8");
	%>
	var loanerName = '<%=loanerName%>';
	var loanerIdNo = '<%=request.getParameter("loanerIdNo")%>';
	var purpose = '<%=purpose%>';
	
	$(function(){
	    //加载微贷呈报意见表
	    $.ajax({
			url : "microcreditOpinion/microcreditOpinionAction!findMicrocreditOpinionDetailByOid.action",
			data : {"loanOrderId":loanOrderId},
			type : "POST",
			async:false,
			success : function(data) {
				console.info(data);
				if(data){
					$("#microcreditOpinionForm").form("load",data);
				}
			}
		});   
	   
		//$("#microcreditOpinionForm").form("load","microcreditOpinion/microcreditOpinionAction!findMicrocreditOpinionByOid.action?loanOrderId="+loanOrderId);
		$("#microcreditOpinionForm input[name='name']").val(loanerName);
		$("#microcreditOpinionForm input[name='idNo']").val(loanerIdNo);
		$("#microcreditOpinionForm input[name='purpose']").val(purpose);
		$("#microcreditOpinionForm input").attr("readonly","readonly").css("background-color","#EBEBE4");
		$("#microcreditOpinionForm textarea").attr("readonly","readonly").css("background-color","#F5F5F5");
		//setTimeout(loadAudtiWayText,300);
	});
	
	/* function loadAudtiWayText(){
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
	} */
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
						<input readonly="readonly" class=" easyui-textbox" name="name"   />
						<input name="loanOrderId" type="hidden" />
					</td>
					<th>
						身份证号
					</th>
					<td>
						<input readonly="readonly" class=" easyui-textbox" name="idNo"   />
					</td>
					<th>
						咨询服务费率
					</th>
					<td>
						<input readonly="readonly" class=" easyui-textbox" name="counselingRateText"   />
					</td>
				</tr>
				
				<tr>
					<th>
						共同借款人
					</th>
					<td>
						<input class=" easyui-textbox" name="coborrowerName"   />
					</td>
					<th>
						身份证号
					</th>
					<td>
						<input class=" easyui-textbox" name="coborrowerIdno"   />
					</td>
					<th>
						收取方式
					</th>
					<td>
						<input readonly="readonly" class=" easyui-textbox" name="collectionMthd"   />
					</td>
				</tr>
				
				<tr>
					<th>
						建议金额(元)
					</th>
					<td>
						<input class="easyui-numberbox" name="adviceLoanAmt" data-options="precision:2,groupSeparator:','" />
					</td>
					<th>
						期限(月)
					</th>
					<td>
						<input class="easyui-textbox" name="adviceLoanPeriodText"   />
					</td>
					<th>
						放款方式
					</th>
					<td>
						<input readonly="readonly" class=" easyui-textbox" name="loanMthd"   />
					</td>
				</tr>
				
				<tr>
					<th>
						贷款用途
					</th>
					<td>
						<input class=" easyui-textbox" name="purpose"   value=""/>
					</td>
					<th>
						利率(年)
					</th>
					<td>
						<input readonly="readonly" class=" easyui-textbox" name="loanRateText"   />
					</td>
					<th>
						还款方式
					</th>
					<td>
						<input readonly="readonly" class=" easyui-textbox" name="adviceRepayMthd"   />
					</td>
				</tr>
				
				<tr>
					<th>信贷方式:</th>
					<td>
						<input name="auditWayText" class="easyui-textbox"/>
					</td>
					<th>
						婚姻状况
					</th>
					<td><input name="maritalStatus" class="easyui-combobox" disabled="disabled"/></td>
				</tr>
				
				<tr>
					<th>
						经办机构/部门
					</th>
					<td>
						<input readonly="readonly" class=" easyui-textbox" name=""   value="IPC项目组-保定"/>
					</td>
					<th>
						经办人
					</th>
					<td colspan="4">
						A:<input name="operatorAName" class="easyui-textbox"/>&nbsp;&nbsp;&nbsp;
						B:<input name="operatorBName" class="easyui-textbox" style="width:250px;"/> 
					</td>
				</tr>
				<tr>
					<th>
						具体措施如下:
					</th>
				</tr>
				<tr>
					<td colspan="6">
						<textarea class="easyui-validatebox easyui-textbox" data-options="required:true" name="specificMeasures" style="width:99%;height:270px;resize: none;"></textarea>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
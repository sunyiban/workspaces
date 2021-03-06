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
<title>拒绝决议表</title>
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
var loanAmount ='<%=request.getParameter("loanAmount")%>';
$(function(){
	//加载拒绝决议表
	//$("#microcreditOpinionRefuseForm").form("load","microcreditOpinion/microcreditOpinionAction!findMicrocreditOpinionDetailByOid.action?loanOrderId="+loanOrderId);
	//加载拒绝决议表   
    $.ajax({
		url : "microcreditOpinion/microcreditOpinionAction!findMicrocreditOpinionDetailByOid.action",
		data : {"loanOrderId":loanOrderId},
		type : "POST",
		async:false,
		success : function(data) {
			if(data){
				data.operatorB = data.operatorB.replace("/\s/g","").split(",");
				$("#microcreditOpinionRefuseForm").form("load",data);
			}
		}
	});   
	
	// 组织机构的信息--进件城市
	$.ajax({
		type : "POST",
		url : "loanOrder/loanOrderAction!findLoanCityByOrderId.action",
		data : {"loanOrderId":loanOrderId},
		success : function(data) {
			if(data){
				$("#microcreditOpinionRefuseDlg input[name='loanCtiy']").val(data.fullName);
			}
		}
	});
	
	// 渲染审查评议表其他的属性值
	$("#microcreditOpinionRefuseDlg input[name='name']").val(loanerName);
	$("#microcreditOpinionRefuseDlg input[name='loanOrderId']").val(loanOrderId);
	$("#microcreditOpinionRefuseDlg input[name='idNo']").val(loanerIdNo);
	$("#microcreditOpinionRefuseDlg input[name='purpose']").val(purpose);
	$("#microcreditOpinionRefuseDlg input[name='loanAmount']").numberbox("setValue",loanAmount);
});

</script>
<!-- 拒绝决议表S -->
<div id="microcreditOpinionRefuseDlg">
	<form id="microcreditOpinionRefuseForm" method="post"> 
		<input name="mcbrId" type="hidden" />
		<div style="text-align:center;">
			<font size="4" style="font-weight: bold;">拒绝决议表</font>
		</div>
		<div>
			<table cellpadding="8px;" style="width:100%;height:100%;">
				<tr>
					<th>
						客户姓名
					</th>
					<td>
						<input name="name" class="easyui-textbox" disabled="disabled"   value="韩冰"/>
						<input name="loanOrderId" type="hidden" />
					</td>
					<th>
						身份证号
					</th>
					<td >
						<input disabled="disabled"   class=" easyui-textbox" name="idNo"   />
					</td>
					<th>
						贷款目的
					</th>
					<td >
						<input name="purpose"  disabled="disabled"  class=" easyui-textbox"/>
					</td>
				</tr>
				
				<tr>
					<th>
						申请金额(元)
					</th>
					<td>
						<input name="loanAmount" disabled="disabled"  class="easyui-numberbox" data-options="precision:2,groupSeparatro:','"/>
					</td>
					<th>
						调查日期
					</th>
					<td >
						<input name="surveyDate"  disabled="disabled" class=" easyui-textbox" />
					</td>
					<th>
						所在地区
					</th>
					<td><input disabled="disabled"  class=" easyui-textbox" name="loanCtiy"  /></td>
					
				</tr>
				
				<tr>
					<th>
						调查人员
					</th>
					<td colspan="4">
						A:<input name="operatorAName" class="easyui-textbox" disabled="disabled"/>&nbsp;&nbsp;&nbsp;
						B:<input name="operatorBName" class="easyui-textbox" style="width:250px;" disabled="disabled"/> 
					</td>
				</tr>
				<tr>
					<th>拒绝原因:</th>
				</tr>
				<tr>
					<td colspan="6">
						<textarea class="easyui-validatebox easyui-textbox" name="rejectCause" disabled="disabled" style="width:99%;height:320px;resize: none;"></textarea>
					</td>
				</tr>
			</table>
		</div>
	</form>	
 </div>
<!-- 决绝决议表E -->
</body>
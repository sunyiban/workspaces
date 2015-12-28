<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.oasys.util.Constants"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../../../layout/script.jsp"></jsp:include>
<title>员工转正申请</title>
<style type="text/css">
.easyui-textbox {
	height: 18px;
	width: 170px;
	line-height: 16px;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
	transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;
}

textarea:focus, input[type="text"]:focus {
	border-color: rgba(82, 168, 236, 0.8);
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px
		rgba(82, 168, 236, 0.6);
	outline: 0 none;
}
.table {
	background-color: transparent;
	border-collapse: collapse;
	border-spacing: 0;
	max-width: 100%;
}
fieldset {
	    border-width: 1px;
	    margin-top: 5px;
	    border-color:#F5F5F5;
	    -moz-border-radius:8px;
}
input, textarea {
	font-weight: normal;
}

.table td {
	padding: 6px;
}
.table th{
    text-align: right;
	padding: 6px;
}
.textStyle{
  text-align: right;
}
</style>
</head>
<script type="text/javascript">
var $datagrid;
var $row;
var $$row;
$(function(){
	createTaskGrid();
});
<%String definitionKey=request.getParameter("key");%>
function createTaskGrid(){
	$datagrid = $("#taskGrid").datagrid({
		url:'empForgetPluginAppController/findAllEmpForgetPluginAppTaskList.do?processKey=<%=definitionKey%>',
		title:'忘打卡申请',
		width: 'auto',
		height : $(this).height()-40,
		pagination:true,
		rownumbers:true,
		border:false,
		singleSelect:true,
		nowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
		pageSize:30,
		pageList:[10,20,30,40],
		remoteSort:false,//定义是否从服务器对数据进行排序。
		striped:true,//是否显示斑马线
		columns:[[
		        {field : 'appNo',title : '申请编号',width :130,align : 'center'},
                {field : 'userName',title : '申请人',width : 110,align : 'center'},
	            {field : 'deptName',title : '所属部门',width : 170,align : 'center'},
                {field : 'appDtime',title : '申请日期',width : 150,align : 'center'},
                {field : 'appStatus',title : '申请状态',width : 90,align : 'center',formatter:function(value,row,index){
                	if(value == null || value == ""){
                		return "初始状态";
                	}
                	return value;
                }},
                {field:'forgetPluginReason',title:'忘打卡事由',width : 400,align : 'center'},
                {field:'remark',title:'备注信息',width:400,align:'center'},
                {field : 'caozuo',title : '操作',width :180,align : 'center',formatter:function(value,row,index){
                	var result = ""; 
				    if (row.taskModel.assistant == null || row.taskModel.assistant == "") {
						result += "<a href='javascript:void(0);' onclick='holdWorkTask("+index+");'>签收任务</a>　　";
					}else{
						result += "<a href='javascript:void(0);' onclick='handleTaskDialog("+index+");'>办理任务</a>　　";
					} 
				    result += "<a href='javascript:void(0);' onclick='checkHistoryOpinions("+ index +");'>查看审批意见</a>　　";
					return result;
                }}
	   ]],
	   onLoadSuccess:function(data){
           $(this).datagrid("doCellTip",{'max-width':'100px'});
	   }
	});
}
//签收任务
function holdWorkTask(index){
	var rows = $datagrid.datagrid("getRows");
	var row = rows[index];
	$.ajax({
		type:"POST",
		url:"empForgetPluginAppController/holdWorkTask.do",
		data:{"taskId" : row.taskId},
		dataType:"JSON",
		success:function(msg){
			if(msg.status){
				$datagrid.datagrid('reload');
			}
			$.messager.alert(msg.title,msg.message,'info');
		}
	});
}
//办理任务
function handleTaskDialog(index){
	var rows = $datagrid.datagrid("getRows");
	$selRow = rows[index];
	$$row = rows[index];
	$.ajax({
		type:"POST",
		url:"workflowAction/findTaskFormKeyByTaskId.do",
		data:{"taskId":$selRow.taskId},
		success:function(jspName){
			 $banliWindow = $("<div></div>").dialog({
					title:'办理任务',
					width:870,
					height:530,
					modal:true,
					href:jspName,
					onLoad:function(){
						$("#taskForm #businessID").val($selRow.fpaId);
				    	$("#taskForm #taskID").val($selRow.taskId);
				    	$("#taskForm #formKey").val($selRow.taskModel.formKey);
				    	$("#taskForm").form("load",$selRow);
				    },
					onClose:function(){
						$datagrid.datagrid('reload');
						$(this).dialog('destroy');
						$row = null;
					},
					buttons:[{
				    	text:'关闭',
				    	iconCls:'icon-cancel',
						handler:function(){
							$banliWindow.dialog('close');
						}
				    }]
			 }); 
		}
	}); 
}
//查看历史审批意见
function checkHistoryOpinions(index){
	var rows = $("#taskGrid").datagrid("getRows");
	$$row = rows[index];//获取本条数据
	$("<div></div>").dialog({
		title: '历史审批意见',    
	    width: 850,    
	    height: 500,    
	    closed: false,    
	    cache: false,    
	    href: 'jsp/pd/optionsList.jsp',    
	    modal: true,
	    onClose : function(){
	    	$(this).dialog('destroy');
	    	$$row = null;
        }
	});
}
//高级搜索
function toSearch(){
	var data = $("#searchForm").serializeObject();
	$waitTaskGrid.datagrid("reload",data);
};
</script>
<body>
<div class="position" style="margin-top: 5px;">您当前所在位置：任务管理  &gt; 任务办理 </div>
<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
	<form id="searchForm">
	  <table>
	    <tr>
	      <td>申请编号:</td>
	      <td><input name="appNo" class="easyui-textbox"/></td>
	      <td>申请日期:</td>
	      <td>
	         <input id="appDateMini" name="appDateMini" class="easyui-textbox easyui-datetimebox" editable="false"/>
	         -
	         <input id="appDateMax" name="appDateMax" class="easyui-textbox easyui-datetimebox" editable="false"/>
	      </td>
	      <td>
	         <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="toSearch();">搜索</a>
	         <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="$('#searchForm').form('clear')">重置</a>
	      </td>
	    </tr>
	  </table>
	</form>
</div>
<table id="taskGrid"></table>
</body>
</html>
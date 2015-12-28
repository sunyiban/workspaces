<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
a{
  text-decoration:none;
}
</style>
</head>
<script type="text/javascript">
var $dialog;
var $datagrid;
var $row;
var $$row;
$(function(){
	createFullmemberAppGrid();
});
function createFullmemberAppGrid(){
	$datagrid = $("#empFullmemberAppGrid").datagrid({
		url:'empFullmemberAppController/findEmpFullmemberAppList.do',
		title:'员工转正申请',
		width: 'auto',
		height : $(this).height()-80,
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
		        {field : 'appNo',title : '申请编号',width :120,align : 'center'},
                {field : 'userName',title : '申请人',width : 70,align : 'center'},
	            {field : 'deptName',title : '所属部门',width : 170,align : 'center'},
                {field : 'entryDate',title : '入职日期',width : 80,align : 'center'},
                {field : 'bgProbDate',title : '试用期开始日期',width : 100,align : 'center'},
                {field : 'edProbDate',title : '试用期结束日期',width : 100,align : 'center'},
                {field : 'regularSal',title : '转正薪资(元)',width : 80,align : 'center'},
                {field : 'workYears',title : '工作经验年限(年)',width : 100,align : 'center'},
                {field : 'position',title : '职位',width : 80,align : 'center'},
                {field : 'graduateSchool',title : '毕业院校',width : 80,align : 'center'},
                {field : 'major',title : '专业',width : 80,align : 'center'},
                {field : 'educationName',title : '学历',width : 80,align : 'center'},
                {field : 'age',title : '年龄',width : 60,align : 'center'},
                {field : 'chooseModeName',title : '甄选方式',width : 80,align : 'center'},
                {field : 'appStatusName',title : '申请状态',width : 80,align : 'center',formatter:function(value,row,index){
                	if(value == null || value == ""){
                		return "初始状态";
                	}
                	return value;
                }},
                {field : 'appDate',title : '申请日期',width : 90,align : 'center'},
                {field : 'workExplain',title : '工作说明',width : 100,align : 'center'},
                {field : 'caozuo',title : '操作',width :180,align : 'center',formatter:function(value,row,index){
                	if(row.procStatus == "1"){
             	    	return "<a href='javascript:void(0);' onclick='toEdit("+index+");'>编辑</a>&nbsp;&nbsp;"+"<a href='javascript:void(0);' onclick='toDelete("+ index + ");'>删除</a>&nbsp;&nbsp;"+"<a href='javascript:void(0);' onclick='sumitApply("+index+");'>提交申请</a>";
             	    }else if(row.procStatus == "2"){
             	    	return "<a href='javascript:void(0);' onclick='checkHistoryOpinions("+ index +");'>查看审批意见</a>&nbsp;&nbsp;"+"<a href='javascript:void(0);' onclick='showImage("+ index + ");'>查看流程图</a>";
             	    }else{
             	    	return "<a href='javascript:void(0);' onclick='checkHistoryOpinions("+ index +");'>查看审批意见</a>";
             	    }
                }}
	   ]],
	   onLoadSuccess:function(data){
           $(this).datagrid("doCellTip",{'max-width':'100px'});
	   }, 
	   toolbar:[{
		   iconCls: 'icon-add',
		   text:'添加',
		   handler:toOpenAddDialog
	   }]
	});
}
//新增弹框
function toOpenAddDialog(){
	$dialog=$("<div></div>").dialog({
		title: '添加',    
	    width: 690,    
	    height: 330,    
	    closed: false,    
	    cache: false,    
	    href: 'jsp/pd/empFullmemberApp/empFullmemberAppForm.jsp',    
	    modal: true,
	    onClose:function(){
	    	//刷新列表
	    	$datagrid.datagrid('reload');
	    	//关闭弹窗
	    	$(this).dialog('destroy');
	    },
	    buttons:[{
	    	text:'保存',
	    	iconCls:'icon-save',
			handler:saveAddEmpFullmemberApp
	    },{
	    	text:'关闭',
	    	iconCls:'icon-cancel',
			handler:function(){
				$dialog.dialog('close');
			}
	    }]
	});
}
//执行新增
function saveAddEmpFullmemberApp(){
	var isValid = $("#empFullmemberAppForm").form('validate');
	if(!isValid){
		return false;
	}
	$.ajax({
		type:'POST',
		url:'empFullmemberAppController/saveEmpFullmemberApp.do',
		data:$("#empFullmemberAppForm").serialize(),
		dataType:'JSON',
		success:function(msg){
		   if(msg.status){
			   $("#empFullmemberAppGrid").datagrid('reload');//刷新列表
			   $dialog.dialog('destroy');//销毁弹窗
		   }
		   //弹出提示信息
		   $.messager.alert(msg.title,msg.message,'info');
		}
	});
}
//执行编辑
function saveEditEmpFullmemberApp(){
	$.ajax({
		type:'POST',
		url:'empFullmemberAppController/saveEmpFullmemberApp.do',
		data:$("#empFullmemberAppForm").serialize(),
		dataType:'JSON',
		success:function(msg){
		   if(msg.status){
			   $("#empFullmemberAppGrid").datagrid('reload');//刷新列表
			   $dialog.dialog('close');//销毁弹窗
		   }
		   //弹出提示信息
		   $.messager.alert(msg.title,msg.message,'info');
		}
	});
}
//删除
function toDelete(index){
	var rows = $("#empFullmemberAppGrid").datagrid("getRows");
	var row = rows[index];
	$.messager.confirm('确定','是否确定删除所选的数据吗?',function(b) {
		if(b){
			$.ajax({
				   type: "POST",
				   url: "empFullmemberAppController/delEmpFullmemberAppByEfaId.do",
				   data:{"efaId":row.efaId},
			       async: false,
			       dataType:'JSON',
				   success: function(msg){
					   if(msg.status){
						   $datagrid.datagrid("reload");
					   }
					   $.messager.alert(msg.title,msg.message,'info');
				   }
			});
		}
	});
}
//编辑
function toEdit(index){
	var rows = $("#empFullmemberAppGrid").datagrid("getRows");
	$row = rows[index];
	$dialog = $("<div></div>").dialog({    
	    title: '修改',    
	    width: 650,    
	    height: 500,    
	    closed: false,    
	    cache: false, 
	    resizable:true,
	    href:'jsp/pd/empFullmemberApp/empFullmemberAppForm.jsp',    
	    modal: true,
	    onClose : function(){
	    	//弹框销毁
	    	$(this).dialog('destroy');
	    	//列表刷新
	    	$datagrid.datagrid("reload");
	    	$row = null;
	    },
	    buttons : [ {
	    	text:'保存',
	    	iconCls : 'icon-save',
			handler:saveEditEmpFullmemberApp
	     },{
			text : '关闭',
			iconCls : 'icon-cancel',
			handler : function() {
				$dialog.dialog('close');
				$row = null;
			}
		}]
	});
}
//提交申请
function sumitApply(index){
	var rows = $("#empFullmemberAppGrid").datagrid("getRows");
	var row = rows[index];
	$.messager.confirm('确定','是否确定提交所选的数据吗?',function(flag) {
		if (flag) {
			$.ajax({
					url:"empFullmemberAppController/sumitApply.do",
					data : {"efaId":row.efaId,"appNo":row.appNo},
					success : function(msg) {
						if(msg.status){
							$datagrid.datagrid('reload');
						}
						$.messager.alert(msg.title,msg.message,'info');
					}
				});
			}
    });
}
//查看流程图
function showImage(index){
	var rows = $("#empFullmemberAppGrid").datagrid("getRows");
	var row = rows[index];
	var src = "empFullmemberAppController/showProcessImg.do?efaId="+row.efaId;
	$('#imageDialog').dialog("open");
	$("#image").attr("src", src);
}
//查看历史审批意见
function checkHistoryOpinions(index){
	var rows = $("#empFullmemberAppGrid").datagrid("getRows");
	$$row = rows[index];//获取本条数据
	$("<div></div>").dialog({
		title: '历史审批意见',    
	    width: 850,    
	    height: 500,    
	    closed: false,    
	    cache: false,    
	    href: 'jsp/ad/optionsList.jsp',    
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
	$datagrid.datagrid("reload",data);
};
</script>
<body>
<div class="position" style="margin-top: 5px;">您当前所在位置：人力资源规划  &gt; 员工关系 &gt; 人事手续管理 &gt; 员工转正申请</div>
<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
	<form id="searchForm">
	  <table>
	    <tr>
	      <td>申请编号:</td>
	      <td><input name="appNo" class="easyui-textbox"/></td>
	      <td>申请日期:</td>
	      <td>
	         <input id="appDateMini" name="appDateMini" class="easyui-textbox easyui-datebox" editable="false"/>
	         -
	         <input id="appDateMax" name="appDateMax" class="easyui-textbox easyui-datebox" editable="false"/>
	      </td>
	      <td>
	         <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="toSearch();">搜索</a>
	         <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="$('#searchForm').form('clear')">重置</a>
	      </td>
	    </tr>
	  </table>
	</form>
</div>
<table id="empFullmemberAppGrid"></table>
<div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
	<img id="image" src="" >
</div>
</body>
</html>
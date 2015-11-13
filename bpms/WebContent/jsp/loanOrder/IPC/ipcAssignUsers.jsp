<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- 车贷负责人 -->
<style type="text/css">
	#acceptTaskForm table input{border: none;}
	.table th{ text-align: right;}
	.table td{ text-align: left;}
</style>
<script type="text/javascript">
$(function(){
	if($row==null){
		$row = $grid.datagrid('getSelected');
	}
	// 渲染用户的信息的列表
	var assignUsersGrid = $("#assignUsersGrid").datagrid({
		url : "loanOrder/loanOrderAction!findCandidatePersonsByCode.action",
		width : 'auto',
		height :  $(this).height()-190,
		queryParams: {"roleCode":"IPCDiFangDiaoCha"},
		rownumbers:true,
		border:false,
		singleSelect:true,
		striped:true,
		columns : [ [ 
		              {field : 'name',title : '用户名',width : 100,align : 'center'},
		              {field : 'email',title : '邮箱',width : 150,align : 'center'},
		              {field : 'tel',title : '电话',width :150,align : 'center'},
		              {field : 'organization',title : '组织',width :220,align : 'center',
		            	    formatter:function(value,row){
			            	  	return value.fullName;  
							}
					  }, 
		              {field : 'description',title : '描述',width : 570,align : 'left'}
		              ] ],
		toolbar : [ {
			iconCls : 'icon-save',
			text : '指派审查员',
			handler : function(){
					// 修改处理人
					var user  = assignUsersGrid.datagrid("getSelected");
					if(user==null) {
						$.messager.alert('提示','请选择要指派的人！','warning');
						return;
					}
					// 确认是否提交
					$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){
						if (r){
							var data = {
								"comment" : $("#comment").val(),
								"title" : $("#title").val(),
								"result" :"IPCGroupLeaderThrough",
								"loanOrderId" : $row.loanOrderId,
								"taskId": $row.taskId,
								"processingResult":"A",
								"userId":user.userId
							}
							$.ajax({
								type : "POST",
								url : "loanOrder/loanOrderAction!submitTask.action",
								data : data,
								success : function(msg) {
									$grid.datagrid('reload');
									$taskFormDialog.dialog('close');
									$assignUsersDailog.dialog('close');
								}
							});
						}
					});
				}
			}]
	});
})
</script>
<!-- 指派人的信息列表 S -->
<div title="指派的用户的信息列表" data-options="iconCls:'icon-cstbase',selected:true" >   
	<table id="assignUsersGrid"></table>
</div>
<!-- 指派人的信息列表 E -->
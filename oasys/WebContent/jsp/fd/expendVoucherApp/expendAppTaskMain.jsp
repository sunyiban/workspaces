<%@page import="com.oasys.util.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
    <title>支出凭单销申请代办任务</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="../../../layout/script.jsp"></jsp:include>
	<style type="text/css">
a{
			text-decoration:none;
		}
</style>
	<script type="text/javascript">
	
		/**
		 * 根据索引获取对象名称
		 * @param target Grid对象
		 * @param index 索引
		 * @returns 索引的对应行的信息
		 */
		//根据index获取该行
		//审批意见表
		var $$row;
		
		var $row;
		function getRowData(index){
			if (!$.isNumeric(index) || index < 0) {
				return undefined;
			}
			var rows = $("#dg").datagrid("getRows");
			return rows[index];
		}	
	
			var $grid;
			$(function() {
				
				$(window).resize(function(){  
		            $("#dg").datagrid("resize",{  
		            	height: $(window).height()-90  
		            });                
		        }); 
				//获取key值
				<%
			    String definitionKey=request.getParameter("key");
			    %>
				$grid =$("#dg").datagrid({
					url : "ExpendVoucherApp/findExpendTaskList.do?definitionKey=<%=definitionKey%>",
					width: 'auto',
					height : $(window).height()-90,
					pagination:true,
					rownumbers:true,
					border:true,
					singleSelect:false,
					nowrap:true,
					multiSort:false,
					border:false,
					fitColumns:false,
					pageList:[10,20,30,40],
					columns : [ [
					             	 {field : 'appNo',title : '申请编号',width:110,align : 'center'},
										{field : 'applicationName',title : '申请人',width:80,align : 'center',
											/* formatter : function(value, row, index) {
												return "<a href=\"javascript:void(0)\" onclick=\"showView("+ index + ");\">" + row.applicationName + "</a>";
											} */
										},
										{field : 'appDate',title : '申请日期',width:80,align : 'center'}, 
										{field : 'clause',title : '即付款项',width : 300,align : 'center'},
										{field : 'grantExp',title : '预借旅费(元)',width :90,align : 'center',
											 formatter : function(value, row, index) {
													if(value.toString().indexOf(".")<0){
														 return value.toString().replace(/(\d)(?=(\d{3})+$)/g,"$1,")+".00"
													}else{
														 return value.toString().split(".")[0].replace(/(\d)(?=(\d{3})+$)/g,"$1,")+"."+value.toString().split(".")[1];;
													}
											}	},
										{field : 'total',title : '合计金额(元)',width : 90,align : 'center',
												 formatter : function(value, row, index) {
														if(value.toString().indexOf(".")<0){
															 return value.toString().replace(/(\d)(?=(\d{3})+$)/g,"$1,")+".00"
														}else{
															 return value.toString().split(".")[0].replace(/(\d)(?=(\d{3})+$)/g,"$1,")+"."+value.toString().split(".")[1];;
														}
												}	},
										{field : 'supplyAmt',title : '补领金额(元)',width :90,align : 'center',
													 formatter : function(value, row, index) {
															if(value.toString().indexOf(".")<0){
																 return value.toString().replace(/(\d)(?=(\d{3})+$)/g,"$1,")+".00"
															}else{
																 return value.toString().split(".")[0].replace(/(\d)(?=(\d{3})+$)/g,"$1,")+"."+value.toString().split(".")[1];;
															}
													}	},
										{field : 'givebackAmt',title : '退还金额(元)',width : 90,align : 'center',
														 formatter : function(value, row, index) {
																if(value.toString().indexOf(".")<0){
																	 return value.toString().replace(/(\d)(?=(\d{3})+$)/g,"$1,")+".00"
																}else{
																	 return value.toString().split(".")[0].replace(/(\d)(?=(\d{3})+$)/g,"$1,")+"."+value.toString().split(".")[1];;
																}
														}	},
										{field : 'procStatus',title : '申请状态',width :80,align : 'center',formatter:function(value,row,index){
						                	if(row.procStatus == "1"){
						                		return "初始状态";
						                	}else if(row.procStatus == "2"){
						                		return "审批中";
						                	}else if(row.procStatus == "3"){
						                		return "已完成";
						                	}else if(row.procStatus == "4"){
						                		return "已失效";
						                	}else if(row.procStatus == "5"){
						                		return "已撤销";
						                	}else{
						                		return "已拒绝";
						                	}
						                }},
										{field : 'remark',title : '备注',width : 300,align : 'center'},
										{field : 'id',title : '操作',width:300,align : 'center',
										formatter : function(value, row, index) {
											var result = ""; 
											if (row.assistant == null || row.assistant == "") {
												result += "<a href='javascript:void(0);' onclick='claimTask("+row.taskID+");'>签收任务</a>　";
											}else{
												result += "<a href='javascript:void(0);' onclick='handleTaskDialog("+index+");'>办理任务</a>　";
											}
											result += "<a href='javascript:void(0);' onclick='lookLeaveAppCommentDialog("+index+");'>查看审批意见</a>　"
											result += "<a href='javascript:void(0);' onclick='showImage("+ index + ");'>查看当前流程图</a>　"
											result+="<a href=\"javascript:void(0)\" onclick=\"lookExpensesAttment("+ index + ");\">查看附件</a>";
											
											return result; 
										}
									} 
							] ],
							 onLoadSuccess:function(data){
								
						           $(this).datagrid("doCellTip",{'max-width':'100px'});
						           
							  }, 
					toolbar : '#tb'
				});
			});	
			
		//办理任务
		function handleTaskDialog(index){
			$row  = getRowData(index);
			$$row =$row;
			$.ajax({
				type:"POST",
				url:"workflowAction/findTaskFormKeyByTaskId.do",
				data:{"taskId":$row.taskID},
				async:false,
				onClose:function(){
					$("#dg").datagrid('reload');
				},
				success:function(jspName){
					 $banliDialog = $("<div></div>").dialog({
							title:'办理任务',
							width:850,
							height:$(window).height()*0.7,
							modal:true,
							href:"jsp/fd/expendVoucherApp/taskExpend/"+jspName,
							onLoad:function(){
								var TaskFrom=$("#taskForm");
								TaskFrom.form("load",$row);
								
								$("#taskForm #businessID").val($row.evaId);
						    	$("#taskForm #taskID").val($row.taskID);
						    	$("#taskForm #definitionKey").val("<%=definitionKey%>");
							},
							onClose:function(){
								$(this).dialog('destroy');
							}
					 }); 
				}
			});
		}	
		
		
		// 查看流程图片
		function  showImage(index) {
			var row = getRowData(index);
			var src = "ExpendVoucherApp/showProcessImg.do?taskId="+ row.taskID+"&applicantNo="+row.applicantNo;
			$('#imageDialog').dialog("open");
			$("#image").attr("src", src);
		}
	    
		// 领取任务
		function claimTask(taskId) {
			//var row = getRowData($grid,index);
			$.messager.confirm('提示','是否确定签收所选任务？',function(flag) {
				if (flag) {
					$.ajax({
						url : "ExpendVoucherApp/getUserTravelTask.do",
						data : {"taskId" : taskId},
						async:false,
						success : function(rsp) {
							if(rsp.status){
								parent.$.messager.alert(rsp.title,rsp.message,'info');
							}else{
								parent.$.messager.alert(rsp.title,rsp.message,'warning');
							}
							$grid.datagrid('reload');
						}
					});
				}
			});
		}
		
		 // 查看流程批注
		function lookLeaveAppCommentDialog(index){
			$$row=getRowData(index);
			$("<div></div>").dialog({
				title: '历史审批意见',    
			    width: 900,    
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
		
		//执行查询
		function subCustomerRepaymentForm(){
			$("#dg").datagrid("load",{
				appNo:$("#appNoMain").val(),
				appDateS:$('#appDateS').datebox('getValue'),
				appDateE:$('#appDateE').datebox('getValue')
			});  
		} 
		
		//查看附件
		function lookExpensesAttment(index){
			//主页面查看时，明细为申请id
			var row = this.getRowData($grid,index);
			checkAttachementDetail(row.appNo,row.applicantNo,"1");
		};
		
</script>
  </head>
  <body>
		<div class="position" style="margin-top: 5px;">您当前所在位置：差旅报销申请  &gt; 代办任务</div>
		<!-- 高级查询栏区域 -->
  		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
				<form id="customerRepaymentForm"  method="post" style="min-width: 1200px;">
					<table cellpadding="0px;">
						<tr>
						  <th>申请编号：</th>
					      <td>
					     	 <input name="appNo" id="appNoMain" class="easyui-textbox"/>
					      </td>
					       <th align="right">申请日期：</th>
						      <td>
						      	 <input id="appDateS" name="appDateS" placeholder="请选择起始日期" class="easyui-textbox easyui-datebox" data-options="editable:false" /> &nbsp;&nbsp;至&nbsp;&nbsp;
						      </td>
						      <td>
						    	 <input id="appDateE" name="appDateE" placeholder="请选择截止日期" class="easyui-textbox easyui-datebox" data-options="editable:false"/>
						      </td>
					      <td>
					         <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="subCustomerRepaymentForm();">搜索</a>
					         <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="$('#customerRepaymentForm').form('reset');">重置</a>
					      </td>
						</tr>
					</table>
				</form>
			</div>
		<table id="dg"></table>
	    <div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
			<img id="image" src="" >
		</div>
		 <div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
			<img id="image" src="" >
		</div>
		<!-- 跳转办理任务 -->
		<div id="mgrTaskView"></div>
  </body>
</html>

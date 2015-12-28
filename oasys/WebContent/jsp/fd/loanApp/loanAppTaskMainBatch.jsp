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
    <title>借款申请代办任务</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="../../../layout/script.jsp"></jsp:include>
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
		            $("#dg").datagrid({  
		            	height: $(window).height()-130  
		            });                
		        }); 
				//获取key值
				<%
			    String definitionKey=request.getParameter("key");
			    %>
				$grid =$("#dg").datagrid({
					url : "LoanTask/findNotTaskClaimList.do?definitionKey=<%=definitionKey%>",
					width: 'auto',
					height : $(this).height()-130,
					pagination:true,
					rownumbers:true,
					border:true,
					singleSelect:false,
					nowrap:true,
					multiSort:false,
					border:false,
					fitColumns:true,
					pageList:[10,20,30,40],
					columns : [ [
					             	 {field : 'appNo',title : '申请编号',width:parseInt($(this).width()*0.07),align : 'center'},
							{field : 'applicantName',title : '申请人',width:parseInt($(this).width()*0.06),align : 'center',
								formatter : function(value, row, index) {
									return "<a href=\"javascript:void(0)\" onclick=\"showView("+ index + ");\">" + row.applicantName + "</a>";
								}
							},
							{field : 'appDate',title : '申请日期',width:parseInt($(this).width()*0.06),align : 'center'},
							{field : 'payerName',title : '付款人',width:parseInt($(this).width()*0.06),align : 'center',
								formatter : function(value, row, index) {
									return "<a href=\"javascript:void(0)\" onclick=\"showView("+ index + ");\">" + row.payerName + "</a>";
								}
							},
							{field : 'payDate',title : '付款日期',width : parseInt($(this).width()*0.06),align : 'center'},
							{field : 'loanAmt',title : '借款金额',width : parseInt($(this).width()*0.06),align : 'center'},
							{field : 'payMode',title : '付款方式',width : parseInt($(this).width()*0.06),align : 'center',formatter:function(value,row,index){
			                	if(row.payMode=="0"){
			                		return "支票";
			                	}else{
			                		return "现金";
			                	}
			                }},
			                {field : 'loanPurpose',title : '借款用途',width : parseInt($(this).width()*0.06),align : 'center',formatter:function(value,row,index){
			                	if(row.loanPurpose=="1"){
			                		return "出差";
			                	}else{
			                		return "其它支付";
			                	}
			                }},
							{field : 'capitalNatureName',title : '资金性质',width : parseInt($(this).width()*0.06),align : 'center'},
							{field : 'loanReson',title : '借款理由',width : parseInt($(this).width()*0.06),align : 'center'},
							 {field : 'isBalance',title : '是否销账',width : parseInt($(this).width()*0.06),align : 'center',formatter:function(value,row,index){
				                	if(row.isBalance=="0"){
				                		return "是";
				                	}else{
				                		return "否";
				                	}
				                }},
							{field : 'procStatus',title : '申请状态',width :parseInt($(this).width()*0.06),align : 'center',formatter:function(value,row,index){
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
							{field : 'remark',title : '备注',width : parseInt($(this).width()*0.1),align : 'center'},
									{field : 'id',title : '操作',width:parseInt($(this).width()*0.22),align : 'center',
										formatter : function(value, row, index) {
											var result = ""; 
											if (row.assistant == null || row.assistant == "") {
												result += "<a href='javascript:void(0);' onclick='claimTask("+row.taskID+");'>签收任务</a>　　";
											}else{
												result += "<a href='javascript:void(0);' onclick='handleTaskDialog("+index+");'>受理任务</a>　　";
											}
											result += "<a href='javascript:void(0);' onclick='lookLeaveAppCommentDialog("+index+");'>查看审批意见</a>　　"
											result += "<a href='javascript:void(0);' onclick='showImage("+ index + ");'>查看当前流程图</a>"
											
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
			
			//批量处理任务
			function signTask(){
				var rows = $grid.datagrid("getSelections");
				if(rows==null || rows.length<=0){
					$.messager.alert("提示","请选择至少一条记录!","warning");
					return false;
				}
				ajaxLoading("正在处理 请稍后...");
				var businessID = new Array();
				var formKey = "";
				var taskID = new Array();
				var appNo = new Array;
				for(var i=0;i<rows.length;i++){
					if($.inArray(rows[i].btaId,businessID)==-1){
						businessID.push(rows[i].btaId);
						formKey=rows[i].formKey;
						taskID.push(rows[i].taskID);
						appNo.push(rows[i].appNo);
					}
				}
				var data1 = "appNo="+appNo.join(",")+"&businessID="+businessID.join(",")+"&taskID="+taskID.join(",")+"&formKey="+formKey;
			 	$.ajax({
					type: "POST",
					url:"LeaveTask/addMangeTaskList.do",
					data:data1,
				    success: function(iJson) {
				    	$("#dg").datagrid("load",{});
				    	ajaxLoadEnd();
			 	    	$.messager.alert(iJson.title,iJson.message,'info');
				    }
				}); 
			}	
			
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
							width:830,
							height:800,
							modal:true,
							href:jspName,
							onLoad:function(){
								var TaskFrom=$("#taskForm");
								TaskFrom.form("load",$row);
								
								$("#taskForm #businessID").val($row.btaId);
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
			var src = "LoanTask/showProcessImg.do?taskId="+ row.taskID+"&applicantNo="+row.applicantNo;
			$('#imageDialog').dialog("open");
			$("#image").attr("src", src);
		}
	    
		// 领取任务
		function claimTask(taskId) {
			//var row = getRowData($grid,index);
			$.messager.confirm('提示','是否确定签收所选任务？',function(flag) {
				if (flag) {
					$.ajax({
						url : "LoanTask/taskUserClaim.do",
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
		
</script>
  </head>
  <body>
      <div data-options="region:'center',border : false">
  		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
				<div class="position" style="margin-top: 5px;">您当前所在位置：借款申请  &gt; 代办任务</div>
		</div>
		<!-- 高级查询栏区域 -->
  		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
				<form id="customerRepaymentForm"  method="post">
					<table cellpadding="10px;">
						<tr>
						  <th>申请编号:</th>
					      <td>
					     	 <input name="appNo" id="appNoMain" class="easyui-textbox"/>
					      </td>
					      <th>申请日期:</th>
					      <td>
					      	 <input id="appDateS" name="appDateS" placeholder="请选择起始日期" class="easyui-textbox easyui-datebox" data-options="editable:false" />
						　                             　至　　
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
		<div id="tb" style="padding:2px 0">
			<a id="id4ExportReports" class="easyui-linkbutton" data-options="iconCls:'icon-add'" plain="true" onclick="signTask();">批量受理任务</a>
		</div>
	    <div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
			<img id="image" src="" >
		</div>
		 <div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
			<img id="image" src="" >
		</div>
		<!-- 跳转办理任务 -->
		<div id="mgrTaskView"></div>
  	</div>	
  </body>
</html>

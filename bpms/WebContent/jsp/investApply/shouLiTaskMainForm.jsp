<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>受理任务</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="../../layout/script.jsp"></jsp:include>
	<style type="text/css">
		a{
			text-decoration:none;
		}
	</style>
	<script type="text/javascript">
			//声明一个全局变量row
			//该变量可以在基于此Main页面上的弹出页面中使用，比如optionList.jsp页面。
			var row;
	
			var $dg;
			var $grid;
			$(function() {
				
				//初始化高级查询区域的组件
				$("#searchbox").searchbox({
					menu:'#mm',
					searcher: function(value, name){
				    	var str="{\"searchName\":\""+name+"\",\"searchValue\":\""+value+"\"}";
			            var obj = eval('('+str+')');
			            $dg.datagrid('load',obj); 						
					}
				});

				// 自动调整页面高度
				$(window).resize(function(){  
			        $("#dg").datagrid({  
			        	height: $(window).height()-100,
			        	width : 'auto'
			        });                
			    });
				
				//加载当前用户，比如销客专员，受理任务的数据
				 $dg = $("#dg");
				 $grid=$dg.datagrid({
					url : "investApply/investApplyAction!findAllClaimedTask.action", 
					width : 'auto',
					height : $(window).height()*0.96,
					pagination:true,
					rownumbers:true,
					border:true,
					singleSelect:true,
					nowrap:true,
					multiSort:false,
					columns : [ [ 
					              {field : 'investorName',  title : '客户姓名',    width : parseInt($(this).width()*0.06), align:'center', 
					            	  formatter: function(value, row, index){
					            		  return "<a href=\"javascript:void(0)\" onclick=\"showInvestorView("+index+")\">"+value+"</a>";
					            	  }
					              },
					              {field : 'idCrad',        title : '身份证号',    width : parseInt($(this).width()*0.08), align:'center'},
					              {field : 'mobTel',        title : '移动电话',    width : parseInt($(this).width()*0.08), align:'center' },
					              {field : 'contractNo',    title : '合同编号',    width : parseInt($(this).width()*0.06), align:'center', 
					            	  formatter: function(value, row, index){
					            		  if(row.contractNo == null || row.contractNo == ""){					            			  
						            		return "";					            			  					
					            		  }else{					            			  
										  	return "<a href=\"javascript:void(0)\" onclick=\"showInvestContractDetailsView("+index+")\">"+value+"</a>";					            			 
					            		  }
					            	  }					            	  					            	  
					              },
					              {field : 'signDate',      title : '合同签署日期', width : parseInt($(this).width()*0.07), align:'center',
					            	  formatter: function(value, row, index){
 				            		  	    return value; 
					            	  }					            	  					            	  					            	  
					              },
					              {field : 'prodId',    title : '理财产品',    width : parseInt($(this).width()*0.06), align:'center' ,
					            	  formatter: function(value, row, index){
									      var result ="<a href='javascript:void(0);' onclick='showInvestorAndInvestProductsDetailsView(\""+row.investOrderId+"\");'>详情</a>";
					      				  return result;					            		  
					            	  }
					           	  },
					              {field : 'bankName',       title : '开户行名称',   width : parseInt($(this).width()*0.08), align:'center',
					            	  formatter: function(value, row, index){
					            		  if(row.bankName == null || row.bankName == ""){					            			  
						            		return "";					            			  					
					            		  }else{
					            			  return value;
					            		  }
					            	  }							           		  
					              },
					              {field : 'actNo',    title : '开户行帐号',   width : parseInt($(this).width()*0.08), align:'center',
					            	  formatter: function(value, row, index){
					            		  if(row.actNo == null || row.actNo == ""){					            			  
						            		return "";					            			  					
					            		  }else{
					            			  return value;
					            		  }					            		  
					            	  }						            	  
					              },
					              {field : 'operType',       title : '操作',width : parseInt($(this).width()*0.15), align:'center', 
					            	  formatter: function(value,row,index){
		      							var result ="<a href='javascript:void(0);' onclick='checkInvestOrderHisApprovalOpinions("+index+");'>查看审批意见</a>　 ";
		      								result+="<a href='javascript:void(0);' onclick='checkProcessImg("+index+");'>查看审批流程</a>　 ";
		      								result+="<a href='javascript:void(0);' onclick='handleTaskDialog("+index+");'>办理任务</a>";
					      				return result;
				            	 	 }
					              }
					              ] ],
		              toolbar:'#tb',
		              onClickCell:function(rowIndex, field, value){
		            	  $(this).datagrid("selectRow","rowIndex");
		              }					              
				});				 				 				 
			});

		// 根据索引获取行的基本信息的函数
		function getRowData (index) {
	        if (!$.isNumeric(index) || index < 0) { return undefined; }
	        var rows = $dg.datagrid("getRows");
	        return rows[index];
	    }
		
		//查看投资人详细信息
		function showInvestorView(index){
			var rows = $("#dg").datagrid("getRows");
			var row = rows[index];
			$('#investorView').dialog({    
					    title: '投资客户详情',    
					    width: 800,    
					    height: 550,    
					    closed: false,    
					    cache: false,    
					    href: 'investor/investorAction!findInvestorByInvestorId.action?investorId='+row.investorId,    
					    modal: true   
					}); 
		}
		
		
		// 查看该投资人的"合同详情"
		function showInvestContractDetailsView(index){
			var row = this.getRowData(index);
			$('#contractInfoDialog').dialog({    
			    title: '合同详情',    
			    width: 600,    	
			    height: 600,    
			    closed: false,
			    closable: true,
			    cache: false,    
			    href: 'investOrder/investOrderAction!findInvestorOrderContractDetails.action?investOrderId='+row.investOrderId,    
			    modal: true   
			});				
		}
		
		
		
		//查看该投资人的理财产品详情界面
		function showInvestorAndInvestProductsDetailsView(investOrderId){			
			$('#investorAndInvestProductsDialog').dialog({    
			    title: '理财产品详情',    
			    width: 800,    	
			    height: 350,    
			    closed: false,
			    closable: true,
			    cache: false,    
			    href: 'investOrder/investOrderAction!findInvestorAndInvestProductsDetails.action?investOrderId='+investOrderId,    
			    modal: true   
			});  													 					
		}
		
		// 查看历史审批意见		
		function checkInvestOrderHisApprovalOpinions(index){
			var row = this.getRowData(index);
			parent.$.modalDialog.openner= $grid;
			parent.$.modalDialog({
				title : '审批意见查看',
				width : 1000,
				height : 650,
				/* href : "jsp/investOrder/investOrderProcessComment.jsp?taskId="+row.taskId */
				href: 'jsp/investApply/optionsList4InvestApplyShouLi.jsp?taskId='+row.taskId
			});
		}		

		// 查看投资订单的流程图片
		function checkProcessImg (index) {
			var row = this.getRowData(index);
			var src = "investApply/investApplyAction!checkWorkFlowImg.action?investOrderId=" + row.investOrderId;
			$('#imageDialog').dialog("open");
			$("#image").attr("src", src);
		}
		
		// 处理任务
		function handleTaskDialog(index) {
			var row = this.getRowData(index);			
			parent.$.modalDialog.openner= $grid;
			$.ajax({
				type : "POST",
				url : "investApply/investApplyAction!findTaskFormKeyByTaskId.action",
				data : {"taskId":row.taskId},
				success : function(jspName) {
					parent.$.modalDialog({
						title : '投资申请审批流程',
						width : 1000,
						height : 650,
						href:jspName+"?investOrderId="+row.investOrderId+"&investorId="+row.investorId+"&taskId="+row.taskId,
						onDestroy:function(){
							//刷新列表
							$dg.datagrid("reload");
						}		
					});
				 }
			});
		}		
		
	</script>
  </head>
  <body>
      <div data-options="region:'center',border : false">
		<div class="position" style="margin-top: 5px;">您当前所在位置： 业务管理  &gt; 财富业务管理  &gt; 任务办理 &gt; 受理任务</div>
		
		<!-- 高级查询栏区域 -->
		<!-- <div id="tb" style="padding:2px 0">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<th style="padding-left:2px">				
						请选择查询项目：
					</th>
					<td style="padding-left:2px">						
						<input id="searchbox" type="text"/>
					</td>
				</tr>
			</table>
		</div>
		<div id="mm">
				<div name="investorName">客户名称</div>
				<div name="idCrad">身份证号</div>
		</div> -->
		
		<!-- 受理任务的投资订单数据表格区域 -->
		<table id="dg"></table>
		
		<!-- 投资客户数据对话框区域 -->
		<div id="investorView"></div>

		<!-- 合同详情信息对话框区域 -->
		<div id="contractInfoDialog"></div>

		<!-- 理财产品详情对话框区域 -->
		<div id="investorAndInvestProductsDialog"></div>

		<!-- 查看历史审批意见对话框区域 -->
		<div id="OpinionsDialog"></div>
		
		<!-- 流程图片弹框 -->
		<div id="imageDialog" class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
		   <img id="image" src="">
		</div>				
  	</div>	
  </body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<jsp:include page="../../layout/script.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投资赎回管理</title>
</head>
<script type="text/javascript">
	$(function(){
		createInvestRedeemListDataGrid();
	});
	
	//定义一个全局的变量
	var rowDataSelected;
	
	//定义导出Excel报表的基础URL
	var exportExcelUrl = "investRedeem/investRedeemAction!exportExcel4RedeemInvestorDetails.action";
	
	//工具
	function getRowData4RedeemMainJsp(index) {
		if (!$.isNumeric(index) || index < 0) {
			return undefined;
		}
		var rows = $("#investRedeemListDataGrid").datagrid("getRows");
		return rows[index];
	}	
	
	// 自动调整页面高度
 	$(window).resize(function(){  
            $("#investRedeemListDataGrid").datagrid({  
            	height: $(window).height()-100,
            	width : 'auto'
            });                
        });
	
	//渲染投资赎回列表
	function createInvestRedeemListDataGrid(){
		$("#investRedeemListDataGrid").datagrid({
  			url:'investRedeem/investRedeemAction!findInvestRedeemList.action',  
			title:'投资赎回列表',
			width: 'auto',
			height : $(window).height()-75,
			pagination:true,
			rownumbers:true,
			border:false,
			/* singleSelect:true, */
			nowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
			pageSize:30,
			pageList:[10,20,30,40],
			remoteSort:false,//定义是否从服务器对数据进行排序。
			striped:true,//是否显示斑马线
			columns:[[
					{field : 'investProcessType',title : '流程类型', width : parseInt($(this).width() * 0.05),align : 'center', 
						formatter: function(value, row, index){
							if(row.investProcessType == "1"){
								return "投资申请";
							}else if(row.investProcessType == "2"){
								return "投资赎回";
							}
						}
					},			          
			        {field : 'chName',title : '客户姓名', width : parseInt($(this).width() * 0.05) ,align : 'center',
		            	  formatter: function(value, row, index){
		            		  return "<a href=\"javascript:void(0)\" onclick=\"showInvestorView("+index+")\">"+value+"</a>";
		            	  }			        				        
			        },
	                {field : 'redeemBeginDate',title : '赎回申请日期', width : parseInt($(this).width() * 0.08) ,align : 'center', 
		            	  formatter: function(value, row, index){
		            		  if(value == null){		            			  
		            		  	return "未开启赎回流程";
		            		  }else{
		            			  return value;
		            		  }
		            	  }			        				        			        		
	                },	                
	                {field : 'createDate',title : '投资申请生成日期', width : parseInt($(this).width() * 0.08) ,align : 'center'},
	                {field : 'beginDate',title : '投资开始日期', width : parseInt($(this).width() * 0.08) ,align : 'center'},
	                {field : 'interestDate',title : '计息日期', width : parseInt($(this).width() * 0.08) ,align : 'center'},
	                {field : 'contractNo',title : '合同编号', width : parseInt($(this).width() * 0.07) ,align : 'center',
						  formatter: function(value, row, index){
							  if(row.contractNo == null || row.contractNo == ""){					            			  
					    		return "";					            			  					
							  }else{					            			  
							  	return "<a href=\"javascript:void(0)\" onclick=\"showInvestContractDetailsView("+index+")\">"+value+"</a>";					            			 
							  }
						  }	                		                
	                },
	                {field : 'bankName',title : '开户行名称', width : parseInt($(this).width() * 0.06) ,align : 'center'},
	                {field : 'actNo',title : '开户账号', width : parseInt($(this).width() * 0.08) ,align : 'center'},
	                {field : 'actName',title : '账户名称', width : parseInt($(this).width() * 0.05) ,align : 'center'},
	                {field : 'prodName',title : '理财产品', width : parseInt($(this).width() * 0.06) ,align : 'center',
		            	  formatter: function(value, row, index){
		            		  var result ="";
		            		  if(row.prodName == "" || row.prodName == "undefined" || row.prodName == null){
		            			  return result;  
		            		  }else{		            			  
		            		  	  return result ="<a href='javascript:void(0);' onclick='showInvestorAndInvestProductsDetailsView(\""+row.investOrderId+"\");'>"+row.prodName+"</a>";
		            		  }		      				  					            		  
		            	  }	                	
	                },
	                {field : 'investEdu',title : '投资金额(元)', width : parseInt($(this).width() * 0.06) ,align : 'center'},
	                {field : 'aa',title : '操作', width : parseInt($(this).width() * 0.12) ,align : 'center',
	                	formatter:function(value,row,index){
							/* 投资申请流程已经结束，投资流程类型为投资申请 */	                		
	                		if(row.processStatus == "0"){	                			
	                			return	"<a href='javascript:void(0);' onclick='deleteInvestRedeemRecord("+ index + ");'>删除赎回申请</a>&nbsp;&nbsp;&nbsp;" +
								 		"<a href='javascript:void(0);' onclick='submitInvestRedeem("+ index + ");'>提交赎回申请</a>" 
								; 
	                		}else if(row.processStatus == "1"){
		             	    	return "<a href='javascript:void(0);' onclick='checkProcessImg("+ index + ");'>查看流程图</a>&nbsp;&nbsp;&nbsp;"  +	
		             	    		   "<a href='javascript:void(0);' onclick='checkInvestOrderOpinions("+ index + ");'>查看审批意见</a>";
	                		}	                		
	                		else{	                				                			
	                			return  "<a href='javascript:void(0);' onclick='checkInvestOrderOpinions("+ index + ");'>查看审批意见</a>";
	                		}	                		
	                }}
	                
		   ]],
 		   toolbar:[
			{
			   iconCls: 'icon-add',
			   text:'新增投资赎回申请',
			   handler:toAddInvestRedeemRecord
		    }
/* 			,{
			   iconCls: 'icon-excel',
			   text:'导出赎回客户明细',
			   handler:toExportExcel4RedeemInvestor,			   
		    }		    
 */		   ]
		});
	}
	
	//导出赎回客户明细报表
	function toExportExcel4RedeemInvestor(){		
/* 		var selectedRowData = $("#investRedeemListDataGrid").datagrid("getSelections");				
		var investOrderIds = new Array();		
		for(var index=0; index<selectedRowData.length; index++){
			investOrderIds.push(selectedRowData[index].investOrderId);
		}		
 		var exportExcelUrl = "investRedeem/investRedeemAction!exportExcel4RedeemInvestor.action?investOrderIds="+investOrderIds;  */		
						
 		//执行报表导出
		window.location.href = exportExcelUrl;		
	}
	
	//新增投资赎回
	function toAddInvestRedeemRecord(){
		//初始化已经完成审批的投资申请信息列表数据
		initAlreadyApprovedInvestApplyList();
		$("#addInvestRedeemDialog").dialog("open");
	}
	
	//初始化已经完成审批的投资申请信息列表数据
	function initAlreadyApprovedInvestApplyList(){
		$("#approvedInvestApplyListDataGrid").datagrid({
			url:'investRedeem/investRedeemAction!findAlreadyApprovedInvestApplyList.action',
			width: 885,
			height: 534,
			pagination:true,
			rownumbers:true,
			border:false,
			singleSelect:true,
			nowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
			pageSize:20,
			pageList:[10,20,30,40],
			remoteSort:false,//定义是否从服务器对数据进行排序。
			striped:true,//是否显示斑马线
			//单击事件，单击“投资申请信息列表”中的某一行，将选中的某个投资申请记录的investOrderId，			
	 		onClickRow:function(rowIndex,rowData){
				rowDataSelected = rowData;				
			},
			columns:[[
						{field : 'investProcessType',title : '流程类型',width :80,align : 'center', 
							formatter: function(value, row, index){
								if(row.investProcessType == "1"){
									return "投资申请";
								}else if(row.investProcessType == "2"){
									return "投资赎回";
								}
							}
						},
				        {field : 'chName',title : '客户姓名',width :80,align : 'center'},
		                {field : 'createDate',title : '投资申请生成日期',width : 130,align : 'center'},	                
		                {field : 'contractNo',title : '合同编号',width :100,align : 'center'},
		                {field : 'bankName',title : '开户行名称',width :100,align : 'center'},
		                {field : 'actNo',title : '开户账号',width :100,align : 'center'},
		                {field : 'actName',title : '账户名称',width :100,align : 'center'},
		                {field : 'prodName',title : '理财产品',width :100,align : 'center'},
		                {field : 'investEdu',title : '投资金额',width :100,align : 'center'},
		                {field : 'beginDate',title : '投资开始日期',width :100,align : 'center'},
		                {field : 'interestDate',title : '计息日期',width :100,align : 'center'}		                
			   ]],
	 		   toolbar:[{
				   iconCls: 'icon-add',
				   text:'确定添加',
				   handler: toAddNewInvestRedeem
			   }]						
		});
	}
	
	//新增投资赎回申请（选择投资申请订单，并将其改变为投资赎回）
	//(1)修改该投资业务订单表(t_bp_invest_order)表中“INVEST_PROCESS_TYPE”字段，将其由“1(代表：投资申请流程)”修改为“2(代表：投资赎回流程)”
	//(2)修改该投资业务订单表(t_bp_invest_order)表中“PROCESS_STATUS”字段，将其修改为“0(代表：流程未开启)”
	function toAddNewInvestRedeem(){		
		var investOrderId = rowDataSelected.investOrderId;
		var investProcessType = "2";   //参数值2(代表：投资赎回流程)
		var processStatus = "0";       //流程状态编码说明:0－未开启，1－已开启，2－已结束

		/* updateInvestOrderProcessType(investOrderId, investProcessType); */	
		$.messager.confirm('确定', '是否确定赎回此笔投资？', function(flag) {
			if(flag){
				$.ajax({
 					url:"investRedeem/investRedeemAction!addNewInvestRedeem.action", 
					data : {
								"investOrderId" : investOrderId,            //投资订单
								"investProcessType" : investProcessType,    //投资流程类型，2代表投资赎回流程。
								"processStatus" :  processStatus            //流程状态
							},
					type : "POST",
					async : false,
					success : function(returnedData){
						if(returnedData.status == true){																					
							$("#addInvestRedeemDialog").dialog("close");																												
							//重新加载DataGrid。
							$("#investRedeemListDataGrid").datagrid("reload");							
						}else{
							parent.$.messager.alert(returnedData.title,returnedData.message,'error');
						}	
					}					
				});				
			}			
		});	
		
	}	
		
	//删除选中的投资赎回记录
	//注意：删除投资赎回记录并没有数据库底层t_bp_invest_order表中的数据InvestOrder信息删除，
	//而是修改该表中一些字段。
	//(1)“INVEST_PROCESS_TYPE”字段，将由原来的“2(投资赎回流程)”其设置为“1(投资申请流程)”
	//(2)“PROCESS_STATUS”字段，暂时修改为“2(已完成状态)”。（待定）
	function deleteInvestRedeemRecord(index){		
		var curRowData = getRowData4RedeemMainJsp(index);		
		var investOrderId = curRowData.investOrderId;	
		var investProcessType = "1";
		var processStatus = "2"; //流程状态编码说明:0－未开启，1－已开启，2－已结束
		$.messager.confirm('确定', '是否确定删除本笔投资赎回？', function(flag) {
			if(flag){
				$.ajax({
 					url:"investRedeem/investRedeemAction!deleteInvestRedeemRecord.action", 
					data : {
								"investOrderId" : investOrderId,             //投资订单
								"investProcessType" : investProcessType,     //投资流程类型，1代表投资申请流程。
								"processStatus" : processStatus              //流程状态编码
							},
					type : "POST",
					success : function(returnedData){
						if(returnedData.status == true){																												
							//重新加载DataGrid。
							$("#investRedeemListDataGrid").datagrid("reload");							
						}else{
							parent.$.messager.alert(returnedData.title,returnedData.message,'error');
						}	
					}					
				});				
			}			
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
		
	//查看投资人详细信息
	function showInvestorView(index){
		var rows = $("#investRedeemListDataGrid").datagrid("getRows");
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
		var row = this.getRowData4RedeemMainJsp(index);
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

	//查看审批意见
	function checkInvestOrderOpinions(index){
		var rows = $("#investRedeemListDataGrid").datagrid("getRows");
		row = rows[index];//获取本条数据
		$('#OpinionsDialog').dialog({    
		    title: '历史审查意见',    
		    width: 800,    
		    height: 500,    
		    closed: false,    
		    cache: false,    
		    href: 'jsp/investOrder/optionsList.jsp',    
		    modal: true   
		});   
	}
	
	//执行高级查询
	function doSearch(){
		var investorName = $("#searchForm input[name='investorName']").val();
		var contractNo = $("#searchForm input[name='contractNo']").val();
		
		$("#investRedeemListDataGrid").datagrid("load", {
			investorName : investorName,
			contractNo : contractNo
		});   //传入空的高级查询条件。
		
		//若用户欲通过高级查询条件去过滤数据，导出报表。
		exportExcelUrl = "investRedeem/investRedeemAction!exportExcel4RedeemInvestorDetails.action?investorName="+investorName+"&contractNo="+contractNo;
	}
	
	
	//清空高级查询的表单
	function clearAdvancedQueryForm(){
		$("#searchForm").form("clear");
		$("#investRedeemListDataGrid").datagrid("load",{});
	}

	//查看流程图
	function checkProcessImg(index){
		var rows = $("#investRedeemListDataGrid").datagrid("getRows");
		var rowm = rows[index];//获取本条数据
		var src = "investRedeem/investRedeemAction!checkWorkFlowImg4InvestRedeemByInvestOrderId.action?investOrderId=" + rowm.investOrderId;
		$('#imageDialog').dialog("open");
		$("#image").attr("src", src);
	}
	
	//提交投资赎回申请
	function submitInvestRedeem(index){
		var row = getRowData4RedeemMainJsp(index);		
		$.messager.confirm('确定','是否确定提交所选的数据吗？',	function(flag) {			
			$.ajax({
				url:"investRedeem/investRedeemAction!saveStartProcess4InvestRedeem.action",
				data : {"investOrderId" : row.investOrderId},
				type : "POST",
				success : function(returnedData){
					if(returnedData.status == true){
						//显示后台返回的正确与否的状态。
						parent.$.messager.show({
							title : returnedData.title,
							msg : returnedData.message,
							timeout : 1000 * 2
						});						
						//重新加载DataGrid。
						$("#investRedeemListDataGrid").datagrid("reload");
					}else{
						parent.$.messager.alert(returnedData.title,returnedData.message,'error');
					}	
				}
			});
		}); // End Of 是否确定提交所选的数据		
	} //提交投资赎回申请	    
	
	
</script>
<body>
<div class="position" style="margin-top: 5px;">您当前所在位置： 业务管理  &gt; 财富业务管理  &gt; 投资赎回管理</div>
   <div class="well well-small" style="margin-left: 5px;margin-top: 5px">
		<span class="badge">投资赎回信息高级查询</span>
		<p>
			<form id="searchForm" action="" method="post">
				<table cellpadding="0" cellspacing="1" border="0"">
					<tr>	
						<th>财富客户姓名：</th>						
						<td><input id="investorName" name="investorName" class="easyui-textbox" style="width:100px;" ></td>
						<th>合同编号：</th>						<!-- data-options="panelHeight:150" -->
						<td><input id="contractNo" name="contractNo" class="easyui-textbox" style="width:100px;"/></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="false" onclick="doSearch();">执行查询</a>&nbsp;&nbsp;
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="false" onclick="clearAdvancedQueryForm()">清空</a>
						</td>																								
					</tr>						
				</table>
			</form>			
		</p>				
   </div>

   <!-- 新增投资赎回申请DIV区域 -->
   <div id="addInvestRedeemDialog" class="easyui-dialog" style="width:900px;height:600px;" title="新增投资赎回申请" data-options="modal:true,resizable:true,iconCls:'icon-add',closed: true">
	    <div title="已经完成审批的投资申请信息列表">   
	       <table id="approvedInvestApplyListDataGrid" style="margin: 0px;padding: 0px;"></table>
	    </div>      
   </div>


   <!-- 投资赎回管理数据列表 -->
   <table id="investRedeemListDataGrid" style="margin: 0px;padding: 0px;overflow: auto;"></table>

	<!-- 投资客户信息对话框区域 -->
	<div id="investorView"></div>   
	
	<!-- 合同详情信息对话框区域 -->
	<div id="contractInfoDialog"></div>	
   
	<!-- 理财产品详情对话框区域 -->
	<div id="investorAndInvestProductsDialog"></div>   
   
	<!-- 流程图片弹框 -->
	<div id="imageDialog" class="easyui-dialog" title="流程图片"
			data-options="border:false,closed:true,fit:true">
			<img id="image" src="">
	</div>
      
	<!-- 审查意见 -->
	<div id="OpinionsDialog"></div>
	
</body>
</html>
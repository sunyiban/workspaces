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
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<jsp:include page="../../../../layout/script.jsp"></jsp:include>
	<style type="text/css">
		.dis{
			display: none;
		}
		a{
			text-decoration:none;
		}
	</style>
	<script type="text/javascript">
			//声明一个全局变量row
			//该变量可以在基于此Main页面上的弹出页面中使用，比如optionList.jsp页面。
			var $dg;
			var $grid;
			var $selRow;
			var $$row;
			var $dialog;
			var procStateJson = [{ 
				"id":"", 
				"text":"全部状态", 
				"selected":true 
				},{ 
				"id":1, 
				"text":"初始状态" 
				},{ 
				"id":2, 
				"text":"审批中" 
				},{ 
				"id":3, 
				"text":"已完成"
				},{ 
				"id":4, 
				"text":"已失效" 
				},{ 
				"id":5, 
				"text":"已撤销" 
				}] ;
		 	$(function() {
		 		
		 		$(window).resize(function(){  
		 	        $("#dg").datagrid("resize",{  
		 	        	height: $(window).height()-85
		 	        });                
		 	    });
		 		<%
			    String definitionKey=request.getParameter("key");
			    %>
				//加载固定资产报废申请的数据
				 $dg = $("#dg");
				 $grid=$dg.datagrid({
					url : "consumablesApp/findConsumablesAppTask.do?definitionKey=<%=definitionKey %>",
					width : 'auto',
					title: "低值易耗品申请",
					height : $(this).height()-85,
					pagination:true,
					rownumbers:true,
					border:true,
					singleSelect:false,
					nowrap:true,
					multiSort:false,
					border:false,
					fitColumns:true,
					columns : [ [
					             
								{field : 'ck',title:'ck',checkbox:true},
								{field : 'appDeptName',title : '申请部门',width : 100,align : 'center',
						        	 formatter: function(value,row,index){
						        		 return row.consumablesApp.appDeptName;
					      				}},
						        {field : 'appNo',title : '申请编号',width :120,align : 'center',
						        	 formatter: function(value,row,index){
						        		 return row.consumablesApp.appNo;
					      				}},
				                {field : 'account',title : '申请人',width : 100,align : 'center',
								        	 formatter: function(value,row,index){
								        		 return row.consumablesApp.account;
							      				}},
				                {field : 'appDate',title : '申请日期',width : 80,align : 'center',
												        	 formatter: function(value,row,index){
												        		 return row.consumablesApp.appDate;
											      				}},
				                /* {field : 'appStatus',title : '申请状态',width : 80,align : 'center'}, */
				                {field : 'procStatus',title : '流程状态',width : 80,align : 'center',formatter:function(value,row,index){
				                	if(row.consumablesApp.procStatus == "1"){
				                		return "初始状态";
				                	}else if(row.consumablesApp.procStatus == "2"){
				                		return "审批中";
				                	}else if(row.consumablesApp.procStatus == "3"){
				                		return "已完成";
				                	}else{
				                		return "已撤销";
				                	}
				                }},
				                {field : 'remark',title : '备注',width : 110,align : 'center',
						        	 formatter: function(value,row,index){
						        		 return row.consumablesApp.remark;
					      				}},
				                {field : 'goodsName',title : '用品名称',width : 80,align : 'center'},
				                {field : 'model',title : '规格型号',width : 80,align : 'center'},
				                /*{field : 'unit',title : '单位',width : 80,align : 'center'},
				                 {field : 'stock',title : '库存',width : 80,align : 'center'},
				                {field : 'price',title : '单价',width : 80,align : 'center'}, */
				                {field : 'qty',title : '数量',width : 80,align : 'center'},
				                {field : 'totalAmtStr',title : '合计金额',width : 80,align : 'center'},
				                {field : 'allAmtStr',title : '总金额',width : 80,align : 'center',
						        	 formatter: function(value,row,index){
						        		 return row.consumablesApp.allAmtStr;
					      				}},
					      		{field : 'heji',title : '合计(元)',width : 80,align : 'center'},
					          {field : 'caozuo',    title : '操作',    width : $(this).width * 0.1, align:'center',
					        	 	formatter: function(value,row,index){
					        		   	var result = ""; 
										if (row.consumablesApp.assistant == null || row.consumablesApp.assistant == "") {
											result += "<a href='javascript:void(0);' onclick='singForTask("+row.consumablesApp.taskID+");'>签收任务</a>　　";
										}else{
											result += "<a href='javascript:void(0);' onclick='saveTask("+index+");'>受理任务</a>　　";
										}
										result += "<a href='javascript:void(0);' onclick='checkHistoryOpinions("+ index +");'>查看审批意见</a>　　";
										result += "<a href='javascript:void(0);' onclick='showImage("+ index + ");'>查看流程图</a>";
										return result; 
				      				}
					          }
					              ] ],
		              toolbar:'#tb',
		              onClickCell:function(rowIndex, field, value){
		            	  $(this).datagrid("selectRow","rowIndex");
		              },onLoadSuccess:function(data){
		            	  var paramObj = {rowStr:'consumablesApp.appDeptName,consumablesApp.appNo',//按照哪列进行分组
			      					rowGroup:'appDeptName,heji:ck,appNo,allAmtStr,account,appDate,caozuo,procStatus' ,//对应rowStr的需要合并单元格的字段
			      					hejiColumn:'totalAmtStr', //需要计算的合计值的所在列
			      					hejiResult:'heji'};//计算后将合计值更新到该列中 
			      		  dataGirdSumMergeFunc($(this),paramObj);
		            	  
		                  $(this).datagrid("doCellTip",{'max-width':'100px'});
		              }
				});
				$('#procStatus').combobox({
					data:procStateJson,
					valueField:'id',
					textField:'text'
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
					if($.inArray(rows[i].consumablesApp.caId,businessID)==-1){
						businessID.push(rows[i].consumablesApp.caId);
						formKey=rows[i].consumablesApp.formKey;
						taskID.push(rows[i].consumablesApp.taskID);
						appNo.push(rows[i].consumablesApp.appNo);
					}
				}
				var data1 = "appNo="+appNo.join(",")+"&businessID="+businessID.join(",")+"&taskID="+taskID.join(",")+"&formKey="+formKey;
			 	  $.ajax({
					type: "POST",
					url:"consumablesApp/saveTaskConsumablesAppBatch.do",
					data:data1,
				    success: function(iJson) {
				    	$("#dg").datagrid("load",{});
				    	ajaxLoadEnd();
			 	    	$.messager.alert(iJson.title,iJson.message,'info');
				    }
				});  
			}
		 	
		 	
		 	
		//执行高级查询
		function doSearch(){			
			$("#dg").datagrid("load",{
				appDept:$("#appDept").val(),
				procStatus:$("#procStatus").combobox("getValue"),
				appDateBefore:$('#appDateBefore').datebox('getValue'),
				appDateAfter:$('#appDateAfter').datebox('getValue')
			}); 
		}

		//重置条件
		function clearAdvancedQueryConditions(){
			//1、清空高级查询各组件内容
			$("#searchForm").form("clear");
			//2、datagrid重新加载
			$("#dg").datagrid("load",{});
		}
		
		
		// 根据索引获取每一行的基本信息
		function getRowData (index) {
	        if (!$.isNumeric(index) || index < 0) { return undefined; }
	        var rows = $grid.datagrid("getRows");
	        return rows[index];
	    }

		function saveTask(index){
			var selectedRow = getRowData(index);
			$selRow = selectedRow.consumablesApp;
			$$row=$selRow;
			var formKey = selectedRow.consumablesApp.formKey;
			$dialog=$("<div></div>").dialog({
				/* 动态显示Dialog的标题	*/
				width : 800,
				height : 450,					
				title : '受理任务',
				href : formKey,
			    onLoad:function(){
			    	var saveOrUpdateForm = $("#taskForm");
			    	saveOrUpdateForm.form("load",$selRow);
			    	$("#taskForm #businessID").val(selectedRow.consumablesApp.caId);
			    	$("#taskForm #taskID").val(selectedRow.consumablesApp.taskID);
			    },
				modal:true,
				resizable:true,
				iconCls:'icon-add',
				closed: false,
				buttons : [{
					text : '关闭',
					iconCls : 'icon-cancel',
					handler : function() {
						$dialog.dialog('close');
						$dialog.dialog('destroy');
				}
			}],onClose:function(){
		    	//刷新列表
		    	$("#dg").datagrid("reload");
		    	//关闭弹窗
		    	$(this).dialog('destroy');
		    }
			});
		}
		
		//签收任务
		function singForTask(taskID){
			$.messager.confirm('签收任务', '是否确认签收任务?', function(d) {
	    		if (d) {
	    			$.ajax( {
	    				type : "POST",
	    				url : 'consumablesApp/signForTask.do',
	    				data : "taskID="+taskID,
	    				dataType:'JSON',
	    				success : function(iJson) {		    					    				
	    					if(iJson.status){
	    						//刷新列表		    						
	    						$("#dg").datagrid("load",{});
	    					}
	    					$.messager.alert(iJson.title,iJson.message,'info');
	    				}
		    			});
	    		}
	    	});		
		}		
		
		//查看历史审批意见
		function checkHistoryOpinions(index){
			$$row = getRowData(index).consumablesApp;
			var $optionsWindow = $("<div></div>").dialog({
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
		        },
		        buttons : [ {
					text : '关闭',
					iconCls : 'icon-cancel',
					handler : function() {
						$optionsWindow.dialog('close');
					}
				}]
			});
		}

		//查看流程图
		function showImage(index){
			var row = getRowData(index);
			var src = "consumablesApp/showProcessImg.do?caId="+row.consumablesApp.caId;
			$('#imageDialog').dialog("open");
			$("#image").attr("src", src);
		}
	</script>
  </head>
  <body>
      <div data-options="region:'center',border : false">
      	<div class="position" style="margin-top: 5px;">您当前所在位置： 任务管理-固定资产报废申请任务列表</div>
		<!-- 高级查询栏区域 -->
  		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<form id="searchForm" action="consumablesApp/findConsumablesAppTask.do" method="post" style="min-width: 1200px;">
				<table cellpadding="0" cellspacing="1" border="0">
					<tr>
<!-- 						<td>所属部门：&nbsp;&nbsp;</td>
						<td><input name="appDept" id="appDept" type="text" class="easyui-textbox easyui-validatebox"  style="width: 170px"/>&nbsp;&nbsp;&nbsp;&nbsp;</td> -->
						<td>申请状态：&nbsp;&nbsp;</td>
						<td><select id="procStatus" class="easyui-combobox" name="procStatus"  style="width: 170px;"></select>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>申请日期：&nbsp;&nbsp;</td>
						<td><input name="appDateBefore" id="appDateBefore" class="easyui-datebox" editable="true" style="width:174px;" value=""  title="开始日期" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;</td>
						<td><input name="appDateAfter" id="appDateAfter" class="easyui-datebox" editable="true" style="width:174px;" value="" title="结束日期"/></td>
						<td width="70px"></td>
						<td colspan="4" align="right">
						    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="false" onclick="doSearch();">执行查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
						    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="false" onclick="clearAdvancedQueryConditions()">条件重置</a>
						</td>	
					</tr>	
				</table>
			</form>			  			
		</div>
		<!-- 理财业绩数据表格区域 -->
		<table id="dg"  width="100%"></table>
		<div id="tb" style="padding:2px 0">
			<a id="id4ExportReports" class="easyui-linkbutton" data-options="iconCls:'icon-add'" plain="true" onclick="signTask();">批量受理任务</a>
		</div>
		<div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
			<img id="image" src="" >
		</div>
		<div id="optionsDialog"></div>
		<!-- 增加或修改理财对话框区域 -->
		<div id="saveOrUpdateInvestProductDialog"></div>	
  	</div>	
  </body>
</html>

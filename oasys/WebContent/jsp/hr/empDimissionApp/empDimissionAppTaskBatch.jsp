<%@page import="com.oasys.util.Constants"%>
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
	<jsp:include page="../../../layout/script.jsp"></jsp:include>
	<style type="text/css">
		a{
			text-decoration:none;
		}
	</style>
	<script type="text/javascript">
			//声明一个全局变量row
			//该变量可以在基于此Main页面上的弹出页面中使用，比如optionList.jsp页面。
			var $$row
			var $dg;
			var $dialog;
			var $grid;
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
				},{ 
				"id":6, 
				"text":"已拒绝" 
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
					url : "empDimissionAppController/findEmpDimissionAppTask.do?definitionKey=<%=definitionKey %>",
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
							  {field : 'edaId',        title : '编号',    width : $(this).width * 0.1, align:'center',
							       	 formatter: function(value,row,index){
							    		 return row.empDimissionApp.edaId;
											}},
								 {field : 'taskID',        title : 'TASK_ID',    width : $(this).width * 0.1, align:'center',
						        	 formatter: function(value,row,index){
						        		 return row.empDimissionApp.taskID;
					      				}},
							 	 {field : 'formKey',        title : 'FORM_KEY',    width : 60, align:'center' ,
									        	 formatter: function(value,row,index){
									        		 return row.empDimissionApp.formKey;
								      				}},
			      				{field : 'appNo',title : '申请编号',width :120,align : 'center',
						        	 formatter: function(value,row,index){
						        		 return row.empDimissionApp.appNo;
					      				}},
			      				{field : 'appDate',title : '申请日期',width : 100,align : 'center',
						        	 formatter: function(value,row,index){
						        		 return row.empDimissionApp.appDate;
					      				}},
				                {field : 'account',title : '申请人',width : 120,align : 'center',
								        	 formatter: function(value,row,index){
								        		 return row.empDimissionApp.account;
							      				}},
					            {field : 'deptName',title : '申请部门',width : 120,align : 'center',
										        	 formatter: function(value,row,index){
										        		 return row.empDimissionApp.deptName;
									      				}},
			      				{field : 'mobile',title : '电话',width : 120,align : 'center',
						        	 formatter: function(value,row,index){
						        		 return row.empDimissionApp.mobile;
					      				}},
					      				{field : 'content',title : '交接内容',width : 80,rowspan:2,align : 'center',formatter:function(value,row,index){
						                	if(row.takeoverGoods == "1"){
						                		return "资料书籍";
						                	}else if(row.takeoverGoods == "2"){
						                		return "设备";
						                	}else if(row.takeoverGoods == "3"){
						                		return "办公用品";
						                	}else if(row.takeoverGoods == "4"){
						                		return "财务状况";
						                	}else if(row.takeoverGoods == "5"){
						                		return "考勤工资";
						                	}else if(row.takeoverGoods == "6"){
						                		return "工装扣款";
						                	}else if(row.takeoverGoods == "7"){
						                		return "社保情况";
						                	}else{
						                		return "其它";
						                	}
						                }},
			      				{field : 'receiverName',        title : '接管人',    width : 120, align:'center' },
			    				{field : 'receiveDate',      title : '接管日期', width : 120, align:'center'},
				                {field : 'procStatus',title : '流程状态',width : 80,align : 'center',formatter:function(value,row,index){
				                	if(row.empDimissionApp.procStatus == "1"){
				                		return "初始状态";
				                	}else if(row.empDimissionApp.procStatus == "2"){
				                		return "审批中";
				                	}else if(row.empDimissionApp.procStatus == "3"){
				                		return "已完成";
				                	}else if(row.empDimissionApp.procStatus == "4"){
				                		return "已失效";
				                	}else if(row.empDimissionApp.procStatus == "5"){
				                		return "已撤销";
				                	}else{
				                		return "已拒绝";
				                	}
				                }},
				                {field : 'remark',        title : '备注',    width : 120, align:'center' ,
						        	 formatter: function(value,row,index){
						        		 return row.empDimissionApp.remark;
					      				}},
			      				{field : 'aa',    title : '操作',    width : $(this).width * 0.1, align:'center',
					        	 	formatter: function(value,row,index){
					        		   	var result = ""; 
										if (row.empDimissionApp.assistant == null || row.empDimissionApp.assistant == "") {
											result += "<a href='javascript:void(0);' onclick='singForTask("+row.empDimissionApp.taskID+");'>签收任务</a>　　";
										}else{
											result += "<a href='javascript:void(0);' onclick='saveTask("+index+");'>办理任务</a>　　";
										}
										result += "<a href='javascript:void(0);' onclick='checkInvestOrderOpinions("+ index +");'>查看审批意见</a>　　";
										result += "<a href='javascript:void(0);' onclick='showImage("+ index + ");'>查看流程图</a>";
										return result; 
				      				}
					          }
				              ] ],
		              toolbar:'#tb',
		              onClickCell:function(rowIndex, field, value){
		            	  $(this).datagrid("selectRow","rowIndex");
		              },onLoadSuccess:function(data){
		            	  var rows = data.rows;
		                  var mergeMap = {};
		                  if(rows){
		                  	for(var i=0;i<rows.length;i++){
		                  		var appNo = rows[i].appNo
		                  		if( appNo in mergeMap ){
		                  			mergeMap[appNo].rowspan++;
		                  		}else{
		                  			mergeMap[appNo]={"index":i,"rowspan":1}
		                  		}
		                  	}
		                  }
		                  for(var i in mergeMap){
		                	  $(this).datagrid('mergeCells',{
		                          index: mergeMap[i].index,
		                          field: 'appNo',
		                          rowspan: mergeMap[i].rowspan
		                      });
		                  	$(this).datagrid('mergeCells',{
		                          index: mergeMap[i].index,
		                          field: 'account',
		                          rowspan: mergeMap[i].rowspan
		                      });
		                      $(this).datagrid('mergeCells',{
		                          index: mergeMap[i].index,
		                          field: 'deptName',
		                          rowspan: mergeMap[i].rowspan
		                      });
		                      $(this).datagrid('mergeCells',{
		                          index: mergeMap[i].index,
		                          field: 'mobile',
		                          rowspan: mergeMap[i].rowspan
		                      });
		                      $(this).datagrid('mergeCells',{
		                          index: mergeMap[i].index,
		                          field: 'appDate',
		                          rowspan: mergeMap[i].rowspan
		                      });
		                      $(this).datagrid('mergeCells',{
		                          index: mergeMap[i].index,
		                          field: 'aa',
		                          rowspan: mergeMap[i].rowspan
		                      });
		                      $(this).datagrid('mergeCells',{
		                          index: mergeMap[i].index,
		                          field: 'remark',
		                          rowspan: mergeMap[i].rowspan
		                      });
		                      $(this).datagrid('mergeCells',{
		                          index: mergeMap[i].index,
		                          field: 'procStatus',
		                          rowspan: mergeMap[i].rowspan
		                      });
		                  }
		                  $(this).datagrid("doCellTip",{'max-width':'100px'});
		              }
				});
				$('#dg').datagrid('hideColumn', 'edaId');
				$('#dg').datagrid('hideColumn','taskID');
 				$('#dg').datagrid('hideColumn','formKey');
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
					if($.inArray(rows[i].empDimissionApp.edaId,businessID)==-1){
						businessID.push(rows[i].empDimissionApp.edaId);
						formKey=rows[i].empDimissionApp.formKey;
						taskID.push(rows[i].empDimissionApp.taskID);
						appNo.push(rows[i].empDimissionApp.appNo);
					}
				}
				var data1 = "appNo="+appNo.join(",")+"&businessID="+businessID.join(",")+"&taskID="+taskID.join(",")+"&formKey="+formKey;
			 	  $.ajax({
					type: "POST",
					url:"empDimissionAppController/saveTaskEmpDimissionAppBatch.do",
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
			$selRow = selectedRow.empDimissionApp;
			$$row = $selRow;
			var formKey = selectedRow.empDimissionApp.formKey;
			$dialog=$("<div></div>").dialog({
				/* 动态显示Dialog的标题	*/
				width : 850,
				height : 450,					
				title : '受理任务',
				href : formKey,
			    onLoad:function(){
			    	var saveOrUpdateForm = $("#taskForm");
			    	saveOrUpdateForm.form("load",$selRow);
			    	$("#taskForm #businessID").val(selectedRow.empDimissionApp.edaId);
			    	$("#taskForm #taskID").val(selectedRow.empDimissionApp.taskID);
			    },
			    modal:true,
				resizable:false,
				iconCls:'icon-add',
				closed: false,
			    buttons : [{
						text : '关闭',
						iconCls : 'icon-cancel',
						handler : function() {
							$dialog.dialog('close');
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
	    				url : 'empDimissionAppController/signForTask.do',
	    				data : "taskID="+taskID,
	    				dataType:'JSON',
	    				success : function(iJson) {		    					    				
	    					if(iJson.status){
	    						//刷新列表		    						
	    						$("#dg").datagrid("load",{});
	    					}
	    					parent.$.messager.alert(iJson.title,iJson.message,'info');
	    				}
		    			});
	    		}
	    	});		
		}	
		
		//查看审批意见
		function checkInvestOrderOpinions(index){
			$$row = getRowData(index).empDimissionApp;
			$("#optionsDialog").dialog({
				/* 动态显示Dialog的标题	*/
				width : 850,
				height : 450,					
				title : "查看审批意见",
				href : "jsp/pd/optionsList.jsp",
				onClose:function(){
					$$row=null;
				},
				modal:true,
				resizable:true,
				iconCls:'icon-add',
				closed: false
			});
		}
		
		//查看流程图
		function showImage(index){
			var row = getRowData(index);
			var src = "empDimissionAppController/showProcessImg.do?edaId="+row.empDimissionApp.edaId;
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
			<form id="searchForm" action="empDimissionAppController/findEmpDimissionAppTask" method="post" style="min-width: 1200px;">
				<table cellpadding="0" cellspacing="1" border="0">
					<tr>
<!-- 						<td>所属部门：&nbsp;&nbsp;</td>
						<td><input name="appDept" id="appDept" type="text" class="easyui-textbox easyui-validatebox"  style="width: 170px"/>&nbsp;&nbsp;&nbsp;&nbsp;</td> -->
						<td>申请状态：&nbsp;&nbsp;</td>
						<td><select id="procStatus" class="easyui-combobox" editable="false" name="procStatus"  style="width: 170px;"></select>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>申请日期：&nbsp;&nbsp;</td>
						<td><input name="appDateBefore" id="appDateBefore" class="easyui-datebox" editable="false" style="width:174px;" value=""  title="开始日期" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;</td>
						<td><input name="appDateAfter" id="appDateAfter" class="easyui-datebox" editable="false" style="width:174px;" value="" title="结束日期"/></td>
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
		<!-- 增加或修改理财对话框区域 -->
		<div id="saveOrUpdateInvestProductDialog"></div>
		<div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
			<img id="image" src="" >
		</div>	
		<div id="optionsDialog"></div>
  	</div>	
  </body>
</html>

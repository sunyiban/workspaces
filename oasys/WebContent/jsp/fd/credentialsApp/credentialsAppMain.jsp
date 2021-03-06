<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
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
<title>证章申请</title>
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

	   $(function() {
		$("#procStatus").combobox({
			valueField: 'value',   
	        textField: 'label',   
	        data: [{
				label: '全部',
				value: ''
			},{
				label: '初始化',
				value: '1'
			},{
				label: '审批中',
				value: '2'
			},{
				label: '已完成',
				value: '3'
			},{
				label: '已失效',
				value: '4'
			},{
				label: '已撤销',
				value: '5'
			},{
				label: '已拒绝',
				value: '6'
			}],
			editable:false ,
			onLoadSuccess : function(){
			userData = $(this).combobox("getData");
			for (var item in userData[0]) {
	                if (item == "value") {
	                    $(this).combobox("select", userData[0][item]);
	                }
	            }
			}
		});	
	  }); 

  	//申请列表
  	var $grid;
  	var $row;
	$(function() {
		$(window).resize(function(){  
            $("#travelExpensesAppgrid").datagrid("resize",{  
            	height: $(window).height()-126,
            	width : 'auto'
            });                
        });
		
		$grid =$("#travelExpensesAppgrid").datagrid({
					url : 'credentials/index.do',
					width: 'auto',
					height : $(window).height()-108,
					pagination:true,
					rownumbers:true,
					border:false,
					singleSelect:true,
					nowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
					pageSize:30,
					pageList:[10,20,30,40],
					remoteSort:false,//定义是否从服务器对数据进行排序。
					striped:true,//是否显示斑马线
					columns : [ [
					        {field : 'appNo',title : '申请编号',width:parseInt($(this).width()*0.06),align : 'center'},
							{field : 'applicantName',title : '申请人姓名',width:parseInt($(this).width()*0.06),align : 'center'},
					        {field : 'deptName',title : '所属部门',width:parseInt($(this).width()*0.06),align : 'center'},
							{field : 'appDate',title : '申请日期',width:parseInt($(this).width()*0.06),align : 'center'},   
							{field : 'csTypeName',title : '证章类型',width : parseInt($(this).width()*0.06),align : 'center'},
							{field : 'csDesc',title : '证章描述',width : parseInt($(this).width()*0.06),align : 'center'},
							{field : 'isOriginalName',title : '是否原件',width : parseInt($(this).width()*0.06),align : 'center'},
							{field : 'planUseDate',title : '计划使用日期',width : parseInt($(this).width()*0.08),align : 'center'},
							{field : 'realUseDateStr',title : '实际使用日期',width : parseInt($(this).width()*0.08),align : 'center'},
							{field : 'csPurpose',title : '证章用途',width :parseInt($(this).width()*0.08),align : 'center'},
							{field : 'isLetOutName',title : '是否外借',width : parseInt($(this).width()*0.08),align : 'center'},
							{field : 'planRestDate',title : '计划归还日期',width : parseInt($(this).width()*0.08),align : 'center'},
							{field : 'realRestDateStr',title : '实际归还日期',width : parseInt($(this).width()*0.08),align : 'center'},
							{field : 'procStatus',title : '申请状态',width :parseInt($(this).width()*0.08),align : 'center'},
							{field : 'remark',title : '备注',width : parseInt($(this).width()*0.1),align : 'center'},
							{field : 'caozuo',title : '操作',width:parseInt($(this).width()*0.2),align : 'center',
								 formatter: function(value,row,index){
					        		  var result='';
					        		  if(row.procStatus=="初始状态"){
					        			  result="<a href='javascript:void(0);'  onclick='saveOrUpdOpenDlg("+index+");'>编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;";				            			  
			            			  	  result+="<a href='javascript:void(0);' onclick='delRows(\""+index+"\");'>删除</a>&nbsp;&nbsp;&nbsp;&nbsp;";
			            			  	  result+="<a href='javascript:void(0);' id='saveApply' onclick='sumitCredentialsApp(\""+index+"\");'>提交申请</a>&nbsp;&nbsp;&nbsp;&nbsp;";
					        		  }else{
					        			  result+="<a href='javascript:void(0);' id='queryApplyView' onclick='checkHistoryOpinions("+index+");'>查看审批意见</a>&nbsp;&nbsp;&nbsp;&nbsp;";
					        			  if(row.procStatus=="审批中"){
					        				  result+="<a href='javascript:void(0);' id='queryApplyFlow' onclick='showImage("+index+");'>查看申请流程</a>&nbsp;&nbsp;&nbsp;&nbsp;";
					        			  }
					        		  }
		      						  return result;					            		  					           		  
				      				}
					          }
							] ],
							 onLoadSuccess:function(data){
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
				                          field: 'applicantName',
				                          rowspan: mergeMap[i].rowspan
				                      });
				                      $(this).datagrid('mergeCells',{
				                          index: mergeMap[i].index,
				                          field: 'deptName',
				                          rowspan: mergeMap[i].rowspan
				                      });
				                      $(this).datagrid('mergeCells',{
				                          index: mergeMap[i].index,
				                          field: 'appDate',
				                          rowspan: mergeMap[i].rowspan
				                      });
				                      $(this).datagrid('mergeCells',{
				                          index: mergeMap[i].index,
				                          field: 'remark',
				                          rowspan: mergeMap[i].rowspan
				                      });
				                      $(this).datagrid('mergeCells',{
				                          index: mergeMap[i].index,
				                          field: 'caozuo',
				                          rowspan: mergeMap[i].rowspan
				                      });
				                  }
// 								 	//当鼠标放上该字段时，提示功能
						            $("#travelExpensesAppgrid").datagrid("doCellTip",{'max-width':'100px'});
						           
							  }, 
					toolbar : '#tb'
				});
	});
	
	
	
  
	//执行查询
	function doSearch(){
		$("#travelExpensesAppgrid").datagrid("load",{
			procStatus:$("#procStatus").combobox("getValue"),
			queryAppDateBegin:$("#queryAppDateBegin").datebox('getValue'),
			queryAppDateEnd:$('#queryAppDateEnd').datebox('getValue'),
			queryUseDateBegin:$('#queryUseDateBegin').datebox('getValue'),
			queryUseDateEnd:$('#queryUseDateEnd').datebox('getValue')
		});  
	}
	//重置条件
	function clearAdvancedQueryConditions(){
		//1、清空高级查询各组件内容
		$("#searchForm").form("clear");
		//2、datagrid重新加载
		$("#dg").datagrid("load",{});
	}
	/* 动态显示弹出的Dialog的标题,显示"添加"或者"修改"*/
	function changeMyTitle(index){
		if(null == index){
			return '新增证章申请';
		}else{
			return '编辑证章申请';
		}
	}
	function changeMyicon(index){
		if(null == index ){
			return 'icon-add';
		}
			return 'icon-edit';
		
	}
	
	//弹窗修改
	function saveOrUpdOpenDlg(index) {
		 $row = this.getRowData($grid,index);
		 $saveOrUpdbanliDialog = $("#saveDialog").dialog({
				title:changeMyTitle(index),
				iconCls: changeMyicon(index),
				width:850,
				height: $(this).height()*0.7,
				modal:true,
				href:"jsp/fd/credentialsApp/credentialsAppAdd.jsp",
				onLoad:function(){
					if(index!=null){
						$("#remark").val(row.remark);
						/*var f = $("#investProductInputOrSaveForm");
						var checkStr=null;
						if(row.csType==1){
							checkStr="CaiWuZhang";
						}else if(row.csType==2){
							checkStr="FaRenZhang";
						}else if(row.csType==3){
							checkStr="FaRenShenFenZheng";
						}else if(row.csType==4){
							checkStr="ZiZhi";
						}else if(row.csType==5){
							checkStr="HeTong";
						}else if(row.csType==6){
							checkStr="QiTa";
						}
						$("#"+checkStr).attr("checked",true);
						removeDisable();
						$("#useDesc"+checkStr).val(row.csPurpose);//根据不同的证章对应不同的使用原因
						$("#"+checkStr+"Name").val(row.csDesc);//证章说明
						$("#isLetOut"+checkStr).combobox("setText",row.isLetOut==0?"是":"否");//是否借出
						$("#isLetOut"+checkStr).combobox("setValue",row.isLetOut);//是否借出
						$("#isOriginal"+checkStr).combobox("setText",row.isOriginal==0?"是":"否");//是否原件
						$("#isOriginal"+checkStr).combobox("setValue",row.isOriginal);//是否原件
						f.form("load", row);*/
					}
				} 
		 }); 	
	}
	
	 // 查看流程批注
	 var $$row = "";
	function lookLeaveAppCommentDialog(index) {
		var rows = $("#travelExpensesAppgrid").datagrid("getRows");
		 $$row = rows[index];//获取本条数据
		$("#optionsDialog").dialog('open').dialog('refresh');
	} 
  
	// 提交申请
	function sumitCredentialsApp(index){
		var row = this.getRowData($grid,index);
		$.messager.confirm('确定','是否确定提交所选的数据吗？',	function(flag) {
			if (flag) {
				$.ajax({
						url : "CredentialsApp/saveCredentialsApply.do",
						data : {"uqaId" : row.uqaId},
						success : function(iJson) {
							if(iJson.status){
// 								subCustomerRepaymentForm();
 	    						$("#travelExpensesAppgrid").datagrid("load",{}); 
	    						$.messager.alert(iJson.title,iJson.message,'info');  	
	    					}else{
	    						$.messager.alert('提示','请重新填写申请！','error');  	
	    					}
						}
					});
				}
			});
	}
	
	/**
	 * 根据索引获取对象名称
	 * @param target Grid对象
	 * @param index 索引
	 * @returns 索引的对应行的信息
	 */
	function getRowData (target,index) {
		if (!$.isNumeric(index) || index < 0) { return undefined; }
		if ($.isEmptyObject(target)) { return undefined; }
	    var rows = target.datagrid("getRows");
	    return rows[index];
	}
	
	//删除请求
	function delRows(index) {
		var row = getRowData($grid,index);
		if (row) {
			$.messager.confirm('提示','是否确定删除所选的数据吗？',function(flag) {
				if (flag) {
					$.ajax({
						url : "credentials/delCredentials.do",
						data : {"id":row.uqaId} ,
						dataType : 'JSON',
						success : function(rsp) {
							if (rsp.status) {
								//删除成功后刷新列表
								$grid.datagrid('reload');
							}
							$.messager.alert(rsp.title, rsp.message,'info');
						}
					});
				}
			});
		}
	}
	
	// 查看流程图片
	function showImage(index) {
		var row = this.getRowData($grid,index);
		var src = "Credentials/showProcessImg.do?uqaId="+ row.uqaId;
		$('#imageDialog').dialog("open");
		$("#image").attr("src", src);
	}
	
	//查看附件
	function lookCredentialsAttment(index){
		//主页面查看时，明细为申请id
		var row = this.getRowData($grid,index);
		checkAttachementDetail(row.appNo,row.applicantNo,'');
	};
	//查看历史审批意见
	function checkHistoryOpinions(index){
		$$row = getRowData($grid,index);
		$dialog=$("<div></div>").dialog({
		/* 动态显示Dialog的标题	*/
		width : 800,
		height : 450,					
		title : "查看审批意见",
		href : "jsp/ad/optionsList.jsp",
		onClose:function(){
			$$row=null;
	    	$(this).dialog('destroy');
		},
		modal:true,
		resizable:false,
		iconCls:'icon-add',
		closed: false
	});
	}
</script>
</head>
<body>
		<div style="margin-left: 5px;margin-top: 5px">
			<div class="position" style="margin-top: 5px;">您当前所在位置：财务管理  &gt; 证章申请</div>
		</div>
		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
			<form id="searchForm" action="credentialsApp/index.do" method="post">
				<table cellpadding="0" cellspacing="1" border="0">
					<tr>
						<td>申请状态：&nbsp;&nbsp;</td>
						<td><input name="procStatus" id="procStatus" type="text" class="easyui-textbox easyui-validatebox" style="width: 170px" readonly="readonly"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>申请时间：&nbsp;&nbsp;</td>
						<td><input name="queryAppDateBegin" id="queryAppDateBegin" class="easyui-datebox" editable="false" style="width:174px;"/></td>
						<td>至</td>
						<td><input name="queryAppDateEnd" id="queryAppDateEnd" class="easyui-datebox" editable="false" style="width:174px;"/></td>	
						<td align="right" style="float:right;text-align: right;">
						    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="false" onclick="doSearch();">执行查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td>  
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="false" onclick="clearAdvancedQueryConditions()">条件重置</a>
						</td>
					</tr>
					<tr>
						<td>实际使用日期：&nbsp;&nbsp;</td>
						<td><input name="queryUseDateBegin" id="queryUseDateBegin" class="easyui-datebox" editable="false" style="width:174px;"/></td>
						<td align="center">至&nbsp;&nbsp;</td>
						<td><input name="queryUseDateEnd" id="queryUseDateEnd" class="easyui-datebox" editable="false" style="width:174px;"/></td>
					</tr>									
				</table>
			</form>			  			
		</div>
			
	
			<div id="tb" style="padding: 2px 0">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td style="padding-left: 2px">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="saveOrUpdOpenDlg();">添加</a></td>
				</tr>
			</table>
		</div>
		
		<table id="travelExpensesAppgrid" title="证章申请"></table>
		<!-- 展示客户信息详情 -->
		<div id="applyView"></div>
		<div id="attachmentList">
			<table id="lookAttachmentList" title="申请附件的信息"></table>
		</div>
		<div id="saveOrUpdateInvestProductDialog"></div>
		<div id="imageDialog" class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
			<img id="image" src="">
		</div>
		<!-- 意见 -->
		<div id="optionsDialog" class="easyui-dialog" title="历史审批意见" style="width:900px;height:500px;" closed="true" data-options="href:'jsp/ad/optionsList.jsp',resizable:true,modal:true">
		<div id="saveDialog"></div>
</body>
</html>

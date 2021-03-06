<%@page import="com.oasys.model.OvertimeApp"%>
<%@page import="com.oasys.util.Constants"%>
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
<title>待办任务主页面</title>
</head>
<script type="text/javascript">
var $$row;
var $dialog1;
var $dialog;
$(function(){
	$(window).resize(function(){  
        $("#waitTaskGrid").datagrid("resize",{  
        	height: $(window).height()-40,
        	width : 'auto'
        });                
    });
	createWaitTaskGrid();
});

//上传附件,明细存储taskId
$("#upploadAttachment").click(function(){
	fileUploadsDlg($row.appNo);
});
//查看附件
$("#checkAttachment").click(function(){
	checkAttachementDetail($row.appNo,$row.taskModel.assistant,'');
});

function lookAttachment(index){
	var row=getRowData(index)
	checkAttachementDetail($row.appNo,row.handler,'');
}

//渲染grid
function createWaitTaskGrid(){
	<%String definitionKey=request.getParameter("key");%>//流程变量
	$("#waitTaskGrid").datagrid({
		url:'overtie/QueryOvertimeTask.do?processKey=<%=definitionKey%>',
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
			{field : 'appNo',        title : '申请编号',    width : parseInt($(this).width()*0.1), align:'center' },
		    {field : 'applicantNo',        title : '姓名',    width :parseInt($(this).width()*0.05), align:'center' },
			{field : 'position',    title : '职位',    width :parseInt($(this).width()*0.06), align:'center' },
			{field : 'deptName',    title : '部门名称',    width :parseInt($(this).width()*0.06), align:'center' },
		    {field : 'appDate',    title : '申请日期',    width :parseInt($(this).width()*0.06), align:'center' },
			{field : 'planBgDtime',    title : '计划开始日期时间',    width :parseInt($(this).width()*0.12), align:'center' },
			{field : 'planEdDtime',    title : '计划结束日期时间',    width :parseInt($(this).width()*0.12), align:'center' },
			{field : 'planOtCnt',    title : '计划加班合计(小时)',    width :parseInt($(this).width()*0.08), align:'center' },
			{field : 'realBgDtime',    title : '实际开始日期时间',    width :parseInt($(this).width()*0.12), align:'center' },
			{field : 'realEdDtime',    title : '实际结束日期时间',    width :parseInt($(this).width()*0.12), align:'center' },
			{field : 'realOtCnt',    title : '实际加班合计(小时)',    width :parseInt($(this).width()*0.08), align:'center' },
			{field : 'remark',    title : '备注信息',    width :parseInt($(this).width()*0.08), align:'center' },
		    {field : 'caozuo',    title : '操作',    width : $(this).width * 0.1, align:'center',
				formatter: function(value,row,index){
					var result = "";
				    if (row.taskModel.assistant == null || row.taskModel.assistant == "") {
						result += "<a href='javascript:void(0);' onclick='holdWorkTask("+index+");'>签收任务</a>　　";
					}else{
						result += "<a href='javascript:void(0);' onclick='handleTaskDialog("+index+");'>办理任务</a>　　";
					}
				    result += "<a href='javascript:void(0);' onclick='checkHistoryOpinions("+ index +");'>查看审批意见</a>　　";
				    result += "<a href='javascript:void(0);' onclick='showImage("+index+");'>查看流程图</a>　　";
					return result;        		             		  					           		  
				}
			}
	   ]],
	   onLoadSuccess:function(data){
		   var rows = data.rows;
           var mergeMap = {};
           if(rows){
           	for(var i=0;i<rows.length;i++){
           		var appNo = rows[i].appNo;
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
               field: 'sumAppQty',
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
           $(this).datagrid("doCellTip",{'max-width':'100px'});
	  }
	});
}
//根据index获取该行
function getRowData(index){
	if (!$.isNumeric(index) || index < 0) {
		return undefined;
	}
	var rows = $("#waitTaskGrid").datagrid("getRows");
	return rows[index];
}
//查看流程图
function showImage(index){
	var row = getRowData(index);
	var src="workflowAction/showProcessImgByBusinessKey.do?processKey="+row.definitionKey+"&resName="+row.resourcesName+"&busID="+row.oveId;
	$('#imageDialog').dialog("open");
	$("#image").attr("src", src);
}
//签收任务
function holdWorkTask(index){
	var row = getRowData(index);
	$.ajax({
		type:"POST",
		url:"callingCard/SignTask.do",
		data:{"taskId" : row.taskId},
		dataType:"JSON",
		success:function(rsp){
			if(rsp.status){
				$.messager.alert("提示","签收成功","warning");
			}else{
				parent.$.messager.alert(rsp.title,rsp.message,'warning');
			}
			$("#waitTaskGrid").datagrid('reload');
		}
	});
}
<%-- //查看流程图
	function showImage(index){
		var selectedRow = getRowData(index);
		//获取taskID
		$.ajax( {
		type : "POST",
		url : 'workflowAction/getTaskIDByBusID.do',
		data : "busID="+selectedRow.oveId+"&definitionKey=OvertimeApp",
		dataType:'JSON',
		success : function(iJson) {		  
	 		var src = "workflowAction/showProcessImg.do?taskId="+iJson+"&imgName="+<%=Constants.OVERTIMEAPP %>;
	 		$('#imageDialog').dialog("open");
	 		$("#image").attr("src", src);
		}
	});
	} --%>
//办理任务
function handleTaskDialog(index){
	var selectedRow = getRowData(index);
	$selRow = selectedRow;
	$$row = $selRow;
	$dialog1=$("<div></div>").dialog({
		width : 920,
		height : $(this).height()*0.7,					
		title : '受理任务',
		href :$selRow.formKey,
	    onLoad:function(){
	    	var taskForm = $("#taskForm");
	    	taskForm.form("load",selectedRow);
	    	$("#taskForm #businessID").val(selectedRow.oveId);
	    	$("#taskForm #taskID").val(selectedRow.taskId);
	    },
		modal:true,
		resizable:false,
		closed: false,
		iconCls:'icon-add',
	    buttons : [{
			text : '关闭',
			iconCls : 'icon-cancel',
			handler : function() {
				$dialog1.dialog('close');
		}
		}],onClose:function(){
	    	//刷新列表
	    	$("#dg").datagrid("reload");
	    	//关闭弹窗
	    	$(this).dialog('destroy');
	    }
	}); 
// 	$.ajax({
// 		type:"POST",
// 		url:"workflowAction/findTaskFormKeyByTaskId.do",
// 		data:{"taskId":$row.taskId},
// 		onClose:function(){
// 			$("#waitTaskGrid").datagrid('reload');
// 		},
// 		success:function(jspName){
// 			 $("#addWindow").dialog({
// 					title:'办理任务',
// 					width:850,
// 					height:400 ,
// 					modal:true,
// 					href:jspName
// 			 }); 
// 		}
// 	});
}
// //查看流程图
// function showImage(index){
// 	var row = getRowData(index);
// 	var src = "workflowAction/showProcessImg.do?taskId="+row.taskId+"&imgName=OA_AD_purchase_BO";
// 	$('#imageDialog').dialog("open");
// 	$("#image").attr("src", src);
// }
//查看历史审批意见
function checkHistoryOpinions(index){
	$$row = getRowData(index);
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
<body>
<div class="position" style="margin-top: 5px;">您当前所在位置： 任务管理  &gt; 待办任务</div>
<table id="waitTaskGrid"></table>
<!-- 新增弹框 -->
<div id="addWindow"></div>
<div id="handleTask"></div>
<div id="imageDialog"  class="easyui-dialog" title="流程图片" data-options="border:false,closed:true,fit:true">
	<img id="image" src="" >
</div>
<div id="optionsDialog" class="easyui-dialog" title="历史审批意见" style="width:900px;height:400px;" closed="true" data-options="href:'jsp/ad/optionsList.jsp',resizable:true,modal:true"></div>
</body>
</html>
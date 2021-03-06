<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
.resize {
	width: 720px;
	height: 70px;
	max-width: 720px;
	max-height: 70px;
	min-width: 720px;
	min-height: 70px;
}

.table {
	background-color: transparent;
	border-collapse: collapse;
	border-spacing: 0;
	max-width: 100%;
}

.table td {
	padding: 6px;
}

.table th {
	text-align: right;
	padding: 6px;
}
</style>
    <form id="baseForm" method="post" style="width: 850px;margin-left:5px;">
    <fieldset>
    <legend><img src="extend/fromedit.png" style="margin-bottom: -3px;"/>基本信息</legend>
    <input name="ptaId" class="easyui-numberbox" type="hidden"/>
	      <input id="appNo" name="appNo" type="hidden"/><!-- 申请编号 -->
	      <input id="sign" type="hidden" value="edit"/><!-- 修改or保存状态标志 -->
	      <input name="applicantNo" class="easyui-numberbox" type="hidden"/>
	      <input name="appDept" class="easyui-numberbox" type="hidden"/>
	      <input id="procStatus" name="procStatus" type="hidden"/>
	      <input name="appStatus" type="hidden"/>
	      <input id="ppeTotalAmt" name="ppeTotalAmt" type="hidden"/>
	      <input id="ppeQty" name="ppeQty" type="hidden"/>
          <table>
             <tr>
                <th>交接日期:</th>
                <td><input id="concedeDate" name="concedeDate" class="easyui-validatebox" disabled="disabled" data-options="editable:false"/></td>
                <th>接收部门:</th>
                <td><input id="takeoverDeptName" name="takeoverDeptName" class="easyui-textbox" required disabled="disabled" data-options="editable:false"/>
                	<input id="takeoverDept" name="takeoverDept" type="hidden"/>
                </td>
                <th>接收人:</th>
                <td><input id="takeoverUserName" name="takeoverUserName" class="easyui-textbox easyui-combobox easyui-validatebox" disabled="disabled" data-options="editable:false"/>
                	<input id="takeoverUser" name="takeoverUser" type="hidden"/>
                </td>
             </tr>
             <tr>
             	<th>交接事由:</th>
             	<td><input id="concedeReson" name="concedeReson" class="easyui-textbox easyui-validatebox" required maxlength="200"/></td>
             	<th>交接地点:</th>
             	<td><input id="concedeAddr" name="concedeAddr" class="easyui-textbox easyui-validatebox" required maxlength="80"/></td>
             </tr>
             <tr>
		         <th class="textStyle">备注:</th>
		         <td colspan="5">
		            <textarea name="remark" rows="5" cols="20" class="easyui-textbox resize" maxlength="200"></textarea>
		         </td>
		       </tr>
             <tr>
	             <td colspan="6" style="text-align: right;">
	                <!-- <a href="javascript:void(0)" id="rsetId" class="easyui-linkbutton" iconCls="icon-reload" onclick="clearForm();">重置</a> -->
	                <a href="javascript:void(0)" id="editId" class="easyui-linkbutton" iconCls="icon-edit" style="display: none;" onclick="editForm()">编辑</a>
	                <a href="javascript:void(0)" id="saveId" class="easyui-linkbutton" iconCls="icon-save" onclick="saveInvestor();">保存</a>
	             </td>
             </tr>
          </table>
    </fieldset>
    </form>
    <div style="margin: 5px;">
        <table id="linkPeopleGrid"></table>
    </div>

<script type="text/javascript">
$(function(){
	initLinkPeopleGrid();
	
	//初始化组织机构
	$("#takeoverDeptName").combotree({
		width:'auto',
		url:"Organization/organizationList.do",
		idFiled:'id',
	 	textFiled:'name',
	 	valueFiled:'id',
	 	parentField:'pid',
	 	onLoadSuccess:function(data){
	 		//加一个全部
	 	},
	 	onChange:function(){
	 			$("#takeoverDept").val($(this).combotree('getValue'));
	 			 RenderName($(this).combotree('getValue'));
	 	}
	}); 
	
	$("#takeoverUserName").combobox({required:true});
	$("#concedeDate").datebox({required:true});
});

//用户名的下拉
function RenderName(organizeId){
	//选中部门后下拉框
		$("#takeoverUserName").combobox({
			width:'auto',
			multiple:false,
			separator:",", 
			url:"BadgeApp/findOrgUserList.do?organizeId="+organizeId,
			valueField:'code',
		 	textFiled:'text',
		 	onLoadSuccess:function(data){
		 		//加一个全部
		 	},
		 	onChange:function(){
		 		$("#takeoverUser").val($(this).combotree('getValue'));
		 	}
		}); 
}

function initLinkPeopleGrid(){
	$("#linkPeopleGrid").datagrid({
		url : "ppeTurnoverAppAttachController/findAllList.do?appNo="+$("#appNo").val(),
		width: 'auto',
		height : 340,
		pagination:false,
		rownumbers:true,
		border:true,
		singleSelect:false,
		nowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
		remoteSort:false,//定义是否从服务器对数据进行排序。
		striped:true,//是否显示斑马线
		columns : [ [
                     {field : 'ck',rowspan:2,checkbox : true},
		             {field : 'ppeNo',title : '资产编号',width : 80,rowspan:2,align : 'center'},
		             {field : 'ppeName',title : '名称',width : 80,rowspan:2,align : 'center'},
		             {field : 'model',title : '规格型号',width : 120,rowspan:2,align : 'center'},
		             {field : 'qty',title : '数量',width : 120,rowspan:2,align : 'center'},
		             {field : 'ppeAmt',title : '单价',width : 120,rowspan:2,align : 'center'}
		             
		]],
		toolbar: [{
			   iconCls: 'icon-add',
			   text:'新增',
			   handler:toFormWindow
		   },"-",{
			iconCls: 'icon-edit',
			text:'编辑',
			handler: toEditContact
		},"-",{
			iconCls: 'icon-cut',
			text:'删除',
			handler:toDelete
		}]
	});
}

//保存申请基本信息
function saveInvestor(){
	var isValid = $("#baseForm").form('validate');
	if(!isValid){
		return false;
	}
	$.ajax({
		type: "POST",
		url:"ppeTurnoverAppController/addPpeTurnoverApp.do",
		data:$('#baseForm').serialize(),
		async: false,//默认true设置下，所有请求均为异步请求
		cache: true,
	    success: function(iJson) {
	    	if(iJson.status){
	    		$("#appNo").val(iJson.data);//
	    	}
	    	$.messager.alert(iJson.title,iJson.message,'info');
	    }
	});
}
function toFormWindow(){
	var appNo=$("#appNo").val();
	if(null==appNo || ""==appNo){
		$.messager.alert("提示", "请先保存基本信息!", "warning");
		return;
	}
	$sonDialog = $("<div></div>").dialog({
		title: '新增资产',    
	    width: 550,    
	    height: 380,    
	    closed: false,    
	    cache: false,    
	    href: 'jsp/ad/ppeTurnover/ppeTurnoverAppAttachForm.jsp',    
	    modal: true,
	    onClose:function(){
	    	$(this).dialog('destroy');
	    },
	    buttons:[{
	    	text:'保存并继续',
	    	iconCls:'icon-save',
			handler:function(){
				saveLinkInfo();
			}
	    },{
	    	text:'保存并关闭',
	    	iconCls:'icon-save',
			handler:function(){
				saveLinkInfo();
				$sonDialog.dialog('destroy');
			}
	    },{
	    	text:'关闭页面',
	    	iconCls:'icon-no',
			handler:function(){
				$sonDialog.dialog('destroy');
			}
	    }]
	});
}
function toEditContact(){
	var row = $("#linkPeopleGrid").datagrid("getSelected");
	var rows = $('#linkPeopleGrid').datagrid('getSelections');
	if (row == null) {
		$.messager.alert("提示", "请选择一条记录进行编辑!", "warning");
		return;
	}
	if(rows.length >1){
		$.messager.alert("提示", "您只能选择一条记录执行编辑!", "warning");
		return;
	}
	//将数据加载到表单中
	$sonDialog = $("<div></div>").dialog({
		title: '移交物品',    
	    width: 670,    
	    height: 380,    
	    closed: false,    
	    cache: false,    
	    href: 'jsp/ad/ppeTurnover/ppeTurnoverAppAttachForm.jsp',    
	    modal: true,
	    onLoad:function(){
	    	var f = $("#linkPeopleForm");
			f.form("load", row);
	    },
	    onClose:function(){
	    	$(this).dialog('destroy');
	    	row = null;
	    },
	    buttons:[{
	    	text:'保存',
	    	iconCls:'icon-save',
			handler:function(){
				saveLinkInfo();
				$sonDialog.dialog('destroy');
			} 
	    },{
	    	text:'取消',
	    	iconCls:'icon-no',
			handler:function(){
				$sonDialog.dialog('destroy');
				row = null;
			}
	    }]
	});
}

//保存附加表信息
function saveLinkInfo(){
	var isValid = $("#linkPeopleForm").form('validate');
	if(!isValid){
		return false;
	}
	var appNo = $("#appNo").val();//投资人id
	$("#appNum").val(appNo);
	$.ajax({
		type: "POST",
		url:"ppeTurnoverAppAttachController/saveTurnoverAppAttach.do",
		data:$('#linkPeopleForm').serialize(),
		async: false,//默认true设置下，所有请求均为异步请求
		cache: true,
	    success: function(iJson) {
	    	if(iJson.status){//保存成功
	    		$("#linkPeopleForm").form('clear');//清空表单
	    		initLinkPeopleGrid()
	    	//	$("#linkPeopleGrid").datagrid('reload',{loanerId:appNo});//刷新列表
	    	}
	    	$.messager.alert(iJson.title,iJson.message,'info');
	    }
	});
}

function toDelete(){
	var selected = $('#linkPeopleGrid').datagrid('getSelections');
	if (selected.length <= 0) {
		$.messager.alert("提示", "请至少选择一条记录执行删除!", "warning");
		return;
	}
	var ids = new Array();
	for(var i=0,len=selected.length; i<len; i++){
		ids.push(selected[i].ptaId);
	}
	ids = ids.join(",");
	$.messager.confirm('删除', '执行删除后，数据将不可恢复,是否执行?', function(d) {
		if (d) {
			$.ajax( {
				type : "POST",
				url : 'ppeTurnoverAppAttachController/deleteByPtaIds.do',
				data : "ptaIds="+ids+"&appNo="+$("#appNo").val(),
				dataType:'JSON',
				success : function(iJson) {
					if(iJson.status){
						//投资人客户id
						var appNo = $("#appNo").val();
						$("#linkPeopleGrid").datagrid("reload");
					}
					$.messager.alert(iJson.title,iJson.message,'info');
				}
			});
		}
	});
}

function loadPpe(){
	$.ajax({
		type : "POST",
		url : "ppeTurnoverAppController/findPpeStock.do",
		data : "ppeCode="+$("#ppeNo").val().trim()+"&appNo="+$("#appNo").val(),
		success : function(iJson){
			if(iJson.status){
				$("#ppeName").val(iJson.data.ppeName);
				$("#model").val(iJson.data.size);
				$("#qty").numberbox('setValue',1);
				$("#ppeAmt").numberbox('setValue',iJson.data.originalValue);
			}else{
				$("#ppeName").val("");
				$("#model").val("");
				$("#qty").numberbox('setValue',"");
				$("#ppeAmt").numberbox('setValue',"");
				$.messager.alert(iJson.title,iJson.message,'info');
			}
		} 
	}); 
}
</script>
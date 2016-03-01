<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- 电核部主管信息  -->
<style type="text/css">
	#acceptTaskForm input{border:none;}
	table {border-radius: 5px;}
	.linkSpan{
	  padding:5px;
	  display:-moz-inline-box;
	  display:inline-block;
	  width:40%; 
	  text-align: center;
	}
	.linkSpanS{
	  padding:5px;
	  display:-moz-inline-box;
	  display:inline-block;
	  width:10%; 
	  text-align: center;
	}
	a{text-decoration: none;}
	a:hover {
	 color: #FF0000;
	}
	.table th{
		text-align: right;
	}
	.table td{
		text-align: left;
	}	
</style>
<script type="text/javascript">
	var $row;
	var $datagrid;
	$(function(){
		// 查看申请状态
		$row = $grid.datagrid('getSelected');
		// 渲染备注
		$datagrid =  $("#lookLoanOrderdg").datagrid({
			url : "loanOrderHis/loanOrderHisAction!findAllLoanOrderHis.action",
			fit : true,
			fitColumns : true,
			pagination:false,
			rownumbers:true,
			border:true,
			singleSelect:true,
			nowrap:true,
			queryParams:{"loanOrderId":$row.loanOrderId},
			multiSort:false,
			fitColumns:true,
			columns : [ [ 
			              {field : 'agentTime',title : '受理时间',width : parseInt($(this).width()*0.1),align : 'center'},
			              {field : 'roleName',title : '受理角色',width : parseInt($(this).width()*0.1),align : 'center'},
			              {field : 'assigneeName',title : '受理人',width : parseInt($(this).width()*0.1),align : 'center'},
			              {field : 'title',title : '审批简述',width :parseInt($(this).width()*0.1),align : 'center'},
			              {field : 'comment',title : '审批详情',width :parseInt($(this).width()*0.1),align : 'center'},
			              {field : 'id',title : '查看附件',width :parseInt($(this).width()*0.08),align : 'center',
				            	formatter:function(value,row,index){
				            		return "<a href='javascript:void(0);' onclick='lookAttachment("+index+");'>查看附件</a>　　" ;
				            	}  
			              }
			              ] ]
		});
		// 渲染姓名
		$("#acceptTaskForm input[name='name']").val($row.name);
		// 渲染身份证号
		$("#acceptTaskForm input[name='idNo']").val($row.idNo);

		//查看附件
		$("#checkAttachment").click(function(){
			checkAttachementDetail('noauditId',$row.loanOrderId,'');
		});
		
		//上传附件
		$("#upploadAttachment").click(function(){
			var attType = $("#attType").combobox("getValue");
			fileUploadsDlg(attType,$row.loanOrderId);
		});
		
		//获取附件类型combobox,并将数据保存 
		$("#attType").combobox({
			valueField : 'code',
			textField : 'text',
			url:'common/commonAction!findTextArr.action?codeMyid=attachment_type',
			onLoadSuccess : function(){
				var val = $(this).combobox("getData");
				if(!$.isEmptyObject(val)){
                    $(this).combobox("select", val[0]["code"]);
				}
			},
			editable:false 
	    });
		
		//渲染电荷专员的信息
		$('#commissioner').combogrid({    
		    panelWidth:450,    
		    idField:'userId',    
		    textField:'name',  
			queryParams: {"roleCode":"IPCDianHeZhuanYuan"},
			url : "loanOrder/loanOrderAction!findCandidatePersonsByCode.action?t="+ new Date(),
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
		              ] ]
		});
	});
 
	// 查看详情附件
	function lookAttachment(index){
		var row = getRowData($datagrid,index);
		// 附件信息
		checkAttachementDetail('noauditId',$row.loanOrderId,row.assignee,'2');
	}
	
	// 提交表单信息
	function  submitTask(result) {
		// 判断是否含有电核专员信息
		if($("#commissioner").combogrid("getValue")==""){
			$.messager.alert("提示","请指定审贷委人员!","warning")
			return false;	
		}
		// 验证备注信息是否已经填写
		/* if($("#comment").val()=="" || $("#title").val()==""){
			$.messager.alert("提示","请填写完备注信息后再进行提交!","warning")
			return false;
		} */
		// 确认是否提交
		$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){
			if (r){
				var data = {
					"comment" : $("#comment").val(),
					"title" : $("#title").val(),
					"result" :result,
					"loanOrderId" : $row.loanOrderId,
					"taskId" :$row.taskId,
					"processingResult":result=="IPCTrialSupervisor2Through"?"A":"B",
					"commissioner":$("#commissioner").combogrid("getValue")

				}
				$.ajax({
					type : "POST",
					url : "loanOrder/loanOrderAction!submitTask.action",
					data : data,
					success : function(msg) {
						$grid.datagrid('reload');
						$taskFormDialog.dialog('close');
					}
				});
			}
		});
	}
</script>
<!-- 受理任务 S -->
<div data-options="region:'north',title:'North Title',split:true" style="overflow: hidden;">
	<div style="width: 900px;height: 190px;overflow: auto;">
		<form id="acceptTaskForm" method="post">
				<input name="id" id="id"  type="hidden"/>
				<input name="auditId" type="hidden" value="noauditId"/>
				 <table class="table" cellpadding="4">
					<tr>
					    <th>客户姓名:</th>
						<td><input name="name" readonly="readonly" type="text"/></td>
						<th>身份证号:</th>
						<td><input name="idNo" readonly="readonly" type="text"/></td>
					</tr>
					<tr>
						<th>电核专员:</th>
						<td><input id="commissioner" name="commissioner " readonly="readonly" type="text"/></td>
					 	<th>备注简述:</th>
						<td>
							<input id="title" name="title" class="easyui-validatebox easyui-textbox" style="border: 1px solid #DDDDDD;">
						</td>
					</tr>
					<tr>
					 	<th>备注详情</th>
						<td colspan="3">
							<textarea id="comment" name="comment" class="easyui-validatebox easyui-textbox" style="width:100%;height:70px;resize:none;"></textarea>
						</td>
					</tr>
					<tr>
						<th>
							附件类型:
						</th>
						<td>
							<input id="attType" class="easyui-textbox easyui-combobox" />
						</td>
						<td colspan="2">
							<a id="checkAttachment" href="javascript:void(0);" class="easyui-linkbutton">查看附件</a>	
							<a id="upploadAttachment" href="javascript:void(0);" class="easyui-linkbutton" >上传附件</a>	
						</td>
					</tr>
				 </table>
		</form>
	</div>
	
	<div style="width: 900px;height:30px;">
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitTask('IPCTrialSupervisor2Through');" >电核专管指派完毕</a>
	</div>
	
	<!-- 附件和备注信息列表 -->
	<div id="lookInfo" class="easyui-accordion" style="height: 390px;width: 900px;overflow: hidden;">
	    <div title="备注信息" data-options="iconCls:'icon-cstbase',selected:true">   
			<table id="lookLoanOrderdg" title="申请备注的信息" style="overflow: auto;"></table>
		</div>
	</div>
</div>
<!-- 受理任务E -->		
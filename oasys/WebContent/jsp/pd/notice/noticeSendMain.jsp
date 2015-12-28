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
    <title>人事通知</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="../../../layout/script.jsp"></jsp:include>
	<script type="text/javascript">
			var $dg;
			var $grid;
			$(function() {
				 $dg = $("#dg");
				 $grid=$dg.datagrid({
					url : "noticeInfoController/findNoticeSendList.do",
					width : 'auto',
					height : $(this).height()-120,
					pagination:true,
					rownumbers:true,
					border:true,
					striped:true,
					singleSelect:false,
					columns : [ [ {field:'ck',checkbox:true},
					              {field : 'noticeNo',title : '通知编号',width : parseInt($(this).width()*0.1),align:'center'},
					              {field : 'topic',title : '主题',width : parseInt($(this).width()*0.15),align:'center',},
					              {field : 'noticeContent',title : '通知内容',width : parseInt($(this).width()*0.2),align:'center'},
					              {field : 'noticeGrade',title : '通知级别',width : parseInt($(this).width()*0.1),align:'center',
					            	  formatter:function(value,row,index){
						            	  	if(value=="1"){
						            	  		return "一般";
						            	  	}else if(value=="2"){
						            	  		return "重要";
						            	  	}else if(value==3){
						            	  		return "紧急";
						            	  	}else{
						            	  		return "未知级别";
						            	  	}
						            	  }},
					              {field : 'sendDtime',title : '发件时间',width : parseInt($(this).width()*0.11),align:'center'},
					              {field : 'isNeedReceipt',title : '是否需要回执',width : parseInt($(this).width()*0.1),align:'center',
					            	  formatter:function(value,row,index){
					            		  if(value=="0"){
					            			  return "需要";
					            		  }else{
					            			  return "不需要";
					            		  }
					            	  }},
					              {field : 'isAllReceipted',title : '是否已全部回执',width : parseInt($(this).width()*0.1),align:'center',
					            		  formatter:function(value,row,index){
						            		  if(value=="0"){
						            			  return "是";
						            		  }else{
						            			  return "否";
						            		  }
						            	  }},
					              {field : 'isHaveAtt',title : '是否包含附件',width : parseInt($(this).width()*0.1),align:'center',
					            	  formatter:function(value,row,index){
					            		  if(value=="0"){
					            			  return "是";
					            		  }else{
					            			  return "否";
					            		  }
					            	  }}
					              ] ],toolbar:'#tb',onLoadSuccess:function(data){
									 	//当鼠标放上该字段时，提示功能
							            $("#dg").datagrid("doCellTip",{'max-width':'100px'});
								  }
				});
			});
			//删除通知信息
			function delRows(){
				 var rows = $('#dg').datagrid('getSelections');//获取选中的记录
				 if(rows!=null&&rows.length>=1){
					 var ids = new Array();
					 $.messager.confirm('删除', '删除该记录将不可恢复，确认删除吗?', function(d) {
						 if(d){
							 $.each(rows,function(i,row){
									if (row) {
										var rowIndex = $('#dg').datagrid('getRowIndex', row);
										$('#dg').datagrid('deleteRow', rowIndex);
										ids.push(row.noiId);//将ID放入数组中
									}
							 });
						 ids = ids.join(",");// 转换为字符串
						 $.ajax({
							    type:'post',
								url:"noticeInfoController/delNoticeInfo.do",
								data: "ids="+ids,
								success: function(rsp){
									$.messager.show({
										title : rsp.title,
										msg : rsp.message,
										timeout : 1000 * 2
									});
									$('#dg').datagrid('reload');
								}
						});
						}
					 })
				 }else{
					 $.messager.alert("提示","请至少选择一条记录!","warning");
				 }
			}
			//弹窗修改
						//弹窗修改
			function updRowsOpenDlg() {
				var row = $dg.datagrid('getSelected');
				if (row) {
					parent.$.modalDialog({
						title : "编辑通知",
						width : 800,
						height : 700,
						href : "noticeInfoController/toEditNotice.do?noiId="+row.noiId+"&noticeNo="+row.noticeNo,
						onLoad:function(){
							var f = parent.$.modalDialog.handler.find("#form");
							f.form("load",row);
							//加载收件人
						},			
						buttons : [ {
							text : '编辑',
							iconCls : 'icon-ok',
							handler : function() {
								parent.$.modalDialog.openner= $grid;
								var f = parent.$.modalDialog.handler.find("#form");
								f.submit();
							}
						}, {
							text : '取消',
							iconCls : 'icon-cancel',
							handler : function() {
								parent.$.modalDialog.handler.dialog('destroy');
								parent.$.modalDialog.handler = undefined;
							}
						}
						]
					});
				}else{
					parent.$.messager.show({
						title :"提示",
						msg :"请选择一行记录!",
						timeout : 1000 * 2
					});
				}
			}
			//弹窗增加
			function addRowsOpenDlg() {
				parent.$.modalDialog({
					title : '添加通知',
					width : 800,
					height : 700,
					href : "noticeInfoController/toEditNotice.do",
					buttons : [ {
						text : '确定',
						iconCls : 'icon-ok',
						handler : function() {
							parent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
							var f = parent.$.modalDialog.handler.find("#form");
							f.submit();
						}
					}, {
						text : '取消',
						iconCls : 'icon-cancel',
						handler : function() {
							parent.$.modalDialog.handler.dialog('destroy');
							parent.$.modalDialog.handler = undefined;
						}
					}
					]
				});
			}
		</script>
  </head>
  <body>
      <div data-options="region:'center',border : false">
      <div style="margin-left: 5px;margin-top: 5px">
			<div class="position" style="margin-top: 5px;">您当前所在位置：人力资源规划 &gt; 人事通知 &gt; 发件箱</div>
		</div>
  		<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
				<span class="badge">提示</span>
				<p>
					在此你可以对<span class="label-info"><strong>人事通知</strong></span>进行管理!
				</p>
		</div>
		<div id="tb" style="padding:2px 0">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td style="padding-left:2px">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRowsOpenDlg();">添加</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updRowsOpenDlg();">编辑</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRows();">删除</a>
					</td>
				</tr>
			</table>
		</div>
		<table id="dg" title="发件箱列表"></table>
  	</div>	
  </body>
</html>

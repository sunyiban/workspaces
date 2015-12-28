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
    <title>工牌申请汇总表</title>
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
		 $("#myId").combobox({
				valueField: 'value',   
		        textField: 'label',   
		        data: [{
					label: '全部',
					value: ''
				},{
					label: '财富端',
					value: 'CF'
				},{
					label: '借款端',
					value: 'JK'
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

		//获取key值
		<%
	    String definitionKey=request.getParameter("key");
	    %>
		//窗口自适应	
		$(window).resize(function(){  
            $("#dg").datagrid({  
            	height: $(window).height()-130  
            });                
        }); 
		
		badgegrid();
		

	});
				
	function badgegrid(){
		
		//获取key值
		$grid =$("#dg").datagrid({
					url : 'BadgeTask/findBadgeCollecList.do?definitionKey=<%=definitionKey%>',
					width: 'auto',
					height :$(this).height()-130,
					pagination : true,
					rownumbers : true,
					border : true,
					singleSelect : true,
					pageList:[10,20,30,40],
					nowrap : true,
					multiSort : false,
					fitColumns : true,
					columns : [ [
									//{field : 'ck',        title : 'ck',    width : $(this).width * 0.1, align:'center',checkbox:true},
							        {field : 'fullName',title : '申请部门',width:parseInt($(this).width()*0.1),align : 'center'},
					             	{field : 'appNo',title : '申请编号',width:parseInt($(this).width()*0.15),align : 'center',formatter : function(value, row, index) {
										return "<a href=\"javascript:void(0)\" onclick=\"showBadgeApp("+ index + ");\">" + value + "</a>";
									}},
							        {field : 'regName',title : '登记人',width:parseInt($(this).width()*0.07),align : 'center',
										formatter : function(value, row, index) {
											return "<a href=\"javascript:void(0)\" onclick=\"showView("+ index + ");\">" + value + "</a>";
										}
									},
									{field : 'appDate',title : '申请日期',width:parseInt($(this).width()*0.09),align : 'center'},   
									
									{field : 'remark',title : '备注',width : parseInt($(this).width()*0.2),align : 'center'},
									{field : 'number',title : '每个申请对应的数量',width:parseInt($(this).width()*0.07),align : 'center'},
									{field : 'heji',title : '数量',width:parseInt($(this).width()*0.07),align : 'center'},
									{field : 'total',title : '总计',width : parseInt($(this).width()*0.2),align : 'center'},
									] ],
									 onLoadSuccess:function(data){
										//隐藏每条申请对应的数量
										 $(this).datagrid("hideColumn","number");
										
										
		            			  		//合并其他列，并计算部门对应的数量
										 var paramObj = {rowStr:'fullName,appNo',//按照哪列进行分组
		            			  					rowGroup:'fullName,heji:ck,appNo,regName,remark,appDate,count',//对应rowStr的需要合并单元格的字段
		            			  					hejiColumn:'number',//需要计算的合计值的所在列
		            			  					hejiResult:'heji'};//计算后将合计值更新到该列中 
										 dataGirdSumMergeFunc($(this),paramObj); 
		            			  					
										//合并合计列
										$(this).datagrid("autoMergeCells",['total']);			
		            			  					
										
								        
										$(this).datagrid("doCellTip",{'max-width':'100px'});
									  }, 
							toolbar : '#tb'
						});
				}
	
	//详情
	function showBadgeApp(index) {
		//var row = $dg.datagrid('getSelected');
			var row = this.getRowData(index);
			$("#applyView").dialog({
				title : '工牌申请详情',
				iconCls:'icon-edit',
				width : 830,
				height : 624,
				modal:true,
				href : "jsp/ad/badgeApply/badgeAppAddForm.jsp",
				 onLoad:function(){
						 badgePeopleGrid(row.appNo);
						 $("#remark").val(row.remark);
						 $("#pnrId").val(row.pnrId);
						 $("#appPeoNo").val(row.appNo);
						 
						 //详情页，只显示查看附件按钮，
						 $("#upploadAttachment").hide();
						 $("#save").hide();
						 $("#rset").hide();
						 //隐藏列表中国的删除
						 $("#appUserView").datagrid({"toolbar":""});
						 //隐藏其他按钮
						 $("#remark").attr("disabled",true);
						 
				}, 
				onClose:function(){
					$grid.datagrid('reload');
					
				} 
			}); 
	}
	
	
	//执行查询
	function subCustomerRepaymentForm(){
			//行政经理查询条件
		$("#dg").datagrid("load",{
			myId:$("#myId").combobox("getValue"),
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
				<div id="collecList" class="position" style="margin-top: 5px;">您当前所在位置：工牌申请  &gt; 汇总表</div>
		</div>
		<!-- 高级查询栏区域 -->
  		<div  class="well well-small" style="margin-left: 5px;margin-top: 5px">
				<form id="customerRepaymentForm"  method="post">
					<table cellpadding="10px;"> 
						<tr>
						  <th>申请编号:</th>
					      <td><input name="appNo" id="appNoMain" class="easyui-textbox"  /></td>
					       <th>所属业务端:</th>
					      <td>
					      	<input id="myId" name="myId" class="easyui-textbox easyui-validatebox" />
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
		<!-- 展示客户信息详情 -->
		<div id="applyView"></div>
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

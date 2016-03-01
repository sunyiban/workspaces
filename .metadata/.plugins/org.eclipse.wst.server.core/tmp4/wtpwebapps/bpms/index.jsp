<%@page import="com.bpms.shiro.ShiroUser"%>
<%@page import="com.bpms.util.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ShiroUser user = Constants.getCurrendUser();
session.setAttribute("user", String.valueOf(user.getUserId()));
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>欢迎</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="layout/script.jsp"></jsp:include>
	<script type="text/javascript">
	// websocket对象
	var websocket;
	var $menuTree;
	$(function(){
		if (jqueryUtil.isLessThanIe8()) {
			$.messager.show({
				title : '警告',
				msg : '您使用的浏览器版本太低！<br/>建议您使用谷歌浏览器来获得更快的页面响应效果！',
				timeout : 1000 * 30
			});
		}
		initMenu();
	    initWebSocket();
	});
	
	// 初始化菜单
	function initMenu(){
		$menuTree = $("#menuTree").tree({
					url : 'systemAction!findAllFunctionList.action',
					method : 'get',
					animate : true,
					onContextMenu : function(e, node) {
						e.preventDefault();
						$(this).tree('select', node.target);
						$('#mm').menu('show', {
							left : e.pageX,
							top : e.pageY
						});
					},
					onSelect : function(node) {
						if (node.attributes.url != "javascript:void(0);") {
							var parent = $(this).tree("getParent",node.target);
							// 点击待办任务的菜单的时候将任务的个数清零
							if(node.id=='168'|| node.id=='169'|| node.id=='151'||node.id=='152'|| node.id=='236'||node.id=='237'){
								removeMessage(node);
								toTaskIdsEmpty(node);
							}
							// 如果为待办任务和受理任务时，根据ID区分Tab页卡的名称，防止重复
							var nodeText = node.text;
							if(node.id=='168'|| node.id=='169'){
								nodeText +="(贷款)"; 
							}else if(node.id=='151'||node.id=='152'){
								nodeText +="(投资申请)";
							}else if(node.id=='236'||node.id=='237'){
								nodeText +="(投资赎回)";
							}							
							var effort = nodeText + "||" + node.iconCls+ "||" + node.attributes.url;
							// 加载Tab页卡
							addTab(effort);
						}
					}
				});
	}
	// 装载新任务的列表
	var unClaimLoanTaskIds = [];              //存放“贷款业务”“待办任务”的TaskId的数据
	var claimLoanTaskIds = [];                //存放“贷款业务”“受理任务”的TaskId的数据
	var unClaimInvestApplyTaskIds = [];       //存放“投资申请业务”“待办任务”的TaskId的数据
	var claimInvestApplyTaskIds = [];         //存放“投资申请业务”“受理任务”的TaskId的数据
	var unClaimInvestRedeemTaskIds = [];      //存放“投资赎回业务”“待办任务”的TaskId的数据
	var claimInvestRedeemTaskIds = [];        //存放“投资赎回业务”“受理任务”的TaskId的数据
	//初始话WebSocket
	function initWebSocket() {
		websocket = new WebSocket(encodeURI('ws://'+window.location.host+'/message'));
		websocket.onopen = function() {
			//连接成功
			console.info("链接成功");
		}
		websocket.onerror = function() {
			//连接失败
			console.info("链接失败");
		}
		websocket.onclose = function() {
			//连接断开
			console.info("链接断开");
		}
		//消息接收
		websocket.onmessage = function(message) {
			var message = JSON.parse(message.data);
			if(message.type=='message'){
				/*************************************菜单消息提示*************************************/
				if (message.dataType== 'unClaimLoanOrder') {//接受贷款待办任务的提示信息
	                handleTaskIds(unClaimLoanTaskIds,message);
	                showMessage(unClaimLoanTaskIds.length,$menuTree.tree('find',"168"));
	                showLoanTotalTaskCount(message);
                }else if (message.dataType== 'claimLoanOrder') {// 接受贷款受理人的提示信息
	               	handleTaskIds(claimLoanTaskIds,message);
	                showMessage(claimLoanTaskIds.length,$menuTree.tree('find',"169"));
	                showLoanTotalTaskCount(message);
  				}else if (message.dataType== 'unClaimInvestApplyOrder') {//接受投资申请业务，待办任务的提示信息
	         		handleTaskIds(unClaimInvestApplyTaskIds,message);
	                showMessage(unClaimInvestApplyTaskIds.length,$menuTree.tree('find',"151"));
	                showInvestApplyTotalTaskCount(message);
                }else if(message.dataType== 'claimInvestApplyOrder') {// 接受投资申请业务，受理任务的提示信息
                	handleTaskIds(claimInvestApplyTaskIds,message);
	                showMessage(claimInvestApplyTaskIds.length,$menuTree.tree('find',"152"));
	                showInvestApplyTotalTaskCount(message);
                }else if (message.dataType== 'unClaimInvestRedeemOrder') {//接受投资赎回业务，待办任务的提示信息
	         		handleTaskIds(unClaimInvestRedeemTaskIds,message);
	                showMessage(unClaimInvestRedeemTaskIds.length,$menuTree.tree('find',"236"));
	                showInvestRedeemTotalTaskCount(message);
                }else if(message.dataType== 'claimInvestRedeemOrder') {// 接受投资赎回业务，受理任务的提示信息
                	handleTaskIds(claimInvestRedeemTaskIds,message);
	                showMessage(claimInvestRedeemTaskIds.length,$menuTree.tree('find',"237"));
	                showInvestRedeemTotalTaskCount(message);
                }				
				/*************************************菜单消息提示*************************************/
			}
		}
	};
	
	// 计算当前任务的个数
	function handleTaskIds(taskIds,message){
		// 如果订单id为空不进行任何操作
		if(message.taskId==null || message.taskId=="") return;
		
		// 如果为空则默认返回0
		if(message.calculateType == null || message.calculateType =="") return;
		
		if(message.calculateType =="add"){// 任务增加
			taskIds.push(message.taskId);
		}else if(message.calculateType =="subtract"){// 任务减少
			// 如果为空则直接返回
			if($.isEmptyObject(taskIds)) return;
			var index = $.inArray(message.taskId,taskIds);
			if(index!=-1){
				taskIds.splice(index,1);
			}
		}
	}
	
	// 显示任务个数
	function showMessage(messageCount,menu){
		removeMessage(menu);
		if(messageCount>0){
			$(menu.target).append("<div class='show_message'>"+messageCount+"<div>");
		}
	}
	
	// 隐藏任务的个数
	function removeMessage(menu){
		$(menu.target).find(".show_message").remove();
	}
	
	// 根据点击菜单将任务的个数至为零
	function toTaskIdsEmpty(menu){
		if(menu.id=="168"){
			unClaimLoanTaskIds = [];
		}else if(menu.id=="169"){
			claimLoanTaskIds = [];
		}else if(menu.id=="151"){
			unClaimInvestApplyTaskIds = [];       //“投资申请业务”“待办任务”
		}else if(menu.id=="152"){
			claimInvestApplyTaskIds = [];         //“投资申请业务”“受理任务”
		}else if(menu.id=="236"){
			unClaimInvestRedeemTaskIds = [];       //“投资赎回业务”“待办任务”  
		}else if(menu.id=="237"){
			claimInvestRedeemTaskIds = [];         //“投资赎回业务”“受理任务” 
		}
	}
	
	// 渲染借款的任务提示的总个数
	function showLoanTotalTaskCount(message){
        $("#taskIframePage").contents().find("#loanUnClaimCount").html(message.unClaimCount);
		$("#taskIframePage").contents().find("#loanClaimCount").html(message.claimCount);
	}
	// 渲染"投资申请"的任务提示总个数
	function showInvestApplyTotalTaskCount(message){
        $("#taskIframePage").contents().find("#investApplyUnClaimCount").html(message.unClaimCount);
		$("#taskIframePage").contents().find("#investApplyClaimCount").html(message.claimCount);
	}

	// 渲染"投资赎回"的任务提示总个数
	function showInvestRedeemTotalTaskCount(message){
        $("#taskIframePage").contents().find("#investRedeemUnClaimCount").html(message.unClaimCount);
		$("#taskIframePage").contents().find("#investRedeemClaimCount").html(message.claimCount);
	}
	
	
	
	</script>
	<style type="text/css">
	#menuAccordion a.l-btn span span.l-btn-text {
	    display: inline-block;
	    height: 14px;
	    line-height: 14px;
	    margin: 0px 0px 0px 10px;
	    padding: 0px 0px 0px 10px;
	    vertical-align: baseline;
	    width: 128px;
	}
	#menuAccordion 	a.l-btn span span.l-btn-icon-left {
	    background-position: left center;
	    padding: 0px 0px 0px 20px;
	}
	#menuAccordion .panel-body {
		padding:5px;
	}
	#menuAccordion span:focus{
		outline: none;
	}
	
	.show_message{
	        background-color: #ff0000;
		    width: 28px;
		    height: 18px;
		    border-radius: 40%;
		    line-height: 20px;
		    color: #fff;
		    text-align: center;
		    border-top: 0;
		    position: absolute;
		    margin-left: 140px;
		    margin-top: -18px;
         }
	
	</style>
  </head>
 <body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:71px;overflow: hidden;"  href="layout/north.jsp"></div>
	<div data-options="region:'west',split:true,title:'导航菜单'" style="width:200px;">
			<ul id="menuTree"></ul>
	</div> 
	<div data-options="region:'south',border:false" style="height:25px;background:#EEE;padding:5px;" href="layout/south.jsp"></div>
	<div data-options="region:'center',plain:true,title:'欢迎来到钱钱金融风控管理系统'" style="overflow: hidden;"  href="layout/center.jsp"></div>
<%--	<jsp:include page="user/loginAndReg.jsp"></jsp:include>--%>
</body>
</html>

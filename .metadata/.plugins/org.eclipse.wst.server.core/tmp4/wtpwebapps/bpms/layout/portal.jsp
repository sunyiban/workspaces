<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <title>主页面</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="../layout/script.jsp"></jsp:include>
	<script type="text/javascript" src="extend/jquery.portal.js"></script>
  	<link rel="stylesheet" href="extend/portal.css" type="text/css"></link>
  	<script type="text/javascript">
  	$(function(){
  		var  $menuTree = $('#menuTree', window.parent.document);
  		// 如果含有任务菜单，则进行渲染任务提示窗，渲染消息提示窗体
  		if($menuTree.tree('find',"168") !=null || $menuTree.tree('find',"169") !=null || $menuTree.tree('find',"151") !=null || $menuTree.tree('find',"152") !=null){
		  	// 渲染任务提示窗
  			$('#taskMessagePanel').panel({    
			  	  width:500,    
			  	  height:300,    
			  	  title: '任务提示栏', 
			  	  iconCls:'icon-tasktodo',
			  	  collapsible:true
		  	}); 
  			
		  	// 含有财富受理任务的菜单，则进行发送消息，获取首页的任务提示的个数，如果没有，则不发送获取任务个数的消息。
	  		if($menuTree.tree('find',"151") !=null || $menuTree.tree('find',"152") !=null){
	  			$.ajax({
	  			   url: "investOrder/investOrderAction!findInvestTaskCount.action",
	  			   type: "POST",
	  			   success: function(data){
	  				 $("#investUnClaimCount").html(data.unClaimTaskCount);
	  				 $("#investClaimCount").html(data.claimTaskCount);
	  			   }
	  			});
	  		}else{
	  			$("#investTaskMessage").remove();
	  		}
		  	
	  		// 含有贷款受理任务的菜单，则进行发送消息，获取首页的任务提示的个数，如果没有，则不发送获取任务个数的消息。
	  		if($menuTree.tree('find',"168") !=null || $menuTree.tree('find',"169") !=null){
	  			$.ajax({
	  			   url: "loanOrder/loanOrderAction!findLoanTaskCount.action",
	  			   type: "POST",
	  			   success: function(data){
	  				 $("#loanUnClaimCount").html(data.unClaimTaskCount);
	  				 $("#loanClaimCount").html(data.claimTaskCount);
	  			   }
	  			});
	  		}else{
	  			$("#loanOrderTaskMessage").remove();
	  		}
	  		
  		}else{
  			// 没有任务菜单，则直接删除对应的提示窗体
  			$('#taskMessagePanel').remove();
  		}
  	})
</script>
</head>
<body style="height:1000px;background-image: url(<%=basePath%>/extend/index.jpg);background-repeat: no-repeat;background-position: center;background-position-x: center;background-position-y: center;">
	<div style="width:600px;height:300px;margin-left: 20px;margin-top: 10px;">   
	    <div id="taskMessagePanel">
			<ol>
				<li id="investTaskMessage">
					<span style="font-size: 10px;font-weight: bold;">*财富</span>
					<dl>
						<dt>
							到目前为止您的<font style="font-weight: bold;font-size: 15px;" color="blue">代办任务</font>共有:
							<font id="investUnClaimCount" style="font-weight: bold;font-size: 20px;margin: 10px;" color="red"></font>个。
						</dt>
						<dt>
							到目前为止您的<font style="font-weight: bold;font-size: 15px;" color="blue">受理任务</font>共有:
							<font id="investClaimCount" style="font-weight: bold;font-size: 20px;margin: 10px;" color="red"></font>个。
						</dt>
					</dl>
				</li>
				<li id="loanOrderTaskMessage">
					<span style="font-size: 10px;font-weight: bold;">*贷款</span>
					<dl>
						<dt>
							到目前为止您的<font style="font-weight: bold;font-size: 15px;" color="blue">代办任务</font>共有:
							<font id="loanUnClaimCount" style="font-weight: bold;font-size: 20px;margin: 10px;" color="red"></font>个。
						</dt>
						<dt>
							到目前为止您的<font style="font-weight: bold;font-size: 15px;" color="blue">受理任务</font>共有:
							<font id="loanClaimCount" style="font-weight: bold;font-size: 20px;margin: 10px;" color="red"></font>个。
						</dt>
					</dl>
				</li>
			</ol>
	    </div>
	</div>  
</body>
</html>
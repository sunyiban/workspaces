/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2016-02-22 05:32:49 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.investApply;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class shouLiTaskMainForm_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"  \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("    <base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("    <title>受理任务</title>\r\n");
      out.write("\t<meta http-equiv=\"pragma\" content=\"no-cache\">\r\n");
      out.write("\t<meta http-equiv=\"cache-control\" content=\"no-cache\">\r\n");
      out.write("\t<meta http-equiv=\"expires\" content=\"0\">    \r\n");
      out.write("\t<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">\r\n");
      out.write("\t<meta http-equiv=\"description\" content=\"This is my page\">\r\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../layout/script.jsp", out, false);
      out.write("\r\n");
      out.write("\t<style type=\"text/css\">\r\n");
      out.write("\t\ta{\r\n");
      out.write("\t\t\ttext-decoration:none;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t</style>\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\t//声明一个全局变量row\r\n");
      out.write("\t\t\t//该变量可以在基于此Main页面上的弹出页面中使用，比如optionList.jsp页面。\r\n");
      out.write("\t\t\tvar row;\r\n");
      out.write("\t\r\n");
      out.write("\t\t\tvar $dg;\r\n");
      out.write("\t\t\tvar $grid;\r\n");
      out.write("\t\t\t$(function() {\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t//初始化高级查询区域的组件\r\n");
      out.write("\t\t\t\t$(\"#searchbox\").searchbox({\r\n");
      out.write("\t\t\t\t\tmenu:'#mm',\r\n");
      out.write("\t\t\t\t\tsearcher: function(value, name){\r\n");
      out.write("\t\t\t\t    \tvar str=\"{\\\"searchName\\\":\\\"\"+name+\"\\\",\\\"searchValue\\\":\\\"\"+value+\"\\\"}\";\r\n");
      out.write("\t\t\t            var obj = eval('('+str+')');\r\n");
      out.write("\t\t\t            $dg.datagrid('load',obj); \t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t// 自动调整页面高度\r\n");
      out.write("\t\t\t\t$(window).resize(function(){  \r\n");
      out.write("\t\t\t        $(\"#dg\").datagrid({  \r\n");
      out.write("\t\t\t        \theight: $(window).height()-100,\r\n");
      out.write("\t\t\t        \twidth : 'auto'\r\n");
      out.write("\t\t\t        });                \r\n");
      out.write("\t\t\t    });\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t//加载当前用户，比如销客专员，受理任务的数据\r\n");
      out.write("\t\t\t\t $dg = $(\"#dg\");\r\n");
      out.write("\t\t\t\t $grid=$dg.datagrid({\r\n");
      out.write("\t\t\t\t\turl : \"investApply/investApplyAction!findAllClaimedTask.action\", \r\n");
      out.write("\t\t\t\t\twidth : 'auto',\r\n");
      out.write("\t\t\t\t\theight : $(window).height()*0.96,\r\n");
      out.write("\t\t\t\t\tpagination:true,\r\n");
      out.write("\t\t\t\t\trownumbers:true,\r\n");
      out.write("\t\t\t\t\tborder:true,\r\n");
      out.write("\t\t\t\t\tsingleSelect:true,\r\n");
      out.write("\t\t\t\t\tnowrap:true,\r\n");
      out.write("\t\t\t\t\tmultiSort:false,\r\n");
      out.write("\t\t\t\t\tcolumns : [ [ \r\n");
      out.write("\t\t\t\t\t              {field : 'investorName',  title : '客户姓名',    width : parseInt($(this).width()*0.06), align:'center', \r\n");
      out.write("\t\t\t\t\t            \t  formatter: function(value, row, index){\r\n");
      out.write("\t\t\t\t\t            \t\t  return \"<a href=\\\"javascript:void(0)\\\" onclick=\\\"showInvestorView(\"+index+\")\\\">\"+value+\"</a>\";\r\n");
      out.write("\t\t\t\t\t            \t  }\r\n");
      out.write("\t\t\t\t\t              },\r\n");
      out.write("\t\t\t\t\t              {field : 'idCrad',        title : '身份证号',    width : parseInt($(this).width()*0.08), align:'center'},\r\n");
      out.write("\t\t\t\t\t              {field : 'mobTel',        title : '移动电话',    width : parseInt($(this).width()*0.08), align:'center' },\r\n");
      out.write("\t\t\t\t\t              {field : 'contractNo',    title : '合同编号',    width : parseInt($(this).width()*0.06), align:'center', \r\n");
      out.write("\t\t\t\t\t            \t  formatter: function(value, row, index){\r\n");
      out.write("\t\t\t\t\t            \t\t  if(row.contractNo == null || row.contractNo == \"\"){\t\t\t\t\t            \t\t\t  \r\n");
      out.write("\t\t\t\t\t\t            \t\treturn \"\";\t\t\t\t\t            \t\t\t  \t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t            \t\t  }else{\t\t\t\t\t            \t\t\t  \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t  \treturn \"<a href=\\\"javascript:void(0)\\\" onclick=\\\"showInvestContractDetailsView(\"+index+\")\\\">\"+value+\"</a>\";\t\t\t\t\t            \t\t\t \r\n");
      out.write("\t\t\t\t\t            \t\t  }\r\n");
      out.write("\t\t\t\t\t            \t  }\t\t\t\t\t            \t  \t\t\t\t\t            \t  \r\n");
      out.write("\t\t\t\t\t              },\r\n");
      out.write("\t\t\t\t\t              {field : 'signDate',      title : '合同签署日期', width : parseInt($(this).width()*0.07), align:'center',\r\n");
      out.write("\t\t\t\t\t            \t  formatter: function(value, row, index){\r\n");
      out.write(" \t\t\t\t            \t\t  \t    return value; \r\n");
      out.write("\t\t\t\t\t            \t  }\t\t\t\t\t            \t  \t\t\t\t\t            \t  \t\t\t\t\t            \t  \r\n");
      out.write("\t\t\t\t\t              },\r\n");
      out.write("\t\t\t\t\t              {field : 'prodId',    title : '理财产品',    width : parseInt($(this).width()*0.06), align:'center' ,\r\n");
      out.write("\t\t\t\t\t            \t  formatter: function(value, row, index){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t      var result =\"<a href='javascript:void(0);' onclick='showInvestorAndInvestProductsDetailsView(\\\"\"+row.investOrderId+\"\\\");'>详情</a>\";\r\n");
      out.write("\t\t\t\t\t      \t\t\t\t  return result;\t\t\t\t\t            \t\t  \r\n");
      out.write("\t\t\t\t\t            \t  }\r\n");
      out.write("\t\t\t\t\t           \t  },\r\n");
      out.write("\t\t\t\t\t              {field : 'bankName',       title : '开户行名称',   width : parseInt($(this).width()*0.08), align:'center',\r\n");
      out.write("\t\t\t\t\t            \t  formatter: function(value, row, index){\r\n");
      out.write("\t\t\t\t\t            \t\t  if(row.bankName == null || row.bankName == \"\"){\t\t\t\t\t            \t\t\t  \r\n");
      out.write("\t\t\t\t\t\t            \t\treturn \"\";\t\t\t\t\t            \t\t\t  \t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t            \t\t  }else{\r\n");
      out.write("\t\t\t\t\t            \t\t\t  return value;\r\n");
      out.write("\t\t\t\t\t            \t\t  }\r\n");
      out.write("\t\t\t\t\t            \t  }\t\t\t\t\t\t\t           \t\t  \r\n");
      out.write("\t\t\t\t\t              },\r\n");
      out.write("\t\t\t\t\t              {field : 'actNo',    title : '开户行帐号',   width : parseInt($(this).width()*0.08), align:'center',\r\n");
      out.write("\t\t\t\t\t            \t  formatter: function(value, row, index){\r\n");
      out.write("\t\t\t\t\t            \t\t  if(row.actNo == null || row.actNo == \"\"){\t\t\t\t\t            \t\t\t  \r\n");
      out.write("\t\t\t\t\t\t            \t\treturn \"\";\t\t\t\t\t            \t\t\t  \t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t            \t\t  }else{\r\n");
      out.write("\t\t\t\t\t            \t\t\t  return value;\r\n");
      out.write("\t\t\t\t\t            \t\t  }\t\t\t\t\t            \t\t  \r\n");
      out.write("\t\t\t\t\t            \t  }\t\t\t\t\t\t            \t  \r\n");
      out.write("\t\t\t\t\t              },\r\n");
      out.write("\t\t\t\t\t              {field : 'operType',       title : '操作',width : parseInt($(this).width()*0.15), align:'center', \r\n");
      out.write("\t\t\t\t\t            \t  formatter: function(value,row,index){\r\n");
      out.write("\t\t      \t\t\t\t\t\t\tvar result =\"<a href='javascript:void(0);' onclick='checkInvestOrderHisApprovalOpinions(\"+index+\");'>查看审批意见</a>　 \";\r\n");
      out.write("\t\t      \t\t\t\t\t\t\t\tresult+=\"<a href='javascript:void(0);' onclick='checkProcessImg(\"+index+\");'>查看审批流程</a>　 \";\r\n");
      out.write("\t\t      \t\t\t\t\t\t\t\tresult+=\"<a href='javascript:void(0);' onclick='handleTaskDialog(\"+index+\");'>办理任务</a>\";\r\n");
      out.write("\t\t\t\t\t      \t\t\t\treturn result;\r\n");
      out.write("\t\t\t\t            \t \t }\r\n");
      out.write("\t\t\t\t\t              }\r\n");
      out.write("\t\t\t\t\t              ] ],\r\n");
      out.write("\t\t              toolbar:'#tb',\r\n");
      out.write("\t\t              onClickCell:function(rowIndex, field, value){\r\n");
      out.write("\t\t            \t  $(this).datagrid(\"selectRow\",\"rowIndex\");\r\n");
      out.write("\t\t              }\t\t\t\t\t              \r\n");
      out.write("\t\t\t\t});\t\t\t\t \t\t\t\t \t\t\t\t \r\n");
      out.write("\t\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t// 根据索引获取行的基本信息的函数\r\n");
      out.write("\t\tfunction getRowData (index) {\r\n");
      out.write("\t        if (!$.isNumeric(index) || index < 0) { return undefined; }\r\n");
      out.write("\t        var rows = $dg.datagrid(\"getRows\");\r\n");
      out.write("\t        return rows[index];\r\n");
      out.write("\t    }\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//查看投资人详细信息\r\n");
      out.write("\t\tfunction showInvestorView(index){\r\n");
      out.write("\t\t\tvar rows = $(\"#dg\").datagrid(\"getRows\");\r\n");
      out.write("\t\t\tvar row = rows[index];\r\n");
      out.write("\t\t\t$('#investorView').dialog({    \r\n");
      out.write("\t\t\t\t\t    title: '投资客户详情',    \r\n");
      out.write("\t\t\t\t\t    width: 800,    \r\n");
      out.write("\t\t\t\t\t    height: 550,    \r\n");
      out.write("\t\t\t\t\t    closed: false,    \r\n");
      out.write("\t\t\t\t\t    cache: false,    \r\n");
      out.write("\t\t\t\t\t    href: 'investor/investorAction!findInvestorByInvestorId.action?investorId='+row.investorId,    \r\n");
      out.write("\t\t\t\t\t    modal: true   \r\n");
      out.write("\t\t\t\t\t}); \r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t// 查看该投资人的\"合同详情\"\r\n");
      out.write("\t\tfunction showInvestContractDetailsView(index){\r\n");
      out.write("\t\t\tvar row = this.getRowData(index);\r\n");
      out.write("\t\t\t$('#contractInfoDialog').dialog({    \r\n");
      out.write("\t\t\t    title: '合同详情',    \r\n");
      out.write("\t\t\t    width: 600,    \t\r\n");
      out.write("\t\t\t    height: 600,    \r\n");
      out.write("\t\t\t    closed: false,\r\n");
      out.write("\t\t\t    closable: true,\r\n");
      out.write("\t\t\t    cache: false,    \r\n");
      out.write("\t\t\t    href: 'investOrder/investOrderAction!findInvestorOrderContractDetails.action?investOrderId='+row.investOrderId,    \r\n");
      out.write("\t\t\t    modal: true   \r\n");
      out.write("\t\t\t});\t\t\t\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//查看该投资人的理财产品详情界面\r\n");
      out.write("\t\tfunction showInvestorAndInvestProductsDetailsView(investOrderId){\t\t\t\r\n");
      out.write("\t\t\t$('#investorAndInvestProductsDialog').dialog({    \r\n");
      out.write("\t\t\t    title: '理财产品详情',    \r\n");
      out.write("\t\t\t    width: 800,    \t\r\n");
      out.write("\t\t\t    height: 350,    \r\n");
      out.write("\t\t\t    closed: false,\r\n");
      out.write("\t\t\t    closable: true,\r\n");
      out.write("\t\t\t    cache: false,    \r\n");
      out.write("\t\t\t    href: 'investOrder/investOrderAction!findInvestorAndInvestProductsDetails.action?investOrderId='+investOrderId,    \r\n");
      out.write("\t\t\t    modal: true   \r\n");
      out.write("\t\t\t});  \t\t\t\t\t\t\t\t\t\t\t\t\t \t\t\t\t\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t// 查看历史审批意见\t\t\r\n");
      out.write("\t\tfunction checkInvestOrderHisApprovalOpinions(index){\r\n");
      out.write("\t\t\tvar row = this.getRowData(index);\r\n");
      out.write("\t\t\tparent.$.modalDialog.openner= $grid;\r\n");
      out.write("\t\t\tparent.$.modalDialog({\r\n");
      out.write("\t\t\t\ttitle : '审批意见查看',\r\n");
      out.write("\t\t\t\twidth : 1000,\r\n");
      out.write("\t\t\t\theight : 650,\r\n");
      out.write("\t\t\t\t/* href : \"jsp/investOrder/investOrderProcessComment.jsp?taskId=\"+row.taskId */\r\n");
      out.write("\t\t\t\thref: 'jsp/investApply/optionsList4InvestApplyShouLi.jsp?taskId='+row.taskId\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\t\t\r\n");
      out.write("\r\n");
      out.write("\t\t// 查看投资订单的流程图片\r\n");
      out.write("\t\tfunction checkProcessImg (index) {\r\n");
      out.write("\t\t\tvar row = this.getRowData(index);\r\n");
      out.write("\t\t\tvar src = \"investApply/investApplyAction!checkWorkFlowImg.action?investOrderId=\" + row.investOrderId;\r\n");
      out.write("\t\t\t$('#imageDialog').dialog(\"open\");\r\n");
      out.write("\t\t\t$(\"#image\").attr(\"src\", src);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t// 处理任务\r\n");
      out.write("\t\tfunction handleTaskDialog(index) {\r\n");
      out.write("\t\t\tvar row = this.getRowData(index);\t\t\t\r\n");
      out.write("\t\t\tparent.$.modalDialog.openner= $grid;\r\n");
      out.write("\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\ttype : \"POST\",\r\n");
      out.write("\t\t\t\turl : \"investApply/investApplyAction!findTaskFormKeyByTaskId.action\",\r\n");
      out.write("\t\t\t\tdata : {\"taskId\":row.taskId},\r\n");
      out.write("\t\t\t\tsuccess : function(jspName) {\r\n");
      out.write("\t\t\t\t\tparent.$.modalDialog({\r\n");
      out.write("\t\t\t\t\t\ttitle : '投资申请审批流程',\r\n");
      out.write("\t\t\t\t\t\twidth : 1000,\r\n");
      out.write("\t\t\t\t\t\theight : 650,\r\n");
      out.write("\t\t\t\t\t\thref:jspName+\"?investOrderId=\"+row.investOrderId+\"&investorId=\"+row.investorId+\"&taskId=\"+row.taskId,\r\n");
      out.write("\t\t\t\t\t\tonDestroy:function(){\r\n");
      out.write("\t\t\t\t\t\t\t//刷新列表\r\n");
      out.write("\t\t\t\t\t\t\t$dg.datagrid(\"reload\");\r\n");
      out.write("\t\t\t\t\t\t}\t\t\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t }\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t</script>\r\n");
      out.write("  </head>\r\n");
      out.write("  <body>\r\n");
      out.write("      <div data-options=\"region:'center',border : false\">\r\n");
      out.write("\t\t<div class=\"position\" style=\"margin-top: 5px;\">您当前所在位置： 业务管理  &gt; 财富业务管理  &gt; 任务办理 &gt; 受理任务</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<!-- 高级查询栏区域 -->\r\n");
      out.write("\t\t<!-- <div id=\"tb\" style=\"padding:2px 0\">\r\n");
      out.write("\t\t\t<table cellpadding=\"0\" cellspacing=\"0\">\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th style=\"padding-left:2px\">\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t请选择查询项目：\r\n");
      out.write("\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t<td style=\"padding-left:2px\">\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<input id=\"searchbox\" type=\"text\"/>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div id=\"mm\">\r\n");
      out.write("\t\t\t\t<div name=\"investorName\">客户名称</div>\r\n");
      out.write("\t\t\t\t<div name=\"idCrad\">身份证号</div>\r\n");
      out.write("\t\t</div> -->\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<!-- 受理任务的投资订单数据表格区域 -->\r\n");
      out.write("\t\t<table id=\"dg\"></table>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<!-- 投资客户数据对话框区域 -->\r\n");
      out.write("\t\t<div id=\"investorView\"></div>\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- 合同详情信息对话框区域 -->\r\n");
      out.write("\t\t<div id=\"contractInfoDialog\"></div>\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- 理财产品详情对话框区域 -->\r\n");
      out.write("\t\t<div id=\"investorAndInvestProductsDialog\"></div>\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- 查看历史审批意见对话框区域 -->\r\n");
      out.write("\t\t<div id=\"OpinionsDialog\"></div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<!-- 流程图片弹框 -->\r\n");
      out.write("\t\t<div id=\"imageDialog\" class=\"easyui-dialog\" title=\"流程图片\" data-options=\"border:false,closed:true,fit:true\">\r\n");
      out.write("\t\t   <img id=\"image\" src=\"\">\r\n");
      out.write("\t\t</div>\t\t\t\t\r\n");
      out.write("  \t</div>\t\r\n");
      out.write("  </body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

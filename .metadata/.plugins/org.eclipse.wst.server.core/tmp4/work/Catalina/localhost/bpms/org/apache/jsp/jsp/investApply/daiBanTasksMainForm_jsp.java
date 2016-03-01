/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2016-02-22 05:32:48 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.investApply;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class daiBanTasksMainForm_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../layout/script.jsp", out, false);
      out.write("\r\n");
      out.write("<title>待办任务</title>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("body{\r\n");
      out.write("   margin: 0px;\r\n");
      out.write("   padding: 0px;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var $taskId;\r\n");
      out.write("$(function(){\r\n");
      out.write("\tcreateMyTaskGrid();\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t// 自动调整页面高度\r\n");
      out.write("\t$(window).resize(function(){  \r\n");
      out.write("        $(\"#taskList\").datagrid({  \r\n");
      out.write("        \theight: $(window).height()-100,\r\n");
      out.write("        \twidth : 'auto'\r\n");
      out.write("        });                \r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("//创建列表\r\n");
      out.write("function createMyTaskGrid(){\r\n");
      out.write("\t$(\"#taskList\").datagrid({\r\n");
      out.write("\t\turl:'investApply/investApplyAction!findAllUnclaimedTaskList.action',\t//获取当前角色中所有没有被签收（指定）的任务\t\r\n");
      out.write("\t\ttitle:'待办任务',\r\n");
      out.write("\t\twidth: 'auto',\r\n");
      out.write("\t\theight: $(window).height()-40,\r\n");
      out.write("\t\tpagination:true,\r\n");
      out.write("\t\trownumbers:true,\r\n");
      out.write("\t\tborder:false,\r\n");
      out.write("\t\tsingleSelect:true,\r\n");
      out.write("\t\tnowrap:true,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。\r\n");
      out.write("\t\tpageSize:30,\r\n");
      out.write("\t\tpageList:[10,20,30,40],\r\n");
      out.write("\t\tremoteSort:false,//定义是否从服务器对数据进行排序。\r\n");
      out.write("\t\tstriped:true,//是否显示斑马线\r\n");
      out.write("\t\tcolumns:[[\r\n");
      out.write("\t\t        {field : 'investorName',title : '客户姓名', width : parseInt($(this).width() * 0.06),align : 'center',formatter:function(value,row,index){\r\n");
      out.write("\t\t        \treturn \"<a href=\\\"javascript:void(0)\\\" onclick=\\\"showInvestorView(\"+index+\")\\\">\"+value+\"</a>\";\r\n");
      out.write("\t\t        }},\r\n");
      out.write("                {field : 'idCrad',title : '证件号码', width : parseInt($(this).width() * 0.08),align : 'center'},\r\n");
      out.write("\t            {field : 'mobTel',title : '手机号码', width : parseInt($(this).width() * 0.08),align : 'center'},\r\n");
      out.write("\t            {field : 'licaichanping',title : '理财产品', width : parseInt($(this).width() * 0.06),align : 'center',formatter:function(value,row,index){\r\n");
      out.write("               \t var result =\"<a href='javascript:void(0);' onclick='showInvestorAndInvestProductsDetailsView(\\\"\"+row.investOrderId+\"\\\");'>详情</a>\";\r\n");
      out.write("    \t\t\t\t return result;\t\r\n");
      out.write("                }},\r\n");
      out.write("                {field : 'financingMgr',title : '理财经理', width : parseInt($(this).width() * 0.06),align : 'center',\r\n");
      out.write("                \tformatter : function(value, row, index){\r\n");
      out.write("                \t\treturn row.financingMgr.name;\r\n");
      out.write("                \t}                \t\r\n");
      out.write("                },\r\n");
      out.write("                {field : 'fmPhone',title : '理财经理电话', width : parseInt($(this).width() * 0.06),align : 'center'},\r\n");
      out.write("                {field : 'bankName',title : '开户行名称', width : parseInt($(this).width() * 0.06),align : 'center'},\r\n");
      out.write("                {field : 'actNo',title : '开户行账号', width : parseInt($(this).width() * 0.06),align : 'center'},\r\n");
      out.write("                {field : 'actName',title : '账户名称', width : parseInt($(this).width() * 0.06),align : 'center'},\r\n");
      out.write("                {field : 'fmSignDate',title : '理财经理签署日期', width : parseInt($(this).width() * 0.07),align : 'center'},\r\n");
      out.write("                {field : 'caozuo',title : '操作', width : parseInt($(this).width() * 0.12),align : 'center',formatter:function(value,row,index){\r\n");
      out.write("                \treturn \"<a href=\\\"javascript:void(0)\\\" onclick=\\\"checkInvestOrderOpinions(\"+index+\")\\\">查看审批意见</a>&nbsp;&nbsp;&nbsp;\"+\r\n");
      out.write("                \t       \"<a href=\\\"javascript:void(0)\\\" onclick=\\\"checkProcessImg(\"+index+\")\\\">查看流程图</a>&nbsp;&nbsp;&nbsp;\"+\r\n");
      out.write("                \t       \"<a href=\\\"javascript:void(0)\\\" onclick=\\\"claimTask(\"+index+\")\\\">签收任务</a>\";\r\n");
      out.write("                }}\r\n");
      out.write("\t   ]]\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("//签收任务\r\n");
      out.write("function claimTask(index){\r\n");
      out.write("\tvar rows = $(\"#taskList\").datagrid(\"getRows\");\r\n");
      out.write("\tvar row = rows[index];\r\n");
      out.write("\t//发送Ajax\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("\t\ttype:'POST',\r\n");
      out.write("\t\turl:'investApply/investApplyAction!pickMyTask.action',\r\n");
      out.write("\t\tdata:'taskId='+row.taskId,\r\n");
      out.write("\t\tdataType:'JSON',\r\n");
      out.write("\t\tsuccess:function(iJson){\r\n");
      out.write("\t\t\tif(iJson.status){\r\n");
      out.write("\t\t\t\t$(\"#taskList\").datagrid(\"reload\");//刷新列表\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tparent.$.messager.show({\r\n");
      out.write("\t\t\t\ttitle : iJson.title,\r\n");
      out.write("\t\t\t\tmsg : iJson.message,\r\n");
      out.write("\t\t\t\ttimeout : 1000 * 2\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}); \r\n");
      out.write("}\r\n");
      out.write("//查看流程图\r\n");
      out.write("function checkProcessImg(index){\r\n");
      out.write("\tvar rows = $(\"#taskList\").datagrid(\"getRows\");\r\n");
      out.write("\tvar row = rows[index];\r\n");
      out.write("/* \tvar src = \"investOrder/investOrderAction!checkWorkFlowImg.action?investOrderId=\" + row.investOrderId; */\r\n");
      out.write("\tvar src = \"investApply/investApplyAction!checkWorkFlowImg.action?investOrderId=\" + row.investOrderId;\r\n");
      out.write("\t$('#imageDialog').dialog(\"open\");\r\n");
      out.write("\t$(\"#image\").attr(\"src\", src);\r\n");
      out.write("}\r\n");
      out.write("//查看投资人详细信息\r\n");
      out.write("function showInvestorView(index){\r\n");
      out.write("\tvar rows = $(\"#taskList\").datagrid(\"getRows\");\r\n");
      out.write("\tvar row = rows[index];\r\n");
      out.write("\t$('#investorView').dialog({    \r\n");
      out.write("\t\t\t    title: '详情',    \r\n");
      out.write("\t\t\t    width: 720,    \r\n");
      out.write("\t\t\t    height: 545,    \r\n");
      out.write("\t\t\t    closed: false,    \r\n");
      out.write("\t\t\t    cache: false,    \r\n");
      out.write("\t\t\t    href: 'investor/investorAction!findInvestorByInvestorId.action?investorId='+row.investorId,    \r\n");
      out.write("\t\t\t    modal: true   \r\n");
      out.write("\t\t\t}); \r\n");
      out.write("}\r\n");
      out.write("var row;\r\n");
      out.write("//查看审批意见\r\n");
      out.write("function checkInvestOrderOpinions(index){\r\n");
      out.write("\tvar rows = $(\"#taskList\").datagrid(\"getRows\");\r\n");
      out.write("\trow = rows[index];//获取本条数据\r\n");
      out.write("\t$taskId = row.taskId;\r\n");
      out.write("\t$('#OpinionsDialog').dialog({    \r\n");
      out.write("\t    title: '历史审查意见',    \r\n");
      out.write("\t    width: 800,    \r\n");
      out.write("\t    height: 500,    \r\n");
      out.write("\t    closed: false,    \r\n");
      out.write("\t    cache: false,    \r\n");
      out.write(" \t    /* href: 'jsp/investApply/optionsList.jsp', */     \r\n");
      out.write("\t    href: 'jsp/investApply/optionsList4InvestApplyDaiBan.jsp',     \r\n");
      out.write("\t    modal: true   \r\n");
      out.write("\t});   \r\n");
      out.write("}\r\n");
      out.write("//查看该投资人的理财产品详情界面\r\n");
      out.write("function showInvestorAndInvestProductsDetailsView(investOrderId){\r\n");
      out.write("\t$('#investorAndInvestProductsView').dialog({    \r\n");
      out.write("\t    title: '理财产品详情',    \r\n");
      out.write("\t    width: 700,    \t\r\n");
      out.write("\t    height: 350,    \r\n");
      out.write("\t    closed: false,\r\n");
      out.write("\t    closable: true,\r\n");
      out.write("\t    cache: false,    \r\n");
      out.write("\t    href: 'investOrder/investOrderAction!findInvestorAndInvestProductsDetails.action?investOrderId='+investOrderId,    \r\n");
      out.write("\t    modal: true   \r\n");
      out.write("\t});\t\t\t\t\t\t\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<body>\r\n");
      out.write("<div class=\"position\" style=\"margin-top: 5px;\">您当前所在位置： 业务管理  &gt; 财富业务管理  &gt; 任务办理 &gt; 待办任务</div>\r\n");
      out.write("<table id=\"taskList\" style=\"margin: 0px;padding: 0px;\"></table>\r\n");
      out.write("<!-- 流程图片弹框 -->\r\n");
      out.write("<div id=\"imageDialog\" class=\"easyui-dialog\" title=\"流程图片\" data-options=\"border:false,closed:true,fit:true\">\r\n");
      out.write("   <img id=\"image\" src=\"\">\r\n");
      out.write("</div>\r\n");
      out.write("<!-- 投资人详细信息 -->\r\n");
      out.write("<div id=\"investorView\"></div>\r\n");
      out.write("<!-- 审查意见 -->\r\n");
      out.write("<div id=\"OpinionsDialog\"></div>\r\n");
      out.write("<!-- 理财产品详情数据区域 -->\r\n");
      out.write("<div id=\"investorAndInvestProductsView\"></div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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

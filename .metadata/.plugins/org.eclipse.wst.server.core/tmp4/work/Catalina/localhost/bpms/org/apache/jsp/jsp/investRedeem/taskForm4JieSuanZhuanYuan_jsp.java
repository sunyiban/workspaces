/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2015-12-23 05:36:34 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.investRedeem;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class taskForm4JieSuanZhuanYuan_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("\t#acceptTaskForm table input{border: none;}\r\n");
      out.write("\ttable {border-radius: 5px;}\r\n");
      out.write("\t.linkSpan{\r\n");
      out.write("\t  padding:5px;\r\n");
      out.write("\t  display:-moz-inline-box;\r\n");
      out.write("\t  display:inline-block;\r\n");
      out.write("\t  width:40%; \r\n");
      out.write("\t  text-align: center;\r\n");
      out.write("\t}\r\n");
      out.write("\t.linkSpanS{\r\n");
      out.write("\t  padding:5px;\r\n");
      out.write("\t  display:-moz-inline-box;\r\n");
      out.write("\t  display:inline-block;\r\n");
      out.write("\t  width:10%; \r\n");
      out.write("\t  text-align: center;\r\n");
      out.write("\t}\r\n");
      out.write("\ta{text-decoration: none;}\r\n");
      out.write("\ta:hover {\r\n");
      out.write("\t color: #FF0000;\r\n");
      out.write("\t}\r\n");
      out.write("\t.table th{\r\n");
      out.write("\t\ttext-align: right;\r\n");
      out.write("\t}\r\n");
      out.write("\t.table td{\r\n");
      out.write("\t\ttext-align: left;\r\n");
      out.write("\t}\t\r\n");
      out.write("\ttextarea{resize: none;}\r\n");
      out.write("</style>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tvar row,row1;\r\n");
      out.write("\tvar investorId='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${investorId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("\tvar investOrderId='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${investOrderId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("\tvar taskId='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${taskId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t//查询投资人详细信息\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\turl:'investRedeem/investRedeemAction!findInvestRedeemInfo4HandleTask.action',\r\n");
      out.write("\t\t\tdata:'investOrderId='+investOrderId,\r\n");
      out.write("\t\t\tdataType:'json',\r\n");
      out.write("\t\t\tasync : false,\r\n");
      out.write("\t\t\tsuccess:function(data){\r\n");
      out.write("\t\t\t\trow = data;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t//渲染页面数据——理财经理的营业部名称\r\n");
      out.write("\t\t$(\"#id4OrgFullName\").html(row.orgFullName);\r\n");
      out.write("\t\t//渲染页面数据——投资者姓名\t\t\r\n");
      out.write("\t\t$(\"#id4ChName\").html(row.chName);\r\n");
      out.write("\t\t$(\"#id4ChName\").css({ color: \"#ff0011\"});\r\n");
      out.write("\t\t//渲染页面数据——投资者姓名\t\t\r\n");
      out.write("\t\t$(\"#id4ChName02\").html(row.chName);\r\n");
      out.write("\t\t$(\"#id4ChName02\").css({ color: \"#ff0011\"});\r\n");
      out.write("\t\t//渲染页面数据——投资起始日期\r\n");
      out.write("\t\t$(\"#id4BeginDate\").html(row.beginDate);\r\n");
      out.write("\t\t$(\"#id4BeginDate\").css({ color: \"#ff0011\"});\r\n");
      out.write("\t\t//渲染页面数据——理财产品名称\r\n");
      out.write("\t\t$(\"#id4ProdName\").html(row.prodName);\r\n");
      out.write("\t\t$(\"#id4ProdName\").css({ color: \"#ff0011\"});\r\n");
      out.write("\t\t//渲染页面数据——理财金额\r\n");
      out.write("\t\t$(\"#id4InvestEdu\").html(row.investEdu);\r\n");
      out.write("\t\t$(\"#id4InvestEdu\").css({ color: \"#ff0011\"});\r\n");
      out.write("\t\t//渲染页面数据——年化收益率\r\n");
      out.write("\t\t$(\"#id4Ars\").html(row.ars);\r\n");
      out.write("\t\t$(\"#id4Ars\").css({ color: \"#ff0011\"});\r\n");
      out.write("\t\t//渲染页面数据——新的年化收益率\r\n");
      out.write("\t\t$(\"#id4NewArs\").html(row.newArs);\r\n");
      out.write("\t\t$(\"#id4NewArs\").css({ color: \"#ff0011\"});\r\n");
      out.write("\t\t//渲染页面数据——申请人姓名(理财经理姓名)\r\n");
      out.write("\t\t$(\"#id4UserName\").html(row.userName);\r\n");
      out.write("\t\t$(\"#id4UserName\").css({ color: \"#ff0011\"});\r\n");
      out.write("\t\t//渲染页面数据——赎回申请日期\r\n");
      out.write("\t\t$(\"#id4RedeemBeginDate\").html(row.redeemBeginDate);\r\n");
      out.write("\t\t$(\"id4RedeemBeginDate\").css({ color: \"#ff0011\"});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t// 查看投资申请备注信息DataGrid\r\n");
      out.write("\t\t$(\"#investApplyMemoDataGrid\").datagrid({\r\n");
      out.write("\t\t\turl : \"investOrderHis/investOrderHisAction!findAllInvestOrderHisList.action?investOrderId=\"+investOrderId,\r\n");
      out.write("\t\t\twidth : 'auto',\r\n");
      out.write("\t\t\theight : 240,\r\n");
      out.write("\t\t\tpagination:true,\r\n");
      out.write("\t\t\trownumbers:true,\r\n");
      out.write("\t\t\tborder:true,\r\n");
      out.write("\t\t\tsingleSelect:true,\r\n");
      out.write("\t\t\tnowrap:true,\r\n");
      out.write("\t\t\tcolumns : [ [ \r\n");
      out.write("\t\t\t              {field : 'agentTime',title : '受理时间',width : parseInt($(this).width()*0.1),sortable:true},\r\n");
      out.write("\t\t\t              {field : 'roleName',title : '受理角色',width : parseInt($(this).width()*0.1)},\r\n");
      out.write("\t\t\t              {field : 'assigneeName',title : '受理人',width : parseInt($(this).width()*0.1),align : 'left'},\r\n");
      out.write("\t\t\t              {field : 'comment',title : '审批意见',width :parseInt($(this).width()*0.1),align : 'left'}\r\n");
      out.write("\t\t\t          ] ]\r\n");
      out.write("\t\t});\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t});\t\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t/**======审批通过或驳回=======*/\r\n");
      out.write("\tfunction  submitTask(result,object) {\r\n");
      out.write("\t\t// 验证备注信息是否已经填写\r\n");
      out.write("\t\tif($(\"#comment\").val()==\"\"){\r\n");
      out.write("\t\t\t$.messager.alert(\"提示\",\"请填写备注信息后再进行提交!\",\"warning\")\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t// 确认是否提交\r\n");
      out.write("\t\t$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){\r\n");
      out.write("\t\t\tif (r){\r\n");
      out.write("\t\t\t\tvar data = {\r\n");
      out.write("\t\t\t\t\t\"comment\" : $(\"#comment\").val(),\r\n");
      out.write("\t\t\t\t\t\"result\" :result,\r\n");
      out.write("\t\t\t\t\t\"investOrderId\" :investOrderId,\r\n");
      out.write("\t\t\t\t\t\"taskId\": taskId\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\ttype : \"POST\",\r\n");
      out.write("\t\t\t\t\turl : \"investRedeem/investRedeemAction!submitTask.action\",\r\n");
      out.write("\t\t\t\t\tdata : data,\r\n");
      out.write("\t\t\t\t\tsuccess : function(msg) {\r\n");
      out.write("\t\t\t\t\t\tparent.$.modalDialog.openner.datagrid('reload');\r\n");
      out.write("\t\t\t\t\t\tparent.$.modalDialog.handler.dialog('close');\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<!-- 受理任务 S -->\r\n");
      out.write("<div data-options=\"region:'north',title:'North Title',split:true\">\r\n");
      out.write("\r\n");
      out.write("\t<!-- 理财收益调整申请单文本界面 -->\t\r\n");
      out.write("\t<div style=\"height: 320px; overflow: auto; border-color: black; border: 1px;\" align=\"center\">\r\n");
      out.write("\t\t<h1><font color=\"red\">理财赎回申请书</font></h1>\r\n");
      out.write("\t\t<div align=\"left\" style=\"margin-left: 160px; font-size: 16px;\">\t\t\r\n");
      out.write("\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;<span id=\"id4OrgFullName\"></span>理财客户<span id=\"id4ChName\"></span>于<span id=\"id4BeginDate\"></span>投资理财<br>\r\n");
      out.write("\t\t\t产品<span id=\"id4ProdName\"></span>，金额为<span id=\"id4InvestEdu\"></span>元整。<br>\t\t\t\r\n");
      out.write("\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;现在因故申请终止协议，按照协议规定，自意支付钱钱金融信息服务（北京）有<br>限公司因终止协议所产生的相关费用。<br><br>\t\t\r\n");
      out.write("\t\t\t特此申请，望批准！<br><br><br>\t\t\t\t\r\n");
      out.write("\t\t\t<div style=\"margin-left: 470px\">\r\n");
      out.write("\t\t\t\t申请人：<span id=\"id4ChName02\"></span><br>\r\n");
      out.write("\t\t\t\t理财经理：<span id=\"id4UserName\"></span><br>\r\n");
      out.write("\t\t\t\t赎回申请日期：<span id=\"id4RedeemBeginDate\"></span>\t\t\t\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\t\t\t\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- 工作人员填写备注的区域 -->\r\n");
      out.write("<!-- \t<div style=\"height: 280px;overflow: auto;\" > -->\r\n");
      out.write("\t<div style=\"margin-left: 160px;\">\r\n");
      out.write("\t\t<form id=\"acceptTaskForm\" method=\"post\">\r\n");
      out.write("\t\t\t <input name=\"id\" id=\"id\"  type=\"hidden\"/>\r\n");
      out.write("\t\t\t <input name=\"auditId\" type=\"hidden\" value=\"noauditId\"/>\t\t\t\r\n");
      out.write("\t\t\t <table class=\"table\" cellpadding=\"5px;\">\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t \t<th>审批意见（必填）:</th>\r\n");
      out.write("\t\t\t\t\t<td><textarea id=\"comment\" name=\"comment\" class=\"easyui-validatebox easyui-textbox\" style=\"width:500px;height:25px;\"></textarea></td>\r\n");
      out.write("\t\t\t\t</tr>\t\t\t\t\t\t\r\n");
      out.write("\t\t\t </table>\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t<div style=\"width: 880px;height:30px;\">\r\n");
      out.write("\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"submitTask('JieSuanZhuanYuanTongGuo',this);\">审批通过</a>\r\n");
      out.write("\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"submitTask('JieSuanZhuanYuanBoHui',this);\">审批驳回</a>\r\n");
      out.write("\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"submitTask('JieSuanZhuanYuanJuJue',this);\">审批拒绝</a>\r\n");
      out.write("\t</div>\r\n");
      out.write("    <div id=\"lookInfo\" class=\"easyui-accordion\" style=\"height: 300px;width: 980px;overflow: hidden;\">\r\n");
      out.write("\t    <div title=\"备注信息\" data-options=\"iconCls:'icon-cstbase',selected:true\" >   \r\n");
      out.write("\t\t\t<table id=\"investApplyMemoDataGrid\" title=\"申请备注的信息\"></table>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("</div>   \r\n");
      out.write("<!-- 受理任务 E -->\t\t\r\n");
      out.write("<div id=\"dd\"></div>\t");
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

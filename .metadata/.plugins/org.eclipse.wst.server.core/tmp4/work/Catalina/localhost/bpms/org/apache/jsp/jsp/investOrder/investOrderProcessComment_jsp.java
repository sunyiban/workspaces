/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2015-12-31 07:15:58 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.investOrder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class investOrderProcessComment_jsp extends org.apache.jasper.runtime.HttpJspBase
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

	String taskId = request.getParameter("taskId");

      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tvar $grid ;\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\tvar $taskId = $(\"#taskId\").val();\r\n");
      out.write("\t\t// 查看申请状态\r\n");
      out.write("\t\tvar row = parent.$.modalDialog.openner.datagrid('getSelected');\r\n");
      out.write("\t\t$grid = $(\"#lookLoanOrderdg\").datagrid({\r\n");
      out.write("\t\t\turl : \"investOrderHis/investOrderHisAction!findAllInvestOrderHisList.action\",\r\n");
      out.write("\t\t\twidth : 'auto',\r\n");
      out.write("\t\t\theight : 610,\r\n");
      out.write("\t\t\tpagination:true,\r\n");
      out.write("\t\t\trownumbers:true,\r\n");
      out.write("\t\t\tborder:true,\r\n");
      out.write("\t\t\tsingleSelect:true,\r\n");
      out.write("\t\t\tnowrap:true,\r\n");
      out.write("\t\t\tqueryParams:{\"investOrderId\":row.investOrderId},\r\n");
      out.write("\t\t\tmultiSort:false,\r\n");
      out.write("\t\t\tcolumns : [ [ \r\n");
      out.write("\t\t\t              {field : 'agentTime',title : '受理时间',width : parseInt($(this).width()*0.1),sortable:true,align:'center'},\r\n");
      out.write("\t\t\t              {field : 'roleName',title : '受理角色',width : parseInt($(this).width()*0.1),align:'center'},\r\n");
      out.write("\t\t\t              {field : 'assigneeName',title : '受理人',width : parseInt($(this).width()*0.1),align : 'center'},\r\n");
      out.write("\t\t\t              {field : 'comment',title : '审批意见',width :parseInt($(this).width()*0.1),align : 'center'},\r\n");
      out.write("\t\t\t              {field : 'id',title : '查看附件',width :parseInt($(this).width()*0.09),align : 'center',\r\n");
      out.write("\t\t\t            \tformatter:function(value,row,index){\r\n");
      out.write("\t\t\t            \t\treturn \"<a href='javascript:void(0);' onclick='lookAttachment(\"+index+\");'>查看附件</a>　　\" ;\r\n");
      out.write("\t\t\t            \t}  \r\n");
      out.write("\t\t\t              }\r\n");
      out.write("\t\t\t              ] ]\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//是否显示查看附件\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\turl:\"investApply/investApplyAction!isShowAttachment.action\",\r\n");
      out.write("\t\t\ttype:\"post\",\r\n");
      out.write("\t\t\tasync:false,\r\n");
      out.write("\t\t\tdata:{\"taskId\":$taskId},\r\n");
      out.write("\t\t\tsuccess:function(data){\r\n");
      out.write("\t\t\t\tif(data){\r\n");
      out.write("\t\t\t\t\t$(\"#lookLoanOrderdg\").datagrid(\"hideColumn\",\"id\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\t\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t// 根据行索引获取行信息\r\n");
      out.write("\tfunction getRowData (index) {\r\n");
      out.write("        if (!$.isNumeric(index) || index < 0) { return undefined; }\r\n");
      out.write("        var rows = $grid.datagrid(\"getRows\");\r\n");
      out.write("        return rows[index];\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("\t// 查看附件信息\r\n");
      out.write("\tfunction lookAttachment(index){\r\n");
      out.write("\t\t\tvar row = getRowData(index);\r\n");
      out.write("\t\t\t$('#attachmentList').dialog({\r\n");
      out.write("\t\t\t\t\ttitle:\"附件信息\",\r\n");
      out.write("\t\t\t\t\twidth: 1000,    \r\n");
      out.write("\t\t\t\t    height: 650,    \r\n");
      out.write("\t\t\t\t    closed: false,    \r\n");
      out.write("\t\t\t\t    cache: false,    \r\n");
      out.write("\t\t\t\t    modal: true   \r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t$(\"#lookAttachmentList\").datagrid({\r\n");
      out.write("\t\t\t\turl : \"attachment/attachmentAction!findAllAttachmentList.action\",\r\n");
      out.write("\t\t\t\twidth : 'auto',\r\n");
      out.write("\t\t\t\theight : 430,\r\n");
      out.write("\t\t\t\tpagination:false,\r\n");
      out.write("\t\t\t\trownumbers:true,\r\n");
      out.write("\t\t\t\tborder:false,\r\n");
      out.write("\t\t\t\tsingleSelect:true,\r\n");
      out.write("\t\t\t\tnowrap:true,\r\n");
      out.write("\t\t\t\tqueryParams:{\"orderId\":row.investOrderId,\"userId\":row.assignee,\"orderType\":\"attachment_type_invest\"},\r\n");
      out.write("\t\t\t\tmultiSort:false,\r\n");
      out.write("\t\t\t\tcolumns : [ [ \r\n");
      out.write("\t\t\t\t              {field : 'attName',title : '附件名称',width : 200,sortable:true,align:'center'},\r\n");
      out.write("\t\t\t\t              {field : 'attTypeName',title : '附件类型',width : 160,align:'center'},\r\n");
      out.write("\t\t\t\t              {field : 'creatorName',title : '创建者',width : 170,align:'center'},\r\n");
      out.write("\t\t\t\t              {field : 'id',title : '查看附件',width :220,align:'center',formatter:function(value,row,index){\r\n");
      out.write("\t\t\t\t            \t\tvar result = \"<a target='_blank' href='jsp/openoffice/documentView.jsp?attId=\"+row.attId+\"'>在线预览</a>　　\" ;\r\n");
      out.write("\t\t\t\t            \t\t\tresult += \"<a target='_blank' href='javascript:void(0);' onclick=\\\"downloadAttachment4InvestOrder('\"+row.attId+\"');\\\">下载</a>　　\" ;\r\n");
      out.write("\t\t\t\t            \t\treturn result;\r\n");
      out.write("\t\t\t\t              }}\r\n");
      out.write("\t\t\t    ] ]\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("<table id=\"lookLoanOrderdg\" title=\"申请备注的信息\"></table>\r\n");
      out.write("<div id=\"attachmentList\">\r\n");
      out.write("\t<input type=\"hidden\" id=\"taskId\" value=\"");
      out.print(taskId);
      out.write("\">\r\n");
      out.write("\t<table id=\"lookAttachmentList\" title=\"申请附件的信息\"></table>\r\n");
      out.write("</div>\r\n");
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

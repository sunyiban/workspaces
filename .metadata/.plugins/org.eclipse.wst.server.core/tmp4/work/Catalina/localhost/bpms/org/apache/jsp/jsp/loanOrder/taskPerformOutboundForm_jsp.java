/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2015-10-29 01:51:13 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.loanOrder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class taskPerformOutboundForm_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t.table th{ text-align: right;}\r\n");
      out.write("\t.table td{ text-align: left;}\r\n");
      out.write("</style>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var $row;\r\n");
      out.write("var $datagrid;\r\n");
      out.write("$(function(){\r\n");
      out.write("\t// 查看申请状态\r\n");
      out.write("\t$row = $grid.datagrid('getSelected');\r\n");
      out.write("\t$datagrid = $(\"#lookLoanOrderdg\").datagrid({\r\n");
      out.write("\t\turl : \"loanOrderHis/loanOrderHisAction!findAllLoanOrderHis.action\",\r\n");
      out.write("\t\twidth : 'auto',\r\n");
      out.write("\t\theight : 340,\r\n");
      out.write("\t\tpagination:false,\r\n");
      out.write("\t\trownumbers:true,\r\n");
      out.write("\t\tborder:true,\r\n");
      out.write("\t\tsingleSelect:true,\r\n");
      out.write("\t\tnowrap:true,\r\n");
      out.write("\t\tqueryParams:{\"loanOrderId\":$row.loanOrderId},\r\n");
      out.write("\t\tmultiSort:false,\r\n");
      out.write("\t\tcolumns : [ [ \r\n");
      out.write("\t\t              {field : 'agentTime',title : '受理时间',width : parseInt($(this).width()*0.1),sortable:true},\r\n");
      out.write("\t\t              {field : 'roleName',title : '受理角色',width : parseInt($(this).width()*0.1)},\r\n");
      out.write("\t\t              {field : 'assigneeName',title : '受理人',width : parseInt($(this).width()*0.1),align : 'left'},\r\n");
      out.write("\t\t              {field : 'title',title : '审批简述',width :parseInt($(this).width()*0.1),align : 'left'},\r\n");
      out.write("\t\t              {field : 'comment',title : '审批详情',width :parseInt($(this).width()*0.1),align : 'left'},\r\n");
      out.write("\t\t              {field : 'id',title : '查看附件',width :parseInt($(this).width()*0.08),align : 'left',\r\n");
      out.write("\t\t\t            \tformatter:function(value,row,index){\r\n");
      out.write("\t\t\t            \t\treturn \"<a href='javascript:void(0);' onclick='lookAttachment(\"+index+\");'>查看附件</a>　　\" ;\r\n");
      out.write("\t\t\t            \t}  \r\n");
      out.write("\t\t              }\r\n");
      out.write("\t\t              ] ]\r\n");
      out.write("\t});\r\n");
      out.write("\t// 渲染姓名\r\n");
      out.write("\t$(\"#acceptTaskForm input[name='name']\").val($row.name);\r\n");
      out.write("\t// 渲染身份证号\r\n");
      out.write("\t$(\"#acceptTaskForm input[name='idNo']\").val($row.idNo);\r\n");
      out.write("\r\n");
      out.write("\t$(\"#attType\").combobox({\r\n");
      out.write("\t\tvalueField : 'code',\r\n");
      out.write("\t\ttextField : 'text',\r\n");
      out.write("\t\turl:'common/commonAction!findTextArr.action?codeMyid=attachment_type',\r\n");
      out.write("\t\tonLoadSuccess : function(){\r\n");
      out.write("\t\t\tvar val = $(this).combobox(\"getData\");\r\n");
      out.write("\t\t\tif(!$.isEmptyObject(val)){\r\n");
      out.write("                $(this).combobox(\"select\", val[0][\"code\"]);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\teditable:false\r\n");
      out.write("    });\r\n");
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("\t//查看附件\r\n");
      out.write("\t$(\"#checkAttachment\").click(function(){\r\n");
      out.write("\t\tcheckAttachementDetail('noauditId',$row.loanOrderId,'');\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t//上传附件\r\n");
      out.write("\t$(\"#upploadAttachment\").click(function(){\r\n");
      out.write("\t\tvar attType = $(\"#attType\").combobox(\"getValue\");\r\n");
      out.write("\t\tfileUploadsDlg(attType,$row.loanOrderId);\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("});\r\n");
      out.write("\t\r\n");
      out.write("\t// 提交表单信息\r\n");
      out.write("\tfunction  submitTask(result) {\r\n");
      out.write("\t\t// 验证备注信息是否已经填写\r\n");
      out.write("\t\tif($(\"#comment\").val()==\"\"||$(\"#title\").val()==\"\"){\r\n");
      out.write("\t\t\t$.messager.alert(\"提示\",\"请填写完备注信息后再进行提交!\",\"warning\")\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t// 确认是否提交\r\n");
      out.write("\t\t$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){\r\n");
      out.write("\t\t\tif (r){\r\n");
      out.write("\t\t\t\tvar data = {\r\n");
      out.write("\t\t\t\t\t\"comment\" : $(\"#comment\").val(),\r\n");
      out.write("\t\t\t\t\t\"title\":$(\"#title\").val(),\r\n");
      out.write("\t\t\t\t\t\"result\" :result,\r\n");
      out.write("\t\t\t\t\t\"loanOrderId\" : $row.loanOrderId,\r\n");
      out.write("\t\t\t\t\t\"taskId\": $row.taskId,\r\n");
      out.write("\t\t\t\t\t\"processingResult\":\"A\"\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\ttype : \"POST\",\r\n");
      out.write("\t\t\t\t\turl : \"loanOrder/loanOrderAction!submitTask.action\",\r\n");
      out.write("\t\t\t\t\tdata : data,\r\n");
      out.write("\t\t\t\t\tsuccess : function(msg) {\r\n");
      out.write("\t\t\t\t\t\t$grid.datagrid('reload');\r\n");
      out.write("\t\t\t\t\t\t$taskFormDialog.dialog('close');\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t// 查看附件\r\n");
      out.write("\tfunction lookAttachment(index){\r\n");
      out.write("\t\tvar row = getRowData($datagrid,index);\r\n");
      out.write("\t\t// 附件信息\r\n");
      out.write("\t\tcheckAttachementDetail('noauditId',$row.loanOrderId,row.assignee,'2');\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t//查看稽核信息\r\n");
      out.write("\tfunction checkAuditReportDetail(){\r\n");
      out.write("\t\twindow.open(\"jsp/loanOrder/auditInfoRecordDetail.jsp?loanOrderId=\"+$row.loanOrderId,\r\n");
      out.write("\t\t\t\t\"稽核信息详情\", 'height=650, width=1000, top=200, left=400, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t//查看信审报告 \r\n");
      out.write("\tfunction checkApplicationReportDetail(){\r\n");
      out.write("\t\twindow.open(\"jsp/loanOrder/applicationReportDetail.jsp?loanOrderId=\"+$row.loanOrderId+\"&loanerId=\"+$row.loanerId,\r\n");
      out.write("\t\t\t\t\"稽核信息详情\", 'height=650, width=1000, top=200, left=400, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("<!-- 受理任务 S -->\r\n");
      out.write("<div data-options=\"region:'north',title:'North Title',split:true\">\r\n");
      out.write("\t<div style=\"width: 980px;height: 190px;overflow: auto;\">\r\n");
      out.write("\t\t<form id=\"acceptTaskForm\" method=\"post\">\r\n");
      out.write("\t\t\t\t<input name=\"id\" id=\"id\"  type=\"hidden\"/>\r\n");
      out.write("\t\t\t\t<input name=\"auditId\" type=\"hidden\" value=\"noauditId\"/>\r\n");
      out.write("\t\t\t\t <table cellpadding=\"5px;\">\r\n");
      out.write("\t\t\t\t\t <tr>\r\n");
      out.write("\t\t\t\t\t    <th>客户姓名</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"name\" readonly=\"readonly\" type=\"text\" class=\"easyui-textbox easyui-validatebox\" data-options=\"required:true\"/></td>\r\n");
      out.write("\t\t\t\t\t\t<th>身份证号</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"idNo\" readonly=\"readonly\" type=\"text\" class=\"easyui-textbox easyui-validatebox\" data-options=\"validType:'idcard'\"/></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t \t<th>备注简述:</th>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input id=\"title\" name=\"title\" class=\"easyui-validatebox easyui-textbox\" style=\"border: 1px solid #DDDDDD;\">\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t \t<th>备注详情:</th>\r\n");
      out.write("\t\t\t\t\t\t<td colspan=\"3\">\r\n");
      out.write("\t\t\t\t\t\t\t<textarea id=\"comment\" name=\"comment\" class=\"easyui-validatebox easyui-textbox\" style=\"width:100%;height:70px;resize:none;\"></textarea>\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t\t\t附件类型:\r\n");
      out.write("\t\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input id=\"attType\" class=\"easyui-textbox easyui-combobox\" />\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t<td colspan=\"2\">\r\n");
      out.write("\t\t\t\t\t\t\t<a id=\"checkAttachment\" href=\"javascript:void(0);\" class=\"easyui-linkbutton\">查看附件</a>\t\r\n");
      out.write("\t\t\t\t\t\t\t<a id=\"upploadAttachment\" href=\"javascript:void(0);\" class=\"easyui-linkbutton\" >上传附件</a>\t\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t </table>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\t<div style=\"width:980px;height:30px;\">\r\n");
      out.write("\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"checkApplicationReportDetail();\">查看信审报告</a>\r\n");
      out.write("\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"checkAuditReportDetail();\">查看稽核信息</a>\r\n");
      out.write("\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"submitTask('PerformOutboundThrough');\">外访完毕</a>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<div id=\"lookInfo\" class=\"easyui-accordion\" style=\"height: 380px;width: 980px;overflow: hidden;\">\r\n");
      out.write("\t\t\t<div title=\"备注信息\" data-options=\"iconCls:'icon-cstbase',selected:true\" >   \r\n");
      out.write("\t\t\t\t<table id=\"lookLoanOrderdg\" title=\"申请备注的信息\"></table>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>   \r\n");
      out.write("<!-- 受理任务 E -->\t\t\t\t\t\r\n");
      out.write("\r\n");
      out.write("\t\t\r\n");
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

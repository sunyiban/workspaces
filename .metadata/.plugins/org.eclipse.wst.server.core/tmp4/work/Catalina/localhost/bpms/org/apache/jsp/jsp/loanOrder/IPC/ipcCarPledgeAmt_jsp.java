/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2015-11-10 05:57:22 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.loanOrder.IPC;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class ipcCarPledgeAmt_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<style type=\"text/css\">\r\n");
      out.write("\tth {\r\n");
      out.write("\t\ttext-align: left;\r\n");
      out.write("\t}\r\n");
      out.write("\ttextarea {\r\n");
      out.write("\t\twidth:100%;\r\n");
      out.write("\t}\r\n");
      out.write("</style>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t   //查询贷款额度\r\n");
      out.write("\t   $.ajax({\r\n");
      out.write("\t\t   url : \"microcreditOpinion/microcreditOpinionAction!findFinalLoanAmt.action\",\r\n");
      out.write("\t\t   type:\"post\",\r\n");
      out.write("\t\t   data:{\"loanOrderId\":$row.loanOrderId},\r\n");
      out.write("\t\t   success:function(data){\r\n");
      out.write("\t\t\t  $(\"#oralAmt\").val(data);\r\n");
      out.write("\t\t   }\r\n");
      out.write("\t   });\r\n");
      out.write("\t   \r\n");
      out.write("\t   //查询车抵额度\r\n");
      out.write("\t   $.ajax({\r\n");
      out.write("\t\t\turl:\"carInfo/carInfoAction!findCarInfoByOrderId.action\",\r\n");
      out.write("\t\t\ttype:\"post\",\r\n");
      out.write("\t\t\tdata:{\"loanOrderId\":$row.loanOrderId},\r\n");
      out.write("\t\t\tsuccess:function(data){\r\n");
      out.write("\t\t\t\tif(data){\r\n");
      out.write("\t\t\t\t\t$(\"#carAmt\").val(data.vehicleMortgageAmt);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\tfunction saveCarPledgeAmt(){\r\n");
      out.write("\t\tvar finalAmt = $(\"#finalAmt\").val();\r\n");
      out.write("\t\t$(\"#saveFinalAmtDateForm\").form(\"submit\",{\r\n");
      out.write("\t\t\turl : \"microcreditOpinion/microcreditOpinionAction!saveCarPledgeAmt.action\",\r\n");
      out.write("\t\t\tonSubmit : function(param) {\r\n");
      out.write("\t\t\t\tvar isValid = $(this).form('validate');\r\n");
      out.write("\t\t\t\tif (isValid){\r\n");
      out.write("\t\t\t\t\tparam.loanOrderId = $row.loanOrderId;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\treturn isValid; // 返回false终止表单提交\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\tcarPledgeAmtDlg.dialog(\"close\");\r\n");
      out.write("\t\t\t\tdata = $.parseJSON(data);\r\n");
      out.write("\t\t\t\t$.messager.show({\r\n");
      out.write("\t\t\t\t\ttitle:\"提示\",\r\n");
      out.write("\t\t\t\t\tmsg:data.message,\r\n");
      out.write("\t\t\t\t\tshowType:\"slide\",\r\n");
      out.write("\t\t\t\t\ttiemout:1000\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction countFinalAMt(key){\r\n");
      out.write("\t\tkey.value= key.value.replace(/[^\\d]/g,'');\r\n");
      out.write("\t\tvar carAmt = $(\"#carAmt\").val();\r\n");
      out.write("\t\tvar finalAmt = parseInt(key.value==\"\"?0:key.value)+parseInt(carAmt==\"\"?0:carAmt);\r\n");
      out.write("\t\t$(\"#finalAmt\").val(finalAmt);\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("\t<div id=\"\" >\r\n");
      out.write("\t\t<form id=\"saveFinalAmtDateForm\" method=\"post\">\r\n");
      out.write("\t\t\t<div style=\"width:92%;text-align: center;padding-left:20px;\">\r\n");
      out.write("\t\t\t\t<table cellpadding=\"8\">\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t\t\t车抵额度\r\n");
      out.write("\t\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t\t<td  colspan=\"2\">\r\n");
      out.write("\t\t\t\t\t\t\t<input id=\"carAmt\" name=\"vehicleMortgageAmt\" class=\"easyui-textbox\" disabled=\"disabled\"/>元\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t\t\t原审批通过额度\r\n");
      out.write("\t\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t\t<td  colspan=\"2\">\r\n");
      out.write("\t\t\t\t\t\t\t<input id=\"oralAmt\" class=\"easyui-textbox\" disabled=\"disabled\"/>元\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t\t\t最终通过额度\r\n");
      out.write("\t\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t\t<td  colspan=\"2\">\r\n");
      out.write("\t\t\t\t\t\t\t<input id=\"finalAccrossAmt\" name=\"finalAmt\" onkeyup=\"countFinalAMt(this);\" onblur=\"countFinalAMt(this);\" class=\"easyui-textbox easyui-validatebox\" data-options=\"validType:'mDouble',required:true\"/>元\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t\t\t总计贷款额度\r\n");
      out.write("\t\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t\t<td colspan=\"2\">\r\n");
      out.write("\t\t\t\t\t\t\t<input id=\"finalAmt\" class=\"easyui-textbox\" readonly=\"readonly\" />元\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t\t<div id=\"upload_form\" style=\"width: 90%; height: 30px; text-align: right;\">\r\n");
      out.write("\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"saveCarPledgeAmt();\">保存</a>\r\n");
      out.write("\t\t</div> \r\n");
      out.write("\t</div>\r\n");
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

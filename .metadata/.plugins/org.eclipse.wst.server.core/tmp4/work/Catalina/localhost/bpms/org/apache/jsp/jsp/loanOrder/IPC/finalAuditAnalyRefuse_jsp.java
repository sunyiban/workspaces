/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2016-01-21 02:46:21 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.loanOrder.IPC;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.bpms.util.Constants;
import com.bpms.shiro.ShiroUser;
import java.util.*;

public final class finalAuditAnalyRefuse_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");

 ShiroUser shiroUser = Constants.getCurrendUser();

      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t\t$(function(){\r\n");
      out.write("\t\t\t$(\"#finaDate\").datetimebox({   \r\n");
      out.write("\t\t\t    required: true,   \r\n");
      out.write("\t\t\t\teditable:false,\r\n");
      out.write("\t\t\t});  \r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t// 渲染贷款期限\r\n");
      out.write("\t\t   $(\"input[name='loanPeriodType']\").combobox({\r\n");
      out.write("\t\t\t\turl : \"common/commonAction!findTextArr.action?codeMyid=loan_period_type\",\r\n");
      out.write("\t\t\t\tvalueField : 'code',\r\n");
      out.write("\t\t\t\ttextField : 'text',\r\n");
      out.write("\t\t\t\tdisabled : true,\r\n");
      out.write("\t\t\t\tonLoadSuccess : function(){\r\n");
      out.write("\t\t            var val = $(this).combobox(\"getData\");\r\n");
      out.write("\t\t            for (var item in val[0]) {\r\n");
      out.write("\t\t                if (item == \"code\") {\r\n");
      out.write("\t\t                    $(this).combobox(\"select\", val[0][item]);\r\n");
      out.write("\t\t                }\r\n");
      out.write("\t\t            }\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t   });\r\n");
      out.write("\r\n");
      out.write("\t\t\t// 渲染月服务费率\r\n");
      out.write("\t\t   $(\"input[name='monthServiceFeeRate']\").combobox({\r\n");
      out.write("\t\t\t\turl : \"common/commonAction!findTextArr.action?codeMyid=month_service_fee_rate\",\r\n");
      out.write("\t\t\t\tvalueField : 'code',\r\n");
      out.write("\t\t\t\ttextField : 'text',\r\n");
      out.write("\t\t\t\tdisabled : true,\r\n");
      out.write("\t\t\t\tonLoadSuccess : function(){\r\n");
      out.write("\t\t            var val = $(this).combobox(\"getData\");\r\n");
      out.write("\t\t            for (var item in val[0]) {\r\n");
      out.write("\t\t                if (item == \"code\") {\r\n");
      out.write("\t\t                    $(this).combobox(\"select\", val[0][item]);\r\n");
      out.write("\t\t                }\r\n");
      out.write("\t\t            }\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t   });\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t \t//查询系统参数\r\n");
      out.write("\t\t\tfunction checkSysParameter(paramCode){\r\n");
      out.write("\t\t\t\tvar datas = \"\";\r\n");
      out.write("\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\turl:\"sysParameter/sysParameterAction!findSysParameter.action\",\r\n");
      out.write("\t\t\t\t\ttype:\"post\",\r\n");
      out.write("\t\t\t\t\tasync:false,\r\n");
      out.write("\t\t\t\t\tdata:{\"parmCode\":paramCode},\r\n");
      out.write("\t\t\t\t\tsuccess:function(data){\r\n");
      out.write("\t\t\t\t\t\tdatas = data.parmValue;\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\terror:function(){\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\treturn datas;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t \t\r\n");
      out.write("\t\t\t$(\"input[name='loanInterestRate']\").numberbox({    \r\n");
      out.write("\t\t\t    value:checkSysParameter('loan_rate'),\r\n");
      out.write("\t\t\t    disabled:true\r\n");
      out.write("\t\t\t}); \r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t// 信贷方式\r\n");
      out.write("\t\t   $(\"input[name='auditWay']\").combobox({\r\n");
      out.write("\t\t\t\twidth:171,\r\n");
      out.write("\t\t\t\turl:\"common/commonAction!findTextArr.action?codeMyid=audit_way\",\r\n");
      out.write("\t\t\t\tvalueField: 'code',\r\n");
      out.write("\t\t\t\ttextField: 'text',\r\n");
      out.write("\t\t\t\teditable: false,\r\n");
      out.write("\t\t\t\trequired:true,\r\n");
      out.write("\t\t\t\tdisabled : true,\r\n");
      out.write("\t\t\t\tonLoadSuccess : function(){\r\n");
      out.write("\t\t            var val = $(this).combobox(\"getData\");\r\n");
      out.write("\t\t            for (var item in val[0]) {\r\n");
      out.write("\t\t                if (item == \"code\") {\r\n");
      out.write("\t\t                    $(this).combobox(\"select\", val[0][item]);\r\n");
      out.write("\t\t                }\r\n");
      out.write("\t\t            }\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t \t//查询初审建议金额\r\n");
      out.write("\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\turl:\"creditAuditReport/creditAuditReportAction!findCreditAuditReportByLoanOrderId.action\",\r\n");
      out.write("\t\t\t\ttype:\"post\",\r\n");
      out.write("\t\t\t\tdata:{\"loanOrderId\":$row.loanOrderId},\r\n");
      out.write("\t\t\t\tasync:false,\r\n");
      out.write("\t\t\t\tsuccess:function(data){\r\n");
      out.write("\t\t\t\t\tif(data.firstAuditReport) {\r\n");
      out.write("\t\t\t\t\t\tvar organizationId = $row.organizationId;\r\n");
      out.write("\t\t\t\t\t\t//初审建议金额为万元\r\n");
      out.write("\t\t\t\t\t\tvar suggestAmt = parseInt(data.firstAuditReport.suggestAmt)*10000;\r\n");
      out.write("\t\t\t\t\t\t$(\"#firstAuditSugAmt\").val(suggestAmt);\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\t\t\t\tif($row.organizationId==79 && suggestAmt <= 150000){\r\n");
      out.write("\t\t\t\t\t\t\t$(\"#auditWay\").combobox(\"select\",\"1\").combobox(\"disable\");\r\n");
      out.write("\t\t\t\t\t\t}else if(suggestAmt <= 100000){\r\n");
      out.write("\t\t\t\t\t\t\t$(\"#auditWay\").combobox(\"select\",\"1\").combobox(\"disable\");\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t// 获取终审报告的信息\r\n");
      out.write("\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\turl:\"creditAuditReport/creditAuditReportAction!findCreditAuditReportByLoanOrderId.action\",\r\n");
      out.write("\t\t\t\ttype:\"post\",\r\n");
      out.write("\t\t\t\tdata:{\"loanOrderId\":$row.loanOrderId},\r\n");
      out.write("\t\t\t\tasync:false,\r\n");
      out.write("\t\t\t\tsuccess:function(data){\r\n");
      out.write("\t\t\t\t\tif(undefined!=data.finalAuditReport) {\r\n");
      out.write("\t\t\t\t\t\t$(\"#finalAuditReport-form\").form(\"load\",data.finalAuditReport);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t// 保存资质分析的信息\r\n");
      out.write("\t\tfunction saveFinalAuditReport(result,object){\r\n");
      out.write("\t\t\tresult = $result;\r\n");
      out.write("\t\t\t// 确认是否提交\r\n");
      out.write("\t\t\t$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){\r\n");
      out.write("\t\t\t\tif (r){\r\n");
      out.write("\t\t\t\t\t\t$(object).parents(\"form:first\").form('submit', {\r\n");
      out.write("\t\t\t\t\t\t\turl : \"finalAuditReportAction/finalAuditReportAction!saveFinalAuditReport.action\",\r\n");
      out.write("\t\t\t\t\t\t\tonSubmit : function(param) {\r\n");
      out.write("\t\t\t\t\t\t\t\tvar isValid = $(this).form('validate');\r\n");
      out.write("\t\t\t\t\t\t\t\tif (isValid){\r\n");
      out.write("\t\t\t\t\t\t\t\t\tparam.loanOrderId = $row.loanOrderId;\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\treturn isValid; // 返回false终止表单提交\r\n");
      out.write("\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\t\t\t\t\tdata = $.parseJSON(data);\r\n");
      out.write("\t\t\t\t\t\t\t\tloadFinalAuditReport(data.data);\r\n");
      out.write("\t\t\t\t\t\t\t\talertMsg(data);\r\n");
      out.write("\t\t\t\t\t\t\t\t$.messager.progress('close'); // 如果提交成功则隐藏进度条\r\n");
      out.write("\t\t\t\t\t\t\t\t// 确认是否提交\r\n");
      out.write("\t\t\t\t\t\t\t\tsubmitTask(result);\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("</script>\r\n");
      out.write("<!-- 终审资质分析 -->\t\r\n");
      out.write("\t<div class=\"easyui-tabs\" style=\"fit:true;\">\r\n");
      out.write("\t\t<div title=\"资质分析\">\r\n");
      out.write("\t\t\t<form id=\"finalAuditReport-form\" method=\"post\">\r\n");
      out.write("\t\t\t\t<input name=\"finaId\" hidden=\"true\" class=\"easyui-validatebox\">\r\n");
      out.write("\t\t\t\t<table class=\"table\" style=\"margin-top: 10px;width:100%;\" cellpadding=\"5px;\">\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td colspan=\"6\"><span style=\"font-weight: bold;font-size: 14px;width:60px;\">[终审资质分析详情]</span></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>合同金额:</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"contractLoanAmount\" class=\"easyui-numberbox\" value=\"0\" data-options=\"groupSeparator:','\" readonly=\"readonly\"/>元</td>\r\n");
      out.write("\t\t\t\t\t\t<th>贷款期限:</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input id=\"loanPeriodType\" name=\"loanPeriodType\"  class=\"easyui-validatebox easyui-textbox easyui-combobox\" />月</td>\r\n");
      out.write("\t\t\t\t\t\t<th>月服务汇率:</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input id=\"monthServiceFeeRate\" name=\"monthServiceFeeRate\" class=\"easyui-validatebox easyui-combobox\" /></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>利息:</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input id=\"loanInterestRate\" name=\"loanInterestRate\"  class=\"easyui-validatebox easyui-numberbox\" data-options=\"min:0,precision:2,required:true\"/></td>\r\n");
      out.write("\t\t\t\t\t\t<th>信访费用:</th>\r\n");
      out.write("\t\t\t\t\t\t<td ><input name=\"visitFee\"  class=\"easyui-validatebox easyui-numberbox\" value=\"0\" readonly=\"readonly\" />元</td>\r\n");
      out.write("\t\t\t\t\t\t<th>信贷方式:</th>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input id=\"auditWay\" name=\"auditWay\" class=\"easyui-combobox\"/>\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>实放金额:</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"actualLoanAmount\"  class=\"easyui-numberbox\" value=\"0\" data-options=\"groupSeparator:','\" readonly=\"readonly\" />元</td>\r\n");
      out.write("\t\t\t\t\t\t<th>月还款额:</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"monthRepay\"  class=\"easyui-numberbox\" value=\"0\" data-options=\"groupSeparator:','\" readonly=\"readonly\" /></td>\r\n");
      out.write("\t\t\t\t\t\t<th>初审建议金额</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input id=\"firstAuditSugAmt\" class=\"easyui-numberbox\" data-options=\"groupSeparator:','\" disabled=\"disabled\"></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>审核专员:</th>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input class=\"easyui-validatebox easyui-textbox\" value=\"");
      out.print(shiroUser.getUser().getName() );
      out.write("\" disabled=\"disabled\"/>\r\n");
      out.write("\t\t\t\t\t\t\t<input id=\"finaPersonnel\" name=\"finaPersonnel\" hidden=\"true\" value=\"");
      out.print(shiroUser.getUserId() );
      out.write("\" class=\"easyui-validatebox easyui-textbox\"/>\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t<th>审核日期</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input id=\"finaDate\" name=\"finaDate\"/></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>审贷部门意见:</th>\r\n");
      out.write("\t\t\t\t\t\t<td colspan=\"6\"><textarea id=\"finaPersSugg\" name=\"finaPersSugg\" style=\"width:95%;height:70px;\" class=\"easyui-validatebox easyui-textbox\" data-options=\"required:true\"></textarea></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td colspan=\"6\" style=\"text-align: right;\">\r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" onclick=\"saveFinalAuditReport('',this);\"\tclass=\"easyui-linkbutton\" iconCls=\"icon-save\">保存</a>\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t</div>\t\r\n");
      out.write("\t</div>\t\r\n");
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

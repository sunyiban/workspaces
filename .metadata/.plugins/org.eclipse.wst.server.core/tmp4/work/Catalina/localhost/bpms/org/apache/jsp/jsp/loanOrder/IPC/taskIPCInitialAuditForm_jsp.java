/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2016-01-20 09:30:55 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.loanOrder.IPC;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class taskIPCInitialAuditForm_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!-- 电核专员(电核部主管指派完毕后)  -->\r\n");
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
      out.write("</style>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tvar $row;\r\n");
      out.write("\tvar $datagrid;\r\n");
      out.write("\tvar $quesDlg;\r\n");
      out.write("\tvar $result;\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t// 查看申请状态\r\n");
      out.write("\t\t$row = $grid.datagrid('getSelected');\r\n");
      out.write("\t\t// 渲染备注\r\n");
      out.write("\t\t$datagrid =  $(\"#lookLoanOrderdg\").datagrid({\r\n");
      out.write("\t\t\turl : \"loanOrderHis/loanOrderHisAction!findAllLoanOrderHis.action\",\r\n");
      out.write("\t\t\tfit : true,\r\n");
      out.write("\t\t\tfitColumns : true,\r\n");
      out.write("\t\t\tpagination:false,\r\n");
      out.write("\t\t\trownumbers:true,\r\n");
      out.write("\t\t\tborder:true,\r\n");
      out.write("\t\t\tsingleSelect:true,\r\n");
      out.write("\t\t\tnowrap:true,\r\n");
      out.write("\t\t\tqueryParams:{\"loanOrderId\":$row.loanOrderId},\r\n");
      out.write("\t\t\tmultiSort:false,\r\n");
      out.write("\t\t\tfitColumns:true,\r\n");
      out.write("\t\t\tcolumns : [ [ \r\n");
      out.write("\t\t\t              {field : 'agentTime',title : '受理时间',width : parseInt($(this).width()*0.1),align : 'center'},\r\n");
      out.write("\t\t\t              {field : 'roleName',title : '受理角色',width : parseInt($(this).width()*0.1),align : 'center'},\r\n");
      out.write("\t\t\t              {field : 'assigneeName',title : '受理人',width : parseInt($(this).width()*0.1),align : 'center'},\r\n");
      out.write("\t\t\t              {field : 'title',title : '审批简述',width :parseInt($(this).width()*0.1),align : 'center'},\r\n");
      out.write("\t\t\t              {field : 'comment',title : '审批详情',width :parseInt($(this).width()*0.1),align : 'center'},\r\n");
      out.write("\t\t\t              {field : 'id',title : '查看附件',width :parseInt($(this).width()*0.08),align : 'center',\r\n");
      out.write("\t\t\t\t            \tformatter:function(value,row,index){\r\n");
      out.write("\t\t\t\t            \t\treturn \"<a href='javascript:void(0);' onclick='lookAttachment(\"+index+\");'>查看附件</a>　　\" ;\r\n");
      out.write("\t\t\t\t            \t}  \r\n");
      out.write("\t\t\t              }\r\n");
      out.write("\t\t\t              ] ]\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t// 渲染姓名\r\n");
      out.write("\t\t$(\"#acceptTaskForm input[name='name']\").val($row.name);\r\n");
      out.write("\t\t// 渲染身份证号\r\n");
      out.write("\t\t$(\"#acceptTaskForm input[name='idNo']\").val($row.idNo);\r\n");
      out.write("\r\n");
      out.write("\t\t//查看附件\r\n");
      out.write("\t\t$(\"#checkAttachment\").click(function(){\r\n");
      out.write("\t\t\tcheckAttachementDetail('noauditId',$row.loanOrderId,'');\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//上传附件\r\n");
      out.write("\t\t$(\"#upploadAttachment\").click(function(){\r\n");
      out.write("\t\t\tvar attType = $(\"#attType\").combobox(\"getValue\");\r\n");
      out.write("\t\t\tfileUploadsDlg(attType,$row.loanOrderId);\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//获取附件类型combobox,并将数据保存 \r\n");
      out.write("\t\t$(\"#attType\").combobox({\r\n");
      out.write("\t\t\tvalueField : 'code',\r\n");
      out.write("\t\t\ttextField : 'text',\r\n");
      out.write("\t\t\turl:'common/commonAction!findTextArr.action?codeMyid=attachment_type',\r\n");
      out.write("\t\t\tonLoadSuccess : function(){\r\n");
      out.write("\t\t\t\tvar val = $(this).combobox(\"getData\");\r\n");
      out.write("\t\t\t\tif(!$.isEmptyObject(val)){\r\n");
      out.write("                    $(this).combobox(\"select\", val[0][\"code\"]);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\teditable:false \r\n");
      out.write("\t    });\r\n");
      out.write("\t});\r\n");
      out.write(" \r\n");
      out.write("\t// 查看详情附件\r\n");
      out.write("\tfunction lookAttachment(index){\r\n");
      out.write("\t\tvar row = getRowData($datagrid,index);\r\n");
      out.write("\t\t// 附件信息\r\n");
      out.write("\t\tcheckAttachementDetail('noauditId',$row.loanOrderId,row.assignee,'2');\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t// 完成任务,弹出问题及答疑汇总\r\n");
      out.write("\tfunction  submitThroughTask(result) {\r\n");
      out.write("\t\t// 验证备注信息是否已经填写\r\n");
      out.write("\t\tif(result!=\"IPCInitialAuditThrough\") {\r\n");
      out.write("\t\t\tif($(\"#comment\").val()==\"\" || $(\"#title\").val() ==\"\"){\r\n");
      out.write("\t\t\t\t$.messager.alert(\"提示\",\"请填写完备注信息后再进行提交!\",\"warning\")\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t} \r\n");
      out.write("\t\t$result = result;\r\n");
      out.write("\t\t$quesDlg = $(\"<div></div>\").dialog({\r\n");
      out.write("\t\t\ttitle : \"问题及答疑汇总\",\r\n");
      out.write("\t\t\twidth : 900,\r\n");
      out.write("\t\t\theight : $(window).height()*0.8,\r\n");
      out.write("\t\t\tclosed : false,\r\n");
      out.write("\t\t\tcloseable : true,\r\n");
      out.write("\t\t\tmodal : true,\r\n");
      out.write("\t\t\tonClose : function(){\r\n");
      out.write("\t\t\t\t$(this).dialog(\"destroy\");\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\thref : \"jsp/loanOrder/IPC/initAuditQuestionsCollect.jsp\"\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t// 完成任务,驳回到电核部主管\r\n");
      out.write("\tfunction submitRejectTask(result){\r\n");
      out.write("\t\t// 验证备注信息是否已经填写\r\n");
      out.write("\t\tif(result!=\"IPCInitialAuditThrough\") {\r\n");
      out.write("\t\t\tif($(\"#comment\").val()==\"\" || $(\"#title\").val() ==\"\"){\r\n");
      out.write("\t\t\t\t$.messager.alert(\"提示\",\"请填写完备注信息后再进行提交!\",\"warning\")\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t} \r\n");
      out.write("\t\tvar data = {\r\n");
      out.write("\t\t\t\"comment\" : $(\"#comment\").val(),\r\n");
      out.write("\t\t\t\"title\" :$(\"#title\").val(),\r\n");
      out.write("\t\t\t\"result\" : result,\r\n");
      out.write("\t\t\t\"loanOrderId\" : $row.loanOrderId,\r\n");
      out.write("\t\t\t\"taskId\" :$row.taskId,\r\n");
      out.write("\t\t\t\"processingResult\":$result==\"IPCInitialAuditThrough\"?\"A\":\"B\"\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\ttype : \"POST\",\r\n");
      out.write("\t\t\turl : \"loanOrder/loanOrderAction!submitTask.action\",\r\n");
      out.write("\t\t\tdata : data,\r\n");
      out.write("\t\t\tsuccess : function(msg) {\r\n");
      out.write("\t\t\t\t$grid.datagrid('reload');\r\n");
      out.write("\t\t\t\t$taskFormDialog.dialog('close');\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\t//填写信审报告\r\n");
      out.write("\tfunction applicationReport(){\r\n");
      out.write("\t\t$(\"<div></div>\").dialog({\r\n");
      out.write("\t\t\ttitle : '信审报告',\r\n");
      out.write("\t\t\twidth : 900,\r\n");
      out.write("\t\t\theight : $(window).height()*0.8,\r\n");
      out.write("\t\t\tmodal:true,\r\n");
      out.write("\t\t\thref : \"jsp/loanOrder/IPC/ipcApplicationReportTbs.jsp?t=\"+new Date(),\r\n");
      out.write("\t\t\tonClose:function(){\r\n");
      out.write("\t\t\t\t$(this).dialog(\"destroy\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}); \r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t//填写稽核信息记录表\r\n");
      out.write("\tfunction ipcAuditInfoRecord(){\r\n");
      out.write("\t\t$(\"<div></div>\").dialog({\r\n");
      out.write("\t\t\ttitle : '稽核信息记录表',\r\n");
      out.write("\t\t\twidth : 900,\r\n");
      out.write("\t\t\theight : $(window).height()*0.8,\r\n");
      out.write("\t\t\tmodal:true,\r\n");
      out.write("\t\t\thref : \"jsp/loanOrder/IPC/ipcAuditInfoRecordTbs.jsp?t=\"+new Date(),\r\n");
      out.write("\t\t\tonClose:function(){\r\n");
      out.write("\t\t\t\t$(this).dialog(\"destroy\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}); \r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("<!-- 受理任务 S -->\r\n");
      out.write("<div data-options=\"region:'north',title:'North Title',split:true\" style=\"overflow: hidden;\">\r\n");
      out.write("\t<div style=\"width: 900px;height: 190px;overflow: auto;\">\r\n");
      out.write("\t\t<form id=\"acceptTaskForm\" method=\"post\">\r\n");
      out.write("\t\t\t\t<input name=\"id\" id=\"id\"  type=\"hidden\"/>\r\n");
      out.write("\t\t\t\t<input name=\"auditId\" type=\"hidden\" value=\"noauditId\"/>\r\n");
      out.write("\t\t\t\t <table class=\"table\" cellpadding=\"5px;\">\r\n");
      out.write("\t\t\t\t\t <tr>\r\n");
      out.write("\t\t\t\t\t    <th>客户姓名:</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"name\" readonly=\"readonly\" type=\"text\"/></td>\r\n");
      out.write("\t\t\t\t\t\t<th>身份证号:</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"idNo\" readonly=\"readonly\" type=\"text\"/></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t \t<th>备注简述:</th>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input id=\"title\" name=\"title\" class=\"easyui-validatebox easyui-textbox\" style=\"border: 1px solid #DDDDDD;\">\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t \t<th>备注详情</th>\r\n");
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
      out.write("\t\r\n");
      out.write("\t<div style=\"width: 900px;height:30px;\">\r\n");
      out.write("\t\t<!-- <a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"ipcAuditInfoRecord()\">填写稽核信息</a> -->\r\n");
      out.write("\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"applicationReport()\">填写信审报告</a>\r\n");
      out.write("\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"submitThroughTask('IPCInitialAuditThrough');\" >电核专员通过</a>\r\n");
      out.write("\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"submitRejectTask('IPCInitialAuditReject');\" >电核专员驳回</a>\t\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- 附件和备注信息列表 -->\r\n");
      out.write("\t<div id=\"lookInfo\" class=\"easyui-accordion\" style=\"height: 390px;width: 900px;overflow: hidden;\">\r\n");
      out.write("\t    <div title=\"备注信息\" data-options=\"iconCls:'icon-cstbase',selected:true\">   \r\n");
      out.write("\t\t\t<table id=\"lookLoanOrderdg\" title=\"申请备注的信息\" style=\"overflow: auto;\"></table>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- 受理任务E -->\t\t");
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

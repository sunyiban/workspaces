/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2015-10-08 01:29:09 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.layout;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class script_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\t\r\n");
      out.write("\t");

		String easyuiThemeName="metro";
		Cookie cookies[] =request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for(Cookie cookie : cookies){
				if (cookie.getName().equals("cookiesColor")) {
					easyuiThemeName = cookie.getValue();
					break;
				}
			}
		}
	
      out.write("\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"themes/");
      out.print(easyuiThemeName );
      out.write("/easyui.css\">\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/xheditor/jquery-1.8.0.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/xheditor/xheditor-1.1.14-zh-cn.min.js\" charset=\"utf-8\"></script>\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"themes/icon.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/common.css\">\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/jquery.easyui.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/easyui-lang-zh_CN.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/jqueryUtil.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/json2.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/ajaxfileupload.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/FusionCharts/FusionCharts.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/FusionCharts/FusionCharts.jqueryplugin.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/FusionCharts/FusionChartsExportComponent.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/validate.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/common.js\"></script>\t\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/WdatePicker/WdatePicker.js\"></script>\t\r\n");
      out.write("\t<style type=\"text/css\">\r\n");
      out.write("\t\t.linkSpan{\r\n");
      out.write("\t\t  padding:5px;\r\n");
      out.write("\t\t  display:-moz-inline-box;\r\n");
      out.write("\t\t  display:inline-block;\r\n");
      out.write("\t\t  width:40%; \r\n");
      out.write("\t\t  text-align: center;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t.linkSpanS{\r\n");
      out.write("\t\t  padding:5px;\r\n");
      out.write("\t\t  display:-moz-inline-box;\r\n");
      out.write("\t\t  display:inline-block;\r\n");
      out.write("\t\t  width:10%; \r\n");
      out.write("\t\t  text-align: center;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\ta{text-decoration: none;}\r\n");
      out.write("\t\ta:hover {\r\n");
      out.write("\t\t color: #FF0000;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t</style>\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\tvar attempData;\r\n");
      out.write("\t\t$(function(){\r\n");
      out.write("               $.ajax({\r\n");
      out.write("            \t   url:\"common/commonAction!findTextArr.action?codeMyid=attachment_type\",\r\n");
      out.write("            \t   type:\"post\",\r\n");
      out.write("            \t   success:function(data){\r\n");
      out.write("            \t\t   attempData = data;           \t\t   \r\n");
      out.write("            \t   }\r\n");
      out.write("               });\r\n");
      out.write("            });\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tvar j = 0;\r\n");
      out.write("\t\t//文件上传对话框  连个参数：附件类型，订单ID\r\n");
      out.write("\t\tfunction fileUploadsDlg(attType,loanOrderId,auditId){\r\n");
      out.write("\t\t\tif(attType==\"\"){\r\n");
      out.write("\t\t\t\t$.messager.alert(\"提示\",\"请先选择附件类型!\",\"info\");\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif(undefined==auditId){\r\n");
      out.write("\t\t\t\tauditId = '';\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\twindow.open('jsp/loanOrder/loanOrderAttachmentForm.jsp?loanOrderId='+loanOrderId+'&attType='+attType+'&auditId='+auditId,\r\n");
      out.write("\t\t\t\t\t\"附件详情\");\r\n");
      out.write("\t\t\t//, \"height=\"+$(window).height()*0.95+\", width=\"+$(window).width()*0.95+\", top=100, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no\"\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//附件详情对话框 四个参数 ：稽核信息ID，订单ID，用户ID，是否是详情 1\r\n");
      out.write("\t\tfunction checkAttachementDetail(auditId,loanOrderId,userId,isDetail){\r\n");
      out.write("\t\t\tif(undefined==userId){\r\n");
      out.write("\t\t\t\tuserId = '';\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif(undefined==isDetail){\r\n");
      out.write("\t\t\t\tisDetail=='';\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\twindow.open('jsp/loanOrder/attachementDetail.jsp?auditId='+auditId+'&loanOrderId='+loanOrderId+'&userId='+userId+'&isDetail='+isDetail,\r\n");
      out.write("\t\t\t\t\t\"附件详情\");\r\n");
      out.write("\t\t\t//, \"height=\"+$(window).height()+\", width=\"+$(window).width()*0.95+\", top=100, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no\"\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//附件详情对话框 四个参数 ：稽核信息ID，订单ID，用户ID，是否是详情 1\r\n");
      out.write("\t\tfunction checkAttachementDetail4InvestOrder(auditId,investOrderId,userId,isDetail){\r\n");
      out.write("\t\t\tif(undefined==userId){\r\n");
      out.write("\t\t\t\tuserId = '';\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif(undefined==isDetail){\r\n");
      out.write("\t\t\t\tisDetail=='';\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\twindow.open('jsp/investOrder/attachementDetail.jsp?auditId='+auditId+'&investOrderId='+investOrderId+'&userId='+userId+'&isDetail='+isDetail,\r\n");
      out.write("\t\t\t\t\t\"附件详情\");\r\n");
      out.write("\t\t\t//, \"height=\"+$(window).height()+\", width=\"+$(window).width()*0.95+\", top=100, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no\"\r\n");
      out.write("\t\t}\t\t\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t// 下载附件（投资业务相关的）\r\n");
      out.write("\t\tfunction downloadAttachment4InvestOrder(attId){\r\n");
      out.write("\t\t\tdownFileByFormPost(\"attachment/attachmentAction!downloadAttachment4InvestOrder.action\", {\"attId\":attId});\r\n");
      out.write("\t\t}\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//删除一条附件（投资业务相关的）\r\n");
      out.write("\t\tfunction deleteAttachment4InvestOrder(obj,attId){\r\n");
      out.write("\t\t\t$.messager.confirm('提示','确认删除此项?',function(r){   \r\n");
      out.write("\t\t\t    if (r){   \r\n");
      out.write("\t\t\t    \t$.ajax({\r\n");
      out.write("\t\t\t\t    \t   url:'attachment/attachmentAction!delAttachment.action',\r\n");
      out.write("\t\t\t\t    \t   data:{\"attId\":attId},\r\n");
      out.write("\t\t\t\t    \t   type:\"post\",\r\n");
      out.write("\t\t\t\t    \t   success:function(data){\r\n");
      out.write("\t\t\t\t    \t\t   $.messager.alert(\"提示\",\"删除成功!\");\r\n");
      out.write("\t\t\t\t    \t\t   var pDiv = $(obj).parent().parent().children();\r\n");
      out.write("\t\t\t\t    \t\t   if(pDiv.length==3){\r\n");
      out.write("\t\t\t\t    \t\t\t   $(obj).parent().parent().empty();\r\n");
      out.write("\t\t\t\t    \t\t   }else if(pDiv.length==4){\r\n");
      out.write("\t\t\t\t    \t\t\t   $(obj).parent().parent().children(\"div:eq(1)\").empty();\r\n");
      out.write("\t\t\t\t    \t\t\t   $(obj).parent().remove();\r\n");
      out.write("\t\t\t\t    \t\t   }else{\r\n");
      out.write("\t\t\t\t    \t\t\t   $(obj).parent().remove();\r\n");
      out.write("\t\t\t\t    \t\t   }\r\n");
      out.write("\t\t\t\t    \t\t  \r\n");
      out.write("\t\t\t\t    \t   },\r\n");
      out.write("\t\t\t\t    \t   error:function(){\r\n");
      out.write("\t\t\t\t    \t\t   $.messager.alert(\"提示\",\"删除失败!\");\r\n");
      out.write("\t\t\t\t    \t   }\r\n");
      out.write("\t\t\t\t       });\r\n");
      out.write("\t\t\t    }   \r\n");
      out.write("\t\t\t});  \r\n");
      out.write("\t\t}\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t</script>\r\n");
      out.write("\t<style type=\"text/css\">\r\n");
      out.write("\t\tbody {\r\n");
      out.write("\t\t    font-family:helvetica,tahoma,verdana,sans-serif;\r\n");
      out.write("\t\t    font-size:13px;\r\n");
      out.write("\t\t    margin:0px 0px 0px 0px;\r\n");
      out.write("\t\t    padding:0px 0px 0px 0px;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t</style>");
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

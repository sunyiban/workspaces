/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.21
 * Generated at: 2015-07-14 02:09:09 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.layout;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class north_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_classes = null;
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

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

      out.write("\r\n");
      out.write("<script type=\"text/javascript\" charset=\"utf-8\">\r\n");
      out.write("\tfunction logout(b) {\r\n");
      out.write("\t\t/*$.post('systemAction!logout.action', function() {\r\n");
      out.write("\t\t\tif (b) {\r\n");
      out.write("\t\t\t\tif (jqueryUtil.isLessThanIe8()) {\r\n");
      out.write("\t\t\t\t\tloginAndRegDialog.dialog('open');\r\n");
      out.write("\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\tlocation.replace('login.jsp');\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t} else {\r\n");
      out.write("\t\t\t\tloginAndRegDialog.dialog('open');\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});*/\r\n");
      out.write("\t\t$.messager.confirm(\"提示\", \"确认退出吗?\",function(r){\r\n");
      out.write("\t\t\tif(r){\r\n");
      out.write("\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\tasync : false,\r\n");
      out.write("\t\t\t\t\tcache : false,\r\n");
      out.write("\t\t\t\t\ttype : \"POST\",\r\n");
      out.write("\t\t\t\t\turl : \"systemAction!logout.action\",\r\n");
      out.write("\t\t\t\t\terror : function() {\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\tsuccess : function(json) {\r\n");
      out.write("\t\t\t\t\t\tlocation.replace(\"login.jsp\");\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tvar userInfoWindow;\r\n");
      out.write("\tfunction showUserInfo() {\r\n");
      out.write("\t\tuserInfoWindow = $('<div/>').window({\r\n");
      out.write("\t\t\tmodal : true,\r\n");
      out.write("\t\t\ttitle : '当前用户信息',\r\n");
      out.write("\t\t\twidth : 350,\r\n");
      out.write("\t\t\theight : 300,\r\n");
      out.write("\t\t\tcollapsible : false,\r\n");
      out.write("\t\t\tminimizable : false,\r\n");
      out.write("\t\t\tmaximizable : false,\r\n");
      out.write("\t\t\t//href : 'userAction!showUserInfo.action',\r\n");
      out.write("\t\t\tonClose : function() {\r\n");
      out.write("\t\t\t\t$(this).window('destroy');\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("<div style=\"position: absolute; right: 0px; bottom: 0px; \">\r\n");
      out.write("\t<a href=\"javascript:void(0);\" class=\"easyui-menubutton\" menu=\"#layout_north_kzmbMenu\" iconCls=\"icon-help\">控制面板</a> \r\n");
      out.write("\t<a href=\"javascript:void(0);\" class=\"easyui-menubutton\" menu=\"#layout_north_zxMenu\" iconCls=\"icon-logout\">注销</a>\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"layout_north_kzmbMenu\" style=\"width: 100px; display: none;\">\r\n");
      out.write("\t<div onclick=\"showUserInfo();\">个人信息</div>\r\n");
      out.write("\t<div class=\"menu-sep\"></div>\r\n");
      out.write("\t<div>\r\n");
      out.write("\t\t<span>更换主题</span>\r\n");
      out.write("\t\t<div style=\"width: 120px;\">\r\n");
      out.write("\t\t\t<div onclick=\"changeTheme('default');\">default</div>\r\n");
      out.write("\t\t\t<div onclick=\"changeTheme('gray');\">gray</div>\r\n");
      out.write("\t\t\t<div onclick=\"changeTheme('cupertino');\">cupertino</div>\r\n");
      out.write("\t\t\t<div onclick=\"changeTheme('dark-hive');\">dark-hive</div>\r\n");
      out.write("\t\t\t<div onclick=\"changeTheme('pepper-grinder');\">pepper-grinder</div>\r\n");
      out.write("\t\t\t<div onclick=\"changeTheme('sunny');\">sunny</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"layout_north_zxMenu\" style=\"width: 100px; display: none;\">\r\n");
      out.write("\t<div onclick=\"loginAndRegDialog.dialog('open');\">锁定窗口</div>\r\n");
      out.write("\t<div class=\"menu-sep\"></div>\r\n");
      out.write("\t<div onclick=\"logout();\">重新登录</div>\r\n");
      out.write("\t<div onclick=\"logout(true);\">退出系统</div>\r\n");
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

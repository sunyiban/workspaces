/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2016-02-29 02:04:06 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.layout;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.bpms.util.Constants;
import com.bpms.shiro.ShiroUser;
import java.util.*;

public final class north_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	ShiroUser user = Constants.getCurrendUser();

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
      out.write("\t\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\tstartTime();\r\n");
      out.write("\t})\r\n");
      out.write("function startTime(){  \r\n");
      out.write("  var today=new Date()  \r\n");
      out.write("  var week=new Array(\"星期日\",\"星期一\",\"星期二\",\"星期三\",\"星期四\",\"星期五\",\"星期六\");  \r\n");
      out.write("  var year=today.getYear()+1900  \r\n");
      out.write("  var month=today.getMonth()+1  \r\n");
      out.write("  var date=today.getDate()  \r\n");
      out.write("  var day=today.getDay()  \r\n");
      out.write("  var h=today.getHours()  \r\n");
      out.write("  var m=today.getMinutes()  \r\n");
      out.write("  var s=today.getSeconds()  \r\n");
      out.write("  // add a zero in front of numbers<10  \r\n");
      out.write("  h=checkTime(h)  \r\n");
      out.write("  m=checkTime(m)  \r\n");
      out.write("  s=checkTime(s)  \r\n");
      out.write("  document.getElementById('time').innerHTML=\" \"+year+\"年\"+month+\"月\"+date+\"日  \"+week[day]+\"  \"+h+\":\"+m+\":\"+s+\" \"  \r\n");
      out.write("  t=setTimeout('startTime()',500)  \r\n");
      out.write(" }  \r\n");
      out.write("  \r\n");
      out.write(" function checkTime(i){  \r\n");
      out.write(" if (i<10)   \r\n");
      out.write("   {i=\"0\" + i}  \r\n");
      out.write("   return i  \r\n");
      out.write(" }  \r\n");
      out.write("</script>\r\n");
      out.write("<style>\r\n");
      out.write(".frame_head{ width:100%;height:71px; background:url(extend/head_bg.jpg) repeat-x; overflow:hidden;}\r\n");
      out.write(".frame_head_bg{width:355px;height:61px;background:url(extend/head_right.jpg) right top  no-repeat; float:right;}\r\n");
      out.write(".frame_head_admin{float:left;width:1300px;height:61px;/* background:url(extend/logo.png)  no-repeat; */ overflow:hidden;}\r\n");
      out.write(".frame_head_admin_box{ height:24px;font-size:12px; margin:33px 980px; line-height:24px; background:#fff;opacity: 0.7;filter: progid:DXImageTransform.Microsoft.Alpha(opacity=70); -ms-filter: \"progid:DXImageTransform.Microsoft.Alpha(opacity=70)\";}\r\n");
      out.write(".admin_left{ background:url(extend/admin_left.gif) no-repeat; width:7px;}\r\n");
      out.write(".admin_right{ background:url(extend/admin_right.gif) no-repeat; width:7px;}\r\n");
      out.write(".admin_icon{ width:28px; height:24px; background:url(extend/admin_icon.gif) no-repeat;}\r\n");
      out.write(".admin_padding{ padding:0 10px;}\r\n");
      out.write(".admin_font{ font-weight:bold;}\r\n");
      out.write("</style>\r\n");
      out.write("    <div class=\"frame_head\">\r\n");
      out.write("    \t<div class=\"frame_head_bg\"></div>\r\n");
      out.write("\t\t<span style=\"position: absolute;left:250px; top: 23px; font: 30px bold;color: white;\">钱钱金融风控管理系统</span>\r\n");
      out.write("    \t<div class=\"frame_head_admin\">\r\n");
      out.write("        \t<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" class=\"frame_head_admin_box\" style=\"width: 100%\">\r\n");
      out.write("            \t<tr>\r\n");
      out.write("                    <td width=\"7\" class=\"admin_left\"></td>\r\n");
      out.write("                    <td width=\"28\" class=\"admin_icon\"></td>\r\n");
      out.write("                \t<td >欢迎您:<span class=\"admin_font\" style=\"margin-right: 10px;\">");
      out.print(user.getUser().getName() );
      out.write("</span><span id=\"time\"></span></td>\r\n");
      out.write("                    <td ></td>\r\n");
      out.write("                    <td width=\"7\" class=\"admin_right\"></td>\r\n");
      out.write("                </tr>\r\n");
      out.write("            </table>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
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

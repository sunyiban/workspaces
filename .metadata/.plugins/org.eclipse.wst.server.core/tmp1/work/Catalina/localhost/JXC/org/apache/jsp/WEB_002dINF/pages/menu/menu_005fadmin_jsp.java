/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.21
 * Generated at: 2015-09-22 06:43:47 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.pages.menu;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class menu_005fadmin_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("jar:file:/E:/workspaces/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/JXC/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153356282000L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1427683825107L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
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
      response.setContentType("text/html; charset=UTF-8");
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("\t<meta charset=\"UTF-8\">\r\n");
      out.write("\t<title>登陆首页</title>\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"media/css/easyui.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"media/css/icon.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"media/css/demo.css\">\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"media/js/jquery.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"media/js/jquery.easyui.min.js\"></script>\r\n");
      out.write("\t<style type=\"text/css\">\r\n");
      out.write("\t\ta:link,a:visited {color: blue; text-decoration:none;font-size:16px;} //未访问：蓝色、无下划线   \r\n");
      out.write("\t\t/* a:VISITED{color: blue; text-decoration:none;font-size:16px;}//点击后样式不变 */\r\n");
      out.write("\t\t.divstyle{\r\n");
      out.write("\t\t\tbackgroundColor:\"rgb(226,224,200)\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\ta div{\r\n");
      out.write("\t\t\tfont-size:14px;\r\n");
      out.write("\t\t\twidth:100%;\r\n");
      out.write("\t\t\theight: 20px;\r\n");
      out.write("\t\t\ttext-align:center;\r\n");
      out.write("\t\t\tpadding-top: 4px;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"easyui-layout\" style=\"background-color: #F8F8FF;overflow: auto;\">\r\n");
      out.write("\t\t\t<div style=\"width: 100%;font-size: 16px;border: thin;\">现场订单</div>\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('order','add');\"><div id=\"orderadd\" onmouseenter=\"enter('orderadd')\" onmouseleave=\"leave('orderadd')\" >设备订购(上海柜台)</div></a>\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('order','otherCounterAdd');\"><div id=\"orderOtheradd\" onmouseenter=\"enter('orderOtheradd')\" onmouseleave=\"leave('orderOtheradd')\" >设备订购(其他柜台)</div></a>\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('order','search');\"><div id=\"searchorder\" onmouseenter=\"enter('searchorder')\" onmouseleave=\"leave('searchorder')\"  >设备退还</div></a>\r\n");
      out.write("\t\t\t<a a href=\"#\" onclick=\"clickutil('order','list');\"><div id=\"orderlist\" onmouseenter=\"enter('orderlist')\" onmouseleave=\"leave('orderlist')\" >订单管理</div></a>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<div style=\"width: 100%;font-size: 16px;border: thin;\">订单管理</div>\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('wxorder','add');\"><div id=\"wxorderadd\" onmouseenter=\"enter('wxorderadd')\" onmouseleave=\"leave('wxorderadd')\" >设备预订</div></a>\r\n");
      out.write("\t\t\t<a a href=\"#\" onclick=\"clickutil('wxorder','list');\"><div id=\"wxorderlist\" onmouseenter=\"enter('wxorderlist')\" onmouseleave=\"leave('wxorderlist')\" >预订单管理</div></a>\r\n");
      out.write("\t\t\t<a a href=\"#\" onclick=\"clickutil('jcborder','list');\"><div id=\"jcborderlist\" onmouseenter=\"enter('jcborderlist')\" onmouseleave=\"leave('jcborderlist')\" >网站订单</div></a>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<div style=\"width: 100%;font-size: 16px;border: thin;\">设备管理</div>\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('equipment','getAll');\"><div id=\"equipmentlist\" onmouseenter=\"enter('equipmentlist')\" onmouseleave=\"leave('equipmentlist')\"  >设备列表</div></a>\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('equipment','add');\"><div id=\"equipmentadd\" onmouseenter=\"enter('equipmentadd')\" onmouseleave=\"leave('equipmentadd')\"  >添加设备</div></a>\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('equipment','instorage');\"><div id=\"equipmentinstorage\" onmouseenter=\"enter('equipmentinstorage')\" onmouseleave=\"leave('equipmentinstorage')\"  >设备入库</div></a>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<div style=\"width: 100%;font-size: 16px;border: thin;\">库存盘点</div>\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('equipment','inventorymainadd');\"><div id=\"equipmentinventory_add\" onmouseenter=\"enter('equipmentinventory_add')\" onmouseleave=\"leave('equipmentinventory_add')\"  >新增盘点</div></a>\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('equipment','inventorymainlist');\"><div id=\"equipmentinventory_list\" onmouseenter=\"enter('equipmentinventory_list')\" onmouseleave=\"leave('equipmentinventory_list')\"  >盘点管理</div></a>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<div style=\"width: 100%;font-size: 16px;border: thin;\">数据卡管理</div>\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('info','list');\"><div id=\"infolist\" onmouseenter=\"enter('infolist')\" onmouseleave=\"leave('infolist')\"  >数据卡列表</div></a>\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('info','add');\"><div id=\"infoadd\" onmouseenter=\"enter('infoadd')\" onmouseleave=\"leave('infoadd')\"  >添加数据卡</div></a>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<div style=\"width: 100%;font-size: 16px;border: thin;\">系统管理</div>\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('user','add');\"><div id=\"useradd\" onmouseenter=\"enter('useradd')\" onmouseleave=\"leave('useradd')\"  >新增用户</div></a>\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('user','discountday');\"><div id=\"userdiscountday\" onmouseenter=\"enter('userdiscountday')\" onmouseleave=\"leave('userdiscountday')\"  >优惠天数</div></a>\r\n");
      out.write("\t\t\t<!-- <a href=\"#\" onclick=\"clickutil('user','managephoto');\"><div id=\"managephoto\" onmouseenter=\"enter('managephoto')\" onmouseleave=\"leave('managephoto')\"  >经营图表</div></a> -->\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('user','ordertabel');\"><div id=\"ordertabel\" onmouseenter=\"enter('ordertabel')\" onmouseleave=\"leave('ordertabel')\"  >订单报表</div></a>\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('user','daymanageinfo');\"><div id=\"daymanageinfo\" onmouseenter=\"enter('daymanageinfo')\" onmouseleave=\"leave('daymanageinfo')\"  >日经营情况</div></a>\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('dictionarycode','list');\"><div id=\"codelist\" onmouseenter=\"enter('codelist')\" onmouseleave=\"leave('codelist')\"  >字典管理</div></a>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<div style=\"width: 100%;font-size: 16px;border: thin;\">经营图表</div>\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('order','queryOrderDetail');\"><div id=\"orderqueryOrderDetail\" onmouseenter=\"enter('orderqueryOrderDetail')\" onmouseleave=\"leave('orderqueryOrderDetail')\"  >订单统计</div></a>\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('user','passengerdetail');\"><div id=\"passengerdetail\" onmouseenter=\"enter('passengerdetail')\" onmouseleave=\"leave('passengerdetail')\"  >旅客明细</div></a>\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('user','billlist');\"><div id=\"billlist\" onmouseenter=\"enter('billlist')\" onmouseleave=\"leave('billlist')\"  >移动WIFI月账单</div></a>\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('user','dayInOutProportion');\"><div id=\"dayInOutProportion\" onmouseenter=\"enter('dayInOutProportion')\" onmouseleave=\"leave('dayInOutProportion')\"  >收支对比图</div></a>\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('user','dayInAndOut');\"><div id=\"dayInAndOut\" onmouseenter=\"enter('dayInAndOut')\" onmouseleave=\"leave('dayInAndOut')\"  >日收支统计图</div></a>\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('user','everyDayOrderNums');\"><div id=\"everyDayOrderNums\" onmouseenter=\"enter('everyDayOrderNums')\" onmouseleave=\"leave('everyDayOrderNums')\"  >日订单数量图</div></a>\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('user','numbersOfOrdersPie');\"><div id=\"numbersOfOrdersPie\" onmouseenter=\"enter('numbersOfOrdersPie')\" onmouseleave=\"leave('numbersOfOrdersPie')\"  >各国订单数量图</div></a>\r\n");
      out.write("\t\r\n");
      out.write("\t\t\t<div style=\"width: 100%;font-size: 16px;border: thin;\">常用信息</div>\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('wxorder','pricelist');\"><div id=\"wxorderpricelist\" onmouseenter=\"enter('wxorderpricelist')\" onmouseleave=\"leave('wxorderpricelist')\"  >价目表</div></a>\r\n");
      out.write("\t\t\t<!-- <a href=\"#\" onclick=\"clickutil('wxorder','counterdetail');\"><div id=\"wxcounterdetail\" onmouseenter=\"enter('wxcounterdetail')\" onmouseleave=\"leave('wxcounterdetail')\"  >柜台详情</div></a> -->\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('wxorder','pordernum');\"><div id=\"wxpordernum\" onmouseenter=\"enter('wxpordernum')\" onmouseleave=\"leave('wxpordernum')\"  >个人订单量</div></a>\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('wxorder','preordernum');\"><div id=\"wxpreordernum\" onmouseenter=\"enter('wxpreordernum')\" onmouseleave=\"leave('wxpreordernum')\"  >预定订单量</div></a>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<div style=\"width: 100%;font-size: 16px;border: thin;\">权限管理</div>\r\n");
      out.write("\t\t\t<a href=\"#\" onclick=\"clickutil('role','findAllRole');\"><div id=\"role\" onmouseenter=\"enter('findAllRole')\" onmouseleave=\"leave('findAllRole')\"  >角色管理</div></a>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t<div id=\"menu_shadow\" style=\"display: none; z-index: 1000; top: 0px; left: 0px; position: fixed; height: 100%; width: 100%;opacity:0.8; background-color:#708090;\">\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t//=============柜台菜单点击方法 开始===============================\r\n");
      out.write("\t\tfunction clickutil(str,address){\r\n");
      out.write("\t\t\ttop.document.getElementById(\"countents\").src = str+\"/\"+address;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction enter(obj){\r\n");
      out.write("\t\t\t/* alert(obj);\r\n");
      out.write("\t\t\talert(obj.style+\":\"+obj.style.cssText);\r\n");
      out.write("\t\t\t */\r\n");
      out.write("\t\t\tvar objs = document.getElementById(obj);\r\n");
      out.write("\t\t\t/* objs.css(\"backgroundColor\",\"rgb(226,224,200)\"); */\r\n");
      out.write("\t\t\tobjs.style.backgroundColor=\"rgb(226,224,200)\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tfunction leave(obj){\r\n");
      out.write("\t\t\tvar objs = document.getElementById(obj);\r\n");
      out.write("\t\t\tobjs.style.backgroundColor=\"\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("</body>\r\n");
      out.write(" \r\n");
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

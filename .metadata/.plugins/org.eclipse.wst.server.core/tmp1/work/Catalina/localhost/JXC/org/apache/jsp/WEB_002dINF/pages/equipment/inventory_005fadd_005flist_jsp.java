/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.21
 * Generated at: 2015-09-22 07:01:29 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.pages.equipment;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class inventory_005fadd_005flist_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"../media/css/easyui.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"../media/css/icon.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"../media/css/demo.css\">\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"../media/js/WdatePicker.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"../media/js/jquery.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"../media/js/jquery.easyui.min.js\"></script>\r\n");
      out.write("\t<style type=\"text/css\">\r\n");
      out.write("\t\tinput{\r\n");
      out.write("\t\t\tborder: 1px solid #95b8e7;border-radius: 5px;height: 20px;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\ttd{\r\n");
      out.write("\t\t\tfont-size: 16px;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t .container{\r\n");
      out.write("                position:relative;\r\n");
      out.write("        }\r\n");
      out.write("        .search{\r\n");
      out.write("                background-image:url(../media/css/icons/search.png);\r\n");
      out.write("                background-repeat:no-repeat;\r\n");
      out.write("                height: 30px;\r\n");
      out.write("\t\t\t    left: 120px;\r\n");
      out.write("\t\t\t    position: absolute;\r\n");
      out.write("\t\t\t    top: 4px;\r\n");
      out.write("\t\t\t    width: 30px;\r\n");
      out.write("\t\t\t    z-index: 99;\r\n");
      out.write("        }\r\n");
      out.write("      \t\t .file-box{ position:relative;width:340px}\r\n");
      out.write("\t\t\t.txt{ height:22px; border:1px solid #cdcdcd; width:180px;}\r\n");
      out.write("\t\t\t.btn{ background-color:#FFF; border:1px solid #CDCDCD;height:24px; width:70px;}\r\n");
      out.write("\t\t\t.file{ position:absolute; top:0px; right:80px; height:24px; filter:alpha(opacity:0);opacity: 0;width:260px }\r\n");
      out.write("\t</style>\r\n");
      out.write("\t<style>\r\n");
      out.write("\t\t.lightbox{width:300px;background:#FFFFFF;border:1px solid #ccc;line-height:25px; top:20%; left:20%;}\r\n");
      out.write("\t\t.lightbox dt{background:#f4f4f4; padding:5px;}\r\n");
      out.write("\t</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"easyui-layout\" >\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\t/* $(function(){\r\n");
      out.write("\t\t\t\tvar left = document.getElementById(\"sim_id\").getBoundingClientRect().left;\r\n");
      out.write("\t\t\t\tvar top = document.getElementById(\"sim_id\").getBoundingClientRect().top;\r\n");
      out.write("\t\t\t\tvar width = document.getElementById(\"sim_id\").offsetWidth;\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tdocument.getElementById(\"search_img\").style.left = left+width-25+\"px\";\r\n");
      out.write("\t\t\t\tdocument.getElementById(\"search_img\").style.top = top+2+\"px\";\r\n");
      out.write("\t\t\t});  */\r\n");
      out.write("\t</script>\r\n");
      out.write("\t<div data-options=\"region:'center'\" scrolling=\"no\" style=\"overflow:auto;background-color:#F8F8FF;\">\r\n");
      out.write("\t\t<div style=\"background-color:#F8F8FF;align:center;width: 100%;height: 40px;overflow: hidden;\">\r\n");
      out.write("\t\t\t<div align=\"right\" style=\"\">\r\n");
      out.write("\t\t\t\t<a onclick=\"instorage();\" class=\"easyui-linkbutton\" style=\"margin-right: 5px;width: 80px;height: 25px;\">导入</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<textarea id = \"eqptlistjson\" style=\"display: none;\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${eqptlistjson}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</textarea>\r\n");
      out.write("\t\t<table id=\"dg\" style=\"height: 90%;background-color:#F8F8FF\" class=\"easyui-datagrid\"  \r\n");
      out.write("\t\t\t\tdata-options=\"rownumbers:true,pagination:false,singleSelect:true,method:'post',remoteSort:false,multiSort:true,fitColumns:true \">\r\n");
      out.write("\t\t\t\t<thead>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<!-- <th data-options=\"field:'ck',checkbox:true\"></th> -->\r\n");
      out.write("\t\t\t\t<th data-options=\"field:'no',width:120,align:'center'\">设备编号</th>\r\n");
      out.write("\t\t\t\t<th data-options=\"field:'d_country',width:120,align:'center'\">国家</th>\r\n");
      out.write("\t\t\t\t<th data-options=\"field:'equipment_state',width:80,align:'center'\">设备状态</th>\r\n");
      out.write("\t\t\t\t<th data-options=\"field:'remark',width:200,align:'center'\">备注</th>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t</thead>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t\t<div id=\"inportdiv\" modal=\"true\" class=\"easyui-dialog\" style=\"width:400px;height:200px;overflow:hidden;\" closed=\"true\" title=\" \">\r\n");
      out.write("        \t <div class=\"file-box\" style=\"text-align: center;\"><!--  -->\r\n");
      out.write("\t\t\t\t<form id=\"uploadform\"  action=\"inventoryInport\" method=\"post\" enctype=\"multipart/form-data\">\r\n");
      out.write("\t\t\t\t<input type='text' name='textfield' id='textfield' class='txt' />\r\n");
      out.write("\t\t\t\t<input type='button' class='btn' value='浏览...' />\r\n");
      out.write("\t\t\t\t<input type=\"file\" name=\"inventoryfile\" class=\"file\" id=\"fileField\" size=\"28\" onchange=\"document.getElementById('textfield').value=this.value\" />\r\n");
      out.write("\t\t\t\t<input type=\"submit\" name=\"submit\" class=\"btn\" value=\"上传\" />\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\t\t\t </div> \r\n");
      out.write("   \t\t </div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!-- 处理租用日期 返还日期 -->\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t//设置租用日期的初始时间为当前时间\r\n");
      out.write("\t\t$(function(){\r\n");
      out.write("\t\t\t$(\"#create_time\").val(new Date().pattern(\"yyyy/MM/dd\"));\r\n");
      out.write("\t\t\t$(\"#inventory_time\").val(new Date().pattern(\"yyyy/MM/dd hh:mm:ss\"));\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tvar data = $(\"#eqptlistjson\").val();\r\n");
      out.write("\t\t\t//alert(data);\r\n");
      out.write("\t\t\tif(data!=\"\"){\r\n");
      out.write("\t\t\t\tdata = $.parseJSON(data);\r\n");
      out.write("\t\t\t\t$(\"#dg\").datagrid(\"loadData\",data);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\tfunction instorage(){\r\n");
      out.write("\t\t\t$(\"#inportdiv\").dialog(\"open\");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("\t\r\n");
      out.write("\t<script language=\"javascript\" type=\"text/javascript\">  \r\n");
      out.write("\t\t\t   /**     \r\n");
      out.write("\t\t\t\t * 对Date的扩展，将 Date 转化为指定格式的String     \r\n");
      out.write("\t\t\t\t * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符     \r\n");
      out.write("\t\t\t\t * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)     \r\n");
      out.write("\t\t\t\t * eg:     \r\n");
      out.write("\t\t\t\t * (new Date()).pattern(\"yyyy-MM-dd hh:mm:ss.S\") ==> 2006-07-02 08:09:04.423     \r\n");
      out.write("\t\t\t\t * (new Date()).pattern(\"yyyy-MM-dd E HH:mm:ss\") ==> 2009-03-10 二 20:09:04     \r\n");
      out.write("\t\t\t\t * (new Date()).pattern(\"yyyy-MM-dd EE hh:mm:ss\") ==> 2009-03-10 周二 08:09:04     \r\n");
      out.write("\t\t\t\t * (new Date()).pattern(\"yyyy-MM-dd EEE hh:mm:ss\") ==> 2009-03-10 星期二 08:09:04     \r\n");
      out.write("\t\t\t\t * (new Date()).pattern(\"yyyy-M-d h:m:s.S\") ==> 2006-7-2 8:9:4.18     \r\n");
      out.write("\t\t\t\t */       \r\n");
      out.write("\t\t\t\tDate.prototype.pattern=function(fmt) {        \r\n");
      out.write("\t\t\t\t    var o = {        \r\n");
      out.write("\t\t\t\t    \"M+\" : this.getMonth()+1, //月份        \r\n");
      out.write("\t\t\t\t    \"d+\" : this.getDate(), //日        \r\n");
      out.write("\t\t\t\t    \"h+\" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时        \r\n");
      out.write("\t\t\t\t    \"H+\" : this.getHours(), //小时        \r\n");
      out.write("\t\t\t\t    \"m+\" : this.getMinutes(), //分        \r\n");
      out.write("\t\t\t\t    \"s+\" : this.getSeconds(), //秒        \r\n");
      out.write("\t\t\t\t    \"q+\" : Math.floor((this.getMonth()+3)/3), //季度        \r\n");
      out.write("\t\t\t\t    \"S\" : this.getMilliseconds() //毫秒        \r\n");
      out.write("\t\t\t\t    };        \r\n");
      out.write("\t\t\t\t    var week = {        \r\n");
      out.write("\t\t\t\t    \"0\" : \"\\日\",        \r\n");
      out.write("\t\t\t\t    \"1\" : \"\\一\",        \r\n");
      out.write("\t\t\t\t    \"2\" : \"\\二\",        \r\n");
      out.write("\t\t\t\t    \"3\" : \"\\三\",        \r\n");
      out.write("\t\t\t\t    \"4\" : \"\\四\",        \r\n");
      out.write("\t\t\t\t    \"5\" : \"\\五\",        \r\n");
      out.write("\t\t\t\t    \"6\" : \"\\六\"       \r\n");
      out.write("\t\t\t\t    };        \r\n");
      out.write("\t\t\t\t    if(/(y+)/.test(fmt)){        \r\n");
      out.write("\t\t\t\t        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+\"\").substr(4 - RegExp.$1.length));        \r\n");
      out.write("\t\t\t\t    }        \r\n");
      out.write("\t\t\t\t    if(/(E+)/.test(fmt)){        \r\n");
      out.write("\t\t\t\t        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? \"\\星\\期\" : \"\\周\") : \"\")+week[this.getDay()+\"\"]);        \r\n");
      out.write("\t\t\t\t    }        \r\n");
      out.write("\t\t\t\t    for(var k in o){        \r\n");
      out.write("\t\t\t\t        if(new RegExp(\"(\"+ k +\")\").test(fmt)){        \r\n");
      out.write("\t\t\t\t            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : ((\"00\"+ o[k]).substr((\"\"+ o[k]).length)));        \r\n");
      out.write("\t\t\t\t        }        \r\n");
      out.write("\t\t\t\t    }        \r\n");
      out.write("\t\t\t\t    return fmt;        \r\n");
      out.write("\t\t\t\t}      \r\n");
      out.write("\t\t\t\t    \r\n");
      out.write("</script>    \r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("\tfunction submitForm(){\r\n");
      out.write("\t\tif($(\"#forbid\").val()==1){\r\n");
      out.write("\t\t\talert(\"请勿重复提交!\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tvar no = $(\"#no\").val();\r\n");
      out.write("\t\tvar countercode = $(\"#counterid\").val();\r\n");
      out.write("\t\tif(no==\"\"){\r\n");
      out.write("\t\t\talert(\"请输入设备号\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tvar existno = $(\"#existno\").val();\r\n");
      out.write("\t\tif(existno==1){\r\n");
      out.write("\t\t\t$.messager.alert(\"提示\",\"此设备已存在!\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//$(\"#equipment\").submit();\t\t\r\n");
      out.write("\t\t$(\"#forbid\").val(\"1\");\r\n");
      out.write("\t\tjQuery.ajax({\r\n");
      out.write("\t\t\turl:\"add\",\r\n");
      out.write("\t\t\tdata:$(\"#equipment\").serialize(),\r\n");
      out.write("\t\t\ttype:\"post\",\r\n");
      out.write("\t\t\terror:function(){\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tsuccess:function(data){\r\n");
      out.write("\t\t\t\tif(data=='1'){\r\n");
      out.write("\t\t\t\t\talert(\"添加成功\");\r\n");
      out.write("\t\t\t\t\t$(\"#no\").val(\"\");\r\n");
      out.write("\t\t\t\t\t$(\"#remark\").val(\"\");\r\n");
      out.write("\t\t\t\t\t$(\"#forbid\").val(\"2\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("        \r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
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

/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.21
 * Generated at: 2015-06-02 01:53:12 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.emergency_005fcontacter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class emergency_005fcontacterMain_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("jar:file:/E:/workspaces/.metadata/.plugins/org.eclipse.wst.server.core/tmp3/wtpwebapps/qqms/WEB-INF/lib/shiro-all-1.2.1.jar!/META-INF/shiro.tld", Long.valueOf(1343195352000L));
    _jspx_dependants.put("/WEB-INF/lib/shiro-all-1.2.1.jar", Long.valueOf(1431328416631L));
  }

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

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"  \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("    <base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("\t<meta http-equiv=\"pragma\" content=\"no-cache\">\r\n");
      out.write("\t<meta http-equiv=\"cache-control\" content=\"no-cache\">\r\n");
      out.write("\t<meta http-equiv=\"expires\" content=\"0\">    \r\n");
      out.write("\t<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">\r\n");
      out.write("\t<meta http-equiv=\"description\" content=\"This is my page\">\r\n");
      out.write("\t\r\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../layout/script.jsp", out, false);
      out.write("\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\tvar sexArr = jqueryUtil.getTextArr(\"gender_type\");//性别\r\n");
      out.write("\t\t\tvar marriageArr = jqueryUtil.getTextArr(\"marriage_type\");//婚姻状况\r\n");
      out.write("\t\t\tvar idTpyeArr = jqueryUtil.getTextArr(\"id_type\");//证件类型\r\n");
      out.write("\t\t\tvar degreeArr = jqueryUtil.getTextArr(\"degree_type\");//学历\r\n");
      out.write("\t\t\tvar jobTypeArr = jqueryUtil.getTextArr(\"job_type\");//职业类型\r\n");
      out.write("\t\t\tvar companyArr = jqueryUtil.getTextArr(\"company_size\");//单位规模\r\n");
      out.write("\t\t\tvar hasChildArr = jqueryUtil.getTextArr(\"has_child\");//家庭情况\r\n");
      out.write("\t\t\tvar provinceArr = jqueryUtil.getAreaTextArr(\"zhongguo\");//获取省\r\n");
      out.write("\t\t\tvar $dg;\r\n");
      out.write("\t\t\tvar $grid;\r\n");
      out.write("\t\t\t$(function() {\r\n");
      out.write("\t\t\t\t $dg = $(\"#dg\");\r\n");
      out.write("\t\t\t\t $grid=$dg.datagrid({\r\n");
      out.write("\t\t\t\t\turl : \"emergencycontacter/emergencyContacterAction!findEmergencyContacterAllList.action\",\r\n");
      out.write("\t\t\t\t\twidth : 'auto',\r\n");
      out.write("\t\t\t\t\theight : $(this).height()-85,\r\n");
      out.write("\t\t\t\t\tpagination:true,\r\n");
      out.write("\t\t\t\t\trownumbers:true,\r\n");
      out.write("\t\t\t\t\tborder:true,\r\n");
      out.write("\t\t\t\t\tsingleSelect:true,\r\n");
      out.write("\t\t\t\t\tnowrap:true,\r\n");
      out.write("\t\t\t\t\tmultiSort:false,\r\n");
      out.write("\t\t\t\t\tonDblClickRow:checkDetail,\r\n");
      out.write("\t\t\t\t\tcolumns : [ [ {field : 'name',title : '姓名',width : parseInt($(this).width()*0.05)},\r\n");
      out.write("\t\t\t\t\t              {field : 'genderType',title : '性别',width : parseInt($(this).width()*0.03),\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tformatter:function(value,row,index){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t return jqueryUtil.showText(value,sexArr);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t  },\r\n");
      out.write("\t\t\t\t\t              {field : 'birthday',title : '出生日期',width : parseInt($(this).width()*0.1),sortable:true},\r\n");
      out.write("\t\t\t\t\t              {field : 'idType',title : '证件类型',width : parseInt($(this).width()*0.1),\r\n");
      out.write("\t\t\t\t\t            \t  formatter:function(value,row,index){\r\n");
      out.write("\t\t\t\t\t            \t\t  return jqueryUtil.showText(value,idTpyeArr);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t}  \r\n");
      out.write("\t\t\t\t\t              },\r\n");
      out.write("\t\t\t\t\t              {field : 'idNo',title : '证件号',width : parseInt($(this).width()*0.12),align : 'left'},\r\n");
      out.write("\t\t\t\t\t              {field : 'tel',title : '手机号',width :parseInt($(this).width()*0.1),align : 'left'},\r\n");
      out.write("\t\t\t\t\t              {field : 'fixedTel',title : '固定电话',width :parseInt($(this).width()*0.1),align : 'left'},\r\n");
      out.write("\t\t\t\t\t              {field : 'email',title : '邮箱',width : parseInt($(this).width()*0.1),align : 'left'},\r\n");
      out.write("\t\t\t\t\t              {field : 'relationship',title : '关系',width : parseInt($(this).width()*0.05),align : 'left'},\r\n");
      out.write("\t\t\t\t\t              {field : 'province',title : '省',width : parseInt($(this).width()*0.03),align : 'left',\r\n");
      out.write("\t\t\t\t\t            \t  formatter:function(value,row,index){\r\n");
      out.write("\t\t\t\t\t            \t\t  return jqueryUtil.showText(value,provinceArr);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t}    \r\n");
      out.write("\t\t\t\t\t              },\r\n");
      out.write("\t\t\t\t\t              {field : 'city',title : '市',width : parseInt($(this).width()*0.03),align : 'left',\r\n");
      out.write("\t\t\t\t\t            \t  formatter:function(value,row,index){\r\n");
      out.write("\t\t\t\t\t            \t\t  var cityArr = jqueryUtil.getAreaTextArr(row.province);//获取市\r\n");
      out.write("\t\t\t\t\t            \t\t  return jqueryUtil.showText(value,cityArr);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t}  \r\n");
      out.write("\t\t\t\t\t              },\r\n");
      out.write("\t\t\t\t\t              {field : 'area',title : '区/县',width : parseInt($(this).width()*0.03),align : 'left',\r\n");
      out.write("\t\t\t\t\t            \t\tformatter:function(value,row,index){\r\n");
      out.write("\t\t\t\t\t            \t\t\tvar xianArr = jqueryUtil.getAreaTextArr(row.city);//获取县\r\n");
      out.write("\t\t\t\t\t            \t\t\treturn jqueryUtil.showText(value,xianArr);\r\n");
      out.write("\t\t\t\t\t            \t\t}  \r\n");
      out.write("\t\t\t\t\t              },\r\n");
      out.write("\t\t\t\t\t              {field : 'street',title : '街道',width : parseInt($(this).width()*0.1),align : 'left'},\r\n");
      out.write("\t\t\t\t\t              {field : 'zip',title : '邮编',width : parseInt($(this).width()*0.05),align : 'left'},\r\n");
      out.write("\t\t\t\t\t              {field : 'workplace',title : '工作地点',width : parseInt($(this).width()*0.15),align : 'left'},\r\n");
      out.write("\t\t\t\t\t              {field : 'created',title : '创建时间',width : parseInt($(this).width()*0.1),align : 'left'},\r\n");
      out.write("\t\t\t\t\t              {field : 'creator',title : '创建人',width : parseInt($(this).width()*0.05),align : 'left'},\r\n");
      out.write("\t\t\t\t\t              {field : 'lastmod',title : '修改人',width : parseInt($(this).width()*0.05),align : 'left'},\r\n");
      out.write("\t\t\t\t\t              {field : 'modifiyer',title : '修改时间',width : parseInt($(this).width()*0.1),align : 'left'},\r\n");
      out.write("\t\t\t\t\t              ]],toolbar:'#tb',\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t \r\n");
      out.write("\t\t\t\t//搜索框\r\n");
      out.write("\t\t\t\t$(\"#searchbox\").searchbox({ \r\n");
      out.write("\t\t\t\t\t menu:\"#mm\", \r\n");
      out.write("\t\t\t\t\t prompt :'模糊查询',\r\n");
      out.write("\t\t\t\t    searcher:function(value,name){   \r\n");
      out.write("\t\t\t\t    \tvar str=\"{\\\"searchName\\\":\\\"\"+name+\"\\\",\\\"searchValue\\\":\\\"\"+value+\"\\\"}\";\r\n");
      out.write("\t\t\t            var obj = eval('('+str+')');\r\n");
      out.write("\t\t\t            $dg.datagrid('reload',obj); \r\n");
      out.write("\t\t\t\t    }\r\n");
      out.write("\t\t\t\t   \r\n");
      out.write("\t\t\t\t}); \r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\t//删除\r\n");
      out.write("\t\t\tfunction delRows(){\r\n");
      out.write("\t\t\t\tvar row = $dg.datagrid('getSelected');\r\n");
      out.write("\t\t\t\tif(row){\r\n");
      out.write("\t\t\t\t\tvar rowIndex = $dg.datagrid('getRowIndex', row);\r\n");
      out.write("\t\t\t\t\t$dg.datagrid('deleteRow', rowIndex);\r\n");
      out.write("\t\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\t\turl:\"loanorder/loanOrderAction!deleteLoanOrder.action\",\r\n");
      out.write("\t\t\t\t\t\tdata: \"id=\"+row.id,\r\n");
      out.write("\t\t\t\t\t\tsuccess: function(rsp){\r\n");
      out.write("\t\t\t\t\t\t\tparent.$.messager.show({\r\n");
      out.write("\t\t\t\t\t\t\t\ttitle : rsp.title,\r\n");
      out.write("\t\t\t\t\t\t\t\tmsg : rsp.message,\r\n");
      out.write("\t\t\t\t\t\t\t\ttimeout : 1000 * 2\r\n");
      out.write("\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\tparent.$.messager.show({\r\n");
      out.write("\t\t\t\t\t\ttitle : \"提示\",\r\n");
      out.write("\t\t\t\t\t\tmsg :\"请选择一行记录!\",\r\n");
      out.write("\t\t\t\t\t\ttimeout : 1000 * 2\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t//弹窗修改\r\n");
      out.write("\t\t\tfunction updRowsOpenDlg() {\r\n");
      out.write("\t\t\t\tvar row = $dg.datagrid('getSelected');\r\n");
      out.write("\t\t\t\tif (row) {\r\n");
      out.write("\t\t\t\t\tparent.$.modalDialog({\r\n");
      out.write("\t\t\t\t\t\ttitle : '编辑人员',\r\n");
      out.write("\t\t\t\t\t\twidth : 600,\r\n");
      out.write("\t\t\t\t\t\theight : 400,\r\n");
      out.write("\t\t\t\t\t\thref : \"jsp/emergency_contacter/emergencyContacterEditDlg.jsp\",\r\n");
      out.write("\t\t\t\t\t\tonLoad:function(){\r\n");
      out.write("\t\t\t\t\t\t\tvar f = parent.$.modalDialog.handler.find(\"#form\");\r\n");
      out.write("\t\t\t\t\t\t\tf.form(\"load\", row);\r\n");
      out.write("\t\t\t\t\t\t},\t\t\t\r\n");
      out.write("\t\t\t\t\t\tbuttons : [ {\r\n");
      out.write("\t\t\t\t\t\t\ttext : '编辑',\r\n");
      out.write("\t\t\t\t\t\t\ticonCls : 'icon-ok',\r\n");
      out.write("\t\t\t\t\t\t\thandler : function() {\r\n");
      out.write("\t\t\t\t\t\t\t\tparent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好\r\n");
      out.write("\t\t\t\t\t\t\t\tvar f = parent.$.modalDialog.handler.find(\"#form\");\r\n");
      out.write("\t\t\t\t\t\t\t\tf.submit();\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t}, {\r\n");
      out.write("\t\t\t\t\t\t\ttext : '取消',\r\n");
      out.write("\t\t\t\t\t\t\ticonCls : 'icon-cancel',\r\n");
      out.write("\t\t\t\t\t\t\thandler : function() {\r\n");
      out.write("\t\t\t\t\t\t\t\tparent.$.modalDialog.handler.dialog('destroy');\r\n");
      out.write("\t\t\t\t\t\t\t\tparent.$.modalDialog.handler = undefined;\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t]\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\tparent.$.messager.show({\r\n");
      out.write("\t\t\t\t\t\ttitle :\"提示\",\r\n");
      out.write("\t\t\t\t\t\tmsg :\"请选择一行记录!\",\r\n");
      out.write("\t\t\t\t\t\ttimeout : 1000 * 2\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t//弹窗增加公司\r\n");
      out.write("\t\t\tfunction addRowsOpenDlg() {\r\n");
      out.write("\t\t\t\tparent.$.modalDialog({\r\n");
      out.write("\t\t\t\t\ttitle : '添加人员',\r\n");
      out.write("\t\t\t\t\twidth : 1000,\r\n");
      out.write("\t\t\t\t\theight : 900,\r\n");
      out.write("\t\t\t\t\thref : \"jsp/emergency_contacter/emergencyContacterEditDlg.jsp\",\r\n");
      out.write("\t\t\t\t\tbuttons : [ {\r\n");
      out.write("\t\t\t\t\t\ttext : '保存',\r\n");
      out.write("\t\t\t\t\t\ticonCls : 'icon-ok',\r\n");
      out.write("\t\t\t\t\t\thandler : function() {\r\n");
      out.write("\t\t\t\t\t\t\tparent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好\r\n");
      out.write("\t\t\t\t\t\t\tvar f = parent.$.modalDialog.handler.find(\"#form\");\r\n");
      out.write("\t\t\t\t\t\t\tf.submit();\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}, {\r\n");
      out.write("\t\t\t\t\t\ttext : '取消',\r\n");
      out.write("\t\t\t\t\t\ticonCls : 'icon-cancel',\r\n");
      out.write("\t\t\t\t\t\thandler : function() {\r\n");
      out.write("\t\t\t\t\t\t\tparent.$.modalDialog.handler.dialog('destroy');\r\n");
      out.write("\t\t\t\t\t\t\tparent.$.modalDialog.handler = undefined;\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t]\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t//高级搜索 删除 row\r\n");
      out.write("\t\t\tfunction tbCompanySearchRemove(curr) {\r\n");
      out.write("\t\t\t\t\t$(curr).closest('tr').remove();\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t//高级查询\r\n");
      out.write("\t\t\tfunction tbsCompanySearch() {\r\n");
      out.write("\t\t\t\tjqueryUtil.gradeSearch($dg,\"#emergencyContacterSearchFm\",\"jsp/emergency_contacter/emergencyContacterSearchDlg.jsp\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t//查看详情\r\n");
      out.write("\t\t\tfunction checkDetail(rowIndex, rowData) {\r\n");
      out.write("\t\t\t\tvar row = $('#dg').datagrid('getData').rows[rowIndex]\r\n");
      out.write("\t\t\t\tvar cityArr = jqueryUtil.getAreaTextArr(row.province);//获取市\r\n");
      out.write("\t\t\t\tvar xianArr = jqueryUtil.getAreaTextArr(row.city);//获取县\r\n");
      out.write("\t\t\t\t$(\"#emergencyDetailFm\").form(\"load\",rowData);\r\n");
      out.write("\t\t\t\tdisableOcx();\r\n");
      out.write("\t\t\t\t$(\"#genderType\").val(jqueryUtil.showText(row.genderType,sexArr));\r\n");
      out.write("\t\t\t\t$(\"#idType\").val(jqueryUtil.showText(row.idType,idTpyeArr));\r\n");
      out.write("\t\t\t\t$(\"#province\").val(jqueryUtil.showText(row.province,provinceArr));\r\n");
      out.write("\t\t\t\t$(\"#city\").val(jqueryUtil.showText(row.city,cityArr));\r\n");
      out.write("\t\t\t\t$(\"#area\").val(jqueryUtil.showText(row.area,xianArr));\r\n");
      out.write("\t\t\t\t$('#emergencyDetailDlg').dialog(\"open\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tfunction disableOcx() {\r\n");
      out.write("\r\n");
      out.write("\t\t\t    var form = document.forms[0];\r\n");
      out.write("\r\n");
      out.write("\t\t\t    for ( var i = 0; i < form.length; i++) {\r\n");
      out.write("\r\n");
      out.write("\t\t\t    var element = form.elements[i];\r\n");
      out.write("\r\n");
      out.write("\t\t\t    //部分元素可以编号 element.name 是元素自定义 name\r\n");
      out.write("\r\n");
      out.write("\t\t\t    if (element.name != \"auditEntity.auditContent\"\r\n");
      out.write("\r\n");
      out.write("\t\t\t    && element.name != \"auditEntity.auditAutograph\"\r\n");
      out.write("\r\n");
      out.write("\t\t\t    && element.name != \"auditEntity.auditTime\"\r\n");
      out.write("\r\n");
      out.write("\t\t\t    && element.name != \"auditEntity.auditState\"\r\n");
      out.write("\r\n");
      out.write("\t\t\t    && element.name != \"submitBtn\"\r\n");
      out.write("\r\n");
      out.write("\t\t\t    && element.name != \"reset\"\r\n");
      out.write("\r\n");
      out.write("\t\t\t    && element.name != \"id\"\r\n");
      out.write("\r\n");
      out.write("\t\t\t    && element.name != \"processInstanceId\"\r\n");
      out.write("\r\n");
      out.write("\t\t\t    && element.name != \"updateForm\") {\r\n");
      out.write("\r\n");
      out.write("\t\t\t    element.disabled = \"true\";\r\n");
      out.write("\r\n");
      out.write("\t\t\t    }\r\n");
      out.write("\r\n");
      out.write("\t\t\t    }\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t    \r\n");
      out.write("\t\t</script>\r\n");
      out.write("  </head>\r\n");
      out.write("  <body>\r\n");
      out.write("      <div data-options=\"region:'center',border : false\">\r\n");
      out.write("  \t\t<div class=\"well well-small\" style=\"margin-left: 5px;margin-top: 5px\">\r\n");
      out.write("\t\t\t\t<span class=\"badge\">提示</span>\r\n");
      out.write("\t\t\t\t<p>\r\n");
      out.write("\t\t\t\t\t在此你可以对<span class=\"label-info\"><strong>紧急联系人</strong></span>进行编辑!\r\n");
      out.write("\t\t\t\t</p>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div id=\"tb\" style=\"padding:2px 0\">\r\n");
      out.write("\t\t\t<table cellpadding=\"0\" cellspacing=\"0\">\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td style=\"padding-left:2px\">\r\n");
      out.write("\t\t\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<!-- <a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-add\" plain=\"true\" onclick=\"addRowsOpenDlg();\">添加</a>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-edit\" plain=\"true\" onclick=\"updRowsOpenDlg();\">编辑</a>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-remove\" plain=\"true\" onclick=\"delRows();\">删除</a> -->\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td style=\"padding-left:2px\">\r\n");
      out.write("\t\t\t\t\t\t<input id=\"searchbox\" type=\"text\"/>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t <td style=\"padding-left:2px\">\r\n");
      out.write("\t\t\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-search\" plain=\"true\" onclick=\"tbsCompanySearch();\">高级查询</a>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div id=\"mm\">\r\n");
      out.write("\t\t\t\t<div name=\"name\">姓名</div>\r\n");
      out.write("\t\t\t\t<div name=\"fixedTel\">固定电话</div>\r\n");
      out.write("\t\t\t\t<div name=\"tel\">手机号</div>\r\n");
      out.write("\t\t\t\t<div name=\"email\">邮箱</div>\r\n");
      out.write("\t\t\t\t<div name=\"workplace\">工作地点</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<table id=\"dg\" title=\"紧急联系人管理\"></table>\r\n");
      out.write("  \t</div>\t\r\n");
      out.write("  \t\r\n");
      out.write("  \t <div id=\"emergencyDetailDlg\"  class=\"easyui-dialog\" modal=\"true\" title=\"紧急联系人详情\" closed=\"true\"  style=\"width:800px;height:300px;overflow:hidden;text-align: center;\">\r\n");
      out.write("\t\t   <form id=\"emergencyDetailFm\">\r\n");
      out.write("\t\t   \t\t <table>\r\n");
      out.write("\t\t\t\t\t <tr>\r\n");
      out.write("\t\t\t\t\t    <th>名称</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"name\" id=\"name\" class=\"easyui-textbox easyui-validatebox\" type=\"text\"/></td>\r\n");
      out.write("\t\t\t\t\t\t<th>性别</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"genderType\" id=\"genderType\" class=\"easyui-textbox easyui-validatebox\" type=\"text\"/></td>\r\n");
      out.write("\t\t\t\t\t    <th>出生日期</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"birthday\" id=\"birthday\" class=\"easyui-textbox easyui-validatebox\" type=\"text\"/></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t \t<th>证件类型</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"idType\" id=\"idType\" class=\"easyui-textbox easyui-validatebox\" type=\"text\"/></td>\r\n");
      out.write("\t\t\t\t\t \r\n");
      out.write("\t\t\t\t\t    <th>证件号</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"idNo\" id=\"idNo\" type=\"text\" class=\"easyui-textbox easyui-validatebox\" /></td>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<th>手机号</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"tel\" id=\"tel\" type=\"text\" class=\"easyui-textbox easyui-validatebox\" /></td>\r\n");
      out.write("\t\t\t\t\t    \r\n");
      out.write("\t\t\t\t\t </tr>\r\n");
      out.write("\t\t\t\t\t <tr>\r\n");
      out.write("\t\t\t\t\t \t<th>固定电话</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"fixedTel\" id=\"fixedTel\" type=\"text\" class=\"easyui-textbox easyui-validatebox\" /></td>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<th>邮箱</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"email\" id=\"email\" type=\"text\" class=\"easyui-textbox easyui-validatebox\" /></td>\r\n");
      out.write("\t\t\t\t\t \r\n");
      out.write("\t\t\t\t\t    <th>关系</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"relationship\" id=\"relationship\" type=\"text\" class=\"easyui-textbox easyui-validatebox\" /></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>省</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"province\" id=\"province\" type=\"text\" class=\"easyui-textbox easyui-validatebox\" /></td>\r\n");
      out.write("\t\t\t\t\t   \r\n");
      out.write("\t\t\t\t\t    <th>市</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"city\" id=\"city\" type=\"text\" class=\"easyui-textbox easyui-validatebox\" /></td>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<th>区/县</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"area\" id=\"area\" type=\"text\" class=\"easyui-textbox easyui-validatebox\" /></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t <tr>\r\n");
      out.write("\t\t\t\t\t    <th>街道</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"street\" id=\"street\" type=\"text\" class=\"easyui-textbox easyui-validatebox\" /></td>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<th>接受债券文件方式</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input name=\"zip\" id=\"zip\" type=\"text\" class=\"easyui-textbox easyui-validatebox\" /></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t </table>\r\n");
      out.write("\t\t   </form>\r\n");
      out.write("\t </div>\r\n");
      out.write("  \t\r\n");
      out.write("  </body>\r\n");
      out.write("</html>\r\n");
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

/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2016-02-25 06:59:54 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class userAndOrganization_jsp extends org.apache.jasper.runtime.HttpJspBase
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

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("<title>流程部署</title>\r\n");
      out.write("<meta http-equiv=\"pragma\" content=\"no-cache\">\r\n");
      out.write("<meta http-equiv=\"cache-control\" content=\"no-cache\">\r\n");
      out.write("<meta http-equiv=\"expires\" content=\"0\">\r\n");
      out.write("<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">\r\n");
      out.write("<meta http-equiv=\"description\" content=\"This is my page\">\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../layout/script.jsp", out, false);
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(basePath );
      out.write("js/ajaxfileupload.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(basePath );
      out.write("jsp/workflow/proc_dep.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var $deptTreeGrid;\r\n");
      out.write("var $userDataGrid;\r\n");
      out.write("$(function(){\r\n");
      out.write("\t$deptTreeGrid = $('#deptTreeGrid').treegrid({    \r\n");
      out.write("\t    url:'orgz/organizationAction!findOrganizationByMyId.action',\r\n");
      out.write("\t    width:\"auto\",\r\n");
      out.write("\t\theight : $(window).height()-120,\r\n");
      out.write("\t\trownumbers:true,\r\n");
      out.write("\t\tanimate: true,\r\n");
      out.write("\t\tcollapsible: true,\r\n");
      out.write("\t\tfitColumns: true,\r\n");
      out.write("\t\tborder:false,\r\n");
      out.write("\t\tstriped:true,\r\n");
      out.write("\t\tsingleSelect:true,\r\n");
      out.write("\t\tcascadeCheck:true,\r\n");
      out.write("\t\tdeepCascadeCheck:true,\r\n");
      out.write("\t\tidField: 'id',\r\n");
      out.write("\t\ttreeField: 'name',\r\n");
      out.write("\t\tparentField : 'pid',\r\n");
      out.write("\t\tanimate :true,\r\n");
      out.write("\t\tcolumns:[[    \r\n");
      out.write("\t\t        {title:'组织机构名称',field:'name',width:$(this).width()*0.06}\r\n");
      out.write("\t    ]],onDblClickRow:function(row){\r\n");
      out.write("        \t$userDataGrid.datagrid(\"clearSelections\");\r\n");
      out.write("\t    \t$.ajax({type: \"POST\",\r\n");
      out.write("\t                url: \"userOrg/userAndOrganization!findBindingUsersByOrganizationId.action\",\r\n");
      out.write("\t                data: {organizationId:row.id},\r\n");
      out.write("\t                dataType: \"json\",\r\n");
      out.write("\t                success: function(data){\r\n");
      out.write("\t                \tvar list = data.rows;\r\n");
      out.write("\t                \tif(list !=null && list.length>0){\r\n");
      out.write("\t                \t\tfor(var i=0;i<list.length;i++){\r\n");
      out.write("\t                \t\t\tvar index = $userDataGrid.datagrid(\"getRowIndex\",list[i].userId);\r\n");
      out.write("\t                \t\t\tconsole.info(index);\r\n");
      out.write("\t                \t\t\t$userDataGrid.datagrid(\"selectRow\",index);\r\n");
      out.write("\t                \t\t}\r\n");
      out.write("\t                \t}\r\n");
      out.write("\t                }\r\n");
      out.write("                });\r\n");
      out.write("\t    },toolbar:[{\r\n");
      out.write("            text:'保存',\r\n");
      out.write("            iconCls:'icon-save',\r\n");
      out.write("            handler:function(){\r\n");
      out.write("            \tvar node = $deptTreeGrid.treegrid(\"getSelected\");\r\n");
      out.write("            \t// 判断是否选中了节点，如果选中了节点判断是不是叶子（营业部信息），如果不是叶子给出相应的提示信息\r\n");
      out.write("            \tif(node == null){\r\n");
      out.write("            \t\t$.messager.alert('提示','请选择要外援的营业部！','waring');\r\n");
      out.write("            \t\treturn;\r\n");
      out.write("            \t}else{\r\n");
      out.write("            \t\tvar childrens = $deptTreeGrid.treegrid(\"getChildren\",node.id);\r\n");
      out.write("            \t\tif(childrens !=null && childrens.length > 0 ){\r\n");
      out.write("                \t\t$.messager.alert('提示','请选择要外援的营业部！','waring');\r\n");
      out.write("                \t\treturn;\r\n");
      out.write("            \t\t}\r\n");
      out.write("            \t}\r\n");
      out.write("            \t\r\n");
      out.write("            \t// 判断是否选中了外援人员 信息\r\n");
      out.write("            \tvar users = $userDataGrid.datagrid(\"getSelections\");\r\n");
      out.write("            \tvar userIds = [];\r\n");
      out.write("            \tif(users ==null || users.length == 0){\r\n");
      out.write("            \t\t$.messager.confirm('提示','您是否取消组织机构和外援的绑定关系？',function(r){    \r\n");
      out.write("            \t\t    if (r){ \r\n");
      out.write("                    \t\tsaveBindingUserAndOrganization(node, userIds)\r\n");
      out.write("            \t\t    }    \r\n");
      out.write("            \t\t});  \r\n");
      out.write("            \t}else{\r\n");
      out.write("            \t\tfor(var i=0;i<users.length;i++){\r\n");
      out.write("            \t\t\tuserIds.push(users[i].userId);\r\n");
      out.write("            \t\t}\r\n");
      out.write("            \t\tsaveBindingUserAndOrganization(node, userIds)\r\n");
      out.write("            \t}\r\n");
      out.write("           \t}\r\n");
      out.write("\t    }]\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t$userDataGrid = $('#userDataGrid').datagrid({    \r\n");
      out.write("\t    url:'users/usersAction!findUsesrsByRoleCode.action', \r\n");
      out.write("\t    queryParams:{roleCode:\"IPCXiaoEDiaoCha\"},\r\n");
      out.write("\t    width:\"auto\",\r\n");
      out.write("\t\theight : $(window).height()-120,\r\n");
      out.write("\t\tfitColumns:true,\r\n");
      out.write("\t\tidField:\"userId\",\r\n");
      out.write("\t    columns:[[    \r\n");
      out.write("\t        {field:'areaName',title:'地区',width:$(this).width()*0.03,align:'right'},  \r\n");
      out.write("\t        {field:'companyName',title:'公司信息',width:$(this).width()*0.04,align:'right'},  \r\n");
      out.write("\t        {field:'deptName',title:'营业部',width:$(this).width()*0.06,align:'right'},   \r\n");
      out.write("\t        {field:'userName',title:'姓名',width:$(this).width()*0.04},    \r\n");
      out.write("\t        {field:'email',title:'邮箱',width:$(this).width()*0.06,align:'right'},   \r\n");
      out.write("\t        {field:'tel',title:'电话',width:$(this).width()*0.06,align:'right'} \r\n");
      out.write("\t    ]],toolbar:[{\r\n");
      out.write("            text:'全选',\r\n");
      out.write("            iconCls:'icon-undo',\r\n");
      out.write("            handler:function(){\r\n");
      out.write("            \t$userDataGrid.datagrid(\"selectAll\");\r\n");
      out.write("           \t}\r\n");
      out.write("        },{\r\n");
      out.write("            text:'取消',\r\n");
      out.write("            iconCls:'icon-cancel',\r\n");
      out.write("            handler:function(){\r\n");
      out.write("            \t$userDataGrid.datagrid(\"clearSelections\");\r\n");
      out.write("           \t}\r\n");
      out.write("        }]\r\n");
      out.write("\t    ,onLoadSuccess:function(data){\r\n");
      out.write("\t\t \tvar rows = data.rows;\r\n");
      out.write("            var areaMergeMap = {};\r\n");
      out.write("            var companyMergeMap = {};\r\n");
      out.write("            var deptMergeMap = {};\r\n");
      out.write("            if(rows){\r\n");
      out.write("            \tfor(var i=0;i<rows.length;i++){\r\n");
      out.write("            \t\tvar areaName = rows[i].areaName;\r\n");
      out.write("            \t\tif( areaName in areaMergeMap ){\r\n");
      out.write("            \t\t\tareaMergeMap[areaName].rowspan++;\r\n");
      out.write("            \t\t}else{\r\n");
      out.write("            \t\t\tareaMergeMap[areaName]={\"index\":i,\"rowspan\":1}\r\n");
      out.write("            \t\t}\r\n");
      out.write("            \t\t\r\n");
      out.write("            \t\tvar companyName = rows[i].companyName;\r\n");
      out.write("            \t\tif(companyName in companyMergeMap){\r\n");
      out.write("            \t\t\tcompanyMergeMap[companyName].rowspan++;\r\n");
      out.write("            \t\t}else{\r\n");
      out.write("            \t\t\tcompanyMergeMap[companyName]={\"index\":i,\"rowspan\":1}\r\n");
      out.write("            \t\t}\r\n");
      out.write("            \t\t\r\n");
      out.write("            \t\tvar deptName = rows[i].deptName;\r\n");
      out.write("            \t\tif(deptName in deptMergeMap){\r\n");
      out.write("            \t\t\tdeptMergeMap[deptName].rowspan++;\r\n");
      out.write("            \t\t}else{\r\n");
      out.write("            \t\t\tdeptMergeMap[deptName]={\"index\":i,\"rowspan\":1}\r\n");
      out.write("            \t\t}\r\n");
      out.write("            \t}\r\n");
      out.write("            }\r\n");
      out.write("            \r\n");
      out.write("            for(var i in areaMergeMap){\r\n");
      out.write("                $(this).datagrid('mergeCells',{\r\n");
      out.write("                    index: areaMergeMap[i].index,\r\n");
      out.write("                    field: 'areaName',\r\n");
      out.write("                    rowspan: areaMergeMap[i].rowspan\r\n");
      out.write("                });\r\n");
      out.write("            }\r\n");
      out.write("            \r\n");
      out.write("            for(var i in companyMergeMap){\r\n");
      out.write("                $(this).datagrid('mergeCells',{\r\n");
      out.write("                    index: companyMergeMap[i].index,\r\n");
      out.write("                    field: 'companyName',\r\n");
      out.write("                    rowspan: companyMergeMap[i].rowspan\r\n");
      out.write("                });\r\n");
      out.write("            }\r\n");
      out.write("            \r\n");
      out.write("            for(var i in deptMergeMap){\r\n");
      out.write("                $(this).datagrid('mergeCells',{\r\n");
      out.write("                    index: deptMergeMap[i].index,\r\n");
      out.write("                    field: 'deptName',\r\n");
      out.write("                    rowspan: deptMergeMap[i].rowspan\r\n");
      out.write("                });\r\n");
      out.write("            }\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});  \r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("/**\r\n");
      out.write(" * 绑定组织机构和外援人员方法\r\n");
      out.write(" */\r\n");
      out.write("function saveBindingUserAndOrganization(node,userIds){\r\n");
      out.write("\t// 保存绑定用户的信息\r\n");
      out.write("\t$.ajax({type: \"POST\",\r\n");
      out.write("        url: \"userOrg/userAndOrganization!saveBindingUserAndOrganization.action\",\r\n");
      out.write("        data: {\"organizationId\":node.id,\"userIds\":userIds.join(\",\")},\r\n");
      out.write("        dataType: \"json\",\r\n");
      out.write("        success: function(data){\r\n");
      out.write("        \tif(data.status){\r\n");
      out.write("            \t$.messager.show({\r\n");
      out.write("            \t\ttitle:'提示',\r\n");
      out.write("            \t\tmsg:'保存成功。',\r\n");
      out.write("            \t\ttimeout:5000,\r\n");
      out.write("            \t\tshowType:'slide'\r\n");
      out.write("            \t});\r\n");
      out.write("        \t}else{\r\n");
      out.write("        \t\t$.messager.alert(\"提示\",\"保存失败\",'error');\r\n");
      out.write("        \t}\r\n");
      out.write("        }\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <!-- 提示信息区域 -->\r\n");
      out.write("\t<div class=\"well well-small\" style=\"margin-left: 5px; margin-top: 5px;\">\r\n");
      out.write("\t\t<span class=\"badge\">提示</span>\r\n");
      out.write("\t\t<p>\r\n");
      out.write("\t\t\t在此你可以对<span class=\"label-info\"><strong>流程</strong></span>进行更新、部署、删除、查询等操作!\r\n");
      out.write("\t\t</p>\r\n");
      out.write("\t\t<div id=\"cc\" class=\"easyui-layout\" style=\"width:1000px;height:750px;\">   \r\n");
      out.write("\t\t\t<div data-options=\"region:'west',split:true,border:true\" style=\"width:300px;\">\r\n");
      out.write("\t\t    \t<table id=\"deptTreeGrid\" ></table>\r\n");
      out.write("\t\t    </div>   \r\n");
      out.write("\t\t    <div data-options=\"region:'center',title:'IPC小额调查人员信息列表'\">\r\n");
      out.write("\t\t    \t<div id=\"userDataGrid\" ></div>\r\n");
      out.write("\t\t    </div>   \r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
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
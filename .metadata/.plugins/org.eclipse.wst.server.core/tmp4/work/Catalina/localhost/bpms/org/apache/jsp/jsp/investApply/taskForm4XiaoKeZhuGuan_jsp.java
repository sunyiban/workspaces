/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2015-12-16 08:32:31 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.investApply;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class taskForm4XiaoKeZhuGuan_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\ttextarea{resize: none;}\r\n");
      out.write("</style>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var row,row1;\r\n");
      out.write("var investorId='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${investorId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("var investOrderId='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${investOrderId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("var taskId='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${taskId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("$(function(){\r\n");
      out.write("\t//查询投资人详细信息\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\turl:'investor/investorAction!findInvestorById.action',\r\n");
      out.write("\t\t\tdata:'investorId='+investorId,\r\n");
      out.write("\t\t\tdataType:'json',\r\n");
      out.write("\t\t\tasync : false,\r\n");
      out.write("\t\t\tsuccess:function(data){\r\n");
      out.write("\t\t\t\trow = data;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t})\r\n");
      out.write("\t// 渲染姓名\r\n");
      out.write("\t$(\"#acceptTaskForm input[name='name']\").val(row.chName);\r\n");
      out.write("\t// 渲染身份证号\r\n");
      out.write("\t$(\"#acceptTaskForm input[name='idNo']\").val(row.idNo);\r\n");
      out.write("\t// 查看申请状态\r\n");
      out.write("\t$(\"#lookLoanOrderdg\").datagrid({\r\n");
      out.write("\t\turl : \"investOrderHis/investOrderHisAction!findAllInvestOrderHisList.action?investOrderId=\"+investOrderId,\r\n");
      out.write("\t\twidth : 'auto',\r\n");
      out.write("\t\theight : 240,\r\n");
      out.write("\t\tpagination:true,\r\n");
      out.write("\t\trownumbers:true,\r\n");
      out.write("\t\tborder:true,\r\n");
      out.write("\t\tsingleSelect:true,\r\n");
      out.write("\t\tnowrap:true,\r\n");
      out.write("\t\tcolumns : [ [ \r\n");
      out.write("\t\t              {field : 'agentTime',title : '受理时间',width : parseInt($(this).width()*0.1),sortable:true},\r\n");
      out.write("\t\t              {field : 'roleName',title : '受理角色',width : parseInt($(this).width()*0.1)},\r\n");
      out.write("\t\t              {field : 'assigneeName',title : '受理人',width : parseInt($(this).width()*0.1),align : 'left'},\r\n");
      out.write("\t\t              {field : 'comment',title : '审批意见',width :parseInt($(this).width()*0.1),align : 'left'},\r\n");
      out.write("\t\t              {field : 'aa',title : '查看附件',width :parseInt($(this).width()*0.09),align : 'left',\r\n");
      out.write("\t\t\t            \tformatter:function(value,row,index){\r\n");
      out.write("\t\t\t            \t\treturn \"<a href='javascript:void(0);' onclick='lookAttachment(\"+index+\");'>查看附件</a>　　\" ;\r\n");
      out.write("\t\t\t            \t}  \r\n");
      out.write("\t\t              }\r\n");
      out.write("\t\t              ] ]\r\n");
      out.write("\t});\t\r\n");
      out.write("\t\r\n");
      out.write("\t//渲染附件类型下拉列表\r\n");
      out.write("\t$(\"#attType\").combobox({\r\n");
      out.write("\t\tvalueField : 'code',\r\n");
      out.write("\t\ttextField : 'text',\r\n");
      out.write("\t\turl:'common/commonAction!findTextArr.action?codeMyid=attachment_type_invest',\r\n");
      out.write("\t\tonLoadSuccess : function(){\r\n");
      out.write("\t\t\tvar val = $(this).combobox(\"getData\");\r\n");
      out.write("\t\t\tif(!$.isEmptyObject(val)){\r\n");
      out.write("                $(this).combobox(\"select\", val[0][\"code\"]);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\teditable:false \r\n");
      out.write("    });\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t// 渲染附件列表\r\n");
      out.write("\t//查看附件\r\n");
      out.write("\t$(\"#checkAttachment\").click(function(){\t\t\r\n");
      out.write("\t\tconsole.info(\"\")\r\n");
      out.write("\t\tcheckAttachementDetail4InvestOrder('noauditId',investOrderId,row.assignee,'2');\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t//上传附件\r\n");
      out.write("\t$(\"#upploadAttachment\").click(function(){\r\n");
      out.write("\t\tvar attType = $(\"#attType\").combobox(\"getValue\");\r\n");
      out.write("\t\tinvestfileUploadsDlg(attType,investOrderId);\r\n");
      out.write("\t});\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t//弹出新引入的第三方文件上传组件，附件类型，订单ID，附件名称\r\n");
      out.write("\tfunction investfileUploadsDlg(attType, investOrderId){\r\n");
      out.write("\t\tif(attType==\"\"){\r\n");
      out.write("\t\t\t$.messager.alert(\"提示\",\"请先选择附件类型!\",\"info\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\twindow.open('jsp/investOrder/investOrderAttachmentForm.jsp?investOrderId='+investOrderId+'&attType='+attType,\r\n");
      out.write("\t\t\t\t\"附件详情\", \"height=\"+$(window).height()*0.95+\", width=\"+$(window).width()*0.95+\", top=100, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no\");\r\n");
      out.write("\t}\t\t\t\r\n");
      out.write("});\t\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t/**==完善客户及合同信息==*/\r\n");
      out.write("\tfunction completeOrderInfo(){\r\n");
      out.write("\t\tconsole.info(\"completeOrderInfo ----> investOrderId = \"+investOrderId);\r\n");
      out.write("\t\tconsole.info(row);\r\n");
      out.write("\t\t//查询地址\r\n");
      out.write("\t\tvar addr=new Array();\r\n");
      out.write("\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\turl:'investor/investorAction!findAddressById.action',\r\n");
      out.write("\t\t\t\tdata:'addressId='+row[\"commAddr\"],\r\n");
      out.write("\t\t\t\tdataType:'json',\r\n");
      out.write("\t\t\t\tasync : false,\r\n");
      out.write("\t\t\t\tsuccess:function(data){\r\n");
      out.write("\t\t\t\t\taddr=data;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}); \r\n");
      out.write("\t\t//打开dialog\r\n");
      out.write("\t\t$(\"#dd\").dialog({\r\n");
      out.write("\t\t\ttitle : '完善理财客户合同信息',\r\n");
      out.write("\t\t\twidth : 920,\r\n");
      out.write("\t\t\theight : 600,\r\n");
      out.write("\t\t\tmodal:true,\r\n");
      out.write("\t\t\t//href : \"jsp/investOrder/investOrderEditForm.jsp?investOrderId=\"+investOrderId,\r\n");
      out.write("\t\t\thref:'investorderAndProducts/investorderAndProductsAction!gotoCompleteOrderInfo.action?orderId='+investOrderId,\r\n");
      out.write("\t\t\tonLoad:function(){\r\n");
      out.write("\t\t\t\t/* 渲染基本客户信息 Tab页面*/\r\n");
      out.write("\t\t\t\tvar f = $(\"#basicInvestorClientInfoForm\");\r\n");
      out.write("\t\t\t\tif(addr!=null){\r\n");
      out.write("\t\t\t\t\trow[\"provinceId\"]=addr[\"provinceId\"];\r\n");
      out.write("\t\t\t\t\trow[\"cityId\"]=addr[\"cityId\"];\r\n");
      out.write("\t\t\t\t\trow[\"areaId\"]=addr[\"areaId\"]; \r\n");
      out.write("\t\t\t\t\trow[\"addressDetails\"]=addr[\"addrDetails\"];\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tf.form(\"load\", row);\r\n");
      out.write("\t\t\t\trenderProvinceSelect('provinceId','cityId','areaId');\r\n");
      out.write("\t\t\t\t$(\"#provinceId\").combobox(\"setValue\",row.provinceId);\r\n");
      out.write("\t\t        $(\"#cityId\").combobox(\"setValue\",row.cityId);\r\n");
      out.write("\t\t        $(\"#areaId\").combobox(\"setValue\",row.areaId); \r\n");
      out.write("\t\t        \r\n");
      out.write("\t\t        /* 渲染紧急联系人 Tab页面*/\r\n");
      out.write("\t\t        //初始化紧急联系人列表\t\t        \r\n");
      out.write("\t\t        initLinkPeopleGrid(row.investorId);\r\n");
      out.write("\t\t        \r\n");
      out.write("\t\t        /* 渲染合同信息 Tab页面*/\r\n");
      out.write("\t\t        //查询InvestOrder订单详情\r\n");
      out.write("\t\t        $.ajax({\r\n");
      out.write("\t\t\t\t\turl:'investOrder/investOrderAction!findByInvestOrderId.action',\r\n");
      out.write("\t\t\t\t\tdata:'investOrderId='+investOrderId,\r\n");
      out.write("\t\t\t\t\tdataType:'json',\r\n");
      out.write("\t\t\t\t\tasync : false,\r\n");
      out.write("\t\t\t\t\tsuccess:function(data){\r\n");
      out.write("\t\t\t\t\t\trow1 = data;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t})\r\n");
      out.write("\t\t\t\t$(\"#constractInfoForm\").form(\"load\",row1);\r\n");
      out.write("\t\t        /* $(\"#orderStatus\").val(row1.orderStatus.statusCode); */\r\n");
      out.write("\t\t        //保存流程状态OrderStatus对象。\r\n");
      out.write("\t\t        if(row1.orderStatus != null){\t\t        \t\r\n");
      out.write("\t\t        \t$(\"#orderStatus\").val(row1.orderStatus.statusCode);\t\t        \r\n");
      out.write("\t\t        }else{\r\n");
      out.write("\t\t        \tconsole.info(\"未提交流程。流程状态OrderStatus对象为NULL。\");\r\n");
      out.write("\t\t\t        console.info(row1);\t\t        \r\n");
      out.write("\t\t        }\t\t        \r\n");
      out.write("\t\t\t} \r\n");
      out.write("\t\t}); \r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t/**======审批通过或驳回=======*/\r\n");
      out.write("\tfunction  submitTask(result,object) {\r\n");
      out.write("\t\t// 验证备注信息是否已经填写\r\n");
      out.write("\t\tif($(\"#comment\").val()==\"\"){\r\n");
      out.write("\t\t\t$.messager.alert(\"提示\",\"请填写备注信息后再进行提交!\",\"warning\")\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t// 确认是否提交\r\n");
      out.write("\t\t$.messager.confirm('提示', '点击按钮之后将进入下一个任务活动环节,此任务将对您不可见!', function(r){\r\n");
      out.write("\t\t\tif (r){\r\n");
      out.write("\t\t\t\tvar data = {\r\n");
      out.write("\t\t\t\t\t\"comment\" : $(\"#comment\").val(),\r\n");
      out.write("\t\t\t\t\t\"result\" :result,\r\n");
      out.write("\t\t\t\t\t\"investOrderId\" :investOrderId,\r\n");
      out.write("\t\t\t\t\t\"taskId\": taskId\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\ttype : \"POST\",\r\n");
      out.write("/* \t\t\t\t\turl : \"investOrder/investOrderAction!submitTask.action\", */\r\n");
      out.write("\t\t\t\t\turl : \"investApply/investApplyAction!submitTask.action\",\r\n");
      out.write("\t\t\t\t\tdata : data,\r\n");
      out.write("\t\t\t\t\tsuccess : function(msg) {\r\n");
      out.write("\t\t\t\t\t\tparent.$.modalDialog.openner.datagrid('reload');\r\n");
      out.write("\t\t\t\t\t\tparent.$.modalDialog.handler.dialog('close');\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t// 根据行索引获取行信息\r\n");
      out.write("\tfunction getRowData (index) {\r\n");
      out.write("\t    if (!$.isNumeric(index) || index < 0) { return undefined; }\r\n");
      out.write("\t    var rows = $(\"#lookLoanOrderdg\").datagrid(\"getRows\");\r\n");
      out.write("\t    return rows[index];\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\t//查看附件信息\r\n");
      out.write("\tfunction lookAttachment(index){\r\n");
      out.write("\t\t\tvar row = getRowData(index);\r\n");
      out.write("\t\t\t$(\"#lookAttachmentList\").datagrid({\r\n");
      out.write("\t\t\t\turl : \"attachment/attachmentAction!findAllAttachmentList.action\",\r\n");
      out.write("\t\t\t\twidth : 'auto',\r\n");
      out.write("\t\t\t\theight : 430,\r\n");
      out.write("\t\t\t\tpagination:false,\r\n");
      out.write("\t\t\t\trownumbers:true,\r\n");
      out.write("\t\t\t\tborder:false,\r\n");
      out.write("\t\t\t\tsingleSelect:true,\r\n");
      out.write("\t\t\t\tnowrap:true,\r\n");
      out.write("\t\t\t\tqueryParams:{\"orderId\":row.investOrderId,\"userId\":row.assignee,\"orderType\":\"attachment_type_invest\"},\r\n");
      out.write("\t\t\t\tmultiSort:false,\r\n");
      out.write("\t\t\t\tcolumns : [ [ \r\n");
      out.write("\t\t\t\t              {field : 'attName',title : '附件名称',width : 200,sortable:true,align:'center'},\r\n");
      out.write("\t\t\t\t              {field : 'attTypeName',title : '附件类型',width : 160,align:'center'},\r\n");
      out.write("\t\t\t\t              {field : 'creatorName',title : '创建者',width : 170,align:'center'},\r\n");
      out.write("\t\t\t\t              {field : 'id',title : '查看附件',width :220,align:'center',formatter:function(value,row,index){\r\n");
      out.write("\t\t\t\t            \t\tvar result = \"<a target='_blank' href='jsp/openoffice/documentView.jsp?attId=\"+row.attId+\"'>在线预览</a>　　\" ;\r\n");
      out.write("\t\t\t\t            \t\t\tresult += \"<a target='_blank' href='javascript:void(0);' onclick=\\\"downloadAttachment4InvestOrder('\"+row.attId+\"');\\\">下载</a>　　\" ;\r\n");
      out.write("\t\t\t\t            \t\treturn result;\r\n");
      out.write("\t\t\t\t              }}\r\n");
      out.write("\t\t\t    ] ]\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t$('#lookInfo').accordion(\"select\",\"附件信息\"); \r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t//打开客户详情汇总页面\r\n");
      out.write("\tfunction queryInvestorSummaryStatement() {\r\n");
      out.write("\t\twindow.open(\"jsp/investorReport/investorSummaryStatement.jsp\",\"客户信息汇总\",\r\n");
      out.write("\t\t\t\t\"height=800,width=1600,top=100,left=100,toolbar=no,menubar=no\");\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- 受理任务 S -->\r\n");
      out.write("\t\t<div data-options=\"region:'north',title:'North Title',split:true\">\r\n");
      out.write("\t\t\t<div style=\"height: 280px;overflow: auto;\" >\r\n");
      out.write("\t\t\t\t<form id=\"acceptTaskForm\" method=\"post\">\r\n");
      out.write("\t\t\t\t\t <input name=\"id\" id=\"id\"  type=\"hidden\"/>\r\n");
      out.write("\t\t\t\t\t <input name=\"auditId\" type=\"hidden\" value=\"noauditId\"/>\r\n");
      out.write("\t\t\t\t\t \r\n");
      out.write("\t\t\t\t\t <!-- 显示客户基本信息区域 -->\r\n");
      out.write("\t\t\t\t\t <table class=\"table\" cellpadding=\"5px;\">\r\n");
      out.write("\t\t\t\t\t\t <tr>\r\n");
      out.write("\t\t\t\t\t\t    <th>客户姓名:</th>\r\n");
      out.write("\t\t\t\t\t\t\t<td><input name=\"name\" readonly=\"readonly\" type=\"text\"/></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<th>身份证号:</th>\r\n");
      out.write("\t\t\t\t\t\t\t<td><input name=\"idNo\" readonly=\"readonly\" type=\"text\"/></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t \t<th>备注:</th>\r\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"3\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<textarea id=\"comment\" name=\"comment\" class=\"easyui-validatebox easyui-textbox\" style=\"width:300px;height:70px;\"></textarea>\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<th>附件类型:</th>\r\n");
      out.write("\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<input id=\"attType\" class=\"easyui-textbox easyui-combobox\" />\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"2\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<a id=\"checkAttachment\" href=\"javascript:void(0);\" class=\"easyui-linkbutton\">查看附件</a>\t\r\n");
      out.write("\t\t\t\t\t\t\t\t<a id=\"upploadAttachment\" href=\"javascript:void(0);\" class=\"easyui-linkbutton\" >上传附件</a>\t\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t </table>\t\t\t\t\t\t\t\t\r\n");
      out.write("\t \t\t\t</form>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<div style=\"width: 880px;height:30px;\">\r\n");
      out.write("\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"completeOrderInfo()\">完善客户及合同信息</a>\r\n");
      out.write("\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"queryInvestorSummaryStatement()\">查看/导出客户详情汇总</a>\r\n");
      out.write("\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"submitTask('XiaoKeZhuGuanTongGuo',this);\">审批通过</a>\r\n");
      out.write("\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"submitTask('XiaoKeZhuGuanBoHui',this);\">审批驳回</a>\r\n");
      out.write("\t\t\t\t<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" onclick=\"submitTask('XiaoKeZhuGuanJuJue',this);\">审批拒绝</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t    <div id=\"lookInfo\" class=\"easyui-accordion\" style=\"height: 300px;width: 980px;overflow: hidden;\">\r\n");
      out.write("\t\t\t    <div title=\"备注信息\" data-options=\"iconCls:'icon-cstbase',selected:true\" >   \r\n");
      out.write("\t\t\t\t\t<table id=\"lookLoanOrderdg\" title=\"申请备注的信息\"></table>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t    <div title=\"附件信息\" data-options=\"iconCls:'icon-cstbase'\" >   \r\n");
      out.write("\t\t\t\t\t<table id=\"lookAttachmentList\" title=\"申请附件的信息\"></table>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t</div>   \r\n");
      out.write("\t\t<!-- 受理任务 E -->\t\t\r\n");
      out.write("\t\t<div id=\"dd\"></div>\t");
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

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="js/validate.js"></script>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	var flag=true;
	
	$(function(){
		//上传附件
		aDisable();
		//休假类型
		findSysNameList();
		//职务代理人
		RenderName();
		
		//对基本信息进行验证
		$("#agentNo").combobox({required:true});
		$("#planBgDtime").datetimebox({required:true});
		$("#planEdDtime").datetimebox({required:true});
	});
	
	// 职务代理人的下拉
	function RenderName(){
		//选中部门后下拉框
		$("#agentNo").combobox({
			width:171,
			url:"LeaveApp/findOrgUserList.do",
			valueField:'code',
		 	textFiled:'text',
		 	onLoadSuccess:function(data){
		 		//加一个全部
		 		
		 	},
		}); 
	}
	
	 //添加其他休假类型
    function addLeTypeOther(){
  	  if($("#radio8").is(":checked")){
  		 $("#leTypeOther").show();
  		 $("#leTypeOther").validatebox({required:true});
  	 }else{
  		 $("#leTypeOther").hide();
  		 $("#leTypeOther").validatebox({required:false});
  	 } 
   } 
	
	function findSysNameList(){
		 //添加申请时，首先添加主表信息
		$.ajax({
			cache:true,
			type:'POST',
			url:'LeaveApp/findDictNameList.do',
			async:false,
			success:function(res){
				for(var i=0;i<res.length;i++){
					$("#le").append("<input type='radio' id='radio"+i+"' name='leType' value='"+res[i].dictCode+"'  onclick='addLeTypeOther();'/>"+res[i].dictName+"");
				}
				//默认选中第一个
				$("#radio0").attr("checked","checked");
				//其他休假类型
	    		$("#le").append("<input  id='leTypeOther' name='leTypeOther' hidden='true' style='width: 170px;height:11px' class='easyui-textbox easyui-validatebox' data-options='editable:false'/>");
			}
		}); 
	}

	//申请人信息保存
	function toSaveBaseInfo(){
		
		var isValid = $("#leaveAppForm").form('validate');
    	if(!isValid){
    		return false;
    	} 
    	
    	//校验实际的请假开始时间和结束时间小于等于计划的开始时间和结束时间
		valReallePlandate();
    	
     	//校验调休时间
    	if($("#radio7").is(":checked")){
    		//校验计划时间
        	valiDays($("#planBgDtime").datetimebox('getValue'),$("#planEdDtime").datetimebox('getValue'));
        	//校验实际时间
        	if($("#realBgDtime").datetimebox('getValue')!="" && $("#realEdDtime").datetimebox('getValue')!=""){
        		valiDays($("#realBgDtime").datetimebox('getValue'),$("#realEdDtime").datetimebox('getValue'));
        	}
        	
    	} 
    	//校验年假
    	if($("#radio6").is(":checked")){
    		//校验计划时间
        	valeYearsLeave($("#planBgDtime").datetimebox('getValue'),$("#planEdDtime").datetimebox('getValue'));
        	//校验实际时间
        	if($("#realBgDtime").datetimebox('getValue')!="" && $("#realEdDtime").datetimebox('getValue')!=""){
        		valeYearsLeave($("#realBgDtime").datetimebox('getValue'),$("#realEdDtime").datetimebox('getValue'));
        	}
    	}  
    	
    	if(!flag){
    		flag=true;
    		return false;
    	}
    	
    	//保存信息
		$.ajax({
			cache:true,
			type:'POST',
			url:"LeaveApp/saveLeaveApp.do",
			data:$('#leaveAppForm').serialize(),
			async:false,
			dataType:'JSON',
			success:function(res){
				 if(res!=null){
					 
					$("#leaveAppForm").form("load",res);
					aDisable();
					//添加时申请人
					$.messager.alert("提示", '休假申请保存成功!',"info")
				}else{
					$.messager.alert("提示", '出错了，保存失败!',"error")
				} 
			}
		}); 
	} 
	
	//校验实际的请假开始时间和结束时间小于等于计划的开始时间和结束时间
	function valReallePlandate(){
		if($("#planBgDtime").datetimebox('getValue')>$("#realBgDtime").datetimebox('getValue') && $("#realBgDtime").datetimebox('getValue')!=""){
			$.messager.alert("提示", '实际的休假开始时间超出计划的休假开始时间！',"error");
			flag=false;
		}
		if($("#planEdDtime").datetimebox('getValue')<$("#realEdDtime").datetimebox('getValue') && $("#realEdDtime").datetimebox('getValue')!=""){
			$.messager.alert("提示", '实际的休假结束时间超出计划的休假结束时间！',"error");
			flag=false;
		}
		
	}
	
	//校验年假天数
	 function valeYearsLeave(bDate,eDate){
		//判断年假开始时间和结束时间必须是同一年
		 if(bDate.split("-")[0]!=eDate.split("-")[0]){
				$.messager.alert("提示", '请重新填写请假时间,年假必须是同一年!',"error")
				flag=false;
		}
		
		if(flag){
			$.ajax({
				cache:true,
				type:'POST',
				url:"LeaveApp/valYearsLeave.do",
				data:{
					"beDate":bDate,
					"edDate":eDate,	
				},
				async:false,
				dataType:'JSON',
				success:function(res){
					if(res==7){
						$.messager.alert("提示", '工作年限不满一年',"error");
						flag=res.status;
					}else if(res==8){
						$.messager.alert("提示", '不能连续请年假，必须相隔一天！',"error");
						flag=res.status;
					}else if(res==9){
						$.messager.alert("提示", '系统出错！',"error");
						flag=res.status;
					}else if(res<5){
						$.messager.alert("提示", '最多请假天数为'+res+'天！',"error");
						flag=res.status;
					}
					
				}
			});
		}
		
	} 
	
	//校验调休天数
	function valiDays(beDate,edDate){
		//判断开始日期、结束日期和系统时间是否是同一年
		var date=new Date();
		if(date.getFullYear()!=beDate.split("-")[0]){
			$.messager.alert("提示", '请重新填写请假时间!',"error")
			flag=false;
		}
		if(date.getFullYear()!=edDate.split("-")[0]){
			$.messager.alert("提示", '请重新填写请假时间!',"error")
			flag=false;
		}
		
		if(flag){
			$.ajax({
				cache:true,
				type:'POST',
				url:"LeaveApp/valPlanDays.do",
				data:{
					"beDate":beDate,
					"edDate":edDate,
				},
				async:false,
				dataType:'JSON',
				success:function(res){
					if(res==1.1){
						$.messager.alert("提示", '不能进行调休申请！',"error")
						flag=false;
					}else if(res!=0){
						$.messager.alert("提示", '最多调休'+res+'天!',"error")
						flag=false;
					}
				}
			});
		}
	}
    
	//关闭
	function toCloseBaseInfo(){
		$("#saveOrUpdateEmpSalDialog").dialog('close');
	}
  	
	  //上传附件
    $("#upploadAttachment").click(function(){
    	if($.trim($("#appNo").val())!=''){
    		fileUploadsDlg($("#appNo").val(),$("#applicantNo").val());
    	}
	});
	//查看附件
	$("#checkAttachment").click(function(){
		if($.trim($("#appNo").val())!=''){
			checkAttachementDetail($("#appNo").val(),$("#applicantNo").val());
    	}
	});
	function aDisable(){
		if($.trim($("#appNo").val())==''){
			$("#upploadAttachment").attr("disabled",true);
			$("#checkAttachment").attr("disabled",true);
		}else{
			$("#upploadAttachment").removeClass("l-btn-disabled");
			$("#checkAttachment").removeClass("l-btn-disabled");
		}
	}
  	
	
	//校验是否是工作日,并判断申请日期和	
	function valdate(date){
		
		var nd=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+ date.getDate()+" "+date.getHours()+":00:00";
		//请假时间段位早上9:00~晚上18:00
		if(Date.parse(nd)<Date.parse(date.getFullYear()+"-"+(date.getMonth()+1)+"-"+ date.getDate()+" "+"9:00:00") || Date.parse(nd)>Date.parse(date.getFullYear()+"-"+(date.getMonth()+1)+"-"+ date.getDate()+" "+"18:00:00")){
			$.messager.alert("提示", '请假时间段位早上9:00~晚上18:00！',"error");
			nd=null;
		}else {
			$.ajax({
				cache:true,
				type:'POST',
				url:"LeaveApp/valdateok.do",
				data:{
					"date":nd
				},
				async:false,
				dataType:'JSON',
				success:function(res){
					
					if(!res.status){
						$.messager.alert("提示", '请勿填写节假日、周末！',"error");
						nd=null;
					}
				}
			});
		}
		return nd;
		
	}
	
	
</script>
<div id="tt">
	<div title="编辑休假申请">
	  <div class="well well-small" style="margin:5px;">
	     	<form id="leaveAppForm">
	      	  <input id="leaId" name="leaId" type="hidden"/><!-- 申请id -->
	      	  <input id="appNo" name="appNo" type="hidden"/><!-- 申请编号 -->
	          <input id="applicantNo" name="applicantNo" type="hidden"/><!-- 申请人id -->
	          <input id="appDate" name="appDate" type="hidden"/><!-- 申请时间 -->
	          
	         <table class="table">
				<tr>
					<th>申请人职位:</th>
					<td>
						<input  name="position" id="position" class="easyui-textbox easyui-validatebox "  data-options="required:true,editable:false,validType:'length[0,25]'" type="text" style="width: 167px"/>&nbsp;</td>					
					</td>
				</tr>
				<tr>
					<th>职务代理人:</th>
					<td>
						<input name="agentNo" id="agentNo" type="text" readonly="readonly"  class="easyui-textbox easyui-validatebox easyui-combobox" data-options="editable:false" style="width: 170px"/>&nbsp;</td>					
					</td>
				</tr>
				
				<tr>
					<th>休假种类:</th>
					<td id="le"  colspan="3" style="height: 22px">
						
					</td>
				</tr>
				
				<tr>
					<th>休假事由:</th>
					<td colspan="3">
						<textarea id="leReason" name="leReason" class="easyui-textbox easyui-validatebox" style="width: 739px;height: 80px;resize:none" data-options="required:true,validType:'Length[0,100]'"></textarea>
					</td>
				</tr>
				
				<tr>
					<th>计划请假开始时间:</th>
					<td ><input id="planBgDtime" name="planBgDtime" placeholder="请选择起始日期" class="easyui-textbox easyui-datetimebox easyui-validatebox" data-options="formatter:valdate,editable:false" /></td>
					<th style="width: 125px">计划请假结束时间:</th>
					<td><input id="planEdDtime" name="planEdDtime" placeholder="请选择结束日期" class="easyui-textbox easyui-datetimebox easyui-validatebox" data-options="formatter:valdate,editable:false" /></td>
				</tr>
				<tr id="real" >
					<th>实际请假开始时间:</th>
					<td ><input id="realBgDtime" name="realBgDtime" placeholder="请选择起始日期" class="easyui-textbox easyui-datetimebox easyui-validatebox" data-options="formatter:valdate,editable:false" /></td>
					<th style="width: 125px">实际请假结束时间:</th>
					<td><input id="realEdDtime" name="realEdDtime" placeholder="请选择结束日期" class="easyui-textbox easyui-datetimebox easyui-validatebox" data-options="formatter:valdate,editable:false" /></td>
				</tr>
				 <tr>
					<th>备注信息:</th>
					<td colspan="3">
					  <textarea id="remark" name="remark" class="easyui-textbox easyui-validatebox" style="width: 739px; height: 80px;resize:none;"  data-options="validType:'Length[0,100]'"></textarea>
					</td>
				</tr>
				<tr>
				   <td colspan="6" style="text-align: right;">
				   	  <a id="upploadAttachment" href="javascript:void(0);" class="easyui-linkbutton"  >上传附件</a>	
					  <a id="checkAttachment" href="javascript:void(0);" class="easyui-linkbutton" >查看附件</a>	
				   	  <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-save" onclick="toSaveBaseInfo();">保存</a>
				   	  <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-no" onclick="toCloseBaseInfo();">关闭</a>
				   </td>
				</tr>
	         </table>
	       </form>
	  </div>
	</div>
	
</div>

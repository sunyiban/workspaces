package com.oasys.viewModel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.oasys.util.Constants;
/**
 * 待办任务绑定前台页面实体对象类 
 * */
public class WorkFlowTasksModel {
	
	private String processInstanceId;//流程实例编号
	private String userName;//申请人
	private Date createDate;//申请时间
	private String processName;//申请类型名称
	private String processKey;//流程类型状态标识
//	private String businessKey;//对应业务表的APP_NO字段
	private String assistant;//指定的候选人
	private String formKey;//跳转对应的受理页面JSP配置(在流程图的formKey中设置)
	private String subFormKey;//子流程中对应受理的jsp地址

	private Map<String, Object> variables = new HashMap<String, Object>();// 指定流程变量
	private String conditionsKey;//定制条件的流程变量键
	private String remark;
	
	/**  -------- 以下字段 为调用通用受理任务必须填充字段 根据个人业务填充 --------  */
	private String taskID;//任务节点ID
	private String taskComment;//审批意见
	private String result;//流程变量表达式的值 1通过 0驳回 2拒绝
	private String formKeyJson;//formKey中设置的json数据
	private String businessID;//业务表ID
	private String businessDefineKey;//流程标识 例：EmpSalPositionChgAppDI_BO_BU
	private String appNo;//业务表中的APP_NO
	private String isSuccess;//处理标识 1通过 0驳回 2拒绝
	/**调整申请节点的标识 在Constants常量类中定义 例：public static String APPLY_FOR_ADJUSTMENT="ApplyForAdjustment";*/
	private String applyStr;
	
	
	

	public WorkFlowTasksModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public WorkFlowTasksModel(String processInstanceId, String userName,
			Date createDate, String processName, String processKey,
			String businessKey, String assistant, String formKey,
			Map<String, Object> variables, String conditionsKey, String remark,
			String taskID, String taskComment, String result,
			String formKeyJson, String businessID, String businessDefineKey,
			String appNo, String isSuccess, String applyStr) {
		super();
		this.processInstanceId = processInstanceId;
		this.userName = userName;
		this.createDate = createDate;
		this.processName = processName;
		this.processKey = processKey;
//		this.businessKey = businessKey;
		this.assistant = assistant;
		this.formKey = formKey;
		this.variables = variables;
		this.conditionsKey = conditionsKey;
		this.remark = remark;
		this.taskID = taskID;
		this.taskComment = taskComment;
		this.result = result;
		this.formKeyJson = formKeyJson;
		this.businessID = businessID;
		this.businessDefineKey = businessDefineKey;
		this.appNo = appNo;
		this.isSuccess = isSuccess;
		this.applyStr = applyStr;
	}




	public String getConditionsKey() {
		return conditionsKey;
	}
	public void setConditionsKey(String conditionsKey) {
		this.conditionsKey = conditionsKey;
	}
	
	public String getApplyStr() {
		if(StringUtils.isBlank(applyStr))
			return Constants.APPLY_FOR_ADJUSTMENT;
		else
			return applyStr;
	}
	public void setApplyStr(String applyStr) {
		this.applyStr = applyStr;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTaskComment() {
		return taskComment;
	}
	public void setTaskComment(String taskComment) {
		this.taskComment = taskComment;
	}
	public String getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getFormKey() {
		return formKey;
	}
	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}
	
//	public String getBusinessKey() {
//		return businessKey;
//	}
//	public void setBusinessKey(String businessKey) {
//		this.businessKey = businessKey;
//	}
	public String getProcessKey() {
		return processKey;
	}
	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}
	
	public String getTaskID() {
		return taskID;
	}
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	
	public String getAssistant() {
		return assistant;
	}
	public void setAssistant(String assistant) {
		this.assistant = assistant;
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getFormKeyJson() {
		if(StringUtils.isNotBlank(getFormKey()) && getFormKey().indexOf("?") != -1){
			return getFormKey().substring(getFormKey().indexOf("?")+1);
		}else{
			return formKeyJson;
		}
	}
	public void setFormKeyJson(String formKeyJson) {
		this.formKeyJson = formKeyJson;
	}
	public String getBusinessID() {
		return businessID;
	}
	public void setBusinessID(String businessID) {
		this.businessID = businessID;
	}
	public String getBusinessDefineKey() {
		return businessDefineKey;
	}
	public void setBusinessDefineKey(String businessDefineKey) {
		this.businessDefineKey = businessDefineKey;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public Map<String, Object> getVariables() {
		return variables;
	}
	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}
	public String getSubFormKey() {
		return subFormKey;
	}


	public void setSubFormKey(String subFormKey) {
		this.subFormKey = subFormKey;
	}
}

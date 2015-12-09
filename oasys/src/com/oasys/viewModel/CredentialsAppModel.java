package com.oasys.viewModel;

import java.util.Date;

public class CredentialsAppModel {
	private Integer uqaId;//主表的Id
	private Integer auqaId;//附表的Id
	private String appNo;
	private Integer applicantNo;
	private String applicantName;
	private Integer deptNo;
	private String deptName;
	private Date appDate;
	private String procStatus;
	private Integer csType;
	private String csTypeName;
	private String csDesc;
	private Integer isOriginal;
	private String isOriginalName;
	private Date planUseDate;
	private Date realUseDate;
	private String realUseDateStr;//列表展示字段 实际使用时间
	private String csPurpose;
	private Integer isLetOut;
	private String isLetOutName;
	private Integer isRestored;
	private String isRestoredName;
	private Date planRestDate;
	private Date realRestDate;
	private String realRestDateStr;//列表展示字段 实际归还时间
	private String remark;
	private String taskId;
	private String formKey;// 跳转对应的受理页面JSP配置(在流程图的formKey中设置)
	/** 各个节点配置的判断与跳转控制json 
	 *   如果formKey中不设置Json跳转控制 那么默认有 通过 拒绝 驳回操作 并且 通过操作会默认传递 "1" 来进行跳转下一流程
	 *   格式为：formKey?{"type":0,"JK":"ChengShiJingLi","CF":"YingYeBuJingLi"} -----该格式跳转借款端或财富端格式
	 *   			  formKey?{"type":1,"result1":"YX1","result2":"YS1","result3":"TuanDuiJingLi"} -----该格式为判断团队经理以上或团队经理以下格式
	 *   			  formKey?{"type":2} ----该格式为只有通过和驳回操作
	 *   			  formKey?{"type":3} ----该格式为只有通过操作*/ 
	private String formKeyJson;
	
	private String assistant;// 指定的候选人
	private String isSuccess;// 是否通过标识
	private String resourcesName;// 资源文件名称
	private String definitionKey;// 流程标识Key
	private String userName;//申请人用于申请人证章归还节点，如果节点为申请人证章归还则不合并单元格
	/**
	 * task对象
	 */
	private WorkFlowTasksModel taskModel;
	
	public CredentialsAppModel() {
	}

	public CredentialsAppModel(Integer applicantNo, String applicantName,
			Integer deptNo, String deptName, Date appDate, String procStatus,
			Integer csType, String csTypeName, String csDesc,
			Integer isOriginal, String isOriginalName, Date planUseDate,
			Date realUseDate, String csPurpose, Integer isLetOut,
			String isLetOutName, Integer isRestored, String isRestoredName,
			Date planRestDate, Date realRestDate) {
		this.applicantNo = applicantNo;
		this.applicantName = applicantName;
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.appDate = appDate;
		this.procStatus = procStatus;
		this.csType = csType;
		this.csTypeName = csTypeName;
		this.csDesc = csDesc;
		this.isOriginal = isOriginal;
		this.isOriginalName = isOriginalName;
		this.planUseDate = planUseDate;
		this.realUseDate = realUseDate;
		this.csPurpose = csPurpose;
		this.isLetOut = isLetOut;
		this.isLetOutName = isLetOutName;
		this.isRestored = isRestored;
		this.isRestoredName = isRestoredName;
		this.planRestDate = planRestDate;
		this.realRestDate = realRestDate;
	}

	public Integer getUqaId() {
		return uqaId;
	}

	public void setUqaId(Integer uqaId) {
		this.uqaId = uqaId;
	}

	public Integer getAuqaId() {
		return auqaId;
	}

	public void setAuqaId(Integer auqaId) {
		this.auqaId = auqaId;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public Integer getApplicantNo() {
		return applicantNo;
	}

	public void setApplicantNo(Integer applicantNo) {
		this.applicantNo = applicantNo;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public Integer getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(Integer deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Date getAppDate() {
		return appDate;
	}

	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	public String getProcStatus() {
		return procStatus;
	}

	public void setProcStatus(String procStatus) {
		this.procStatus = procStatus;
	}

	public Integer getCsType() {
		return csType;
	}

	public void setCsType(Integer csType) {
		this.csType = csType;
	}

	public String getCsTypeName() {
		return csTypeName;
	}

	public void setCsTypeName(String csTypeName) {
		this.csTypeName = csTypeName;
	}

	public String getCsDesc() {
		return csDesc;
	}

	public void setCsDesc(String csDesc) {
		this.csDesc = csDesc;
	}

	public Integer getIsOriginal() {
		return isOriginal;
	}

	public void setIsOriginal(Integer isOriginal) {
		this.isOriginal = isOriginal;
	}

	public String getIsOriginalName() {
		return isOriginalName;
	}

	public void setIsOriginalName(String isOriginalName) {
		this.isOriginalName = isOriginalName;
	}

	public Date getPlanUseDate() {
		return planUseDate;
	}

	public void setPlanUseDate(Date planUseDate) {
		this.planUseDate = planUseDate;
	}

	public Date getRealUseDate() {
		return realUseDate;
	}

	public void setRealUseDate(Date realUseDate) {
		this.realUseDate = realUseDate;
	}
	
	public String getRealUseDateStr() {
		return realUseDateStr;
	}

	public void setRealUseDateStr(String realUseDateStr) {
		this.realUseDateStr = realUseDateStr;
	}

	public String getCsPurpose() {
		return csPurpose;
	}

	public void setCsPurpose(String csPurpose) {
		this.csPurpose = csPurpose;
	}

	public Integer getIsLetOut() {
		return isLetOut;
	}

	public void setIsLetOut(Integer isLetOut) {
		this.isLetOut = isLetOut;
	}

	public String getIsLetOutName() {
		return isLetOutName;
	}

	public void setIsLetOutName(String isLetOutName) {
		this.isLetOutName = isLetOutName;
	}

	public Integer getIsRestored() {
		return isRestored;
	}

	public void setIsRestored(Integer isRestored) {
		this.isRestored = isRestored;
	}

	public String getIsRestoredName() {
		return isRestoredName;
	}

	public void setIsRestoredName(String isRestoredName) {
		this.isRestoredName = isRestoredName;
	}

	public Date getPlanRestDate() {
		return planRestDate;
	}

	public void setPlanRestDate(Date planRestDate) {
		this.planRestDate = planRestDate;
	}

	public Date getRealRestDate() {
		return realRestDate;
	}

	public void setRealRestDate(Date realRestDate) {
		this.realRestDate = realRestDate;
	}

	public String getRealRestDateStr() {
		return realRestDateStr;
	}

	public void setRealRestDateStr(String realRestDateStr) {
		this.realRestDateStr = realRestDateStr;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getFormKey() {
		return formKey;
	}

	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

	public String getFormKeyJson() {
		return formKeyJson;
	}

	public void setFormKeyJson(String formKeyJson) {
		this.formKeyJson = formKeyJson;
	}

	public String getAssistant() {
		return assistant;
	}

	public void setAssistant(String assistant) {
		this.assistant = assistant;
	}

	public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getResourcesName() {
		return resourcesName;
	}

	public void setResourcesName(String resourcesName) {
		this.resourcesName = resourcesName;
	}

	public String getDefinitionKey() {
		return definitionKey;
	}

	public void setDefinitionKey(String definitionKey) {
		this.definitionKey = definitionKey;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public WorkFlowTasksModel getTaskModel() {
		return taskModel;
	}

	public void setTaskModel(WorkFlowTasksModel taskModel) {
		this.taskModel = taskModel;
	}
}
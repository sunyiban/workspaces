package com.oasys.viewModel;

import java.math.BigDecimal;
import java.util.Date;

public class VehicleExpensesAppModel {
	/*主表*/
	private Integer veaId;
	private String appNo;
	private Integer applicantNo;
	private Integer deptNo;
	private Date appDate;
	private String appStatus;
	private String carNo;
	private String bankName;
	private String intoAct;
	private String actName;
	private Integer feeInfoId;
	private Character feeType;
	private String feeTypeOther;
	private Character procStatus;
	private String remark;
	/*高速路费*/
	private Integer efiId;
	private BigDecimal appAmtGaoSu;
	private Date paymentDate;
	private String tollgateName;
    /*加油费*/
	private Integer ffiId;
	private BigDecimal appAmtJiaYou;
	private BigDecimal bgMileageJiaYou;
	private BigDecimal edMileageJiaYou;
	private Date prevAppDate;
	private BigDecimal cardBalance;
	/*保养费*/
	private Integer mfiId;
	private BigDecimal appAmtBaoYang;
	private BigDecimal bgMileageBaoYang;
	private BigDecimal edMileageBaoYang;
	private Date prevAppDateBaoYang;
	/*停车费*/
	private Integer pfiId;
	private BigDecimal appAmtTingChe;
	private Date parkingDtime;
	private String parkingPlace;
	/*维修费*/
	private Integer rfiId;
	private BigDecimal appAmtWeiXiu;
	private String repairItem;
	private String repairReson;
	/*保险费*/
	private Integer ifiId;
	private BigDecimal appAmtBaoXian;
	private BigDecimal bgMileageBaoXian;
	private BigDecimal edMileageBaoXian;
	private Date insuranceBgDtime;
	private Date insuranceEdDtime;
	private String icName;

	private String userName;
	private String deptName;
	private String position;
	
	private String taskId;//任务Id
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
	/**
	 * task对象
	 */
	private WorkFlowTasksModel taskModel;
	
	public VehicleExpensesAppModel() {
	}
	public VehicleExpensesAppModel(Integer veaId, String appNo,
			Integer applicantNo, Integer deptNo, Date appDate, String appStatus,
			String carNo, String bankName, String intoAct, String actName,
			Integer feeInfoId, Character feeType, String feeTypeOther,
			Character procStatus, String remark, Integer efiId, BigDecimal appAmtGaoSu,
			Date paymentDate, String tollgateName, Integer ffiId,
			BigDecimal appAmtJiaYou, BigDecimal bgMileageJiaYou,
			BigDecimal edMileageJiaYou, Date prevAppDate, BigDecimal cardBalance,
			Integer mfiId, BigDecimal appAmtBaoYang, BigDecimal bgMileageBaoYang,
			BigDecimal edMileageBaoYang, Date prevAppDateBaoYang, Integer pfiId,
			BigDecimal appAmtTingChe, Date parkingDtime, String parkingPlace,
			Integer rfiId, BigDecimal appAmtWeiXiu, String repairItem,
			String repairReson, Integer ifiId, BigDecimal appAmtBaoXian,
			BigDecimal bgMileageBaoXian, BigDecimal edMileageBaoXian, Date insuranceBgDtime,
			Date insuranceEdDtime, String icName) {
		this.veaId = veaId;
		this.appNo = appNo;
		this.applicantNo = applicantNo;
		this.deptNo = deptNo;
		this.appDate = appDate;
		this.appStatus = appStatus;
		this.carNo = carNo;
		this.bankName = bankName;
		this.intoAct = intoAct;
		this.actName = actName;
		this.feeInfoId = feeInfoId;
		this.feeType = feeType;
		this.feeTypeOther = feeTypeOther;
		this.procStatus = procStatus;
		this.remark = remark;
		this.efiId = efiId;
		this.appAmtGaoSu = appAmtGaoSu;
		this.paymentDate = paymentDate;
		this.tollgateName = tollgateName;
		this.ffiId = ffiId;
		this.appAmtJiaYou = appAmtJiaYou;
		this.bgMileageJiaYou = bgMileageJiaYou;
		this.edMileageJiaYou = edMileageJiaYou;
		this.prevAppDate = prevAppDate;
		this.cardBalance = cardBalance;
		this.mfiId = mfiId;
		this.appAmtBaoYang = appAmtBaoYang;
		this.bgMileageBaoYang = bgMileageBaoYang;
		this.edMileageBaoYang = edMileageBaoYang;
		this.prevAppDateBaoYang = prevAppDateBaoYang;
		this.pfiId = pfiId;
		this.appAmtTingChe = appAmtTingChe;
		this.parkingDtime = parkingDtime;
		this.parkingPlace = parkingPlace;
		this.rfiId = rfiId;
		this.appAmtWeiXiu = appAmtWeiXiu;
		this.repairItem = repairItem;
		this.repairReson = repairReson;
		this.ifiId = ifiId;
		this.appAmtBaoXian = appAmtBaoXian;
		this.bgMileageBaoXian = bgMileageBaoXian;
		this.edMileageBaoXian = edMileageBaoXian;
		this.insuranceBgDtime = insuranceBgDtime;
		this.insuranceEdDtime = insuranceEdDtime;
		this.icName = icName;
	}
	public Integer getVeaId() {
		return veaId;
	}
	public void setVeaId(Integer veaId) {
		this.veaId = veaId;
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
	public Integer getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(Integer deptNo) {
		this.deptNo = deptNo;
	}
	public Date getAppDate() {
		return appDate;
	}
	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getIntoAct() {
		return intoAct;
	}
	public void setIntoAct(String intoAct) {
		this.intoAct = intoAct;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public Integer getFeeInfoId() {
		return feeInfoId;
	}
	public void setFeeInfoId(Integer feeInfoId) {
		this.feeInfoId = feeInfoId;
	}
	public Character getFeeType() {
		return feeType;
	}
	public void setFeeType(Character feeType) {
		this.feeType = feeType;
	}
	public String getFeeTypeOther() {
		return feeTypeOther;
	}
	public void setFeeTypeOther(String feeTypeOther) {
		this.feeTypeOther = feeTypeOther;
	}
	public Character getProcStatus() {
		return procStatus;
	}
	public void setProcStatus(Character procStatus) {
		this.procStatus = procStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getEfiId() {
		return efiId;
	}
	public void setEfiId(Integer efiId) {
		this.efiId = efiId;
	}
	public BigDecimal getAppAmtGaoSu() {
		return appAmtGaoSu;
	}
	public void setAppAmtGaoSu(BigDecimal appAmtGaoSu) {
		this.appAmtGaoSu = appAmtGaoSu;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getTollgateName() {
		return tollgateName;
	}
	public void setTollgateName(String tollgateName) {
		this.tollgateName = tollgateName;
	}
	public Integer getFfiId() {
		return ffiId;
	}
	public void setFfiId(Integer ffiId) {
		this.ffiId = ffiId;
	}
	public BigDecimal getAppAmtJiaYou() {
		return appAmtJiaYou;
	}
	public void setAppAmtJiaYou(BigDecimal appAmtJiaYou) {
		this.appAmtJiaYou = appAmtJiaYou;
	}
	public BigDecimal getBgMileageJiaYou() {
		return bgMileageJiaYou;
	}
	public void setBgMileageJiaYou(BigDecimal bgMileageJiaYou) {
		this.bgMileageJiaYou = bgMileageJiaYou;
	}
	public BigDecimal getEdMileageJiaYou() {
		return edMileageJiaYou;
	}
	public void setEdMileageJiaYou(BigDecimal edMileageJiaYou) {
		this.edMileageJiaYou = edMileageJiaYou;
	}
	public Date getPrevAppDate() {
		return prevAppDate;
	}
	public void setPrevAppDate(Date prevAppDate) {
		this.prevAppDate = prevAppDate;
	}
	public BigDecimal getCardBalance() {
		return cardBalance;
	}
	public void setCardBalance(BigDecimal cardBalance) {
		this.cardBalance = cardBalance;
	}
	public Integer getMfiId() {
		return mfiId;
	}
	public void setMfiId(Integer mfiId) {
		this.mfiId = mfiId;
	}
	public BigDecimal getAppAmtBaoYang() {
		return appAmtBaoYang;
	}
	public void setAppAmtBaoYang(BigDecimal appAmtBaoYang) {
		this.appAmtBaoYang = appAmtBaoYang;
	}
	public BigDecimal getBgMileageBaoYang() {
		return bgMileageBaoYang;
	}
	public void setBgMileageBaoYang(BigDecimal bgMileageBaoYang) {
		this.bgMileageBaoYang = bgMileageBaoYang;
	}
	public BigDecimal getEdMileageBaoYang() {
		return edMileageBaoYang;
	}
	public void setEdMileageBaoYang(BigDecimal edMileageBaoYang) {
		this.edMileageBaoYang = edMileageBaoYang;
	}
	public Date getPrevAppDateBaoYang() {
		return prevAppDateBaoYang;
	}
	public void setPrevAppDateBaoYang(Date prevAppDateBaoYang) {
		this.prevAppDateBaoYang = prevAppDateBaoYang;
	}
	public Integer getPfiId() {
		return pfiId;
	}
	public void setPfiId(Integer pfiId) {
		this.pfiId = pfiId;
	}
	public BigDecimal getAppAmtTingChe() {
		return appAmtTingChe;
	}
	public void setAppAmtTingChe(BigDecimal appAmtTingChe) {
		this.appAmtTingChe = appAmtTingChe;
	}
	public Date getParkingDtime() {
		return parkingDtime;
	}
	public void setParkingDtime(Date parkingDtime) {
		this.parkingDtime = parkingDtime;
	}
	public String getParkingPlace() {
		return parkingPlace;
	}
	public void setParkingPlace(String parkingPlace) {
		this.parkingPlace = parkingPlace;
	}
	public Integer getRfiId() {
		return rfiId;
	}
	public void setRfiId(Integer rfiId) {
		this.rfiId = rfiId;
	}
	public BigDecimal getAppAmtWeiXiu() {
		return appAmtWeiXiu;
	}
	public void setAppAmtWeiXiu(BigDecimal appAmtWeiXiu) {
		this.appAmtWeiXiu = appAmtWeiXiu;
	}
	public String getRepairItem() {
		return repairItem;
	}
	public void setRepairItem(String repairItem) {
		this.repairItem = repairItem;
	}
	public String getRepairReson() {
		return repairReson;
	}
	public void setRepairReson(String repairReson) {
		this.repairReson = repairReson;
	}
	public Integer getIfiId() {
		return ifiId;
	}
	public void setIfiId(Integer ifiId) {
		this.ifiId = ifiId;
	}
	public BigDecimal getAppAmtBaoXian() {
		return appAmtBaoXian;
	}
	public void setAppAmtBaoXian(BigDecimal appAmtBaoXian) {
		this.appAmtBaoXian = appAmtBaoXian;
	}
	public BigDecimal getBgMileageBaoXian() {
		return bgMileageBaoXian;
	}
	public void setBgMileageBaoXian(BigDecimal bgMileageBaoXian) {
		this.bgMileageBaoXian = bgMileageBaoXian;
	}
	public BigDecimal getEdMileageBaoXian() {
		return edMileageBaoXian;
	}
	public void setEdMileageBaoXian(BigDecimal edMileageBaoXian) {
		this.edMileageBaoXian = edMileageBaoXian;
	}
	public Date getInsuranceBgDtime() {
		return insuranceBgDtime;
	}
	public void setInsuranceBgDtime(Date insuranceBgDtime) {
		this.insuranceBgDtime = insuranceBgDtime;
	}
	public Date getInsuranceEdDtime() {
		return insuranceEdDtime;
	}
	public void setInsuranceEdDtime(Date insuranceEdDtime) {
		this.insuranceEdDtime = insuranceEdDtime;
	}
	public String getIcName() {
		return icName;
	}
	public void setIcName(String icName) {
		this.icName = icName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
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
	public WorkFlowTasksModel getTaskModel() {
		return taskModel;
	}
	public void setTaskModel(WorkFlowTasksModel taskModel) {
		this.taskModel = taskModel;
	}
}

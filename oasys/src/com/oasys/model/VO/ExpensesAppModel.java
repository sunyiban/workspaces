package com.oasys.model.VO;

import java.math.BigDecimal;
import java.util.Date;

import com.oasys.viewModel.WorkFlowTasksModel;

public class ExpensesAppModel implements java.io.Serializable,Cloneable{

	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
	private Integer btaId;
	/**
	 * 申请编号
	 */
	private String appNo;
	/**
	 * 申请人id
	 */
	private Integer applicantNo;
	/**
	 * 所在部门id
	 */
	private Integer deptNo;
	/**
	 * 申请时间
	 */
	private Date appDate;
	/**
	 * 申请类型
	 */
	private String expType;
	/**
	 * 申请状态
	 */
	private String appStatus;
	/**
	 * 费用用途
	 */
	private String expReson;
	/**
	 * 单价
	 */
	private String price;
	/**
	 * 规格型号
	 */
	private String model;
	/**
	 * 申请金额
	 */
	private String appAmt;
	/**
	 * 支付方式
	 */
	private String payMode;
	/**
	 * 转入账号
	 */
	private String intoAct;
	/**
	 * 申请数量
	 */
	private BigDecimal appQty;
	/**
	 * 现有数量
	 */
	private BigDecimal alreadyQty;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 账户名称
	 */
	private String actName;
	/**
	 * 票据类型
	 */
	private String billType;
	/**
	 * 其他票据类型
	 */
	private String billTypeOther;
	/**
	 * 流程状态
	 */
	private String procStatus;
	/**
	 * 备注信息
	 */
	private String remark;
	
	
	
	/**
	 * 所在部门名称
	 */
	private String fullName;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 支付方式名称
	 */
	private String payModeName;
	/**
	 * 票据类型名称
	 */
	private String billTypeName;
	/**
	 * 费用申请
	 */
	private String expTypeName;
	/**
	 * 最小申请金额
	 */
	private BigDecimal appAmtMini;
	/**
	 *  最大申请金额
	 */
	private BigDecimal appAmtMax;
	/**
	 * 最小申请日期
	 */
	private String appDateMini;
	/**
	 * 最小申请日期
	 */
	private String appDateMax;
	/**
	 * taskmodel
	 */
	private WorkFlowTasksModel taskModel;
	/**
	 * taskid
	 */
	private String taskId;
	/**
	 * id字符串
	 */
	private String ids;
	
	private String formKey;
	
	/**
	 * 流程图的id
	 */
	private String processKey;
	
	public String getProcessKey() {
		return processKey;
	}
	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}
	public Integer getBtaId() {
		return btaId;
	}
	public void setBtaId(Integer btaId) {
		this.btaId = btaId;
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
	public String getExpReson() {
		return expReson;
	}
	public void setExpReson(String expReson) {
		this.expReson = expReson;
	}
	public String getAppAmt() {
		return appAmt;
	}
	public void setAppAmt(String appAmt) {
		this.appAmt = appAmt;
	}
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	public String getIntoAct() {
		return intoAct;
	}
	public void setIntoAct(String intoAct) {
		this.intoAct = intoAct;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getBillTypeOther() {
		return billTypeOther;
	}
	public void setBillTypeOther(String billTypeOther) {
		this.billTypeOther = billTypeOther;
	}
	public String getProcStatus() {
		return procStatus;
	}
	public void setProcStatus(String procStatus) {
		this.procStatus = procStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPayModeName() {
		return payModeName;
	}
	public void setPayModeName(String payModeName) {
		this.payModeName = payModeName;
	}
	public String getBillTypeName() {
		return billTypeName;
	}
	public void setBillTypeName(String billTypeName) {
		this.billTypeName = billTypeName;
	}
	public BigDecimal getAppAmtMini() {
		return appAmtMini;
	}
	public void setAppAmtMini(BigDecimal appAmtMini) {
		this.appAmtMini = appAmtMini;
	}
	public BigDecimal getAppAmtMax() {
		return appAmtMax;
	}
	public void setAppAmtMax(BigDecimal appAmtMax) {
		this.appAmtMax = appAmtMax;
	}
	public String getAppDateMini() {
		return appDateMini;
	}
	public void setAppDateMini(String appDateMini) {
		this.appDateMini = appDateMini;
	}
	public String getAppDateMax() {
		return appDateMax;
	}
	public void setAppDateMax(String appDateMax) {
		this.appDateMax = appDateMax;
	}
	public WorkFlowTasksModel getTaskModel() {
		return taskModel;
	}
	public void setTaskModel(WorkFlowTasksModel taskModel) {
		this.taskModel = taskModel;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getExpType() {
		return expType;
	}
	public void setExpType(String expType) {
		this.expType = expType;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public BigDecimal getAppQty() {
		return appQty;
	}
	public void setAppQty(BigDecimal appQty) {
		this.appQty = appQty;
	}
	public BigDecimal getAlreadyQty() {
		return alreadyQty;
	}
	public void setAlreadyQty(BigDecimal alreadyQty) {
		this.alreadyQty = alreadyQty;
	}
	public String getExpTypeName() {
		return expTypeName;
	}
	public void setExpTypeName(String expTypeName) {
		this.expTypeName = expTypeName;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getFormKey() {
		return formKey;
	}
	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}
	@Override
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}

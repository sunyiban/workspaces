package com.oasys.model;

// Generated 2015-12-4 9:32:01 by Hibernate Tools 4.0.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.oasys.viewModel.WorkFlowTasksModel;

/**
 * TOaAdPpeUsedConfirmApp generated by hbm2java
 */
@Entity
@Table(name = "t_oa_ad_ppe_used_confirm_app", catalog = "oasys", uniqueConstraints = @UniqueConstraint(columnNames = "APP_NO"))
public class PpeUsedConfirmApp implements java.io.Serializable ,Cloneable{

	private Integer psaId;
	private String appNo;
	/**申请人id*/
	private Integer applicantNo;
	/**申请人部门id*/
	private Integer appDept;
	private Date appDate;
	private String appStatus;
	/**使用性质*/
	private String useNature;
	/**数量*/
	private Integer usedQty;
	/**使用人id*/
	private Integer user;
	/**使用人部门id*/
	private Integer userDept;
	private String procStatus;
	private String remark;
	
	/**申请名字*/
	private String applicantName;
	/**申请人部门名字*/
	private String appDeptName;
	/**使用人名字*/
	private String userName;
	/**使用人部门名字*/
	private String userDeptName;
	 /**开始申请时间
	 */
	private String appDateS;
	/**
	 * //结束时间
	 */
	private String appDateE;
	
	
	/**固定资产编号*/
	private String ppeNo;
	/***
	 * 固定资产名称
	 */
	private String ppeName;
	/**借款端、财富端*/
	private String myId;
	/**附加表id*/
	private Integer puaPsaId;
	/**归还人id*/
	private Integer reverter;
	
	
	/** WorkFlow propertites */
	private WorkFlowTasksModel taskModel;//task对象
	private String result;//审批通过或驳回标识
	private String taskID;//任务taskID
	private String formKey;//跳转对应的受理页面JSP配置(在流程图的formKey中设置)
	private String assistant;//指定的候选人
	private String isSuccess;//是否通过标识
	private String ideaRemark;//审批意见
	private String definitionKey;//流程表示key
	
	
	
	public PpeUsedConfirmApp() {
	}

	

	public PpeUsedConfirmApp(Integer psaId, String appNo, Integer applicantNo,
			Integer appDept, Date appDate, String appStatus, String useNature,
			Integer usedQty, Integer user, Integer userDept, String procStatus,
			String remark, String applicantName, String appDeptName,
			String userName, String userDeptName, String appDateS,
			String appDateE, String ppeNo, String ppeName,
			WorkFlowTasksModel taskModel, String result, String taskID,
			String formKey, String assistant, String isSuccess,
			String ideaRemark, String definitionKey,String myId,Integer puaPsaId,Integer reverter) {
		super();
		this.psaId = psaId;
		this.appNo = appNo;
		this.applicantNo = applicantNo;
		this.appDept = appDept;
		this.appDate = appDate;
		this.appStatus = appStatus;
		this.useNature = useNature;
		this.usedQty = usedQty;
		this.user = user;
		this.userDept = userDept;
		this.procStatus = procStatus;
		this.remark = remark;
		this.applicantName = applicantName;
		this.appDeptName = appDeptName;
		this.userName = userName;
		this.userDeptName = userDeptName;
		this.appDateS = appDateS;
		this.appDateE = appDateE;
		this.ppeNo = ppeNo;
		this.ppeName = ppeName;
		this.taskModel = taskModel;
		this.result = result;
		this.taskID = taskID;
		this.formKey = formKey;
		this.assistant = assistant;
		this.isSuccess = isSuccess;
		this.ideaRemark = ideaRemark;
		this.definitionKey = definitionKey;
		this.myId=myId;
		this.puaPsaId=puaPsaId;
		this.reverter=reverter;
	}

	@Transient
	public Integer getReverter() {
		return reverter;
	}



	public void setReverter(Integer reverter) {
		this.reverter = reverter;
	}



	@Transient
	public Integer getPuaPsaId() {
		return puaPsaId;
	}



	public void setPuaPsaId(Integer puaPsaId) {
		this.puaPsaId = puaPsaId;
	}



	@Transient
	public String getMyId() {
		return myId;
	}



	public void setMyId(String myId) {
		this.myId = myId;
	}



	@Transient
	public String getApplicantName() {
		return applicantName;
	}


	
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}


	@Transient
	public String getAppDeptName() {
		return appDeptName;
	}



	public void setAppDeptName(String appDeptName) {
		this.appDeptName = appDeptName;
	}


	@Transient
	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}


	@Transient
	public String getUserDeptName() {
		return userDeptName;
	}



	public void setUserDeptName(String userDeptName) {
		this.userDeptName = userDeptName;
	}


	@Transient
	public String getAppDateS() {
		return appDateS;
	}



	public void setAppDateS(String appDateS) {
		this.appDateS = appDateS;
	}


	@Transient
	public String getAppDateE() {
		return appDateE;
	}



	public void setAppDateE(String appDateE) {
		this.appDateE = appDateE;
	}


	@Transient
	public String getPpeNo() {
		return ppeNo;
	}



	public void setPpeNo(String ppeNo) {
		this.ppeNo = ppeNo;
	}


	@Transient
	public String getPpeName() {
		return ppeName;
	}



	public void setPpeName(String ppeName) {
		this.ppeName = ppeName;
	}


	@Transient
	public WorkFlowTasksModel getTaskModel() {
		return taskModel;
	}



	public void setTaskModel(WorkFlowTasksModel taskModel) {
		this.taskModel = taskModel;
	}


	@Transient
	public String getResult() {
		return result;
	}



	public void setResult(String result) {
		this.result = result;
	}


	@Transient
	public String getTaskID() {
		return taskID;
	}



	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}


	@Transient
	public String getFormKey() {
		return formKey;
	}



	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}


	@Transient
	public String getAssistant() {
		return assistant;
	}



	public void setAssistant(String assistant) {
		this.assistant = assistant;
	}


	@Transient
	public String getIsSuccess() {
		return isSuccess;
	}



	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}


	@Transient
	public String getIdeaRemark() {
		return ideaRemark;
	}



	public void setIdeaRemark(String ideaRemark) {
		this.ideaRemark = ideaRemark;
	}


	@Transient
	public String getDefinitionKey() {
		return definitionKey;
	}



	public void setDefinitionKey(String definitionKey) {
		this.definitionKey = definitionKey;
	}



	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "PSA_ID", unique = true, nullable = false)
	public Integer getPsaId() {
		return this.psaId;
	}

	public void setPsaId(Integer psaId) {
		this.psaId = psaId;
	}

	@Column(name = "APP_NO", unique = true, length = 25)
	public String getAppNo() {
		return this.appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	@Column(name = "APPLICANT_NO")
	public Integer getApplicantNo() {
		return this.applicantNo;
	}

	public void setApplicantNo(Integer applicantNo) {
		this.applicantNo = applicantNo;
	}

	@Column(name = "APP_DEPT")
	public Integer getAppDept() {
		return this.appDept;
	}

	public void setAppDept(Integer appDept) {
		this.appDept = appDept;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "APP_DATE", length = 10)
	public Date getAppDate() {
		return this.appDate;
	}

	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	@Column(name = "APP_STATUS", length = 100)
	public String getAppStatus() {
		return this.appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	@Column(name = "USE_NATURE", length = 1)
	public String getUseNature() {
		return this.useNature;
	}

	public void setUseNature(String useNature) {
		this.useNature = useNature;
	}

	@Column(name = "USED_QTY")
	public Integer getUsedQty() {
		return this.usedQty;
	}

	public void setUsedQty(Integer usedQty) {
		this.usedQty = usedQty;
	}

	@Column(name = "USER")
	public Integer getUser() {
		return this.user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	@Column(name = "USER_DEPT")
	public Integer getUserDept() {
		return this.userDept;
	}

	public void setUserDept(Integer userDept) {
		this.userDept = userDept;
	}

	@Column(name = "PROC_STATUS", length = 1)
	public String getProcStatus() {
		return this.procStatus;
	}

	public void setProcStatus(String procStatus) {
		this.procStatus = procStatus;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

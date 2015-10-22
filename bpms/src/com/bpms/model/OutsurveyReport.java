package com.bpms.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author liuhh 外访调查报告对应的实体类
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_bp_outsurvey_report")
public class OutsurveyReport implements java.io.Serializable {

	private String outsurveyReportId;
	private String loanOrderId;
	private String surveyer; //调查人员
	private Date surveyDate; //调查时间
	private String distance;//往返里程(km)
	private String customerName;//客户姓名
	private String comAddress;//经营地址
	private String isLocal;//是否本地人
	private String homeAddress;//家庭地址
	private String isOwn;//是否自有
	private String carInfo;//车产信息
	private String isGuaranty;//是否抵押
	private String otherInfo;//其他
	private String actPurpose;//实际贷款目的
	private BigDecimal actualAmt;//实际资金需求(万元)
	private String oralEmployee;//客户口述员工总数
	private String inviewEmployee;//可见员工数
	private String inviewCustomer;//可见客户数
	private String offSeason;//淡季(月份)
	private String busySeason;//旺季(月份)
	private String shoulderSeason;//平季(月份)
	private BigDecimal offSeasonAmt;//淡季营业额
	private BigDecimal buysSeasonAmt;//旺季营业额
	private BigDecimal shoulderSeasonAmt;//平季营业额
	private String grossMargin;//(选填) 毛利率
	private String netMargin;///净利率
	private String increaseMargin;//加价率
	private String oralGrossMargin;//(选填) 口述毛利率
	private String oralNetmargin;//口述净利润
	private String businessHis;//简述经营历史
	private String businessModel;//现经营模式/情况
	private String updownSituation;//上下游情况(包括结款方式及占比)
	private String businessAssetSitutaion;//生意资产情况
	private String loanSituation;//贷款情况(公司/银行、期数、金额、月还款金额)
	private String manageStaticFee;//经营基本费用
	private String otherBusinessProject;//其它经营项目(具体项目、收支等情况)
	private String familySituation;//家庭情况(家庭成员收入开支等)
	private String otherSituation;//调查中其他需说明情况

	public OutsurveyReport() {
	}

	public OutsurveyReport(String outsurveyReportId, Date surveyDate) {
		this.outsurveyReportId = outsurveyReportId;
		this.surveyDate = surveyDate;
	}

	public OutsurveyReport(String outsurveyReportId, String loanOrderId,
			String surveyer, Date surveyDate, String distance,
			String customerName, String comAddress, String isLocal,
			String homeAddress, String isOwn, String carInfo,
			String isGuaranty, String otherInfo, String actPurpose,
			BigDecimal actualAmt, String oralEmployee, String inviewEmployee,
			String inviewCustomer, String offSeason, String busySeason,
			String shoulderSeason, BigDecimal offSeasonAmt,
			BigDecimal buysSeasonAmt, BigDecimal shoulderSeasonAmt,
			String grossMargin, String netMargin, String increaseMargin,
			String oralGrossMargin, String oralNetmargin, String businessHis,
			String businessModel, String updownSituation,
			String businessAssetSitutaion, String loanSituation,
			String manageStaticFee, String otherBusinessProject,
			String familySituation, String otherSituation) {
		this.outsurveyReportId = outsurveyReportId;
		this.loanOrderId = loanOrderId;
		this.surveyer = surveyer;
		this.surveyDate = surveyDate;
		this.distance = distance;
		this.customerName = customerName;
		this.comAddress = comAddress;
		this.isLocal = isLocal;
		this.homeAddress = homeAddress;
		this.isOwn = isOwn;
		this.carInfo = carInfo;
		this.isGuaranty = isGuaranty;
		this.otherInfo = otherInfo;
		this.actPurpose = actPurpose;
		this.actualAmt = actualAmt;
		this.oralEmployee = oralEmployee;
		this.inviewEmployee = inviewEmployee;
		this.inviewCustomer = inviewCustomer;
		this.offSeason = offSeason;
		this.busySeason = busySeason;
		this.shoulderSeason = shoulderSeason;
		this.offSeasonAmt = offSeasonAmt;
		this.buysSeasonAmt = buysSeasonAmt;
		this.shoulderSeasonAmt = shoulderSeasonAmt;
		this.grossMargin = grossMargin;
		this.netMargin = netMargin;
		this.increaseMargin = increaseMargin;
		this.oralGrossMargin = oralGrossMargin;
		this.oralNetmargin = oralNetmargin;
		this.businessHis = businessHis;
		this.businessModel = businessModel;
		this.updownSituation = updownSituation;
		this.businessAssetSitutaion = businessAssetSitutaion;
		this.loanSituation = loanSituation;
		this.manageStaticFee = manageStaticFee;
		this.otherBusinessProject = otherBusinessProject;
		this.familySituation = familySituation;
		this.otherSituation = otherSituation;
	}

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "OUTSURVEY_REPORT_ID", insertable = true, updatable = true, nullable = false, length = 40, unique = true)
	public String getOutsurveyReportId() {
		return this.outsurveyReportId;
	}

	public void setOutsurveyReportId(String outsurveyReportId) {
		this.outsurveyReportId = outsurveyReportId;
	}

	@Column(name = "LOAN_ORDER_ID", length = 40)
	public String getLoanOrderId() {
		return this.loanOrderId;
	}

	public void setLoanOrderId(String loanOrderId) {
		this.loanOrderId = loanOrderId;
	}

	@Column(name = "SURVEYER", length = 40)
	public String getSurveyer() {
		return this.surveyer;
	}

	public void setSurveyer(String surveyer) {
		this.surveyer = surveyer;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SURVEY_DATE", nullable = false, length = 19)
	public Date getSurveyDate() {
		return this.surveyDate;
	}

	public void setSurveyDate(Date surveyDate) {
		this.surveyDate = surveyDate;
	}

	@Column(name = "DISTANCE", length = 40)
	public String getDistance() {
		return this.distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	@Column(name = "CUSTOMER_NAME", length = 40)
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column(name = "COM_ADDRESS", length = 120)
	public String getComAddress() {
		return this.comAddress;
	}

	public void setComAddress(String comAddress) {
		this.comAddress = comAddress;
	}

	@Column(name = "IS_LOCAL", length = 1)
	public String getIsLocal() {
		return this.isLocal;
	}

	public void setIsLocal(String isLocal) {
		this.isLocal = isLocal;
	}

	@Column(name = "HOME_ADDRESS", length = 120)
	public String getHomeAddress() {
		return this.homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	@Column(name = "IS_OWN", length = 1)
	public String getIsOwn() {
		return this.isOwn;
	}

	public void setIsOwn(String isOwn) {
		this.isOwn = isOwn;
	}

	@Column(name = "CAR_INFO", length = 120)
	public String getCarInfo() {
		return this.carInfo;
	}

	public void setCarInfo(String carInfo) {
		this.carInfo = carInfo;
	}

	@Column(name = "IS_GUARANTY", length = 1)
	public String getIsGuaranty() {
		return this.isGuaranty;
	}

	public void setIsGuaranty(String isGuaranty) {
		this.isGuaranty = isGuaranty;
	}

	@Column(name = "OTHER_INFO", length = 120)
	public String getOtherInfo() {
		return this.otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	@Column(name = "ACT_PURPOSE", length = 120)
	public String getActPurpose() {
		return this.actPurpose;
	}

	public void setActPurpose(String actPurpose) {
		this.actPurpose = actPurpose;
	}

	@Column(name = "ACTUAL_AMT", precision = 20, scale = 5)
	public BigDecimal getActualAmt() {
		return this.actualAmt;
	}

	public void setActualAmt(BigDecimal actualAmt) {
		this.actualAmt = actualAmt;
	}

	@Column(name = "ORAL_EMPLOYEE", length = 10)
	public String getOralEmployee() {
		return this.oralEmployee;
	}

	public void setOralEmployee(String oralEmployee) {
		this.oralEmployee = oralEmployee;
	}

	@Column(name = "INVIEW_EMPLOYEE", length = 10)
	public String getInviewEmployee() {
		return this.inviewEmployee;
	}

	public void setInviewEmployee(String inviewEmployee) {
		this.inviewEmployee = inviewEmployee;
	}

	@Column(name = "INVIEW_CUSTOMER", length = 10)
	public String getInviewCustomer() {
		return this.inviewCustomer;
	}

	public void setInviewCustomer(String inviewCustomer) {
		this.inviewCustomer = inviewCustomer;
	}

	@Column(name = "OFF_SEASON", length = 2)
	public String getOffSeason() {
		return this.offSeason;
	}

	public void setOffSeason(String offSeason) {
		this.offSeason = offSeason;
	}

	@Column(name = "BUSY_SEASON", length = 2)
	public String getBusySeason() {
		return this.busySeason;
	}

	public void setBusySeason(String busySeason) {
		this.busySeason = busySeason;
	}

	@Column(name = "SHOULDER_SEASON", length = 2)
	public String getShoulderSeason() {
		return this.shoulderSeason;
	}

	public void setShoulderSeason(String shoulderSeason) {
		this.shoulderSeason = shoulderSeason;
	}

	@Column(name = "OFF_SEASON_AMT", precision = 20, scale = 5)
	public BigDecimal getOffSeasonAmt() {
		return this.offSeasonAmt;
	}

	public void setOffSeasonAmt(BigDecimal offSeasonAmt) {
		this.offSeasonAmt = offSeasonAmt;
	}

	@Column(name = "BUYS_SEASON_AMT", precision = 20, scale = 5)
	public BigDecimal getBuysSeasonAmt() {
		return this.buysSeasonAmt;
	}

	public void setBuysSeasonAmt(BigDecimal buysSeasonAmt) {
		this.buysSeasonAmt = buysSeasonAmt;
	}

	@Column(name = "SHOULDER_SEASON_AMT", precision = 20, scale = 5)
	public BigDecimal getShoulderSeasonAmt() {
		return this.shoulderSeasonAmt;
	}

	public void setShoulderSeasonAmt(BigDecimal shoulderSeasonAmt) {
		this.shoulderSeasonAmt = shoulderSeasonAmt;
	}

	@Column(name = "GROSS_MARGIN", length = 5)
	public String getGrossMargin() {
		return this.grossMargin;
	}

	public void setGrossMargin(String grossMargin) {
		this.grossMargin = grossMargin;
	}

	@Column(name = "NET_MARGIN", length = 5)
	public String getNetMargin() {
		return this.netMargin;
	}

	public void setNetMargin(String netMargin) {
		this.netMargin = netMargin;
	}

	@Column(name = "INCREASE_MARGIN", length = 5)
	public String getIncreaseMargin() {
		return this.increaseMargin;
	}

	public void setIncreaseMargin(String increaseMargin) {
		this.increaseMargin = increaseMargin;
	}

	@Column(name = "ORAL_GROSS_MARGIN", length = 5)
	public String getOralGrossMargin() {
		return this.oralGrossMargin;
	}

	public void setOralGrossMargin(String oralGrossMargin) {
		this.oralGrossMargin = oralGrossMargin;
	}

	@Column(name = "ORAL_NETMARGIN", length = 5)
	public String getOralNetmargin() {
		return this.oralNetmargin;
	}

	public void setOralNetmargin(String oralNetmargin) {
		this.oralNetmargin = oralNetmargin;
	}

	@Column(name = "BUSINESS_HIS", length = 2500)
	public String getBusinessHis() {
		return this.businessHis;
	}

	public void setBusinessHis(String businessHis) {
		this.businessHis = businessHis;
	}

	@Column(name = "BUSINESS_MODEL", length = 40)
	public String getBusinessModel() {
		return this.businessModel;
	}

	public void setBusinessModel(String businessModel) {
		this.businessModel = businessModel;
	}

	@Column(name = "UPDOWN_SITUATION", length = 120)
	public String getUpdownSituation() {
		return this.updownSituation;
	}

	public void setUpdownSituation(String updownSituation) {
		this.updownSituation = updownSituation;
	}

	@Column(name = "BUSINESS_ASSET_SITUTAION", length = 120)
	public String getBusinessAssetSitutaion() {
		return this.businessAssetSitutaion;
	}

	public void setBusinessAssetSitutaion(String businessAssetSitutaion) {
		this.businessAssetSitutaion = businessAssetSitutaion;
	}

	@Column(name = "LOAN_SITUATION", length = 120)
	public String getLoanSituation() {
		return this.loanSituation;
	}

	public void setLoanSituation(String loanSituation) {
		this.loanSituation = loanSituation;
	}

	/*@Column(name = "MANAGE_STATIC_FEE", precision = 20, scale = 5)
	public BigDecimal getManageStaticFee() {
		return this.manageStaticFee;
	}

	public void setManageStaticFee(BigDecimal manageStaticFee) {
		this.manageStaticFee = manageStaticFee;
	}*/
	@Column(name = "MANAGE_STATIC_FEE", length = 2500)
	public String getManageStaticFee() {
		return manageStaticFee;
	}

	public void setManageStaticFee(String manageStaticFee) {
		this.manageStaticFee = manageStaticFee;
	}
	
	@Column(name = "OTHER_BUSINESS_PROJECT", length = 120)
	public String getOtherBusinessProject() {
		return this.otherBusinessProject;
	}

	public void setOtherBusinessProject(String otherBusinessProject) {
		this.otherBusinessProject = otherBusinessProject;
	}

	@Column(name = "FAMILY_SITUATION", length = 120)
	public String getFamilySituation() {
		return this.familySituation;
	}

	public void setFamilySituation(String familySituation) {
		this.familySituation = familySituation;
	}

	@Column(name = "OTHER_SITUATION", length = 120)
	public String getOtherSituation() {
		return this.otherSituation;
	}

	public void setOtherSituation(String otherSituation) {
		this.otherSituation = otherSituation;
	}

}
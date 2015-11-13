package com.bpms.model.vo;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 微贷呈报意见报MODEL
 * @author Sun
 *
 */
public class MicrocreditOpinionModel implements java.io.Serializable{

	private static final long serialVersionUID = 590561796130507390L;
	private String mcbrId;
	private String loanOrderId;
	private String coborrowerName;
	private String coborrowerIdno;
	private BigDecimal adviceLoanAmt;
	private String adviceLoanPeriod;
	private String adviceLoanPeriodText;//贷款期限值
	private String adviceRepayMthd;
	private String loanMthd;
	private BigDecimal loanRate;
	private String loanRateText;//利率值 %
	private BigDecimal counselingRate;
	private String counselingRateText;//服务费率值 %
	private String collectionMthd;
	private String operatorA;
	private String operatorAName; //经办人A姓名
	private String operatorB;
	private String operatorBName;//经办人B姓名
	private String maritalStatus;//婚姻状态值
	private String riskCtrlMeasures;
	private String specificMeasures;
	private String firstMeeting;
	private String verification;
	private String deptPrincipal;
	private BigDecimal finalLoanAmt;
	private String finalLoanPeriod;
	private String finalRepayMthd;
	private Date surveyDate;
	private String rejectCause;
	private String auditWay;
	private String auditWayText;
	private BigDecimal monthRepay;//月还金额
	private BigDecimal actualLoanAmount;//实放金额
	private String isThrough;//是否通过(汇总意见)
	private String reason;//理由建议(汇总)
	private String maritalStatusRemark;//婚姻状况备注
	private String carInfoRemark;//车抵信息备注
	private BigDecimal visitFee;//信访费用
	
	public String getMcbrId() {
		return mcbrId;
	}
	public void setMcbrId(String mcbrId) {
		this.mcbrId = mcbrId;
	}
	public String getLoanOrderId() {
		return loanOrderId;
	}
	public void setLoanOrderId(String loanOrderId) {
		this.loanOrderId = loanOrderId;
	}
	public String getCoborrowerName() {
		return coborrowerName;
	}
	public void setCoborrowerName(String coborrowerName) {
		this.coborrowerName = coborrowerName;
	}
	public String getCoborrowerIdno() {
		return coborrowerIdno;
	}
	public void setCoborrowerIdno(String coborrowerIdno) {
		this.coborrowerIdno = coborrowerIdno;
	}
	public BigDecimal getAdviceLoanAmt() {
		return adviceLoanAmt;
	}
	public void setAdviceLoanAmt(String adviceLoanAmt) {
		if (StringUtils.isNotBlank(adviceLoanAmt)) {
			this.adviceLoanAmt = BigDecimal.valueOf(Double
					.valueOf(adviceLoanAmt));
		}
	}
	public String getAdviceLoanPeriod() {
		return adviceLoanPeriod;
	}
	public void setAdviceLoanPeriod(String adviceLoanPeriod) {
		this.adviceLoanPeriod = adviceLoanPeriod;
	}
	public String getAdviceRepayMthd() {
		return adviceRepayMthd;
	}
	public void setAdviceRepayMthd(String adviceRepayMthd) {
		this.adviceRepayMthd = adviceRepayMthd;
	}
	public String getLoanMthd() {
		return loanMthd;
	}
	public void setLoanMthd(String loanMthd) {
		this.loanMthd = loanMthd;
	}
	public BigDecimal getLoanRate() {
		return loanRate;
	}
	public void setLoanRate(String loanRate) {
		if (StringUtils.isNotBlank(loanRate)) {
			this.loanRate = BigDecimal.valueOf(Double
					.valueOf(loanRate));
		}
	}
	public BigDecimal getCounselingRate() {
		return counselingRate;
	}
	public void setCounselingRate(String counselingRate) {
		if (StringUtils.isNotBlank(counselingRate)) {
			this.counselingRate = BigDecimal.valueOf(Double
					.valueOf(counselingRate));
		}
	}
	public String getCollectionMthd() {
		return collectionMthd;
	}
	public void setCollectionMthd(String collectionMthd) {
		this.collectionMthd = collectionMthd;
	}
	public String getOperatorA() {
		return operatorA;
	}
	public void setOperatorA(String operatorA) {
		this.operatorA = operatorA;
	}
	public String getOperatorB() {
		return operatorB;
	}
	public void setOperatorB(String operatorB) {
		this.operatorB = operatorB;
	}
	public String getRiskCtrlMeasures() {
		return riskCtrlMeasures;
	}
	public void setRiskCtrlMeasures(String riskCtrlMeasures) {
		this.riskCtrlMeasures = riskCtrlMeasures;
	}
	public String getSpecificMeasures() {
		return specificMeasures;
	}
	public void setSpecificMeasures(String specificMeasures) {
		this.specificMeasures = specificMeasures;
	}
	public String getFirstMeeting() {
		return firstMeeting;
	}
	public void setFirstMeeting(String firstMeeting) {
		this.firstMeeting = firstMeeting;
	}
	public String getVerification() {
		return verification;
	}
	public void setVerification(String verification) {
		this.verification = verification;
	}
	public String getDeptPrincipal() {
		return deptPrincipal;
	}
	public void setDeptPrincipal(String deptPrincipal) {
		this.deptPrincipal = deptPrincipal;
	}
	public BigDecimal getFinalLoanAmt() {
		return finalLoanAmt;
	}
	public void setFinalLoanAmt(String finalLoanAmt) {
		if (StringUtils.isNotBlank(finalLoanAmt)) {
			this.finalLoanAmt = BigDecimal.valueOf(Double
					.valueOf(finalLoanAmt));
		}
	}
	public String getFinalLoanPeriod() {
		return finalLoanPeriod;
	}
	public void setFinalLoanPeriod(String finalLoanPeriod) {
		this.finalLoanPeriod = finalLoanPeriod;
	}
	public String getFinalRepayMthd() {
		return finalRepayMthd;
	}
	public void setFinalRepayMthd(String finalRepayMthd) {
		this.finalRepayMthd = finalRepayMthd;
	}
	public Date getSurveyDate() {
		return surveyDate;
	}
	public void setSurveyDate(Date surveyDate) {
		this.surveyDate = surveyDate;
	}
	public String getRejectCause() {
		return rejectCause;
	}
	public void setRejectCause(String rejectCause) {
		this.rejectCause = rejectCause;
	}
	public String getAuditWay() {
		return auditWay;
	}
	public void setAuditWay(String auditWay) {
		this.auditWay = auditWay;
	}
	public String getAdviceLoanPeriodText() {
		return adviceLoanPeriodText;
	}
	public void setAdviceLoanPeriodText(String adviceLoanPeriodText) {
		this.adviceLoanPeriodText = adviceLoanPeriodText;
	}
	public String getLoanRateText() {
		return loanRateText;
	}
	public void setLoanRateText(String loanRateText) {
		this.loanRateText = loanRateText;
	}
	public String getCounselingRateText() {
		return counselingRateText;
	}
	public void setCounselingRateText(String counselingRateText) {
		this.counselingRateText = counselingRateText;
	}
	public String getOperatorAName() {
		return operatorAName;
	}
	public void setOperatorAName(String operatorAName) {
		this.operatorAName = operatorAName;
	}
	public String getOperatorBName() {
		return operatorBName;
	}
	public void setOperatorBName(String operatorBName) {
		this.operatorBName = operatorBName;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public void setAdviceLoanAmt(BigDecimal adviceLoanAmt) {
		this.adviceLoanAmt = adviceLoanAmt;
	}
	public void setLoanRate(BigDecimal loanRate) {
		this.loanRate = loanRate;
	}
	public void setCounselingRate(BigDecimal counselingRate) {
		this.counselingRate = counselingRate;
	}
	public void setFinalLoanAmt(BigDecimal finalLoanAmt) {
		this.finalLoanAmt = finalLoanAmt;
	}
	public String getAuditWayText() {
		return auditWayText;
	}
	public void setAuditWayText(String auditWayText) {
		this.auditWayText = auditWayText;
	}
	public BigDecimal getMonthRepay() {
		return monthRepay;
	}
	public void setMonthRepay(BigDecimal monthRepay) {
		this.monthRepay = monthRepay;
	}
	public BigDecimal getActualLoanAmount() {
		return actualLoanAmount;
	}
	public void setActualLoanAmount(BigDecimal actualLoanAmount) {
		this.actualLoanAmount = actualLoanAmount;
	}
	public String getIsThrough() {
		return isThrough;
	}
	public void setIsThrough(String isThrough) {
		this.isThrough = isThrough;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getMaritalStatusRemark() {
		return maritalStatusRemark;
	}
	public void setMaritalStatusRemark(String maritalStatusRemark) {
		this.maritalStatusRemark = maritalStatusRemark;
	}
	public String getCarInfoRemark() {
		return carInfoRemark;
	}
	public void setCarInfoRemark(String carInfoRemark) {
		this.carInfoRemark = carInfoRemark;
	}
	public BigDecimal getVisitFee() {
		return visitFee;
	}
	public void setVisitFee(BigDecimal visitFee) {
		this.visitFee = visitFee;
	}
	
}

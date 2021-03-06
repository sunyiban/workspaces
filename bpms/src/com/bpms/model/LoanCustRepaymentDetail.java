package com.bpms.model;

// Generated 2015-8-18 13:22:39 by Hibernate Tools 4.3.1

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
 * 贷款客户还款明细
 * @author liuhh
 *
 */
@Entity
@Table(name = "t_bp_loan_cust_repayment_detail")
public class LoanCustRepaymentDetail implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String rdId;
	private String contractNo;
	private int periods;
	private Date planRepmtDate;
	private BigDecimal planRepmtAmt;
	private Date realRepmtDate;
	private BigDecimal realRepmtAmt;
	private String repmtAct;
	private Integer overdueDays;
	private BigDecimal lateFee;//滞纳金 月还金额*0.1
	private BigDecimal defaultInterest;//罚息，计算公式为月还金额*0.0005*分期期数*逾期天数（累计）
	private BigDecimal freeInterestFee;
	private String rpmtStatus;
	private String operator;
	private String remark;

	public LoanCustRepaymentDetail() {
	}

	public LoanCustRepaymentDetail(String rdId, String contractNo,
			int periods) {
		this.rdId = rdId;
		this.contractNo = contractNo;
		this.periods = periods;
	}

	public LoanCustRepaymentDetail(String rdId, String contractNo,
			int periods, Date planRepmtDate, BigDecimal planRepmtAmt,
			Date realRepmtDate, BigDecimal realRepmtAmt, String repmtAct,
			Integer overdueDays, BigDecimal lateFee,
			BigDecimal defaultInterest, BigDecimal freeInterestFee,
			String rpmtStatus, String operator, String remark) {
		this.rdId = rdId;
		this.contractNo = contractNo;
		this.periods = periods;
		this.planRepmtDate = planRepmtDate;
		this.planRepmtAmt = planRepmtAmt;
		this.realRepmtDate = realRepmtDate;
		this.realRepmtAmt = realRepmtAmt;
		this.repmtAct = repmtAct;
		this.overdueDays = overdueDays;
		this.lateFee = lateFee;
		this.defaultInterest = defaultInterest;
		this.freeInterestFee = freeInterestFee;
		this.rpmtStatus = rpmtStatus;
		this.operator = operator;
		this.remark = remark;
	}

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "RD_ID", insertable = true, updatable = true, nullable = false, length = 40, unique = true)
	public String getRdId() {
		return this.rdId;
	}

	public void setRdId(String rdId) {
		this.rdId = rdId;
	}

	@Column(name = "CONTRACT_NO", nullable = false, length = 50)
	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	@Column(name = "PERIODS", nullable = false)
	public int getPeriods() {
		return this.periods;
	}

	public void setPeriods(int periods) {
		this.periods = periods;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PLAN_REPMT_DATE", length = 10)
	public Date getPlanRepmtDate() {
		return this.planRepmtDate;
	}

	public void setPlanRepmtDate(Date planRepmtDate) {
		this.planRepmtDate = planRepmtDate;
	}

	@Column(name = "PLAN_REPMT_AMT", precision = 10)
	public BigDecimal getPlanRepmtAmt() {
		return this.planRepmtAmt;
	}

	public void setPlanRepmtAmt(BigDecimal planRepmtAmt) {
		this.planRepmtAmt = planRepmtAmt;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REAL_REPMT_DATE", length = 10)
	public Date getRealRepmtDate() {
		return this.realRepmtDate;
	}

	public void setRealRepmtDate(Date realRepmtDate) {
		this.realRepmtDate = realRepmtDate;
	}

	@Column(name = "REAL_REPMT_AMT", precision = 10)
	public BigDecimal getRealRepmtAmt() {
		return this.realRepmtAmt;
	}

	public void setRealRepmtAmt(BigDecimal realRepmtAmt) {
		this.realRepmtAmt = realRepmtAmt;
	}

	@Column(name = "REPMT_ACT", length = 32)
	public String getRepmtAct() {
		return this.repmtAct;
	}

	public void setRepmtAct(String repmtAct) {
		this.repmtAct = repmtAct;
	}

	@Column(name = "OVERDUE_DAYS")
	public Integer getOverdueDays() {
		return this.overdueDays;
	}

	public void setOverdueDays(Integer overdueDays) {
		this.overdueDays = overdueDays;
	}

	@Column(name = "LATE_FEE", precision = 10)
	public BigDecimal getLateFee() {
		return this.lateFee;
	}

	public void setLateFee(BigDecimal lateFee) {
		this.lateFee = lateFee;
	}

	@Column(name = "DEFAULT_INTEREST", precision = 10)
	public BigDecimal getDefaultInterest() {
		return this.defaultInterest;
	}

	public void setDefaultInterest(BigDecimal defaultInterest) {
		this.defaultInterest = defaultInterest;
	}

	@Column(name = "FREE_INTEREST_FEE", precision = 10)
	public BigDecimal getFreeInterestFee() {
		return this.freeInterestFee;
	}

	public void setFreeInterestFee(BigDecimal freeInterestFee) {
		this.freeInterestFee = freeInterestFee;
	}

	@Column(name = "RPMT_STATUS", length = 1)
	public String getRpmtStatus() {
		return this.rpmtStatus;
	}

	public void setRpmtStatus(String rpmtStatus) {
		this.rpmtStatus = rpmtStatus;
	}

	@Column(name = "OPERATOR", length = 40)
	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "REMARK", length = 100)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}

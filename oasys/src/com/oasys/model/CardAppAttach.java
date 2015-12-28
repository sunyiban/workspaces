package com.oasys.model;

// Generated 2015-9-25 9:55:05 by Hibernate Tools 4.0.0

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TOaAdCardAppAttach generated by hbm2java
 */
@Entity
@Table(name = "t_oa_ad_card_app_attach")
public class CardAppAttach implements java.io.Serializable {

	private Integer caId;
	private String appNo;
	private Integer applicantNo;
	private String position;
	private Integer deptNo;
	private String personalTel;
	private String officeTel;
	private String email;
	private String branchAddr;
	private String comUrl;
	private Integer appQty;
	private String unit;
	private BigDecimal price;//单价
	private BigDecimal subTotalAMT;//合计金额
	private Integer userId;
	private String remark;
	private String name;
	private String fullName;
	private String positionName;
//	private CardApp cardApp = new CardApp();

	public CardAppAttach() {
	}

	public CardAppAttach(String appNo, Integer applicantNo,
			String position, Integer deptNo, String personalTel,
			String officeTel, String email, String branchAddr, String comUrl,
			Integer appQty, String unit, String remark) {
		this.appNo = appNo;
		this.applicantNo = applicantNo;
		this.position = position;
		this.deptNo = deptNo;
		this.personalTel = personalTel;
		this.officeTel = officeTel;
		this.email = email;
		this.branchAddr = branchAddr;
		this.comUrl = comUrl;
		this.appQty = appQty;
		this.unit = unit;
		this.remark = remark;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CA_ID", unique = true, nullable = false)
	public Integer getCaId() {
		return this.caId;
	}

	public void setCaId(Integer caId) {
		this.caId = caId;
	}

	@Column(name = "APP_NO", length = 25)
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

	@Column(name = "POSITION", length = 50)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "DEPT_NO")
	public Integer getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(Integer deptNo) {
		this.deptNo = deptNo;
	}

	@Column(name = "PERSONAL_TEL")
	public String getPersonalTel() {
		return this.personalTel;
	}

	public void setPersonalTel(String personalTel) {
		this.personalTel = personalTel;
	}

	@Column(name = "OFFICE_TEL")
	public String getOfficeTel() {
		return this.officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	@Column(name = "EMAIL", length = 80)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "BRANCH_ADDR", length = 100)
	public String getBranchAddr() {
		return this.branchAddr;
	}

	public void setBranchAddr(String branchAddr) {
		this.branchAddr = branchAddr;
	}

	@Column(name = "COM_URL")
	public String getComUrl() {
		return this.comUrl;
	}

	public void setComUrl(String comUrl) {
		this.comUrl = comUrl;
	}

	@Column(name = "APP_QTY")
	public Integer getAppQty() {
		return this.appQty;
	}

	public void setAppQty(Integer appQty) {
		this.appQty = appQty;
	}

	@Column(name = "UNIT", length = 10)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@Column(name = "PRICE",length = 10)
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "SUBTOTAL_AMT",length = 10)
	public BigDecimal getSubTotalAMT() {
		return subTotalAMT;
	}

	public void setSubTotalAMT(BigDecimal subTotalAMT) {
		this.subTotalAMT = subTotalAMT;
	}

	@Transient
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Transient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Transient
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	@Transient
	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	
//	@ManyToOne()
//	public CardApp getCardApp() {
//		return cardApp;
//	}
//
//	public void setCardApp(CardApp cardApp) {
//		this.cardApp = cardApp;
//	}
	
	
}

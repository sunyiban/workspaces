package com.oasys.model;

// Generated 2015-12-10 13:43:04 by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TOaAdMaintFeeInfo generated by hbm2java
 */
@Entity
@Table(name = "t_oa_ad_maint_fee_info")
public class MaintFeeInfo implements java.io.Serializable {

	private Integer mfiId;
	private BigDecimal appAmt;
	private BigDecimal bgMileage;
	private BigDecimal edMileage;
	private Date prevAppDate;
	private String remark;

	public MaintFeeInfo() {
	}

	public MaintFeeInfo(BigDecimal appAmt, BigDecimal bgMileage,
			BigDecimal edMileage, Date prevAppDate, String remark) {
		this.appAmt = appAmt;
		this.bgMileage = bgMileage;
		this.edMileage = edMileage;
		this.prevAppDate = prevAppDate;
		this.remark = remark;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "MFI_ID", unique = true, nullable = false)
	public Integer getMfiId() {
		return this.mfiId;
	}

	public void setMfiId(Integer mfiId) {
		this.mfiId = mfiId;
	}

	@Column(name = "APP_AMT", precision = 10)
	public BigDecimal getAppAmt() {
		return this.appAmt;
	}

	public void setAppAmt(BigDecimal appAmt) {
		this.appAmt = appAmt;
	}

	@Column(name = "BG_MILEAGE", precision = 10)
	public BigDecimal getBgMileage() {
		return this.bgMileage;
	}

	public void setBgMileage(BigDecimal bgMileage) {
		this.bgMileage = bgMileage;
	}

	@Column(name = "ED_MILEAGE", precision = 10)
	public BigDecimal getEdMileage() {
		return this.edMileage;
	}

	public void setEdMileage(BigDecimal edMileage) {
		this.edMileage = edMileage;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PREV_APP_DATE", length = 10)
	public Date getPrevAppDate() {
		return this.prevAppDate;
	}

	public void setPrevAppDate(Date prevAppDate) {
		this.prevAppDate = prevAppDate;
	}

	@Column(name = "REMARK", length = 256)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}

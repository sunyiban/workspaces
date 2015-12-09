package com.oasys.model;
// Generated 2015-11-19 11:40:50 by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * TOaBusinessTripAttach generated by hbm2java
 */
@Entity
@Table(name = "t_oa_business_trip_attach", catalog = "oasys")
public class BusinessTripAttach implements java.io.Serializable {

	private Integer btaId;
	private String appNo;
	private String btOrig;
	private String btDest;
	private Date planBgDtime;
	private Date planEdDtime;
	private BigDecimal planBtDays;
	private Date realBgDtime;
	private Date realEdDtime;
	private BigDecimal realBtDays;
	private Character vehicle;
	private String vehicleOther;
	private Integer bills;
	private BigDecimal total;

	
	/**
	 * 交通工具名字
	 */
	private String vehicleName;
	
	public BusinessTripAttach() {
	}

	public BusinessTripAttach(String appNo, String btOrig, String btDest,
			Date planBgDtime, Date planEdDtime, BigDecimal planBtDays,
			Date realBgDtime, Date realEdDtime, BigDecimal realBtDays,
			Character vehicle, String vehicleOther, Integer bills,String vehicleName,
			BigDecimal total) {
		this.appNo = appNo;
		this.btOrig = btOrig;
		this.btDest = btDest;
		this.planBgDtime = planBgDtime;
		this.planEdDtime = planEdDtime;
		this.planBtDays = planBtDays;
		this.realBgDtime = realBgDtime;
		this.realEdDtime = realEdDtime;
		this.realBtDays = realBtDays;
		this.vehicle = vehicle;
		this.vehicleOther = vehicleOther;
		this.bills = bills;
		this.total = total;
		this.vehicleName=vehicleName;
	}

	@Transient
	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "BTA_ID", unique = true, nullable = false)
	public Integer getBtaId() {
		return this.btaId;
	}

	public void setBtaId(Integer btaId) {
		this.btaId = btaId;
	}

	@Column(name = "APP_NO", length = 25)
	public String getAppNo() {
		return this.appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	@Column(name = "BT_ORIG", length = 150)
	public String getBtOrig() {
		return this.btOrig;
	}

	public void setBtOrig(String btOrig) {
		this.btOrig = btOrig;
	}

	@Column(name = "BT_DEST", length = 150)
	public String getBtDest() {
		return this.btDest;
	}

	public void setBtDest(String btDest) {
		this.btDest = btDest;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PLAN_BG_DTIME", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getPlanBgDtime() {
		return this.planBgDtime;
	}

	public void setPlanBgDtime(Date planBgDtime) {
		this.planBgDtime = planBgDtime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PLAN_ED_DTIME", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getPlanEdDtime() {
		return this.planEdDtime;
	}

	public void setPlanEdDtime(Date planEdDtime) {
		this.planEdDtime = planEdDtime;
	}

	@Column(name = "PLAN_BT_DAYS", precision = 4, scale = 1)
	public BigDecimal getPlanBtDays() {
		return this.planBtDays;
	}

	public void setPlanBtDays(BigDecimal planBtDays) {
		this.planBtDays = planBtDays;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REAL_BG_DTIME", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getRealBgDtime() {
		return this.realBgDtime;
	}

	public void setRealBgDtime(Date realBgDtime) {
		this.realBgDtime = realBgDtime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REAL_ED_DTIME", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getRealEdDtime() {
		return this.realEdDtime;
	}

	public void setRealEdDtime(Date realEdDtime) {
		this.realEdDtime = realEdDtime;
	}

	@Column(name = "REAL_BT_DAYS", precision = 4, scale = 1)
	public BigDecimal getRealBtDays() {
		return this.realBtDays;
	}

	public void setRealBtDays(BigDecimal realBtDays) {
		this.realBtDays = realBtDays;
	}

	@Column(name = "VEHICLE", length = 1)
	public Character getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Character vehicle) {
		this.vehicle = vehicle;
	}

	@Column(name = "VEHICLE_OTHER", length = 30)
	public String getVehicleOther() {
		return this.vehicleOther;
	}

	public void setVehicleOther(String vehicleOther) {
		this.vehicleOther = vehicleOther;
	}

	@Column(name = "BILLS")
	public Integer getBills() {
		return this.bills;
	}

	public void setBills(Integer bills) {
		this.bills = bills;
	}

	@Column(name = "TOTAL", precision = 10)
	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
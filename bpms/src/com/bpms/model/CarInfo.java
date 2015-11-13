package com.bpms.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 车辆买卖协议表
 * @author Sun
 *
 */

@Entity  
@Table(name = "t_bp_vehicle_sale_protocol")
public class CarInfo implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private String carinfoId;//车辆信息ID
	private String carLicense;//车牌号
	private String carName;//品牌型号
	private String engineNo;//发动机号
	private String carFrameNo;//车架号
	private String loaner;			//贷款人姓名
	private String loanerIdno;		//贷款人身份证号
	private String loanerPostalAddr;//贷款人通讯地址
	private String loanerTel;		//贷款人联系电话
	private Date loanerSignDate;	//贷款人签字日期
	// private BigDecimal loanEdu;		//贷款额度，合同金额
	//private Date loanBgDate;		//贷款日期
	private String lender;			//出借人，目前固定为于德建
	private String address;			//出借人地址
	private Date lenderSignDate;	//出借人签字日期
	private String loanOrderId;		//订单ID
	private BigDecimal vehicleMortgageAmt;//车抵金额
	
	public CarInfo() {
	}

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "VSP_ID", insertable = true, updatable = true, nullable = false, length = 40, unique = true)
	public String getCarinfoId() {
		return carinfoId;
	}
	public void setCarinfoId(String carinfoId) {
		this.carinfoId = carinfoId;
	}
	
	@Column(name = "LICENSE_PLATE", length = 10)
	public String getCarLicense() {
		return carLicense;
	}
	public void setCarLicense(String carLicense) {
		this.carLicense = carLicense;
	}
	
	@Column(name = "BRAND_MODELS", length = 128)
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	
	@Column(name = "ENGINE_NO", length = 15)
	public String getEngineNo() {
		return engineNo;
	}
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	
	@Column(name = "VIN", length = 20)
	public String getCarFrameNo() {
		return carFrameNo;
	}
	public void setCarFrameNo(String carFrameNo) {
		this.carFrameNo = carFrameNo;
	}
	
	@Column(name = "DEBTOR", length = 128)
	public String getLoaner() {
		return loaner;
	}
	public void setLoaner(String loaner) {
		this.loaner = loaner;
	}
	
	@Column(name = "DEBTOR_IDCARD2", length = 20)
	public String getLoanerIdno() {
		return loanerIdno;
	}
	public void setLoanerIdno(String loanerIdno) {
		this.loanerIdno = loanerIdno;
	}
	
	@Column(name = "DEBTOR_ADDR", length = 256)	
	public String getLoanerPostalAddr() {
		return loanerPostalAddr;
	}
	public void setLoanerPostalAddr(String loanerPostalAddr) {
		this.loanerPostalAddr = loanerPostalAddr;
	}
	
	@Column(name = "DEBTOR_MOBILE", length = 20)	
	public String getLoanerTel() {
		return loanerTel;
	}
	public void setLoanerTel(String loanerTel) {
		this.loanerTel = loanerTel;
	}
	
	@Column(name = "DEBTOR_SIGN_DATE", length = 40)	
	public Date getLoanerSignDate() {
		return loanerSignDate;
	}
	public void setLoanerSignDate(Date loanerSignDate) {
		this.loanerSignDate = loanerSignDate;
	}
	
	@Column(name = "CREDITOR", length = 128)	
	public String getLender() {
		return lender;
	}
	public void setLender(String lender) {
		this.lender = lender;
	}
	
	@Column(name = "CREDITOR_ADDR", length = 256)	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "CREDITOR_SIGN_DATE")	
	public Date getLenderSignDate() {
		return lenderSignDate;
	}
	public void setLenderSignDate(Date lenderSignDate) {
		this.lenderSignDate = lenderSignDate;
	}

	@Column(name = "LOAN_ORDER_ID", length = 40)	
	public String getLoanOrderId() {
		return loanOrderId;
	}

	public void setLoanOrderId(String loanOrderId) {
		this.loanOrderId = loanOrderId;
	}

	@Column(name = "VEHICLE_MORTGAGE_AMT", precision = 20, scale = 5)
	public BigDecimal getVehicleMortgageAmt() {
		return vehicleMortgageAmt;
	}

	public void setVehicleMortgageAmt(BigDecimal vehicleMortgageAmt) {
		this.vehicleMortgageAmt = vehicleMortgageAmt;
	}
	
}

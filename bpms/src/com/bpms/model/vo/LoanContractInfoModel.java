package com.bpms.model.vo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.bpms.util.Constants;
import com.bpms.util.DateUtils;
import com.bpms.util.NumberFormatUtil;

/**
 * 贷款合同的明细的信息
 */
public class LoanContractInfoModel implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String loanReviewRommittee1;// 贷审委1
	private String loanReviewRommittee2;// 贷审委2
	private String loanReviewRommittee3;// 贷审委3
	private String loanReviewRommitteeName1;// 贷审委1姓名
	private String loanReviewRommitteeName2;// 贷审委2姓名
	private String loanReviewRommitteeName3;// 贷审委3姓名
	private String loanOrderId;// 订单的ID
	private String loanName;// 贷款申请人的姓名
	private String loanType;// 贷款类型，工薪贷1.3%,事业贷1.4%
	private String loanTypeName;// 贷款类型的名称
	private String loanIdNo;// 贷款申请人的身份证号
	private String loanHukouAddrId;// 贷款申请人的户籍地址对应地址表中的ID
	private String loanHukouAddr;// 贷款申请人的户籍地址
	private String loanCurAddr;// 贷款申请人的当前地址
	private String loanMobileTel;// 贷款申请人的手机电话
	private String salesMan;// 业务员
	private String organizationId;// 登记者所属的机构的ID
	private Integer loanPeriod;// 贷款期限
	private String operatorAId;// 经办人A
	private String operatorBId;// 经办人B
	private String operatorAName;// 经办人A的姓名
	private String operatorBName;// 经办人B的姓名
	private String contractNo;// 合同编号
	private String loanerActName;// 开户名
	private String loanerActNum;// 开户行账号
	private String loanerBankName;// 开户行名称
	private Date contractSignDate;// 合同签署日期
	private String contractSignSite;// 合同签署地
	private Date loanBgDate;// 贷款开始日期
	private Date loanEdDate;// 贷款结束日期
	private String monthlyRepayment;// 月还款额
	private String remark;// 合同备注
	private String loanEdu;// 贷款额度
	private String loanInterest;// 贷款利息
	private String visitFee;// 信访费用
	private String monthlyFee;// 月服务费
	private String teamManger;// 团队经理
	private Integer monthlyRepaymentDate;// 月还款日
	private String drawPlatform = "好易联";// 划扣平台
	private Date repaymentBgDate;// 还款开始日期
	private Date repaymentEndDate;// 还款截止日期
	private Date drawDate;// 划扣日期
	private String loanCity;// 进件城市
	private String belongTo;// 所属业务ID

	public String getLoanReviewRommittee1() {
		return loanReviewRommittee1;
	}

	public void setLoanReviewRommittee1(String loanReviewRommittee1) {
		this.loanReviewRommittee1 = loanReviewRommittee1;
	}

	public String getLoanReviewRommittee2() {
		return loanReviewRommittee2;
	}

	public void setLoanReviewRommittee2(String loanReviewRommittee2) {
		this.loanReviewRommittee2 = loanReviewRommittee2;
	}

	public String getLoanReviewRommittee3() {
		return loanReviewRommittee3;
	}

	public void setLoanReviewRommittee3(String loanReviewRommittee3) {
		this.loanReviewRommittee3 = loanReviewRommittee3;
	}

	public String getLoanReviewRommitteeName1() {
		return loanReviewRommitteeName1;
	}

	public void setLoanReviewRommitteeName1(String loanReviewRommitteeName1) {
		this.loanReviewRommitteeName1 = loanReviewRommitteeName1;
	}

	public String getLoanReviewRommitteeName2() {
		return loanReviewRommitteeName2;
	}

	public void setLoanReviewRommitteeName2(String loanReviewRommitteeName2) {
		this.loanReviewRommitteeName2 = loanReviewRommitteeName2;
	}

	public String getLoanReviewRommitteeName3() {
		return loanReviewRommitteeName3;
	}

	public void setLoanReviewRommitteeName3(String loanReviewRommitteeName3) {
		this.loanReviewRommitteeName3 = loanReviewRommitteeName3;
	}

	public String getLoanOrderId() {
		return loanOrderId;
	}

	public void setLoanOrderId(String loanOrderId) {
		this.loanOrderId = loanOrderId;
	}

	public String getLoanName() {
		return loanName;
	}

	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getLoanIdNo() {
		return loanIdNo;
	}

	public void setLoanIdNo(String loanIdNo) {
		this.loanIdNo = loanIdNo;
	}

	public String getLoanHukouAddrId() {
		return loanHukouAddrId;
	}

	public void setLoanHukouAddrId(String loanHukouAddrId) {
		this.loanHukouAddrId = loanHukouAddrId;
	}

	public String getLoanHukouAddr() {
		return loanHukouAddr;
	}

	public void setLoanHukouAddr(String loanHukouAddr) {
		this.loanHukouAddr = loanHukouAddr;
	}

	public String getLoanCurAddr() {
		return loanCurAddr;
	}

	public void setLoanCurAddr(String loanCurAddr) {
		this.loanCurAddr = loanCurAddr;
	}

	public String getLoanMobileTel() {
		return loanMobileTel;
	}

	public void setLoanMobileTel(String loanMobileTel) {
		this.loanMobileTel = loanMobileTel;
	}

	public String getSalesMan() {
		return salesMan;
	}

	public void setSalesMan(String salesMan) {
		this.salesMan = salesMan;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(Integer loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public String getOperatorAId() {
		return operatorAId;
	}

	public void setOperatorAId(String operatorAId) {
		this.operatorAId = operatorAId;
	}

	public String getOperatorBId() {
		return operatorBId;
	}

	public void setOperatorBId(String operatorBId) {
		this.operatorBId = operatorBId;
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

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getLoanerActName() {
		return loanerActName;
	}

	public void setLoanerActName(String loanerActName) {
		this.loanerActName = loanerActName;
	}

	public String getLoanerActNum() {
		return loanerActNum;
	}

	public void setLoanerActNum(String loanerActNum) {
		this.loanerActNum = loanerActNum;
	}

	public String getLoanerBankName() {
		return loanerBankName;
	}

	public void setLoanerBankName(String loanerBankName) {
		this.loanerBankName = loanerBankName;
	}

	public Date getContractSignDate() {
		return contractSignDate;
	}

	public void setContractSignDate(Date contractSignDate) {
		this.contractSignDate = contractSignDate;
		if (contractSignDate != null) {
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(contractSignDate);
			if (calendar.get(Calendar.DATE) >= 15) {
				this.setMonthlyRepaymentDate(15);
			} else {
				this.setMonthlyRepaymentDate(30);
			}
		}
	}

	public Date getLoanBgDate() {
		return loanBgDate;
	}

	public void setLoanBgDate(Date loanBgDate) {
		this.loanBgDate = loanBgDate;
		if (loanBgDate != null) {
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(loanBgDate);
			if (calendar.get(Calendar.DATE) >=15) {
				this.setRepaymentBgDate(DateUtils.getNextMonth15Day(loanBgDate));
			} else {
				this.setRepaymentBgDate(DateUtils.get30Day(loanBgDate));
			}
		}
		this.setDrawDate(loanBgDate);
	}

	public Date getLoanEdDate() {
		return loanEdDate;
	}

	public void setLoanEdDate(Date loanEdDate) {
		this.loanEdDate = loanEdDate;
		if (loanEdDate != null) {
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(loanEdDate);
			if (calendar.get(Calendar.DATE) >= 15) {
				this.setRepaymentEndDate(DateUtils.getNextMonth15Day(loanEdDate));
//				calendar.set(Calendar.DATE, 30);
			} else {
				this.setRepaymentEndDate(DateUtils.get30Day(loanEdDate));
//				calendar.set(Calendar.DATE, 15);

			}
		}
	}

	public String getMonthlyRepayment() {
		return monthlyRepayment;
	}

	public void setMonthlyRepayment(String monthlyRepayment) {
		this.monthlyRepayment = NumberFormatUtil.formatNumber2Str(2,
				monthlyRepayment);
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLoanEdu() {
		return loanEdu;
	}

	public void setLoanEdu(String loanEdu) {
		this.loanEdu = NumberFormatUtil.formatNumber2Str(2, loanEdu);
	}

	public String getLoanInterest() {
		return loanInterest;
	}

	public void setLoanInterest(String loanInterest) {
		this.loanInterest = NumberFormatUtil.formatNumber2Str(2, loanInterest);
	}

	public String getVisitFee() {
		return visitFee;
	}

	public void setVisitFee(String visitFee) {
		this.visitFee = NumberFormatUtil.formatNumber2Str(2, visitFee);
	}

	public String getMonthlyFee() {
		return monthlyFee;
	}

	//
	public void setMonthlyFee() {
		BigDecimal monthlyFee = null;
		// 事业贷的时候
		if (loanType.equals("A")) {
			monthlyFee = NumberFormatUtil.formatNumber(2, loanEdu)
					.multiply(new BigDecimal(Constants.BUSINESS_LOAN_MONTHLY_RATE))
					.multiply(new BigDecimal(loanPeriod));
		} else if (loanType.equals("B")) {// 工薪贷
			monthlyFee = NumberFormatUtil.formatNumber(2, loanEdu)
					.multiply(new BigDecimal(Constants.WAGE_LOAN_MONTHLY_RATE))
					.multiply(new BigDecimal(loanPeriod));
		}
		this.monthlyFee = NumberFormatUtil.formatNumber2Str(2, monthlyFee);
	}

	public void setMonthlyFee(String monthlyFee) {
		this.monthlyFee = NumberFormatUtil.formatNumber2Str(2, monthlyFee);
	}

	public String getTeamManger() {
		return teamManger;
	}

	public void setTeamManger(String teamManger) {
		this.teamManger = teamManger;
	}

	public Integer getMonthlyRepaymentDate() {
		return monthlyRepaymentDate;
	}

	public void setMonthlyRepaymentDate(Integer monthlyRepaymentDate) {
		this.monthlyRepaymentDate = monthlyRepaymentDate;
	}

	public String getDrawPlatform() {
		return drawPlatform;
	}

	public void setDrawPlatform(String drawPlatform) {
		this.drawPlatform = drawPlatform;
	}

	public Date getRepaymentEndDate() {
		return repaymentEndDate;
	}

	public void setRepaymentEndDate(Date repaymentEndDate) {
		this.repaymentEndDate = repaymentEndDate;
	}

	public Date getRepaymentBgDate() {
		return repaymentBgDate;
	}

	public void setRepaymentBgDate(Date repaymentBgDate) {
		this.repaymentBgDate = repaymentBgDate;
	}

	public Date getDrawDate() {
		return drawDate;
	}

	public void setDrawDate(Date drawDate) {
		this.drawDate = drawDate;
	}

	public String getLoanTypeName() {
		return loanTypeName;
	}

	public void setLoanTypeName(String loanTypeName) {
		this.loanTypeName = loanTypeName;
	}

	public String getContractSignSite() {
		return contractSignSite;
	}

	public void setContractSignSite(String contractSignSite) {
		this.contractSignSite = contractSignSite;
	}

	public LoanContractInfoModel() {
		super();
	}

	public String getLoanCity() {
		return loanCity;
	}

	public void setLoanCity(String loanCity) {
		this.loanCity = loanCity;
	}

	public String getBelongTo() {
		return belongTo;
	}

	public void setBelongTo(String belongTo) {
		this.belongTo = belongTo;
	}
	
	

}

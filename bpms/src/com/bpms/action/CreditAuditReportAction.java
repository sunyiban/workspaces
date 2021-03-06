package com.bpms.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.CreditAuditReport;
import com.bpms.service.CreditAuditReportService;
import com.bpms.view.model.DataModel;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * 信审报告 请求处理器的action
 * 
 * @author 孙一般 2015/06/23.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/06/23 孙一般 创建.
 */
@Namespace("/creditAuditReport")
@Action(value = "creditAuditReportAction")
public class CreditAuditReportAction extends BaseAction implements
		ModelDriven<CreditAuditReport> {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private CreditAuditReport creditAuditReport = new CreditAuditReport();

	/** 自动注入service. */
	@Autowired
	private CreditAuditReportService creditAuditReportService;

	private String loanerId;

	/**
	 * 根据订单的ID获取信审报告的信息
	 */
	public void findCreditAuditReportByLoanOrderId() {
		try {
			OutputJson(creditAuditReportService
					.findCreditAuditReportByLoanOrderId(creditAuditReport
							.getLoanOrderId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveCreditAuditReport() {
		// 修改信申报告的网查信息
		CreditAuditReport report = creditAuditReportService
				.findCreditAuditReportByLoanOrderId(creditAuditReport
						.getLoanOrderId());
		report.setComCreditStatus(creditAuditReport.getComCreditStatus());
		report.setLawQueryStatus(creditAuditReport.getLawQueryStatus());
		report.setLoanCity(creditAuditReport.getLoanCity());
		report.setLoanInfo(creditAuditReport.getLoanInfo());
		report.setLoanInfoDesc(creditAuditReport.getLoanInfoDesc());
		report.setActualNeedAmount(creditAuditReport.getActualNeedAmount());
		boolean result = creditAuditReportService
				.persistenceCreditAuditReport(report);
		String message = result ? "网查信息保存成功!" : "网查信息保存失败!";
		OutputJson(new DataModel("信申报告网查信息", message, result, report));
	}

	/**
	 * 查询信审报告详情
	 */
	public void queryApplicationReportDetail() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loanerId", loanerId);
		map.put("loanOrderId", creditAuditReport.getLoanOrderId());
		OutputJson(creditAuditReportService.queryApplicationReportDetail(map));
	}

	@Override
	public CreditAuditReport getModel() {
		return creditAuditReport;
	}

	public String getLoanerId() {
		return loanerId;
	}

	public void setLoanerId(String loanerId) {
		this.loanerId = loanerId;
	}

}

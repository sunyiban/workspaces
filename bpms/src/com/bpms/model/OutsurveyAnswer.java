package com.bpms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author liuhh 实地调查报告总结附加表
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "t_bp_field_survey_report_summ_attach")
public class OutsurveyAnswer implements java.io.Serializable {

	private String answerId;
	private String outsurveyReportSummaryId;
	private String questionId;
	private String answer;

	public OutsurveyAnswer() {
	}

	public OutsurveyAnswer(String answerId) {
		this.answerId = answerId;
	}

	public OutsurveyAnswer(String answerId, String outsurveyReportSummaryId,
			String questionId, String answer) {
		this.answerId = answerId;
		this.outsurveyReportSummaryId = outsurveyReportSummaryId;
		this.questionId = questionId;
		this.answer = answer;
	}

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "RSA_ID", insertable = true, updatable = true, nullable = false, length = 40, unique = true)
	public String getAnswerId() {
		return this.answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	@Column(name = "SRS_ID", length = 40)
	public String getOutsurveyReportSummaryId() {
		return this.outsurveyReportSummaryId;
	}

	public void setOutsurveyReportSummaryId(String outsurveyReportSummaryId) {
		this.outsurveyReportSummaryId = outsurveyReportSummaryId;
	}

	@Column(name = "QUESTION_ID", length = 40)
	public String getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	@Column(name = "QUESTION_FAQ", length = 2000)
	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}

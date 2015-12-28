package com.oasys.model;

// Generated 2015-12-9 20:15:16 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TOaPdNoticeInfoAttach generated by hbm2java
 */
@Entity
@Table(name = "t_oa_pd_notice_info_attach")
public class NoticeInfoAttach implements java.io.Serializable {

	private Integer niaId;
	private String noticeNo;
	private Integer receiver;
	private String isReceipted;
	private String isDel;

	public NoticeInfoAttach() {
	}

	public NoticeInfoAttach(String noticeNo, Integer receiver,
			String isReceipted, String isDel) {
		this.noticeNo = noticeNo;
		this.receiver = receiver;
		this.isReceipted = isReceipted;
		this.isDel = isDel;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "NIA_ID", unique = true, nullable = false)
	public Integer getNiaId() {
		return this.niaId;
	}

	public void setNiaId(Integer niaId) {
		this.niaId = niaId;
	}

	@Column(name = "NOTICE_NO", length = 25)
	public String getNoticeNo() {
		return this.noticeNo;
	}

	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}

	@Column(name = "RECEIVER")
	public Integer getReceiver() {
		return this.receiver;
	}

	public void setReceiver(Integer receiver) {
		this.receiver = receiver;
	}

	@Column(name = "IS_RECEIPTED", length = 1)
	public String getIsReceipted() {
		return this.isReceipted;
	}

	public void setIsReceipted(String isReceipted) {
		this.isReceipted = isReceipted;
	}

	@Column(name = "IS_DEL", length = 1)
	public String getIsDel() {
		return this.isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

}
package com.bpms.model;

// Generated 2015-6-16 10:13:19 by Hibernate Tools 4.3.1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * TSysDict generated by hbm2java
 */
@Entity
@Table(name = "t_sys_dict")
public class SysDict implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer codeId;
	private String dictCode;
	private String dictName;
	private Integer sort;
	private String dictType;
	private String iconcls;
	private String state;
	private String permissionid;
	private String parentId;
	private String description;
	private String status;
	private Date created;
	private Date lastmod;
	private String creater;
	private String modifyer;

	public SysDict() {
	}

	public SysDict(Integer codeId) {
		this.codeId = codeId;
	}

	public SysDict(Integer codeId, String dictCode, String dictName,
			Integer sort, String dictType, String iconcls, String state,
			String permissionid, String parentId, String description,
			String status, Date created, Date lastmod, String creater,
			String modifyer) {
		this.codeId = codeId;
		this.dictCode = dictCode;
		this.dictName = dictName;
		this.sort = sort;
		this.dictType = dictType;
		this.iconcls = iconcls;
		this.state = state;
		this.permissionid = permissionid;
		this.parentId = parentId;
		this.description = description;
		this.status = status;
		this.created = created;
		this.lastmod = lastmod;
		this.creater = creater;
		this.modifyer = modifyer;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "paymentableGenerator", strategy = "native")
	@Column(name = "CODE_ID", insertable = true, updatable = true, nullable = false, length = 40, unique = true)
	public Integer getCodeId() {
		return this.codeId;
	}

	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}

	@Column(name = "DICT_CODE", length = 100)
	public String getDictCode() {
		return this.dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	@Column(name = "DICT_NAME")
	public String getDictName() {
		return this.dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	@Column(name = "SORT")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "DICT_TYPE", length = 1)
	public String getDictType() {
		return this.dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	@Column(name = "ICONCLS", length = 100)
	public String getIconcls() {
		return this.iconcls;
	}

	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}

	@Column(name = "STATE", length = 20)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "PERMISSIONID", length = 40)
	public String getPermissionid() {
		return this.permissionid;
	}

	public void setPermissionid(String permissionid) {
		this.permissionid = permissionid;
	}

	@Column(name = "PARENT_ID", length = 40)
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "DESCRIPTION", length = 2000)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED", length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTMOD", length = 19)
	public Date getLastmod() {
		return this.lastmod;
	}

	public void setLastmod(Date lastmod) {
		this.lastmod = lastmod;
	}

	@Column(name = "CREATER", length = 40)
	public String getCreater() {
		return this.creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	@Column(name = "MODIFYER", length = 40)
	public String getModifyer() {
		return this.modifyer;
	}

	public void setModifyer(String modifyer) {
		this.modifyer = modifyer;
	}

}

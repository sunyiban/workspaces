package com.oasys.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_role",catalog="QQMS")
@DynamicUpdate(true)
@DynamicInsert(true)
public class Role implements java.io.Serializable {
	private static final long serialVersionUID = -8220535212044563981L;
	private Integer roleId;
	private String name;
	private Integer pid;
	private String roleType;
	private String description;
	private String status;
	private Date created;
	private Date lastmod;
	private Integer sort;
	private Integer creater;
	private Integer modifyer;
	private String roleCode;
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);
	private Set<OrganizationRole> organizationRoles = new HashSet<OrganizationRole>(
			0);
	private Set<RolePermission> rolePermissions = new HashSet<RolePermission>(0);

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** full constructor */
	public Role(String name, Integer pid, String roleType, String description,
			String status, Date created, Date lastmod, Integer sort,
			Integer creater, Integer modifyer, String roleCode,
			Set<UserRole> userRoles, Set<RolePermission> rolePermissions) {
		this.name = name;
		this.pid = pid;
		this.roleType = roleType;
		this.description = description;
		this.status = status;
		this.created = created;
		this.lastmod = lastmod;
		this.sort = sort;
		this.creater = creater;
		this.modifyer = modifyer;
		this.roleCode = roleCode;
		this.userRoles = userRoles;
		this.rolePermissions = rolePermissions;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ROLE_ID", unique = true, nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "NAME", length = 55)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PID", length = 11)
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Column(name = "ROLE_TYPE", length = 1)
	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	@Column(name = "DESCRIPTION", length = 500)
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
	@Column(name = "CREATED", length = 10)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTMOD", length = 10)
	public Date getLastmod() {
		return this.lastmod;
	}

	public void setLastmod(Date lastmod) {
		this.lastmod = lastmod;
	}

	@Column(name = "SORT")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "CREATER")
	public Integer getCreater() {
		return this.creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	@Column(name = "MODIFYER")
	public Integer getModifyer() {
		return this.modifyer;
	}

	public void setModifyer(Integer modifyer) {
		this.modifyer = modifyer;
	}

	@Column(name = "ROLE_CODE")
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
	public Set<OrganizationRole> getOrganizationRoles() {
		return organizationRoles;
	}

	public void setOrganizationRoles(Set<OrganizationRole> organizationRoles) {
		this.organizationRoles = organizationRoles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "role")
	public Set<RolePermission> getRolePermissions() {
		return this.rolePermissions;
	}

	public void setRolePermissions(Set<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

}
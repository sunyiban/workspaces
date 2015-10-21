package com.bpms.shiro;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.bpms.model.Users;

public class ShiroUser implements Serializable {
	private static final long serialVersionUID = -1748602382963711884L;
	private Integer userId;
	private String account;
	private List<String> roleCodes;
	private Users user;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<String> getRoleCodes() {
		return roleCodes;
	}

	public void setRoleCodes(List<String> roleCodes) {
		this.roleCodes = roleCodes;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public ShiroUser(List<String> roleCodes, Users user) {
		super();
		this.userId = user.getUserId();
		this.account = user.getAccount();
		this.roleCodes = roleCodes;
		this.user = user;
	}
	
	public String getJsonRoles(){
		return JSON.toJSONString(getRoleCodes());
	}

}

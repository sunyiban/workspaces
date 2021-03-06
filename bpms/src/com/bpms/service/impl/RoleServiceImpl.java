package com.bpms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.Role;
import com.bpms.service.RoleService;
import com.bpms.util.Collections;

/**
 * 角色查询的实现类
 * 
 * @author liuhh
 *
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private BaseDAO<Role> roleDao;

	@Override
	public Role findRoleByCode(String roleCode) {
		String hql = "select r from Role r where r.roleCode='"+ roleCode + "'";
		List<Role> list = roleDao.find(hql);
		if (Collections.listIsNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}	

	@Override
	public Role findRoleByName(String roleName) {
		String hql = "select r from Role r where r.name ='"+roleName+"'";
		List<Role> list = roleDao.find(hql);
		if (Collections.listIsNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void saveRole(Role role) {
		roleDao.save(role);
	}
	
	public void delRole(Role role){
		roleDao.delete(role);
	}
}

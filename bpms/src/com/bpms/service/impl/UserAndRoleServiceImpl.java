package com.bpms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.Role;
import com.bpms.model.UserAndRole;
import com.bpms.service.UserAndRoleService;
import com.bpms.util.Collections;

@Service("userAndRoleService")
public class UserAndRoleServiceImpl implements UserAndRoleService{
	@Autowired
	private BaseDAO<UserAndRole> userAndRoleDao;
	
	@Override
	public void saveUserAndRoles(UserAndRole userAndRole) {
		userAndRoleDao.save(userAndRole);		
	}

	@Override
	public UserAndRole isExistUserAndRoles(UserAndRole userAndRole) {
		String hql = "from UserAndRole ur where ur.roleId ='"+userAndRole.getRoleId()+"' and ur.userId ='"+userAndRole.getUserId()+"' ";
		List<UserAndRole> userAndRoles = userAndRoleDao.find(hql);
		if(Collections.listIsNotEmpty(userAndRoles)){
			return userAndRoles.get(0);
		}else{
			return null;
		}
	}

	@Override
	public void deleteUserAndRoles(Integer userId) {
		String hql = "delete from UserAndRole ur where ur.userId='"+userId+"'";
		userAndRoleDao.executeHql(hql);
	}

	@Override
	public void deleteUserAndRoleByRole(Role role) {
		String hql = "delete from UserAndRole ur where ur.roleId='"+role.getRoleId()+"'";
		userAndRoleDao.executeHql(hql);
	}
	
	

}

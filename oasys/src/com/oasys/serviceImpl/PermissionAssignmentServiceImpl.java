package com.oasys.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.Permission;
import com.oasys.model.Role;
import com.oasys.model.RolePermission;
import com.oasys.service.PermissionAssignmentService;
import com.oasys.shiro.ShiroUser;
import com.oasys.util.Constants;
import com.oasys.util.HqlUtil;
import com.oasys.viewModel.TreeGrid;

@SuppressWarnings("unchecked")
@Service("permissionAssignmentService")
public class PermissionAssignmentServiceImpl implements
		PermissionAssignmentService {
	@SuppressWarnings("rawtypes")
	public PublicDao publicDao;

	@SuppressWarnings("rawtypes")
	@Autowired
	public void setPublicDao(PublicDao publicDao) {
		this.publicDao = publicDao;
	}

	public Permission getFunction(Integer id) {
		return (Permission) publicDao.get(Permission.class, id);
	}

	public Role getRole(Integer roleId) {
		return (Role) publicDao.get(Role.class, roleId);
	}

	public List<TreeGrid> findAllFunctionsList(Integer pid) {

		String hql = "from Permission t where t.status='A' ";
		List<Permission> list = publicDao.find(hql);
		List<TreeGrid> tempList = new ArrayList<TreeGrid>();
		for (Permission function : list) {
			TreeGrid treeGridModel = new TreeGrid();
			treeGridModel.setId(function.getPermissionId() + "");
			if (function.getPid() != null) {
				treeGridModel.setState("open");
			}
			treeGridModel.setPid(function.getPid() == null ? null : function
					.getPid().toString());
			treeGridModel.setIconCls(function.getIconCls());
			treeGridModel.setName(function.getName());
			treeGridModel.setPath(function.getUrl());
			treeGridModel.setMyid(function.getMyid());
			treeGridModel.setPName(function.getPname());
			treeGridModel.setSort(function.getSort() + "");
			treeGridModel.setIsused(function.getIsused());
			treeGridModel.setType(function.getType());
			treeGridModel.setDescription(function.getDescription());
			tempList.add(treeGridModel);
		}
		return tempList;
	}

	@SuppressWarnings("rawtypes")
	public List<Permission> getRolePermission(Integer roleId) {
		String sql = "SELECT t.PERMISSION_ID FROM T_ROLE_AND_PERMISSION t WHERE t.STATUS = 'A' and t.ROLE_ID="
				+ roleId;
		List list = publicDao.findBySQL(sql);
		List<Permission> list2 = new ArrayList<Permission>();
		if (list.size() != 0) {
			for (Object object : list) {
				Permission p = new Permission();
				p.setPermissionId(Integer.valueOf(object.toString()));
				list2.add(p);
			}
		}
		return list2;
	}

	public List<Role> findAllRoleList(Map<String, Object> param, Integer page,
			Integer rows, boolean isPage) {
		String hql = "from Role t where t.status='A' ";
		hql += HqlUtil.getSearchConditionsHQL("t", param);
		List<Role> tempList = null;
		if (isPage) {
			tempList = publicDao.find(hql, param, page, rows);
		} else {
			tempList = publicDao.find(hql, param);
		}
		for (Role role : tempList) {
			role.setRolePermissions(null);
			role.setUserRoles(null);
		}
		return tempList;
	}

	public Long getCount(Map<String, Object> param) {
		String hql = "select count(*) from Role t where t.status='A' ";
		hql += HqlUtil.getSearchConditionsHQL("t", param);
		return publicDao.count(hql, param);
	}

	public boolean savePermission(Integer roleId, String checkedIds) {
		Integer userId = Constants.getCurrendUser().getUserId();
		Role role = this.getRole(roleId);
		Map<String, RolePermission> map = new HashMap<String, RolePermission>();
		Set<RolePermission> rolePermissions = role.getRolePermissions();
		for (RolePermission rolePermission : rolePermissions) {
			Integer permissionId = rolePermission.getPermission()
					.getPermissionId();
			map.put(permissionId.toString(), rolePermission);
			updRolePermission(userId, rolePermission,
					Constants.PERSISTENCE_DELETE_STATUS);
		}
		if (null != checkedIds && !"".equals(checkedIds)) {
			String[] ids = checkedIds.split(",");
			for (String id : ids) {
				RolePermission rolePermission = map.get(id);
				if (rolePermission != null) {
					updRolePermission(userId, rolePermission,
							Constants.PERSISTENCE_STATUS);
				} else {
					Permission function = this.getFunction(Integer.valueOf(id));
					Date date = new Date();
					rolePermission = new RolePermission();
					rolePermission.setCreated(date);
					rolePermission.setLastmod(date);
					rolePermission.setStatus(Constants.PERSISTENCE_STATUS);
					rolePermission.setCreater(userId);
					rolePermission.setModifyer(userId);
					rolePermission.setPermission(function);
					rolePermission.setRole(role);
					publicDao.save(rolePermission);
				}
			}
		}
		return true;
	}

	private void updRolePermission(Integer userId,
			RolePermission rolePermission, String satus) {
		rolePermission.setLastmod(new Date());
		rolePermission.setCreater(userId);
		rolePermission.setModifyer(userId);
		rolePermission.setStatus(satus);
		publicDao.update(rolePermission);
	}

	public boolean persistenceRole(Map<String, List<Role>> map) {
		this.addRole(map.get("addList"));
		this.updRole(map.get("updList"));
		this.delRole(map.get("delList"));
		return true;
	}

	private boolean addRole(List<Role> addList) {
		if (addList != null && addList.size() != 0) {
			ShiroUser users = Constants.getCurrendUser();
			for (Role role : addList) {
				role.setCreated(new Date());
				role.setLastmod(new Date());
				role.setStatus(Constants.PERSISTENCE_STATUS);
				role.setCreater(users.getUserId());
				role.setModifyer(users.getUserId());
				publicDao.save(role);
			}
		}
		return true;
	}

	private boolean delRole(List<Role> delList) {
		if (delList != null && delList.size() != 0) {
			ShiroUser users = Constants.getCurrendUser();
			for (Role role : delList) {
				role.setLastmod(new Date());
				role.setModifyer(users.getUserId());
				role.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
				publicDao.deleteToUpdate(role);
			}
		}
		return true;
	}

	private boolean updRole(List<Role> updList) {
		if (updList != null && updList.size() != 0) {
			ShiroUser users = Constants.getCurrendUser();
			for (Role role : updList) {
				role.setLastmod(new Date());
				role.setModifyer(users.getUserId());
				publicDao.update(role);
			}
		}
		return true;
	}

	/*
	 * (非 Javadoc) <p>Title: persistenceRole</p> <p>Description: 弹窗持久化角色</p>
	 * 
	 * @param r
	 * 
	 * @return
	 */
	public boolean persistenceRole(Role r) {
		Integer userId = Constants.getCurrendUser().getUserId();
		if (null == r.getRoleId() || "".equals(r.getRoleId())) {
			r.setCreated(new Date());
			r.setLastmod(new Date());
			r.setCreater(userId);
			r.setModifyer(userId);
			r.setStatus(Constants.PERSISTENCE_STATUS);
			publicDao.save(r);
		} else {
			r.setLastmod(new Date());
			r.setModifyer(userId);
			publicDao.update(r);
		}

		return true;
	}

	public boolean persistenceRole(Integer roleId) {
		Integer userId = Constants.getCurrendUser().getUserId();
		//如果为true,那么说明已经有人被赋予该角色,就不能删除该角色
		if(checkRelation(roleId)){
			return false;
		}
		Role role = (Role) publicDao.get(Role.class, roleId);
		role.setLastmod(new Date());
		role.setModifyer(userId);
		role.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
		publicDao.deleteToUpdate(role);
		return true;
	}
	/**
	 * 查询用户角色表，确认是否有人被赋予该角色
	 * @author panchuanhe 
	 * @creatTime 20150612
	 */
    public boolean checkRelation(Integer roleId){
    	StringBuffer sql = new StringBuffer("select * from t_user_and_role t where 1=1 and t.ROLE_ID = '"+roleId+"' and t.STATUS = 'A'");
    	List<Object> list = publicDao.findBySQL(sql.toString());
    	if(null!=list && list.size()>0){
    		return true;
    	}
    	return false;
    }
    
    /**
     * 
     * @author: xujianwei
     * @time:2015年7月10日 上午10:42:26
     * @Title:isExitCode
     * @Description:TODO 查询角色编码是否已存在（这里描述这个方法的作用）
     * @return
     * @throws:
     */
    public boolean isExistsCode(Role role){
    	//判断paren_id为1的字典项的字典编码（dict_code）只存在1个
    			String rolecode=role.getRoleCode();
    			Integer roleId = role.getRoleId();
    			String hql="from Role t where  t.status='A' and t.roleCode='"+rolecode+"'";
    			Long count = publicDao.count("select count(*)"+hql);
    			boolean flag = false;
    			if (null==roleId||"".equals(roleId))
    			{
    				//新增操作
    				if(count>0){
    					flag=true;
    				}
    			}else{
    				//修改操作
    				hql+=" and t.roleId <>'"+roleId+"'";
    				Long count1 = publicDao.count("select count(*)"+hql);
    				if(count1>=1){
    					flag=true;
    				}
    			}
    	return flag;
    }
}
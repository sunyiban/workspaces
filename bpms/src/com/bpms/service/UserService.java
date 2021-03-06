package com.bpms.service;

import java.util.List;

import com.bpms.model.Users;

/**
 * 用户信息的查询的service
 * 
 * @author Administrator
 *
 */
public interface UserService {

	/**
	 * 根据角色的编码查询角色信息列表
	 * 
	 * @param roleCode
	 *            角色信息编码
	 * @return
	 */
	List<Users> findUsersByRoleCode(String roleCode);

	/**
	 * @Description 获取全部的用户的信息
	 * @param roleId
	 * @return
	 */
	public List<Users> findAllUsers();

	/**
	 * 根据用户ID查询所有该角色用户
	 * 
	 * @param userid
	 * @return
	 */
	List<Users> findUsersByUserId(Integer userid);

	/**
	 * 根据ID查询用户
	 * 
	 * @param userid
	 * @return
	 */
	Users findUserById(Integer userid);

	/**
	 * 根据角色的code和组织机构的id获取用户的信息
	 * 
	 * @param roleCode
	 *            角色的code
	 * @param OrganizationId
	 *            组织机构的id
	 * @return
	 */
	List<Users> findUsersByRoleCodeAndOrganizationId(String roleCode,
			Integer OrganizationId);

	/**
	 * 根据用户的ID列表获取用户列表
	 * @param userIds 用户的ID列表
	 * @return 用户信息
	 */
	List<Users> findUsersByUserIds(List<String> userIds);
	
	/**
	 * 通过邮箱获取用户的信息
	 * @param email 邮箱的信息
	 * @return 用户的信息
	 */
	Users findUserByNameAndPoneAndEmail(String name,String phone,String email);

	/**
	 * 
	 * @param email 邮箱的信息
	 * @return 用户的信息
	 */
	/**
	 * 根据RoleCode和OrgId，判断是否存在财富端的指定角色和指定部门下的用户。
	 * 团队经理，大团经理，营业部经理，
	 * @Title: findUsersByRoleCodeAndOrgId4InvestBusiness 
	 * @param @param roleCode
	 * @param @param parseInt
	 * @author ZHANGJIAN
	 * @return List<Users>
	 * @date 2016年2月24日 上午10:57:07
	 */
	boolean isUserExistByRoleCodeAndOrgId4InvestBusiness(String roleCode,
			String orgId);
}

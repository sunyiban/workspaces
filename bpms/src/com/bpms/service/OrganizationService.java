package com.bpms.service;

import java.util.List;
import java.util.Map;

import com.bpms.model.Organization;
import com.bpms.view.model.ComboBoxModel;
import com.bpms.view.model.TreeGrid;
import com.bpms.view.model.TreeModel;

/**
 * 开户行service
 * 
 * @author panchuanhe 2015/6/30
 */
public interface OrganizationService {
	/**
	 * 根据Id获取组织机构的信息
	 * 
	 * @param id
	 * @return
	 */
	Organization findOrganizationById(Integer id);
	
	/**
	 * 
	 * @time:2015年8月6日 下午7:15:56
	 * @Title:findOrganizationList
	 * @Description:TODO 查询组织机构树模型（这里描述这个方法的作用）
	 * @return
	 * @throws:
	 */
	public List<TreeModel> findOrganizationList();
	
	/**
	 * 查询所属地区
	 * @return
	 */
	public List<ComboBoxModel> findOrganizationCombo();
	
	/**
	 * 查询部门列表
	 * @return
	 * @param treelevel
	 */
	public List<Map<String,String>> findOrganizationDept(String treelevel);
	
	/**
	 * 根据myid获取组织机构的信息
	 * @param myId
	 * @return
	 */
	public List<Organization> findOrganizationByMyId(String myId);
	
	/**
	 *根据MYid获取组织机构的信息
	 * @param myId
	 * @return
	 */
	public List<TreeGrid> findOrgTreeModelByMyId(String myId);
}

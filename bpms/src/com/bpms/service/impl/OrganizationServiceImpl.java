package com.bpms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.Organization;
import com.bpms.service.OrganizationService;
import com.bpms.util.Constants;
import com.bpms.util.HqlUtil;
import com.bpms.view.model.ComboBoxModel;
import com.bpms.view.model.TreeGrid;
import com.bpms.view.model.TreeModel;


@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private BaseDAO<Organization> organizationDao;

	@Override
	public Organization findOrganizationById(Integer id) {
		return organizationDao.get(Organization.class, id);
	}
	@Override
	public List<TreeModel> findOrganizationList() {
		String hql = "from Organization o where o.status='A'";
		List<Organization> tempList = organizationDao.find(hql);
		List<TreeModel> list = new ArrayList<TreeModel>();
		for (Organization o : tempList) {
			TreeModel treeModel = new TreeModel();
			treeModel.setId(o.getOrganizationId() + HqlUtil.NULL_STRING);
			treeModel.setPid(o.getPid() == null ? null : o.getPid().toString());
			treeModel.setName(o.getFullName());
			treeModel.setState(Constants.TREE_STATUS_OPEN);
			treeModel.setIconCls(o.getIconcls());
			list.add(treeModel);
		}
		return list;
	}
	@Override
	public List<ComboBoxModel> findOrganizationCombo() {
		String sql = "SELECT tz.ORGANIZATION_ID code,tz.FULL_NAME text FROM t_organization tz WHERE tz.REGION_TYPE in (1,3)";
		List<Object> list = organizationDao.findBySQL(sql);
		List<ComboBoxModel> comboxList = new ArrayList<ComboBoxModel>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] g = (Object[]) list.get(i);
				comboxList.add(new ComboBoxModel((Integer)g[0]+"",String.valueOf(g[1])));
			}
		}
		return comboxList;
	}
	@Override
	public List<Map<String, String>> findOrganizationDept(String treelevel) {
		String sql = "SELECT t.ORGANIZATION_ID code , t.FULL_NAME texts FROM t_organization t WHERE t.TREE_LEVEL = '"+treelevel+"'";
		List<Object> list = organizationDao.findBySQL(sql);
		List<Map<String,String>> lm = new ArrayList<Map<String,String>>();
		if(null != list && list.size() > 0) {
			for(Object o : list) {
				Object[] obj = (Object[]) o;
				Map<String,String> map = new HashMap<String,String>();
				map.put("code", ((Integer) obj[0])+"");
				map.put("text", (String) obj[1]);
				lm.add(map);
			}
		}
		return lm;
	}
	
	@Override
	public List<Organization> findOrganizationByMyId(String myId) {
		String hql  =  "from Organization o where o.myid = '"+myId+"' order by o.pid,o.organizationId";
		return organizationDao.find(hql);
	}
	
	@Override
	public List<TreeGrid> findOrgTreeModelByMyId(String myId) {
		List<Organization> list = findOrganizationByMyId(myId);
		List<TreeGrid> result = new ArrayList<TreeGrid>();
		for (Organization organization : list) {
			TreeGrid treeGridModel = new TreeGrid();
			treeGridModel.setId(organization.getOrganizationId()+"");
			treeGridModel.setState("open");
			treeGridModel.setPid(organization.getPid() == null ? null : String.valueOf(organization.getPid()));
			treeGridModel.setName(organization.getFullName());
			result.add(treeGridModel);
		}
		return result;
	}
	

}


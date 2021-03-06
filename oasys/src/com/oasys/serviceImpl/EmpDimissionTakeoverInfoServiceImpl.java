package com.oasys.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.EmpDimissionTakeoverInfo;
import com.oasys.model.PpeTurnoverAttach;
import com.oasys.model.Users;
import com.oasys.service.EmpDimissionTakeoverInfoService;
import com.oasys.service.OrganizationService;
import com.oasys.service.UserService;
@Service(value="empDimissionTakeoverInfoService")
public class EmpDimissionTakeoverInfoServiceImpl implements
		EmpDimissionTakeoverInfoService {
	@Autowired
	public PublicDao<EmpDimissionTakeoverInfo> publicDao;
	@Autowired
	private UserService userService;
	@Autowired
	private OrganizationService orgService;
	@Override
	public List<EmpDimissionTakeoverInfo> findEmpDimissionTakeoverList(
			String appNo) {
		String hql = "from EmpDimissionTakeoverInfo where appNo = '"+appNo+"'";
		List<EmpDimissionTakeoverInfo> list = publicDao.find(hql);
		Users user = null;
		for (EmpDimissionTakeoverInfo empDimissionTakeoverInfo : list) {
			user = userService.getUserByID(empDimissionTakeoverInfo.getReceiverNo());
			empDimissionTakeoverInfo.setReceiverName(user.getName());
			empDimissionTakeoverInfo.setReceiverDept(user.getOrganizeId());
			empDimissionTakeoverInfo.setReceiverDeptName(orgService.getOrgNameByID(user.getOrganizeId()));
		}
		return list;
	}
	@Override
	public void saveEmpDimissionTakeoverInfo(
			EmpDimissionTakeoverInfo empDimissionTakeoverInfo) {
		// TODO Auto-generated method stub
		publicDao.saveOrUpdate(empDimissionTakeoverInfo);
	}
	@Override
	public void doDeleteById(EmpDimissionTakeoverInfo takeoverInfo) {
		// TODO Auto-generated method stub
		publicDao.delete(takeoverInfo);
	}
}

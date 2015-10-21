package com.bpms.service.impl;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.AuditInfoRecord;
import com.bpms.model.vo.AuditInfoRecordModel;
import com.bpms.service.AuditInfoRecordService;
import com.bpms.service.CommonService;
/**
 * 稽核信息记录ServiceImpl
 * @author PANCHUANHE
 * @date 2016/6/16
 */
@Service("auditInfoRecordService")
public class AuditInfoRecordServiceImpl implements AuditInfoRecordService {
	
	@Autowired
	private BaseDAO<AuditInfoRecord> baseDAO;
	@Autowired
	private CommonService commonService;

	@Override
	public boolean saveAuditInfoRecord(AuditInfoRecord auditInfoRecord) {
		if (StringUtils.isBlank(auditInfoRecord.getAuditId())) {
			baseDAO.save(auditInfoRecord);
		} else {
			baseDAO.update(auditInfoRecord);
		}
		return true;
	}

	@Override
	public List<AuditInfoRecord> findAIRByOid(String oid) {
		String hql = "from AuditInfoRecord o where o.loanOrder.loanOrderId='"+oid+"'";
		if(null != baseDAO.find(hql))
			return baseDAO.find(hql);
		else 
			return null;
	}

	@Override
	public AuditInfoRecord findAuditByName(String name,
			String loanOrderId,String auditItem) {
		String hql = "from AuditInfoRecord o where o.loanOrder.loanOrderId='"+loanOrderId+"' AND o.name = '"+name+"' AND o.auditItem = '"+auditItem+"'";
		List<AuditInfoRecord> list = baseDAO.find(hql);
		if(list.size()>0)
			return list.get(0);
		else 
			return null;
	}

	@Override
	public boolean deleteAudit(AuditInfoRecord air) {
		baseDAO.delete(air);
		return true;
	}

	@Override
	public List<AuditInfoRecordModel> findAuditInfoDetailByOid(String oid) {
		String hql = "from AuditInfoRecord o where o.loanOrder.loanOrderId='"+oid+"'";
		List<AuditInfoRecord> list = baseDAO.find(hql);
		List<AuditInfoRecordModel> auditList = new ArrayList<AuditInfoRecordModel>();
		if(null!=list && list.size()>0){
			for(AuditInfoRecord adir : list){
				AuditInfoRecordModel audit = new AuditInfoRecordModel();
				try {
					PropertyUtils.copyProperties(audit, adir);
				} catch (IllegalAccessException | InvocationTargetException
						| NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				audit.setAuditItemName(commonService.findDictName("audit_item", adir.getAuditItem()));
				if(null==adir.getName()){
					audit.setName(" ");
				}
				if(null==adir.getContactMethod()){
					audit.setContactMethod(" ");
				}
				if("audit_applicant".equals(adir.getAuditItem())){
					audit.setName(adir.getLoanOrder().getName());
					audit.setContactMethod(adir.getLoanOrder().getMobileTel());
				}
				auditList.add(audit);
			}
			return auditList;
		}
		return null;
	}

}

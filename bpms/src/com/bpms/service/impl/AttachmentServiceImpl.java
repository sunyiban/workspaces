package com.bpms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.Attachment;
import com.bpms.model.AuditinforecordAndAttachment;
import com.bpms.model.vo.AttachmentModel;
import com.bpms.service.AttachmentService;
import com.bpms.util.Collections;
import com.bpms.util.SaveUploadFileUtil;

@Service
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
	private BaseDAO<Attachment> attachmentDao;
	@Autowired
	private BaseDAO<AuditinforecordAndAttachment> auditInfoRecordAndAttachmentDao;

	@Override
	public boolean persistenceAttachment(Attachment attachment) {
		if (StringUtils.isBlank(attachment.getAttId())) {
			attachmentDao.save(attachment);
		} else {
			attachmentDao.update(attachment);
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AttachmentModel> findAttachmentList(String userId,
			String orderId) {
		// 定义返回值
		List<AttachmentModel> attachmentModels = new ArrayList<AttachmentModel>();
		/*String sql = "SELECT" + "		tba.ATT_DESC" + "		,tba.ATT_ID"
				+ "		,tba.ATT_NAME" + "		,tba.ATT_TYPE"
				+ "		,tsd.DICT_NAME AS ATT_TYPE_NAME" + "		,tba.CREATE_DATE"
				+ "		,tba.CREATOR" + "		,tu.NAME AS CREATOR_NAME, tba.ATT_URL" + "	FROM"
				+ "		t_bp_attachment tba"
				+ "	LEFT JOIN t_sys_dict tsd ON tba.ATT_TYPE = tsd.DICT_CODE"
				+ "	LEFT JOIN t_users tu ON tba.CREATOR = tu.USER_ID"
				+ "	WHERE" + "		tba.CREATOR = '" + userId + "'"
				+ "	AND tba.ORDER_ID = '" + orderId + "'";*/
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT tba.ATT_DESC,tba.ATT_ID,tba.ATT_NAME,tba.ATT_TYPE,tsd.DICT_NAME AS ATT_TYPE_NAME ");
		sql.append(" ,tba.CREATE_DATE,tba.CREATOR,tu.USER_NAME AS CREATOR_NAME, tba.ATT_URL, tba.FILE_TYPE ");
		sql.append(" FROM t_bp_attachment tba LEFT JOIN t_sys_dict tsd ON tba.ATT_TYPE = tsd.DICT_CODE");
		sql.append(" LEFT JOIN t_users tu ON tba.CREATOR = tu.USER_ID ");
		sql.append(" WHERE tba.CREATOR = '" + userId + "'AND tba.ORDER_ID = '" + orderId + "'");
		List<Object> list = attachmentDao.findBySQL(sql.toString());
		if (Collections.listIsNotEmpty(list)) {
			for (Object object : list) {
				Object[] obj = (Object[]) object;
				AttachmentModel model = new AttachmentModel();
				model.setAttDesc((String) obj[0]);
				model.setAttId((String) obj[1]);
				model.setAttName((String) obj[2]);
				model.setAttType((String) obj[3]);
				model.setAttTypeName((String) obj[4]);
				model.setCreateDate((Date) obj[5]);
				model.setCreator((String) obj[6]);
				model.setCreatorName((String) obj[7]);
				model.setAttURL(SaveUploadFileUtil.getFileSystemExtranetURL()+(String) obj[8]);
				model.setFileType((String) obj[9]);
				attachmentModels.add(model);
			}
		}
		return attachmentModels;
	}

	@Override
	public boolean persistenceAttachmentAndAuditInfo(
			AuditinforecordAndAttachment aaa) {
		if (StringUtils.isBlank(aaa.getAuditAttId())) {
			auditInfoRecordAndAttachmentDao.save(aaa);
		} else {
			auditInfoRecordAndAttachmentDao.update(aaa);
		}
		return true;
	}

	@Override
	public boolean delAttachment(String id) {
		String hql = "DELETE FROM Attachment t WHERE t.attId = '" + id + "'";
		Integer i = attachmentDao.executeHql(hql);
		return i > 0 ? true : false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<AttachmentModel> findAttachmentByULA(String userId,
			String orderType, String auditId, String loanOrderId ,String isDetail) {
		// 定义返回值
		List<AttachmentModel> list = new ArrayList<AttachmentModel>();
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT ta.ATT_ID,ta.ATT_NAME,ta.ATT_TYPE,tt.DICT_NAME,ta.ATT_URL,ta.FILE_TYPE  FROM t_bp_attachment ta LEFT JOIN t_bp_auditinforecord_and_attachment tbaa ON ta.ATT_ID = tbaa.ATT_ID LEFT JOIN  (select tsd.* from t_sys_dict tsd where tsd.PARENT_ID = (select tsd1.code_id from t_sys_dict tsd1 where tsd1.DICT_CODE = 'attachment_type') ) tt on ta.ATT_TYPE = tt.DICT_CODE WHERE ");
		sb.append(" ta.ORDER_TYPE = '" + orderType +"' ");
		sb.append(" AND ta.ORDER_ID = '" + loanOrderId + "'");
		if ("noauditId".equals(auditId)) {
			//sql += " AND tbaa.AUDIT_ID IS NULL";
			sb.append(" AND tbaa.AUDIT_ID IS NULL ");
		} else {
			//sql += " AND tbaa.AUDIT_ID = '" + auditId + "'";
			sb.append(" AND tbaa.AUDIT_ID = '" + auditId + "'");
		}
		//如果不是查看详情，则只显示当前登陆人所上传附件
		if(!"1".equals(isDetail)){
			sb.append("  AND ta.CREATOR = '" + userId + "'" );
		}
		List result = attachmentDao.findBySQL(sb.toString());
		if (Collections.listIsNotEmpty(result)) {
			for (Object object : result) {
				Object[] obj = (Object[]) object;
				AttachmentModel model = new AttachmentModel();
				model.setAttId((String) obj[0]);
				model.setAttName((String) obj[1]);
				model.setAttType((String) obj[2]);
				model.setAttTypeName((String) obj[3]);
				model.setAttURL(SaveUploadFileUtil.getFileSystemExtranetURL()+(String)obj[4]);
				model.setFileType((String) obj[5]);
				list.add(model);
			}
		}
		
		return list;
	}

	@Override
	public Attachment findSameByNT(Attachment att, String auditId) {
		/*String hql = "SELECT at FROM Attachment at,AuditinforecordAndAttachment aaa,AuditInfoRecord air WHERE at.attId = aaa.attId AND air.auditId = aaa.auditId AND air.auditId = '"
				+ auditId
				+ "' AND at.attName = '"
				+ att.getAttName()
				+ "' AND at.attType = '"
				+ att.getAttType()
				+ "' AND at.creator = '"
				+ att.getCreator()
				+ "' AND at.orderId = '" + att.getOrderId() + "'";*/
		String hql = "";
		if(StringUtils.isBlank(auditId)){
			hql = "SELECT ta FROM Attachment ta WHERE ta.orderId = '"+att.getOrderId()+"' AND ta.creator = '"+att.getCreator()+"' AND ta.attType = '"+att.getAttType()+"' AND ta.attName = '"+att.getAttName()+"'";
		}else {
			hql = "SELECT ta FROM Attachment ta , AuditinforecordAndAttachment taaa  WHERE taaa.attId = ta.attId AND ta.orderId = '"+att.getOrderId()+"' AND ta.creator = '"+att.getCreator()+"' AND ta.attType = '"+att.getAttType()+"' AND ta.attName = '"+att.getAttName()+"' AND taaa.auditId = '"+auditId+"'";
		}
		List<Attachment> attlist = attachmentDao.find(hql);
		if (attlist.size() == 0) {
			return null;
		} else {
			return attlist.get(0);
		}

	}

	@Override
	public boolean delAttachAuditRL(String id) {
		String hql = "DELETE FROM AuditinforecordAndAttachment t WHERE t.attId = '"
				+ id + "'";
		Integer i = auditInfoRecordAndAttachmentDao.executeHql(hql);
		return i > 0 ? true : false;
	}

	@Override
	public Attachment findAttachmentById(String id) {
		return attachmentDao.get(Attachment.class, id);
	}
	
	@Override
	public Attachment findSameAttachment(Attachment att){
		String hql = "SELECT at FROM Attachment at WHERE at.attName = '"
				+ att.getAttName()
				+ "' AND at.attType = '"
				+ att.getAttType()
				+ "' AND at.creator = '"
				+ att.getCreator()
				+ "' AND at.orderId = '" + att.getOrderId() + "'";
		List<Attachment> attlist = attachmentDao.find(hql);
		if (attlist.size() == 0) {
			return null;
		} else {
			return attlist.get(0);
		}
	}
	
	@Override
	public List<AttachmentModel> findAttachmentByOrderTypeAndOrderId(String userId,
			String orderType,String investOrderId) {
		// 定义返回值
		List<AttachmentModel> list = new ArrayList<AttachmentModel>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ta.ATT_ID,ta.ATT_NAME,ta.ATT_TYPE,tt.DICT_NAME, ta.ATT_URL, ta.FILE_TYPE FROM t_bp_attachment ta  LEFT JOIN  (select tsd.* from t_sys_dict tsd where tsd.PARENT_ID = (select tsd1.code_id from t_sys_dict tsd1 where tsd1.DICT_CODE = 'attachment_type_invest') ) tt on ta.ATT_TYPE = tt.DICT_CODE WHERE ta.ORDER_TYPE = '");
		sql.append(orderType + "' AND ta.CREATOR = '"+ userId+ "' AND ta.ORDER_ID = '" + investOrderId + "'");

		List<Object> result = attachmentDao.findBySQL(sql.toString());
		if (Collections.listIsNotEmpty(result)) {
			for (Object object : result) {
				Object[] obj = (Object[]) object;
				AttachmentModel model = new AttachmentModel();
				model.setAttId((String) obj[0]);
				model.setAttName((String) obj[1]);
				model.setAttType((String) obj[2]);
				model.setAttTypeName((String) obj[3]);
				model.setAttURL(SaveUploadFileUtil.getFileSystemExtranetURL()+(String)obj[4]);
				model.setFileType((String)obj[5]);
				list.add(model);
			}
		}
		return list;
	}
	
	@Override
	public List<AttachmentModel> findAllAttachmentList(String userId,
			String orderType, String orderId) {
		    // 定义返回值
			List<AttachmentModel> attachmentModels = new ArrayList<AttachmentModel>();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT tba.ATT_DESC,");
			sql.append("tba.ATT_ID,");
			sql.append("tba.ATT_NAME,");
			sql.append("tba.ATT_TYPE,");
			sql.append("(SELECT tsd2.DICT_NAME from t_sys_dict tsd2 where tsd2.DICT_CODE = tba.ATT_TYPE and tsd2.PARENT_ID = (SELECT tsd.CODE_ID from t_sys_dict tsd where tsd.DICT_CODE = '"+orderType+"')) as ATT_TYPE_NAME,");
			sql.append("tba.CREATE_DATE,");
			sql.append("tba.CREATOR,");
			sql.append("tu.USER_NAME AS CREATOR_NAME");
			sql.append(" FROM t_bp_attachment tba LEFT JOIN t_users tu ON tba.CREATOR = tu.USER_ID");
			sql.append(" WHERE tba.CREATOR = '"+userId+"' AND tba.ORDER_ID = '"+orderId+"'");
			List<Object> list = attachmentDao.findBySQL(sql.toString());
			if (Collections.listIsNotEmpty(list)) {
				for (Object object : list) {
					Object[] obj = (Object[]) object;
					AttachmentModel model = new AttachmentModel();
					model.setAttDesc((String) obj[0]);
					model.setAttId((String) obj[1]);
					model.setAttName((String) obj[2]);
					model.setAttType((String) obj[3]);
					model.setAttTypeName((String) obj[4]);
					model.setCreateDate((Date) obj[5]);
					model.setCreator((String) obj[6]);
					model.setCreatorName((String) obj[7]);
					attachmentModels.add(model);
				}
			}
			return attachmentModels;
	}

	// 本方法为投资业务专用，非本业务人员，请勿修改。
	@Override
	public List findAllAttachmentList4BanLiRenwu(String orderId,
			String orderType) {		
	    // 定义返回值
		List<AttachmentModel> attachmentModels = new ArrayList<AttachmentModel>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT tba.ATT_DESC,");
		sql.append("tba.ATT_ID,");
		sql.append("tba.ATT_NAME,");
		sql.append("tba.ATT_TYPE,");
		sql.append("(SELECT tsd2.DICT_NAME from t_sys_dict tsd2 where tsd2.DICT_CODE = tba.ATT_TYPE and tsd2.PARENT_ID = (SELECT tsd.CODE_ID from t_sys_dict tsd where tsd.DICT_CODE = '"+orderType+"')) as ATT_TYPE_NAME,");
		sql.append("tba.CREATE_DATE,");
		sql.append("tba.CREATOR,");
		sql.append("tu.USER_NAME AS CREATOR_NAME");
		sql.append(" FROM t_bp_attachment tba LEFT JOIN t_users tu ON tba.CREATOR = tu.USER_ID");
		sql.append(" WHERE tba.ORDER_ID = '"+orderId+"'");
		List<Object> list = attachmentDao.findBySQL(sql.toString());
		if (Collections.listIsNotEmpty(list)) {
			for (Object object : list) {
				Object[] obj = (Object[]) object;
				AttachmentModel model = new AttachmentModel();
				model.setAttDesc((String) obj[0]);
				model.setAttId((String) obj[1]);
				model.setAttName((String) obj[2]);
				model.setAttType((String) obj[3]);
				model.setAttTypeName((String) obj[4]);
				model.setCreateDate((Date) obj[5]);
				model.setCreator((String) obj[6]);
				model.setCreatorName((String) obj[7]);
				attachmentModels.add(model);
			}
		}
		return attachmentModels;				
	}
	
	
	@Override
	public List<AttachmentModel> findAttachmentByAuditId(String auditId) {
		List<AttachmentModel> list = new ArrayList<AttachmentModel>();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT tatt.ATT_ID,tatt.ATT_NAME,tatt.ATT_TYPE,td.DICT_NAME FROM t_bp_attachment tatt");
		sql.append(" LEFT JOIN t_bp_auditinforecord_and_attachment taaa ON taaa.ATT_ID = tatt.ATT_ID  ");
		sql.append(" LEFT JOIN t_sys_dict td ON td.DICT_CODE = tatt.ATT_TYPE ");
		sql.append(" WHERE taaa.AUDIT_ID = '"+auditId+"' AND td.PARENT_ID = (SELECT td.CODE_ID FROM t_sys_dict td WHERE td.DICT_CODE = 'attachment_type') ");
		
		List<Object> result = attachmentDao.findBySQL(sql.toString());
		if (Collections.listIsNotEmpty(result)) {
			for (Object object : result) {
				Object[] obj = (Object[]) object;
				AttachmentModel model = new AttachmentModel();
				model.setAttId((String) obj[0]);
				model.setAttName((String) obj[1]);
				model.setAttType((String) obj[2]);
				model.setAttTypeName((String) obj[3]);
				list.add(model);
			}
		}
		return list;
	}

	@Override
	public boolean deleteAttachmentByUrl(String attUrl) {
		String hql = "DELETE FROM Attachment t WHERE t.attURL = '" + attUrl + "'";
		Integer i = attachmentDao.executeHql(hql);
		return i > 0 ? true : false;
	}



}

package com.bpms.service;

import java.util.List;

import com.bpms.model.Attachment;
import com.bpms.model.AuditinforecordAndAttachment;
import com.bpms.model.vo.AttachmentModel;

/**
 * 
 * 附件的操作的service
 * 
 * @author 刘洪虎 2015/05/07.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/05/07 刘洪虎 创建.
 */
public interface AttachmentService {
	/**
	 * 持久化附件
	 */
	boolean persistenceAttachment(Attachment attachment);

	/**
	 * 根据用户的id和订单的id查询附件列表
	 * 
	 * @param userId
	 *            用户的id
	 * @param orderId
	 *            订单的id
	 * @return
	 */
	List<AttachmentModel> findAttachmentList(String userId, String orderId);

	/**
	 * 根据登陆用户，订单类型，稽核信息ID查询附件信息,订单ID
	 */
	List<AttachmentModel> findAttachmentByULA(String userId, String orderType,
			String auditId, String loanOrderId, String isDetail);

	/**
	 * 保存附件 稽核信息关系
	 */
	boolean persistenceAttachmentAndAuditInfo(AuditinforecordAndAttachment aaa);

	/**
	 * 根据ID删除附件
	 */
	boolean delAttachment(String id);
	
	/**
	 * 根据URL删除附件
	 * @param attUrl
	 * @return
	 */
	boolean deleteAttachmentByUrl(String attUrl);

	/**
	 * 通过附件名称，类型，订单ID，创建人ID,稽核信息ID 查询订单
	 * 
	 * @param attName
	 * @param attType
	 * @param creator
	 * @param orderId
	 * @return
	 */
	Attachment findSameByNT(Attachment attachment, String auditId);

	/**
	 * 删除附件，稽核信息关系
	 * 
	 * @param id
	 * @return
	 */
	boolean delAttachAuditRL(String id);

	/**
	 * 根据附件的id获取附件信息
	 * 
	 * @param id
	 * @return
	 */
	Attachment findAttachmentById(String id);
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年7月28日 下午1:36:42
	 * @Title:findSameAttachment
	 * @Description:TODO 通过附件名称，类型，订单ID，创建人ID 查询投资附件是否重复上传（这里描述这个方法的作用）
	 * @return
	 * @throws:
	 */
	Attachment findSameAttachment(Attachment att);
	
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年7月28日 下午1:53:33
	 * @Title:findAttachmentByOrderTypeAndOrderId
	 * @Description:TODO 查询投资人附件列表（这里描述这个方法的作用）
	 * @param userId
	 * @param orderType
	 * @param investOrderId
	 * @return
	 * @throws:
	 */
	public List<AttachmentModel> findAttachmentByOrderTypeAndOrderId(String userId,
			String orderType,String investOrderId);
	/**
	 * @Title: findAllAttachmentList 
	 * @Description: TODO 附件列表
	 * @param @param userId 受理人id
	 * @param @param orderType 附件类型
	 * @param @param orderId 订单id
	 * @param @return
	 * @return List<AttachmentModel>
	 * @throws
	 */
	public List<AttachmentModel> findAllAttachmentList(String userId,String orderType,String orderId);
	
	/**
	 * 根据稽核信息ID获取附件列表
	 * @param auditId
	 * @return
	 */
	public List<AttachmentModel> findAttachmentByAuditId(String auditId);

	/**
	 * @Title: findAllAttachmentList4BanLiRenwu
	 * @Description: TODO 投资申请时，办理任务对话框中，点击“备注信息”下拉框中的“查看附件”时，通过该方法进行查看
	 * @param @return
	 * @return String
	 * @throws
	 */		
	List findAllAttachmentList4BanLiRenwu(String orderId, String orderType);
}

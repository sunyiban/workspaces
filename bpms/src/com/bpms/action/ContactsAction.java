package com.bpms.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.AuditInfoRecord;
import com.bpms.model.Contacts;
import com.bpms.model.LoanorderAndContacts;
import com.bpms.model.vo.ContactsModel;
import com.bpms.service.AuditInfoRecordService;
import com.bpms.service.ContactsService;
import com.bpms.service.InvestorAndContactsService;
import com.bpms.service.LoanorderAndContactsService;
import com.bpms.util.Collections;
import com.bpms.view.model.DataModel;
import com.bpms.view.model.GridModel;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 紧急联系人Action
 * 
 * @author panchuanhe2015/06/23
 */
@Namespace("/contacts")
@Action(value = "contactsAction")
public class ContactsAction extends BaseAction implements
		ModelDriven<ContactsModel> {

	private static final long serialVersionUID = 1L;
	@Autowired
	private ContactsService contactsServiceImpl;
	@Autowired
	private LoanorderAndContactsService LoanorderAndContactsServiceImpl;
	@Autowired
	private InvestorAndContactsService investorAndContactsServiceImpl;
	@Autowired
	private AuditInfoRecordService auditInfoRecordService;
	
	private ContactsModel contactsModel;
	// 客户id
	private String loanerId;
	// 紧急联系人ids字符串
	private String contactIds;
	// 订单id
	private String loanOrderId;
	// 区分是查看紧急联系人类表,还是编辑紧急联系人列表
	private String operate;
	//投资者的ID
	private String investorId;	

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	/**
	 * 新增
	 */
	public String saveContacts() {
		boolean fl = contactsServiceImpl.saveContacts(contactsModel);
		DataModel json = null;
		if (fl) {
			json = new DataModel("提示", "恭喜你,保存成功!", true);
		} else {
			json = new DataModel("提示", "出错了,保存失败!", false);
		}
		OutputJson(json);
		return null;
	}

	/**
	 * 编辑时，回显紧急联系人列表,已与本订单绑定的紧急联系人，设置为默认选中
	 * 
	 * @return
	 */
	public String findAllListChacked() {
		GridModel gridModel = new GridModel();
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("cusId", loanerId);
			param.put("loanOrderId", loanOrderId);
			param.put("operate", operate);
			gridModel.setRows(contactsServiceImpl.findContactList(param));
//			gridModel.setTotal(contactsServiceImpl.findContactListCount(param));
			OutputJson(gridModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 贷款人的紧急联系人删除
	 */
	public String doDeleteById() {
		if (StringUtils.isNotBlank(contactIds)) {
			String[] ids = contactIds.split(",");
			for (int i = 0; i < ids.length; i++) {
				List<LoanorderAndContacts> loanorderAndContactsList = LoanorderAndContactsServiceImpl.findListByContactId(ids[i]);
				for (LoanorderAndContacts loanorderAndContacts : loanorderAndContactsList) {
					// 如果该紧急联系人被其订单绑定，则该紧急联系人不能被删除
					if (!loanOrderId.equals(loanorderAndContacts
							.getLoanOrderId())) {
						OutputJson(new DataModel("提示","该紧急联系人已被其他订单绑定不能删除,删除失败!",false));
						return null;
					} else {
						// 如果该联系人已经绑定了本订单，那么先解除关系在删除
						LoanorderAndContactsServiceImpl
								.deleteByContactIdAndLoanOrderId(
										loanorderAndContacts.getContactId(),
										loanorderAndContacts.getLoanOrderId());
					}
				}
				Contacts contacts = new Contacts();
				contacts.setContactId(ids[i]);
				contactsServiceImpl.doDeleteById(contacts);
			}
			OutputJson(new DataModel("提示","删除成功.",true));
			return null;
		}
		OutputJson(new DataModel("提示", "删除失败.", false));
		return null;
	}

	/**
	 * 贷款人的紧急联系人编辑
	 */
	public String doEdit() {
		if (StringUtils.isNotBlank(contactsModel.getContactId())) {
			List<LoanorderAndContacts> loanorderAndContactsList = LoanorderAndContactsServiceImpl.findListByContactId(contactsModel.getContactId());
			if(Collections.listIsNotEmpty(loanorderAndContactsList)){
				for (LoanorderAndContacts loanorderAndContacts : loanorderAndContactsList) {
					// 如果该紧急联系人被其订单绑定，则该紧急联系人不能被删除
					if (!loanOrderId.equals(loanorderAndContacts.getLoanOrderId())) {
						OutputJson(new DataModel("提示", "该紧急联系人已被其他订单绑定不能修改,修改失败!", false));
						return null;
					}
				}
			}
			OutputJson(new DataModel("提示", "可以进行编辑!", true));
			return null;
		}
		OutputJson(new DataModel("提示","没有选中要编辑的联系人!", false));
		return null;
	}
	
	
	/*************************************************/
	/**          投资相关的方法(贷款业务不要修改)             **/                               
	/*************************************************/
	/**
	 * 获得某个投资者相关的紧急联系人列表
	 * @Title: findAllInvestorContactsList 
	 * @author ZHANGJIAN
	 * @return String
	 * @date 2015年11月30日 下午3:07:07
	 */
	public String findInvestorContactListByInvestorId(){
		try {			
			GridModel gridModel = new GridModel();
			gridModel.setRows(contactsServiceImpl.findListById(investorId));
			gridModel.setTotal(contactsServiceImpl.count(investorId));
			OutputJson(gridModel);		
		} catch (Exception e) {
			e.printStackTrace();
		}				
		return null;
	}	
	
    /**
     * 投资人的紧急联系人删除
     */
	public String deleteByContactId(){
		DataModel json = null;
		//判断id串是否为空
		if (StringUtils.isNotBlank(contactIds)){
			String[] ids = contactIds.split(",");
			//循环这个id数组
			for(String contactId:ids){
				//删除该联系人与投资人的关联关系
				investorAndContactsServiceImpl.deleteByContactId(contactId);
				//在删除该紧急联系人
				Contacts contacts = new Contacts();
				contacts.setContactId(contactId);
				contactsServiceImpl.doDeleteById(contacts);
			}
			json = new DataModel("提示","恭喜你删除成功!",true);
			OutputJson(json);
			return null;
		}
		json = new DataModel("提示","出错了,删除失败!",false);
		OutputJson(json);
		return null;
	}

	/**
	 * 投资人的紧急联系人编辑
	 * @return
	 */
	public String doEditContact(){
		
		return null;
	}
	public String findContactByOid() {
		OutputJson(contactsServiceImpl.findContactByOid(loanOrderId));
		return null;
	}

	public String findContactsByOid() {
		List<ContactsModel> cts = contactsServiceImpl.findContactsByOid(loanOrderId);
		if(null != cts && cts.size()>0){
			for(ContactsModel contact : cts){
				String auditItem = "audit_"+contact.getRelationship();
				AuditInfoRecord auditInfo = auditInfoRecordService.findAuditByName(contact.getChName(), loanOrderId,auditItem);
				if(null!=auditInfo){
					contact.setAuditId(auditInfo.getAuditId());
					contact.setPhoneAuditRecord(auditInfo.getPhoneAuditRecord());
					contact.setWebAuditRecord(auditInfo.getWebAuditRecord());
					contact.setAuditItem(auditItem);
				}
			}
		}
		OutputJson(cts);
		return null;
	}

	@Override
	public ContactsModel getModel() {
		if (contactsModel == null) {
			contactsModel = new ContactsModel();
		}
		return contactsModel;
	}

	public String getLoanerId() {
		return loanerId;
	}

	public void setLoanerId(String loanerId) {
		this.loanerId = loanerId;
	}

	public String getContactIds() {
		return contactIds;
	}

	public void setContactIds(String contactIds) {
		this.contactIds = contactIds;
	}

	public String getLoanOrderId() {
		return loanOrderId;
	}

	public void setLoanOrderId(String loanOrderId) {
		this.loanOrderId = loanOrderId;
	}

	public ContactsModel getContactsModel() {
		return contactsModel;
	}

	public void setContactsModel(ContactsModel contactsModel) {
		this.contactsModel = contactsModel;
	}

	public String getInvestorId() {
		return investorId;
	}

	public void setInvestorId(String investorId) {
		this.investorId = investorId;
	}

}

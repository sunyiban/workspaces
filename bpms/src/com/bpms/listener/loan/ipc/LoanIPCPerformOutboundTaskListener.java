package com.bpms.listener.loan.ipc;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.bpms.listener.loan.BaseLoanTaskListener;
import com.bpms.util.Constants;

/**
 * 小额调查活动节点监听器
 * 
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanIPCPerformOutboundTaskListener extends BaseLoanTaskListener  implements TaskListener{

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_IPCXIAOERDIAOCHA;
	}

	@Override
	protected String[] getOrganizationIds() {
		String[] organizationIds = { Constants.LOAN_ORGANIZATION_ID };
		return organizationIds;
	}

	@Override
	public void notify(DelegateTask task) {
		// 设置当前用户执行的角色信息
		setLocalVariableRole(task);
		// 设置地方执行候选人组
		addCandidateGroupLocal(task);
		// 发送消息提醒
		sendMessageByCandidateGroup(task);
	}

}
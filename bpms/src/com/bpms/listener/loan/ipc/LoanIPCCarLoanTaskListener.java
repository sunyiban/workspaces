package com.bpms.listener.loan.ipc;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.bpms.listener.loan.BaseLoanTaskListener;
import com.bpms.util.Constants;

/**
 * 车贷负责人活动节点的监听器
 * 
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanIPCCarLoanTaskListener extends BaseLoanTaskListener implements TaskListener {

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_IPCCHEDAISHUJU;
	}

	@Override
	protected String[] getOrganizationIds() {
		return null;
	}

	@Override
	public void notify(DelegateTask task) {
		// 设置当前用户执行的角色信息
		setLocalVariableRole(task);
		// 设置当前执行候选人组
		addCandidateGroupLocal(task);
		// 发送任务个数提醒消息
		sendMessageByCandidateGroup(task);
	}
}

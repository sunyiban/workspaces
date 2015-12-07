package com.bpms.listener.loan.ipc;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.bpms.listener.loan.BaseLoanTaskListener;
import com.bpms.util.Constants;

/**
 * IPC负责人活动节点的监听器
 * 
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanIPCDepartmentSupervisorTaskListener extends BaseLoanTaskListener  implements TaskListener{

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_IPCBUMENZHUGUAN;
	}

	@Override
	protected String[] getOrganizationIds() {
		String[] organizationIds = { Constants.LOAN_ORGANIZATION_ID };
		return organizationIds;
	}

	@Override
	public void notify(DelegateTask task) {
		// 设置当前执行候选人组
		addCandidateGroupHQ(task);
		// 设置当前用户执行的角色信息
		setLocalVariableRole(task);
		// 给候选人组发送消息提醒
		sendMessageByCandidateGroup(task);;
	}
}

package com.bpms.listener.loan.ipc;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.bpms.listener.loan.BaseLoanTaskListener;
import com.bpms.util.Constants;
/**
 * IPC地方调查员活动节点监听
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanIPCCensorTaskListener extends BaseLoanTaskListener implements TaskListener  {

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_IPCDIFANGDIAOCHA;
	}
	
	@Override
	public void notify(DelegateTask task) {
		// 设置当前候选组--给指派的时候使用
		addCandidateGroupLocal(task);
		// 设置当前受理人信息
		task.setAssignee((String) task.getVariable("IPCCensorAssign"));
		// 设置当前受理角色信息
		setLocalVariableRole(task);
		// 发送用户受理任务提醒消息
		sendMessageByAssignee(task);
	}

	@Override
	protected String[] getOrganizationIds() {
		return null;
	}

}

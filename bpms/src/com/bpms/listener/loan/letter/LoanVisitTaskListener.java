package com.bpms.listener.loan.letter;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.bpms.listener.loan.BaseLoanTaskListener;
import com.bpms.util.Constants;

/**
 * 执行外访活动节点监听器
 * 
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanVisitTaskListener extends BaseLoanTaskListener  implements TaskListener{

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_VISIT;
	}

	@Override
	public void notify(DelegateTask task) {
		// 设置该活动节点受理人角色的信息
		setLocalVariableRole(task);
		// 设置地方候选组
		addCandidateGroupLocal(task);
		// 发送提示消息
		sendMessageByCandidateGroup(task);
	}

	@Override
	protected String[] getOrganizationIds() {
		return null;
	}
}

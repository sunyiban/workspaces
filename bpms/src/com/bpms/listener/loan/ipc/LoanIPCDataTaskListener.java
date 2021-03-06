package com.bpms.listener.loan.ipc;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.bpms.listener.loan.BaseLoanTaskListener;
import com.bpms.model.LoanOrder;
import com.bpms.model.LoanOrderHis;
import com.bpms.util.Constants;

/**
 * 数据岗活动节点的监听器
 * 
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanIPCDataTaskListener extends BaseLoanTaskListener implements TaskListener {

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_IPCSHUJUGANG;
	}

	@Override
	protected String[] getOrganizationIds() {
		String[] organizationIds = { Constants.LOAN_ORGANIZATION_ID, Constants.LOAN_ORGANIZATION_ID_SHUJUZU };
		return organizationIds;
	}

	@Override
	public void notify(DelegateTask task) {
		// 设置本地候选人组
		addCandidateGroupLocal(task);
		// 设置总部的候选人组
		addCandidateGroupHQ(task);
		// 设置当前用户执行的角色信息
		setLocalVariableRole(task);
		
		// 获取当前执行的业务订单
		LoanOrder loanOrder = getLoanOrderByTaskId(task);
		// 获取当前业务订单的备注信息,获取当前IPCShuJuGang角色受理人,设置受理人信息
		LoanOrderHis loanOrderHis = loanOrderHisService.findLoanOrderHis(getRoleCode(), loanOrder.getLoanOrderId());
		
		// 判断订单备注是否存在,如果存在则直接使用最近的一次IPC数据岗受理人.否则设定人受理组
		if (null!=loanOrderHis) {
			task.setAssignee(loanOrderHis.getAssignee());
			sendMessageByAssignee(task);
		}else{
			// 发送候选组人消息提醒
			sendMessageByCandidateGroup(task);;
		}
	}

}

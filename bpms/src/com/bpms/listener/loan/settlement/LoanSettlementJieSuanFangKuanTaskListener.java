package com.bpms.listener.loan.settlement;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.bpms.listener.loan.BaseLoanTaskListener;
import com.bpms.util.Constants;

/**
 * 贷款结算子流程中的结算放款对应活动节点的监听器
 * 
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanSettlementJieSuanFangKuanTaskListener extends	BaseLoanTaskListener  implements TaskListener{

	@Override
	protected String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_JIESUANFANGKUAN;
	}

	@Override
	protected String[] getOrganizationIds() {
		String[] organizationIds = { Constants.LOAN_ORGANIZATION_JIESUANZHONGXIN };
		return organizationIds;
	}

	@Override
	public void notify(DelegateTask task) {
		// 设置当前任务的受理角色
		setLocalVariableRole(task);
		// 设置总部候选人组
		addCandidateGroupHQ(task);
		// 发送提示消息
		sendMessageByCandidateGroup(task);
	}

}

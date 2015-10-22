package com.bpms.listener2;

import com.bpms.listener.BaseLoanTaskListener;
import com.bpms.util.Constants;
/**
 * 初审主管活动节点监听器
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanIPCTrialSupervisorTaskListener extends BaseLoanTaskListener {

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_IPCDIANHEZHUGUAN;
	}

}
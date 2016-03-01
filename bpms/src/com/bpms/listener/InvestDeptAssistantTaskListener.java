package com.bpms.listener;

import java.util.List;

import org.activiti.engine.delegate.DelegateTask;

import com.bpms.model.Role;
import com.bpms.model.Users;
import com.bpms.util.Collections;
import com.bpms.util.Constants;

/**
 * @Creater chenfl
 * @File_Name FinancingDeptAssistantTaskListener.java
 * @Version v1.0
 * @Creation_Date 2015年5月25日 下午1:23:14
 * @Modifier
 * @Modified_Date
 * @Description 财富业务流程部门助理节点任务监听器
 */
@SuppressWarnings("serial")
public class InvestDeptAssistantTaskListener extends BaseInvestTaskListener {

	@Override
	public String getRoleCode() {
		return Constants.INVEST_ROLE_CODE_BUMENZHULI; //部门助理角色常量
	}

	@Override
	public void notify(DelegateTask task) {			
		
		List<Users> users = userService.findUsersByRoleCode(getRoleCode());
		Role role = roleService.findRoleByCode(getRoleCode());
		task.createVariableLocal("role", role);
		if (Collections.listIsNotEmpty(users)) {
			for (Users user : users) {
				task.addCandidateUser(user.getUserId().toString());
				/*task.addCandidateGroup(rolecode);*/
			}
			sendMessageByUsers(task, users);
		}

	}

}

package com.oasys.listener.fd.ExpendVoucherApp;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.oasys.listener.BaseTaskListener;
import com.oasys.model.Users;
import com.oasys.util.Constants;

/**
 * 人员分配监听器
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class ExpendAppAssistantTaskListener extends BaseTaskListener implements
		TaskListener {

	@Override
	public void notify(DelegateTask task) {

		if (task.getTaskDefinitionKey().startsWith(
				Constants.APPLY_FOR_ADJUSTMENT) || task.getTaskDefinitionKey().startsWith(Constants.USERAPPLYOK)) {
			if (null != task.getVariable(Constants.CURRENT_USER_KEY)) {
				task.setAssignee(task.getVariable(Constants.CURRENT_USER_KEY)
						.toString());// 申请调整时 将任务指定回提交申请人
				task.removeVariable(Constants.RCN_RESULT);
			}
		} else if(task.getTaskDefinitionKey().startsWith(Constants.OA_ROLE_CODE_VEEP)){
			//执行到副总裁节点，只有赵雪梅能够处理
			Users users = userService.findUserByEName("xuemeizhao");
			if(users!=null){
				task.setAssignee(users.getUserId().toString());
				task.removeVariable(Constants.RCN_RESULT);
			}
			
		}else {
			setTaskRoleCodeByTask(task);
		}
	}
}

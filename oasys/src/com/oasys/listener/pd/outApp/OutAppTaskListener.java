package com.oasys.listener.pd.outApp;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.oasys.listener.BaseTaskListener;
import com.oasys.util.Constants;
/**
 * userTask监听
 * @ClassName: ExpensesAppTaskListener 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年10月10日 下午4:54:37
 */
public class OutAppTaskListener extends BaseTaskListener implements TaskListener{
	private static final long serialVersionUID = 1L;
	@Override
	public void notify(DelegateTask task) {
		//申请调整时 将任务指定回提交申请人
		if(task.getTaskDefinitionKey().startsWith(Constants.APPLY_FOR_ADJUSTMENT) || task.getTaskDefinitionKey().startsWith(Constants.PROPOSER_AFFIRM)){
			if(null != task.getVariable(Constants.CURRENT_USER_KEY))
				task.setAssignee(task.getVariable(Constants.CURRENT_USER_KEY).toString());
		}else{
			setTaskRoleCodeByTask(task);
		}
	}
}

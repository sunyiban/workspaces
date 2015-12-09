package com.oasys.listener.fd.TravelExpensesApp;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.el.FixedValue;

import com.oasys.listener.BaseTaskListener;
import com.oasys.util.Constants;


/**
 * 差率报销监听器，节点分配
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class TravelAppAssistantTaskListener extends BaseTaskListener implements TaskListener {
	

	@Override
	public void notify(DelegateTask task) {
		
		if(task.getTaskDefinitionKey().startsWith(Constants.APPLY_FOR_ADJUSTMENT)){
			if(null != task.getVariable(Constants.APPUSER))
				//提交审可能不是申请人
				task.setAssignee(task.getVariable(Constants.APPUSER).toString());		
			}else{
			setTaskRoleCodeByTask(task);
		}
	}
}

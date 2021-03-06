package com.oasys.listener.fd.LoanApp;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.model.BadgeApp;
import com.oasys.model.LeaveApp;
import com.oasys.model.StatusNameRef;
import com.oasys.service.BadgeAppService;
import com.oasys.service.ExpendVoucherAppService;
import com.oasys.service.LeaveAppService;
import com.oasys.service.LoanAppService;
import com.oasys.service.TravelExpensesAppService;
import com.oasys.service.workFlow.WorkFlowTaskService;
import com.oasys.util.Constants;

/**
 * 借款申请结束监听器
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class LoanAppStopExecutionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// 得到上下文环境
		WebApplicationContext ctx = ContextLoader
				.getCurrentWebApplicationContext();
		// 使用上下文环境中的getBean方法得到bean实例
		LoanAppService loanAppService= (LoanAppService) ctx.getBean("loanAppService");
		// 使用上下文环境中的getBean方法得到bean实例
		WorkFlowTaskService workFlowTaskService = (WorkFlowTaskService) ctx
				.getBean("workFlowTaskService");
		// 根据流程实例获取流程的BusinessKey,并获取当前的业务订单的id
		Integer id = null;
		if (StringUtils.isNotBlank(execution.getProcessBusinessKey())) {
			// 截取字符串，取BusinessKey小数点的第2个值,第二个值为业务订单的id
			id = Integer.valueOf(execution.getProcessBusinessKey().split("\\.")[1]);
		}
		
		Task task =workFlowTaskService.getTaskByBusinessKey(id.toString(), execution.getProcessBusinessKey().split("\\.")[0]);
		// 修改订单的状态 判断状态是否为申请撤销 如果的话 将流程状态更新为已撤销
		if(task.getTaskDefinitionKey().startsWith(Constants.APPLY_FOR_ADJUSTMENT) && Constants.TASK_COMMIT_RESULT.equals(execution.getVariable("result"))){
			loanAppService.updateProcStatus(id, "5");
		}else if( "2".equals(execution.getVariable("result"))){
			loanAppService.updateProcStatus(id, "6");//已拒绝
		}else if(execution.getVariable("result").toString().endsWith("JuJue")){
			loanAppService.updateProcStatus(id, "6");//已拒绝
		}else{
			loanAppService.updateProcStatus (id, "3");
		}
	}

}

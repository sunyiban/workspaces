package com.oasys.listener.ad.badgeApp;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.service.BadgeAppService;
import com.oasys.service.PPEScrapAppService;
import com.oasys.util.Constants;

/**
 * 开启流程监听器
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class BadgeAppStartExecutionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {

		if(execution.getVariable(Constants.CURRENT_BUSINESS_ID) != null){
			WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
			BadgeAppService badgeAppService =  (BadgeAppService) ctx
					.getBean("badgeAppService"); 
			Integer id = Integer.valueOf(execution.getVariable(Constants.CURRENT_BUSINESS_ID).toString());
			badgeAppService.upBadgeProcStatus(id,Constants.PROC_APPROVAL);
		}
	}

}

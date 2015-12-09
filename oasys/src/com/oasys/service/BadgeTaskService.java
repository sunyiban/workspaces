package com.oasys.service;


import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ActivitiTaskAlreadyClaimedException;

import com.oasys.model.BadgeApp;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.BadgeAppModel;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.WorkFlowTasksModel;
/**
 * 工牌申请流程
 * @author Administrator
 *
 */
public interface BadgeTaskService 
{

	
	/**
	 * 根据申请id开启流程
	 * @param pnrId
	 * @return
	 */
	String addBadgeAppStartProcessInstance(Integer pnrId);
	/**
	 * 查看个人任务代办列表
	 * @Title: findNotTaskClimList 
	 * @Description: TODO
	 * @param @param badgeApp
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<String>
	 * @date 2015年10月8日 下午3:12:35
	 * @throws
	 */
	List<BadgeAppModel> findNotTaskClimList(BadgeApp badgeApp,PageUtil pageUtil);
	/**
	 * 工牌申请数量
	 * @Title: findTotal 
	 * @Description: TODO
	 * @param @param badgeApp
	 * @param @return
	 * @author WANGXINCHENG
	 * @return Long
	 * @date 2015年10月8日 下午3:35:01
	 * @throws
	 */
	Long findTotal(BadgeApp badgeApp);
	/**
	 * 领取任务
	 * @Title: getTaskUserClaim 
	 * @Description: TODO
	 * @param @param taskId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年9月24日 上午9:55:27
	 * @throws
	 */
	void getTaskUserClaim(String taskId) throws ActivitiTaskAlreadyClaimedException;
	/**
	 * 个人任务办理
	 * @Title: addBadgeTaskMgr 
	 * @Description: TODO
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年10月9日 下午1:52:11
	 * @throws
	 */
	String addBadgeTaskMgr(WorkFlowTasksModel taskModel) throws Exception;
	
	/**
	 * 查看申请流程
	 * @Title: getDiagramResourceByPaId 
	 * @Description: TODO
	 * @param @param response
	 * @param @param paId
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年10月9日 下午6:59:44
	 * @throws
	 */
	void getDiagramResourceByPaId(HttpServletResponse response,Integer pnrId);

	/**
	 * 获取当前登录用户的角色
	 * @Title: getUserRole 
	 * @Description: TODO
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月20日 下午1:39:34
	 * @throws
	 */
	boolean getUserRole();
	
	/**
	 * 查询工牌申请汇总表
	 * @Title: findBadgeList 
	 * @Description: TODO
	 * @param @param badgeApp
	 * @param @param pageUtil
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<BadgeApp>
	 * @date 2015年11月30日 下午5:59:14
	 * @throws
	 */
	GridModel findBadgeList(BadgeApp badgeApp,PageUtil pageUtil);
	/**
	 * 查询工牌申请汇总表总申请数量
	 * @Title: findBadgeListTotal 
	 * @Description: TODO
	 * @param @param badgeApp
	 * @param @return
	 * @author WANGXINCHENG
	 * @return Long
	 * @date 2015年12月1日 下午1:42:06
	 * @throws
	 */
	Long findBadgeListTotal(BadgeApp badgeApp);
	
}
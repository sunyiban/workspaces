package com.bpms.service;

import java.io.IOException;
import java.util.List;

import org.activiti.engine.task.Task;

import com.bpms.model.InvestOrder;
import com.bpms.model.Users;

/**
 * 个人投资工作流service
 * @author panchuanhe
 * 2015/7/17
 */
public interface InvestOrderWorkFlowService {
	/**
	 * 启动流程实例
	 * @param loanOrder 启动流程实例参数
	 * @return 启动流程实例是否成功
	 */
	boolean saveStartProcessInstance(InvestOrder investOrder);
	/**
	 * @Title: checkWorkFlowImgByInvestOrderId 
	 * @Description: 查看流程图
	 * @param @param investOrderId
	 * @return void
	 * @throws
	 */
	void checkWorkFlowImgByInvestOrderId(String investOrderId) throws IOException;
	/**
	 * @Title: findAllTask 
	 * @Description: TODO 查询所有的待办任务
	 * @param @param firstResult 起始
	 * @param @param maxResults 每页总条数
	 * @param @return
	 * @return List<InvestOrder>
	 * @throws
	 */
	List<InvestOrder> findAllTask(Integer firstResult, Integer maxResults);
	/**
	 * @Title: findInvestOrderByTaskId 
	 * @Description: TODO 根据任务id查询订单
	 * @param @param taskId
	 * @param @return
	 * @return InvestOrder
	 * @throws
	 */
	InvestOrder findInvestOrderByTaskId(String taskId);
	
	/**
	 * @Title: findInvestOrderByTaskObj 
	 * @Description: TODO 根据Task对象查询订单
	 * @param Task Obj
	 * @return InvestOrder
	 * @throws
	 */
	InvestOrder findInvestOrderByTaskObj(Task task);

	
	/**
	 * @Title: findAllTaskCount 
	 * @Description: TODO 总条数
	 * @param @param taskId
	 * @param @return
	 * @return Long
	 * @throws
	 */
	Long findAllTaskCount(String userId);
	/**
	 * @Title: saveMyTask 
	 * @Description: TODO 签收任务
	 * @param @param taskId 任务ID
	 * @return void
	 * @throws
	 */
	void saveMyTask(String taskId);
	
	/**
	 * 获取当前"逍客专员"已经受理的所有的投资订单investOrder
	 * 	@param firstResult
	 * 	@param maxResults
	 * @return List<InvestOrder>
	 */	
	public List<InvestOrder> findAllClaimTask(Integer firstResult, Integer maxResults);
	
	/**
	 * 获取当前"逍客专员"已经受理的投资订单investOrder的"总数量"
	 * @return Long
	 */		
	public Long findAllClaimTaskCount(String userId);
	
	/**
	 * 
	 * @time:2015年7月28日 下午3:06:47
	 * @Title:saveSubmitTask
	 * @Description:TODO 完成任务（这里描述这个方法的作用）
	 * @param investOrder
	 * @param comment
	 * @param result
	 * @param processingResult
	 * @throws:
	 */
	public void saveSubmitTask(InvestOrder investOrder, String comment,
			String result, String processingResult);
	
	/**
	 * 
	 * @time:2015年8月3日 上午9:56:08
	 * @Title:getFinancingManagerByOrderId
	 * @Description:TODO 根据订单得到理财经理（这里描述这个方法的作用）
	 * @param investOrderId
	 * @throws:
	 */
	public Users getFinancingManagerByOrderId(String investOrderId);
}

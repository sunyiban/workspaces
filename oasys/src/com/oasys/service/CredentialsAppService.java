package com.oasys.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.oasys.model.CredentialsApp;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.CredentialsAppModel;
import com.oasys.viewModel.WorkFlowTasksModel;

public interface CredentialsAppService {
	//展示数据
	List<CredentialsAppModel> getList(Map<String, Object> map,PageUtil pageUtil,Integer id,Integer id1,String sql);
	//统计总数
	Long getCount(Map<String, Object> map);
	//保存主表数据
	boolean saveCredentialsApp(CredentialsApp credentialsApp);
	//删除主表数据
	boolean delCredentialsApp(Integer id);
	//更改流程状态
	boolean updateProcStatus(Integer id,String status);
	//更改线的状态
	void updateAppStatus(Integer id,String result);
	//提交申请
	String submitCredentialsApply(Integer id);
	//查看流程图
	void getDiagramResourceByCaId(HttpServletResponse response,Integer uqaId);
	//根据Id获取appNo确实是否外借
	boolean isBorrow(Integer id);
	//获取待办任务
	List<CredentialsAppModel> getTaskByGroup(Integer firstResult,Integer maxResults,WorkFlowTasksModel workFlowTasksModel,Map<String, Object> map);
	//获取待办任务总数
	Long getTaskCountByGroup(WorkFlowTasksModel workFlowTasksModel,Map<String, Object> map);
	//办理任务
	String saveSubmitTask(WorkFlowTasksModel taskModel,String csTypeName,String csType);
	//更改申请状态
	void updateApplyStatus(Integer id,String result);
	//批量受理任务
	String saveSubmitTaskBatch(WorkFlowTasksModel taskModel);
} 

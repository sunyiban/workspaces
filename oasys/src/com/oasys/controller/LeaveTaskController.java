package com.oasys.controller;



import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oasys.model.BadgeApp;
import com.oasys.model.LeaveApp;
import com.oasys.service.BadgeTaskService;
import com.oasys.service.LeaveAppService;
import com.oasys.service.LeaveTaskService;
import com.oasys.service.OrganizationService;
import com.oasys.service.StatusNameRefService;
import com.oasys.service.UserService;
import com.oasys.service.workFlow.WorkFlowService;
import com.oasys.service.workFlow.WorkFlowTaskService;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.BadgeAppModel;
import com.oasys.viewModel.DataModel;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;
import com.oasys.viewModel.WorkFlowTasksModel;
/**
 * 
 * @ClassName: LoginController
 * @Description: TODO
 * @Author wangxincheng
 * @Version 1.0
 * @Date 2015年8月17日 下午2:24:06
 *工牌申请表
 */

@Controller
@RequestMapping("/LeaveTask")
public class LeaveTaskController extends BaseController{
	
	@Autowired
	private LeaveTaskService leaveTaskService;
	@Autowired
	private LeaveAppService leaveAppService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private UserService userService;
	//申请状态
	@Autowired
	private StatusNameRefService  statusNameRefService;
	/** 注入service. */
	@Autowired
	private WorkFlowService workflowService;
	@Autowired
	private WorkFlowTaskService workFlowTaskService;	
	
	
	//提交申请
	@ResponseBody
	@RequestMapping(value="/startLeaveApp",method=RequestMethod.POST)
	public String startLeaveApp(HttpServletResponse httpServletResponse,@RequestParam(value="leaId",required=true) Integer leaId){
		String resultStr = "";
		Json json=null;
		resultStr = leaveTaskService.addLeaveStartProcessInstance(leaId);
		if(StringUtils.isBlank(resultStr)){
			json=getMessage(false);
		}else{
			json=new Json("提示", resultStr, true);
		}
		OutputJson(httpServletResponse,json);
		return null;
	}
	
	/**
	 * 查看休假申请待办任务列表
	 * @Title: findNotTaskClaimList 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param page
	 * @param @param rows
	 * @param @param leaveApp
	 * @param @param bindingResult
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年10月27日 上午11:10:59
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findNotTaskClaimList",method=RequestMethod.POST)
	public String findNotTaskClaimList(HttpServletResponse httpServletResponse,Integer page,Integer rows,@ModelAttribute("leaveApp") LeaveApp leaveApp, BindingResult bindingResult){
		//分页后展示数据
		PageUtil pageUtil = new PageUtil(page,rows);
		//申请详情
		List<LeaveApp> taskClimList = leaveTaskService.findNotTaskClimList(leaveApp, pageUtil);
		//申请数量
		Long total = leaveTaskService.findTotal(leaveApp);
		OutputJson(httpServletResponse, new GridModel(taskClimList, total));
		
		return null;
	}
	
	/**
	 * 个人领取任务
	 * @Title: taskUserClaim 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param taskId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年9月24日 上午10:48:17
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/taskUserClaim",method=RequestMethod.POST)
	public String taskUserClaim(HttpServletResponse httpServletResponse,String taskId){
		try {
			leaveTaskService.getTaskUserClaim(taskId);
			OutputJson(httpServletResponse,new DataModel("提示", "任务签收成功！", true));
		} catch (ActivitiTaskAlreadyClaimedException e) {
			OutputJson(httpServletResponse,new DataModel("提示", "此任务已被他人签收！", false));
		}
		return null;
	}
	
	/**
	 * 查询申请流程图
	 * @Title: showProcessImg 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param taskId
	 * @param @param leaId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年10月27日 上午11:12:55
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/showProcessImg",method=RequestMethod.GET)
	public String showProcessImg(HttpServletResponse httpServletResponse,String taskId,Integer leaId){
		String imgName = leaveAppService.findimgName(leaId);
		try {
			workflowService.getDiagramResourceByTaskId(httpServletResponse, imgName, taskId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	/**
	 * 公用的任务办理
	 * @Title: saveTaskLeaveCliApp 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param taskModel
	 * @param @param bindingResult
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月4日 下午4:04:55
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/saveTaskLeaveCliApp",method=RequestMethod.POST)
	public String saveTaskLeaveCliApp(HttpServletResponse httpServletResponse,@ModelAttribute("taskModel") WorkFlowTasksModel taskModel,String realBgDtime,String realEdDtime,  BindingResult bindingResult){
		boolean saveSuccess = true;
		String resultStr = "";
		try {
			resultStr = leaveTaskService.saveSubmitTask(taskModel,realBgDtime, realEdDtime);//执行受理申请
			if(StringUtils.isBlank(resultStr)){
				saveSuccess = false;
			}
		} catch (Exception e) {
			saveSuccess = false;
		}
		Json json = StringUtils.isBlank(resultStr)?getMessage(saveSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse, json);
		return null;
	}

	/**
	 * 批量处理任务
	 * @Title: addMangeTaskList 
	 * @Description: TODO
	 * @param @param httpRequest
	 * @param @param httpServletResponse
	 * @param @param taskModel
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月17日 下午4:06:47
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/addMangeTaskList",method=RequestMethod.POST)
	public String addMangeTaskList(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse, WorkFlowTasksModel taskModel) {
		boolean saveSuccess = true;
		String resultStr = "";
		try {
			resultStr =leaveTaskService.addMangeTaksList(taskModel);
		} catch (Exception e) {
			saveSuccess = false;
		}
		Json json = StringUtils.isBlank(resultStr)?getMessage(saveSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse, json);
		return null;
	}
	
	
}



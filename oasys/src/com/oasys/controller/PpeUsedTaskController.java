package com.oasys.controller;



import java.io.IOException;
import java.util.List;

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

import com.oasys.model.PpeUsedConfirmApp;
import com.oasys.service.OrganizationService;
import com.oasys.service.PpeUsedConfirmService;
import com.oasys.service.PpeUsedTaskService;
import com.oasys.service.StatusNameRefService;
import com.oasys.service.UserService;
import com.oasys.service.workFlow.WorkFlowService;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.DataModel;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;
import com.oasys.viewModel.WorkFlowTasksModel;
/**
 * 固定资产使用申请任务
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/PpeUsedTask")
public class PpeUsedTaskController extends BaseController{
	
	@Autowired
	private PpeUsedTaskService ppeUsedTaskService;
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
	private PpeUsedConfirmService ppeUsedConfirmService;
	
	
	
	/**
	 * 提交申请
	 * @Title: startBadgeApp 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param pnrId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月7日 下午4:58:55
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/startPpeUsedConfirm",method=RequestMethod.POST)
	public String startPpeUsedConfirm(HttpServletResponse httpServletResponse,@RequestParam(value="psaId",required=true) Integer psaId){
		String resultStr = "";
		Json json=null;
		resultStr = ppeUsedTaskService.addStartProcessInstance(psaId);
		if(StringUtils.isBlank(resultStr)){
			json=getMessage(false);
		}else{
			json=new Json("提示", resultStr, true);
		}
		OutputJson(httpServletResponse,json);
		return null;
	}
	
	/**
	 * 查询待办任务
	 * @Title: findPpeUsedTaskList 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param page
	 * @param @param rows
	 * @param @param ppeUsedConfirmApp
	 * @param @param bindingResult
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月8日 上午10:51:24
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findPpeUsedTaskList",method=RequestMethod.POST)
	public String findPpeUsedTaskList(HttpServletResponse httpServletResponse,Integer page,Integer rows,@ModelAttribute("ppeUsedConfirmApp") PpeUsedConfirmApp ppeUsedConfirmApp, BindingResult bindingResult){
		//分页后展示数据
		PageUtil pageUtil = new PageUtil(page,rows);
		//工牌申请详情
		List<PpeUsedConfirmApp> taskList = ppeUsedTaskService.findPpeUsedTaskList(ppeUsedConfirmApp, pageUtil);
		Long total = ppeUsedTaskService.findPpeUsedTaskTotal(ppeUsedConfirmApp);
		
		OutputJson2(httpServletResponse, new GridModel(taskList, total));
		
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
	 * @date 2015年12月8日 上午10:58:14
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/taskUserClaim",method=RequestMethod.POST)
	public String taskUserClaim(HttpServletResponse httpServletResponse,String taskId){
		try {
			ppeUsedTaskService.getTaskUserClaim(taskId);
			OutputJson(httpServletResponse,new DataModel("提示", "任务签收成功。", true));
		} catch (ActivitiTaskAlreadyClaimedException e) {
			OutputJson(httpServletResponse,new DataModel("提示", "此任务已被他人签收！！", false));
		}
		return null;
	}
	
	/**
	 * 任务办理
	 * @Title: saveTaskMgrResult 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param attPsaId
	 * @param @param reverter
	 * @param @param taskModel
	 * @param @param bindingResult
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月8日 下午3:16:11
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/saveTaskMgrResult",method=RequestMethod.POST)
	public String saveTaskMgrResult(HttpServletResponse httpServletResponse,Integer attPsaId,Integer reverter,@ModelAttribute("taskModel") WorkFlowTasksModel taskModel,  BindingResult bindingResult){
		boolean saveSuccess = true;
		String resultStr = "";
		try {
			resultStr = ppeUsedTaskService.addUserTask(taskModel, attPsaId, reverter);
		} catch (Exception e) {
			saveSuccess = false;
		}
		Json json = StringUtils.isBlank(resultStr)?getMessage(saveSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse, json);
		return null;
	}
	
	/**
	 * 查看流程图
	 * @Title: showProcessImg 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param taskId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月8日 下午7:54:49
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/showProcessImg",method=RequestMethod.GET)
	public String showProcessImg(HttpServletResponse httpServletResponse,String taskId){
		
		String imgName=Constants.PPEUSEDCONFIRMIMAGE;
		try {
			workflowService.getDiagramResourceByTaskId(httpServletResponse, imgName, taskId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}



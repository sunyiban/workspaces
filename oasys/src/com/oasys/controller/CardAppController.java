package com.oasys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oasys.model.CardApp;
import com.oasys.model.CardAppAttach;
import com.oasys.service.AuditPorcService;
import com.oasys.service.CardAppAttachService;
import com.oasys.service.CardApplyService;
import com.oasys.service.OrganizationService;
import com.oasys.service.RoleService;
import com.oasys.service.UserService;
import com.oasys.service.workFlow.WorkFlowTaskService;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.ComboBoxModel;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;
import com.oasys.viewModel.UsersModel;
import com.oasys.viewModel.WorkFlowTasksModel;

/**
 * 
 * @author Guo
 *
 */
@Controller 
public class CardAppController extends BaseController{
	private static Logger logger = Logger.getLogger(CardAppController.class);
	@Autowired
	private CardApplyService cardApplyService;
	@Autowired
	private AuditPorcService auditPorcService;
	@Autowired
	private UserService userService;
	@Autowired
	private CardAppAttachService cardAppAttachService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private WorkFlowTaskService workTaskService;

	/**
	 * 展示名片
	 * @Title: index 
	 * @Description: 展示名片
	 * @param @param response
	 * @param @param request
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年9月30日 上午10:59:50
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/callingCard/index")
	public String index(HttpServletResponse response,HttpServletRequest request,Integer page,Integer rows){
		Map<String, Object> map = new HashMap<String, Object>();
		GridModel gridModel = new GridModel();
		PageUtil pageUtil = new PageUtil(page,rows);
		map.put("status", request.getParameter("procStatus"));
		map.put("dateBegin", request.getParameter("appDateBefore"));
		map.put("dateEnd", request.getParameter("appDateAfter"));
		map.put("daetApplyBegin", request.getParameter("appApplyDateBefore"));
		map.put("daetApplyEnd", request.getParameter("appApplyDateAfter"));
		gridModel.setRows(cardApplyService.getList(map,pageUtil,1,null));//0查询所有人的申请,1查询登录人申请
		gridModel.setTotal(cardApplyService.getCount(map,1));
		OutputJson2(response,gridModel);
		return null;
	}
	
	/**
	 * 删除名片
	 * @Title: removeCard 
	 * @Description: 删除名片
	 * @param @param id
	 * @param @param map
	 * @param @param response
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年9月29日 下午2:07:40
	 * @throws
	 */
	@RequestMapping(value="/callingCard/removeCard")
	public String removeCard(@RequestParam("id")String id,ModelMap map,HttpServletResponse response){
		boolean flag=cardApplyService.delCard(Integer.parseInt(id+""));
		if(flag){
	 		 OutputJson2(response,new Json("提示","删除申请成功!",flag));
	 	}else {
	 		 OutputJson2(response,new Json("提示","删除申请失败!",flag));
		}
		return null;
	}
	
	/**
	 * 保存名片申请
	 * @Title: saveCardApp 
	 * @Description: 保存名片申请
	 * @param @param httpServletResponse
	 * @param @param cardAppAttach
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年11月17日 下午7:51:52
	 * @throws
	 */
	@RequestMapping("/callingCard/saveCardApp.do")
	public String saveCardApp(HttpServletResponse httpServletResponse,CardAppAttach cardAppAttach,String price){
		if(cardAppAttach.getName()==null || "".equals(cardAppAttach.getName())){
			cardAppAttach.setApplicantNo(Constants.getCurrendUser().getUserId());//获取当前登录人提交人 总部
		}
		if(cardAppAttach.getDeptNo()==null){
			cardAppAttach.setDeptNo(organizationService.findOrganizationByUserId(Constants.getCurrendUser().getUserId()).getOrganizationId());//获取当前登录人的部门 总部
		}
		CardApp cardApp = cardApplyService.saveCardAttach(cardAppAttach,price);//保存附件表和主表
		OutputJson(httpServletResponse,cardApp);
		return null;
	}
	
	/**
	 * 查询添加的申请
	 * @Title: findCardAttList 
	 * @Description: 查询添加的申请
	 * @param @param httpServletResponse
	 * @param @param appNo
	 * @param @param deptNo
	 * @param @param page
	 * @param @param rows
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年11月17日 下午7:52:17
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="callingCard/findCardAttList",method=RequestMethod.POST)
	public String findCardAttList(HttpServletResponse httpServletResponse,String appNo,String deptNo,Integer page,Integer rows){
		//增加
		if(deptNo==null || "".equals(deptNo)){
			deptNo=organizationService.findOrganizationByUserId(Constants.getCurrendUser().getUserId()).getOrganizationId()+"";
		}
		//申请人条数
		Long atttotal = cardApplyService.findCardAtttotal(appNo, deptNo);
		//申请人信息
		//分页后展示数据
		PageUtil pageUtil = new PageUtil(page,rows);
		List<CardAppAttach> attList = cardApplyService.findCardAttList(appNo,deptNo,pageUtil);
		OutputJson(httpServletResponse, new GridModel(attList, atttotal) );
		return null;
	}
	
	/**
	 * 删除名片申请
	 * @Title: deleteCardAttList 
	 * @Description: 删除名片申请
	 * @param @param httpServletResponse
	 * @param @param ids
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年11月17日 下午7:53:22
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/callingCard/delCardApp",method=RequestMethod.POST)
	public String deleteCardAttList(HttpServletResponse httpServletResponse,String ids){
		boolean flag = cardApplyService.delCardApply(ids);
		OutputJson(httpServletResponse, getMessage(flag));
		return null;
	}
	
	/**
	 * 加载姓名
	 * @Title: getUserInfo 
	 * @Description: 加载姓名
	 * @param @param q
	 * @param @param response
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年9月30日 上午11:00:58
	 * @throws
	 */
	@RequestMapping(value="/callingCard/getUserInfo")
	public String getUserInfo(@RequestParam("q")String q,HttpServletResponse response){
		GridModel gridModel = new GridModel();
		gridModel.setRows(userService.findOrgUserList(q));
		OutputJson(response, gridModel);
		List<ComboBoxModel> userList = userService.findOrgUserList(q);
		OutputJson(response, userList);
		return null;
	}
	
	/**
	 * 根据姓名加载信息
	 * @Title: loadUserInfo 
	 * @Description: 根据姓名加载信息
	 * @param @param q
	 * @param @param response
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年9月30日 上午11:01:14
	 * @throws
	 */
	@RequestMapping(value="/callingCard/loadUserInfo")
	public String loadUserInfo(@RequestParam("q")String q,HttpServletResponse response){
		List<UsersModel> list=null;
		try {
			list = userService.loadUserInfo(Integer.parseInt(q));
			OutputJson(response,list.get(0));
		} catch (NumberFormatException e) {
			System.err.println(e.toString());
		}
		return null;
	}
	
	/**
	 * 提交申请
	 * @Title: saveCardApply 
	 * @Description: 提交申请
	 * @param @param q
	 * @param @param response
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年9月30日 上午11:02:13
	 * @throws
	 */
	@RequestMapping(value="/callingCard/saveCardApply")
	public String saveCardApply(@RequestParam("id")String q,HttpServletResponse response){
		boolean isSuccess = true;
		String resultStr = "";
		try {
			resultStr=cardApplyService.submitCardApply(Integer.parseInt(q));
		} catch (Exception e) {
			System.out.println(e.toString());
			isSuccess = false;
		}
		Json json = StringUtils.isBlank(resultStr)?getMessage(isSuccess):new Json("提示", resultStr, true);
		OutputJson(response,json);
		return null;
	}
	
	/**
	 * 查看待办任务
	 * @Title: findAllPurchaseAppTaskList 
	 * @Description: 查看待办任务
	 * @param @param httpServletResponse
	 * @param @param page
	 * @param @param rows
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年9月24日 上午9:35:24
	 * @throws
	 */
	 @ResponseBody
	 @RequestMapping(value="/callingCard/QueryCardTask",method=RequestMethod.POST)
	 public String findAllPurchaseAppTaskList(HttpServletRequest request,HttpServletResponse httpServletResponse,Integer page,Integer rows,WorkFlowTasksModel workFlowTasksModel){
//	    	Integer firstResult = (page-1)*rows;
//	        Integer maxResults = rows;
		 	Map<String, Object> map = new HashMap<String, Object>();
			map.put("daetApplyBegin", request.getParameter("appApplyDateBefore"));
			map.put("daetApplyEnd", request.getParameter("appApplyDateAfter"));
	        GridModel gridModel = new GridModel();
	        gridModel.setRows(cardApplyService.getTaskByGroup(map,page,rows,workFlowTasksModel));
	        gridModel.setTotal(cardApplyService.getTaskTotalByGroup(map,workFlowTasksModel));
	        OutputJson2(httpServletResponse, gridModel);
	    	return null;
	  }
	 
	 /**
	  * 签收任务
	  * @Title: signTask 
	  * @Description: 签收任务
	  * @param @param taskId
	  * @param @param httpServletResponse
	  * @param @return
	  * @author Guo
	  * @return String
	  * @date 2015年10月9日 下午2:24:31
	  * @throws
	  */
	 @ResponseBody
	 @RequestMapping(value="/callingCard/SignTask",method=RequestMethod.POST)
	 public String signTask(@RequestParam("taskId")String taskId,HttpServletResponse httpServletResponse){
		 boolean isSuccess = true;
		 try{
		    workTaskService.signForTask(taskId);//执行任务签收
		 }catch (ActivitiTaskAlreadyClaimedException e) {
			System.out.println(e.toString());
			isSuccess = false;
		 }
		 OutputJson(httpServletResponse,getMessage(isSuccess));
		 return null;
	  }
	 
	 
	 /**
	  * 办理任务
	  * @Title: handleTast 
	  * @Description: 办理任务
	  * @param @param status
	  * @param @param taskId
	  * @param @param isSuccess
	  * @param @param httpServletResponse
	  * @param @return
	  * @author Guo
	  * @return String
	  * @date 2015年10月9日 下午2:24:43
	  * @throws
	  */
	 @ResponseBody
	 @RequestMapping(value="/callingCard/handleTask",method=RequestMethod.POST)
	public String saveTaskEmpSalPositionChgApp(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse, @ModelAttribute("taskModel") WorkFlowTasksModel taskModel,  BindingResult bindingResult) {
		boolean saveSuccess = true;
		String resultStr = "";
		try {
			resultStr = cardApplyService.saveSubmitTask(taskModel);//执行受理申请
		} catch (Exception e) {
			System.out.println(e.toString());
			saveSuccess = false;
		}
		Json json = StringUtils.isBlank(resultStr)?getMessage(saveSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse, json);
		return null;
	}
	 /**
	  * 批量受理任务
	  * @Title: saveTaskCardAppBarch 
	  * @Description: 批量受理任务
	  * @param @return
	  * @author Guo
	  * @return String
	  * @date 2015年12月8日 下午2:18:12
	  * @throws
	  */
	 @ResponseBody
	 @RequestMapping("callingCard/saveTaskCardAppBatch")
	 public String saveTaskCardAppBarch(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse, WorkFlowTasksModel taskModel){
		 boolean saveSuccess = true;
			String resultStr = "";
			try {
				resultStr = cardApplyService.saveSubmitTaskBatch(taskModel);//执行受理申请
			} catch (Exception e) {
				System.out.println(e.toString());
				saveSuccess = false;
			}
			Json json = StringUtils.isBlank(resultStr)?getMessage(saveSuccess):new Json("提示", resultStr, true);
			OutputJson(httpServletResponse, json);
			return null;
	 }
	 
	 /**
	  * 查看流程图
	  * @Title: showProcessImg 
	  * @Description: 查看流程图
	  * @param @param httpServletResponse
	  * @param @param paId
	  * @param @return
	  * @author Guo
	  * @return String
	  * @date 2015年10月14日 下午6:50:10
	  * @throws
	  */
	 @RequestMapping(value="/callingCard/showProcessImg",method=RequestMethod.GET)
     public String showProcessImg(HttpServletResponse response,Integer caId){
	 	cardApplyService.getDiagramResourceByCaId(response, caId);
    	return null;
     }
	 
	 /**
	  * 查询申请人部门内所有满足入职满1个月的用户(分部)
	  * @Title: getUserByDept 
	  * @Description: 查询申请人部门内所有满足入职满1个月的用户(分部)
	  * @param @param response
	  * @param @param organizeId
	  * @param @return
	  * @author Guo
	  * @return String
	  * @date 2015年12月2日 上午11:11:02
	  * @throws
	  */
	 @RequestMapping(value="/callingCard/getUserByDept")
	 public String getUserByDept(HttpServletResponse response,Integer organizeId){
		 List<ComboBoxModel> list = cardApplyService.getUserByDeptNo(organizeId);
		 OutputJson(response, list);
		 return null;
	 }
	 
	 /**
	  * 判断总部分部
	  * @Title: getTypeByIsHeadOrBranch 
	  * @Description: 判断总部分部
	  * @param @param response
	  * @param @return
	  * @author Guo
	  * @return String
	  * @date 2015年12月18日 上午10:02:14
	  * @throws
	  */
	 @RequestMapping(value="/callingCard/isHeadOrBranch")
	 public String getTypeByIsHeadOrBranch(HttpServletResponse response){
		 boolean flag = false;//分部
		 if("0".equals(organizationService.findOrganizationByUserId(Constants.getCurrendUser().getUserId()).getDeptLevel())){
			flag = true;
		 }
		 OutputJson(response, getMessage(flag));
		 return null;
	 }
	 
}

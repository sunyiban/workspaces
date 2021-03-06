package com.oasys.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oasys.model.StaffRecruitAppModel;
import com.oasys.model.VO.StaffRecruitAppVOModel;
import com.oasys.service.OrganizationService;
import com.oasys.service.StaffRecruitAppService;
import com.oasys.service.UserService;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.DataModel;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;
import com.oasys.viewModel.WorkFlowTasksModel;



/**
 * 员工招聘申请Controller
 * @ClassName: StaffRecruitAppController 
 * @author ZHANGJIAN
 * @date 2015年11月10日 
 */
@Controller
@RequestMapping("/staffRecruitAppController")
public class StaffRecruitAppController extends BaseController{
	
	@Autowired //与员工招聘申请相关的Service
	private StaffRecruitAppService staffRecruitAppService;  
	
	@Autowired //与当前的登录用户相关的Service
	private UserService userService;  
	
	@Autowired //与公司组织相关的Service
	private OrganizationService organizationService; 
	

	/**
	 *  查看 员工招聘申请对象List
	 * @Title: findStaffRecruitAppList 
	 * @param @param httpServletResponse
	 * @param @param page
	 * @param @param rows
	 * @param @param staffRecruitAppModel
	 * @author ZHANGJIAN
	 * @return String
	 * @date 2015年11月10日 下午3:24:50
	 */
	@ResponseBody
	@RequestMapping(value="/findStaffRecruitAppList",method=RequestMethod.POST)
	public String findStaffRecruitAppList(HttpServletResponse httpServletResponse,Integer page,Integer rows,StaffRecruitAppVOModel voModel) {		
		PageUtil pageUtil = new PageUtil(page, rows);
		GridModel gridModel = new GridModel();
		gridModel.setRows(staffRecruitAppService.findStaffRecruitAppList(pageUtil, voModel));
		gridModel.setTotal(staffRecruitAppService.countSatisfyiedStaffRecruitAppModel(voModel));
		OutputJson2(httpServletResponse, gridModel);
		return null;				
	}	
	
	
	/**
	 * 新增 员工招聘申请
	 * @Title: saveStaffRecruitApp 
	 * @param @param httpServletResponse
	 * @param @param staffRecruitAppModel
	 * @author ZHANGJIAN
	 * @return String
	 * @date 2015年11月10日 下午3:25:25
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/saveStaffRecruitApp",method=RequestMethod.POST)	
	public String saveStaffRecruitApp(HttpServletResponse httpServletResponse,StaffRecruitAppModel oneModel){		
		
		boolean isSaveSuccess = staffRecruitAppService.saveStaffRecruitApp(oneModel);
		if(isSaveSuccess){
			OutputJson(httpServletResponse, new DataModel("提示","更新数据成功!",isSaveSuccess));
		}else{
			OutputJson(httpServletResponse, new DataModel("提示","出错了,保存失败!",isSaveSuccess));
		}		
		return null;
	}
		
	/**
	 * 根据指定ID，删除指定StaffRecruitApp对象。
	 * @Title: deleteStaffRecruitAppByMraID 
	 * @param @param httpServletResponse
	 * @param @param mraId
	 * @author ZHANGJIAN
	 * @return String
	 * @date 2015年11月12日 下午3:38:40
	 */
	@ResponseBody
	@RequestMapping(value="/deleteStaffRecruitAppByMraID",method=RequestMethod.POST)	
	public String deleteStaffRecruitAppByMraID(HttpServletResponse httpServletResponse,Integer mraId){				
		boolean isDelSuccess = staffRecruitAppService.deleteStaffRecruitAppByMraID(mraId);
		if(isDelSuccess){
			OutputJson(httpServletResponse, new DataModel("提示","成功删除指定数据!",isDelSuccess));
		}else{
			OutputJson(httpServletResponse, new DataModel("提示","删除指定数据失败!",isDelSuccess));
		}		
		return null;
	}

	/***********************************************************/
	/***********************************************************/	
	/******************** 下边是流程相关的方法   ************************/
	/***********************************************************/
	/***********************************************************/	
	
	
	/**
	 * 开启流程
	 * @Title: submitApply 
	 * @param @param httpServletResponse
	 * @param @param oneModel
	 * @author ZHANGJIAN
	 * @return String
	 * @date 2015年11月20日 上午10:45:53
	 */
	@ResponseBody
	@RequestMapping(value="/startProcessStaffRecruitApp",method=RequestMethod.POST)		
	public String startProcessStaffRecruitApp(HttpServletResponse httpServletResponse,StaffRecruitAppModel oneModel){
		boolean isSuccess = true;
		String resultStr = "";
		try {
			resultStr = staffRecruitAppService.startProcessStaffRecruitApp(oneModel);
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
		}
		Json json = StringUtils.isBlank(resultStr)?getMessage(isSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse,json);
		return null;	
	}

	/**
	 * 显示流程图
	 * @Title: showProcessImg 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param mraId
	 * @param @return
	 * @author ZHANGJIAN
	 * @return String
	 * @date 2015年11月19日 下午4:51:35
	 * @throws
	 */
    @RequestMapping(value="/showProcessImg",method=RequestMethod.GET)
    public String showProcessImg(HttpServletResponse httpServletResponse,Integer mraId){
    	staffRecruitAppService.getDiagramResourceByPaId(httpServletResponse,mraId);
    	return null;
    }	
	
    
    
    /**
     * 查询所有任务List
     * @Title: findAllStaffRecruitAppTaskList 
     * @param @param httpServletResponse
     * @param @param page
     * @param @param rows
     * @param @param workFlowTasksModel
     * @author ZHANGJIAN
     * @return String
     * @date 2015年11月20日 下午4:26:42
     * @throws
     */
	@ResponseBody
	@RequestMapping(value="/findAllStaffRecruitAppTaskList",method=RequestMethod.POST)
	public String findAllStaffRecruitAppTaskList(HttpServletResponse httpServletResponse,Integer page,Integer rows,WorkFlowTasksModel workFlowTasksModel){
		GridModel gridModel = new GridModel();
		gridModel.setRows(staffRecruitAppService.findAllStaffRecruitAppTaskList(new PageUtil(page, rows),workFlowTasksModel));
		gridModel.setTotal(staffRecruitAppService.countFindAllStaffRecruitAppTaskList());
		OutputJson2(httpServletResponse, gridModel);
		return null;
	}
    
	
	/**
     * 签收任务
     * @Title: holdWorkTask 
     * @Description: TODO
     * @param @param httpServletResponse
     * @param @param taskId
     * @param @return
     * @author PANCHUANHE
     * @return String
     * @date 2015年10月13日 下午1:21:00
     * @throws
     */
    @ResponseBody
    @RequestMapping(value="/holdWorkTask",method=RequestMethod.POST)
    public String holdWorkTask(HttpServletResponse httpServletResponse,String taskId){
    	boolean bl = staffRecruitAppService.saveHoldWorkTask(taskId);
    	if(bl){
    		OutputJson(httpServletResponse, new Json("提示","签收任务成功!",bl));
    	}else{
    		OutputJson(httpServletResponse, new Json("提示","出错了,签收任务失败!",bl));
    	}
    	return null;
    }
    
    /**
     * 提交任务
     * @Title: submitTask 
     * @Description: TODO
     * @param @param httpServletResponse
     * @param @param taskModel
     * @param @param bindingResult
     * @param @return
     * @author ZHANGJIAN
     * @return String
     * @date 2015年11月23日 下午2:37:06
     * @throws
     */
    @ResponseBody
    @RequestMapping(value="/submitTask",method=RequestMethod.POST)
    public String submitTask(HttpServletResponse httpServletResponse,@ModelAttribute("taskModel") WorkFlowTasksModel taskModel,BindingResult bindingResult){
    	boolean saveSuccess = true;
    	String resultStr = "";
    	try {
    		resultStr = staffRecruitAppService.saveSubmitTaskBo(taskModel);
		} catch (Exception e) {
			e.printStackTrace();
			saveSuccess = false;
		}
    	Json json = StringUtils.isBlank(resultStr)?getMessage(saveSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse, json);
    	return null;
    }
    /**
     * 批量办理任务
     * @Title: submitTaskBatch 
     * @Description: TODO
     * @param @param httpServletResponse
     * @param @param taskModel
     * @param @param bindingResult
     * @param @return
     * @author PANCHUANHE
     * @return String
     * @date 2016年1月6日 下午3:26:43
     * @throws
     */
    @ResponseBody
    @RequestMapping(value="/submitTaskBatch",method=RequestMethod.POST)
    public String submitTaskBatch(HttpServletResponse httpServletResponse,@ModelAttribute("taskModel") WorkFlowTasksModel taskModel,BindingResult bindingResult){
    	boolean saveSuccess = true;
    	String resultStr = "";
    	try {
    		resultStr = staffRecruitAppService.submitTaskBatch(taskModel);
		} catch (Exception e) {
			e.printStackTrace();
			saveSuccess = false;
		}
    	Json json = StringUtils.isBlank(resultStr)?getMessage(saveSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse, json);
    	return null;
    }
    
}

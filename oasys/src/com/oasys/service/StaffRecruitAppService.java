package com.oasys.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.oasys.model.StaffRecruitAppModel;
import com.oasys.model.VO.StaffRecruitAppVOModel;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.WorkFlowTasksModel;

/**
 * 员工招聘申请Service
 * @ClassName: StaffRecruitAppService 
 * @author ZHANGJIAN
 * @date 2015年10月12日 上午9:20:24
 */
public interface StaffRecruitAppService {
	
	/**
	 * 新增 员工招聘申请
	 * @Title: saveStaffRecruitApp 
	 * @param manpowerRequisitionApp
	 * @author ZHANGJIAN
	 * @return boolean
	 * @date 2015年11月10日 下午2:22:32
	 */
	public boolean saveStaffRecruitApp(StaffRecruitAppModel staffRecruitAppModel);
	
	/**
	 * 查看 员工招聘申请对象List
	 * @Title: findStaffRecruitAppList 
	 * @param pageUtil
	 * @param staffRecruitAppModel
	 * @author ZHANGJIAN
	 * @return List<StaffRecruitAppModel>
	 * @date 2015年11月10日 下午2:36:44
	 */
	public List<StaffRecruitAppVOModel> findStaffRecruitAppList(PageUtil pageUtil,StaffRecruitAppVOModel staffRecruitAppVOModel);
	
	/**
	 * 根据不同的ID(ids) 查找，员工招聘申请对象List
	 * @Title: findStaffRecruitAppByIds 
	 * @param @param ids
	 * @param @param pageUtil
	 * @author ZHANGJIAN
	 * @return List<StaffRecruitAppModel>
	 * @date 2015年11月10日 下午2:40:02
	 */
	public List<StaffRecruitAppVOModel> findStaffRecruitAppByIds(String ids, PageUtil pageUtil, StaffRecruitAppVOModel staffRecruitAppVOModel);
	
	/**
	 * 根据不同的ID(ids) 查找，员工招聘申请对象List
	 * @Title: countFindAllStaffRecruitAppModel 
	 * @Description: TODO
	 * @param @return
	 * @author ZHANGJIAN
	 * @return Long
	 * @date 2015年11月10日 下午2:42:30
	 * @throws
	 */
	public Long countAllStaffRecruitAppModel();

	
	/**
	 * 返回满足查询条件的记录条数。
	 * @Title: countSatisfyiedStaffRecruitAppModel 
	 * @param @param StaffRecruitAppVOModel
	 * @author ZHANGJIAN
	 * @return Long
	 * @date 2015年11月12日 下午8:23:38
	 * @throws
	 */
	public Long countSatisfyiedStaffRecruitAppModel(StaffRecruitAppVOModel voModel);
	
	
	/**
	 * 根据ID(ids) 查找，删除指定的员工招聘申请对象。
	 * @Title: deleteStaffRecruitAppByMraID 
	 * @param @param mraID
	 * @author ZHANGJIAN
	 * @return boolean
	 * @date 2015年11月11日 下午5:47:02
	 * @throws
	 */
	public boolean deleteStaffRecruitAppByMraID(Integer mraId);
	
	
	
	/*******************************************************************/
	/************************ 与流程处理相关的方法    *****************************/
	/*******************************************************************/
	
	/**
	 * 提交(员工招聘)申请
	 * @Title: sumitApply 
	 * @param @param model
	 * @author ZHANGJIAN
	 * @return String
	 * @date 2015年11月13日 下午2:13:45
	 */
	public String startProcessStaffRecruitApp(StaffRecruitAppModel model);
	
	
    /**
     * 判断该用户是总部还是分部
     * @Title: getProcessImgName 
     * @param @param StaffRecruitAppModel
     * @return String
     * @date 2015年10月13日 上午10:58:39
     * @throws
     */
	public String getProcessImgName(StaffRecruitAppModel model) ;
	
	/**
	 * 更新申请状态appStatus
	 *   申请状态，记录流程中各分支线路名称，例如：AdminDirectorReject
	 * @Title: updateStaffRecruitAppStatus 
	 * @Description: TODO
	 * @param @param state
	 * @author ZHANGJIAN
	 * @date 2015年11月13日 下午5:31:02
	 */
	public void updateStaffRecruitAppStatus(Integer mraId, String state);

	
	/**
	 * 更新流程状态
	 * 	取值范围：1－初始状态，2－审批中，3－已完成，4－已失效，5－已撤销
	 * @Title: updateStaffRecruitProcessStatus 
	 * @param @param mraId
	 * @param @param state
	 * @author ZHANGJIAN
	 * @return void
	 * @date 2015年11月13日 下午5:33:07
	 */
	public void updateStaffRecruitProcessStatus(Integer mraId, String state);

	/**
	 * 
	 * @Title: getDiagramResourceByPaId 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param mraId
	 * @author ZHANGJIAN
	 * @return void
	 * @date 2015年11月19日 下午4:50:06
	 * @throws
	 */
	public void getDiagramResourceByPaId(
			HttpServletResponse httpServletResponse, Integer mraId);

	/**
	 * 查询所有任务List
	 * @Title: findAllEmpFullmemberAppTaskList 
	 * @param @param pageUtil
	 * @param @param workFlowTasksModel
	 * @author ZHANGJIAN
	 * @return List
	 * @date 2015年11月20日 下午4:23:41
	 * @throws
	 */	
	public List findAllStaffRecruitAppTaskList(PageUtil pageUtil,
			WorkFlowTasksModel workFlowTasksModel);

	/**
	 * 计算所有任务的条数
	 * @Title: countFindAllStaffRecruitAppTaskList 
	 * @Description: TODO
	 * @param @return
	 * @author ZHANGJIAN
	 * @return Long
	 * @date 2015年11月20日 下午4:25:04
	 * @throws
	 */
	public Long countFindAllStaffRecruitAppTaskList();

	/**
	 * 签收任务
	 * @Title: saveHoldWorkTask 
	 * @param @param taskId
	 * @author ZHANGJIAN
	 * @return boolean
	 * @date 2015年11月23日 上午11:56:54
	 */
	public boolean saveHoldWorkTask(String taskId);
	
	/**
	 * 提交任务
	 * @Title: saveSubmitTaskBo 
	 * @param @param taskModel
	 * @author ZHANGJIAN
	 * @return String
	 * @date 2015年11月23日 下午2:37:30
	 * @throws
	 */
	public String saveSubmitTaskBo(WorkFlowTasksModel taskModel);
	/**
	 * 批量办理任务
	 * @Title: submitTaskBatch 
	 * @Description: TODO
	 * @param @param taskModel
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2016年1月6日 下午3:31:05
	 * @throws
	 */
	public String submitTaskBatch(WorkFlowTasksModel taskModel);
	
}

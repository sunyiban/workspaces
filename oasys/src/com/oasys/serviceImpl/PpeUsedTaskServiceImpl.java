package com.oasys.serviceImpl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.PpeUsedConfirmApp;
import com.oasys.model.PpeUsedConfirmAppAttach;
import com.oasys.service.AuditProcHisService;
import com.oasys.service.PpeUsedConfirmService;
import com.oasys.service.PpeUsedTaskService;
import com.oasys.service.StatusNameRefService;
import com.oasys.service.workFlow.WorkFlowTaskService;
import com.oasys.serviceImpl.workFlow.WorkFlowBaseServiceImpl;
import com.oasys.util.Collections;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.WorkFlowTasksModel;
@Service("ppeUsedTaskService")
@SuppressWarnings("unchecked")
public class PpeUsedTaskServiceImpl  extends WorkFlowBaseServiceImpl  implements PpeUsedTaskService
{

	
	//申请状态
	@Autowired
	private StatusNameRefService  statusNameRefService;

	@Autowired
	private AuditProcHisService auditProcHisService;
	
	
	@Autowired
	private PublicDao<PpeUsedConfirmApp> ppeUsedDao;
	
	@Autowired
	private WorkFlowTaskService workFlowTaskService;
	@Autowired
	private PpeUsedConfirmService ppeUsedConfirmService; 
	@Autowired
	private PublicDao<PpeUsedConfirmAppAttach> ppeAttDao;
	
	/**
	 * 提交申请
	 */
	@Override
	public String addStartProcessInstance(Integer psaId) {
		try {
			PpeUsedConfirmApp ppeUsedConfirmApp = ppeUsedDao.get(PpeUsedConfirmApp.class, psaId);
			String appNo=ppeUsedConfirmApp.getAppNo();
			Integer total = ppeUsedConfirmService.findPpeUsedConAttTotal(appNo);
			if(total==null || total==0){
				return "请填写要处理的固定资产信息！";
			}
			
			String proDefKey =Constants.PPEUSEDCONFIRM;
			// 定义businessKey
			String businessKey = proDefKey + "." + psaId;
			
			WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
			taskModel.setBusinessID(psaId.toString());//业务ID
			taskModel.setBusinessDefineKey(proDefKey);// 获取启动实例的key
			taskModel.setAppNo(appNo);//编号
			Map<String,Object> var=new HashMap<String, Object>();
			//流程实例添加使用人id，
			var.put(Constants.APPUSER, ppeUsedConfirmApp.getUser());
			
			//借用时，
			if(ppeUsedConfirmApp.getUseNature().equals(Constants.PPEUSEDLY)){
				//字表中的id集合
				List<String> list = ppeUsedConfirmService.findPpeUsedAttPsaIdList(ppeUsedConfirmApp.getAppNo());
				var.put("assigneeList", list);
				//数量
				var.put("count", list.size());
			}
			
			
			taskModel.setVariables(var);
			
			Map<String, Object> resultMap = workFlowTaskService.startProcessInstance(taskModel);
			
			//判断任务未结束
			if(null != resultMap.get(Constants.STATUS_REF_ID) && StringUtils.isNotBlank(resultMap.get(Constants.STATUS_REF_ID).toString())){
				ppeUsedConfirmService.updateAppStatus(Integer.valueOf(taskModel.getBusinessID()),resultMap.get(Constants.STATUS_REF_ID).toString());// 更新申请状态
				
			}
			
			//更新申请时间
			ppeUsedConfirmApp.setAppDate(new Date());
			ppeUsedDao.saveOrUpdate(ppeUsedConfirmApp);
			
			
			return resultMap.get(Constants.RESULT_STR).toString();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}

	/**
	 * 查询待办任务
	 */
	@Override
	public List<PpeUsedConfirmApp> findPpeUsedTaskList(
			PpeUsedConfirmApp ppeUsedConfirmApp, PageUtil pageUtil) {
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			List<PpeUsedConfirmApp> modeList=new ArrayList<PpeUsedConfirmApp>();
			
			WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
			taskModel.setProcessKey(ppeUsedConfirmApp.getDefinitionKey());
			List<WorkFlowTasksModel> workList = workFlowTaskService.findAcceptTaskByGroup(taskModel);
			if(null == workList || workList.size() == 0)return new ArrayList<PpeUsedConfirmApp>();
			
			
			StringBuffer sql = ppeUsedConfirmService.getPpeUsedConSql();
			if(StringUtils.isNotBlank(ppeUsedConfirmApp.getAppNo())){
				sql.append(" AND puc.APP_NO='"+ppeUsedConfirmApp.getAppNo()+"' ");
			}else {
				if(StringUtils.isNotBlank(ppeUsedConfirmApp.getMyId())){
					sql.append(" AND tr2.MYID='"+ppeUsedConfirmApp.getMyId()+"' ");
				}
				if(StringUtils.isNotBlank(ppeUsedConfirmApp.getAppDateS())){
					sql.append( " AND puc.APP_DATE >='" + ppeUsedConfirmApp.getAppDateS()+"' ");  //申请开始日期
				}
				if(StringUtils.isNotBlank(ppeUsedConfirmApp.getAppDateE())){
					sql.append( " AND puc.APP_DATE <='" + ppeUsedConfirmApp.getAppDateE()+"' ");  //申请结束日期
				}
			}
			
			sql.append(" AND PUC.PSA_ID IN("+getTaskPPEids(workList)+") ");
			sql.append(" ORDER BY puc.PSA_ID DESC ");
			
			List<Object> list = ppeUsedDao.findBySql(sql.toString(), pageUtil);
			
			if(Collections.listIsNotEmpty(list)){
				PpeUsedConfirmApp confirmApp=new PpeUsedConfirmApp();
				for (int i = 0; i < list.size(); i++) {
					Object[] obj=(Object[]) list.get(i);
					PpeUsedConfirmApp app=(PpeUsedConfirmApp) confirmApp.clone();
					app.setPsaId(obj[0]==null?0:(Integer)obj[0]);//申请id
					app.setAppNo(obj[1]==null?"":String.valueOf(obj[1]));//申请编号
					app.setApplicantNo(obj[2]==null?0:(Integer)obj[2]);//申请人id
					app.setApplicantName(obj[3]==null?"":String.valueOf(obj[3]));//申请人名字
					app.setAppDept(obj[4]==null?0:(Integer)obj[4]);//申请人部门id
					app.setAppDeptName(obj[5]==null?"":String.valueOf(obj[5]));//申请人部门名字
					app.setAppDate(obj[6]==null?null:sdf.parse(String.valueOf(obj[6])));//申请编号
					app.setAppStatus(obj[7]==null?null:String.valueOf(obj[7]));//申请状态
					app.setUseNature(obj[8]==null?"":String.valueOf(obj[8]));//使用性质
					app.setUsedQty(obj[9]==null?0:(Integer)obj[9]);//使用数量
					app.setUser(obj[10]==null?0:(Integer)obj[10]);//使用人id
					app.setUserName(obj[11]==null?"":String.valueOf(obj[11]));//使用人名字
					app.setUserDept(obj[12]==null?0:(Integer)obj[12]);//使用人部门id
					app.setUserDeptName(obj[13]==null?"":String.valueOf(obj[13]));//使用人部门名字
					app.setRemark(obj[14]==null?"":String.valueOf(obj[14]));//备注信息
					app.setProcStatus(obj[15]==null?"":String.valueOf(obj[15]));//流程状态
					app.setPpeNo(obj[16]==null?"":String.valueOf(obj[16]));//固定资产编号
					app.setPpeName(obj[17]==null?"":String.valueOf(obj[17]));//固定资产名字
					app.setMyId(obj[18]==null?"":String.valueOf(obj[18]));//借款端/财富端
					app.setPuaPsaId(obj[19]==null?0:(Integer)obj[19]);//固定资产使用附加表id
					app.setMyId(obj[20]==null?"":String.valueOf(obj[20]));//判断借款端财富端
					app.setReverter(obj[21]==null?0:(Integer)obj[21]);//归还人id；
					
					//---------------自定义查询任务-----------------
					/*String executionId = this.runtimeService.createExecutionQuery().processInstanceBusinessKey(Constants.PPEUSEDCONFIRM+"."+app.getPsaId()).singleResult().getId();
					Task task = this.taskService.createTaskQuery().executionId(executionId).singleResult();
					if(task==null){
						//开始并行网管
						task = this.taskService.createTaskQuery().taskDescription(Constants.PPEUSEDCONFIRM+"."+app.getPuaPsaId()).singleResult();
					}
					
					if(task!=null){
						app.setTaskID(task.getId());
						app.setAssistant(task.getAssignee());
						app.setFormKey(task.getFormKey());
						
						modeList.add(app);
					}*/
					
					for (WorkFlowTasksModel wl : workList) {
						Object obg = taskService.getVariable(wl.getTaskID().toString(),"${assignee}");
						String auqaId = (obg == null ? "" : obg.toString());
						String buID = StringUtils.isNotBlank(auqaId) ? auqaId : wl.getBusinessID();;
						String uqaId = StringUtils.isNotBlank(auqaId) ?app.getPuaPsaId().toString():app.getPsaId().toString();
						if(buID.equals(uqaId)){
								app.setTaskModel(wl);
								app.setTaskID(wl.getTaskID());
								app.setAssistant(wl.getAssistant());
								app.setFormKey(wl.getFormKey());
								app.setUserName(wl.getUserName());
								modeList.add(app);
						}
					}
					
				}
			}
			
			
			return modeList;
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ArrayList<PpeUsedConfirmApp>();
	}
	
	/**
	 * 查询未办理的任务总数量
	 */
	@Override
	public Long findPpeUsedTaskTotal(PpeUsedConfirmApp ppeUsedConfirmApp) {
		
		try {
			WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
			taskModel.setProcessKey(ppeUsedConfirmApp.getDefinitionKey());
			List<WorkFlowTasksModel> workList = workFlowTaskService.findAcceptTaskByGroup(taskModel);
			if(null == workList || workList.size() == 0)return 0L;
			
			Long total=0L;
			
			StringBuffer sql=new StringBuffer();
			sql.append("SELECT COUNT(*) ");
			sql.append(" FROM  t_oa_ad_ppe_used_confirm_app puc ");
			sql.append("LEFT JOIN t_oa_ad_ppe_used_confirm_app_attach pua ON puc.APP_NO = pua.APP_NO ");
			//申请人呢
			sql.append("LEFT JOIN qqms.t_users u1 ON puc.APPLICANT_NO = u1.USER_ID ");
			sql.append("LEFT JOIN qqms.t_organization tr1 ON puc.APP_DEPT = tr1.ORGANIZATION_ID ");
			//使用人
			sql.append("LEFT JOIN qqms.t_users u2 ON puc.`USER` = u2.USER_ID ");
			sql.append("LEFT JOIN qqms.t_organization tr2 ON puc.USER_DEPT = tr2.ORGANIZATION_ID ");
			sql.append(" WHERE 1=1 AND pua.GIVEBACK_DATE is NULL ");
			sql.append(" AND puc.PSA_ID IN("+getTaskPPEids(workList)+") ");
			
			if(StringUtils.isNotBlank(ppeUsedConfirmApp.getAppNo())){
				sql.append(" AND puc.APP_NO='"+ppeUsedConfirmApp.getAppNo()+"' ");
			}else if(StringUtils.isNotBlank(ppeUsedConfirmApp.getMyId())){
				sql.append(" AND tr1.MYID='"+ppeUsedConfirmApp.getMyId()+"' ");
			}else{
				if(StringUtils.isNotBlank(ppeUsedConfirmApp.getAppDateS())){
					sql.append( " AND puc.APP_DATE >='" + ppeUsedConfirmApp.getAppDateS()+"' ");  //申请开始日期
				}
				if(StringUtils.isNotBlank(ppeUsedConfirmApp.getAppDateE())){
					sql.append( " AND puc.APP_DATE <='" + ppeUsedConfirmApp.getAppDateE()+"' ");  //申请结束日期
				}
			}
			
			total=ppeUsedDao.findTotalCount(sql.toString());
			return total;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0L;
	}
	
	
	
	
	//获取task中工牌申请表的id集合
	private String getTaskPPEids(List<WorkFlowTasksModel> workList){
		String ids = "";
		for (WorkFlowTasksModel workFlowTasksModel : workList) {
			ids+=workFlowTasksModel.getBusinessID()+",";
		}
		if(ids.length()>0){
			ids = ids.substring(0, ids.length()-1);
		}
		return ids;
	}
	
	/**
	 * 个人领取任务
	 */
	@Override
	public void getTaskUserClaim(String taskId) throws ActivitiTaskAlreadyClaimedException{
		//领取人id
		Integer userId=Constants.getCurrendUser().getUserId();
		this.taskService.claim(taskId, String.valueOf(userId));
	}

	/**
	 * 办理任务
	 */
	@Override
	public String addUserTask(WorkFlowTasksModel taskModel, Integer attPsaId,
			Integer reverter) throws Exception {
		try {
			Map<String,Object> resultMap = workFlowTaskService.saveSubmitTask(taskModel);
			if(StringUtils.isNotBlank(resultMap.get(Constants.STATUS_REF_ID).toString())){
				ppeUsedConfirmService.updateAppStatus(Integer.valueOf(taskModel.getBusinessID()),resultMap.get(Constants.STATUS_REF_ID).toString());// 更新流程状态
			}
			
			//判断如果是归还，更改归还信息
			if(reverter!=null && reverter!=0 && attPsaId!=null){
				PpeUsedConfirmAppAttach ppeUsedConfirmAppAttach = ppeAttDao.get(PpeUsedConfirmAppAttach.class, attPsaId);
				ppeUsedConfirmAppAttach.setReverter(reverter);
				ppeUsedConfirmAppAttach.setGivebackDate(new Date());
				ppeAttDao.saveOrUpdate(ppeUsedConfirmAppAttach);
			}
			
			
			return resultMap.get(Constants.RESULT_STR).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	

	
	
	
	
	
	
	
	
}

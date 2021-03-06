package com.oasys.serviceImpl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.ExpyFeeInfo;
import com.oasys.model.FuelFeeInfo;
import com.oasys.model.InsuranceFeeInfo;
import com.oasys.model.MaintFeeInfo;
import com.oasys.model.ParkingFeeInfo;
import com.oasys.model.RepairFeeInfo;
import com.oasys.model.VehicleExpensesApp;
import com.oasys.service.OrganizationService;
import com.oasys.service.VehicleUsesRegService;
import com.oasys.service.workFlow.WorkFlowTaskService;
import com.oasys.util.Collections;
import com.oasys.util.Constants;
import com.oasys.util.DateUtils;
import com.oasys.util.PageUtil;
import com.oasys.util.UniqueIdUtil;
import com.oasys.viewModel.VehicleExpensesAppModel;
import com.oasys.viewModel.WorkFlowTasksModel;

@Service("vehicleUsesRegService")
public class VehicleUsesRegServiceImpl implements VehicleUsesRegService {
	@Autowired
	private PublicDao<VehicleExpensesAppModel> publicDaoVO;
	@Autowired
	private PublicDao<VehicleExpensesApp> publicDao;
	@Autowired
	private PublicDao<RepairFeeInfo> publicWeiXiu;
	@Autowired
	private PublicDao<FuelFeeInfo> publicJiaYou;
	@Autowired
	private PublicDao<InsuranceFeeInfo> publicBaoXian;
	@Autowired
	private PublicDao<MaintFeeInfo> publicBaoYang;
	@Autowired
	private PublicDao<ParkingFeeInfo> publicTingChe;
	@Autowired
	private PublicDao<ExpyFeeInfo> publicGaoSu;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private WorkFlowTaskService workFlowTaskService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private TaskService taskService;
	
	
	@Override
	public List<VehicleExpensesAppModel> getList(Map<String, Object> map,
			PageUtil pageUtil,Integer id,String sql) {
		//默认打开不显示数据，根据下拉框中要实现的费用类别查询
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("SELECT a.VEA_ID '编号',a.APP_NO '申请编号',u.USER_NAME '申请人',o.FULL_NAME '所属部门',a.APP_DATE '申请日期',");//5
		stringBuffer.append("a.APP_STATUS  '申请状态',a.CAR_NO '车牌号码',a.BANK_NAME  '银行名称',a.INTO_ACT '转入账号',a.ACT_NAME  '账户名称',a.FEE_INFO_ID  '费用信息编号',");//11
		stringBuffer.append("a.FEE_TYPE '费用类型',a.FEE_TYPE_OTHER '其他费用类型',a.PROC_STATUS '流程状态',a.REMARK '备注信息',f.FFI_ID '主键ID加油费',");//16
		stringBuffer.append("f.APP_AMT '申请金额加油费',f.BG_MILEAGE '启程公里数加油费',f.ED_MILEAGE '结束公里数加油费',f.PREV_APP_DATE '上次申请费用日期加油费',");//20
		stringBuffer.append("f.CARD_BALANCE '卡内余额加油费',p.PFI_ID '主键ID停车费',p.APP_AMT '申请金额停车费',p.PARKING_DTIME '停车时间，记录停车日期时间停车费',");//24
		stringBuffer.append("p.PARKING_PLACE '停车地点停车费',m.MFI_ID '主键ID保养费',m.APP_AMT '申请金额保养费',m.BG_MILEAGE '启程公里数保养费',m.ED_MILEAGE '结束公里数保养费',");//29
		stringBuffer.append("m.PREV_APP_DATE '上次申请费用时间保养费',i.IFI_ID '主键ID保险费',i.APP_AMT '申请金额保险费',i.BG_MILEAGE '启程公里数保险费',");//33
		stringBuffer.append("i.ED_MILEAGE '结束公里数保险费',i.INSURANCE_BG_DTIME '保险开始时间保险费',i.INSURANCE_ED_DTIME '保险结束时间保险费',i.IC_NAME '保险公司名称保险费',");//37
		stringBuffer.append("r.RFI_ID '主键ID维修费',r.APP_AMT '申请金额维修费',r.REPAIR_ITEM '维修项目维修费',r.REPAIR_RESON '维修原因维修费',e.EFI_ID '主键ID高速路费',");//42
		stringBuffer.append("e.APP_AMT '申请金额高速路费',e.PAYMENT_DATE '缴费日期高速路费',e.TOLLGATE_NAME '收费站名称高速路费',r.NAME '职位名称'");//45
		stringBuffer.append(" FROM t_oa_ad_vehicle_expenses_app a");
		stringBuffer.append(" LEFT JOIN t_oa_ad_maint_fee_info m on a.FEE_INFO_ID = m.MFI_ID");
		stringBuffer.append(" LEFT JOIN t_oa_ad_parking_fee_info p on a.FEE_INFO_ID = p.PFI_ID");
		stringBuffer.append(" LEFT JOIN t_oa_ad_insurance_fee_info i on a.FEE_INFO_ID = i.IFI_ID");
		stringBuffer.append(" LEFT JOIN t_oa_ad_fuel_fee_info f on a.FEE_INFO_ID = f.FFI_ID");
		stringBuffer.append(" LEFT JOIN t_oa_ad_repair_fee_info r on a.FEE_INFO_ID = r.RFI_ID");
		stringBuffer.append(" LEFT JOIN t_oa_ad_expy_fee_info e on a.FEE_INFO_ID = e.EFI_ID");
		stringBuffer.append(" LEFT JOIN qqms.t_users u on a.APPLICANT_NO = u.USER_ID");
		stringBuffer.append(" LEFT JOIN qqms.t_organization o on a.DEPT_NO = o.ORGANIZATION_ID");
		stringBuffer.append(" LEFT JOIN qqms.t_user_and_role ur ON ur.USER_ID = u.USER_ID");
		stringBuffer.append(" LEFT JOIN qqms.t_role r ON r.ROLE_ID  = ur.ROLE_ID");
		if(StringUtils.isNotBlank(map.get("type")+"") && map.get("type")!=null){
			stringBuffer.append(" WHERE a.FEE_TYPE = "+map.get("type"));
		}else {
			stringBuffer.append(" WHERE 1<>1");//默认不展示字段,根据是否选中下拉框进行查询
		}
		if(sql!=null && !"".equals(sql)){
			stringBuffer.append(" AND a.VEA_ID in ("+sql+")");
		}
		if(StringUtils.isNotBlank(map.get("procStatus")+"") && map.get("procStatus")!=null){
			stringBuffer.append(" and a.PROC_STATUS="+map.get("procStatus"));
		}
		if(StringUtils.isNotBlank(map.get("appDateBegin")+"") && map.get("appDateBegin")!=null){
			stringBuffer.append(" AND a.APP_DATE >='"+map.get("appDateBegin")+"'");
		}
		if(StringUtils.isNotBlank(map.get("appDateEnd")+"") && map.get("appDateEnd")!=null){
			stringBuffer.append(" AND a.APP_DATE <='"+map.get("appDateEnd")+"'");
		}
		if(id==1){//查看当前申请人的申请
			stringBuffer.append(" AND a.APPLICANT_NO = "+Constants.getCurrendUser().getUserId());
		}
		stringBuffer.append(" ORDER BY a.APP_DATE DESC,a.VEA_ID DESC");
		List<Object> list = publicDaoVO.findBySql(stringBuffer.toString(),pageUtil);
		List<VehicleExpensesAppModel> listVO = new ArrayList<VehicleExpensesAppModel>();
		for (int i = 0; i < list.size(); i++) {
			Object[] object=(Object[]) list.get(i);
			VehicleExpensesAppModel veam = new VehicleExpensesAppModel();
			veam.setVeaId(Integer.parseInt(object[0]+""));
			veam.setAppNo(object[1]+"");
			veam.setUserName(object[2]+"");
			veam.setDeptName(object[3]+"");
			veam.setAppDate(DateUtils.parse(object[4]+"",DateUtils.DATE_SMALL_STR));
			veam.setAppStatus(object[5]+"");
			veam.setCarNo(object[6]+"");
			veam.setBankName(object[7]+"");
			veam.setIntoAct(object[8]+"");
			veam.setActName(object[9]+"");
			veam.setFeeInfoId(object[10]==null?0:Integer.parseInt(object[10]+""));
			veam.setFeeType((char)object[11]);
			veam.setFeeTypeOther(object[12]+"");
			veam.setProcStatus((char)object[13]);
			veam.setRemark(object[14]==null?"":object[14]+"");
			veam.setFfiId(object[15]==null?null:Integer.parseInt(object[15]+""));
			veam.setAppAmtJiaYou(object[16]==null?null:new BigDecimal(object[16]+""));
			veam.setBgMileageJiaYou(object[17]==null?null:new BigDecimal(object[17]+""));
			veam.setEdMileageJiaYou(object[18]==null?null:new BigDecimal(object[18]+""));
			veam.setPrevAppDate(object[19]==null?null:DateUtils.parse(object[19]+"",DateUtils.DATE_SMALL_STR));
			veam.setCardBalance(object[20]==null?null:new BigDecimal(object[20]+""));
			veam.setPfiId(object[21]==null?null:Integer.parseInt(object[21]+""));
			veam.setAppAmtTingChe(object[22]==null?null:new BigDecimal(object[22]+""));
			veam.setParkingDtime(object[23]==null?null:DateUtils.parse(object[23]+"",DateUtils.DATE_SMALL_STR));
			veam.setParkingPlace(object[24]+"");
			veam.setMfiId(object[25]==null?null:Integer.parseInt(object[25]+""));
			veam.setAppAmtBaoYang(object[26]==null?null:new BigDecimal(object[26]+""));
			veam.setBgMileageBaoYang(object[27]==null?null:new BigDecimal(object[27]+""));
			veam.setEdMileageBaoYang(object[28]==null?null:new BigDecimal(object[28]+""));
			veam.setPrevAppDateBaoYang(object[29]==null?null:DateUtils.parse(object[29]+"",DateUtils.DATE_SMALL_STR));
			veam.setIfiId(object[30]==null?null:Integer.parseInt(object[30]+""));
			veam.setAppAmtBaoXian(object[31]==null?null:new BigDecimal(object[31]+""));
			veam.setBgMileageBaoXian(object[32]==null?null:new BigDecimal(object[32]+""));
			veam.setEdMileageBaoXian(object[33]==null?null:new BigDecimal(object[33]+""));
			veam.setInsuranceBgDtime(object[34]==null?null:DateUtils.parse(object[34]+"",DateUtils.DATE_SMALL_STR));
			veam.setInsuranceEdDtime(object[35]==null?null:DateUtils.parse(object[35]+"",DateUtils.DATE_SMALL_STR));
			veam.setIcName(object[36]+"");
			veam.setRfiId(object[37]==null?null:Integer.parseInt(object[37]+""));
			veam.setAppAmtWeiXiu(object[38]==null?null:new BigDecimal(object[38]+""));
			veam.setRepairItem(object[39]+"");
			veam.setRepairReson(object[40]+"");
			veam.setEfiId(object[41]==null?null:Integer.parseInt(object[41]+""));
			veam.setAppAmtGaoSu(object[42]==null?null:new BigDecimal(object[42]+""));
			veam.setPaymentDate(object[43]==null?null:DateUtils.parse(object[43]+"",DateUtils.DATE_SMALL_STR));
			veam.setTollgateName(object[44]+"");
			veam.setPosition(object[45]+"");
			veam.setDefinitionKey(Constants.VEHICLEEXPENSES_JK);
			veam.setResourcesName("OA_AD_VehicleExpenses");
			listVO.add(veam);
		}
		return listVO;
	}

	//统计总条数
	@Override
	public Long getCount(Map<String, Object> map) {
		StringBuffer stringBuffer = new StringBuffer("SELECT COUNT(1)");
		stringBuffer.append(" FROM t_oa_ad_vehicle_expenses_app a");
		stringBuffer.append(" LEFT JOIN t_oa_ad_maint_fee_info m on a.FEE_INFO_ID = m.MFI_ID");
		stringBuffer.append(" LEFT JOIN t_oa_ad_parking_fee_info p on a.FEE_INFO_ID = p.PFI_ID");
		stringBuffer.append(" LEFT JOIN t_oa_ad_insurance_fee_info i on a.FEE_INFO_ID = i.IFI_ID");
		stringBuffer.append(" LEFT JOIN t_oa_ad_fuel_fee_info f on a.FEE_INFO_ID = f.FFI_ID");
		stringBuffer.append(" LEFT JOIN t_oa_ad_repair_fee_info r on a.FEE_INFO_ID = r.RFI_ID");
		stringBuffer.append(" LEFT JOIN t_oa_ad_expy_fee_info e on a.FEE_INFO_ID = e.EFI_ID");
		stringBuffer.append(" LEFT JOIN qqms.t_users u on a.APPLICANT_NO = u.USER_ID");
		stringBuffer.append(" LEFT JOIN qqms.t_organization o on a.DEPT_NO = o.ORGANIZATION_ID");
		if(StringUtils.isNotBlank(map.get("type")+"") && map.get("type")!=null){
			stringBuffer.append(" WHERE a.FEE_TYPE = "+map.get("type"));
		}else {
			stringBuffer.append(" WHERE 1!=1");//默认不展示字段,根据是否选中下拉框进行查询
		}
		if(StringUtils.isNotBlank(map.get("procStatus")+"") && map.get("procStatus")!=null){
			stringBuffer.append(" and a.PROC_STATUS="+map.get("procStatus"));
		}
		if(StringUtils.isNotBlank(map.get("appDateBegin")+"") && map.get("appDateBegin")!=null){
			stringBuffer.append(" AND a.APP_DATE >="+map.get("appDateBegin"));
		}
		if(StringUtils.isNotBlank(map.get("appDateEnd")+"") && map.get("appDateEnd")!=null){
			stringBuffer.append(" AND a.APP_DATE <="+map.get("appDateEnd"));
		}
		stringBuffer.append(" AND a.APPLICANT_NO = "+Constants.getCurrendUser().getUserId());
		Long count=publicDao.findTotalCount(stringBuffer.toString());
		return count;
	}
	@Override
	public String addVehicleExpenses(VehicleExpensesAppModel veam) {
		boolean flag = false;
		String appNo = null;
		//主表
		VehicleExpensesApp vea = new VehicleExpensesApp();
		if(veam.getVeaId()!=null && !veam.getVeaId().equals("")){
			vea.setVeaId(veam.getVeaId());
		}
		vea.setActName(veam.getActName());
		vea.setAppDate(DateUtils.parse(DateUtils.getNowTime()));
		vea.setApplicantNo(Constants.getCurrendUser().getUserId());
		vea.setAppNo(UniqueIdUtil.generate("CF"));
		vea.setBankName(veam.getBankName());
		vea.setCarNo(veam.getCarNo());
		vea.setDeptNo(organizationService.findOrganizationByUserId(vea.getApplicantNo()).getOrganizationId());
		vea.setFeeType(veam.getFeeType());
		vea.setFeeTypeOther(veam.getFeeTypeOther());
		vea.setIntoAct(veam.getIntoAct());
		vea.setProcStatus((char) '1');
		vea.setRemark(veam.getRemark());
		if(Integer.parseInt(veam.getFeeType()+"")==1){
			FuelFeeInfo ffi = new FuelFeeInfo();
			if(veam.getFfiId()!=null && !veam.getFfiId().equals("")){
				ffi.setFfiId(veam.getFfiId());
			}
			ffi.setAppAmt(veam.getAppAmtJiaYou());
			ffi.setBgMileage(veam.getBgMileageJiaYou());
			ffi.setEdMileage(veam.getEdMileageJiaYou());
			ffi.setCardBalance(veam.getCardBalance());
			ffi.setPrevAppDate(veam.getPrevAppDate());//上一次申请时间
//			ffi.setRemark();//
			publicJiaYou.saveOrUpdate(ffi);
			vea.setFeeInfoId(ffi.getFfiId());
			flag = true;
		}
		if(Integer.parseInt(veam.getFeeType()+"")==2){
			ParkingFeeInfo pfi = new ParkingFeeInfo();
			if(veam.getPfiId()!=null && !veam.getPfiId().equals("")){
				pfi.setPfiId(veam.getPfiId());
			}
			pfi.setAppAmt(veam.getAppAmtTingChe());
			pfi.setParkingDtime(veam.getParkingDtime());
			pfi.setParkingPlace(veam.getParkingPlace());
			publicTingChe.saveOrUpdate(pfi);
			vea.setFeeInfoId(pfi.getPfiId());
			flag = true;
		}
		if(Integer.parseInt(veam.getFeeType()+"")==3){
			ExpyFeeInfo efi = new ExpyFeeInfo();
			if(veam.getEfiId()!=null && !veam.getEfiId().equals("")){
				efi.setEfiId(veam.getEfiId());
			}
			efi.setAppAmt(veam.getAppAmtGaoSu());
			efi.setPaymentDate(veam.getPaymentDate());
			efi.setTollgateName(veam.getTollgateName());
			publicGaoSu.saveOrUpdate(efi);
			vea.setFeeInfoId(efi.getEfiId());
			flag = true;
		}
		if(Integer.parseInt(veam.getFeeType()+"")==4){
			RepairFeeInfo rfi = new RepairFeeInfo();
			if(veam.getRfiId()!=null && !veam.getRfiId().equals("")){
				rfi.setRfiId(veam.getRfiId());
			}
			rfi.setAppAmt(veam.getAppAmtWeiXiu());
			rfi.setRepairItem(veam.getRepairItem());
			rfi.setRepairReson(veam.getRepairReson());
			publicWeiXiu.saveOrUpdate(rfi);
			vea.setFeeInfoId(rfi.getRfiId());
			flag = true;
		}
		if(Integer.parseInt(veam.getFeeType()+"")==5){
			MaintFeeInfo mfi = new MaintFeeInfo();
			if(veam.getMfiId()!=null && !veam.getMfiId().equals("")){
				mfi.setMfiId(veam.getMfiId());
			}
			mfi.setAppAmt(veam.getAppAmtBaoYang());
			mfi.setBgMileage(veam.getBgMileageBaoYang());
			mfi.setEdMileage(veam.getEdMileageBaoYang());
			mfi.setPrevAppDate(veam.getPrevAppDateBaoYang());//上次申请费用时间
			publicBaoYang.saveOrUpdate(mfi);
			vea.setFeeInfoId(mfi.getMfiId());
			flag = true;
		}
		if(Integer.parseInt(veam.getFeeType()+"")==6){
			InsuranceFeeInfo ifi = new InsuranceFeeInfo();
			if(veam.getIfiId()!=null && !veam.getIfiId().equals("")){
				ifi.setIfiId(veam.getIfiId());
			}
			ifi.setAppAmt(veam.getAppAmtBaoXian());
			ifi.setBgMileage(veam.getBgMileageBaoXian());
			ifi.setEdMileage(veam.getEdMileageBaoXian());
			ifi.setIcName(veam.getIcName());
			ifi.setInsuranceBgDtime(veam.getInsuranceBgDtime());
			ifi.setInsuranceEdDtime(veam.getInsuranceEdDtime());
			publicBaoXian.saveOrUpdate(ifi);
			vea.setFeeInfoId(ifi.getIfiId());
			flag = true;
		}
		if(Integer.parseInt(veam.getFeeType()+"")==7){
			vea.setFeeTypeOther(veam.getFeeTypeOther());
		}
		publicDao.saveOrUpdate(vea);
		if(flag){
			appNo = vea.getAppNo();
		}
		return appNo;
	}
	@Override
	public boolean delVehicleExpenses(Integer id) {
		boolean flag = false;
		try {
			VehicleExpensesApp o = publicDao.get(VehicleExpensesApp.class, id);
			publicDao.delete(o);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public String submitVehicleExpensesApply(Integer id) {
		VehicleExpensesApp vea = publicDao.get(VehicleExpensesApp.class, id);
		String proDefKey= Constants.VEHICLEEXPENSES_JK;//借款端车辆费用申请
		
		WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
		taskModel.setBusinessID(id.toString());//业务ID
		taskModel.setBusinessDefineKey(proDefKey);// 获取启动实例的key
		taskModel.setApplyStr(Constants.APPLY_FOR_ADJUSTMENT);//调整申请节点的标识
		taskModel.setAppNo(vea.getAppNo());
		taskModel.setSubFormKey("jsp/ad/carRegister/saveTask/default.jsp");
		Map<String, Object> variables = new HashMap<String, Object>();
		if(Integer.parseInt(vea.getFeeType()+"")==1){
			FuelFeeInfo ffi = publicJiaYou.get(FuelFeeInfo.class, vea.getFeeInfoId());
			variables.put("money", ffi.getAppAmt());//设置流程变量  金额(用于判断500元以上或者以下)
		}
		if(Integer.parseInt(vea.getFeeType()+"")==2){
			ParkingFeeInfo pfi = publicTingChe.get(ParkingFeeInfo.class, vea.getFeeInfoId());
			variables.put("money", pfi.getAppAmt());//设置流程变量  金额(用于判断500元以上或者以下)
		}
		if(Integer.parseInt(vea.getFeeType()+"")==3){
			ExpyFeeInfo efi = publicGaoSu.get(ExpyFeeInfo.class, vea.getFeeInfoId());
			variables.put("money", efi.getAppAmt());//设置流程变量  金额(用于判断500元以上或者以下)
		}
		if(Integer.parseInt(vea.getFeeType()+"")==4){
			RepairFeeInfo rfi = publicWeiXiu.get(RepairFeeInfo.class, vea.getFeeInfoId());
			variables.put("money", rfi.getAppAmt());//设置流程变量  金额(用于判断500元以上或者以下)
		}
		if(Integer.parseInt(vea.getFeeType()+"")==5){
			MaintFeeInfo mfi = publicBaoYang.get(MaintFeeInfo.class, vea.getFeeInfoId());
			variables.put("money", mfi.getAppAmt());//设置流程变量  金额(用于判断500元以上或者以下)
		}
		if(Integer.parseInt(vea.getFeeType()+"")==6){
			InsuranceFeeInfo ifi = publicBaoXian.get(InsuranceFeeInfo.class, vea.getFeeInfoId());
			variables.put("money", ifi.getAppAmt());//设置流程变量  金额(用于判断500元以上或者以下)
		}
		taskModel.setVariables(variables);
		Map<String,Object> resultMap = workFlowTaskService.startProcessInstance(taskModel);
		if(null != resultMap.get(Constants.STATUS_REF_ID) && org.apache.commons.lang3.StringUtils.isNotBlank(resultMap.get(Constants.STATUS_REF_ID).toString())){
			this.updateStatus(Integer.valueOf(taskModel.getBusinessID()),resultMap.get(Constants.STATUS_REF_ID).toString());// 更新流程状态
		}
		return resultMap.get(Constants.RESULT_STR).toString();
	}
	@Override
	public void updateStatus(Integer id, String status) {
		publicDao.executeHql("update VehicleExpensesApp set appStatus="+status+" where veaId="+id+"");
	}
	
	@Override
	public void updateProcStatus(Integer id, String status) {
//		VehicleExpensesApp vehicleExpensesApp = publicDao.get(VehicleExpensesApp.class, id);
//		vehicleExpensesApp.setProcStatus((char)status);
//		publicDao.saveOrUpdate(vehicleExpensesApp);
		publicDao.executeHql("update VehicleExpensesApp set procStatus="+status+" where veaId="+id+"");
	}
	
	@Override
	public void getDiagramResourceByCaId(HttpServletResponse response,
			Integer veaId) {
		// 图片的文件的流
		InputStream in = null;
		try {
			String proDefKey = "VehicleExpenses";
			String imgName = "";
			imgName="OA_AD_VehicleExpenses";
			String businessKey = proDefKey + "." + veaId;
			// 获取当前执行的流程实例
			ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(businessKey).singleResult();
			if(pi!=null){
				// 获取流程定义的实体对象（对应.bpmn文件中的数据）
				ProcessDefinitionEntity pd = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(pi.getProcessDefinitionId());
				// 获取当前执行任务流程
				List<Task> tasks = this.taskService.createTaskQuery().processInstanceBusinessKey(businessKey).list();
				// 获取图片的流程图片名称
				String resourceName = imgName + ".png";
				// 获取图片的文件的流
				in = this.repositoryService.getResourceAsStream(pd.getDeploymentId(),resourceName);
				// 获取图片对象
				BufferedImage bimg;
				bimg = ImageIO.read(in);
				// 得到Graphics2D 对象
				Graphics2D g2d = (Graphics2D) bimg.getGraphics();
				// 设置颜色和画笔粗细
				g2d.setColor(Color.RED);
				g2d.setStroke(new BasicStroke(3));
				// 遍历执行的任务,画图
				for (Task task : tasks) {
					// 根据任务的获取当前执行对象的id,根据执行对象的id获取执行对象的信息
					Execution execution = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
					// 根据当前的执行对象的id获取正在执行的活动信息
					ActivityImpl activityImpl = pd.findActivity(execution.getActivityId());
					// 绘制矩形
					Rectangle2D rectangle = new Rectangle2D.Float(
							activityImpl.getX(), activityImpl.getY(),
							activityImpl.getWidth(), activityImpl.getHeight());
						g2d.draw(rectangle);
					}
					// 写入response输出流中
					ImageIO.write(bimg, "png", response.getOutputStream());
				}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	@Override
	public List<VehicleExpensesAppModel> getTaskByGroup(int firstResult,
			int maxResults, WorkFlowTasksModel workFlowTasksModel1,Map<String, Object> map) {
		//登录角色所要办理的的任务集合
		List<WorkFlowTasksModel> taskModelList = workFlowTaskService.findAcceptTaskByGroup(workFlowTasksModel1);
		List<VehicleExpensesAppModel> purchaseAppModelList = new ArrayList<VehicleExpensesAppModel>();//返回的结果集
		String ids = "";
		if (Collections.listIsNotEmpty(taskModelList)) {
			for (WorkFlowTasksModel workFlowTasksModel : taskModelList) {
				ids += workFlowTasksModel.getBusinessID()+",";
			}
			//id字符串
			ids = ids.substring(0, ids.length()-1);
//			//根据id字符串查出的集合
			List<VehicleExpensesAppModel> pamList = getList(map, new PageUtil(1, 1000000),0,getTaskEMPids(taskModelList));
			for (WorkFlowTasksModel wl : taskModelList) {
				for (VehicleExpensesAppModel purchaseAppModel : pamList) {
					if(Integer.valueOf(wl.getBusinessID()) == purchaseAppModel.getVeaId()){
						purchaseAppModel.setTaskModel(wl);
						purchaseAppModel.setTaskId(wl.getTaskID());
						purchaseAppModel.setAssistant(wl.getAssistant());
						purchaseAppModel.setFormKey(wl.getFormKey());
						purchaseAppModelList.add(purchaseAppModel);
					}
				}
			}
		}
		return purchaseAppModelList;
	}

	private String getTaskEMPids(List<WorkFlowTasksModel> workList){
		String ids = "";
		for (WorkFlowTasksModel workFlowTasksModel : workList) {
			ids+=workFlowTasksModel.getBusinessID()+",";
		}
		if(ids.length()>0){
			ids = ids.substring(0, ids.length()-1);
		}
		return ids;
	}
	
	@Override
	public String saveSubmitTask(WorkFlowTasksModel taskModel) {
		String resultStr = "";
		if(StringUtils.isNotBlank(taskModel.getTaskID())){
			Map<String,Object> resultMap = workFlowTaskService.saveSubmitTask(taskModel);//调用通用受理任务方法
			if(null != resultMap.get(Constants.STATUS_REF_ID)){
				updateStatus(Integer.valueOf(taskModel.getBusinessID()),resultMap.get(Constants.STATUS_REF_ID).toString());// 更新线的状态
			}
			resultStr = resultMap.get(Constants.RESULT_STR).toString();
		}
		return resultStr;
	}
	@Override
	public String saveSubmitTaskBatch(WorkFlowTasksModel taskModel) {
		List<Map<String,Object>> resultMapList = workFlowTaskService.saveSubmitTaskBatch(taskModel);//调用通用受理任务方法
		String resultStr = "";
		if(Collections.listIsNotEmpty(resultMapList)){
			for (Map<String, Object> map : resultMapList) {
				if(map.containsKey(Constants.STATUS_REF_ID) && map.containsKey(Constants.BUSINESS_ID)){
					updateStatus(Integer.valueOf(map.get(Constants.BUSINESS_ID).toString()),map.get(Constants.STATUS_REF_ID).toString());// 更新流程状态
					resultStr = map.get(Constants.RESULT_STR).toString();
				}
			}
		}
		return resultStr;
	}
	@Override
	public Long getTaskCountByGroup(WorkFlowTasksModel workFlowTasksModel1,
			Map<String, Object> map) {
		Integer count = 0;
		//登录角色所要办理的的任务集合
		List<WorkFlowTasksModel> taskModelList = workFlowTaskService.findAcceptTaskByGroup(workFlowTasksModel1);
		String ids = "";
		if (Collections.listIsNotEmpty(taskModelList)) {
			for (WorkFlowTasksModel workFlowTasksModel : taskModelList) {
				ids += workFlowTasksModel.getBusinessID()+",";
			}
			//id字符串
			ids = ids.substring(0, ids.length()-1);
//					//根据id字符串查出的集合
			List<VehicleExpensesAppModel> pamList = getList(map, new PageUtil(1, 1000000),0,getTaskEMPids(taskModelList));
			for (WorkFlowTasksModel wl : taskModelList) {
				for (VehicleExpensesAppModel purchaseAppModel : pamList) {
					if(Integer.valueOf(wl.getBusinessID()) == purchaseAppModel.getVeaId()){
						count++;
					}
				}
			}
		}
		return Long.parseLong(count+"");
	}
}

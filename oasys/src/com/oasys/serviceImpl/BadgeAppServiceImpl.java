package com.oasys.serviceImpl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.BadgeApp;
import com.oasys.model.BadgeAppAttach;
import com.oasys.model.Organization;
import com.oasys.model.Role;
import com.oasys.model.Users;
import com.oasys.service.BadgeAppService;
import com.oasys.service.OrganizationService;
import com.oasys.service.StatusNameRefService;
import com.oasys.service.UserService;
import com.oasys.util.Collections;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;
import com.oasys.util.UniqueIdUtil;
import com.oasys.viewModel.BadgeAppModel;
import com.oasys.viewModel.ComboBoxModel;
@Service("badgeAppService")
@SuppressWarnings("unchecked")
public class BadgeAppServiceImpl    implements BadgeAppService
{
	@Autowired
	private PublicDao<BadgeApp> publicDao;
	
	@Autowired
	private PublicDao<Users> publicUserDao;
	@Autowired
	private PublicDao<Organization> publicOrganDao;
	
	@Autowired
	private UserService userService;
	//申请人
	@Autowired
	private PublicDao<BadgeAppAttach> publicAttDao;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private StatusNameRefService statusNameRefService;
	
	
	

	@Override
	public BadgeApp findBadgeAppNo(String appNo) {
		String hql="from BadgeApp where appNo='"+appNo+"'";
		List<BadgeApp> list = publicDao.find(hql);
		if(list !=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public BadgeApp saveBadgeAttach(BadgeAppAttach badgeAppAtt) {
		try {
			BadgeApp badgeApp=new BadgeApp();
			Integer user=Constants.getCurrendUser().getUserId();
			if(badgeAppAtt.getAppNo()==""){
				//保存主表信息
				//编码
				
				String  appNo=UniqueIdUtil.generate("GP");
				
				
				badgeApp.setAppNo(appNo);
				//登记人
				badgeApp.setRegistrantNo(user);
				//登记日期
				badgeApp.setRegDatetime(new Date());
				//流程状态
				badgeApp.setProcStatus("1");
				//保存备注信息
				badgeApp.setRemark(badgeAppAtt.getRemark());
				//保存主表
				publicDao.save(badgeApp);
				
				//保存附加表
				String userIds=badgeAppAtt.getName();
				String[] userList = userIds.split(",");
				Integer deptNo = userService.findOrgByuserId(Integer.parseInt(userList[0])).getOrganizationId();
				
				for (String userId : userList) {
					BadgeAppAttach badgeAppAttach=new BadgeAppAttach();
					//部门编号，判断当前用户是否是超级管理元
					if(user!=1){
						badgeAppAttach.setDeptNo(deptNo);
					}else{
						badgeAppAttach.setDeptNo(userService.getUserByID(Integer.parseInt(userId)).getOrganizeId());
					}
					//申请编号
					badgeAppAttach.setAppNo(appNo);
					//设置申请人id
					badgeAppAttach.setApplicantNo(Integer.parseInt(userId));
					//获取英文名
					Users users = userService.getUserByID(Integer.parseInt(userId));
					badgeAppAttach.setNamePinyin(users.getEname());
					//申请人职位信息
					Role role = userService.findRoleListByUserId(Integer.parseInt(userId)).get(0);
					if(role!=null){
						badgeAppAttach.setPosition(role.getRoleCode());
					}
					//保存附加表
					publicAttDao.save(badgeAppAttach);
				}
			}else{
				String userIds=badgeAppAtt.getName();
				
				if(StringUtils.isNotBlank(userIds)){
					String[] userList = userIds.split(",");
					//获取部门id
					Integer deptNo = userService.findOrgByuserId(Integer.parseInt(userList[0])).getOrganizationId();
					
					for (String userId : userList) {
						BadgeAppAttach badgeAppAttach=new BadgeAppAttach();
						//部门编号，判断当前用户是否是超级管理元
						if(user!=1){
							badgeAppAttach.setDeptNo(deptNo);
						}else{
							badgeAppAttach.setDeptNo(userService.getUserByID(Integer.parseInt(userId)).getOrganizeId());
						}
						//申请编号
						badgeAppAttach.setAppNo(badgeAppAtt.getAppNo());
						//设置申请人id
						badgeAppAttach.setApplicantNo(Integer.parseInt(userId));
						//获取英文名
						Users users = userService.getUserByID(Integer.parseInt(userId));
						badgeAppAttach.setNamePinyin(users.getEname());
						//申请人职位信息
						Role role = userService.findRoleListByUserId(Integer.parseInt(userId)).get(0);
						badgeAppAttach.setPosition(role.getRoleCode());
						//保存附加表
						publicAttDao.save(badgeAppAttach);
					}
				}
				//当备注信息改变时，保存备注信息
				badgeApp = this.findBadgeAppNo(badgeAppAtt.getAppNo());
				badgeApp.setRemark(badgeAppAtt.getRemark());
				publicDao.saveOrUpdate(badgeApp);
				
			}
			
			return badgeApp;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<BadgeAppAttach> findBadgeAttList(String appNo,PageUtil pageUtil) {
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("baa.PNR_ID '申请附加表id',");
			sql.append("baa.APP_NO '申请编号',");
			sql.append("baa.APPLICANT_NO '申请人id',");
			sql.append("baa.NAME_PINYIN '申请人拼音',");
			sql.append("baa.POSITION '职位编码',");
			sql.append("baa.DEPT_NO '部门id' ");
			sql.append(" FROM t_oa_ad_badge_app_attach baa WHERE 1=1 ");
			sql.append(" AND baa.APP_NO = '"+appNo+"' ");
			sql.append("  ORDER BY baa.DEPT_NO ");
			List<Object> list = publicAttDao.findBySql(sql.toString(), pageUtil);
			List<BadgeAppAttach> attList=new ArrayList<BadgeAppAttach>();
			if(Collections.listIsNotEmpty(list)){
				BadgeAppAttach appAttach=new BadgeAppAttach();
				for (int i = 0; i < list.size(); i++) {
					BadgeAppAttach cmodel=(BadgeAppAttach) appAttach.clone();
					Object[] obj = (Object[]) list.get(i);
					cmodel.setPnrId(obj[0] == null?0:(Integer)obj[0]);
					cmodel.setAppNo(obj[1]==null?"":String.valueOf(obj[1]));
					cmodel.setApplicantNo(obj[2]==null?0:(Integer)obj[2]);
					cmodel.setNamePinyin(obj[3]==null?"":String.valueOf(obj[3]));
					cmodel.setPosition(obj[4]==null?"":String.valueOf(obj[4]));
					cmodel.setDeptNo(obj[5]==null?null:(Integer)obj[5]);
					attList.add(cmodel);
				}
			}
			return attList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<BadgeAppAttach>();
	}

	@Override
	public Long findbadgeAtttotal(String appNo) {
		try {
			String sql="SELECT COUNT(*) FROM t_oa_ad_badge_app_attach  baa where baa.APP_NO='"+appNo+"'  ";
			sql+=" ORDER BY baa.APP_NO ";
			Long totalCount = publicAttDao.findTotalCount(sql);
			return totalCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}
	
	
	@Override
	public boolean deleteBadgeAttList(String ids) {
		try {
			publicDao.executeHql("delete from BadgeAppAttach where id in ("+ids+")");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
	@Override
	public List<BadgeAppModel> getBadgeApp(PageUtil pageUtil,BadgeApp badgeApp) {
		List<BadgeAppModel> modelList=new ArrayList<BadgeAppModel>();
				try {
					StringBuffer sql = new StringBuffer();
					SimpleDateFormat djr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Integer userId=Constants.getCurrendUser().getUserId();//当前登录人id
					
					
					
					sql.append("SELECT ");
					sql.append("ba.PNR_ID '申请id',");
					sql.append("ba.APP_NO '申请编号',");
					sql.append("rg.FULL_NAME '部门',");
					sql.append("u.USER_NAME '姓名',");
					sql.append("u.USER_ENAME '英文名',");
					sql.append("r.`NAME` '职位',");
					sql.append("ba.REMARK '备注',");
					sql.append("ba.APP_STATUS '申请状态',");
					sql.append("ba.PROC_STATUS '流程状态',");
					sql.append("ba.REG_DATETIME '登记日期',");
					sql.append("ba.APP_DATE '申请日期',");
					sql.append("baa.APPLICANT_NO '申请人id', ");
					sql.append("ba.REGISTRANT_NO '登记人id', ");
					sql.append(" baa.DEPT_NO '部门id', ");
					sql.append(" rg.MYID '区分财富端借款端', ");
					sql.append(" ur.USER_NAME '登记人姓名' ");
					sql.append(" FROM t_oa_ad_badge_app ba ");
					sql.append("LEFT JOIN t_oa_ad_badge_app_attach baa ON ba.APP_NO = baa.APP_NO ");
					sql.append(" LEFT JOIN qqms.t_users ur ON ba.REGISTRANT_NO = ur.USER_ID ");
					sql.append("LEFT JOIN qqms.t_organization  tr ON ur.ORGANIZATION_ID= tr.ORGANIZATION_ID ");
					sql.append("LEFT JOIN qqms.t_users u ON baa.APPLICANT_NO = u.USER_ID ");
					sql.append("LEFT JOIN qqms.t_organization  rg ON baa.DEPT_NO = rg.ORGANIZATION_ID ");
					sql.append("LEFT JOIN qqms.t_role r ON baa.POSITION = r.ROLE_CODE WHERE 1=1 ");
					if(userId!=1){
						
						sql.append(" AND ba.REGISTRANT_NO = "+Constants.getCurrendUser().getUserId());
					}
					
					if(StringUtils.isNotBlank(badgeApp.getAppNo())){
						sql.append(" AND ba.APP_NO='"+badgeApp.getAppNo()+"' ");
					}else {
						if(StringUtils.isNotBlank(badgeApp.getMyId())){
							sql.append(" AND tr.MYID='"+badgeApp.getMyId()+"' ");
						}
						if(StringUtils.isNotBlank(badgeApp.getAppDateS())){
							sql.append( " AND ba.APP_DATE >='" + badgeApp.getAppDateS()+"' ");  //申请开始日期
						}
						if(StringUtils.isNotBlank(badgeApp.getAppDateE())){
							sql.append( " AND ba.APP_DATE <='" + badgeApp.getAppDateE()+"' ");  //申请结束日期
						}
						if(StringUtils.isNotBlank(badgeApp.getProcStatus())){
							sql.append(" AND ba.PROC_STATUS='"+badgeApp.getProcStatus()+"' ");
						}
						
					}
					sql.append(" ORDER BY ba.PNR_ID DESC,baa.DEPT_NO DESC ");
					
					List<Object> badgeList =publicDao.findBySql(sql.toString(), pageUtil);
					
					if(Collections.listIsNotEmpty(badgeList)){
						BadgeAppModel badgeAppModel=new BadgeAppModel();
						for (int i = 0; i < badgeList.size(); i++) {
							BadgeAppModel cmodel=(BadgeAppModel) badgeAppModel.clone();
							Object[] obj = (Object[]) badgeList.get(i);
							cmodel.setPnrId(obj[0] == null?0:(Integer)obj[0]);
							cmodel.setAppNo(obj[1]==null?"":String.valueOf(obj[1]));
							cmodel.setFullName(obj[2]==null?"":String.valueOf(obj[2]));
							cmodel.setName(obj[3]==null?"":String.valueOf(obj[3]));
							cmodel.setNamePinyin(obj[4]==null?"":String.valueOf(obj[4]));
							cmodel.setPositionName(obj[5]==null?"":String.valueOf(obj[5]));
							cmodel.setRemark(obj[6]==null?"":String.valueOf(obj[6]));
							cmodel.setAppStatus(obj[7]==null?"":String.valueOf(obj[7]));
							cmodel.setProcStatus(obj[8]==null?"":String.valueOf(obj[8]));
							cmodel.setRegDatetime(obj[9]==null?null:djr.parse(String.valueOf(obj[9])));
							cmodel.setAppDate(obj[10]==null?null:String.valueOf(obj[10]));
							cmodel.setApplicantNo(obj[11] == null?0:(Integer)obj[11]);
							cmodel.setRegistrantNo(obj[12] == null?0:(Integer)obj[12]);
							cmodel.setDeptNo(obj[13] == null?0:(Integer)obj[13]);
							cmodel.setMyId(obj[14]==null?"":String.valueOf(obj[14]));
							cmodel.setRegistrantNanme(obj[15]==null?"":String.valueOf(obj[15]));//登记人姓名
							modelList.add(cmodel);
						}
					}
			} catch (ParseException e) {
				e.printStackTrace();
				return new ArrayList<BadgeAppModel>();
			}
		return modelList;
	}

	@Override
	public Long findtotal(BadgeApp badgeApp) {
		try {
			StringBuffer sql=new StringBuffer();
			sql.append("SELECT COUNT(*) FROM t_oa_ad_badge_app ba LEFT JOIN t_oa_ad_badge_app_attach baa ON ba.APP_NO = baa.APP_NO  ");
			sql.append(" LEFT JOIN qqms.t_users ur ON ba.REGISTRANT_NO = ur.USER_ID ");
			sql.append("LEFT JOIN qqms.t_organization  tr ON ur.ORGANIZATION_ID= tr.ORGANIZATION_ID WHERE 1=1 ");
			if(Constants.getCurrendUser().getUserId()!=1){
				sql.append(" AND ba.REGISTRANT_NO = "+Constants.getCurrendUser().getUserId());
			}
			
			if(StringUtils.isNotBlank(badgeApp.getAppNo())){
				sql.append(" AND ba.APP_NO='"+badgeApp.getAppNo()+"' ");
			}else if(StringUtils.isNotBlank(badgeApp.getMyId())){
				sql.append(" AND tr.MYID='"+badgeApp.getMyId()+"' ");
			}else{
				if(StringUtils.isNotBlank(badgeApp.getAppDateS())){
					sql.append(" AND ba.APP_DATE >='" + badgeApp.getAppDateS()+"' ");  //申请开始日期
				}
				if(StringUtils.isNotBlank(badgeApp.getAppDateE())){
					sql.append( " AND ba.APP_DATE <='" + badgeApp.getAppDateE()+"' ");  //申请结束日期
				}
				if(StringUtils.isNotBlank(badgeApp.getProcStatus())){
					sql.append(" AND  ba.PROC_STATUS='"+badgeApp.getProcStatus()+"' ");
				}
				
			}
			Long totalCount = publicDao.findTotalCount(sql.toString());
			return totalCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}

	@Override
	public boolean deleteBadgeApp(String appNo) {
		try {
			publicDao.executeHql("delete from BadgeApp where appNo = '" + appNo+"'");
			publicAttDao.executeHql("delete from BadgeAppAttach where appNo = '" + appNo+"'");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void upBadgeProcStatus(Integer id, String c) {
		BadgeApp badgeApp = publicDao.get(BadgeApp.class, id);
		badgeApp.setProcStatus(c);
		publicDao.saveOrUpdate(badgeApp);
	}

	@Override
	public void upBadgeAppStatus(Integer id, String appStatus) {
		BadgeApp badgeApp = publicDao.get(BadgeApp.class, id);
		badgeApp.setAppStatus(appStatus);
		publicDao.saveOrUpdate(badgeApp);
	}

	@Override
	public BadgeApp findBadgeByPnrId(Integer pnrId) {
		return publicDao.get(BadgeApp.class, pnrId);
	}

	@Override
	public boolean deletebadgeNotDate(String pnrIds) {
		try {
			String hql="delete from BadgeApp where id in ("+pnrIds+")";
			publicDao.executeHql(hql);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public String findDeptList() {
		
		String ids="";
		String sql="SELECT baa.APPLICANT_NO FROM t_oa_ad_badge_app_attach baa LEFT JOIN t_oa_ad_badge_app ba ON baa.APP_NO=ba.APP_NO WHERE 1=1 AND ba.PROC_STATUS IN('1','2')";
		List<Integer> list = publicAttDao.findBySQL(sql);
		if(list!=null){
			if(list.size()==1){
				return String.valueOf(list.get(0));
			}else{
				for(int i=0;i<list.size();i++){
					if(i==list.size()-1){
						ids+=list.get(i);
					}else{
						ids+=list.get(i)+",";
					}
				}
			}
			return ids;
		}
		return null;
	}

	@Override
	public List<ComboBoxModel> findDeptNoUserList( Integer deptNo) {
		try {
			String ids="";
				ids = this.findDeptList();
			
			
			Integer userId = Constants.getCurrendUser().getUserId();
			Integer organizeId = userService.getUserByID(userId).getOrganizeId();
			//第一次添加时
			if(deptNo==null || deptNo==0){
				deptNo=organizeId;
			}
			
			
			
			
			
			
			List<ComboBoxModel> list=new ArrayList<ComboBoxModel>();
			String hql=" FROM Users  WHERE 1=1 ";
			
			 if(userId==1){
					//为超级管理员
					if(StringUtils.isNotBlank(ids)){
						//分部排除
						hql+=" and userId NOT IN ("+ids+") ";
					}
			}else{
				Organization organization = organizationService.findOrganizationByOrganizationId(deptNo.intValue());
				if(organization.getDeptLevel().equals("1") && userId!=1){
					if(StringUtils.isNotBlank(ids)){
						//分部排除
						hql+=" and userId NOT IN ("+ids+") ";
					}
					//分部
					if(deptNo!=null || deptNo!=0){
						hql+="  and organizeId="+deptNo;
					}
				}else if(organization.getDeptLevel().equals("0") && userId!=1 ){
					//总部就自己
					//判断总部人员二次添加
					if(StringUtils.isNotBlank(ids)){
						String[] strs = ids.split(",");
						for (String str : strs) {
							if(Integer.parseInt(str)==userId){
								return new ArrayList<ComboBoxModel>();
							}
						}
					}
					
					hql+=" and userId="+userId;
				}
			}
			
			
			List<Users> userList = publicUserDao.find(hql);
			for (Users users : userList) {
				//key编码,申请人id
				 String code=String.valueOf(users.getUserId());
				 //value，申请人名字
				 String text=users.getName();
				
				ComboBoxModel boxModel=new ComboBoxModel(code, text);
				 list.add(boxModel);
				
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据登记人所在部门id
	 * 获取流程key
	 */
	@Override
	public String getBusinessKey(Integer pnrId) {
		BadgeApp badgeApp = publicDao.get(BadgeApp.class, pnrId);
		Integer userId=badgeApp.getRegistrantNo();
		Organization organization = organizationService.findOrganizationByUserId(userId);
		//0;总部，1：分部
		if(organization.getDeptLevel().equals("0")){
			return Constants.BADGEAPP_HO;
		}else{
			return Constants.BADGEAPP_BO;
		}
	}
	/**
	 * 获取流程图
	 */
	@Override
	public String getTaskImage(Integer pnrId) {
		BadgeApp badgeApp = publicDao.get(BadgeApp.class, pnrId);
		Integer userId=badgeApp.getRegistrantNo();
		Organization organization = organizationService.findOrganizationByUserId(userId);
		//0;总部，1：分部
		if(organization.getDeptLevel().equals("0")){
			return Constants.BADGEAPP_HO_IMG;
		}else{
			return Constants.BADGEAPP_BO_IMG;
		}
	}

	/**
	 * 查询当前登录人的部门id
	 * 
	 */
	@Override
	public Integer findRegUserDeptNo() {
		try {
			Integer userId=Constants.getCurrendUser().getUserId();
			String deptLea = organizationService.findOrgDeptLevelByUserID(userId);
			if(deptLea.equals("0") || userId==1){
				//总部,超级管理员，返回null
				return null;
			}else if(deptLea.equals("1")){
				//分部返回相应的部门id
				Integer organizeId = userService.getUserByID(userId).getOrganizeId();
				return organizeId;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	
	
	
	
	
	
	
}

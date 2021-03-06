package com.bpms.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.LoanOrder;
import com.bpms.model.MicrocreditOpinion;
import com.bpms.model.Users;
import com.bpms.model.vo.MicrocreditOpinionModel;
import com.bpms.service.CommonService;
import com.bpms.service.LoanOrderService;
import com.bpms.service.MicrocreditOpinionService;
import com.bpms.service.UserService;

@Service("microcreditOpinionService")
public class MicrocreditOpinionServiceImpl implements MicrocreditOpinionService {
	
	@Autowired
	BaseDAO<MicrocreditOpinion> baseDAO;
	@Autowired
	private CommonService commonservice;
	@Autowired
	private UserService userservice;
	@Autowired
	private LoanOrderService loanOrderService;
	
	@Override
	public boolean saveMicrocreditOpinion(MicrocreditOpinion mo) {
		if(StringUtils.isBlank(mo.getMcbrId())){
			baseDAO.save(mo);
		}else{
			baseDAO.update(mo);
		}
		return true;
	}

	@Override
	public MicrocreditOpinion findMicrocreditOpinionByOid(String loanOrderId) {
		String hql = " FROM MicrocreditOpinion mo WHERE mo.loanOrderId = '"+loanOrderId+"'";
		List<MicrocreditOpinion> list = baseDAO.find(hql);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public MicrocreditOpinionModel findMicOpinionModelByOid(String loanOrderId) {
		String hql = " FROM MicrocreditOpinion mo WHERE mo.loanOrderId = '"+loanOrderId+"'";
		List<MicrocreditOpinion> list = baseDAO.find(hql);
		MicrocreditOpinionModel mom = new MicrocreditOpinionModel();
		if(list.size()>0){
			MicrocreditOpinion mic = list.get(0);
			try {
				PropertyUtils.copyProperties(mom, mic);
			} catch (IllegalAccessException | InvocationTargetException
					| NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mom.setAdviceLoanPeriodText(commonservice.findDictName("loan_period_type", mic.getAdviceLoanPeriod()));
			if(null!=mic.getLoanRate()) {
				mom.setLoanRateText(mic.getLoanRate().multiply(new BigDecimal(100)).doubleValue()+"%");
			}
			if(null!=mic.getCounselingRate()) {
				mom.setCounselingRateText(mic.getCounselingRate().multiply(new BigDecimal(100)).doubleValue()+"%");
			}
			Integer operatorA = Integer.parseInt(mic.getOperatorA());
			Users usera = userservice.findUserById(operatorA);
			mom.setOperatorAName(usera.getName());
			String[] operatorBs = mic.getOperatorB().trim().split(",");
			String operatorBName = "";
			for(int i = 0 ; i < operatorBs.length; i++) {
				Integer operatorB = Integer.parseInt(operatorBs[i].trim());
				Users userb = userservice.findUserById(operatorB);
				operatorBName += userb.getName()+",";
			}
			mom.setOperatorBName(operatorBName);
			LoanOrder lo = loanOrderService.findLoanOrderById(loanOrderId);
			mom.setMaritalStatus(commonservice.findDictName("marriage_type",lo.getMarriageType()));
			mom.setAuditWayText(commonservice.findDictName("audit_way", mic.getAuditWay()));
		}
		return mom;
	}

}

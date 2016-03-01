package com.bpms.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.AccountInfo;
import com.bpms.mydao.loan.AccountInfoMapper;
import com.bpms.service.AccountInfoService;
import com.bpms.util.Constants;
/**
 * 开户行信息AccountInfoServiceImpl
 * @author panchuanhe
 * 2015/5/30
 */
@Service("accountInfoService")
public class AccountInfoServiceImpl implements AccountInfoService {

	@Autowired
	private BaseDAO<AccountInfo> baseDAO;
	
	@Autowired
	private AccountInfoMapper accountInfoMapper;
	
	@Override
	public boolean saveAccountInfo(AccountInfo accountInfo) {
		try {
			//获取用户id
			Integer userid = Constants.getCurrendUser().getUserId();
			//有ID就更新
			if(StringUtils.isNotBlank(accountInfo.getAccountId())){
				baseDAO.update(accountInfo);
			}else{
				//创建时间
				accountInfo.setCreateDate(new Date());
				//创建人
				accountInfo.setCreator(String.valueOf(userid));
				baseDAO.save(accountInfo);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<AccountInfo> findListByLoanerId(String loanerId) {
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("from com.bpms.model.AccountInfo t where 1=1 and t.cusId = '"+loanerId+"'");
			hql.append(" order by t.primaryBackup");
			return baseDAO.find(hql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AccountInfo findById(String id) {
		try {
			return baseDAO.get(AccountInfo.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteById(String id) {
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("delete from com.bpms.model.AccountInfo t where 1=1 and t.accountId = '"+id+"'");
			baseDAO.executeHql(hql.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<Map<String, Object>> findAccountInfoList(
			Map<String, Object> param) {
		return accountInfoMapper.findAccountInfoList(param);
	}
	
	@Override
	public long findAccountInfoListCount(Map<String, Object> param) {
		return accountInfoMapper.findAccountInfoListCount(param);
	}

	@Override
	public AccountInfo hasICBCAccount(String loanerId,String loanOrderId) {
		String hql = "SELECT t FROM AccountInfo t,LoanorderAndAccountinfo l WHERE t.accountId = l.accountId AND t.isICBC = 'Y' AND t.cusId = '"+loanerId+"' AND l.loanOrderId = '"+loanOrderId+"' ORDER BY t.primaryBackup";
		List<AccountInfo> list = baseDAO.find(hql);
		if(null!=list && list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
	}

}

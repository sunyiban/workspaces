package com.bpms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.Address;
import com.bpms.model.Company;
import com.bpms.model.vo.CompanyModel;
import com.bpms.mydao.loan.CompanyMapper;
import com.bpms.service.AddressService;
import com.bpms.service.CompanyService;
import com.bpms.util.Constants;
/**
 * bpms 贷款人公司信息servicImpl
 * @author panchuanhe
 * @date 2015/6/16
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private BaseDAO<Company> baseDAO;
	@Autowired
	private AddressService addressServiceImpl;
	@Autowired
	private CompanyMapper companyMapper;
	
	@Override
	public boolean saveCompanyModel(CompanyModel companyModel) {
		try {
			//获取用户id
			Integer userid = Constants.getCurrendUser().getUserId();
			Company company = new Company();
			PropertyUtils.copyProperties(company,companyModel);
			if (StringUtils.isBlank(company.getComId())) {
				//创建时间
				company.setCreateDate(new Date());
				//创建人
				company.setCreator(String.valueOf(userid));
				baseDAO.save(company);
			} else {
				baseDAO.update(company);
			}
			/**保存地址**/
			Address Addr = new Address();
			Addr.setProvinceId(companyModel.getDwProvince()==null?0:companyModel.getDwProvince());//省
			Addr.setCityId(companyModel.getDwCity()==null?0:companyModel.getDwCity());//市
			Addr.setAreaId(companyModel.getDwArea()==null?0:companyModel.getDwArea());//县
			Addr.setAddrDetails(companyModel.getDwAddrDetails()==null?"":companyModel.getDwAddrDetails());//详细
			Addr.setHostType("A");//客户类型
			Addr.setAddrType("A");//地址类别
			Addr.setHostId(company.getComId());//单位id
			addressServiceImpl.saveAddress(Addr);
			
			company.setAddress(Addr.getAddrId());//更新主数据地址
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<CompanyModel> findCompanyByLonaId(String loanerId){
		try {
			StringBuffer hql = new StringBuffer("from com.bpms.model.Company t where 1=1 and t.loanerId = '"+loanerId+"'");
			hql.append(" order by t.createDate desc");
			List<Company> list = baseDAO.find(hql.toString());
			List<CompanyModel> modellist = new ArrayList<CompanyModel>();//返回的List
			for(Company company:list){
				CompanyModel cm = new CompanyModel();
				PropertyUtils.copyProperties(cm, company);
				
				String addrId = company.getAddress();//id
				//根据id查到地址
				Address address = addressServiceImpl.findById(addrId);
				if(address!=null){
					cm.setDwProvince(address.getProvinceId());
					cm.setDwCity(address.getCityId());
					cm.setDwArea(address.getAreaId());
					cm.setDwAddrDetails(address.getAddrDetails());
				}
				modellist.add(cm);
			}
			return modellist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Company> findCompanyByLoanOrderId(String loanerOrderId) {
		String hql =  "select c FROM Company c , LoanorderAndCompany lc , LoanOrder l " +
		        " WHERE c.comId = lc.comId AND l.loanOrderId = lc.loanOrderId AND l.loanOrderId = '"+loanerOrderId+"'";
		//查询出列表
		List<Company> list = baseDAO.find(hql.toString());
		return list;
	}
    
	@Override
	public Long count(Integer id) {
		Long count = 0L;
		StringBuffer hql = new StringBuffer("select count(*) from com.bpms.model.Company t where 1=1 and t.loanerId = '"+id+"'");
		count = baseDAO.count(hql.toString());
		return count;
	}

	@Override
	public boolean saveCompany(Company company) {
		if(StringUtils.isBlank(company.getComId())){
			baseDAO.save(company);
		}else{
			baseDAO.update(company);
		}
		return true;
	}

	@Override
	public Company findCompanyById(String ComId) {
		try {
			Company company = baseDAO.get(Company.class, ComId);
			return company;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteById(String id) {
		try {
			StringBuffer hql = new StringBuffer("delete from com.bpms.model.Company c where c.comId = '"+id+"'");
			baseDAO.executeHql(hql.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Company findComByON(String loanOrderId, String name) {
		String hql = "SELECT c FROM Company c , LoanorderAndCompany lac WHERE c.comId = lac.comId AND c.name = '"+name+"' AND lac.loanOrderId = '"+loanOrderId+"'";
		List<Company> list = baseDAO.find(hql);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> findCompanyList(Map<String, Object> param) {
		return companyMapper.findCompanyList(param);
	}

	@Override
	public long findCompanyListCount(Map<String, Object> param) {
		return companyMapper.findCompanyListCount(param);
	}

}

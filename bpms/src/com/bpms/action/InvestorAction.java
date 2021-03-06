package com.bpms.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.Address;
import com.bpms.model.Investor;
import com.bpms.service.AddressService;
import com.bpms.service.InvestOrderService;
import com.bpms.service.InvestorService;
import com.bpms.util.Constants;
import com.bpms.util.PageUtil;
import com.bpms.view.model.DataModel;
import com.bpms.view.model.GridModel;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * 个人贷款业务的申请单请求处理器的action
 * 
 * @author 刘洪虎 2015/05/07.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/05/07 刘洪虎 创建.
 */
@Namespace("/investor")
@Action(value = "investorAction",results = {@Result(name="success",location="/jsp/investOrder/investorView.jsp")})
public class InvestorAction extends BaseAction implements
		ModelDriven<Investor> {
	/**
	 * @Fields serialVersionUID : TODO（用一句话描述这个变量表示什么）
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Investor investor;
	private InvestorService investorService;
	private AddressService addressService;
	private InvestOrderService investOrderService;
	
	private String addressId;
	private String investOrderId;
	
	@Autowired
	public void setInvestorService(InvestorService investorService) {
		this.investorService = investorService;
	}
	
	@Autowired
	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}
	
	@Autowired
	public void setInvestOrderService(InvestOrderService investOrderService) {
		this.investOrderService = investOrderService;
	}

	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年7月15日 上午9:35:30
	 * @Title:findAllInvestor
	 * @Description:TODO 投资客户列表（这里描述这个方法的作用）
	 * @return
	 * @throws:
	 */
	public String findAllInvestor(){
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != searchValue && !"".equals(searchValue)) {
			map.put(searchName, Constants.GET_SQL_LIKE + searchValue
					+ Constants.GET_SQL_LIKE);
		}
		PageUtil pageUtil = new PageUtil(page, rows, searchAnds,
				searchColumnNames, searchConditions, searchVals);
		GridModel gridModel = new GridModel();
		gridModel.setRows(investorService.findInvestorList(map, pageUtil));
		gridModel.setTotal(investorService.getCount(map, pageUtil));
		OutputJson2(gridModel);
		return null;
		
	}
	
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年7月27日 下午4:28:15
	 * @Description:TODO 根据投资人id查找投资人（这里描述这个方法的作用）
	 * @return
	 * @throws:
	 */
	public String findInvestorById(){
		OutputJson2(investorService.findInvestorByInvestorId(getModel().getInvestorId()));
		return null;
	}
	
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年7月15日 下午1:08:09
	 * @Title:persistenceInvestorDlg
	 * @Description:TODO 持久化客户（这里描述这个方法的作用）
	 * @return
	 * @throws Exception
	 * @throws:
	 */
	public String persistenceInvestorDlg() throws Exception {
		DataModel json = new DataModel();
		boolean bl = investorService.persistenceInvestor(getModel());
		//部门助理更新客户信息同时更新订单
		if(StringUtils.isNotBlank(investOrderId)){
			investOrderService.persistenceInvestOrder(investOrderId,getModel());
		}
		if(bl){
			json.setStatus(true);
			json.setMessage("恭喜你，保存成功!");
			json.setTitle("提示");
			json.setData(getModel().getInvestorId());
		}else{
			json = new DataModel("提示","出错了,保存失败!",false);
		}
		OutputJson(json);
		return null;
	}


	/**
	 * @author: xujianwei
	 * @time:2015年7月15日 下午1:27:49
	 * @Title:delInvestor
	 * @Description:TODO 删除客户（这里描述这个方法的作用）
	 * @return
	 * @throws Exception
	 * @throws:
	 */
	public String delInvestor() throws Exception {
		boolean flag =investorService.delInvestor(IDS);
		DataModel json = new DataModel();
		if(flag){
			json.setStatus(true);
			json.setTitle("提示信息");
			json.setMessage("删除成功！");
		}else{
			json.setStatus(false);
			json.setTitle("提示信息");
			json.setMessage("删除失败！");
		}
		OutputJson(json);
		return null;
	}

	
	/**
	 * @author: xujianwei
	 * @time:2015年7月15日 下午7:50:43
	 * @Title:findAddressById
	 * @Description:TODO 根据addressId查询地址详情（这里描述这个方法的作用）
	 * @return
	 * @throws:
	 */
	public String findAddressById(){
		Address addr = addressService.findById(addressId);
		OutputJson(addr);
		return null;
	}

	
	/**
	 * @Description: 根据投资人id查询投资人信息
	 * @return String
	 * @throws
	 */
	public String findInvestorByInvestorId(){
	   investor = investorService.findInvestorByInvestorId(investor.getInvestorId());
	   return "success";
	}

	
	@Override
	public Investor getModel() {
		// TODO Auto-generated method stub
		if(investor==null){
			investor=new Investor();
		}
		return investor;
	}

	public Investor getInvestor() {
		return investor;
	}

	public void setInvestor(Investor investor) {
		this.investor = investor;
	}
	
	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getInvestOrderId() {
		return investOrderId;
	}

	public void setInvestOrderId(String investOrderId) {
		this.investOrderId = investOrderId;
	}		
}

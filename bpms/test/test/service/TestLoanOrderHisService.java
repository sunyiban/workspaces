package test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import test.TestBase;

import com.alibaba.fastjson.JSON;
import com.bpms.model.LoanContract;
import com.bpms.model.LoanOrder;
import com.bpms.mydao.loan.LoanOrderMapper;
import com.bpms.service.LoanContractService;
import com.bpms.service.RoleService;
import com.bpms.service.UserAndRoleService;
import com.bpms.service.UserService;

public class TestLoanOrderHisService extends TestBase {

	@Autowired
	private LoanOrderMapper loanOrderMapper;

	@Autowired
	private LoanContractService loanContractService;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserAndRoleService userAndRoleService;

	@Test
	@Transactional(readOnly = false)
	public void findLoanOrderList() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("creator", "88");
		param.put("name", "白");
		param.put("start", 0);
		param.put("end", 10);
		List<Map<String, Object>> loanOrderList = loanOrderMapper
				.findLoanOrderList(param);
		System.out.println(JSON.toJSONString(loanOrderList));

		Long count = loanOrderMapper.findLoanOrderListCount(param);
		System.out.println(count);
	}

	@SuppressWarnings("unused")
	@Test
	@Transactional
	public void getShiFangJinE() {
		LoanContract calculateContract = loanContractService
				.calculateContract("2c928ac1519eefda01519ef621970002");
		System.out.println("Nihao ");
	}

	@Test
	@Transactional
	public void getContractNo() {
		LoanOrder loanOrder = new LoanOrder("2c928ac1520b16d601520b4470200003");
		loanOrder.setCreator(15285);
		String createContractNo = loanContractService
				.createContractNo(loanOrder);
		System.out.println(createContractNo);
	}

}

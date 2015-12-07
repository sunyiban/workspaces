package test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import test.TestBase;

import com.alibaba.fastjson.JSON;
import com.bpms.mydao.LoanOrderMapper;

public class TestLoanOrderHisService extends TestBase {

	@Autowired
	private LoanOrderMapper loanOrderMapper;

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

}

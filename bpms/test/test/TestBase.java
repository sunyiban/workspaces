package test;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Copyright(C) JiNanShangJie 2015.
 * 
 * JUit4 测试的基类.
 * 
 * @author 刘洪虎 2015/03/10.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/03/10 刘洪虎 创建.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-beans.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)  
public class TestBase {
	/** 日志. */
	protected Logger logger;

	/** 构造方法. */
	public TestBase() {
		logger = Logger.getLogger(this.getClass());
	}	
}

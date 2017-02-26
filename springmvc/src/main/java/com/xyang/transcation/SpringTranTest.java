package com.xyang.transcation;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @描述 不使用事务控制
 *     
 * @date 2017年2月26日-下午5:27:18
 * @author IBM
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringTranTest {

	private static String outter = "a";
	private static String inner = "b";
	private static double money = 200d;

	
	@Resource(name = "accountService")
	private IAccountService accountService;

	@Test
	public void tran() {
		accountService.transfer(outter, inner, money,false);
	}

	@Test
	public void reset() {
		accountService.transfer(inner, outter, money,false);
	}
}

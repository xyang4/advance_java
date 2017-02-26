package com.xyang.transcation;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @描述 spring 编程式事务控制
 * @date 2017年2月26日-下午5:27:18
 * @author IBM
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext1.xml")
public class SpringTranTest1 {

	@Resource(name = "accountService") // 使用代理类
	private IAccountService accountService;
	private static String outter = "a";
	private static String inner = "b";
	private static double money = 200d;

	@Test
	public void tran() {
		accountService.transfer(outter, inner, money,false);
	}

	@Test
	public void reset() {
		accountService.transfer(inner, outter, money,false);
	}
}

package com.xyang.transcation;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @描述 spring 声明式事务控制 <br>
 *     基于aspectj的xml方式配置
 * @date 2017年2月26日-下午5:27:18
 * @author IBM
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext3.xml")
public class SpringTranTest4 {

	private static String outter = "a";
	private static String inner = "b";
	private static double money = 200d;

	@Resource(name = "accountService") // 使用代理类
	private IAccountService accountService;

	@Test
	public void tran_0() {
		accountService.transfer(outter, inner, money,false);
	}
	@Test
	public void tran_1() {
		accountService.transfer(outter, inner, money,true);
	}

	@Test
	public void reset() {
		accountService.transfer(inner, outter, money,false);
	}
}

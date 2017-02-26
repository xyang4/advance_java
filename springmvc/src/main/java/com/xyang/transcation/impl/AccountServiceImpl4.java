package com.xyang.transcation.impl;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xyang.transcation.IAccountDao;
import com.xyang.transcation.IAccountService;

/**
 * @描述 使用注解方式控制事务
 * @date 2017年2月26日-下午6:05:21
 * @author IBM
 *
 */
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class AccountServiceImpl4 implements IAccountService {

	private IAccountDao accountDao;

	// 使用构造器注入
	public void setAccountDao(IAccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public void transfer(final String outter, final String inner, final Double money, final boolean hasException) {
		accountDao.outMoney(outter, inner, money);
		// 制造异常
		if (hasException) {
			throw new RuntimeException();
		}
		accountDao.inMoney(outter, inner, money);
	}

}

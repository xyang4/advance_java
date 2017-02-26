package com.xyang.transcation.impl;

import com.xyang.transcation.IAccountDao;
import com.xyang.transcation.IAccountService;

/**
 * @描述 不做事务处理
 * @date 2017年2月26日-下午6:05:21
 * @author IBM
 *
 */
public class AccountServiceImpl implements IAccountService {
	private IAccountDao accountDao;

	// 使用构造器注入
	public void setAccountDao(IAccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public void transfer(String outter, String inner, Double money, boolean hasException) {
		accountDao.outMoney(outter, inner, money);
		// 制造异常
		if (hasException) {
			throw new RuntimeException();
		}
		accountDao.inMoney(outter, inner, money);
	}

}

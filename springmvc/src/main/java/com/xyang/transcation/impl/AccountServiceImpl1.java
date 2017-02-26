package com.xyang.transcation.impl;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.xyang.transcation.IAccountDao;
import com.xyang.transcation.IAccountService;

/**
 * @描述 使用编程式事务处理
 * @date 2017年2月26日-下午6:05:21
 * @author IBM
 *
 */
public class AccountServiceImpl1 implements IAccountService {
	private IAccountDao accountDao;

	// 使用构造器注入
	public void setAccountDao(IAccountDao accountDao) {
		this.accountDao = accountDao;
	}

	// 注入事务管理模板
	private TransactionTemplate transactionTemplate;

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	@Override
	public void transfer(final String outter, final String inner, final Double money, final boolean hasException) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				accountDao.outMoney(outter, inner, money);
				// 制造异常
				if (hasException){
					throw new RuntimeException();
				}
				accountDao.inMoney(outter, inner, money);
			}
		});
	}

}

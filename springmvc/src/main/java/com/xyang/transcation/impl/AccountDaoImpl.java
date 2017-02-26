package com.xyang.transcation.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.xyang.transcation.IAccountDao;

public class AccountDaoImpl extends JdbcDaoSupport implements IAccountDao {

	@Override
	public int outMoney(String outter, String inner, Double money) {
		String sql = "update account set money = money-? where name=?";
		return this.getJdbcTemplate().update(sql, money, outter);
	}

	@Override
	public int inMoney(String outter, String inner, Double money) {
		String sql = "update account set money = money+? where name=?";
		return this.getJdbcTemplate().update(sql, money, inner);
	}

}

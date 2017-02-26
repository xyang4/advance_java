package com.xyang.transcation;

public interface IAccountDao {
	public int outMoney(String outter, String inner, Double money);

	public int inMoney(String outter, String inner, Double money);
}

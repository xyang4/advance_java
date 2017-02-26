package com.xyang.transcation;

/**
 * @描述 转账业务层接口
 * @date 2017年2月26日-下午4:43:18
 * @author IBM
 *
 */
public interface IAccountService {
	public void transfer(String out, String in, Double money,boolean hasException);
}

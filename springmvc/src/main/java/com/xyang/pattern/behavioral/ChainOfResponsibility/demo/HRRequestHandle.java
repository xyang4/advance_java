package com.xyang.pattern.behavioral.ChainOfResponsibility.demo;

/**
 * @描述 ConcreteHandler - HR请求处理
 * @date 2017年2月9日-上午10:57:20
 * @author IBM
 *
 */
public class HRRequestHandle implements RequestHandle {

	@Override
	public void handleRequest(Request request) {
		if (request instanceof DimissionRequest) {
			System.out.println("要离职, 人事审批!");
		}

		System.out.println("请求完毕");

	}

}

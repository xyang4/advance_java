package com.xyang.pattern.behavioral.ChainOfResponsibility.demo;

/**
 * @描述 ConcreteHandler - 项目经理处理
 * @date 2017年2月9日-上午10:58:48
 * @author IBM
 *
 */
public class PMRequestHandle implements RequestHandle {

	RequestHandle rh;

	public PMRequestHandle(RequestHandle rh) {
		this.rh = rh;
	}

	@Override
	public void handleRequest(Request request) {
		if (request instanceof AddMoneyRequest) {
			System.out.println("要加薪, 项目经理审批!");
		} else {
			rh.handleRequest(request);
		}
	}
}

package com.xyang.pattern.behavioral.ChainOfResponsibility.demo;

/**
 * @描述 ConcreteHandler - 项目组长处理请求
 * @date 2017年2月9日-上午10:47:29
 * @author IBM
 *
 */
public class TLRequestHandle implements RequestHandle {

	RequestHandle rh;

	public TLRequestHandle(RequestHandle rh) {
		this.rh = rh;
	}

	@Override
	public void handleRequest(Request request) {
		if (request instanceof LeaveRequest) {
			System.out.println("要请假, 项目组长审批!");
		} else {
			rh.handleRequest(request);
		}
	}
}

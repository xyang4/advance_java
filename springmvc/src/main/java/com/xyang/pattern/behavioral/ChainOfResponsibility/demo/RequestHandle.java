package com.xyang.pattern.behavioral.ChainOfResponsibility.demo;

/**
 * @描述 Handler 
 * @date 2017年2月9日-上午10:57:47
 * @author IBM
 *
 */
public interface RequestHandle {
	public void handleRequest(Request request);
}

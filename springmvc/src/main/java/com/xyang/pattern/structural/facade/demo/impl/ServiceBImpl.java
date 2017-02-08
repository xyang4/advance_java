package com.xyang.pattern.structural.facade.demo.impl;

import com.xyang.pattern.structural.facade.demo.ServiceB;

public class ServiceBImpl implements ServiceB {

	@Override
	public void methodB() {
		System.out.println("这是服务B");
	}

}

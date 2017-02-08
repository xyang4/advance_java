package com.xyang.pattern.structural.facade.demo.impl;

import com.xyang.pattern.structural.facade.demo.ServiceA;

public class ServiceAImpl implements ServiceA {

	@Override
	public void methodA() {
		System.out.println("这是服务A");
	}

}

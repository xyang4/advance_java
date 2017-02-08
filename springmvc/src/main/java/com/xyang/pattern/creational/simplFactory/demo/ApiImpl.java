package com.xyang.pattern.creational.simplFactory.demo;

public class ApiImpl implements Api {

	@Override
	public void operation(String str) {
		System.out.printf("简单工厂实现，输入信息为:%s.", str);
	}

}

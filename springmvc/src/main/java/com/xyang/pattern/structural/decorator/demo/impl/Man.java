package com.xyang.pattern.structural.decorator.demo.impl;

import com.xyang.pattern.structural.decorator.demo.Person;

/**
 * @描述 ConcreteComponent 组件对象的具体实现，可给该对象添加一些职责
 * @date 2017年2月2日-下午7:14:20
 * @author IBM
 *
 */
public class Man implements Person {

	@Override
	public void eat() {
		System.out.println("男人在吃");
	}

}

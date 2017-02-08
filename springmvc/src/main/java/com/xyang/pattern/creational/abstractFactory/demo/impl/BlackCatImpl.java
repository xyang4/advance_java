package com.xyang.pattern.creational.abstractFactory.demo.impl;

import com.xyang.pattern.creational.abstractFactory.demo.ICat;

/**
 * @描述 ConcreteProduct 定义一个将被相应的具体工厂创建的产品对象。 实现<code>{@link #AbstractProduct}</code>接口。
 * @date 2017年2月1日-上午11:49:26
 * @author IBM
 *
 */
public class BlackCatImpl implements ICat {

	@Override
	public void eat() {
		System.out.println("The black cat is eating!");

	}

}

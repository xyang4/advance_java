package com.xyang.pattern.creational.abstractFactory.demo.impl;

import com.xyang.pattern.creational.abstractFactory.demo.IAnimalFactory;
import com.xyang.pattern.creational.abstractFactory.demo.ICat;
import com.xyang.pattern.creational.abstractFactory.demo.IDog;

/**
 * @描述 ConcreteFactory 实现创建具体产品对象的操作。
 * 
 * @date 2017年2月1日-上午11:48:14
 * @author IBM
 *
 */
public class BlackAnimalFactoryImpl implements IAnimalFactory {

	@Override
	public ICat createCat() {
		return new BlackCatImpl();
	}

	@Override
	public IDog createDog() {
		return new BlackDogImpl();
	}

}

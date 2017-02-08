package com.xyang.pattern.creational.abstractFactory.demo.impl;

import com.xyang.pattern.creational.abstractFactory.demo.IAnimalFactory;
import com.xyang.pattern.creational.abstractFactory.demo.ICat;
import com.xyang.pattern.creational.abstractFactory.demo.IDog;

public class WhiteAnimalFactoryImpl implements IAnimalFactory {

	@Override
	public ICat createCat() {
		return new WhiteCatImpl();
	}

	@Override
	public IDog createDog() {
		return new WhiteDogImpl();
	}

}

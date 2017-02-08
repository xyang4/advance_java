package com.xyang.pattern.creational.abstractFactory.demo.impl;

import com.xyang.pattern.creational.abstractFactory.demo.IDog;

public class WhiteDogImpl implements IDog {

	@Override
	public void eat() {
		System.out.println("The white dog is eating!");
	}

}

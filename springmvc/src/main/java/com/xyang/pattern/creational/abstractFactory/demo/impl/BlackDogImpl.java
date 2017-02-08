package com.xyang.pattern.creational.abstractFactory.demo.impl;

import com.xyang.pattern.creational.abstractFactory.demo.IDog;

public class BlackDogImpl implements IDog {

	@Override
	public void eat() {
		System.out.println("The black dog is eating");
	}

}

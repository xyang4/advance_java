package com.xyang.pattern.creational.abstractFactory.demo.impl;

import com.xyang.pattern.creational.abstractFactory.demo.ICat;

public class WhiteCatImpl implements ICat {

	@Override
	public void eat() {
		System.out.println("The white cat is eating!");
	}

}

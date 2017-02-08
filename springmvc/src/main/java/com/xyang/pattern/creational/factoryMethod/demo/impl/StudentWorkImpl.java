package com.xyang.pattern.creational.factoryMethod.demo.impl;

import com.xyang.pattern.creational.factoryMethod.demo.IWork;

public class StudentWorkImpl implements IWork {

	@Override
	public void doWork() {
		System.out.println("学生做作业!");
	}

}

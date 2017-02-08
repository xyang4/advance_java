package com.xyang.pattern.creational.factoryMethod.demo.impl;

import com.xyang.pattern.creational.factoryMethod.demo.IWork;

public class TeacherWorkImpl implements IWork {

	@Override
	public void doWork() {
		System.out.println("老师审批作业!");

	}

}

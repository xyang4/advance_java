package com.xyang.pattern.creational.abstractFactory.order.impl;

import com.xyang.pattern.creational.abstractFactory.order.IOrderMainDAO;

public class RdbMainDAOImpl implements IOrderMainDAO {

	@Override
	public void saveOrderMain() {
		System.out.println("now in RdbMainDAOImpl saveOrderMain");
	}

}

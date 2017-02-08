package com.xyang.pattern.creational.abstractFactory.order.impl;

import com.xyang.pattern.creational.abstractFactory.order.IOrderDetailDAO;

public class RdbDetailDAOImpl implements IOrderDetailDAO {

	@Override
	public void saveOrderDetail() {
		System.out.println("now in RdbDetailDAOImpl saveOrderDetail");
	}

}

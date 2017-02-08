package com.xyang.pattern.creational.abstractFactory.order;

import com.xyang.pattern.creational.abstractFactory.order.impl.RdbDetailDAOImpl;
import com.xyang.pattern.creational.abstractFactory.order.impl.RdbMainDAOImpl;

public class RdbDAOFactory extends DAOFactory {

	@Override
	public IOrderMainDAO createOrderMainDAO() {
		return new RdbMainDAOImpl();
	}

	@Override
	public IOrderDetailDAO createOrderDetailDAO() {
		return new RdbDetailDAOImpl();
	}

}

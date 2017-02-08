package com.xyang.pattern.creational.abstractFactory.order;

import com.xyang.pattern.creational.abstractFactory.order.impl.XMLDetailDAOImpl;
import com.xyang.pattern.creational.abstractFactory.order.impl.XMLMainDAOImpl;

public class XmlDAOFactory extends DAOFactory {

	@Override
	public IOrderMainDAO createOrderMainDAO() {
		return new XMLMainDAOImpl();
	}

	@Override
	public IOrderDetailDAO createOrderDetailDAO() {
		return new XMLDetailDAOImpl();
	}

}

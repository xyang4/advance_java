package com.xyang.pattern.creational.abstractFactory.order;

public abstract class DAOFactory {
	public abstract IOrderMainDAO createOrderMainDAO();

	public abstract IOrderDetailDAO createOrderDetailDAO();
}

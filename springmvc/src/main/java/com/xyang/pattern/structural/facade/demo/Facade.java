package com.xyang.pattern.structural.facade.demo;

import com.xyang.pattern.structural.facade.demo.impl.ServiceAImpl;
import com.xyang.pattern.structural.facade.demo.impl.ServiceBImpl;
import com.xyang.pattern.structural.facade.demo.impl.ServiceCImpl;

public class Facade {
	ServiceA sa;
	ServiceB sb;
	ServiceC sc;

	public Facade() {
		sa = new ServiceAImpl();
		sb = new ServiceBImpl();
		sc = new ServiceCImpl();
	}

	public void methodA() {
		sa.methodA();
		// sb.methodB();
	}

	public void methodB() {
		sb.methodB();
		// sc.methodC();
	}

	public void methodC() {
		sc.methodC();
		// sa.methodA();
	}

}

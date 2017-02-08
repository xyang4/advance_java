package com.xyang.pattern.structural.adapter.demo.impl;

import com.xyang.pattern.structural.adapter.demo.Adaptee;
import com.xyang.pattern.structural.adapter.demo.Target;

/**
 * @描述 适配者 对{@link Adaptee}的接口与Target接口进行适配
 * 
 * @date 2017年2月2日-下午7:43:56
 * @author IBM
 *
 */
public class Adapter implements Target {

	private Adaptee adaptee;

	public Adapter(Adaptee adaptee) {
		this.adaptee = adaptee;
	}
	@Override
	public void adapteeMethod() {
		adaptee.adapteeMethod();
	}
	@Override
	public void adapterMethod() {
		System.out.println("Adapter method!");
	}
}

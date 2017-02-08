package com.xyang.pattern.structural.decorator.demo;

/**
 * @描述 ConcreteDecorator 向组件添加职责。
 * @date 2017年2月2日-下午7:19:40
 * @author IBM
 *
 */
public class ManDecoratorA extends Decorator {
	@Override
	public void eat() {
		super.eat();
		reEat();
		System.out.println("ManDecoratorA类");
	}

	public void reEat() {
		System.out.println("再吃一顿饭");
	}
}

package com.xyang.pattern.structural.decorator.demo;

/**
 * @描述 Decorator 维持一个指向Component对象的指针，并定义一个与Component接口一致的接口。
 * 
 * @date 2017年2月2日-下午7:17:10
 * @author IBM
 *
 */
public abstract class Decorator implements Person {

	protected Person person;

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public void eat() {
		person.eat();
	}

}

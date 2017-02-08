package com.xyang.pattern.creational.abstractFactory.demo;

/**
 * @描述 AbstractFactory ： 声明一个创建抽象产品对象的操作接口。
 * @date 2017年2月1日-上午11:44:33
 * @author IBM
 *
 */
public interface IAnimalFactory {
	ICat createCat();

	IDog createDog();

}

package com.xyang.annotation;

public class AnnotationTest {
	// 1.注解元素必须有确定的值，要么在定义注解的默认值中指定，要么在使用注解时指定，非基本类型的注解元素的值不可为null
	public static void main(String[] args) {
		FruitInfoUtil.getFruitInfo(Apple.class);
	}
}

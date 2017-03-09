package com.xyang.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @描述 水果颜色注解
 * @date 2017年2月27日-下午11:22:58
 * @author IBM
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {
	/**
	 * 颜色枚举
	 */
	public enum Color {
		BULE, RED, GREEN
	};

	/**
	 * 颜色属性
	 */
	Color name() default Color.GREEN;

}

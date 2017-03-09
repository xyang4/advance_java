package com.xyang.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @描述 通过使用负数或空字符串表示某个元素不存在
 * @date 2017年2月27日-下午11:35:15
 * @author IBM
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitProvider {
	/**
	 * 供应商编号
	 */
	public int id() default -1;

	/**
	 * 供应商名称
	 */
	public String name() default "";

	/**
	 * 供应商地址
	 */
	public String address() default "";
}

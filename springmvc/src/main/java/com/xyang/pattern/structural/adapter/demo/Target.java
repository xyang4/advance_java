package com.xyang.pattern.structural.adapter.demo;

/**
 * @描述 定义Client使用的与特定领域相关的接口。
 * 
 * @date 2017年2月2日-下午7:39:04
 * @author IBM
 *
 */
public interface Target {
	/**
	 * @描述 适配操作
	 * @date 2017年2月2日-下午7:47:07
	 */
	void adapteeMethod();

	/**
	 * @描述 需要适配的对象的具体操作
	 * @date 2017年2月2日-下午7:47:21
	 */
	void adapterMethod();

}

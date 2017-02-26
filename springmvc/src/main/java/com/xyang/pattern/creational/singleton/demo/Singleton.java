package com.xyang.pattern.creational.singleton.demo;

/**
 * @描述 单例模式
 * @date 2017年2月9日-下午1:31:24
 * @author IBM
 *
 */
public class Singleton {
	private static Singleton instance = null;
	
	public static Singleton getInstance_low() {
		if (null == instance) {
			instance = new Singleton();
		}
		return instance;
	}

	public static synchronized Singleton getSynInstance1() {
		if (null == instance) {
			instance = new Singleton();
		}
		return instance;
	}

	public static Singleton getSynInstance2() {
		if (null == instance) {
//			synchronized (instance) {
			synchronized (Singleton.class){
				// 创建对象和赋值操作是分开进行的,多线程时可能出错
				instance = new Singleton();
			}
		}
		return instance;
	}
	/**
	 * 使用内部类来维护单例，因JVM内部机制可保证类加载其过程线程互斥
	 */
	private static class SingletonFactory {
		private static Singleton instance = new Singleton();
	}

	public static Singleton getInstance() {
		return SingletonFactory.instance;
	}

	/**
	 * 如果该对象被用于序列化，可以保证对象在序列化前后保持一致
	 */
	public Object readResolve() {
		return instance;
	}
}

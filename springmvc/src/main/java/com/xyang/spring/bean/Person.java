package com.xyang.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Person implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {
	private String name;
	private String address;
	private String phone;

	private BeanFactory beanFactory;
	private String beanName;

	public Person() {
		System.out.println("【构造器】调用Person的构造器实例化");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("【DiposibleBean接口】调用DiposibleBean.destory()");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("【InitializingBean接口】调用InitializingBean.afterPropertiesSet()");
	}

	// 这是BeanNameAware接口方法
	@Override
	public void setBeanName(String name) {
		System.out.println("【BeanNameAware接口】调用BeanNameAware.setBeanName()");
		this.beanName = name;
	}

	// 这是BeanFactoryAware接口方法
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()");
		this.beanFactory = beanFactory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("【注入属性】注入属性name");
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		System.out.println("【注入属性】注入属性address");
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		System.out.println("【注入属性】注入属性phone");
		this.phone = phone;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public String getBeanName() {
		return beanName;
	}

	// 通过<bean>的init-method属性指定的初始化方法
	public void myInit() {
		System.out.println("【init-method】调用<bean>的init-method属性指定的初始化方法");
	}

	// 通过<bean>的destroy-method属性指定的初始化方法
	public void myDestory() {
		System.out.println("【destroy-method】调用<bean>的destroy-method属性指定的初始化方法");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [name=" + name + ", address=" + address + ", phone=" + phone + ", beanFactory=" + beanFactory
				+ ", beanName=" + beanName + "]";
	}

}

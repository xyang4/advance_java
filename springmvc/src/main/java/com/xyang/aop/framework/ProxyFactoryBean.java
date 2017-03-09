package com.xyang.aop.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.xyang.proxy.Advice;

public class ProxyFactoryBean {
	private Advice advice;
	private Object target;

	public Object getProxy() {
		Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
				new InvocationHandler() {
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						advice.beforeMethod(method);
						Object retVal = method.invoke(target, args);
						advice.afterMethod(method);
						return retVal;

					}
				});
		return proxy;
	}

	public void setAdvice(Advice advice) {
		this.advice = advice;
	}

	public void setTarget(Object target) {
		this.target = target;
	}
}

package com.xyang.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import com.xyang.proxy.impl.LogAdviceImpl;
import com.xyang.proxy.impl.LogHandlerImpl;
import com.xyang.proxy.impl.UserManagerImpl;
import com.xyang.proxy.impl.UserManagerImplProxy;

public class ProxyTest {
	/**
	 * 1.代理类和委托类须实现相同接口，代理类的方法是通过委托类来实现的。如若为跟多的类实现代理则需添加相应的代理类。 <br>
	 * 2.代理对象在编译期已经确定
	 */
	@Test
	public void staticProxyTest() {
		IUserManager userManager = new UserManagerImplProxy(new UserManagerImpl());
		userManager.addUser("1111", "张三");
	}

	/**
	 * 核心类 java.lang.reflect.InvocationHandler接口和 java.lang.reflect.Proxy
	 * 通过InvocationHandler.invoke方法统一处理目标类的方法<br>
	 * AOP是思想，动态代理是实现
	 */
	@Test
	public void dynamicProxyTest() {
		LogHandlerImpl logHandler = new LogHandlerImpl();
		IUserManager userManager = (IUserManager) logHandler.newProxyInstance(new UserManagerImpl());
		userManager.addUser("1111", "张三");
	}

	@Test
	public void basicDynamicProxyTest() {
		final ArrayList<String> target = new ArrayList<>();
		Collection<String> proxyClazz = (Collection<String>) getProxyClazz(target, new LogAdviceImpl());
		proxyClazz.add("a");
		proxyClazz.add("b");
		proxyClazz.add("c");
		int size = proxyClazz.size();
		System.out.println(size);
	}

	private static Object getProxyClazz(final Object target, final Advice advice) {

		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						advice.beforeMethod(method);
						Object ret = method.invoke(target, args);
						advice.afterMethod(method);
						return ret;
					}
				});
	}
}

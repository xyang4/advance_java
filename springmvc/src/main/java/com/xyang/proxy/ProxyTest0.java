package com.xyang.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;

import com.xyang.proxy.impl.LogAdviceImpl;

public class ProxyTest0 {
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		// 1.获取代理类对象
		Class clazzProxy1 = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
		System.out.println(clazzProxy1.getName());// $Proxy0()
													// $Proxy0(InvocationHandler,int)

		System.out.println("----------begin constructors list----------");

		Constructor[] constructors = clazzProxy1.getConstructors();
		for (Constructor constructor : constructors) {
			String name = constructor.getName();
			StringBuilder sBuilder = new StringBuilder(name);
			sBuilder.append('(');
			Class[] clazzParams = constructor.getParameterTypes();
			for (Class clazzParam : clazzParams) {
				sBuilder.append(clazzParam.getName()).append(',');
			}
			if (clazzParams != null && clazzParams.length != 0)
				sBuilder.deleteCharAt(sBuilder.length() - 1);
			sBuilder.append(')');
			System.out.println(sBuilder.toString());
		}

		System.out.println("----------begin methods list----------");
		/*
		 * $Proxy0() $Proxy0(InvocationHandler,int)
		 */
		Method[] methods = clazzProxy1.getMethods();
		for (Method method : methods) {
			String name = method.getName();
			StringBuilder sBuilder = new StringBuilder(name);
			sBuilder.append('(');
			Class[] clazzParams = method.getParameterTypes();
			for (Class clazzParam : clazzParams) {
				sBuilder.append(clazzParam.getName()).append(',');
			}
			if (clazzParams != null && clazzParams.length != 0)
				sBuilder.deleteCharAt(sBuilder.length() - 1);
			sBuilder.append(')');
			System.out.println(sBuilder.toString());
		}

		System.out.println("----------begin create instance object----------");
		// Object obj = clazzProxy1.newInstance();
		Constructor constructor = clazzProxy1.getConstructor(InvocationHandler.class);
		class MyInvocationHander1 implements InvocationHandler {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// TODO Auto-generated method stub
				return null;
			}
		}

		// 2.使用 newInstance
		Collection proxy1 = (Collection) constructor.newInstance(new MyInvocationHander1());

		System.out.println(proxy1);
		proxy1.clear();
		// proxy1.size();
		// System.out.println("111111111111111");

		Collection proxy2 = (Collection) constructor.newInstance(new InvocationHandler() {

			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				return null;
			}

		});
		// 2.使用静态工厂方法获取动态代理类
		Collection proxy3 = (Collection) Proxy.newProxyInstance(Collection.class.getClassLoader(),
				new Class[] { Collection.class }, new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

						long beginTime = System.currentTimeMillis();
						Object retVal = method.invoke(new ArrayList<>(), args);
						long endTime = System.currentTimeMillis();
						System.out.println(method.getName() + " running time of " + (endTime - beginTime));
						return retVal;

					}
				});
		proxy3.add("zxx");
		proxy3.add("lhm");
		proxy3.add("bxd");
		System.out.println(proxy3.getClass().getName() + " " + proxy3.size());

		//
		System.out.println("+++ 封装后的代理类");
		final ArrayList<String> target = new ArrayList<>();
		Collection proxy4 = (Collection) getProxy(target, new LogAdviceImpl());
		proxy4.add("zxx");
		proxy4.add("lhm");
		proxy4.add("bxd");
		System.out.println(proxy4.getClass().getName() + " " + proxy4.size());
	}

	private static Object getProxy(final Object target, final Advice advice) {
		Object proxy3 = Proxy.newProxyInstance(target.getClass().getClassLoader(),
				/* new Class[]{Collection.class}, */
				target.getClass().getInterfaces(), new InvocationHandler() {
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

						/*
						 * long beginTime = System.currentTimeMillis(); Object
						 * retVal = method.invoke(target, args); long endTime =
						 * System.currentTimeMillis();
						 * System.out.println(method.getName() +
						 * " running time of " + (endTime - beginTime)); return
						 * retVal;
						 */

						advice.beforeMethod(method);
						Object retVal = method.invoke(target, args);
						advice.afterMethod(method);
						return retVal;
					}
				});
		return proxy3;
	}
}

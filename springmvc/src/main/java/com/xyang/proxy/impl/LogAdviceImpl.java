package com.xyang.proxy.impl;

import java.lang.reflect.Method;

import com.xyang.proxy.Advice;

public class LogAdviceImpl implements Advice {
	long beginTime = 0;

	@Override
	public void beforeMethod(Method method) {
		System.out.printf("+++ begin -- > method[ %s ]\n",method.getName());
		beginTime = System.currentTimeMillis();
	}

	@Override
	public void afterMethod(Method method) {
		long endTime = System.currentTimeMillis();
		System.out.printf("+++ end-- > method[ %s ] time consuming: %d\n",method.getName(),(endTime - beginTime));
	}

}

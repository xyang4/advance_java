package com.xyang.proxy;

import java.lang.reflect.Method;

/** 
 * @描述 
 * @date 2017年2月28日-下午10:06:42
 * @author IBM
 *
 */
public interface Advice {
	void beforeMethod(Method method);
	void afterMethod(Method method);
}

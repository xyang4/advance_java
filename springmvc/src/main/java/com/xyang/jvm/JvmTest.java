package com.xyang.jvm;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class JvmTest {
	/**
	 * @描述 如果在线程执行的过程中，栈空间不够用便抛出该异常
	 * @date 2017年2月9日-下午5:02:52
	 */
	@Test
	public void stackOverflowTest() {
		method();
	}

	public static void method() {
		for (;;)
			method();
	}

	/**
	 * @描述 内存溢出， 当自动扩展到计算机本身内存大小时会抛出OutOfMemoryError
	 * @date 2017年2月9日-下午5:03:28
	 */
	@Test
	public void outOfMemoryTest() {
		List list = new ArrayList();
		for (;;) {
			int[] tmp = new int[1000000];
			list.add(tmp);
		}
	}
//	1.类的加载、连接与初始化
	
	

}

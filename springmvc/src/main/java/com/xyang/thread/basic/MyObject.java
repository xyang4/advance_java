package com.xyang.thread.basic;

/**
 * 对象锁的同步和异步问题
 * 
 * @author alienware
 *
 */
public class MyObject {

	public synchronized void method1() {
		try {
			System.out.println(Thread.currentThread().getName() + "_ method1");
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void method2() {
		System.out.println(Thread.currentThread().getName() + "_ method2");
	}

	// 异步执行，因其未被上锁
	public void method3() {
		System.out.println(Thread.currentThread().getName() + "_ method3");
	}

	public static void main(String[] args) {

		final MyObject mo = new MyObject();

		/**
		 * 分析： <br>
		 * t1线程先持有object对象的Lock锁，t2线程可以以异步的方式调用对象中的非synchronized修饰的方法
		 * t1线程先持有object对象的Lock锁，t2线程如果在这个时候调用对象中的同步（synchronized）方法则需等待，也就是同步
		 */
		new Thread(new Runnable() {
			@Override
			public void run() {
				mo.method1();
			}
		}, "t1").start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				mo.method2();
			}
		}, "t2").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				mo.method3();
			}
		}, "t2").start();

	}

}

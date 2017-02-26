package com.xyang.thread;

import java.util.Random;

public class ThreadScoreData {
	private static int price;
	private static int loop_num = 3;
	private static ThreadLocal<Integer> buyThreadPrice = new ThreadLocal<>();

	public static void main(String[] args) {
		// data_handle_0();
		// data_handle_1();
		data_handle_2();
		// data_handle_3();
	}

	private static void data_handle_0() {
		for (int i = 0; i < loop_num; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					price = new Random().nextInt(10000);
					System.out.println("生产线程名称:" + Thread.currentThread().getName() + "，价格：" + price);
					new A().getPriceInfo();
					new B().getPriceInfo();
				}
			}).start();
		}
	}

	/**
	 * 每个线程的对象都不同，所以价格不一直F
	 */
	private static void data_handle_1() {
		for (int i = 0; i < loop_num; i++) {
			new Thread(new Runnable() {
				@Override
				public synchronized void run() {
					price = new Random().nextInt(10000);
					System.out.println("生产线程名称:" + Thread.currentThread().getName() + "，价格：" + price);
					new A().getPriceInfo();
					new B().getPriceInfo();
				}
			}).start();
		}
	}

	private static void data_handle_2() {
		for (int i = 0; i < loop_num; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					synchronized (ThreadScoreData.class) {
						price = new Random().nextInt(10000);
						System.out.println("生产线程名称:" + Thread.currentThread().getName() + "，价格：" + price);
						new A().getPriceInfo();
						new B().getPriceInfo();
					}
				}
			}).start();
		}
	}

	private static void data_handle_3() {
		for (int i = 0; i < loop_num; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					price = new Random().nextInt(10000);
					System.out.println("生产线程名称:" + Thread.currentThread().getName() + "，价格：" + price);
					buyThreadPrice.set(price);

					new C().getPriceInfo();
					new D().getPriceInfo();
				}
			}).start();
		}
	}

	// A模块
	static class A {
		public void getPriceInfo() {
			System.out.println(Thread.currentThread().getName() + "进入A模块,获取的价格为:" + price);
		}
	}

	// B模块
	static class B {
		public void getPriceInfo() {
			System.out.println(Thread.currentThread().getName() + "进入B模块,获取的价格为:" + price);
		}
	}

	// C模块
	static class C {
		public void getPriceInfo() {
			System.out.println(Thread.currentThread().getName() + "进入C模块,获取的价格为:" + buyThreadPrice.get());
		}
	}

	// D模块
	static class D {
		public void getPriceInfo() {
			System.out.println(Thread.currentThread().getName() + "进入D模块,获取的价格为:" + buyThreadPrice.get());
		}
	}
}

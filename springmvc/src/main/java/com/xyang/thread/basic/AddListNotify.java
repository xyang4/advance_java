package com.xyang.thread.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class AddListNotify {
	private volatile static List<String> list = new ArrayList<>();

	public void add() {
		list.add("bjsxt");
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {
		// test_0();
		// test_1_useWaitAndNotify();
		test_2_useCountDownLatch();
	}

	/**
	 * 不做任何同步处理的
	 */
	private static void test_0() {
		final AddListNotify list1 = new AddListNotify();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for (int i = 0; i < 10; i++) {
						list1.add();
						System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
						Thread.sleep(500);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					if (list1.size() == 5) {
						System.out.println("当前线程收到通知：" + Thread.currentThread().getName() + " list size = 5 线程停止..");
						throw new RuntimeException();
					}
				}
			}
		}, "t2");

		t1.start();
		t2.start();
	}

	/**
	 * notify/wait针对已经获得锁的对象操作
	 */
	private static void test_1_useWaitAndNotify() {
		final AddListNotify list2 = new AddListNotify();
		// 1 实例化一个 lock
		// 当使用wait 和 notify 的时候 ， 一定要配合着synchronized关键字去使用
		final Object lock = new Object();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					synchronized (lock) {
						for (int i = 0; i < 10; i++) {
							list2.add();
							System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
							Thread.sleep(500);
							if (list2.size() == 5) {
								System.out.println("已经发出通知..");
								lock.notify();// 唤醒一个等待的线程，并继续执行，知道退出同步的代码块后才释放资源锁
							}
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (lock) {
					if (list2.size() != 5) {
						try {
							System.out.println("t2进入...");
							lock.wait();// 让出cpu控制权、释放资源锁，进入阻塞，等待唤醒notify
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
					throw new RuntimeException();
				}
			}
		}, "t2");
		// 开启线程
		t1.start();
		t2.start();
	}

	@SuppressWarnings("unused")
	private static void test_2_useCountDownLatch() {
		final AddListNotify list2 = new AddListNotify();

		final CountDownLatch countDownLatch = new CountDownLatch(1);

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for (int i = 0; i < 10; i++) {
						list2.add();
						System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
						Thread.sleep(500);
						if (list2.size() == 5) {
							System.out.println("已经发出通知..");
							countDownLatch.countDown();
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				if (list2.size() != 5) {
					try {
						System.out.println("t2进入...");
						countDownLatch.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
				throw new RuntimeException();
			}
		}, "t2");

		t1.start();
		t2.start();
	}
}

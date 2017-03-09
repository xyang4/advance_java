package com.xyang.thread.basic;

public class ThreadPriorityTest {

	public static void main(String[] args) {
		Thread thread1 = new Thread(new MyThread());
		MyThread2 thread2 = new MyThread2();

		thread1.start();
		thread2.start();
		thread1.setPriority(Thread.MIN_PRIORITY);
		thread2.setPriority(Thread.MAX_PRIORITY);

	}
}

class MyThead implements Runnable {
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println(Thread.activeCount() + "thread======>AAA");
		}
	}
}

// 第二种方案
class MyThread2 extends Thread {

	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println(Thread.activeCount() + "thread======BBB");
		}
	}

}
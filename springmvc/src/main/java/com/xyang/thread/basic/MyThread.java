package com.xyang.thread.basic;

/**
 * @描述 <br>
 *     线程安全：当多个线程访问某一个类（对象或方法）时，这个对象始终都能表现出正确的行为，那么这个类（对象或方法）就是线程安全的。
 *     synchronized：可以在任意对象及方法上加锁，而加锁的这段代码称为"互斥区"或"临界区"
 * @date 2017年2月21日-下午5:02:45
 * @author IBM
 *
 */
public class MyThread extends Thread {

	private int count = 5;

	// synchronized加锁
	@Override
	public synchronized void run() {
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}

	public static void main(String[] args) {
		/*
		 * 分析：当多个线程访问myThread的run方法时，以排队的方式进行处理（这里排对是按照CPU分配的先后顺序而定的），
		 * 一个线程想要执行synchronized修饰的方法里的代码： 
		 * 1 尝试获得锁
		 * 2如果拿到锁，执行synchronized代码体内容；拿不到锁，这个线程就会不断的尝试获得这把锁，直到拿到为止，
		 * 而且是多个线程同时去竞争这把锁。（也就是会有锁竞争的问题）
		 */
		MyThread myThread = new MyThread();
		new Thread(myThread, "t1").start();
		new Thread(myThread, "t2").start();
		new Thread(myThread, "t3").start();
		new Thread(myThread, "t4").start();
		new Thread(myThread, "t5").start();
	}
}

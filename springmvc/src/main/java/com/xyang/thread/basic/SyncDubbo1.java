package com.xyang.thread.basic;

/**
 * @描述 重入锁
 * @date 2017年2月21日-下午6:01:07
 * @author IBM
 *
 */
public class SyncDubbo1 {
	static class Main {
		public int i = 10;

		public synchronized void operationSup() {
			try {
				i--;
				System.out.println("Main print i = " + i);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	static class Sub extends Main {
		public synchronized void operationSub() {
			try {
				while (i > 0) {
					i--;
					System.out.println("Sub print i = " + i);
					Thread.sleep(100);
					this.operationSup();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void method1() {
		System.out.println("method1..");
		method2();
	}

	public synchronized void method2() {
		System.out.println("method2..");
		method3();
	}

	public synchronized void method3() {
		System.out.println("method3..");
	}

	public static void main(String[] args) {
		final SyncDubbo1 sd = new SyncDubbo1();
		new Thread(new Runnable() {
			@Override
			public void run() {
				sd.method1();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				Sub sub = new Sub();
				sub.operationSub();
			}
		}).start();
	}
}

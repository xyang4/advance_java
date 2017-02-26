package com.xyang.thread.basic;

/**
 * @描述 synchronized异常
 * @date 2017年2月21日-下午6:08:03
 * @author IBM
 *
 */
public class SyncException {
	private int i = 0;

	public synchronized void operation() {
		while (true) {
			try {
				i++;
				Thread.sleep(100);
				System.out.println(Thread.currentThread().getName() + " , i = " + i);
				if (i == 10) {
					Integer.parseInt("a");
					// throw new RuntimeException();
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(" log info");
//				throw new RuntimeException();
				// continue;
			}
		}
	}

	public static void main(String[] args) {

		final SyncException se = new SyncException();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				se.operation();
			}
		}, "t1");
		t1.start();
	}
}

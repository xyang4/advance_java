package com.xyang.thread.concurrent;

/**
 * @描述 threadLocal --> seqNum
 * @date 2017年3月2日-下午6:11:55
 * @author IBM
 *
 */
public class ThreadLocal4NumTest {
	private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
		@Override
		public Integer initialValue() {
			return 0;
		}
	};

	private int getNextNum() {
		threadLocal.set(threadLocal.get() + 1);
		return threadLocal.get();
	}

	public static void main(String[] args) {
		ThreadLocal4NumTest sn = new ThreadLocal4NumTest();

		new ThreadClient(sn).start();
		new ThreadClient(sn).start();
		new ThreadClient(sn).start();
		new ThreadClient(sn).start();
	}

	private static class ThreadClient extends Thread {
		private ThreadLocal4NumTest sn;

		public ThreadClient(ThreadLocal4NumTest tl4n) {
			this.sn = tl4n;
		}

		@Override
		public void run() {
			for (int i = 0; i < 6; i++) {
				System.out.printf("thread[%s] ---> sn[%d]。\n", Thread.currentThread().getName(), sn.getNextNum());
			}
		}
	}
}

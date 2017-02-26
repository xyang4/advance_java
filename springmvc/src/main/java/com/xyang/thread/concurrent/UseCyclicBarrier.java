package com.xyang.thread.concurrent;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @描述 带所有线程调用await()才继续执行
 * @date 2017年2月21日-下午3:02:53
 * @author IBM
 *
 */
public class UseCyclicBarrier {
	static class Runner implements Runnable {
		private CyclicBarrier barrier;
		private String name;

		public Runner(CyclicBarrier barrier, String name) {
			this.barrier = barrier;
			this.name = name;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(1000 * (new Random()).nextInt(5));
				System.out.println(name + " 准备OK.");
				// Waits until all parties have invoked await on this barrier
				barrier.await();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(name + " Go!!");
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		CyclicBarrier barrier = new CyclicBarrier(3); // 3
		ExecutorService executor = Executors.newFixedThreadPool(3);

		executor.submit(new Thread(new Runner(barrier, "zhangsan")));
		executor.submit(new Thread(new Runner(barrier, "lisi")));
		executor.submit(new Thread(new Runner(barrier, "wangwu")));

		executor.shutdown();
	}

}

package com.xyang;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Test;

public class ThreadPoolTest {
	private static ExecutorService threadPool;
	private static final int itemNum = 10;

	@Test
	public void threadPoolTest() {
	}

	/**
	 * @描述 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
	 * @date 2017年3月4日-下午4:45:29
	 */
	@Test
	public void newCachedThreadPoolTest() {
		threadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < itemNum; i++) {
			final int index = i;
			try {
				Thread.sleep(index * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("index --> newCachedThreadPool :" + index);
				}
			});
		}
	}

	/**
	 * @描述 创建定长的线程池，超出的线程在池中等待
	 * @date 2017年3月4日-下午4:51:11
	 */
	@Test
	public void newFixedThreadPoolTest() {
		threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		for (int i = 0; i < 10; i++) {
			final int index = i;
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("index --> newFixedThreadPoolTest:" + index);
				}
			});
		}
	}

	@Test
	public void newScheduledThreadPoolTest() {
		ScheduledExecutorService threadPool = Executors
				.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
		// 1. 延迟3s执行
		threadPool.schedule(new Runnable() {
			@Override
			public void run() {
				System.out.println("delay 3 seconds");
			}
		}, 3, TimeUnit.SECONDS);
		// 2.延迟1s每三秒执行一次
		threadPool.scheduleAtFixedRate(new Runnable() {
			public void run() {
				System.out.println("delay 1 seconds, and excute every 3 seconds");
			}
		}, 1, 3, TimeUnit.SECONDS);
	}

	@Test
	public void newSingleThreadPoolTest() {
		threadPool = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 10; i++) {
			final int index = i;
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			threadPool.execute(new Runnable() {
				public void run() {
					System.out.println("index --> newSingleThreadPoolTest:" + index);
				}
			});
		}
	}

	@After
	public void destory() {
		if (null != threadPool) {
			threadPool.shutdown();
		}
	}
}

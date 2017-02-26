package com.xyang.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {
	public static void main(String[] args) {
		
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));

		ThreadPoolExecutor executor1 = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
		/* 推荐使用工厂方法创建 ThreadPoolExecutor，因其配置有最佳参数设置
		 ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
		 public static ExecutorService newFixedThreadPool(int nThreads) {
        		return new ThreadPoolExecutor(nThreads, nThreads,0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
			}
        */
		for (int i = 0; i < 15; i++) {
			MyTask myTask = new MyTask(i);
			executor.execute(myTask);
			System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" + executor.getQueue().size()
					+ "，已执行玩别的任务数目：" + executor.getCompletedTaskCount());
		}
		executor.shutdown();
	}
}

class MyTask implements Runnable {
	/**
	 * 编号
	 */
	private int taskNO;

	public MyTask(int num) {
		this.taskNO = num;
	}

	@Override
	public void run() {
		System.out.println("线程:"+Thread.currentThread().getName()+" 正在执行task:" + taskNO);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("线程:"+Thread.currentThread().getName()+" task " + taskNO + "执行完毕");
	}
}

package com.xyang.thread.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class UseFuture implements Callable<String> {
	private String param;

	public UseFuture(String para) {
		this.param = para;
	}

	/**
	 * 这里是真实的业务逻辑，其执行可能很慢
	 */
	@Override
	public String call() throws Exception {
		// 模拟执行耗时
		Thread.sleep(3000);
		return Thread.currentThread().getName() + ":" + this.param + "处理完成";
	}

	// 主控制函数
	public static void main(String[] args) throws Exception {
		String queryStr = "query";
		// 1.构造FutureTask，并且传入需要真正进行业务逻辑处理的类,该类一定是实现了Callable接口的类
		FutureTask<String> future1 = new FutureTask<String>(new UseFuture(queryStr));
		FutureTask<String> future2 = new FutureTask<String>(new UseFuture(queryStr));
		// 创建一个固定线程的线程池且线程数为1,
		ExecutorService executor = Executors.newFixedThreadPool(2);
		// 这里提交任务future,则开启线程执行RealData的call()方法执行
		Future<?> f1 = executor.submit(future1);
		Future<?> f2 = executor.submit(future2);
		// executor.execute(future);
		System.out.println("请求完毕");
		
		// true if this task completed
		System.out.println(f1.isDone());
		System.out.println(f2.isDone());
		try {
			// 这里可以做额外的数据操作，也就是主程序执行其他业务逻辑
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 调用获取数据方法,如果call()方法没有执行完成,则依然会进行等待
		System.out.println("数据：" + future1.get());
		System.out.println("数据：" + future2.get());
		System.out.println(f1.isDone());
		System.out.println(f2.isDone());
		executor.shutdown();
	}
}

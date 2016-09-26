package com.xyang.thread.concurrent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Test;

public class ExecutorServiceTest {
	private final static int loopNum = 10;
	private static ExecutorService executorService;

	@Test
	public void executorServiceTest() throws Exception {
		// 1.创建
		// executorService = Executors.newFixedThreadPool(10);
		// 2.执行
		// executeExe(executorService, loopNum);
		// int resultNum = submitWithParamRunnableExe(executorService, loopNum);
		// System.out.println("执行submit（Runnable）返回执行次数: "+resultNum);
		// List<Future<String>> resultList =
		// submitWithParamCallableExe(executorService, loopNum);
		// System.out.println("执行submit（Callable） 的返回值 future.get() ="+resultList.get(0).get());

		executorService = Executors.newSingleThreadExecutor();
		Set<Callable<String>> callables = new HashSet<Callable<String>>(loopNum);
		callables.add(new Callable<String>() {
			public String call() throws Exception {
				return "Task 1";
			}
		});
		callables.add(new Callable<String>() {
			public String call() throws Exception {
				return "Task 2";
			}
		});
		String result = executorService.invokeAny(callables);
		System.out.println("result = " + result);
		// 3.关闭
		executorService.shutdown();
	}

	private int submitWithParamRunnableExe(ExecutorService executorService,
			int loopNum) throws InterruptedException, ExecutionException {
		List<Future<?>> result = new ArrayList<>();
		for (int i = 0; i < loopNum; i++) {
			Future<?> future = executorService.submit(new Runnable() {
				@Override
				public void run() {
					System.out.println("Asynchronous task has result");
				}
			});
			if (null == future.get()) // returns null if the task has finished
										// correctly.
				result.add(future);
		}
		return result.size();
	}

	private List<Future<String>> submitWithParamCallableExe(
			ExecutorService executorService, int loopNum) throws Exception {
		List<Future<String>> result = new ArrayList<>();
		for (int i = 0; i < loopNum; i++) {
			Future<String> future = executorService.submit(new Callable() {
				@Override
				public Object call() throws Exception {
					System.out.println("Asynchronous task has result");
					return "success";
				}
			});
			if (null != future.get()) // returns null if the task has finished
										// correctly.
				result.add(future);
		}
		return result;
	}

	private void executeExe(ExecutorService executorService, int loopNum) {
		for (int i = 0; i < loopNum; i++) {
			executorService.execute(new Runnable() {
				public void run() {
					System.out.println("Asynchronous task no result");
				}
			});
		}
	}

	@After
	public void destory() {
		if (null != executorService) {
			executorService.shutdown();
		}
	}
}

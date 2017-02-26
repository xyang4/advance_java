package com.xyang.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程创建方式： (1)继承Thread; (2)实现Runnable接口； (3)实现Callable接口;
 * Runnable和Callable接口的区别：<br>
 * <ul>
 * <li>(1)Callable重写的方法是call(),Runnable重写的方法是run();</li>
 * <li>(2)Callable的任务执行后可返回值，而Runnable不能返回值；</li>
 * <li>(3)call方法可以抛出异常，run()不可以；</li>
 * <li>(4)运行Callable任务可以拿到一个future对象，表示异步计算的结果，
 * 它供检查计算是否完成的方法，以等待计算完成，并检索计算的结果。通过Future对象可以了解任务的执行情况，可取消任务的执行，还可以获取执行的结果。
 * </li>
 * </ul>
 * 
 * @描述
 * @date 2017年2月16日-下午6:01:02
 * @author IBM
 *
 */
public class Actor extends Thread {
	@Override
	public void run() {
		System.out.println(getName() + "是一个演员！");
		int count = 0;
		boolean keepRunning = true;

		while (keepRunning) {
			System.out.println(getName() + "登台演出：" + (++count));
			if (count == 100) {
				keepRunning = false;
			}
			if (count % 10 == 0) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(getName() + "的演出结束了！");
	}

	public static void main(String[] args) {
		// 1.使用集成创建线程
		Thread actor = new Actor();
		actor.setName("Mr. Thread");
		actor.start();
		// 2.使用实现创建线程
		Thread actressThread = new Thread(new Actress(), "Ms. Runnable");
		actressThread.start();

		create_type_3();
	}

	private static void create_type_3() {
		ExecutorService es = Executors.newCachedThreadPool();
		List<Future<String>> results = new ArrayList<Future<String>>();
		for (int i = 0; i < 5; i++) {
			results.add(es.submit(new TaskWithResult(i)));
		}

		for (Future<String> fs : results) {
			try {
				System.out.println(fs.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

}

class Actress implements Runnable {

	@Override
	public void run() {
		execute_handle();

	}

	private void execute_handle() {
		System.out.println(Thread.currentThread().getName() + "是一个演员！");
		int count = 0;
		boolean keepRunning = true;

		while (keepRunning) {
			System.out.println(Thread.currentThread().getName() + "登台演出：" + (++count));
			if (count == 100) {
				keepRunning = false;
			}
			if (count % 10 == 0) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(Thread.currentThread().getName() + "的演出结束了！");
	}
}

class TaskWithResult implements Callable<String> {
	private int id;

	public TaskWithResult(int id) {
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		return "result of TaskWithResult" + id;
	}
}

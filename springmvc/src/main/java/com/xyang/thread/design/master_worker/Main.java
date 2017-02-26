package com.xyang.thread.design.master_worker;

import java.util.Random;

/**
 * @描述 模拟master-worker模式 <br>
 *     Master 负责接收并分配任务，Worker负责处理子任务
 * @date 2017年2月21日-下午3:59:03
 * @author IBM
 *
 */
public class Main {

	public static void main(String[] args) {
		int num = Runtime.getRuntime().availableProcessors();
		System.out.println("cpu 内核数:" + num);
		Master master = new Master(new MyWorker(), num);

		Random r = new Random();
		for (int i = 1; i <= 100; i++) {
			Task t = new Task();
			t.setId(i);
			t.setPrice(r.nextInt(1000));
			master.submit(t);
		}
		master.execute();
		long start = System.currentTimeMillis();

		while (true) {
			if (master.isComplete()) {
				int priceResult = master.getResult();
				System.out.println("最终结果：" + priceResult + ", 执行时间：" + (System.currentTimeMillis() - start));
				break;
			}
		}

	}
}

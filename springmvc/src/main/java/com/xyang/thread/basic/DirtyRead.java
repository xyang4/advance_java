package com.xyang.thread.basic;

import org.springframework.context.annotation.Description;

/**
 * 业务整体需要使用完整的synchronized，保持业务的原子性。
 * 
 * @author alienware
 *
 */
public class DirtyRead {

	private String username = "bjsxt";
	private String password = "123";

	public synchronized void setValue(String username, String password) {
		this.username = username;

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.password = password;

		System.out.println("setValue最终结果：username = " + username + " , password = " + password);
	}

	@Deprecated
	public void _getValue() {
		System.out.println("_getValue方法得到：username = " + this.username + " , password = " + this.password);
	}

	public synchronized void getValue() {
		System.out.println("getValue方法得到：username = " + this.username + " , password = " + this.password);
	}

	public static void main(String[] args) throws Exception {
		final DirtyRead dr = new DirtyRead();
		new Thread(new Runnable() {
			@Override
			public void run() {
				dr.setValue("z3", "456");
			}
		}).start();

		// 模拟其他业务逻辑处理
		Thread.sleep(1000);

		dr._getValue();
		dr.getValue();
	}

}

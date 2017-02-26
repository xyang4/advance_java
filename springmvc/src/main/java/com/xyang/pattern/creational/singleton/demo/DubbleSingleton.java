package com.xyang.pattern.creational.singleton.demo;

public class DubbleSingleton {
	private static DubbleSingleton ds;

	public static DubbleSingleton getDs() {
		if (null == ds) {
			try {
				// 模拟初始化对象的准备时间
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (DubbleSingleton.class) {
				if (null == ds) {
					ds = new DubbleSingleton();
				}
			}
		}
		return ds;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(DubbleSingleton.getDs().hashCode());
				}
			}, new Integer(i).toString()).start();
		}
	}
}

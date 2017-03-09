package com.xyang.pattern.behavioral.template.demo;

public abstract class Template {
	public abstract void print();
	public void update() {
		System.out.println("开始打印");
		for (int i = 0; i < 10; i++) {
			print();
		}
		System.out.println("结束打印");
	}

}

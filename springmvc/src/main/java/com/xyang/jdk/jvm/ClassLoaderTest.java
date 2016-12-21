package com.xyang.jdk.jvm;

import java.util.Random;

import org.junit.Test;

public class ClassLoaderTest {
	@Test
	public void varInitTest() {
		Singleton singleton = Singleton.getInstance();
		System.out.printf("a == %d b == %d\n", singleton.a, singleton.b);
		System.out.printf("a == %d b == %d", Singleton.a, Singleton.b);
	}

	@Test
	public void finalTest() {
		// System.out.println(FinalTest.x);
		System.out.println(FinalTest.y);
	}

	static {
		System.out.println("ClassLoaderTest static block");
	}

	@Test
	public void classInitTest1() {
		System.out.println(Child.b);
	}

	@Test
	public void classInitTest2() {
		Parent parent;
		System.out.println("----------------------");
		parent = new Parent();
		System.out.println(Parent.a);
		System.out.println(parent.a);
		System.out.println(Child.b);
	}

	/**
	 * 调用ClassLoader并不会对类进行初始化
	 * 
	 * @throws Exception
	 */
	@Test
	public void classLoaderTest1() throws Exception {
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		String class_name_str = "java.lang.String";
		String class_name_cus = "com.xyang.jdk.jvm.Parent";
		Class<?> clazz = loader.loadClass(class_name_str);// null
		System.out.println(clazz.getClassLoader());
		System.out.println("--------------------");
		clazz = Class.forName(class_name_cus);
		System.out.println(clazz.getClassLoader());// sun.misc.Launcher$AppClassLoader@2712ee9
	}
	// 1.类的主动使用与被动使用
	// 2.类加载器分类
	// 3.编译常量，ClassLoader类，系统类加载器
	// 4.自定义类加载器
	// 5.类的卸载机制

}

class Singleton {
	private static Singleton singleton = new Singleton();
	static int a;
	static int b = 0;

	private Singleton() {
		a++;
		b++;
	}

	public static Singleton getInstance() {
		return singleton;
	}
}

class FinalTest {
	public static final int x = 6 / 3;
	public static final int y = new Random().nextInt(100);
	static {
		System.out.println("FianlTest static block");
	}
}

class Parent {
	static int a = 3;
	static {
		System.out.println("Parent static block");
	}
}

class Child extends Parent {
	static int b = 4;
	static {
		System.out.println("Child static block");
	}
}
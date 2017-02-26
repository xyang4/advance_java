package com.xyang.jvm;

public class ClassLoaderTest {
	private static ClassLoaderTest singleton = new ClassLoaderTest();
	public static int counter1;
	public static int counter2 = 0;

	private ClassLoaderTest() {
		counter1++;
		counter2++;

	}

	public static ClassLoaderTest getInstance() {
		return singleton;
	}

	public static void main(String[] args) {
		ClassLoaderTest singleton = ClassLoaderTest.getInstance();
		System.out.println("counter1 = " + singleton.counter1);
		System.out.println("counter2 = " + singleton.counter2);
		System.out.println("counter2 = " + ClassLoaderTest.counter2);
	}
}

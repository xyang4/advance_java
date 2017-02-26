package com.xyang;

import org.junit.Test;

class Parent {
	static String a = "Parent";

	public String getV() {
		return a;
	}
}

class Chile extends Parent {
	static String a = "Chile";
}

public class Demo {
	String str;
	@Test
	public void test_1() {
		Parent p = new Chile();
		System.out.println(p.getV());
	}
}

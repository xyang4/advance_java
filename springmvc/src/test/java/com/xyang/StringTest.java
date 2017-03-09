package com.xyang;

import org.junit.Test;

public class StringTest {
	@Test
	public void string_test1() {
		Child child = new Child();
		String s = child.getV();
		System.out.println(s);
	}

	@Test
	public void str_test2() {
		String s = new String("abc");
		String s1 = "abc";
		String s2 = new String("abc");
		System.out.println(s == s1);
		System.out.println(s == s2);
		System.out.println(s1 == s2);
		String hello = "hello";
		String hel = "hel";
		String lo = "lo";
		System.out.println(hello == "hel" + "lo");//true
		System.out.println(hello == hel + lo);//false
		System.out.println(hello == "hel" + lo);//false
	}
	@Test
	public void string_test() {
		SendValue sv = new SendValue();
		sv.change(sv.str);
		System.out.println(sv.str);
	}

	@Test
	public void stringBuffer_test() {
		StringBuffer a = new StringBuffer("A");
		StringBuffer b = new StringBuffer("B");
		operator(a, b);
		System.out.println(a + "," + b);
	}

	private void operator(StringBuffer x, StringBuffer y) {
		x.append(y);
		y = x;
	}
}

class SendValue {
	public String str = "6";

	public void change(String str) {
		str = "10";
	}
}

class _Parent {
	public static String s = "s";
	/*
	 * protected String getV() { return s; }
	 */

}

class Child extends _Parent {

	public static String s = "ss";

	public String getV() {
		return s;
	}
}

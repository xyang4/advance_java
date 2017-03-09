package com.xyang;

import org.junit.Test;

public class ExceptionTest {
	@Test
	public void test1() {
		int i = m();
		System.out.println(i);
		//2 4 4 4 
	}

	private int m() {
		int i = 5;
		try {
			i = 1 / 0;
			System.out.println(i);
			return i;
		} catch (RuntimeException ex) {
			i = 2;
			System.out.println(i);
			return i;
		} catch (Exception e) {
			i = 3;
			System.out.println(i);
			return i;
		} finally {
			i = 4;
			System.out.println(i);
		}
	}
}

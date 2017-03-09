package com.xyang;

import java.math.BigDecimal;

import org.junit.Test;

public class NumTest {
	private static int num1 = 1;
	private static int num2 = 3;

	@Test
	public void bigDecimalTest() {
		BigDecimal bd1 = new BigDecimal(num1);
		BigDecimal bd2 = new BigDecimal(num2);
		BigDecimal ret = bd1.add(bd2);
		System.out.println(ret.setScale(6,BigDecimal.ROUND_HALF_UP).doubleValue());
	}
}

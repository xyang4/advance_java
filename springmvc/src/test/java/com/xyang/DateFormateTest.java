package com.xyang;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormateTest {
	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println(Thread.currentThread().getName() + ":" + DateUtil.parse("2013-05-24 06:02:20"));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
}

class DateUtil {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String formatDate(Date date) throws ParseException {
		return sdf.format(date);
	}

	public static Date parse(String strDate) throws ParseException {
		return sdf.parse(strDate);
	}
}
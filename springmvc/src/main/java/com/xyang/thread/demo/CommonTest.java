package com.xyang.thread.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommonTest {
	private static List<String> list = new ArrayList<String>();
	static {
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
	}

	public static void main(String[] args) {
		sort(list);
	}
	private static <T extends Comparable<? super T>> void sort(List<T> list){
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			System.out.printf("## %d%n",i);
		}
		Collections.sort(list);
	}
}

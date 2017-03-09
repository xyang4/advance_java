package com.xyang;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @描述 需要循环数组结构的数据时，建议使用普通for循环，因为for循环采用下标访问，对于数组结构的数据来说，采用下标访问比较好。
 *     需要循环链表结构的数据时，一定不要使用普通for循环，这种做法很糟糕，数据量大的时候有可能会导致系统崩溃
 * @date 2017年3月4日-上午11:08:45
 * @author IBM
 *
 */
public class ItemTest {
	// 实例化arrayList
	private static List<Integer> arrayList = new ArrayList<Integer>();
	// 实例化linkList
	private static List<Integer> linkList = new LinkedList<Integer>();
	private static int itemNum = 10000000;

	public static void init() { // 插入10万条数据
		for (int i = 0; i < itemNum; i++) {
			arrayList.add(i);
			linkList.add(i);
		}
	}

	public static void main(String[] args) {
		init();
		// 1.用for循环arrayList
		for4ArrayList();

		// 2.用foreach循环arrayList
//		forEach4ArrayList();

		// 用for循环linkList
		// for4LinkList();

		// 用froeach循环linkList
		forEach4LinkList();

	}

	private static void for4ArrayList() {
		int array;
		long arrayForStartTime = System.currentTimeMillis();
		for (int i = 0; i < arrayList.size(); i++) {
			array = arrayList.get(i);
		}
		long arrayForEndTime = System.currentTimeMillis();
		System.out.println("用for循环arrayList 10万次花费时间：" + (arrayForEndTime - arrayForStartTime) + "毫秒");
	}

	private static void forEach4ArrayList() {
		int array;
		long arrayForeachStartTime = System.currentTimeMillis();
		for (Integer in : arrayList) {
			array = in;
		}
		long arrayForeachEndTime = System.currentTimeMillis();
		System.out.println("用foreach循环arrayList 10万次花费时间：" + (arrayForeachEndTime - arrayForeachStartTime) + "毫秒");
	}

	private static void for4LinkList() {
		long linkForStartTime = System.currentTimeMillis();
		int link = 0;
		for (int i = 0; i < linkList.size(); i++) {
			link = linkList.get(i);
		}
		long linkForEndTime = System.currentTimeMillis();
		System.out.println("用for循环linkList 10万次花费时间：" + (linkForEndTime - linkForStartTime) + "毫秒");
	}

	private static void forEach4LinkList() {
		int link;
		long linkForeachStartTime = System.currentTimeMillis();
		for (Integer in : linkList) {
			link = in;
		}
		long linkForeachEndTime = System.currentTimeMillis();
		System.out.println("用foreach循环linkList 10万次花费时间：" + (linkForeachEndTime - linkForeachStartTime) + "毫秒");
	}
}

package com.xyang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

public class CollectionsTest {
	@Test
	public void hashCodeTest() {

	}

	@Test
	public void arrayList_test() {
		List<Integer> arrayList = new ArrayList<>();
		int i = 1;
		arrayList.add(i++);
		System.out.println(arrayList);
	}

	@Test
	public void traversal_arrayList_test() {
		List<String> list = Arrays.asList("str1", "str2");// 这种方法生成的list，是不支持添加或删除元素的
		System.out.println(list.getClass().getSimpleName());
		// list.add("other");//java.lang.UnsupportedOperationException
		// 方法一
		Iterator<String> ite1 = list.iterator();
		while (ite1.hasNext()) {
			String str = ite1.next();
			System.out.println(str);
		}
		System.out.println("---------------------");
		// 方法二(方法一的变形)
		for (Iterator<String> ite2 = list.iterator(); ite2.hasNext();) {
			String str = ite2.next();
			System.out.println(str);
		}
		System.out.println("---------------------");
		// 方法三
		for (String s : list) {
			System.out.println(s);
		}
	}

	@Test
	public void linkedList_test() {
		List link = new LinkedList();
		link.add(123);
		link.add("lwc");
		link.add(8.8);
		link.add("nxj");
		link.add(520);
		printList(link);
		printReversedList(link);
	}

	@Test
	public void list_map_test() {
		Map<Integer, String> map1 = new HashMap<Integer, String>() {
			{
				put(new Integer(1), "lwc");
				put(new Integer(2), "nxj");
			}
		};
		Map<Integer, String> map2 = new HashMap<Integer, String>() {
			{
				put(new Integer(3), "tom");
				put(new Integer(4), "cat");
			}
		};
		List<Map<Integer, String>> list = new ArrayList<>();
		list.add(map1);
		list.add(map2);
		System.out.println(list);
		for (Iterator<Map<Integer, String>> ite2 = list.iterator(); ite2.hasNext();) {
			Map<Integer, String> m = ite2.next();
			System.out.println(m);
		}
		System.out.println("-----------------------------");
		// 方法三:
		for (Map<Integer, String> m : list) {
			System.out.println(m);
		}
	}

	@Test
	public void hashMap_test() {
		Map<Integer, String> map = new HashMap<>(2);
		map.put(1, "a");
		map.put(2, "b");
		map.put(2, "c");
		map.put(null, "d");
		Set<Integer> keys = map.keySet();
		System.out.println(keys);
	}

	@Test
	public void concurrentHashMap_test() {
		Map<Integer, String> chm = new ConcurrentHashMap<>();
		chm.put(1, "a");
		chm.put(2, "b");
		chm.put(2, "c");
		chm.put(null, "d");
		System.out.println(chm.keySet());

	}

	private static void printList(List link) {
		System.out.println("正序链表中的元素");
		// 的到链表的迭代器,位置指向链头
		ListIterator li = link.listIterator();
		// 判断迭代器中是否有下一个元素
		while (li.hasNext()) {
			// 返回下个元素
			System.out.print(li.next() + " ");
		}
		System.out.println();
	}

	private static void printReversedList(List link) {
		System.out.println("逆向链表中的元素");
		// 的到链表的迭代器,位置指向link.size()结尾
		ListIterator li = link.listIterator(link.size());
		// 判断迭代器中是否有前一个元素
		while (li.hasPrevious()) {
			// 返回前一个元素
			System.out.print(li.previous() + " ");
		}
		System.out.println();
	}

	@Test
	public void array2List_test() {
		String[] str = { "l", "w", "c" };
		// 使用Java类库中java.util.Arrays类的静态方法asList()
		List<String> l = Arrays.asList(str);
		System.out.println(str);
		System.out.println(l);
	}
}

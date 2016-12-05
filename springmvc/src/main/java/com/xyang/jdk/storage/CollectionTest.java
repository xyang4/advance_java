package com.xyang.jdk.storage;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class CollectionTest {
	@Test
	public void mapTest() {
		Map<String, Object> map = new HashMap<>();
		map.put("a","a");
		map.put("b","b");
		map.put("c","c");
		map.put("d","d");
		System.out.println(map);
		String v = (String) map.get("a");
		System.out.println(v);
	}
}

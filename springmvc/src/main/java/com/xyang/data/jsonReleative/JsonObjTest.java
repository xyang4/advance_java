package com.xyang.data.jsonReleative;

import org.json.JSONObject;

public class JsonObjTest {
	public static void main(String[] args) {
		JSONObject jsonObj = new JSONObject();
		for (int i = 0; i < 20;) {
			jsonObj.put("k" + i, "v" + i++);
		}
		String jsonStr = jsonObj.toString();
		System.out.println(jsonStr);
	}
}

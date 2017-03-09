package com.xyang.annotation;

import com.xyang.annotation.FruitColor.Color;

public class Apple {
	@FruitName(name = "Apple")
	private String appleName;
	
	@FruitColor(name = Color.RED)
	private String appleColor;
	
	@FruitProvider(id = 1, name = "陕西红富士集团", address = "陕西省西安市延安路89号红富士大厦")
	private String appleProvider;

	public void displayName() {
		System.out.println("水果的名字是：苹果");
	}
}

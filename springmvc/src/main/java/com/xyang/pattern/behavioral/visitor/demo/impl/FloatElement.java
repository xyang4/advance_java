package com.xyang.pattern.behavioral.visitor.demo.impl;

import com.xyang.pattern.behavioral.visitor.demo.Visitable;
import com.xyang.pattern.behavioral.visitor.demo.Visitor;

/**
 * @描述 ConcreteElement <br>
 *     实现Accept操作，该操作以一个访问者为参数。
 * 
 * @date 2017年2月7日-上午10:45:30
 * @author IBM
 *
 */
public class FloatElement implements Visitable {
	private Float fe;

	public FloatElement(Float fe) {
		this.fe = fe;
	}

	public Float getFe() {
		return this.fe;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitFloat(this);
	}
}

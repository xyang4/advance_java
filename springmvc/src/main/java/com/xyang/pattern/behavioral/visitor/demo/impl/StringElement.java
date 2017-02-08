package com.xyang.pattern.behavioral.visitor.demo.impl;

import com.xyang.pattern.behavioral.visitor.demo.Visitable;
import com.xyang.pattern.behavioral.visitor.demo.Visitor;

/**
 * @描述 ConcreteElement <br>
 *     实现每个由Visitor声明的操作。 每个操作实现本算法的一部分，而该算法片断乃是对应于结构中对象的类。
 *     ConcreteVisitor为该算法提供了上下文并存储它的局部状态。 这一状态常常在遍历该结构的过程中累积结果。
 * 
 * 
 * @date 2017年2月7日-上午10:45:30
 * @author IBM
 *
 */
public class StringElement implements Visitable {
	private String se;

	public StringElement(String se) {
		this.se = se;
	}

	public String getSe() {
		return this.se;
	}

	public void accept(Visitor visitor) {
		visitor.visitString(this);
	}

}

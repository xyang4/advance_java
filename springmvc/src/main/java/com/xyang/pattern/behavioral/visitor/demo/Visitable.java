package com.xyang.pattern.behavioral.visitor.demo;

/**
 * @描述 Element 定义一个Accept操作，它以一个访问者为参数。
 * 
 * @date 2017年2月7日-上午10:44:45
 * @author IBM
 *
 */
public interface Visitable {
	public void accept(Visitor visitor);
}

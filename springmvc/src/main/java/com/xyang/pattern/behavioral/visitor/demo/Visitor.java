package com.xyang.pattern.behavioral.visitor.demo;

import java.util.Collection;

import com.xyang.pattern.behavioral.visitor.demo.impl.FloatElement;
import com.xyang.pattern.behavioral.visitor.demo.impl.StringElement;

/**
 * @描述 Visitor <br>
 *     为ConcreteElement的每一个类声明一个Visit操作。 该操作的名字和特征标识了发送Visit请求给该访问者的那个类。
 *     这使得访问者可以确定正被访问元素的具体的类。 这样访问者就可以通过该元素的特定接口直接访问它。
 * 
 * @date 2017年2月7日-上午10:41:02
 * @author IBM
 *
 */
public interface Visitor {
	public void visitString(StringElement stringE);

	public void visitFloat(FloatElement floatE);

	public void visitCollection(Collection<?> collection);

}

package com.xyang.pattern.behavioral.visitor.demo;

import java.util.Collection;

import com.xyang.pattern.behavioral.visitor.demo.impl.FloatElement;
import com.xyang.pattern.behavioral.visitor.demo.impl.StringElement;

/**
 * @描述 Visitor 
 * @date 2017年2月7日-上午10:41:02
 * @author IBM
 *
 */
public interface Visitor {
	public void visitString(StringElement stringE);

	public void visitFloat(FloatElement floatE);

	public void visitCollection(Collection<?> collection);

}

package com.xyang.pattern.behavioral;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.xyang.pattern.behavioral.ChainOfResponsibility.demo.AddMoneyRequest;
import com.xyang.pattern.behavioral.ChainOfResponsibility.demo.DimissionRequest;
import com.xyang.pattern.behavioral.ChainOfResponsibility.demo.HRRequestHandle;
import com.xyang.pattern.behavioral.ChainOfResponsibility.demo.PMRequestHandle;
import com.xyang.pattern.behavioral.ChainOfResponsibility.demo.Request;
import com.xyang.pattern.behavioral.ChainOfResponsibility.demo.RequestHandle;
import com.xyang.pattern.behavioral.ChainOfResponsibility.demo.TLRequestHandle;
import com.xyang.pattern.behavioral.observer.demo.Citizen;
import com.xyang.pattern.behavioral.observer.demo.HuangPuCitizen;
import com.xyang.pattern.behavioral.observer.demo.Policeman;
import com.xyang.pattern.behavioral.observer.demo.TianHeCitizen;
import com.xyang.pattern.behavioral.observer.demo.impl.HuangPuPoliceman;
import com.xyang.pattern.behavioral.observer.demo.impl.TianHePoliceman;
import com.xyang.pattern.behavioral.visitor.demo.ConcreteVisitor;
import com.xyang.pattern.behavioral.visitor.demo.Visitable;
import com.xyang.pattern.behavioral.visitor.demo.Visitor;
import com.xyang.pattern.behavioral.visitor.demo.impl.FloatElement;
import com.xyang.pattern.behavioral.visitor.demo.impl.StringElement;

/**
 * @描述 设计模式-行为类型相关测试
 * @date 2017年2月9日-上午10:33:21
 * @author IBM
 *
 */
public class BehavioralTest {
	/**
	 * @描述 观察者模式<br>
	 *     定义对象间的一种一对多的依赖关系,当一个对象的状态发生改变时,所有依赖于它的对象都得到通知并被自动更新。
	 * 
	 * @date 2017年2月7日-上午10:34:57
	 */
	@Test
	public void observerTest() {
		Policeman thPol = new TianHePoliceman();
		Policeman hpPol = new HuangPuPoliceman();

		Citizen citizen = new HuangPuCitizen(hpPol);
		citizen.sendMessage("unnormal");
		citizen.sendMessage("normal");

		System.out.println("===========");

		citizen = new TianHeCitizen(thPol);
		citizen.sendMessage("normal");
		citizen.sendMessage("unnormal");

	}

	/**
	 * @描述 访问者模式 [ 解耦数据结构与算法 ]
	 *     <h2>预留通路，回调实现</h2>
	 * @date 2017年2月9日-上午11:06:48
	 */
	@Test
	public void vistorTest() {
		Visitor visitor = new ConcreteVisitor();
		Visitable se = new StringElement("abc");
		se.accept(visitor);
		Visitable fe = new FloatElement(new Float(1.5));
		fe.accept(visitor);
		System.out.println("===========================");
		List<Visitable> result = new ArrayList<>();
		result.add(new StringElement("a"));
		result.add(new StringElement("b"));
		result.add(new StringElement("c"));
		result.add(new FloatElement(new Float(0.5)));
		result.add(new FloatElement(new Float(1.5)));
		result.add(new FloatElement(new Float(2.5)));
		visitor.visitCollection(result);
	}

	/**
	 * @描述 责任链模式测试
	 *     <h2>分离职责，动态组合</h2>
	 * @date 2017年2月9日-上午10:59:55
	 */
	@Test
	public void chainOfResponsibilityTest() {
		RequestHandle hr = new HRRequestHandle();
		RequestHandle pm = new PMRequestHandle(hr);
		RequestHandle tl = new TLRequestHandle(pm);

		// team leader处理离职请求
		Request request = new DimissionRequest();
		tl.handleRequest(request);

		System.out.println("===========");
		// team leader处理加薪请求
		request = new AddMoneyRequest();
		tl.handleRequest(request);

		System.out.println("========");
		// 项目经理上理辞职请求
		request = new DimissionRequest();
		pm.handleRequest(request);
	}
}

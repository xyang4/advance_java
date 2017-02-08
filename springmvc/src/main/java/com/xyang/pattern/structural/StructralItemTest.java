package com.xyang.pattern.structural;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.xyang.pattern.behavioral.observer.demo.Citizen;
import com.xyang.pattern.behavioral.observer.demo.HuangPuCitizen;
import com.xyang.pattern.behavioral.observer.demo.Policeman;
import com.xyang.pattern.behavioral.observer.demo.TianHeCitizen;
import com.xyang.pattern.behavioral.observer.demo.impl.HuangPuPoliceman;
import com.xyang.pattern.behavioral.observer.demo.impl.TianHePoliceman;
import com.xyang.pattern.behavioral.visitor.demo.Visitor;
import com.xyang.pattern.behavioral.visitor.demo.impl.FloatElement;
import com.xyang.pattern.behavioral.visitor.demo.impl.StringElement;
import com.xyang.pattern.structural.adapter.demo.Adaptee;
import com.xyang.pattern.structural.adapter.demo.Target;
import com.xyang.pattern.structural.adapter.demo.impl.Adapter;
import com.xyang.pattern.structural.decorator.demo.ManDecoratorA;
import com.xyang.pattern.structural.decorator.demo.ManDecoratorB;
import com.xyang.pattern.structural.decorator.demo.impl.Man;
import com.xyang.pattern.structural.facade.demo.Facade;
import com.xyang.pattern.structural.facade.demo.ServiceA;
import com.xyang.pattern.structural.facade.demo.ServiceB;
import com.xyang.pattern.structural.facade.demo.impl.ServiceAImpl;
import com.xyang.pattern.structural.facade.demo.impl.ServiceBImpl;

/**
 * @描述 结构类型相测试
 * @date 2017年2月2日-下午7:21:02
 * @author IBM
 *
 */
public class StructralItemTest {
	/**
	 * 装饰模式
	 * 
	 * @描述 动态地给一个对象添加一些额外的职责。就增加功能来说，Decorator模式相比生成子类更为灵活。
	 * @date 2017年2月2日-下午7:29:01
	 */
	@Test
	public void decoratorTest() {
		Man man = new Man();
		ManDecoratorA md1 = new ManDecoratorA();
		ManDecoratorB md2 = new ManDecoratorB();

		md1.setPerson(man);
		md2.setPerson(md1);
		md2.eat();

	}

	/**
	 * @描述 适配器模式 将一个类的接口转换成客户希望的另外一个接口。Adapter模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。
	 * @date 2017年2月2日-下午7:45:53
	 */
	@Test
	public void adapterTest() {
		Target target = new Adapter(new Adaptee());
		target.adapteeMethod();

		target.adapterMethod();

	}

	/**
	 * @描述 外观模式 <br>
	 *     为子系统中的一组接口提供一个一致的界面，Facade模式定义了一个高层接口，这个接口使得这一子系统更加容易使用。
	 * @date 2017年2月6日-上午10:27:13
	 */
	@Test
	public void facadeTest() {
		ServiceA sa = new ServiceAImpl();
		ServiceB sb = new ServiceBImpl();
		sa.methodA();
		sb.methodB();
		System.out.println("========");
		// facade
		Facade facade = new Facade();
		facade.methodA();
		facade.methodB();
	}

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

	@Test
	public void vistorTest() {
		Visitor visitor = new ConcreteVisitor();
		StringElement se = new StringElement("abc");
		se.accept(visitor);

		FloatElement fe = new FloatElement(new Float(1.5));
		fe.accept(visitor);
		System.out.println("===========");
		List result = new ArrayList();
		result.add(new StringElement("abc"));
		result.add(new StringElement("abc"));
		result.add(new StringElement("abc"));
		result.add(new FloatElement(new Float(1.5)));
		result.add(new FloatElement(new Float(1.5)));
		result.add(new FloatElement(new Float(1.5)));
		visitor.visitCollection(result);

	}
}

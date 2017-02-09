package com.xyang.pattern.structural;

import org.junit.Test;

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
}

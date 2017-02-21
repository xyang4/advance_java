package com.xyang.pattern.creational;

import org.junit.Test;

import com.xyang.pattern.creational.abstractFactory.demo.IAnimalFactory;
import com.xyang.pattern.creational.abstractFactory.demo.ICat;
import com.xyang.pattern.creational.abstractFactory.demo.IDog;
import com.xyang.pattern.creational.abstractFactory.demo.impl.BlackAnimalFactoryImpl;
import com.xyang.pattern.creational.abstractFactory.demo.impl.WhiteAnimalFactoryImpl;
import com.xyang.pattern.creational.abstractFactory.order.DAOFactory;
import com.xyang.pattern.creational.abstractFactory.order.IOrderDetailDAO;
import com.xyang.pattern.creational.abstractFactory.order.IOrderMainDAO;
import com.xyang.pattern.creational.abstractFactory.order.RdbDAOFactory;
import com.xyang.pattern.creational.abstractFactory.order.XmlDAOFactory;
import com.xyang.pattern.creational.factoryMethod.demo.IWorkFactory;
import com.xyang.pattern.creational.factoryMethod.demo.impl.StudentWorkFactoryImpl;
import com.xyang.pattern.creational.factoryMethod.demo.impl.TeacherWorkFactoryImpl;
import com.xyang.pattern.creational.factoryMethod.export.Export2DBOperate;
import com.xyang.pattern.creational.factoryMethod.export.ExportOperate;
import com.xyang.pattern.creational.simplFactory.demo.Api;
import com.xyang.pattern.creational.simplFactory.demo.Factory;
import com.xyang.pattern.creational.singleton.demo.Singleton;

/**
 * @描述 创建型类型相关模式测试
 * @date 2017年1月29日-下午9:43:13
 * @author IBM
 *
 */
public class CreateiIemTest {

	/**
	 * 精髓： 完全封装、隔离选择实现
	 * 
	 * @描述 简单工厂
	 * @date 2017年1月29日-下午9:37:08
	 */
	@Test
	public void simpleFactoryTest() {
		Api api = Factory.createApi();
		api.operation(" hello world");
	}

	/**
	 * 精髓：1.延迟到子类选择实现
	 * 
	 * @描述 工厂方法模式： 定义一个用于创建对象的接口，让子类决定实例化哪一个类。FactoryMethod使一个类的实例化延迟到其子类。
	 * @date 2017年1月29日-下午9:02:45
	 */
	@Test
	public void factoryMethodTest1() {
		IWorkFactory studentWorkFactory = new StudentWorkFactoryImpl();
		studentWorkFactory.getWork().doWork();

		IWorkFactory teacherWorkFactory = new TeacherWorkFactoryImpl();
		teacherWorkFactory.getWork().doWork();

	}

	/**
	 * @描述 目的在于让父类在不知道具体实现的情况下完成自身功能的调用，具体实现延迟到子类中实现。
	 * @date 2017年1月30日-下午12:08:23
	 */
	@Test
	public void factoryMethodTest2() {
		ExportOperate operate = new Export2DBOperate();
		operate.export("nothing");
	}

	/**
	 * 精髓： 1.分离接口和实现 2.选择产品簇的实现
	 * 
	 * @描述 抽象工厂模式：提供一个创建一系列相关或相互依赖对象的接口，而无需指定它们具体的类。
	 * 
	 * @date 2017年1月29日-下午9:24:35
	 */
	@Test
	public void abstractFactory4AnimalTest() {
		IAnimalFactory blackAnimalFactory = new BlackAnimalFactoryImpl();
		ICat blackCat = blackAnimalFactory.createCat();
		blackCat.eat();
		IDog blackDog = blackAnimalFactory.createDog();
		blackDog.eat();

		IAnimalFactory whiteAnimalFactory = new WhiteAnimalFactoryImpl();
		ICat whiteCat = whiteAnimalFactory.createCat();
		whiteCat.eat();
		IDog whiteDog = whiteAnimalFactory.createDog();
		whiteDog.eat();

	}

	@Test
	public void abstractFactory4OrderTest() {
		DAOFactory df = new RdbDAOFactory();
		saveOrder(df);
		saveOrder(new XmlDAOFactory());

	}

	private void saveOrder(DAOFactory df) {
		IOrderMainDAO mainDAO = df.createOrderMainDAO();
		IOrderDetailDAO detailDAO = df.createOrderDetailDAO();
		mainDAO.saveOrderMain();
		detailDAO.saveOrderDetail();
	}

	/**
	 * @描述 单例模式测试
	 * @date 2017年2月9日-下午4:14:34
	 * @throws Exception
	 */
	@Test
	public void singletonTest() throws Exception {
		final int code = Singleton.getSynInstance2().hashCode();
		System.out.println(code);
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					int num = Singleton.getSynInstance2().hashCode();
					Object obj ;
					if (num != code) {
						System.out.println(num);
					}
				}
			}).start();
		}
	}
}

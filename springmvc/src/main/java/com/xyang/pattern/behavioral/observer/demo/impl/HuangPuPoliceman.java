package com.xyang.pattern.behavioral.observer.demo.impl;

import com.xyang.pattern.behavioral.observer.demo.Citizen;
import com.xyang.pattern.behavioral.observer.demo.Policeman;

/**
 * @描述 ConcreteObserver[具体观察者]<br>
 *     维护一个指向ConcreteSubject对象的引用。 存储有关状态，这些状态应与目标的状态保持一致。
 *     实现Observer的更新接口以使自身状态与目标的状态保持一致
 * 
 * @date 2017年2月7日-上午10:31:52
 * @author IBM
 *
 */
public class HuangPuPoliceman implements Policeman {

	@Override
	public void action(Citizen ci) {
		String help = ci.getHelp();
		if (help.equals("normal")) {
			System.out.println("一切正常, 不用出动");
		}
		if (help.equals("unnormal")) {
			System.out.println("有犯罪行为, 黄埔警察出动!");
		}
	}

}

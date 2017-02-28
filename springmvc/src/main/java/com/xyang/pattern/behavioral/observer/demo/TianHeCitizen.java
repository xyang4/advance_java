package com.xyang.pattern.behavioral.observer.demo;

/**
 * @描述 ConcreteSubject[具体目标] <br>
 *     将有关状态存入各ConcreteObserver对象。 当它的状态发生改变时,向它的各个观察者发出通知
 * @date 2017年2月7日-上午10:30:33
 * @author IBM
 *
 */
public class TianHeCitizen extends Citizen {

	public TianHeCitizen(Policeman pol) {
		setPolicemen();
		register(pol);
	}
	@Override
	public void sendMessage(String help) {
		setHelp(help);
		for (int i = 0; i < pols.size(); i++) {
			Policeman pol = pols.get(i);
			// 通知警察行动
			pol.action(this);
		}
	}

}

package com.xyang.pattern.behavioral.observer.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述 Subject 目标知道它的观察者。可以有任意多个观察者观察同一个目标。 提供注册和删除观察者对象的接口。
 * 
 * @date 2017年2月7日-上午10:26:58
 * @author IBM
 *
 */
public abstract class Citizen {
	List<Policeman> pols;

	String help = "normal";

	public abstract void sendMessage(String help);

	public void setHelp(String help) {
		this.help = help;
	}

	public String getHelp() {
		return this.help;
	}

	public void setPolicemen() {
		this.pols = new ArrayList<>();
	}

	public void register(Policeman pol) {
		this.pols.add(pol);
	}

	public void unRegister(Policeman pol) {
		this.pols.remove(pol);
	}

}

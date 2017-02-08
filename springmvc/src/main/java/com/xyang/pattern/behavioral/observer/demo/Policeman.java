package com.xyang.pattern.behavioral.observer.demo;

/**
 * @描述 Observer 为那些在目标发生改变时需获得通知的对象定义一个更新接口。
 * 
 * @date 2017年2月7日-上午10:27:50
 * @author IBM
 *
 */
public interface Policeman {
	void action(Citizen ci);

}

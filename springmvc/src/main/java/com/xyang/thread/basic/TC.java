package com.xyang.thread.basic;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class TC {
	private LinkedList<Object> list = new LinkedList<>();//存储元素
	private AtomicInteger count = new AtomicInteger(0);//计数
	private final int minSize=0;
	private final int maxSize;
	
	public TC(int maxSize){
		this.maxSize = maxSize;
	}
	private final Object lock = new Object();
	public void add(Object obj){
		synchronized(lock){
			while(count.get()==maxSize){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			list.add(obj);
			count.incrementAndGet();
			lock.notify();
		}
	}
	public Object take() throws InterruptedException{
		Object res = null;
		synchronized(lock){
			while(count.get()==minSize){
				lock.wait();
			}
			res = list.removeFirst();
			System.out.println("当前容量:"+count.decrementAndGet());
			lock.notify();
		}
		return res;
	}
	public int getSize(){
		return count.get();
	}
}

package com.xyang.thread.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class Temp extends Thread {
	@Override
	public void run() {
		System.out.println("run");
	}
}

public class ScheduledJob {

	public static void main(String args[]) throws Exception {

		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

		ScheduledFuture<?> scheduleTask = scheduler.scheduleWithFixedDelay(new Temp(), 5, 1, TimeUnit.SECONDS);
		System.out.println(scheduleTask.get().toString());

	}
}
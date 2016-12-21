package com.xyang.jdk.jvm;

import org.junit.Test;

public class JVMTest {
	
	@Test 
	public void onStackTest() {
		long b=System.currentTimeMillis();
        for(int i=0;i<100000000;i++){
            alloc();
        }
        long e=System.currentTimeMillis();
        System.out.println(e-b);
	}
	public static void alloc(){
        byte[] b=new byte[2];
        b[0]=1;
    }
}

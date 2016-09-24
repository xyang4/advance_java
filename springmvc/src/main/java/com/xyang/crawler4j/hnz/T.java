package com.xyang.crawler4j.hnz;

import java.io.UnsupportedEncodingException;

import com.xyang.utils.CodingUtils;

public class T {
	public static void main(String[] args) throws UnsupportedEncodingException {

		String word = "山西";
        String retValue = CodingUtils.toBrowserCode(word, null);
        System.out.print(retValue);
	}
}

class CodeConverter {

}

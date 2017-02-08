package com.xyang.pattern.creational.factoryMethod.export.impl;

import com.xyang.pattern.creational.factoryMethod.export.IExportFile;

public class Export2TextFileImpl implements IExportFile {

	@Override
	public boolean export(String data) {
		System.out.printf("到处数据 %s 到文本文件中", data);
		return true;
	}

}

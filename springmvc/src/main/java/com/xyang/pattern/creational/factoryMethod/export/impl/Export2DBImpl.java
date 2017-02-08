package com.xyang.pattern.creational.factoryMethod.export.impl;

import com.xyang.pattern.creational.factoryMethod.export.IExportFile;

public class Export2DBImpl implements IExportFile {

	@Override
	public boolean export(String data) {
		System.out.printf("导出数据 %s 到数据库中", data);
		return true;
	}

}

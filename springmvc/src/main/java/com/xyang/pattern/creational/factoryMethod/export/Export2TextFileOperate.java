package com.xyang.pattern.creational.factoryMethod.export;

import com.xyang.pattern.creational.factoryMethod.export.impl.Export2TextFileImpl;

public class Export2TextFileOperate extends ExportOperate {

	@Override
	protected IExportFile factoryMethod() {
		return new Export2TextFileImpl();
	}

}

package com.xyang.pattern.creational.factoryMethod.export;

import com.xyang.pattern.creational.factoryMethod.export.impl.Export2DBImpl;

public class Export2DBOperate extends ExportOperate{

	@Override
	protected IExportFile factoryMethod() {
		return new Export2DBImpl();
	}

}

package com.xyang.pattern.creational.factoryMethod.export;

public abstract class ExportOperate {
	public boolean export(String data) {
		IExportFile api = factoryMethod();
		return api.export(data);
	}

	/**
	 * @描述
	 *     <h1 style="color:red;">工厂方法</h1> 创建导出的文件对象的接口对象
	 * @date 2017年1月30日-上午11:19:14
	 * @return
	 */
	protected abstract IExportFile factoryMethod();
}

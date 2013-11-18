package com.wisbook.hibernate3.inject;
/**
 * 注入者规范
 * @author volador
 *
 */
public interface Injector {
	/**
	 * 处理对象的注入
	 * @param o
	 */
	void inject(Object o);
}

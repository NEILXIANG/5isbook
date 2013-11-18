package com.wisbook.hibernate3.inject;

import java.util.ArrayList; 
import java.util.List;
/**
 * 注入者容器，hold住所有的注入者，所有的注入者在spring ioc中进行注入
 * @author volador
 *
 */
public class InjectorHolder {
	private List<Injector> injectors = new ArrayList<Injector>();
	
	public List<Injector> getInjectors() {
		return injectors;
	}
	
	public void setInjectors(List<Injector> injectors) {
		this.injectors = injectors;
	} 
}

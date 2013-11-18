package com.wisbook.util.springutil;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring容器启动时保存ApplicationContext，方便其他类使用 <br>
 * 在spring容器启动的时候，就会为这个单例工具注入ApplicationContext
 * @author volador
 *
 */
public class ApplicationContextUtil implements ApplicationContextAware{
	private static ApplicationContext instance;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		instance = applicationContext;
	} 
	
	public static ApplicationContext getApplicatoinContext(){
		return instance;
	}
}

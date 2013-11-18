package com.wisbook.hibernate3.inject.injectors;

import org.springframework.beans.factory.InitializingBean; 

import com.wisbook.dao.order.EntityTypeDao;
import com.wisbook.hibernate3.aware.EntityTypeDaoAware;
import com.wisbook.hibernate3.inject.AbstractInjector;
/**
 * 注入者：给Entity注入EntityDao
 * @author volador
 *
 */
public class EntityTypeDaoInjector extends AbstractInjector implements InitializingBean{

	private EntityTypeDao entityTypeDao;
	
	@Override
	public void inject(Object o) {
		if(o instanceof EntityTypeDaoAware){
			EntityTypeDaoAware ea = (EntityTypeDaoAware)o;
			ea.setEntityTypeDao(entityTypeDao);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.entityTypeDao = (EntityTypeDao) this.getApplicationContext().getBean("entityTypeDao");
	}
}

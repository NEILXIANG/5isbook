package com.wisbook.hibernate3.inject.injectors;

import org.springframework.beans.factory.InitializingBean;
import com.wisbook.dao.cms.EntityValueDao;
import com.wisbook.hibernate3.aware.EntityValueDaoAware;
import com.wisbook.hibernate3.inject.AbstractInjector;
/**
 * 注入者：给Entity注入EntityValueDao
 * @author volador
 *
 */
public class EntityValueDaoInjector extends AbstractInjector implements InitializingBean{

	private EntityValueDao entityValueDao;

	@Override
	public void inject(Object o) {
		if(o instanceof EntityValueDaoAware){
			EntityValueDaoAware evda = (EntityValueDaoAware)o;
			evda.setEntityValueDao(this.entityValueDao);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.entityValueDao = (EntityValueDao) this.getApplicationContext().getBean("entityValueDao");
	}
}

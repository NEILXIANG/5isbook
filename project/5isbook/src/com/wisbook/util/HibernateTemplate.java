package com.wisbook.util;

import org.hibernate.Query;
/**
 * 开放query接口的工具
 * @author volador
 *
 */
public class HibernateTemplate extends org.springframework.orm.hibernate3.HibernateTemplate{
	public Query createQuery(String hql){
		return this.getSession().createQuery(hql);
	}
	
}

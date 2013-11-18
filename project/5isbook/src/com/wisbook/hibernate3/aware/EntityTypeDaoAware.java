package com.wisbook.hibernate3.aware;

import com.wisbook.dao.order.EntityTypeDao;

/**
 * 该接口用于model的依赖注入,注入的时机是：存在一个hibernate load的监听器，
 * 每当hibernate从数据库中load一个model出来的时候，监听器会检测model是否实现该接口，
 * 若是实现，进行相关的依赖注入
 * @see com.wisbook.hibernate3.aware.listener.DaoAwareListener
 * @author volador
 *
 */
public interface EntityTypeDaoAware {
	/**
	 * 注入EntityTypeDao
	 * @param entityTypeDao
	 */
	void setEntityTypeDao(EntityTypeDao entityTypeDao);
}
 
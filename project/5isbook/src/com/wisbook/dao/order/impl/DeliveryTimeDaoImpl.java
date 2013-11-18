package com.wisbook.dao.order.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.wisbook.dao.base.AbstractBaseDao;
import com.wisbook.dao.order.DeliveryTimeDao;
import com.wisbook.model.order.DeliveryTime;

@Component("deliveryTimeDao")
public class DeliveryTimeDaoImpl extends AbstractBaseDao<DeliveryTime,Integer> implements DeliveryTimeDao{

	@Override
	protected Class<DeliveryTime> getEntityClazz() {
		return DeliveryTime.class;
	}

	@Override
	public List<DeliveryTime> fetchAllRecord() {
		String HQL = "from DeliveryTime";
		@SuppressWarnings("unchecked")
		List<DeliveryTime> list = this.getHibernateTemplate().createQuery(HQL).list();
		return list;
	}

}

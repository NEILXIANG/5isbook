package com.wisbook.dao.order.impl;

import org.springframework.stereotype.Component;

import com.wisbook.dao.base.AbstractBaseDao;
import com.wisbook.model.order.OrderItem;
import com.wisbook.model.order.OrderItemPK;

@Component("orderItemDao")
public class OrderItemDaoImpl extends AbstractBaseDao<OrderItem,OrderItemPK>{

	@Override
	protected Class<OrderItem> getEntityClazz() {
		return OrderItem.class;
	}

}

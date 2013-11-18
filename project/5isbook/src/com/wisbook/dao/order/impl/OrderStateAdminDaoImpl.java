package com.wisbook.dao.order.impl;

import org.springframework.stereotype.Component;

import com.wisbook.dao.base.AbstractBaseDao;
import com.wisbook.model.order.OrderStateAdmin;
import com.wisbook.model.order.OrderStateAdminPK;
@Component("orderStateAdminDao")
public class OrderStateAdminDaoImpl extends AbstractBaseDao<OrderStateAdmin,OrderStateAdminPK>{

	@Override
	protected Class<OrderStateAdmin> getEntityClazz() {
		return OrderStateAdmin.class;
	}

}

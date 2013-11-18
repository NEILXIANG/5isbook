package com.wisbook.dao.order.impl;

import org.springframework.stereotype.Component;

import com.wisbook.dao.base.AbstractBaseDao;
import com.wisbook.dao.order.OrderStateDao;
import com.wisbook.model.order.OrderState;
import com.wisbook.model.order.OrderStateEnum;
@Component("orderStateDao")
public class OrderStateDaoImpl extends AbstractBaseDao<OrderState,Integer> implements OrderStateDao{

	@Override
	protected Class<OrderState> getEntityClazz() {
		return OrderState.class;
	}

	@Override
	public OrderState getOrderState(OrderStateEnum state) {
		String HQL = "from OrderState os where os.state=:state";
		OrderState os = (OrderState) this.getHibernateTemplate().createQuery(HQL)
							.setParameter("state",state)
							.uniqueResult();
		return os;
	}

}

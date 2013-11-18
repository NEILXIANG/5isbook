package com.wisbook.dao.order.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.wisbook.dao.base.AbstractBaseDao;
import com.wisbook.dao.order.OrderDao;
import com.wisbook.model.order.Order;
import com.wisbook.util.PageBean;
@Component("orderDao")
public class OrderDaoImpl extends AbstractBaseDao<Order,Integer> implements OrderDao{

	@Override
	protected Class<Order> getEntityClazz() {
		return Order.class;
	}

	@Override
	public Order getOrderByAccount(String account, int orderId) {
		String HQL = "from Order o where o.user.account=:account and o.id=:orderId";
		Order order = (Order) this.getHibernateTemplate().createQuery(HQL)
						.setString("account",account)
						.setInteger("orderId",orderId)
						.uniqueResult();
		return order;
	}
	
	@Override
	public PageBean<Order> getOrdersByAccount(String account,int pageIndex,int pageSize) {
		String HQL = "from Order o where o.user.account=:account";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("account", account);
		return fetchEntityByPageHQL(HQL, pageIndex, pageSize,params);
	}
}

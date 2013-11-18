package com.wisbook.action.configuration;

import javax.annotation.Resource;

import com.wisbook.dao.base.BaseDao;
import com.wisbook.dao.order.OrderStateDao;
import com.wisbook.model.order.OrderState;
import com.wisbook.model.order.OrderStateEnum;
import com.wisbook.util.BasicTestCase;
import com.wisbook.util.HibernateTemplate;
import com.wisbook.util.PageBean;

public class OrderStateTest extends BasicTestCase{
	private OrderStateAction action;
	private OrderStateDao orderStateDao;
	private HibernateTemplate hibernateTemplate;
	
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void setOrderStateDao(OrderStateDao orderStateDao) {
		this.orderStateDao = orderStateDao;
	}

	@Resource(name="orderState")
	public void setAction(OrderStateAction action) {
		this.action = action;
	}


	public void testExecute() throws Exception{
		this.setDefaultRollback(false);
		System.out.println("action execute result:"+action.execute());
	}
	
	public void testGet(){
		String HQL = "from OrderState";
		PageBean<OrderState> list = ((BaseDao<OrderState,Integer>)this.orderStateDao).fetchEntityByPageHQL(HQL, 1,1);
		System.out.println(list.getTotalPage());
	}
	
}

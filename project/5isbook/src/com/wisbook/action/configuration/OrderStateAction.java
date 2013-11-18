package com.wisbook.action.configuration;


import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.wisbook.dao.base.BaseDao;
import com.wisbook.model.order.OrderState;
import com.wisbook.model.order.OrderStateEnum;
@Component("orderState")
@Scope("prototype")
public class OrderStateAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BaseDao<OrderState,Integer> orderStateDao;
	
	public BaseDao<OrderState, Integer> getOrderStateDao() {
		return orderStateDao;
	}

	@Resource(name="orderStateDao")
	public void setOrderStateDao(BaseDao<OrderState, Integer> orderStateDao) {
		this.orderStateDao = orderStateDao;
	}
	
	public String execute(){
		OrderState os1 = new OrderState();
		OrderState os2 = new OrderState();
		OrderState os3 = new OrderState();
		OrderState os4 = new OrderState();
		OrderState os5 = new OrderState();
		
		os1.setState(OrderStateEnum.COMMITTED);
		os1.setNextState(os2);
		os2.setState(OrderStateEnum.DISPOSED);
		os2.setNextState(os3);
		os3.setState(OrderStateEnum.OUT_STORAGE);
		os3.setNextState(os4);
		os4.setState(OrderStateEnum.RECEIVED);
		os5.setState(OrderStateEnum.CANCEL);
		
		this.orderStateDao.saveEntity(os1);
		this.orderStateDao.saveEntity(os2);
		this.orderStateDao.saveEntity(os3);
		this.orderStateDao.saveEntity(os4);
		this.orderStateDao.saveEntity(os5);
		return Action.SUCCESS;
	}
	
}

package com.wisbook.action.user;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.wisbook.model.order.Order;
import com.wisbook.service.order.OrderManager;
/**
 * 查看订单详细
 * @author volador
 *
 */
@Component("com.wisbook.action.user.orderView")
@Scope("prototype")
public class OrderView extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private OrderManager orderManager;
	private Map<String,Object> session;
	
	private int id;
	private Order order;
	
	@Override
	public String execute() throws Exception {
		if(id <= 0) return Action.ERROR;
		String account = (String) session.get("ACEGI_SECURITY_LAST_USERNAME");
		order = orderManager.getOrderById(id);
		if(!order.getUser().getAccount().equals(account)) return Action.ERROR;
		return Action.SUCCESS;
	}
	
	@Resource(name="orderManager")
	public void setOrderManager(OrderManager orderManager) {
		this.orderManager = orderManager;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Order getOrder() {
		return order;
	}

	public void setId(int id) {
		this.id = id;
	}

}

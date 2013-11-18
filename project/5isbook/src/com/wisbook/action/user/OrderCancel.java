package com.wisbook.action.user;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.wisbook.service.order.OrderManager;
/**
 * 用户取消订单
 * @author volador
 *
 */
@Component("com.wisbook.action.user.orderCancel")
@Scope("prototype")
public class OrderCancel extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 1L;

	private int id; //取消订单的id
	
	private Map<String,Object> session;
	private OrderManager manager;
	
	@Override
	public String execute() throws Exception {
		String account = (String) session.get("ACEGI_SECURITY_LAST_USERNAME");
		if(id == 0) return Action.ERROR;
		boolean result = manager.cancelOrderByUser(account, id);
		if(!result) return Action.ERROR;
		return Action.SUCCESS;
	}
	
	public void setId(int id){
		this.id = id;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	@Resource(name="orderManager")
	public void setOrderManager(OrderManager orderManager){
		this.manager = orderManager;
	}
}

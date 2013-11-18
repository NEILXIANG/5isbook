package com.wisbook.action.cart;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.wisbook.model.order.Order;
import com.wisbook.service.cart.Cart;
import com.wisbook.service.order.OrderManager;
/**
 * 产生订单信息
 * @author volador
 *
 */
@Component("com.wisbook.action.cart.buyAction")
@Scope("prototype")
public class BuyAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	private int addressId;
	private int deliveryTimeId;
	
	private Order order;
	
	private Map<String,Object> session;
	private OrderManager orderManager;
	
	@Override
	public String execute() throws Exception {
		Cart cart = (Cart) session.get("cart");
		deliveryTimeId = deliveryTimeId <= 0 ? 1 : deliveryTimeId;
		if(addressId <= 0) return "null_address"; //地址不可用
		if(cart.isEmpty()){
			System.out.println("cart is null");
			return "cart_null";
		}
		String account = (String) session.get("ACEGI_SECURITY_LAST_USERNAME");
		order = this.orderManager.addOrder(cart, deliveryTimeId, account, addressId);
		session.remove("cart");
		return Action.SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Resource(name="orderManager")
	public void setOrderManager(OrderManager orderManager) {
		this.orderManager = orderManager;
	}

	public Order getOrder() {
		return order;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public void setDeliveryTimeId(int deliveryTimeId) {
		this.deliveryTimeId = deliveryTimeId;
	}
}

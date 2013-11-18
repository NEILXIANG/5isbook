package com.wisbook.action.cart;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.wisbook.model.order.DeliveryTime;
import com.wisbook.model.user.Address;
import com.wisbook.model.user.User;
import com.wisbook.service.cart.Cart;
import com.wisbook.service.order.OrderManager;
import com.wisbook.service.user.UserService;

/**
 * 结算页
 * @author volador
 *
 */
@Component("com.wisbook.action.cart.checkoutAction")
@Scope("prototype")
public class CheckoutAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	private Map<String,Object> session;
	private UserService userService;
	private OrderManager orderManager;
	private Cart cart;
	private Set<Address> addresses; //当前用户所有地址
	private List<DeliveryTime> deliveryTimes; //所有可以用的配送地址
	
	private String message = ""; //页面显示的信息
	
	@Override
	public String execute() throws Exception {
		this.cart = (Cart) this.session.get("cart");
		if(this.cart == null || this.cart.isEmpty()) return "empty";
		String account = (String) session.get("ACEGI_SECURITY_LAST_USERNAME");
		User user = this.userService.getUserByAccount(account, account);
		addresses = user.getAddresses();
		if(addresses == null || addresses.size() <= 0){
			message = "您还没登记个人配送地址哦，点击下面链接去完善个人配送地址吧，您的购物车信息我们会为您保存下来的。";
			return "null_address";
		}
		this.deliveryTimes = this.orderManager.getAllDeliveryTimes();
		return Action.SUCCESS;
	}
	
	
	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Resource(name="orderManager")
	public void setOrderManager(OrderManager orderManager) {
		this.orderManager = orderManager;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public String getMessage() {
		return message;
	}


	public Cart getCart() {
		return cart;
	}


	public Set<Address> getAddresses() {
		return addresses;
	}


	public List<DeliveryTime> getDeliveryTimes() {
		return deliveryTimes;
	}

}

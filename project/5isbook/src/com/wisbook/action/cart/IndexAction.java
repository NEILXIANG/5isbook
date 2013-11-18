package com.wisbook.action.cart;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.wisbook.service.cart.Cart;

/**
 * 购物车首页
 * 
 * @author volador
 * 
 */
@Component("com.wisbook.action.cart.indexAction")
@Scope("prototype")
public class IndexAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cart cart = null;
	private Map<String, Object> session;

	@Override
	public String execute() throws Exception {
		this.cart = (Cart) this.session.get("cart");
		return Action.SUCCESS;
	}

	public Cart getCart() {
		return cart;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}

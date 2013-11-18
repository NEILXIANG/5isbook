package com.wisbook.action.cart;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.wisbook.service.cart.Cart;
import com.wisbook.service.cart.CartManager;

/**
 * 负责删除跟清除工作
 * @author volador
 *
 */
@Component("com.wisbook.action.cart.deleteAndClearAction")
@Scope("prototype")
public class DeleteAndClearAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id; //需要删除的id
	private CartManager cartManager;
	private Map<String,Object> session;
	
	public String delete(){
		if(this.id == 0) return Action.ERROR;
		Cart cart = (Cart) this.session.get("cart");
		this.cartManager.deleteItem(cart, this.id);
		return Action.SUCCESS;
	}
	
	public String clear(){
		Cart cart = (Cart) this.session.get("cart");
		if(cart != null && !cart.isEmpty()) this.cartManager.clear(cart);
		return Action.SUCCESS;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Resource(name="cartManager")
	public void setCartManager(CartManager cartManager) {
		this.cartManager = cartManager;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	
}

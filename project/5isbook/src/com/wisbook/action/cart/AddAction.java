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
 * 添加购物车项
 * @author volador
 *
 */
/**
 * @author volador
 *
 */
@Component("com.wisbook.action.cart.addAction")
@Scope("prototype")
public class AddAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CartManager cartManager;
	private String goodsType; //购买种类
	private int buyNum; //购买数量
	private int id;	//商品id
	
	private Map<String,Object> session;
	private boolean flag = false;
	
	@Override
	public String execute() throws Exception {
		if(this.id <= 0) return Action.ERROR;
		Cart cart = (Cart) this.session.get("cart");
		if(cart == null){
			flag = true;
		}
		cart = this.cartManager.addItem(cart, id, this.goodsType,this.buyNum);
		if(flag){
			this.session.put("cart",cart);
		}
		return Action.SUCCESS;
	}
	
	@Override
	public void validate() {
		this.buyNum = this.buyNum <= 0?1:this.buyNum;
	}

	@Resource(name="cartManager")
	public void setCartManager(CartManager cartManager) {
		this.cartManager = cartManager;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}

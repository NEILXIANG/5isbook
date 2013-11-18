package com.wisbook.service.impl;

import java.math.BigDecimal;
import java.util.Iterator;

import javax.annotation.Resource;

import com.wisbook.model.order.Order;
import com.wisbook.model.order.OrderStateEnum;
import com.wisbook.service.cart.Cart;
import com.wisbook.service.cart.CartItem;
import com.wisbook.service.cart.CartManager;
import com.wisbook.service.order.OrderManager;
import com.wisbook.util.BasicTestCase;
import com.wisbook.util.PageBean;

public class OrderTest extends BasicTestCase {

	private OrderManager orderManager;
	private CartManager cartManager;
	private Cart cart;

	public CartManager getCartManager() {
		return cartManager;
	}

	@Resource(name = "cartManager")
	public void setCartManager(CartManager cartManager) {
		this.cartManager = cartManager;
	}

	public OrderManager getOrderManager() {
		return orderManager;
	}

	@Resource(name = "orderManager")
	public void setOrderManager(OrderManager orderManager) {
		this.orderManager = orderManager;
	}

	public void testGetByState() {
		PageBean<Order> es = this.orderManager.getOrdersByState(
				OrderStateEnum.CANCEL, 1, 9);
		System.out.println(es);
	}

	public void testInsert() {
		this.setDefaultRollback(false);
		for (int i = 1; i <= 20; i++) {
			cart= cartManager.addItem(cart, 14, "authorised_edition", 10);
			orderManager.addOrder(cart, 2, "fuck@qq.com", 606);
		}
	}
}
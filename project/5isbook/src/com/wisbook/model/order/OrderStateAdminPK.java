package com.wisbook.model.order;

import java.io.Serializable;  

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Embeddable
public class OrderStateAdminPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//状态属于该订单
	private Order order;
	//当前状态
	private OrderState orderState;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="order_id",nullable=false)
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="order_state_id",nullable=false)
	public OrderState getOrderState() {
		return orderState;
	}
	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + (order == null?0:order.hashCode());
		result = 37 * result + (orderState == null?0:orderState.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof OrderStateAdminPK)) return false;
		OrderStateAdminPK osa = (OrderStateAdminPK)obj;
		return 
				order.getId() == osa.getOrder().getId() &&
				orderState.getId() == osa.getOrderState().getId();
	}
}

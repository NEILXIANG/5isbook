package com.wisbook.model.order;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 定义订单状态
 * 	订单主要有几种状态：（已提交，该状态下用户可以取消）（已处理）（已出库）（已收货）（已取消）
 * @author volador
 *
 */
@Entity
@Table(name="order_state")
public class OrderState {
	private int id;
	//状态枚举
	private OrderStateEnum state;
	//下一个状态
	private OrderState nextState;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="next_state_id")
	public OrderState getNextState() {
		return nextState;
	}
	public void setNextState(OrderState nextState) {
		this.nextState = nextState;
	}
	//判断能否给用户cancel掉,注意，只有后台没处理过该订单，用户才能取消
	public boolean canBeCancelByUser(){
		if(state == OrderStateEnum.COMMITTED)
			return true;
		return false;
	}
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Enumerated(EnumType.STRING)
	public OrderStateEnum getState() {
		return state;
	}
	public void setState(OrderStateEnum state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return state.toString();
	}
}

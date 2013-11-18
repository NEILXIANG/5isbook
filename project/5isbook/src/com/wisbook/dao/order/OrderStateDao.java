package com.wisbook.dao.order;

import com.wisbook.model.order.OrderState;
import com.wisbook.model.order.OrderStateEnum;

public interface OrderStateDao{
	/**
	 * 根据state获取记录
	 * @param state
	 * @return
	 */
	OrderState getOrderState(OrderStateEnum state);
}

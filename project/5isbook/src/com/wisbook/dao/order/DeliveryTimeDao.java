package com.wisbook.dao.order;

import java.util.List;

import com.wisbook.model.order.DeliveryTime;

public interface DeliveryTimeDao{
	/**
	 * 获取数据库中所有的记录
	 * @return 配送时间列表
	 */
	List<DeliveryTime> fetchAllRecord();
}

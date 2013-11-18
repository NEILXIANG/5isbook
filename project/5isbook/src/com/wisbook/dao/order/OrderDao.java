package com.wisbook.dao.order;

import com.wisbook.model.order.Order;
import com.wisbook.util.PageBean;
/**
 * 订单操作DAO
 * @author volador
 *
 */
public interface OrderDao {
	/**
	 * 获取某用户的order[以用户账号限定该订单是该用户的]
	 * @param account 用户账号
	 * @param orderId 订单id
	 * @return 订单记录
	 */
	Order getOrderByAccount(String account,int orderId);
	/**
	 * 拿到用户全部订单
	 * @param account 用户账号
	 * @param pageIndex 当前页
	 * @param pageSize 每页大小
	 * @return 订单记录
	 */
	PageBean<Order> getOrdersByAccount(String account,int pageIndex,int pageSize);
}

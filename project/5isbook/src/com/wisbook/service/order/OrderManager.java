package com.wisbook.service.order;

import java.util.List;

import com.wisbook.model.order.DeliveryTime;
import com.wisbook.model.order.Order; 
import com.wisbook.model.order.OrderStateEnum;
import com.wisbook.service.cart.Cart;
import com.wisbook.util.PageBean;

public interface OrderManager {
	/**
	 * 获取系统中全部的配送时间记录
	 * @return 配送时间列表
	 */
	List<DeliveryTime> getAllDeliveryTimes();
	/**
	 * 根据订单的状态拿到数量信息，默认关注该状态下的订单
	 * @param state 状态枚举
	 * @return 数量
	 */
	long getOrderCountByState(OrderStateEnum state);
	/**
	 * 拿到系统全部的订单
	 * @return 系统全部订单数量
	 */
	long getAllOrderCount();
	/**
	 * 根据订单的状态拿到数量
	 * @param state 状态枚举
	 * @param mask 关注于该状态还是非改状态的订单
	 * @return
	 */
	long getOrderCountByState(OrderStateEnum state,StateMark mark);
	/**
	 * 根据id拿到订单信息
	 * @param id 订单id
	 * @return
	 */
	Order getOrderById(int id);
	/**
	 * 分页获得某个状态下面的所有订单信息
	 * @param state
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	PageBean<Order> getOrdersByState(OrderStateEnum state,int pageIndex,int pageSize);
	/**
	 * 
	 * @param state
	 * @param mark
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	PageBean<Order> getOrdersByState(OrderStateEnum state,StateMark mark,int pageIndex,int pageSize);
	/**
	 * 列举当前用户的订单
	 * @param currentAccount 当前登录用户
	 * @param state 筛选的订单状态
	 * @return 订单列表
	 */
	PageBean<Order> getOrdersByAccountAndState(String currentAccount,OrderStateEnum state,int pageIndex,int pageSize);
	/**
	 * 
	 * @param currentAccount
	 * @param states 将所有条件关联起来，进行or操作
	 * @param mark
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	PageBean<Order> getOrdersByAccountAndState(String currentAccount,StateMark mark,int pageIndex,int pageSize,OrderStateEnum...states);
	/**
	 * 获取用户全部订单记录
	 * @param currentAccount 当前登录用户
	 * @param pageIndex 当前页
	 * @param PageSize 每页几条
	 * @return 订单分页实体
	 */
	PageBean<Order> getAllOrdersByAccount(String currentAccount,int pageIndex,int pageSize);
	/**
	 * 把订单推进到下一个状态[正常情况],并记录下操作记录
	 * @param userAccount 处理订单的用户account
	 * @param orderId 订单id
	 * @return 成功/失败 失败的原因可能是订单已经处于最终态了
	 */
	boolean processToNextState(String userAccount,int orderId);
	/**
	 * 用户关闭订单,注意处理用户是否关闭的是自己的订单,用户的操作记录并没有记录下来,注意修改商品数量
	 * @param userAccount 用户账号
	 * @param orderId 订单id
	 * @return 成功/失败
	*/
	boolean cancelOrderByUser(String userAccount,int orderId);
	/**
	 * 管理员关闭订单,管理员可以关闭每个状态下面的订单，注意修改商品的数量
	 * @param userAccount 管理员账号
	 * @param orderId 订单id
	 * @return 成功/失败
	 */
	boolean cancelOrderByAdmin(String userAccount,int orderId);
	/**
	 * 产生订单,这里需要考虑更多问题，并发来了怎么办。。。数据一致性怎么解决
	 * @param cart 购物车
	 * @param deliveryTimeId 配送时间
	 * @param account 下单用户
	 * @param addressId 配送地址
	 * @return 新生成的订单
	 */
	Order addOrder(Cart cart,int deliveryTimeId,String account,int addressId);
	
}

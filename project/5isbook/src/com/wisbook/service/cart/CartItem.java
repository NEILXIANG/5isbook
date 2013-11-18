package com.wisbook.service.cart;

import java.math.BigDecimal;

import com.wisbook.model.cms.Entity;
import com.wisbook.model.order.EntityType;

/**
 * 购物车项
 * @author volador
 *
 */
public interface CartItem {
	/**
	 * 该项的id;每一个购物车项都有一个唯一的id号
	 * @return
	 */
	int getId();
	/**
	 * 所选中的的商品
	 * @return 商品实体
	 */
	Entity getEntity();
	/**
	 * 所选中的商品的id
	 * @return
	 */
	int getEntityId();
	/**
	 * 选中该商品的哪个种类
	 * @return 种类实体
	 */
	EntityType getEntityType();
	/**
	 * 选中该商品的种类的名字
	 * @return
	 */
	String getEntityTypeName();
	/**
	 * 需要多少件商品
	 * @return 商品件数
	 */
	int getBuyNum();
	/**
	 * 添加购买数,最多不能多于上限
	 * @param num
	 * @return true/false 成功/失败
	 */
	boolean increaseBuyNum(int num);
	/**
	 * 减少购买数,最少减少到0
	 * @param num
	 * @return true/false 成功/失败
	 */
	boolean decreaseBuyNum(int num);
	/**
	 * 订单项小记
	 * @return
	 */
	BigDecimal getTotalPrice();
}

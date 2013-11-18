package com.wisbook.service.cart;

import java.math.BigDecimal;

/**
 * 购物车
 * @author volador
 *
 */
public interface Cart extends Iterable<CartItem>{
	/**
	 * 拿到购物车总价
	 * @return 总价
	 */
	BigDecimal getTotalPrice();
	/**
	 * 添加一个购物车项,由添加者保证添加数据的准确性
	 * @param entityId 商品id
	 * @param entityTypeName 商品类别
	 * @param buyNum 购买数量
	 */
	void addItem(int entityId,String entityTypeName,int buyNum);
	/**
	 * 删除购物车项
	 * @param cartItemId 购物车项id
	 * @return true/false 成功/失败
	 */
	boolean delete(int cartItemId);
	/**
	 * 增加购物车项的购买数量
	 * @param cartItemId 购物车项
	 * @param num 添加的数量
	 * @return true/false  成功/失败
	 */
	boolean increaseNumofCartItem(int cartItemId,int num);
	/**
	 * 减少购物车想的购买数量，若减少的数量比现存数量少，则晴空现存数量
	 * @param cartItemId 购物车项
	 * @param num 减少数量
	 * @return true/false 成功/失败
	 */
	boolean decreaseNumOfCartItem(int cartItemId,int num);
	/**
	 * 清除购物车
	 */
	void clear();
	/**
	 * 该购物车是否为空
	 * @return true/false
	 */
	boolean isEmpty();
}

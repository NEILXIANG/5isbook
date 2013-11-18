package com.wisbook.service.cart;
/**
 * 处理购物车
 * @author volador
 *
 */
public interface CartManager {
	/**
	 * 给购物车添加一个购物车项,假如添加的购物车项已经存在了，就直接增加数量
	 * @param cart 购物车
	 * @param entityId 商品id
	 * @param entityType 商品类别
	 * @param buyNum 购买数量
	 * @return 更改后的购物车，假如购物车不存在，则新建一个购物车
	 */
	Cart addItem(Cart cart,int entityId,String entityType,int buyNum);
	/**
	 * 删除某个购物车项
	 * @param cart 购物车
	 * @param itemId 购物车项id
	 * @return 若购物车项存在且删除成功：true；否则：false
	 */
	boolean deleteItem(Cart cart,int itemId);
	/**
	 * 增加一个购物车项的购买数量
	 * @param cart 购物车
	 * @param itemId 购物车项
	 * @param buyNum 增加的数量
	 * @return 若购物车存在且操作成功：true；否则：false
	 */
	boolean increaseItemBuyNum(Cart cart,int itemId,int buyNum);
	/**
	 * 减少一个购物车项的购买数量
	 * @param cart 购物车
	 * @param itemId 购物车项id
	 * @param buyNum 减少数量
	 * @return 若购物车存在且操作成功：true；否则：false
	 */
	boolean decreaseItemBuyNum(Cart cart,int itemId,int buyNum);
	/**
	 * 清除购物车
	 */
	void clear(Cart cart);
}

package com.wisbook.service.cart;

import java.math.BigDecimal; 
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.wisbook.dao.base.BaseDao;
import com.wisbook.model.cms.Entity;
import com.wisbook.model.cms.EntityTypeNotFoundException;
import com.wisbook.model.order.EntityType;
/**
 * 购物车实例
 * @author volador
 *
 */
public class CartImpl implements Cart{

	/*
	 * 主要用来获取商品信息
	 */
	private BaseDao<Entity,Integer> entityDao;
	/*
	 * 保存购物车项的结构
	 */
	private Map<Integer,CartItem> items;
	/*
	 * 记录当前购物车项的id记录
	 */
	private int idRecord = 1;
	/*
	 * 购物车单项
	 */
	private class CartItemImpl implements CartItem{
		/*
		 * 该项的id
		 */
		private int id;
		/*
		 * 购买的商品
		 */
		private int entityId;
		/*
		 * 购买商品的种类
		 */
		private String entityTypeName;
		/*
		 * 购买商品的数量
		 */
		private int buyNum;
		
		@Override
		public String toString() {
			return this.entityId+this.entityTypeName;
		}
		
		public CartItemImpl(int id,int entityId,String entityTypeName,int buyNum) {
			this.id = id;
			this.entityId = entityId;
			this.entityTypeName = entityTypeName;
			this.buyNum = buyNum;
		}
		
		@Override
		public int getId() {
			return this.id;
		}

		@Override
		public Entity getEntity() {
			return entityDao.getEnitytById(this.entityId);
		}

		@Override
		public int getEntityId() {
			return this.entityId;
		}

		@Override
		public EntityType getEntityType() {
			return this.getEntity().getEntityType(this.entityTypeName);
		}

		@Override
		public String getEntityTypeName() {
			return this.entityTypeName;
		}

		@Override
		public int getBuyNum() {
			return this.buyNum;
		}

		@Override
		public boolean increaseBuyNum(int num) {
			int max = getEntityType().getStorageCount();
			if(max <= buyNum) return false;
			buyNum += num;
			if(buyNum > max) buyNum = max;
			return true;
		}

		@Override
		public boolean decreaseBuyNum(int num) {
			if(buyNum <= 0) return false; 
			if(this.buyNum <= num) this.buyNum = 0;
			else this.buyNum -= num;
			return true;
		}

		@Override
		public BigDecimal getTotalPrice() {
			BigDecimal singlePrice = null;
			try {
				singlePrice = this.getEntity().getSinglePriceByEntityType(this.entityTypeName);
			} catch (EntityTypeNotFoundException e) {
				e.printStackTrace();
				singlePrice = new BigDecimal("0");
			}
			return singlePrice.multiply(new BigDecimal(this.buyNum)).setScale(2,BigDecimal.ROUND_HALF_UP);
		}
		
	}
	
	public CartImpl(BaseDao<Entity,Integer> entityDao) {
		this.entityDao = entityDao;
		this.items = new HashMap<Integer,CartItem>();
	}
	
	@Override
	public Iterator<CartItem> iterator() {
		return items.values().iterator();
	}

	@Override
	public BigDecimal getTotalPrice() {
		BigDecimal result = new BigDecimal("0");
		for(CartItem item:items.values()){
			result = result.add(item.getTotalPrice());
		}
		return result.setScale(2,BigDecimal.ROUND_HALF_UP);
	}

	@Override
	public void addItem(int entityId, String entityTypeName, int buyNum) {
		boolean existed = false;
		String flag = entityId+entityTypeName;
		int existedItemId = 0;
		for(CartItem item:this.items.values()){
			if(flag.equals(item.toString())){
				existed = true;
				existedItemId = item.getId();
				break;
			}
		}
		if(existed){
			this.increaseNumofCartItem(existedItemId, buyNum);
		}else{
			CartItem cartItem = new CartItemImpl(this.idRecord,entityId,entityTypeName,buyNum);
			this.items.put(this.idRecord,cartItem);
			this.idRecord++;
		}
	}

	@Override
	public boolean delete(int cartItemId) {
		boolean flag = false;
		if(this.items.containsKey(cartItemId)){
			this.items.remove(cartItemId);
		}
		return flag;
	}

	@Override
	public boolean increaseNumofCartItem(int cartItemId, int num) {
		boolean flag = false;
		CartItem item = this.items.get(cartItemId);
		if(item != null){
			flag = item.increaseBuyNum(num);
		}
		return flag;
	}

	@Override
	public boolean decreaseNumOfCartItem(int cartItemId, int num) {
		boolean flag = false;
		CartItem item = this.items.get(cartItemId);
		if(item != null){
			flag = item.decreaseBuyNum(num);
		}
		return flag;
	}

	@Override
	public void clear() {
		this.items.clear();
	}

	@Override
	public boolean isEmpty() {
		boolean flag = true;
		if(this.items.size() > 0) flag = false;
		return flag;
	}

}

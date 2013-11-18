package com.wisbook.service.cart;

import javax.annotation.Resource; 

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Component;

import com.wisbook.dao.base.BaseDao;
import com.wisbook.model.cms.Entity;
import com.wisbook.model.order.EntityType;
import com.wisbook.service.user.AccessErrorException;

/**
 * 购物车管理的默认实现
 * @author volador
 *
 */
@Component("cartManager")
public class DefaultCartManagerImpl implements CartManager{

	private BaseDao<Entity,Integer> entityDao;
	
	@Resource(name="entityDao")
	public void setEntityDao(BaseDao<Entity, Integer> entityDao) {
		this.entityDao = entityDao;
	}

	@Override
	public Cart addItem(Cart cart, int entityId, String entityType,int buyNum) {
		if(cart == null) cart = new CartImpl(this.entityDao);
		Entity entity = null;
		EntityType et = null;
		try{
			entity = this.entityDao.getEnitytById(entityId);
			et = entity.getEntityType(entityType);
		}catch(NullPointerException e){
			throw new AccessErrorException(e);
		}catch(ObjectNotFoundException e){
			throw new AccessErrorException(e);
		}
		if(et == null) throw new AccessErrorException("can not find an entity_type[name="+entityType+"] in entity[id=]"+entityId);
		if(buyNum >= et.getStorageCount()) buyNum = et.getStorageCount();
		cart.addItem(entityId, entityType, buyNum);
		return cart;
	}

	@Override
	public boolean deleteItem(Cart cart, int itemId) {
		boolean flag = false;
		if(cart != null){
			flag = cart.delete(itemId);
		}
		return flag;
	}

	@Override
	public boolean increaseItemBuyNum(Cart cart, int itemId, int buyNum) {
		boolean flag = false;
		if(cart != null){
			flag = cart.increaseNumofCartItem(itemId, buyNum);
		}
		return flag;
	}

	@Override
	public boolean decreaseItemBuyNum(Cart cart, int itemId, int buyNum) {
		boolean flag = false;
		if(cart != null){
			flag = cart.decreaseNumOfCartItem(itemId, buyNum);
		}
		return flag;
	}

	@Override
	public void clear(Cart cart) {
		cart.clear();
	}

}

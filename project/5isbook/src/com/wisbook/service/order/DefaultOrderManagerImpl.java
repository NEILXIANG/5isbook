package com.wisbook.service.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Component;

import com.wisbook.dao.base.BaseDao;
import com.wisbook.dao.order.DeliveryTimeDao;
import com.wisbook.dao.order.OrderDao;
import com.wisbook.dao.order.OrderStateDao;
import com.wisbook.dao.user.AddressDao;
import com.wisbook.model.cms.Category;
import com.wisbook.model.cms.Entity;
import com.wisbook.model.order.DeliveryTime;
import com.wisbook.model.order.EntityType;
import com.wisbook.model.order.EntityTypePK;
import com.wisbook.model.order.Order;
import com.wisbook.model.order.OrderItem;
import com.wisbook.model.order.OrderItemPK;
import com.wisbook.model.order.OrderState;
import com.wisbook.model.order.OrderStateAdmin;
import com.wisbook.model.order.OrderStateAdminPK;
import com.wisbook.model.order.OrderStateEnum;
import com.wisbook.model.user.Address;
import com.wisbook.model.user.User;
import com.wisbook.service.cart.Cart;
import com.wisbook.service.cart.CartItem;
import com.wisbook.service.goods.manager.GoodsManager;
import com.wisbook.service.user.AccessErrorException;
import com.wisbook.util.PageBean;

@Component("orderManager")
public class DefaultOrderManagerImpl implements OrderManager {

	private BaseDao<Order, Integer> orderDao;
	private OrderStateDao orderStateDao;
	private BaseDao<User, String> userDao;
	private BaseDao<DeliveryTime, Integer> deliveryTimeDao;
	private BaseDao<Address, Integer> addressDao;
	private BaseDao<EntityType, EntityTypePK> entityTypeDao;
	private BaseDao<OrderItem, OrderItemPK> orderItemDao;
	private GoodsManager goodsManager;

	@Resource(name="bookManager")
	public void setGoodsManager(GoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}

	@Resource(name = "orderItemDao")
	public void setOrderItemDao(BaseDao<OrderItem, OrderItemPK> orderItemDao) {
		this.orderItemDao = orderItemDao;
	}

	@Resource(name = "entityTypeDao")
	public void setEntityTypeDao(BaseDao<EntityType, EntityTypePK> entityTypeDao) {
		this.entityTypeDao = entityTypeDao;
	}

	@Resource(name = "addressDao")
	public void setAddressDao(BaseDao<Address, Integer> addressDao) {
		this.addressDao = addressDao;
	}

	@Resource(name = "deliveryTimeDao")
	public void setDeliveryTimeDao(
			BaseDao<DeliveryTime, Integer> deliveryTimeDao) {
		this.deliveryTimeDao = deliveryTimeDao;
	}

	@Resource(name = "userDao")
	public void setUserDao(BaseDao<User, String> userDao) {
		this.userDao = userDao;
	}

	@Resource(name = "orderStateDao")
	public void setOrderStateDao(OrderStateDao orderStateDao) {
		this.orderStateDao = orderStateDao;
	}

	@Resource(name = "orderDao")
	public void setOrderDao(BaseDao<Order, Integer> orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	public long getOrderCountByState(OrderStateEnum state) {
		return getOrderCountByState(state, StateMark.EQUALS);
	}

	@Override
	public Order getOrderById(int id) {
		return this.orderDao.getEnitytById(id);
	}

	@Override
	public PageBean<Order> getOrdersByState(OrderStateEnum state,
			int pageIndex, int pageSize) {
		return getOrdersByState(state, StateMark.EQUALS, pageIndex, pageSize);
	}

	@Override
	public boolean processToNextState(String userAccount, int orderId) {
		boolean flag = false;
		Order order = this.orderDao.getEnitytById(orderId);
		User admin = this.userDao.getEnitytById(userAccount);
		if (order != null && admin != null) {
			OrderState nowState = order.getOrderState();
			OrderState nextState = nowState.getNextState();
			if (nextState != null) {
				this.changeState(order, nextState, admin);
				flag = true;
			}
		}
		if (flag)
			this.orderDao.updateEntity(order);
		return flag;
	}

	@Override
	public synchronized boolean cancelOrderByAdmin(String userAccount, int orderId) {
		boolean flag = false;
		Order order = this.orderDao.getEnitytById(orderId);
		User admin = this.userDao.getEnitytById(userAccount);
		if (order != null && order.getOrderState().getNextState() != null
				&& admin != null) {
			OrderState cancelState = this.orderStateDao
					.getOrderState(OrderStateEnum.CANCEL);
			changeState(order, cancelState, admin);
			returnStoreCount(order);
			flag = true;
		}
		if (flag)
			this.orderDao.updateEntity(order);
		return flag;
	}
	/**
	 * 将订单中的货物全部返回仓库
	 * @param order
	 */
	private void returnStoreCount(Order order){
		Set<OrderItem> items = order.getOrderItems();
		for(OrderItem item : items){
			Entity e = item.getOrderItemPK().getEntity();
			String typeName = item.getOrderItemPK().getEntityType().getEntityTypePK().getGoodsType().getName();
			int increaceNum = item.getBuyNum();
			goodsManager.increaseGoods(e.getId(), typeName, increaceNum);
		}
	}

	/**
	 * 改变订单状态
	 * 
	 * @param order
	 *            被操作订单
	 * @param state
	 *            改变到该状态
	 * @param operator
	 *            谁操作的
	 */
	private void changeState(Order order, OrderState state, User operator) {
		order.setOrderState(state);
		OrderStateAdmin osa = new OrderStateAdmin();
		osa.setAdmin(operator);
		osa.setOpTime(new Date());
		OrderStateAdminPK pk = new OrderStateAdminPK();
		pk.setOrder(order);
		pk.setOrderState(state);
		osa.setOrderStateAdminPK(pk);
		order.getOrderStateAdmin().add(osa);
	}

	@Override
	public synchronized boolean cancelOrderByUser(String userAccount, int orderId) {
		boolean flag = false;
		Order order = ((OrderDao) this.orderDao).getOrderByAccount(userAccount,
				orderId);
		if (order != null) {
			OrderState cancelState = this.orderStateDao
					.getOrderState(OrderStateEnum.CANCEL);
			order.setOrderState(cancelState);
			returnStoreCount(order);
			flag = true;
		}
		if (flag = true)
			this.orderDao.updateEntity(order);
		return flag;
	}

	@Override
	public PageBean<Order> getOrdersByAccountAndState(String currentAccount,
			OrderStateEnum state, int pageIndex, int pageSize) {
		return getOrdersByAccountAndState(currentAccount, StateMark.EQUALS,
				pageIndex, pageSize, state);
	}

	@Override
	public synchronized Order addOrder(Cart cart, int deliveryTimeId,
			String account, int addressId) {
		Order order = new Order();

		Address address = ((AddressDao) this.addressDao)
				.getAddressByAccountAndId(account, addressId);
		if (address == null)
			throw new AccessErrorException("can not find out an address[id="
					+ addressId + "] of user[account=" + account + "].");
		DeliveryTime dt = this.deliveryTimeDao.getEnitytById(deliveryTimeId);
		try {
			dt.getTime();
		} catch (ObjectNotFoundException e) {
			throw new AccessErrorException(
					"can not find out a delivery_time[id=" + deliveryTimeId
							+ "].");
		}
		OrderState orderState = this.orderStateDao
				.getOrderState(OrderStateEnum.COMMITTED);
		User user = this.userDao.getEnitytById(account);
		Date date = new Date();

		order.setUser(user);
		order.setRealName(address.getRealName());
		order.setAddress(address.getAddress());
		order.setLongTel(address.getLongTel());
		order.setShortTel(address.getShortTel());
		order.setDeliveryTime(dt);
		order.setOrderState(orderState);
		order.setCreateTime(date);
		order.setDeliveryPrice(new BigDecimal("0"));

		this.orderDao.saveEntity(order);

		Iterator<CartItem> itemIterator = cart.iterator();
		while (itemIterator.hasNext()) {
			CartItem item = itemIterator.next();

			EntityType entityType = item.getEntityType();
			Entity entity = item.getEntity();
			int exChangeNumber = item.getBuyNum();
			if (exChangeNumber >= entityType.getStorageCount())
				exChangeNumber = entityType.getStorageCount();
			entityType.setStorageCount(entityType.getStorageCount()
					- exChangeNumber);
			this.entityTypeDao.updateEntity(entityType);

			OrderItemPK pk = new OrderItemPK();
			pk.setEntity(entity);
			pk.setEntityType(entityType);
			pk.setOrder(order);

			StringBuilder sb = new StringBuilder();
			sb.append(entity.getCategory().getBasicCode());
			sb.append(Category.CATEGORY_SEPERATOR);
			sb.append(entity.getId());
			sb.append(Category.CATEGORY_SEPERATOR);
			sb.append(entityType.getEntityTypePK().getGoodsType()
					.getStorageCode());
			if (exChangeNumber <= 1) {
				sb.append(entityType.getStorageCount() + 1);
			} else {
				sb.append("[");
				sb.append(entityType.getStorageCount() + 1);
				sb.append("~");
				sb.append(entityType.getStorageCount() + exChangeNumber);
				sb.append("]");
			}

			OrderItem oItem = new OrderItem();
			oItem.setOrderItemPK(pk);
			oItem.setBuyNum(exChangeNumber);
			oItem.setStoCodeList(sb.toString());
			this.orderItemDao.saveEntity(oItem);
			order.getOrderItems().add(oItem);
		}
		this.orderDao.updateEntity(order);
		return order;
	}

	@Override
	public List<DeliveryTime> getAllDeliveryTimes() {
		return ((DeliveryTimeDao) this.deliveryTimeDao).fetchAllRecord();
	}

	@Override
	public PageBean<Order> getAllOrdersByAccount(String currentAccount,
			int pageIndex, int pageSize) {
		return ((OrderDao) this.orderDao).getOrdersByAccount(currentAccount,
				pageIndex, pageSize);
	}

	@Override
	public long getOrderCountByState(OrderStateEnum state, StateMark mark) {
		String HQL = "from Order o where o.orderState" + mark.toString()
				+ ":state";
		Map<String,Object> params = new HashMap<String,Object>();
		OrderState oState = this.orderStateDao.getOrderState(state);
		params.put("state",oState);
		return this.orderDao.countEntity(HQL,params);
	}

	@Override
	public PageBean<Order> getOrdersByAccountAndState(String currentAccount,
			StateMark mark, int pageIndex, int pageSize,
			OrderStateEnum... states) {
		if (states == null || states.length <= 0 || currentAccount == null
				|| currentAccount.length() <= 0)
			throw new IllegalArgumentException(
					"states or account can not be null.");
		StringBuilder sb = new StringBuilder(
				"from Order o where o.user.account=:account and (");
		int index = 0;
		while (index < states.length) {
			sb.append("o.orderState" + mark.toString() + ":state" + index
					+ " or ");
			index++;
		}
		sb.delete(sb.length() - 4, sb.length());
		sb.append(")");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("account", currentAccount);
		index = 0;
		while(index < states.length){
			OrderState oState = this.orderStateDao.getOrderState(states[index]);
			params.put("state"+index, oState);
			index++;
		}
		return this.orderDao.fetchEntityByPageHQL(sb.toString(), pageIndex,
				pageSize, params);
	}

	@Override
	public PageBean<Order> getOrdersByState(OrderStateEnum state,
			StateMark mark, int pageIndex, int pageSize) {
		String HQL = "from Order o where o.orderState" + mark.toString()
				+ ":state";
		Map<String, Object> params = new HashMap<String, Object>();
		OrderState oState = this.orderStateDao.getOrderState(state);
		params.put("state", oState);
		return this.orderDao.fetchEntityByPageHQL(HQL, pageIndex, pageSize,
				params);
	}

	@Override
	public long getAllOrderCount() {
		String HQL = "from Order";
		return orderDao.countEntity(HQL);
	}
}

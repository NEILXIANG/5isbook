package com.wisbook.service.goods.manager.book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wisbook.dao.base.BaseDao;
import com.wisbook.dao.cms.CategoryDao;
import com.wisbook.dao.cms.EntityDao;
import com.wisbook.dao.order.OrderDao;
import com.wisbook.model.cms.Attribute;
import com.wisbook.model.cms.Category;
import com.wisbook.model.cms.Document;
import com.wisbook.model.cms.Entity;
import com.wisbook.model.order.OrderStateEnum;
import com.wisbook.service.goods.manager.GoodsManager;
import com.wisbook.util.BasicTestCase;
import com.wisbook.util.HibernateTemplate;
import com.wisbook.util.PageBean;

public class BookManagerTest extends BasicTestCase{
	
	private GoodsManager goodManager;
	private HibernateTemplate hibernateTemplate;
	private CategoryDao categoryDao;
	private EntityDao entityDao;
	private OrderDao orderDao;
	
	
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	public EntityDao getEntityDao() {
		return entityDao;
	}

	public void setEntityDao(EntityDao entityDao) {
		this.entityDao = entityDao;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public GoodsManager getBookManager() {
		return goodManager;
	}

	public void setBookManager(GoodsManager bookManager) {
		this.goodManager = bookManager;
	}

	
	public void testGetEntity() throws Throwable{
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("state",OrderStateEnum.CANCEL);
		long result = ((BaseDao)orderDao).countEntity("from Order o where o.orderState=:state",params);
		System.out.println(result);
	}
	
	public void testUpdate() throws Throwable{
		this.setDefaultRollback(false);
		Map<String,Object> content = new HashMap<String,Object>();
		content.put("summary","<h1>fuck_idd</h1>");
		this.goodManager.updateEntityValue(15, content);
	}
	
	public void testCategory(){
		Category c = this.categoryDao.getEntityByBasicCode("1.2.3");
		List<Entity> es = this.goodManager.recommendEntitys(c, 10);
		System.out.println(es);
	}
	
}


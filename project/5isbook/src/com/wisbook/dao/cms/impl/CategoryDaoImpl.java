package com.wisbook.dao.cms.impl;

import org.springframework.stereotype.Component;

import com.wisbook.dao.base.AbstractBaseDao;
import com.wisbook.dao.cms.CategoryDao;
import com.wisbook.model.cms.Category;
@Component("categoryDao")
public class CategoryDaoImpl extends AbstractBaseDao<Category, Integer> implements CategoryDao {

	@Override
	public Category getEntityByBasicCode(String basicCode) {
		Category c = (Category) this.getHibernateTemplate().createQuery("from Category c where c.basicCode=:basicCode")
					.setString("basicCode",basicCode)
					.setCacheable(true)
					.uniqueResult();
		return c;
	}

	@Override
	protected Class<Category> getEntityClazz() {
		return Category.class;
	}

}

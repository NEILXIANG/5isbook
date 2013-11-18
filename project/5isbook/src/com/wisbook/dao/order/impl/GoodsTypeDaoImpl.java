package com.wisbook.dao.order.impl;

import org.springframework.stereotype.Component;

import com.wisbook.dao.base.AbstractBaseDao;
import com.wisbook.dao.order.GoodsTypeDao;
import com.wisbook.model.order.GoodsType;

@Component("goodsTypeDao")
public class GoodsTypeDaoImpl extends AbstractBaseDao<GoodsType,Integer> implements GoodsTypeDao{

	@Override
	protected Class<GoodsType> getEntityClazz() {
		return GoodsType.class;
	}

	@Override
	public GoodsType getGoodsTypeByName(String name) {
		GoodsType gt = null;
		gt = (GoodsType) this.getHibernateTemplate().createQuery("from GoodsType gt where gt.name=:name")
				.setString("name",name)
				.uniqueResult();
		return gt;
	}

}

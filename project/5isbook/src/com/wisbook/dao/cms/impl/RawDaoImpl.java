package com.wisbook.dao.cms.impl;

import org.springframework.stereotype.Component;

import com.wisbook.dao.base.AbstractBaseDao;
import com.wisbook.dao.cms.RawDao;
import com.wisbook.model.cms.Raw;
@Component("rawDao")
public class RawDaoImpl extends AbstractBaseDao<Raw,Integer> implements RawDao{

	@Override
	public Raw getEntityByName(String name) {
		Raw raw = (Raw) this.getHibernateTemplate().createQuery("from Raw r where r.name =:name")
				  .setString("name",name)
				  .uniqueResult();
		return raw;
	}

	@Override
	protected Class<Raw> getEntityClazz() {
		return Raw.class;
	}

}

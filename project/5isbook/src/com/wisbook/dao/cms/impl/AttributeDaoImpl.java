package com.wisbook.dao.cms.impl;

import org.springframework.stereotype.Component;

import com.wisbook.dao.base.AbstractBaseDao; 
import com.wisbook.dao.cms.AttributeDao;
import com.wisbook.model.cms.Attribute;
@Component("attributeDao")
public class AttributeDaoImpl extends AbstractBaseDao<Attribute,Integer> implements AttributeDao{

	@Override
	public Attribute getEntityByName(int documentId,String name) {
		Attribute attribute = (Attribute) this.getHibernateTemplate().createQuery("from Attribute attr where attr.document.id=:docId and attr.name=:name")
				  .setInteger("docId",documentId)
				  .setString("name",name)
				  .uniqueResult();
		return attribute;
	}

	@Override
	protected Class<Attribute> getEntityClazz() {
		return Attribute.class;
	}

}

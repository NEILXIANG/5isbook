package com.wisbook.dao.cms.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.wisbook.dao.base.AbstractBaseDao;
import com.wisbook.dao.cms.AttributeDao;
import com.wisbook.dao.cms.EntityValueDao;
import com.wisbook.model.cms.Attribute;
import com.wisbook.model.cms.Entity;
import com.wisbook.model.cms.EntityValue;

@Component("entityValueDao")
public class EntityValueDaoImpl extends AbstractBaseDao<EntityValue, Integer>
		implements EntityValueDao {

	private AttributeDao attributeDao;

	@Resource(name = "attributeDao")
	public void setAttributeDao(AttributeDao attributeDao) {
		this.attributeDao = attributeDao;
	}

	@Override
	protected Class<EntityValue> getEntityClazz() {
		return EntityValue.class;
	}

	@Override
	public EntityValue getEntityValue(Entity entity, String attrName) {
		String targetObj = null;
		String HQL = null;
		Attribute attr = this.attributeDao.getEntityByName(entity.getDocument()
				.getId(), attrName);
		if (attr == null)
			return null;
		if (attr.getRaw().getName().equals("text"))
			targetObj = "TextEntityValue";
		else
			targetObj = "BigTextEntityValue";
		HQL = "from "
				+ targetObj
				+ " ev where ev.entity.id=:entityId and ev.attribute.id=:attrId";
		EntityValue ev = (EntityValue) this.getHibernateTemplate()
				.createQuery(HQL).setInteger("entityId", entity.getId())
				.setInteger("attrId", attr.getId()).uniqueResult();
		return ev;
	}

}

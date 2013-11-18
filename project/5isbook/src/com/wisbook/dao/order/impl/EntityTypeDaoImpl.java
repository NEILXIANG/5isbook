package com.wisbook.dao.order.impl;

import org.springframework.stereotype.Component;

import com.wisbook.dao.base.AbstractBaseDao;
import com.wisbook.dao.order.EntityTypeDao;
import com.wisbook.model.order.EntityType;
import com.wisbook.model.order.EntityTypePK;

@Component("entityTypeDao")
public class EntityTypeDaoImpl extends AbstractBaseDao<EntityType,EntityTypePK> implements
		EntityTypeDao {

	@Override
	public EntityType getEntityType(int entityId, String typeName) {
		String hql = "from EntityType et where et.entityTypePK.entity.id=:entityId and et.entityTypePK.goodsType.name=:name";
		EntityType et = null;
		et = (EntityType) this.getHibernateTemplate().createQuery(hql)
				.setInteger("entityId",entityId)
				.setString("name",typeName)
				.uniqueResult();
		return et;
	}

	@Override
	protected Class<EntityType> getEntityClazz() {
		return EntityType.class;
	}

}

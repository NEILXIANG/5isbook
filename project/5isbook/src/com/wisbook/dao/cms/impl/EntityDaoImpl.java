package com.wisbook.dao.cms.impl;

import javax.annotation.Resource;  

import org.springframework.stereotype.Component;

import com.wisbook.dao.base.AbstractBaseDao;
import com.wisbook.dao.base.BaseDao;
import com.wisbook.dao.cms.EntityDao;
import com.wisbook.model.cms.Entity;
import com.wisbook.model.cms.EntityValue;

/**
 * entity dao implements
 * @author volador
 *
 */
@Component("entityDao")
public class EntityDaoImpl extends AbstractBaseDao<Entity,Integer> implements EntityDao{

	private BaseDao<EntityValue,Integer> entityValueDao;
	
	public BaseDao<EntityValue, Integer> getEntityValueDao() {
		return entityValueDao;
	}

	@Resource(name="entityValueDao")
	public void setEntityValueDao(BaseDao<EntityValue, Integer> entityValueDao) {
		this.entityValueDao = entityValueDao;
	}

	@Override
	protected Class<Entity> getEntityClazz() {
		return Entity.class;
	}

	@Override
	public Entity getEntityByIdAndDocName(int entityId, String docName) {
		String HQL = "from Entity e where e.document.name='"+docName+"' and e.id="+entityId;
		Entity entity = null;
		entity = (Entity) this.getHibernateTemplate().createQuery(HQL).uniqueResult();
		return entity;
	}
}

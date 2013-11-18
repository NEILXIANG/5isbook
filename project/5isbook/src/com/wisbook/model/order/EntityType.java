package com.wisbook.model.order;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 表示entity所关联的一个种类，跟entity是多对一关系 跟goods type是一对多关系
 * @author volador
 *
 */
@Table(name="entity_type")
@Entity
public class EntityType{
	private EntityTypePK entityTypePK; //主键
	private int storageCount; //该种类下有几条记录
	
	@EmbeddedId
	public EntityTypePK getEntityTypePK() {
		return entityTypePK;
	}
	public void setEntityTypePK(EntityTypePK entityTypePK) {
		this.entityTypePK = entityTypePK;
	}
	@Column(name="storage_count")
	public int getStorageCount() {
		return storageCount;
	}
	public void setStorageCount(int storageCount) {
		this.storageCount = storageCount;
	}
}

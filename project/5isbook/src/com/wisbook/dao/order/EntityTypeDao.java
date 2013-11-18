package com.wisbook.dao.order;

import com.wisbook.model.order.EntityType;

/**
 * EntityType dao 
 * @author volador
 *
 */
public interface EntityTypeDao {

	/**
	 * 通过entityid跟typeName获取记录
	 * @param entityId
	 * @param typeName
	 * @return
	 */
	EntityType getEntityType(int entityId, String typeName);

}

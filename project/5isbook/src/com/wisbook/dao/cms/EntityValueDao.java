package com.wisbook.dao.cms;

import com.wisbook.model.cms.Entity;
import com.wisbook.model.cms.EntityValue;

/**
 * entity value 的dao,所有的操作都要分发到具体的内容表中
 * @author volador
 *
 */
public interface EntityValueDao {
	/**
	 * 通过entityId跟属性名字拿到属性值记录
	 * @param entity
	 * @param attrName
	 * @return
	 */
	EntityValue getEntityValue(Entity entity,String attrName);
}

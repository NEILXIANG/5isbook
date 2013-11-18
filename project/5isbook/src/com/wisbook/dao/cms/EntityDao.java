package com.wisbook.dao.cms;

import com.wisbook.model.cms.Entity;
/**
 * entity dao interface
 * @author volador
 *
 */
public interface EntityDao {
	/**
	 * 通过id跟名字确认某文档下面的一条记录
	 * @param entityId：实体id
	 * @param docName：文档名字
	 * @return 记录实体
	 */
	Entity getEntityByIdAndDocName(int entityId,String docName);
}

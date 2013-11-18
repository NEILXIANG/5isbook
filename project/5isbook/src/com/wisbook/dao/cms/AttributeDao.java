package com.wisbook.dao.cms;



import com.wisbook.model.cms.Attribute;
/**
 * Attribute的dao
 * @author volador
 *
 */
public interface AttributeDao {
	/**
	 * 根据名字获取记录
	 * @param name 属性名字
	 * @param documentId 所属文档的id
	 * @return
	 */
	Attribute getEntityByName(int documentId,String name);
}

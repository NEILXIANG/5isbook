package com.wisbook.dao.cms;

import com.wisbook.model.cms.Raw;
/**
 * raw的dao
 * @author volador
 *
 */
public interface RawDao {
	/**
	 * 根据名字获取记录
	 * @param name
	 * @return
	 */
	Raw getEntityByName(String name);
}

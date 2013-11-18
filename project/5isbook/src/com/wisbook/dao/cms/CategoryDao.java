package com.wisbook.dao.cms;

import com.wisbook.model.cms.Category;

/**
 * 分类树
 * @author volador
 *
 */
public interface CategoryDao {
	/**
	 * 通过名字找出分类
	 * @param name:分类的名字
	 * @return 分类实例
	 */
	Category getEntityByBasicCode(String basicCode);
}

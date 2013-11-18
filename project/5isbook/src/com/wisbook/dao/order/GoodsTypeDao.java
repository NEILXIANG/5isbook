package com.wisbook.dao.order;

import com.wisbook.model.order.GoodsType;

/**
 * GoodsType Dao
 * @author volador
 *
 */
public interface GoodsTypeDao {
	/**
	 * 通过名字来查询结果
	 * @param name：种类的名字
	 * @return
	 */
	GoodsType getGoodsTypeByName(String  name);

}

package com.wisbook.hibernate3.aware;

import com.wisbook.service.goods.manager.GoodsManager;

/**
 * 给category注入bookmanager
 * @author volador
 *
 */
public interface GoodsManagerAware{
	void setGoodsManager(GoodsManager t);
}

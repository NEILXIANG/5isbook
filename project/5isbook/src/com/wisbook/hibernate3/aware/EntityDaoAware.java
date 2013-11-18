package com.wisbook.hibernate3.aware;
import com.wisbook.dao.cms.*;
/**
 * 注入entityDAO 该接口在 购物车/entity注入 上使用
 * @author volador
 *
 */
public interface EntityDaoAware {
	void setEntityDao(EntityDao entityDao);
}

package com.wisbook.dao.base;

import java.io.Serializable; 
import java.util.Map;

import com.wisbook.util.PageBean;

/**
 * dao的顶级接口，提供基本的增删查改功能
 * @author volador
 *
 */
public interface BaseDao<T,ID extends Serializable> {
	/**
	 * 添加实体类
	 * @param entity：需要添加的实体
	 * @return 实体的ID 
	 */
	Serializable saveEntity(final T entity);
	/**
	 * 修改实体类
	 * @param entity:需要修改的实体
	 * @return 实体类ID
	 */
	void updateEntity(final T entity);
	/**
	 * 删除实体
	 * @param entity
	 */
	void deleteEntity(final T entity);
	/**
	 * 通过主键查询实体类
	 * @param id：实体的id
	 * @return 实体对象
	 */
	T getEnitytById(final ID id);
	/**
	 * 根据HQL拿到分页数据
	 * @param HQL
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageBean<T> fetchEntityByPageHQL(String HQL,int currentPage,int pageSize);
	/**
	 * 
	 * @param HQL
	 * @param currentPage
	 * @param pageSize
	 * @param params 参数[参数名-参数值]
	 * @return
	 */
	PageBean<T> fetchEntityByPageHQL(String HQL,int currentPage,int pageSize,Map<String,Object> params);
	/**
	 * 根据HQL拿到记录数
	 * @param HQL
	 * @return
	 */
	Long countEntity(String HQL);
	/**
	 * 根据HQL拿到记录数，带参数
	 * @param HQL
	 * @param params
	 * @return
	 */
	Long countEntity(String HQL,Map<String,Object> params);
}

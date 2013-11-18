package com.wisbook.dao.base;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;

import com.wisbook.util.HibernateTemplate;
import com.wisbook.util.PageBean;
/**
 * 抽象BaseDao提供基本方法实现
 * @author volador
 *
 * @param <T>
 * @param <ID>
 */
public abstract class AbstractBaseDao<T,ID extends Serializable> extends AbstractDividePage<T> implements BaseDao<T, ID> {
	
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource(name="hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	protected abstract Class<T> getEntityClazz();
	
	@Override
	public Serializable saveEntity(T entity) {
		return this.hibernateTemplate.save(entity);
	};
	
	@Override
	public void updateEntity(T entity) {
		this.hibernateTemplate.merge(entity);
	};
	
	@Override
	public void deleteEntity(T entity) {
		this.hibernateTemplate.delete(entity);
	};
	
	@SuppressWarnings("unchecked")
	@Override
	public T getEnitytById(ID id) {
		return (T) this.hibernateTemplate.load(this.getEntityClazz(), id);
	};
	
	@Override
	public PageBean<T> fetchEntityByPageHQL(String HQL, int currentPage,int pageSize) {
		return super.dividePageOperation(HQL, currentPage, pageSize);
	}
	
	@Override
	public PageBean<T> fetchEntityByPageHQL(String HQL, int currentPage,
			int pageSize, Map<String, Object> params) {
		return super.dividePageOperation(HQL, currentPage, pageSize,params);
	}
	
	@Override
	public Long countEntity(String HQL) {
		String prefix = HQL.substring(0,5);
		if(!"select".equals(prefix)){
			HQL = "select count(*) " + HQL;
		}
		return (Long) hibernateTemplate.createQuery(HQL).uniqueResult();
	}
	
	@Override
	public Long countEntity(String HQL, Map<String, Object> params) {
		String prefix = HQL.substring(0,5);
		if(!"select".equals(prefix)){
			HQL = "select count(*) " + HQL;
		}
		Query q = hibernateTemplate.createQuery(HQL);
		if(params != null && params.size() > 0)
		for(Map.Entry<String, Object> entry : params.entrySet()){
			q.setParameter(entry.getKey(),entry.getValue());
		}
		return (Long) q.uniqueResult();
	}
}

package com.wisbook.dao.base;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.wisbook.util.HibernateTemplate;
import com.wisbook.util.PageBean;

/**
 * 抽象分页类
 * @author volador
 *
 * @param <T>
 */
public abstract class AbstractDividePage<T> {
	private static int CURRENT_PAGE	= 0; //当前页
	private static int PAGE_COUNT = 0;  //每页几条
	private static int TOTAL_PAGE = 0;  //总页数
	private static long TOTAL_COUNT = 0; //总记录数
	private static int BEGIN_INDEX = 0; //从哪里开始取数
	
	abstract HibernateTemplate getHibernateTemplate();
	
	@SuppressWarnings("unchecked")
	PageBean<T> dividePageOperation(final String HQL,int currentPage,int pageSize,final Map<String,Object> params){
		CURRENT_PAGE = currentPage <= 0?1:currentPage;
		PAGE_COUNT = pageSize;
		TOTAL_COUNT = (Long) this.getHibernateTemplate().execute(new HibernateCallback(){

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String totalCountHql = "select count(*) " + HQL;
				Query q = session.createQuery(totalCountHql);
				if(params != null && params.keySet().size() >0){
					for(String key:params.keySet()){
						q.setParameter(key,params.get(key));
					}
				}
				return q.uniqueResult();
			}
			
		});
		if(TOTAL_COUNT == 0) return null;
		TOTAL_PAGE = countTotalPage(TOTAL_COUNT,PAGE_COUNT);
		BEGIN_INDEX = getIndex(currentPage,pageSize);
		
		PageBean<T> pb = new PageBean<T>();
		pb.setCurrentPage(CURRENT_PAGE);
		pb.setPageSize(PAGE_COUNT);
		pb.setTotalPage(TOTAL_PAGE);
		List<T> list = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query q = session.createQuery(HQL);
				if(params != null && params.keySet().size() >0){
					for(String key:params.keySet()){
						q.setParameter(key,params.get(key));
					}
				}
				return q.setFirstResult(BEGIN_INDEX).setMaxResults(PAGE_COUNT).list();
			}
			
		});
		pb.setList(list);
		return pb;
	}
	
	PageBean<T> dividePageOperation(final String HQL,int currentPage,int pageSize){
		return this.dividePageOperation(HQL, currentPage, pageSize,null);
	}
	/**
	 * 拿到总页数
	 * @param totalCount：总体记录数
	 * @param pageSize：每页条数
	 * @return
	 */
	private int countTotalPage(long totalCount,int pageSize){
		return (int) (totalCount % pageSize == 0 ? totalCount/pageSize : totalCount/pageSize+1);
	}
	/**
	 * 拿到开始取数据的位置
	 * @param currentPage 当前页
	 * @param pageSize 每页几条
	 * @return
	 */
	private int getIndex(int currentPage,int pageSize){
		return pageSize*(currentPage-1);
	}
}

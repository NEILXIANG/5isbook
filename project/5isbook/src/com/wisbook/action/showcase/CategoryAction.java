package com.wisbook.action.showcase;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.wisbook.model.cms.Category;
import com.wisbook.model.cms.Entity;
import com.wisbook.service.goods.manager.GoodsManager;
import com.wisbook.servlet.listener.StartupAndLoadCategory;
import com.wisbook.util.PageBean;

/**
 * 显示分类里面的商品
 * @author volador
 *
 */
@Component("com.wisbook.action.showcase.categoryAction")
@Scope("prototype")
public class CategoryAction extends ActionSupport implements ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Category root;
	private GoodsManager manager;
	private int pageIndex; //当前页
	private int pageSize; //每页几条记录
	private PageBean<Entity> entitys; //当前分类下的实体记录
	private static final int DEFAULT_PAGE_INDEX = 1; //默认当前页
	private static final int DEFAULT_PAGE_SIZE = 21; //默认每页大小
	private int id; //category id
	
	private ServletContext servletContext;
	
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		Map<Integer,Category> cl =  (Map<Integer, Category>) servletContext
				.getAttribute(StartupAndLoadCategory.CATEGORY_ALL_NODES_IN_MAP);
		Category category = cl.get(id);
		if(category == null) return Action.ERROR;
		if(category.getChildren().size() > 0) return Action.ERROR;  //必须是叶节点
		try{
			root = category.getParent().getParent();
		}catch(NullPointerException e){
			return Action.ERROR; //木有3层结构
		}
		this.entitys = this.manager.fetchGoodsByCategory(category.getId(), pageIndex, pageSize);
		return Action.SUCCESS;
	}
	
	@Override
	public void validate() {  //搞定分页的事宜
		this.pageIndex = this.pageIndex <= 0?DEFAULT_PAGE_INDEX:this.pageIndex;
		this.pageSize = this.pageSize <= 0?DEFAULT_PAGE_SIZE:this.pageSize;
	}
	
	
	public PageBean<Entity> getEntitys() {
		return entitys;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getId() {
		return id;
	}
	
	public int getCid(){
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Category getRoot() {
		return root;
	}

	@Resource(name="bookManager")
	public void setManager(GoodsManager manager) {
		this.manager = manager;
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.servletContext = context;
	}
	
}

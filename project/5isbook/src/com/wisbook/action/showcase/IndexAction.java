package com.wisbook.action.showcase;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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

/**
 * 首页
 * 
 * @author volador
 * 
 */
@Component("com.wisbook.action.showcase.indexAction")
@Scope("prototype")
public class IndexAction extends ActionSupport implements ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private volatile List<Category> categoryList = null;
	private List<Category> showCategoryList = new ArrayList<Category>();
	private ServletContext servletContext;
	private static int SHOW_CATEGORY_SIZE = 6; //页面主体部分显示5块
	private static int SHOW_RECOMMEND_ENTITY_SIZE = 14; //主体推荐的个数
	
	private Map<Integer,List<Entity>> recmEntitys = new HashMap<Integer,List<Entity>>(); //每个类别推荐的商品
	private GoodsManager manager;

	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		this.categoryList = (List<Category>) this.servletContext
				.getAttribute(StartupAndLoadCategory.CATEGORY_LIST_NODES);
		if (this.categoryList.size() <= SHOW_CATEGORY_SIZE)
			this.showCategoryList = this.categoryList;
		else { //随机在分类集里面找5个出来显示 这里有bug，应该找那些有书的分类出来
			Set<Integer> luckNumList = new HashSet<Integer>();
			Random random = new Random(System.currentTimeMillis());
			int maxSize = this.categoryList.size();
			while (luckNumList.size() < SHOW_CATEGORY_SIZE) {
				int resultNum = Math.abs(random.nextInt()) % maxSize;
				luckNumList.add(resultNum);
			}
			for (int luckNum : luckNumList) {
				this.showCategoryList.add(this.categoryList.get(luckNum));
			}
		}
		if(showCategoryList.size() > 0)
		for(Category c : showCategoryList){
			recmEntitys.put(c.getId(), manager.recommendEntitys(c, SHOW_RECOMMEND_ENTITY_SIZE));
		}
		return Action.SUCCESS;
	}

	@Resource(name="bookManager")
	public void setManager(GoodsManager manager) {
		this.manager = manager;
	}

	public Map<Integer, List<Entity>> getRecmEntitys() {
		return recmEntitys;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}
	
	public List<Category> getShowCategoryList() {
		return showCategoryList;
	}

	@Override
	public void setServletContext(ServletContext servleContext) {
		this.servletContext = servleContext;
	}
}

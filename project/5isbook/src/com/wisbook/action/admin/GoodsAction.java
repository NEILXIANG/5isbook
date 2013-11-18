package com.wisbook.action.admin;

import java.io.IOException; 
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.wisbook.model.cms.Category;
import com.wisbook.model.cms.Entity;
import com.wisbook.service.goods.manager.ErrorContentException;
import com.wisbook.service.goods.manager.GoodsManager;
import com.wisbook.servlet.listener.StartupAndLoadCategory;
import com.wisbook.util.AccessErrorException;
import com.wisbook.util.PageBean;

/**
 * @author Ban 后台商品管理
 * 
 */
@Component("adminGoods")
@Scope("prototype")
public class GoodsAction extends ActionSupport implements ServletContextAware {
	private static final long serialVersionUID = 1L;
	private GoodsManager goodsManager;
	private ServletContext servletContext;
	// 商品种类子类列表
	private Set<Category> categorySet;
	// 商品种类
	private Category category;
	// 商品种类类别编号
	private int categoryType = 1;
	// 商品Id
	private int entityId;
	// 特定商品的特定种类名
	private String typeName;
	// 当前页,默认为1
	private int pageIndex=1;
	// 每页几条记录，默认为10
	private int pageSize=10;
	// 当前分类下的实体记录
	private PageBean<Entity> entitys = new PageBean<Entity>();
	// category id
	private int id;

	/**
	 * 展示商品列表
	 */
	@SuppressWarnings("unchecked")
	public String showEntities() throws ErrorContentException {
		Map<Integer, Category> cl = (Map<Integer, Category>) servletContext
				.getAttribute(StartupAndLoadCategory.CATEGORY_ALL_NODES_IN_MAP);
	    category = cl.get(id);
		if(category == null) return Action.ERROR;
		if(category.getChildren().size() > 0) return Action.ERROR;  //必须是叶节点
		this.entitys = this.goodsManager.fetchGoodsByCategory(category.getId(),
				pageIndex, pageSize);
		return Action.SUCCESS;
	}

	/**
	 * 查看商品库存商品
	 * 
	 * @throws AccessErrorException
	 * @throws IOException
	 */
	public void checkStoreCount() throws AccessErrorException, IOException {
		ServletActionContext.getResponse().getWriter()
				.write("count:" + goodsManager.checkStoreCount(entityId));
	}

	/**
	 * 更改商品数量
	 * 根据现在的数值判断是增加还是删除
	 * @throws AccessErrorException
	 */
	public void changeStoreCount() throws AccessErrorException {
		//目标数量
		int count = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("count"));
		//现在的数量
		int nowCount=goodsManager.checkStoreCount(entityId,typeName);
		int result = nowCount -count;
		//现在的比目标多，则为减少
		if(result>0)
			goodsManager.decreaseGoods(entityId, typeName, result);
		//否则则为增加
		else
		{
			result=-result;
			goodsManager.increaseGoods(entityId, typeName, result);
		}
	}

	/**
	 * 清空商品库存
	 * 
	 * @throws AccessErrorException
	 */
	public void clearGoodsStoreCount() throws AccessErrorException {
		goodsManager.clearGoodsStoreCount(entityId);
	}

	/**
	 * 查找商品
	 */
	public String find() {
		return SUCCESS;
	}

	@Resource(name = "bookManager")
	public void setGoodsManager(GoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}

	public Set<Category> getCategorySet() {
		return categorySet;
	}

	public void setCategorySet(Set<Category> categorySet) {
		this.categorySet = categorySet;
	}
	 
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(int categoryType) {
		this.categoryType = categoryType;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public GoodsManager getGoodsManager() {
		return goodsManager;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public PageBean<Entity> getEntitys() {
		return entitys;
	}

	public void setEntitys(PageBean<Entity> entitys) {
		this.entitys = entitys;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}

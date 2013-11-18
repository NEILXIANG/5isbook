package com.wisbook.action.showcase;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.wisbook.model.cms.Entity;
import com.wisbook.service.goods.manager.GoodsManager;
import com.wisbook.util.PageBean;
/**
 * 搜索
 * @author volador
 *
 */
@Component("com.wisbook.action.showcase.searchAction")
@Scope("prototype")
public class SearchAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static int DEFAULT_INDEX = 1;  //默认当前页
	private static int DEFAULT_SIZE = 10; //默认每页显示条数
	public static final String SEARCH_ERROR = "没有搜索到相关结果.";
	
	private String content; //内容
	private String attributeName; //搜索属性
	private int index; //当前页数
	private int size; //每页显示条数
	
	private String message; //结果信息
	private PageBean<Entity> results;  //搜索结构
	
	private GoodsManager goodsManager;

	@Override
	public String execute() throws Exception {
		results = goodsManager.fetchGoodsByAttribute(attributeName, content, index, size);
		if(results == null) message = SEARCH_ERROR;
		return Action.SUCCESS;
	}
	
	@Override
	public void validate() {
		index = (index <= 0) ? DEFAULT_INDEX : index;
		size = (size <= 0) ? DEFAULT_SIZE : size;
	}
	
	public PageBean<Entity> getResults() {
		return results;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	@Resource(name="bookManager")
	public void setGoodsManager(GoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}

	public String getMessage() {
		return message;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getContent() {
		return content;
	}

	public String getAttributeName() {
		return attributeName;
	}
}

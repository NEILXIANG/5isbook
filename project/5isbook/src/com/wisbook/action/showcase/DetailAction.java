package com.wisbook.action.showcase;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.ObjectNotFoundException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.wisbook.model.cms.Entity;
import com.wisbook.service.goods.manager.GoodsManager;
/**
 * 展示商品
 * @author volador
 *
 */
@Component("com.wisbook.action.showcase.detailAction")
@Scope("prototype")
public class DetailAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int RECOMMEND_SIZE = 6; //推荐条目
	private GoodsManager manager;
	private Entity book = null;
	private int id;
	private List<Entity> recommendEntitys;

	@Override
	public String execute() throws Exception {
		if(id == 0) return Action.ERROR;
		this.book = this.manager.getGoodsById(id);
		try{
			book.getPrice();
			this.recommendEntitys = manager.recommendEntitys(book.getCategory().getParent().getParent(),RECOMMEND_SIZE);
		}catch(ObjectNotFoundException e){
			return Action.ERROR;
		}catch(Exception e){
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}
	
	@Resource(name="bookManager")
	public void setManager(GoodsManager manager) {
		this.manager = manager;
	}
	

	public Entity getBook() {
		return book;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Entity> getRecommendEntitys() {
		return recommendEntitys;
	}
}

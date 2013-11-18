package com.wisbook.action.admin;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.wisbook.model.cms.Attribute;
import com.wisbook.model.cms.Entity;
import com.wisbook.model.cms.EntityValue;
import com.wisbook.service.goods.manager.GoodsManager;
import com.wisbook.service.goods.manager.ParameterException;
import com.wisbook.util.AccessErrorException;

/**
 * @author Ban 后台商品管理 商品详细信息的展示和修改
 * 
 */
@Component("goodsDetail")
@Scope("prototype")
public class GoodsDetailAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private GoodsManager goodsManager;
	// 商品Id
	private int entityId;
	// 商品实体
	private Entity entity;
	// 以下为商品的具体属性，书名，作者，页数，书本详情，出版社，出版日期， 作者简介
	private String title;
	private String author;
	private int pages;
	private String summary;
	private String publisher;
	private String pubdate;
	private String authorIntro;
	// 展示商品属性可用于展示和修改，show为true代表查看，跳至查看页面，否则去到修改页面
	private boolean show;
	// 属性集
	private Set<Attribute> attributes;
	// 属性的值集
	private Set<EntityValue> valueSet;

	/**
	 * 更改商品信息
	 * 
	 * @throws AccessErrorException
	 * @throws com.wisbook.service.user.AccessErrorException
	 * @throws ParameterException
	 */
	public String edit() throws AccessErrorException,
			com.wisbook.service.user.AccessErrorException, ParameterException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("author", author);
		map.put("title", title);
		map.put("pages", pages);
		map.put("publisher", publisher);
		map.put("pubdate", pubdate);
		map.put("author-intro", authorIntro);
		map.put("summary", summary);
		entity = goodsManager.updateEntityValue(entityId, map);
		return "edit";
	}

	/**
	 * 查看商品的详细信息 返回商品实体，商品的属性集，属性值集
	 * 
	 * @throws AccessErrorException
	 */
	public String detail() throws AccessErrorException {

		entity = goodsManager.getGoodsById(entityId);
		if (show)
			return "detail";
		return "edit";
	}

	@Resource(name = "bookManager")
	public void setGoodsManager(GoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getAuthorIntro() {
		return authorIntro;
	}

	public void setAuthorIntro(String author_intro) {
		this.authorIntro = author_intro;
	}

	public GoodsManager getGoodsManager() {
		return goodsManager;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Set<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<Attribute> attributes) {
		this.attributes = attributes;
	}

	public Set<EntityValue> getValueSet() {
		return valueSet;
	}

	public void setValueSet(Set<EntityValue> valueSet) {
		this.valueSet = valueSet;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

}

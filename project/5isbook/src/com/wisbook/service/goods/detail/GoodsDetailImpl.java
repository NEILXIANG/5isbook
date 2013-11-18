package com.wisbook.service.goods.detail;

import java.math.BigDecimal; 
import java.util.HashMap;
import java.util.Map;

import com.wisbook.model.cms.Category;

public class GoodsDetailImpl implements GoodsDetail{
	
	private BigDecimal price;
	private Category category;
	private Map<String,Object> content=new HashMap<String,Object>();
	
	@Override
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Override
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Override
	public Map<String, Object> getContent() {
		return content;
	}
	
	public void setContent(Map<String, Object> content) {
		this.content = content;
	}
}

package com.wisbook.service.goods.detail;

import java.math.BigDecimal; 
import java.util.Map;

import com.wisbook.model.cms.Category;

/**
 * 定义一种商品的信息格式
 * @author volador
 *
 */
public interface GoodsDetail {
	/**
	 * 商品的价格
	 * @return 价格
	 */
	BigDecimal getPrice();
	/**
	 * 拿到商品的分类
	 * @return 分类实例
	 */
	Category getCategory();
	/**
	 * 商品各属性的值
	 * @return map映射对象
	 */
	Map<String,Object> getContent();
}

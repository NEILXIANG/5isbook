package com.wisbook.service.goods.document;

import com.wisbook.model.cms.Document;

/**
 * 商品文档管理
 * @author volador
 *
 */
public interface DocumentManager {
	/**
	 * 根据商品名字拿到商品格式定义文档
	 * @param goodsName：商品名字
	 * @return 商品文档
	 */
	Document getGoodsDocumentByName(String goodsName);
}

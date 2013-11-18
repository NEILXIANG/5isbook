package com.wisbook.service.goods.document;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.wisbook.dao.base.BaseDao;
import com.wisbook.dao.cms.DocumentDao;
import com.wisbook.model.cms.Document;
/**
 * 商品文档管理实现
 * @author volador
 *
 */
@Component("documentManager")
public class DefaultDocumentManagerImpl implements DocumentManager{

	private BaseDao<Document,Integer> documentDao;
	
	public BaseDao<Document, Integer> getDocumentDao() {
		return documentDao;
	}

	@Resource(name="documentDao")
	public void setDocumentDao(BaseDao<Document, Integer> documentDao) {
		this.documentDao = documentDao;
	}

	@Override
	public Document getGoodsDocumentByName(String goodsName) {
		DocumentDao dd = (DocumentDao)documentDao;
		Document document = dd.getDocumentByName(goodsName);
		return document;
	}

}


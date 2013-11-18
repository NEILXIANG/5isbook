package com.wisbook.service.goods.detail;

import javax.annotation.Resource; 

import com.wisbook.model.cms.Document;
import com.wisbook.service.goods.document.DocumentManager;
/**
 * 抽象provider给每个provider封装document信息
 * @author volador
 *
 */
public abstract class AbstractGoodsDetailProvider implements GoodsDetailProvider{
	private DocumentManager documentManager ;
	
	@Resource(name="documentManager")
	public void setDocumentManager(DocumentManager documentManager){
		this.documentManager = documentManager;
	}
	
	protected Document getDocument(){
		return this.documentManager.getGoodsDocumentByName(getDocumentName());
	}
	/*
	 * 拿到该provider所关联的document名字
	 */
	protected abstract String getDocumentName();
}

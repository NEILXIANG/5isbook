package com.wisbook.service.configuration.providers;

import java.util.List;  

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import com.wisbook.dao.base.BaseDao;
import com.wisbook.dao.cms.DocumentDao;
import com.wisbook.dao.cms.RawDao;
import com.wisbook.dao.cms.AttributeDao;
import com.wisbook.model.cms.Attribute;
import com.wisbook.model.cms.Document;
import com.wisbook.model.cms.Raw;
import com.wisbook.service.configuration.AbstractConfigeService;
import com.wisbook.service.configuration.XmlFileFormatException;
import com.wisbook.util.XPath;
/**
 * 配置系统的文档板块
 * @author volador
 *
 */
@Component("documentConfigeServiceProvider")
public class DocumentConfigeServiceProvider extends AbstractConfigeService {
	
	private BaseDao<Raw,Integer> rawDao;
	private BaseDao<Document,Integer> documentDao;
	private BaseDao<Attribute,Integer> attributeDao;
	
	public BaseDao<Attribute, Integer> getAttributeDao() {
		return attributeDao;
	}

	@Resource(name="attributeDao")
	public void setAttributeDao(BaseDao<Attribute, Integer> attributeDao) {
		this.attributeDao = attributeDao;
	}

	public BaseDao<Document, Integer> getDocumentDao() {
		return documentDao;
	}

	@Resource(name="documentDao")
	public void setDocumentDao(BaseDao<Document, Integer> documentDao) {
		this.documentDao = documentDao;
	}

	public BaseDao<Raw, Integer> getRawDao() {
		return rawDao;
	}

	@Resource(name="rawDao")
	public void setRawDao(BaseDao<Raw, Integer> rawDao) {
		this.rawDao = rawDao;
	}


	@Override
	protected void process() throws XmlFileFormatException {
		Node root = this.getRootNode();
		//元属性
		List<Node> rawList = XPath.selectNodes("/config/raw",root);
		//文档
		List<Node> documentList = XPath.selectNodes("/config/document",root);
		
		/*
		 * 先把raw给插入系统里面，若该raw已经存在，则忽略
		 */
		if(rawList != null && rawList.size() > 0)
		for(Node r:rawList){
			String name = XPath.selectText("@name",r);
			String displayName = XPath.selectText("@display_name",r);
			
			if(this.assertNotNull(name) && ((RawDao)this.rawDao).getEntityByName(name) == null){
				Raw raw = new Raw();
				raw.setName(name);
				raw.setDisplayName(displayName);
				this.rawDao.saveEntity(raw);
			}
		}
		
		/*
		 * 插入文档，若该文档已经存在，则忽略
		 */
		if(documentList != null && documentList.size() > 0)
		for(Node d:documentList){
			String name = XPath.selectText("@name",d);
			String displayName = XPath.selectText("@display_name",d);
			
			if(this.assertNotNull(name) && ((DocumentDao)this.documentDao).getDocumentByName(name) == null){
				Document document = new Document();
				document.setName(name);
				document.setDisplayName(displayName);
				
				
				//搜索该文档下面的所有属性，注入文档中；若该属性已经存在，忽略该记录；若该属性的元属性不存在，则忽略该条记录
				List<Node> attributeList = XPath.selectNodes("/config/document/attribute",d);
				if(attributeList != null && attributeList.size() > 0)
				for(Node a:attributeList){
					String subName = XPath.selectText("@name",a);
					String subDisplayName = XPath.selectText("@display_name",a);
					String subRawName = XPath.selectText("@raw_name",a);
					
					Raw raw = ((RawDao)this.rawDao).getEntityByName(subRawName);
					if(raw == null) break;
					if(!this.assertNotNull(subName) && ((AttributeDao)this.attributeDao).getEntityByName(document.getId(),subName) != null) break;
					
					Attribute attribute = new Attribute();
					attribute.setName(subName);
					attribute.setDisplayName(subDisplayName);
					attribute.setRaw(raw);
					attribute.setDocument(document);
					document.getAttributes().add(attribute);
				}
				this.documentDao.saveEntity(document);
			}
		}
	}
	

}

package com.wisbook.dao.cms.impl;

import org.springframework.stereotype.Component;

import com.wisbook.dao.base.AbstractBaseDao;
import com.wisbook.dao.cms.DocumentDao;
import com.wisbook.model.cms.Document;
/**
 * 
 * @author volador
 *
 */
@Component("documentDao")
public class DocumentDaoImpl extends AbstractBaseDao<Document,Integer> implements DocumentDao{

	@Override
	public Document getDocumentByName(String name) {
		String HQL = "from Document d where d.name=:name";
		Document doc = null;
		doc = (Document) this.getHibernateTemplate().createQuery(HQL).setString("name",name).uniqueResult();
		return doc;
	}

	@Override
	protected Class<Document> getEntityClazz() {
		return Document.class;
	}

}

package com.wisbook.dao.cms.impl;
 
import org.springframework.stereotype.Component;

import com.wisbook.dao.base.AbstractBaseDao;
import com.wisbook.dao.cms.BigTextEntityValueDao;
import com.wisbook.model.cms.BigTextEntityValue;
@Component("bigTextEntityValueDao")
public class BigTextEntityValueDaoImpl extends AbstractBaseDao<BigTextEntityValue,Integer> implements BigTextEntityValueDao{

	@Override
	protected Class<BigTextEntityValue> getEntityClazz() {
		return BigTextEntityValue.class;
	}

}

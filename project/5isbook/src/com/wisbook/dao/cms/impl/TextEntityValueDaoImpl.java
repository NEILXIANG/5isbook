package com.wisbook.dao.cms.impl;

import org.springframework.stereotype.Component;

import com.wisbook.dao.base.AbstractBaseDao;
import com.wisbook.dao.cms.TextEntityValueDao;
import com.wisbook.model.cms.TextEntityValue;
@Component("textEntityValueDao")
public class TextEntityValueDaoImpl extends AbstractBaseDao<TextEntityValue,Integer> implements TextEntityValueDao{

	@Override
	protected Class<TextEntityValue> getEntityClazz() {
		return TextEntityValue.class;
	}

}

package com.wisbook.dao.cms.impl;

import com.wisbook.dao.base.BaseDao;
import com.wisbook.dao.cms.BigTextEntityValueDao;
import com.wisbook.dao.cms.TextEntityValueDao;
import com.wisbook.model.cms.BigTextEntityValue;
import com.wisbook.model.cms.TextEntityValue;
import com.wisbook.util.BasicTestCase;

public class EntityValueDaoTest extends BasicTestCase{
	private TextEntityValueDao textEntityValueDao;
	private BigTextEntityValueDao bigTextEntityValueDao;

	public BigTextEntityValueDao getBigTextEntityValueDao() {
		return bigTextEntityValueDao;
	}

	public void setBigTextEntityValueDao(BigTextEntityValueDao bigTextEntityValueDao) {
		this.bigTextEntityValueDao = bigTextEntityValueDao;
	}

	public TextEntityValueDao getTextEntityValueDao() {
		return textEntityValueDao;
	}

	public void setTextEntityValueDao(TextEntityValueDao textEntityValueDao) {
		this.textEntityValueDao = textEntityValueDao;
	}



	public void testGet(){
		
	}
}

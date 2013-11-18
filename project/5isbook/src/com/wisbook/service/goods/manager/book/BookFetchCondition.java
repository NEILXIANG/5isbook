package com.wisbook.service.goods.manager.book;

import com.wisbook.service.goods.manager.FetchCondition;

public interface BookFetchCondition extends FetchCondition{
	public static final String NO = "from Entity e where e.document.name = 'book'";
}

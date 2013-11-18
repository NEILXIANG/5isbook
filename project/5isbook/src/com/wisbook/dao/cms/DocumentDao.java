package com.wisbook.dao.cms;

import com.wisbook.model.cms.Document;
/**
 * document的dao
 * @author volador
 *
 */

public interface DocumentDao {
	/**
	 * 根据名字获取记录
	 * @param name
	 * @return
	 */
	Document getDocumentByName(String name);
}

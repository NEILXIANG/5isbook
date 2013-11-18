package com.wisbook.service.goods.manager;

import java.util.List;
import java.util.Map;

/**
 * 添加商品后的结果
 * @author volador
 *
 */
public interface AddResult {
	Map<String,StoreInfo> getStoreInfo();
	List<String> getErrorMessages();
	List<String> getErrorContents();
	boolean hasError();
}

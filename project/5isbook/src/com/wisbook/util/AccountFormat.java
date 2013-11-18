package com.wisbook.util;
/**
 * 账户格式
 * @author volador
 *
 */
public interface AccountFormat {
	/**
	 * 检查该账户是否是某种格式
	 * @param account
	 * @return
	 */
	boolean testFormat(String account);
	
	/**
	 * 获取判断格式后的信息
	 * @return
	 */
	String getErrorMessage();
}

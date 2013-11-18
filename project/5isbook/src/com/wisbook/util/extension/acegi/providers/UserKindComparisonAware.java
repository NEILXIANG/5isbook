package com.wisbook.util.extension.acegi.providers;
/**
 * 
 * @author volador
 *
 */
public interface UserKindComparisonAware {
	/**
	 * 自己所期望的j_userkind类型
	 * @param expectedUserKind
	 */
	void setExpectedUserKind(String expectedUserKind);
	/**
	 * 实际上得到的j_userkind类型
	 * @param currentUserKind
	 */
	void setCurrentUserKind(String currentUserKind);
	
}

package com.wisbook.service.goods.manager;
/**
 * 数据库不存在该attribute
 * @author volador
 *
 */
public class NoSuchAttributeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchAttributeException(String message) {
		super(message);
	}
}

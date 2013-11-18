package com.wisbook.util;
/**
 * 非法访问系统时出现的错误
 * @author volador
 *
 */
public class AccessErrorException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AccessErrorException(String message){
		super(message);
	}
}

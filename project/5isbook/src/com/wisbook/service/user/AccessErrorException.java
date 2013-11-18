package com.wisbook.service.user;
/**
 * 非法访问系统时出现的错误
 * @author volador
 *
 */
public class AccessErrorException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AccessErrorException(String message){
		super(message);
	}
	public AccessErrorException(Throwable e){
		super(e);
	}
}

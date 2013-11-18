package com.wisbook.util.extension.acegi;

import org.acegisecurity.AuthenticationException;
/**
 * 系统不支持该种user kind的登录
 * @author volador
 *
 */
public class BadUserKindException extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BadUserKindException(String message){
		super(message);
	}

}

package com.wisbook.service.goods.manager;
/**
 * 参数错误
 * @author volador
 *
 */
public class ParameterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ParameterException(String errorMessage){
		super(errorMessage);
	}
}

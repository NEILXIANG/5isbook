package com.wisbook.service.goods.manager;
/**
 * provider处理数据时发生的异常，封装异常信息
 * @author volador
 *
 */
public class ErrorContentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object errorContent;
	
	public Object getErrorContent() {
		return errorContent;
	}

	public void setErrorContent(Object errorContent) {
		this.errorContent = errorContent;
	}

	public ErrorContentException(Object errorContent){
		this.errorContent = errorContent;
	}

}

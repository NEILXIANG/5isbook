package com.wisbook.service.configuration;
/**
 * 读取文件错误
 * @author volador
 *
 */
public class FileAccessException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FileAccessException(String message) {
		super(message);
	}

}

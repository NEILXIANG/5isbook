package com.wisbook.service.configuration;
/**
 * xml的配置文件格式错误
 * @author volador
 *
 */
public class XmlFileFormatException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public XmlFileFormatException(String message){
		super(message);
	}
}

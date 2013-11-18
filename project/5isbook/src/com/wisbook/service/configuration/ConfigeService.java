package com.wisbook.service.configuration;

import java.io.File; 
import java.io.FileNotFoundException;
/**
 * 掌控配置的service接口
 * @author volador
 *
 */
public interface ConfigeService {
	/**
	 * 进行相关的配置工作
	 * @param filePath 配置文件的路径
	 * @throws FileAccessException 文件访问错误
 	 * @throws FileNotFoundException 配置文件无法找到
	 * @throws XmlFileFormatException 配置文件格式错误
	 */
	void confige(String filePath) throws FileAccessException,FileNotFoundException,XmlFileFormatException;
	/**
	 * 以File形式配置
	 * @param configFile
	 */
	void confige(File configFile)  throws XmlFileFormatException, FileAccessException,FileNotFoundException;
}

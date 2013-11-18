package com.wisbook.service.configuration;

import java.io.File;  
import java.io.FileNotFoundException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.wisbook.util.XPath;

/**
 * 模板模式，具体核心算法的实现有子类提供
 * 实现配置算法的抽象类，其里面提供了一些有用的方法，具体配置算法有子类提供
 * @author volador
 *
 */
public abstract class AbstractConfigeService implements ConfigeService{

	private File configeFile;
	private Document xmlDoc;
	
	
	public File getConfigeFile() {
		return configeFile;
	}

	public void setConfigeFile(File configeFile) {
		this.configeFile = configeFile;
	}

	public Document getXmlDoc() {
		return xmlDoc;
	}

	public void setXmlDoc(Document xmlDoc) {
		this.xmlDoc = xmlDoc;
	}
	
	/**
	 * 判断str非空
	 * @param str
	 * @return
	 */
	protected boolean assertNotNull(String str){
		boolean flag = false;
		if(str != null && str.length() >0) flag = true;
		return flag;
	}

	private File getFileWithPath(String filePath) throws FileNotFoundException{
		File file = new File(filePath);
		if(!file.exists()) throw new FileNotFoundException("file: '"+filePath+"' can not found!");
		return file;
	}
	
	private Document getDocumentWithFile(File file) throws XmlFileFormatException, FileAccessException{
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
	    domFactory.setNamespaceAware(true); // never forget this!
	    org.w3c.dom.Document xmlDoc=null;
	    try{
	    	DocumentBuilder builder = domFactory.newDocumentBuilder();
	    	xmlDoc = builder.parse(file);
	    }catch(SAXException ex){
	    	throw new XmlFileFormatException("file:'"+file.getName()+"' is not a xml file!");
	    }catch(Exception e){
	    	throw new FileAccessException("file:'"+file.getName()+"'操作失败!");
	    }
	    return xmlDoc;
	}
	
	protected Node getRootNode() throws XmlFileFormatException{
		Node rootNode = XPath.selectNode("/config", xmlDoc);
		if(rootNode == null) throw new XmlFileFormatException("配置文件的根元素必须为<config>!");
		return rootNode;
	}
	
	@Override
	public void confige(String filePath) throws XmlFileFormatException, FileAccessException, FileNotFoundException {
		this.configeFile = this.getFileWithPath(filePath);
		this.xmlDoc = this.getDocumentWithFile(configeFile);
		process();
	}
	
	@Override
	public void confige(File configFile) throws XmlFileFormatException, FileAccessException, FileNotFoundException {
		if(!configFile.exists()) throw new FileNotFoundException("file: '"+configFile.getName()+"' can not found!");
		this.configeFile = configFile;
		this.xmlDoc = this.getDocumentWithFile(configeFile);
		process();
	};
	/**
	 * 子类需要进行解析的算法实现
	 * @throws XmlFileFormatException xml格式错误
	 */
	protected abstract void process() throws XmlFileFormatException;
}

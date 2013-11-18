package com.wisbook.service.goods.detail.providers;


import java.io.File;    
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Set; 
import java.util.Map;
import java.util.List;


import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import com.wisbook.dao.base.BaseDao;
import com.wisbook.dao.cms.CategoryDao;
import com.wisbook.model.cms.Attribute;
import com.wisbook.model.cms.Category;
import com.wisbook.model.cms.Entity;
import com.wisbook.model.order.EntityType;
import com.wisbook.model.order.EntityTypePK;
import com.wisbook.service.goods.detail.AbstractGoodsDetailProvider;
import com.wisbook.service.goods.detail.GoodsDetail;
import com.wisbook.service.goods.detail.GoodsDetailImpl;
import com.wisbook.service.goods.manager.GoodsManager;
import com.wisbook.service.goods.manager.NoSuchAttributeException;
import com.wisbook.service.goods.manager.StoreInfo;
import com.wisbook.util.BookColumnsStr;
import com.wisbook.util.DoubanImageTool;
import com.wisbook.util.Excels;
import com.wisbook.util.PageBean;
import com.wisbook.util.XPath;
/**
 * 通过excel来导入书籍信息<br>
 * excel中的数据格式：isbn+正版本数+盗版本数+复印本数+价格+分类编号<br>
 * 当excel列不够时，会抛出NotEnoughColumnsException,getMessage()将可获得缺少列的信息<br>
 * 当传入非excel文档时，将抛出IsNotExcelFileException
 * 其他的书籍信息从网上抓取
 * @author volador
 *
 */
@Component("excelBookDetailProvider")
public class ExcelBookDetailProvider extends AbstractGoodsDetailProvider implements FileAware{

	//excel file
	private FileInputStream bookExcel;
	//过程是否产生有错误
	private boolean hasError; 
	//过程是否遇到系统中已经存在的商品
	private boolean hasExistedRecord;
	//商品列表value:StoreInfo
	private Map<String,StoreInfo> existedRecords;
	//无法处理的isbn集合
	private List<String> errorIsbnList;
	//错误信息
	private List<String> errorMessage;
	/*
	 * 在准备网上获取信息前，先查看系统中是否已经存在该图书记录了，若有，则不用再从网上获取，直接增加库存。
	 */
	private GoodsManager bookManager;
	private BaseDao<EntityType,EntityTypePK> entityTypeDao;
	private BaseDao<Category,Integer> categoryDao;
	
	@Resource(name="categoryDao")
	public void setCategoryDao(BaseDao<Category, Integer> categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Resource(name="entityTypeDao")
	public void setEntityTypeDao(BaseDao<EntityType, EntityTypePK> entityTypeDao) {
		this.entityTypeDao = entityTypeDao;
	}

	@Resource(name="bookManager")
	public void setBookManager(GoodsManager bookManager) {
		this.bookManager = bookManager;
	}
	
	/**
	 * 调用getGoodsDetails()前必须先调用该方法set进一个excel文件流
	 * @param bookExcel
	 */
	@Override
	public void setFile(FileInputStream bookExcel){
		this.bookExcel=bookExcel;
	}
	@Override
	public void setFile(File file) throws FileNotFoundException{
		this.bookExcel = new FileInputStream(file);
	}
	
	/**
	 * 根据属性获取对应的值，若不存在该属性，则返回值为空串
	 * @param attribute
	 * @param doc
	 * @return
	 */
	private String getNodeValueByAttribute(Attribute attribute,Document doc){
		String attrValue="";
		NodeList dbNodeList=doc.getElementsByTagName("db:attribute");
	    NodeList linkNodeList=doc.getElementsByTagName("link");
	    for(int i=0;i<dbNodeList.getLength();i++){
	    	Node node=dbNodeList.item(i);
	    	if(node.getAttributes().item(0).getNodeValue().equals(attribute.getName())){
	    		attrValue=node.getFirstChild().getNodeValue();
	    		return attrValue;
	    	}
	    }
	    for(int i=0;i<linkNodeList.getLength();i++){
		       Node node=linkNodeList.item(i);
		       if(attribute.getName()!=null&&attribute.getName().equals(XPath.selectText("@rel",node))){
		    	   attrValue=XPath.selectText("@href",node);
		    	   return attrValue;
		       }
		}
	    attrValue=doc.getElementsByTagName(attribute.getName()).item(0).getFirstChild().getNodeValue();
		return attrValue;
	}
	
	@Override
	public List<GoodsDetail> getGoodsDetails() {
		Set<Attribute> attributes=this.getDocument().getAttributes();
		errorIsbnList = new ArrayList<String>();
		errorMessage = new ArrayList<String>();
		existedRecords = new HashMap<String,StoreInfo>();
		hasError = false;
		hasExistedRecord = false;
		//填充商品除attribute之外的其他字段
		List<GoodsDetail> goodsDetailList=Excels.getGoodsDetailList(this.bookExcel,this.errorIsbnList);
		//填充商品attribute字段
		for(int k=0;k<goodsDetailList.size();k++){
			GoodsDetail gd=goodsDetailList.get(k);
			Map<String, Object> map=gd.getContent();
			String isbn=(String)map.get(BookColumnsStr.isbn.value);
			String requestUrl="http://api.douban.com/book/subject/isbn/"+isbn;
			//是否已经在系统中存在,若已经存在，则添加库存后结束当前处理,记得还要移除goodsdetail记录
			if(DealIfExistInDB(isbn,map)){
				goodsDetailList.remove(k);
				k--;
				continue;
			}
			//整理分类，查看标记的分类信息是否存在，若不存在，该条记录出错
			String categoryId = (String) map.get(BookColumnsStr.category_id.value);
			Category category = ((CategoryDao)this.categoryDao).getEntityByBasicCode(categoryId);
			if(category == null){
				this.errorIsbnList.add((String)map.get(BookColumnsStr.isbn.value));
				continue;
			}
			((GoodsDetailImpl)gd).setCategory(category);
			try {
				URL url = new URL(requestUrl);
				URLConnection conn = url.openConnection();    
			    InputStream is = conn.getInputStream();  
			    DocumentBuilderFactory domfac=DocumentBuilderFactory.newInstance();
			    DocumentBuilder domBuder=domfac.newDocumentBuilder();
			    Document doc=domBuder.parse(is);
			    //解析xml，获取attribute对应的值,注意，image要另外拿
			    for(Attribute attr:attributes){
			    	if(attr.getName()!=null &&
			    	   !attr.getName().equals("isbn") &&
			    	   !attr.getName().equals("medium_image") &&
			    	   !attr.getName().equals("large_image")){
			    		String attributeValue = null;
			    		try{
			    				attributeValue = getNodeValueByAttribute(attr,doc);
			    		}catch(Exception e){
			    			attributeValue = "木有属性";
			    		}
				    	map.put(attr.getName(), attributeValue);
			    	}
			    }
			    //填充中图与大图
			    String originalImage = map.get("image").toString();
			    String mediunImage = DoubanImageTool.tracsformToMediumSize(originalImage);
			    String largeImage = DoubanImageTool.transformToLargeSize(originalImage);
			    map.put("medium_image",mediunImage);
			    map.put("large_image",largeImage);
			} catch (IOException e) {
				goodsDetailList.remove(k);
				k--;
				errorIsbnList.add(isbn);
				this.errorMessage.add("UnknownHostException(IOException):"+e.getMessage());
			} catch (ParserConfigurationException e) {
				goodsDetailList.remove(k);
				k--;
				errorIsbnList.add(isbn);
				this.errorMessage.add("ParserConfigurationException:"+e.getMessage());
			} catch (SAXException e) {
				goodsDetailList.remove(k);
				k--;
				errorIsbnList.add(isbn);
				this.errorMessage.add("SAXException:"+e.getMessage());
			}
		}
		if(this.errorIsbnList.size() != 0){
			this.hasError = true;
		}
		if(this.existedRecords.size() != 0){
			this.hasExistedRecord = true;
		}
		
		return goodsDetailList;
	}
	

	/**
	 * 查看该isbn是否已经在商品中存在了,若存在了，则增加库存
	 * @param isbn 书本的isbn
	 * @param content 商品信息，用于获取各个种类商品的数量
	 * @return true/false 是/否
	 */
	private boolean DealIfExistInDB(String isbn,Map<String,Object> content) {
		boolean flag = false;
		//其实只有一个记录
		PageBean<Entity> entitys;
		try {
			entitys = this.bookManager.fetchGoodsByAttribute("isbn",isbn,1,10,false);
		} catch (NoSuchAttributeException e) {
			throw new RuntimeException(e);
		}
		if(entitys!=null && entitys.getList().size() > 0){  //存在，更库存，顺便登记
			Entity entity = entitys.getList().get(0);
			//正版
			int numOfAuthorisedEdition = (Integer) content.get(BookColumnsStr.numOfAuthorisedEdition.value);
			//盗版
			int numOfPirateEdition = (Integer) content.get(BookColumnsStr.numOfPirateEdition.value);
			//复印
			int numOfCopyEdition = (Integer) content.get(BookColumnsStr.numOfCopyEdition.value);
			
			EntityType authorised = entity.getEntityType("authorised_edition");
			EntityType pirate = entity.getEntityType("pirate_edition");
			EntityType copy = entity.getEntityType("copy_edition");
			
			authorised.setStorageCount(authorised.getStorageCount()+numOfAuthorisedEdition);
			pirate.setStorageCount(pirate.getStorageCount()+numOfPirateEdition);
			copy.setStorageCount(copy.getStorageCount()+numOfCopyEdition);
			
			this.entityTypeDao.updateEntity(authorised);
			this.entityTypeDao.updateEntity(pirate);
			this.entityTypeDao.updateEntity(copy);
			//保存存储信息
			StoreInfo info = new StoreInfo(entity,numOfAuthorisedEdition,numOfPirateEdition,numOfCopyEdition);
			
			info.setExistedBefore(true);
			
			this.existedRecords.put(isbn, info);
			
			flag = true;
		}
		return flag;
	}
	@Override
	protected String getDocumentName() {
		return "book";
	}

	@Override
	public boolean hasError() {
		return this.hasError;
	}

	@Override
	public List<String> getErrorContent() {
		return this.errorIsbnList;
	}

	@Override
	public List<String> getErrorMessage() {
		return this.errorMessage;
	}

	@Override
	public boolean hasExistedRecord() {
		return this.hasExistedRecord;
	}

	@Override
	public Map<String, StoreInfo> getExistedRecord() {
		return this.existedRecords;
	}
	

}

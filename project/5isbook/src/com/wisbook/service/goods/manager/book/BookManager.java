package com.wisbook.service.goods.manager.book;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Component;

import com.wisbook.dao.base.BaseDao;
import com.wisbook.dao.cms.CategoryDao;
import com.wisbook.dao.order.GoodsTypeDao;
import com.wisbook.model.cms.Attribute;
import com.wisbook.model.cms.BigTextEntityValue;
import com.wisbook.model.cms.Category;
import com.wisbook.model.cms.Document;
import com.wisbook.model.cms.Entity;
import com.wisbook.model.cms.EntityValue;
import com.wisbook.model.cms.TextEntityValue;
import com.wisbook.model.order.EntityType;
import com.wisbook.model.order.EntityTypePK;
import com.wisbook.model.order.GoodsType;
import com.wisbook.service.goods.detail.GoodsDetail;
import com.wisbook.service.goods.manager.AbstractManager;
import com.wisbook.service.goods.manager.StoreInfo;
import com.wisbook.util.BookColumnsStr;

/**
 * @author volador
 * 
 */
@Component("bookManager")
public class BookManager extends AbstractManager {

	private BaseDao<GoodsType, Integer> goodsTypeDao;
	private CategoryDao categoryDao;
	private Category root;
	
	@Resource(name = "categoryDao")
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Resource(name = "goodsTypeDao")
	public void setGoodsTypeDao(BaseDao<GoodsType, Integer> goodsTypeDao) {
		this.goodsTypeDao = goodsTypeDao;
	}

	@Override
	protected String getDocumentName() {
		return "book";
	}

	@Override
	protected Map<String, StoreInfo> storeProcess(List<GoodsDetail> goods) { // 对于书本来说，所有的资料都是text的，比较好做
		Document doc = this.getDocument();

		Map<String, StoreInfo> successIsbns = new HashMap<String, StoreInfo>();
		// 搞定每一本图书
		for (GoodsDetail detail : goods) {
			Entity entity = new Entity();
			entity.setDocument(doc);
			entity.setPrice(detail.getPrice());
			entity.setCategory(detail.getCategory());
			getEntityDao().saveEntity(entity);

			String isbn = null; // 记录这本书的isbn
			// 填充attr
			for (Attribute attr : doc.getAttributes()) {
				Object attrContent = detail.getContent().get(attr.getName());
				if (attr.getName().equals("isbn"))
					isbn = attrContent.toString();
				if (attrContent != null) { // text的就放在text_entity_value里面，richtext的就放在big_text_entity_value里面
					EntityValue ev = null;
					if (attr.getRaw().getName().equals("text")) {
						ev = new TextEntityValue();
					} else
						ev = new BigTextEntityValue();
					ev.setEntity(entity);
					ev.setAttribute(attr);
					entity.getEntityValues().add(ev);
					ev.setContent(attrContent.toString());
				}
			}

			this.getEntityDao().updateEntity(entity);

			// 填EntityType信息
			int numOfAuthorisedEdition = (Integer) detail.getContent().get(
					BookColumnsStr.numOfAuthorisedEdition.value);
			int numOfPirateEdition = (Integer) detail.getContent().get(
					BookColumnsStr.numOfPirateEdition.value);
			int numOfCopyEdition = (Integer) detail.getContent().get(
					BookColumnsStr.numOfCopyEdition.value);
			
			GoodsType gAuthorised = ((GoodsTypeDao) goodsTypeDao)
					.getGoodsTypeByName("authorised_edition");
			GoodsType gPirate = ((GoodsTypeDao) goodsTypeDao)
					.getGoodsTypeByName("pirate_edition");
			GoodsType gCopy = ((GoodsTypeDao) goodsTypeDao)
					.getGoodsTypeByName("copy_edition");

			entity = createEntityType(entity,gAuthorised,numOfAuthorisedEdition);
			entity = createEntityType(entity,gPirate,numOfPirateEdition);
			entity = createEntityType(entity, gCopy, numOfCopyEdition);

			getEntityDao().updateEntity(entity);
			
			entity.setEntityTypeDao(getEntityTypeDao()); //这个是预留给storeinfo内部使用的
			
			// 记录这次存储过程
			StoreInfo info = new StoreInfo(entity,numOfAuthorisedEdition,numOfPirateEdition,numOfCopyEdition); // 一个detail指代一本书，生成一个info
			
			successIsbns.put(isbn, info);
		}
		return successIsbns;
	}
	/**
	 * 生成EntityType
	 * @param entity 该entity所属于的entity
	 * @param gt 该entitytype的种类
	 * @param stoNum 该entitytype下面的库存
	 * @return 更新后的entity
	 */
	private Entity createEntityType(Entity entity,GoodsType gt,int stoNum){
		EntityType newEt = new EntityType();
		EntityTypePK pk1 = new EntityTypePK();
		pk1.setEntity(entity);
		pk1.setGoodsType(gt);
		newEt.setEntityTypePK(pk1);
		newEt.setStorageCount(stoNum);
		entity.getEntityTypes().add(newEt);
		return entity;
	}
	
	@Override
	public Category getRootCategory() {
		if(root == null)
		root = this.categoryDao.getEntityByBasicCode("1");
		return root;
	}

	/*
	 * 关于图书的获取规则:假设这些节点的所有孩子节点为s，则s的每个节点的孩子节点不能有孩子节点。其实就是树的倒数3层结构
	 */
	@Override
	public List<Category> fetchListCategory() {
		List<Category> list = new ArrayList<Category>();
		fetchList(getRootCategory(), list);
		return list;
	}

	/**
	 * 获取目标列表
	 * 
	 * @param root
	 *            树根
	 * @param content
	 *            容器
	 * @return 目标列表
	 */
	private void fetchList(Category root, List<Category> content) {
		if (isTarget(root)) {
			content.add(root);
		} else {
			if (isExistAndHaveChildren(root)) {
				for (Category son : root.getChildren()) {
					fetchList(son, content);
				}
			}
		}
	}

	/**
	 * 检查该分类是不是我们要找的目标
	 * 
	 * @param category
	 * @return
	 */
	private boolean isTarget(Category category) {
		boolean flag = false;
		if (isExistAndHaveChildren(category)) {
			Set<Category> sons = category.getChildren();
			boolean allSonsHaveNoChild = true;
			boolean stopTrace = false;
			for (Category son : sons) {
				if (isExistAndHaveChildren(son)) {
					if (allSonsHaveNoChild)
						allSonsHaveNoChild = false;
					for (Category subSon : son.getChildren()) {
						if (isExistAndHaveChildren(subSon)) {
							stopTrace = true;
							break;
						}
					}
				}
				if (stopTrace)
					break;
			}
			if (!allSonsHaveNoChild && !stopTrace)
				flag = true;
		}
		return flag;
	}

	private boolean isExistAndHaveChildren(Category category) {
		boolean flag = false;
		if (category != null && category.getChildren() != null
				&& category.getChildren().size() > 0) {
			flag = true;
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Category getCategoryById(int id) {
		Category category = ((BaseDao<Category, Integer>) this.categoryDao)
				.getEnitytById(id);
		try {
			category.getBasicCode();
		} catch (ObjectNotFoundException e) {
			category = null;
		}
		return category;
	}

}

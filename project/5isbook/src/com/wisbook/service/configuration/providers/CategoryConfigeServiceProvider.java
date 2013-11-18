package com.wisbook.service.configuration.providers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import com.wisbook.dao.base.BaseDao;
import com.wisbook.model.cms.Category;
import com.wisbook.service.configuration.AbstractConfigeService;
import com.wisbook.service.configuration.XmlFileFormatException;
import com.wisbook.util.XPath;
@Component("categoryConfigeServiceProvider")
public class CategoryConfigeServiceProvider extends AbstractConfigeService{

	private BaseDao<Category,Integer> categoryDao;
	
	public BaseDao<Category, Integer> getCategoryDao() {
		return categoryDao;
	}

	@Resource(name="categoryDao")
	public void setCategoryDao(BaseDao<Category, Integer> categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	protected void process() throws XmlFileFormatException {
		Node root = this.getRootNode();
		//所有树根的集合
		List<Node> treeList = XPath.selectNodes("/config/node",root);
		for(Node treeNode:treeList){
			String displayName = XPath.selectText("@display_name",treeNode);
			Category tree = new Category();
			tree.setDisplayName(displayName);
			tree.setBasicCode("1"); //所有基本库存码都是从1开始迭代
			tree.setParent(null);
			//把这棵树装配完
			tree = treeIterator("node",treeNode,tree);
			this.categoryDao.saveEntity(tree);
		}
	}

	/**
	 * 遍历树
	 * @param url 儿子的url
	 * @param currentNode 当前的节点
	 * @param currentCategory 当前实体
	 * @return:已经装配好了的当前实体
	 */
	private Category treeIterator(String url,Node currentNode,Category currentCategory){
		List<Node> children = XPath.selectNodes(url,currentNode);
		//没儿子了
		if(children == null || children.size() == 0) return currentCategory;
		int index = 1;
		for(Node node:children){
			String displayName = XPath.selectText("@display_name",node);
			Category son = new Category();
			son.setDisplayName(displayName);
			son.setBasicCode(currentCategory.getBasicCode() + Category.CATEGORY_SEPERATOR + index); //儿子的库存码由父亲的库存码加上自己的迭代吗组成
			son = treeIterator(url,node,son);
			son.setParent(currentCategory);
			currentCategory.getChildren().add(son);
			index++;
		}
		return currentCategory;
	}

}

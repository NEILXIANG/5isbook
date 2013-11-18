package com.wisbook.servlet.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;

import com.wisbook.model.cms.Category;
import com.wisbook.service.goods.manager.GoodsManager;
import com.wisbook.util.springutil.ApplicationContextUtil;

/**
 * 监听项目的起动，启动后立马加载整个分类到servletcontext中
 * 全部分类节点以map的结构保存：map<id,category>,保存在servletcontext中，保存名为"category_nodes"
 * 用于主页主类显示的节点以list的结构保存
 * :list<category>,保存在servletcontext中，保存名为："category_list_nodes"
 * 
 * @author volador
 * 
 */
public class StartupAndLoadCategory implements ServletContextListener {

	public static final String CATEGORY_ALL_NODES_IN_MAP = "all_nodes_in_map";
	public static final String CATEGORY_LIST_NODES = "list_nodes";
	public static final String CATEGORY_LIST_END_NODES = "list_end_nodes";
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {  
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ApplicationContext ap = ApplicationContextUtil.getApplicatoinContext();
		if (ap == null)
			throw new RuntimeException(
					"can not get 'ApplicationContext' instanse by ApplicationContextUtil.getApplicationContext()");
		GoodsManager manager = (GoodsManager) ap.getBean("bookManager");
		Category root = manager.getRootCategory();
		Map<Integer, Category> allCategoryNodes = new HashMap<Integer, Category>(350);
		List<Category> listLastCategory = new ArrayList<Category>(); //存储所有的叶子节点
		iteTree(root, allCategoryNodes,listLastCategory); // 遍历节点，并把节点记录进map里面
		List<Category> listCategory = manager.fetchListCategory(); // 可以用在主页主类上显示的分类
		ServletContext context = event.getServletContext();
		context.setAttribute(CATEGORY_ALL_NODES_IN_MAP, allCategoryNodes);
		context.setAttribute(CATEGORY_LIST_NODES, listCategory);
		context.setAttribute(CATEGORY_LIST_END_NODES, listLastCategory);
	}

	/**
	 * 遍历树，吧每个节点的信息保存在map结构里面,广度遍历
	 * @param root 树根
	 * @param allCategoryNodes 保存数节点信息的map,key是id
	 */
	private void iteTree(Category root, Map<Integer, Category> allCategoryNodes,List<Category> lastNode) {
		if(root != null){
			allCategoryNodes.put(root.getId(), root);
			if(root.getChildren().size() > 0){
				for(Category son : root.getChildren()){
					iteTree(son,allCategoryNodes,lastNode);
				}
			}else{ //叶节点
				lastNode.add(root);
			}
		}
	}

}

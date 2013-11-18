package com.wisbook.action.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.wisbook.model.cms.Category;
import com.wisbook.servlet.listener.StartupAndLoadCategory;

/**
 * @author Ban 后台商品管理
 * 
 */
@Component("adminShowCategory")
@Scope("prototype")
public class ShowAllCategoryAction extends ActionSupport implements
		ServletContextAware, SessionAware {

	private static final long serialVersionUID = 1L;
	private volatile List<Category> categoryList = null;
	private ServletContext servletContext;

	private Map<String, Object> session;

	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		this.categoryList = (List<Category>) this.servletContext
				.getAttribute(StartupAndLoadCategory.CATEGORY_LIST_NODES);
		session.put("categoryList", categoryList);
		return Action.SUCCESS;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	@Override
	public void setServletContext(ServletContext servleContext) {
		this.servletContext = servleContext;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

}

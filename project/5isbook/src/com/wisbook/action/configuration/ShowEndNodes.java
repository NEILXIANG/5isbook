package com.wisbook.action.configuration;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.wisbook.servlet.listener.StartupAndLoadCategory;
/**
 * 展示叶节点信息
 * @author volador
 *
 */
@Component("com.wisbook.action.configuration.showEndNodes")
@Scope("prototype")
public class ShowEndNodes extends ActionSupport implements ServletContextAware{

	private static final long serialVersionUID = 1L;
	private ServletContext context;
	private List<Category> endNodes;
	
	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		endNodes = (List<Category>) context.getAttribute(StartupAndLoadCategory.CATEGORY_LIST_END_NODES);
		return Action.SUCCESS;
	}
	
	public List<Category> getEndNodes() {
		return endNodes;
	}
}

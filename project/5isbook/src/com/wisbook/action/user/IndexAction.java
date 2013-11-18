package com.wisbook.action.user;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 用户中心首页 
 * @author volador
 *
 */
@Component("com.wisbook.action.user.indexAction")
@Scope("prototype")
public class IndexAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	@Override
	public String execute() throws Exception {
		return Action.SUCCESS;
	}

}

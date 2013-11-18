package com.wisbook.action.user;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.wisbook.service.user.UserService;
/**
 * 删除地址
 * @author volador
 *
 */
@Component("com.wisbook.action.user.deleteAddress")
@Scope("prototype")
public class DeleteAddress extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	private UserService userService;
	private Map<String,Object> session;

	private int id; //删除的id
	
	@Override
	public String execute() throws Exception {
		if(id == 0) return Action.ERROR;
		String account = (String) session.get("ACEGI_SECURITY_LAST_USERNAME");
		boolean result = userService.deleteAddress(account, id);
		return result?Action.SUCCESS:Action.ERROR;
	}

	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setId(int id) {
		this.id = id;
	}
}

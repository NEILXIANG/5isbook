package com.wisbook.action.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.wisbook.model.user.Address;
import com.wisbook.service.user.UserService;
/**
 * 地址中心首页
 * @author volador
 *
 */
@Component("com.wisbook.action.user.addressIndex")
@Scope("prototype")
public class AddressIndex extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	private UserService userService;
	private Map<String,Object> session;
	private List<Address> addresses;
	
	@Override
	public String execute() throws Exception {
		String account = (String) session.get("ACEGI_SECURITY_LAST_USERNAME");
		addresses = userService.getAllAddress(account);
		return Action.SUCCESS;
	}

	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public List<Address> getAddresses() {
		return addresses;
	}
}

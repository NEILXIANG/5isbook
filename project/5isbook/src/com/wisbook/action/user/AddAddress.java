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
 * 添加一个新地址
 * @author volador
 *
 */
/**
 * @author lenovo
 *
 */
@Component("com.wisbook.action.user.addAddress")
@Scope("prototype")
public class AddAddress extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	private UserService userService;
	private Map<String,Object> session;

	private String realName; //正式姓名
	private String address; //地址
	private String longTel; //长号
	private String shortTel; //短号
	
	private boolean validateResult;
	
	@Override
	public String execute() throws Exception {
		if(!validateResult) return Action.INPUT;
		String account = (String) session.get("ACEGI_SECURITY_LAST_USERNAME");
		boolean result = userService.addAddress(account, realName, address, longTel, shortTel);
		return result?Action.SUCCESS:Action.ERROR;
	}
	
	@Override
	public void validate() { //校验信息
		validateResult = 
			   realName != null && realName.length() > 0 &&
			   address != null && address.length() > 0 &&
			   (longTel != null || longTel.length() > 0 ||
			   shortTel != null || shortTel.length() > 0);		
	}
	
	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLongTel() {
		return longTel;
	}

	public void setLongTel(String longTel) {
		this.longTel = longTel;
	}

	public String getShortTel() {
		return shortTel;
	}

	public void setShortTel(String shortTel) {
		this.shortTel = shortTel;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
}

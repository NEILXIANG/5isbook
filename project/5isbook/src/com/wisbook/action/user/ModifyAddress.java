package com.wisbook.action.user;

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
 * 修改地址
 * @author volador
 *
 */
@Component("com.wisbook.action.user.modifyAddress")
@Scope("prototype")
public class ModifyAddress extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	private Map<String,Object> session;
	private UserService userService;
	
	private String id;
	private String realName;
	private String address;
	private String longTel;
	private String shortTel;
	private String formFlag; //是否是表单传输过来的
	
	@Override
	public String execute() throws Exception {
		String account = (String) session.get("ACEGI_SECURITY_LAST_USERNAME");
		String ret = Action.SUCCESS;
		if(id == null || id.length() <=0 || "0".equals(id)) return Action.ERROR;
		int realId = 0;
		try{
			realId = Integer.parseInt(id);
		}catch(Exception e){
			return Action.ERROR;
		}
		if(formFlag == null || formFlag.length() <= 0){ //首次请求，显示数据
			Address addr = userService.getAddress(account, realId);
			if(addr == null) return Action.ERROR;
			realName = addr.getRealName();
			address = addr.getAddress();
			longTel = addr.getLongTel();
			shortTel = addr.getShortTel();
			ret = Action.INPUT;
		}else{ //表单传数据来了，更新数据吧
			boolean result = userService.modifyAddress(account, realId, realName, address, longTel, shortTel);
			if(!result) ret = Action.ERROR; 
		}
		return ret;
	}
	
	@Override
	public void validate() { //如果不是从表单过来，填值，不做校验
		if(formFlag != null && formFlag.length() > 0){
			if(realName == null || realName.length() <= 0) addFieldError("realName", "为方便我们配送，真实姓名不能为空");
			else if(address == null || address.length() <= 0) addFieldError("address", "为方便我们配送，配送地址不能为空");
			else if(longTel == null || longTel.length() <= 0) addFieldError("longTel", "为方便我们配送，留下您的长号吧");
			else if(shortTel == null || shortTel.length() <= 0) addFieldError("shortTel", "为方便我们配送，留下您的短号吧");
		}
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getFormFlag() {
		return formFlag;
	}

	public void setFormFlag(String formFlag) {
		this.formFlag = formFlag;
	}
	
	
}

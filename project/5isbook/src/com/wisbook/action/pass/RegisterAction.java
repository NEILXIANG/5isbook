package com.wisbook.action.pass;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wisbook.dto.UserDto;
import com.wisbook.service.user.UserService;
import com.wisbook.util.AccountFormat;
@Component("com.wisbook.action.pass.registerAction")
@Scope("prototype")
public class RegisterAction extends ActionSupport implements ModelDriven<UserDto>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDto userDto = new UserDto(); 
	private UserService userService;
	private AccountFormat formatter;
	private String message = "注册成功咯~";
	
	public String execute(){
		String account = this.userDto.getAccount();
		String password = this.userDto.getPassword();
		this.userService.registryUser(account, password);
		return Action.SUCCESS;
	}
	
	@Override
	public void validate() {
		if(!isExist(userDto.getAccount())) this.addFieldError("account","亲，忘记填账号啦.");
		else if(!isExist(userDto.getPassword())) this.addFieldError("password","亲，忘记填密码啦.");
		else if(!isExist(userDto.getRePassword())) this.addFieldError("rePassword","亲，忘记重复密码啦.");
		else if(!this.formatter.testFormat(userDto.getAccount())) this.addFieldError("account",this.formatter.getErrorMessage());
		else if(!this.userDto.getPassword().equals(this.userDto.getRePassword())) this.addFieldError("password","亲，密码跟重复密码要一样哦.");
		else if(this.userService.accountExist(this.userDto.getAccount())) this.addFieldError("account","亲，这个账号被人用了，换过一个吧.");
	}
	
	private boolean isExist(String str){
		if(str != null && str.length() >0) return true;
		return false;
	}
	
	
	public String getMessage() {
		return message;
	}

	@Resource(name="emailFormat")
	public void setFormatter(AccountFormat formatter) {
		this.formatter = formatter;
	}

	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	@Override
	public UserDto getModel() {
		return this.userDto;
	}

}

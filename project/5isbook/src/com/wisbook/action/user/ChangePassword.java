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
 * 更改密码
 * @author volador
 *
 */
@Component("com.wisbook.action.user.changePassword")
@Scope("prototype")
public class ChangePassword extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private Map<String,Object> session;
	private UserService userService;
	
	private String originalPass; //原始密码
	private String newPass; //新密码
	private String reNewPass; //重复新密码
	
	private String message;
	private boolean validateResult;
	
	
	@Override
	public String execute() throws Exception {
		String ret = Action.SUCCESS;
		if(validateResult) return Action.INPUT;
		String account = (String) session.get("ACEGI_SECURITY_LAST_USERNAME");
		boolean result = userService.changePassword(account, originalPass,newPass);
		if(result){
			message = "更改成功~";
		}else{
			validateResult = true;
			message = "更改失败:原密码错误.";
			ret = Action.INPUT;
		}
		//清空数据
		return ret;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void validate() {
		validateResult = false;
		if(originalPass == null || originalPass.length() <= 0){
			message = "原始密码不能为空";
			validateResult = true;
		}
		else if(newPass == null || newPass.length() <= 0){
			message = "新密码不能为空";
			validateResult = true;
		}
		else if(reNewPass == null || reNewPass.length() <= 0){
			message = "重复新密码不能为空";
			validateResult = true;
		}
		else if(!newPass.equals(reNewPass)){
			message = "新密码与重复密码不匹配.";
			validateResult = true;
		}
	}

	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getOriginalPass() {
		return originalPass;
	}

	public void setOriginalPass(String originalPass) {
		this.originalPass = originalPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getReNewPass() {
		return reNewPass;
	}

	public void setReNewPass(String reNewPass) {
		this.reNewPass = reNewPass;
	}

	public String getMessage() {
		return message;
	}

	public boolean isValidateResult() {
		return validateResult;
	}
	
}

package com.wisbook.action.admin;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.wisbook.service.user.UserService;
import com.wisbook.util.AccessErrorException;

/**
 * @author Ban 后台管理员更改自己密码
 */
@Component("changePassword")
@Scope("prototype")
public class ChangePasswordAction extends ActionSupport   {
	private static final long serialVersionUID = 1L;

	private UserService userService;
	private String oldPassword;
	private String newPassword;
	private String newPasswordRepeat;
	private String message;
	private String adminAccount;
	
	public String execute() throws AccessErrorException {
		boolean ok = userService.changePassword(adminAccount,
				oldPassword, newPassword);
		// 保存更改是否成功
		if (ok)
			message = "成功更改密码！";
		else
			message = "更改密码失败，可能是你的原密码不对！";
		return SUCCESS;
	}

	/**
	 * 验证注册用户的数据
	 */
	@Override
	public void validate() {
		System.out.println(oldPassword +"\n"+newPassword+"\n"+newPasswordRepeat);
		if (!isExist(oldPassword))
			this.addFieldError("oldPassword", "sorry,旧密码不能为空.");
		else if (!isExist(newPassword))
			this.addFieldError("newPassword", "sorry,新密码不能为空.");
		else if (!isExist(newPasswordRepeat))
			this.addFieldError("newPasswordRepeat", "sorry,确认密码不能为空.");
		else if (!newPassword.equals(newPasswordRepeat))
			this.addFieldError("newPassword", "sorry,两次输入的密码不相同.");
	}

	private boolean isExist(String str) {
		if (str != null && str.length() > 0)
			return true;
		return false;
	}

	@Resource(name = "userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}

	public String getAdminAccount() {
		return adminAccount;
	}

	public void setAdminAccount(String adminAccount) {
		this.adminAccount = adminAccount;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordRepeat() {
		return newPasswordRepeat;
	}

	public void setNewPasswordRepeat(String newPasswordRepeat) {
		this.newPasswordRepeat = newPasswordRepeat;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

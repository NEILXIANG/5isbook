package com.wisbook.action.admin;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.wisbook.service.user.UserService;
import com.wisbook.util.AccessErrorException;
import com.wisbook.util.AccountFormat;

/**
 * @author Ban 后台用户管理
 */
@Component("addAdminUser")
@Scope("prototype")
public class AddAdminUserAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	private String account;
	private String password;
	private String rePassword;
	private AccountFormat formatter;
	// 该次登录的管理员账号 用于测试
	private String adminAccount ;
	private String message;

	/**
	 * 新增一个管理员，只有超级用户才有权限 此处有问题，因为新增时只设置了用户，没有设置权限
	 * */
	public String execute() throws AccessErrorException {
		userService.registryAdmin(adminAccount, account, password);
		message = " 注册成功~ 请牢记你的账号:" + account;
		return SUCCESS;
	}

	/**
	 * 验证注册用户的数据
	 */
	@Override
	public void validate() {
		if (!isExist(account))
			this.addFieldError("account", "sorry,账号不能为空.");
		else if (!isExist(password))
			this.addFieldError("password", "sorry,密码不能为空.");
		else if (!isExist(rePassword))
			this.addFieldError("rePassword", "sorry,确认密码不能为空.");
		else if (!this.formatter.testFormat(account))
			this.addFieldError("account",
					this.formatter.getErrorMessage());
		else if (!password.equals(rePassword))
			this.addFieldError("password", "sorry,两次输入的密码不相同.");
		else if (userService.accountExist(account))
			this.addFieldError("account", "sorry,该用户已经被注册");
	}

	private boolean isExist(String str) {
		if (str != null && str.length() > 0)
			return true;
		return false;
	}

	@Resource(name = "emailFormat")
	public void setFormatter(AccountFormat formatter) {
		this.formatter = formatter;
	}

	@Resource(name = "userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAdminAccount() {
		return adminAccount;
	}

	public void setAdminAccount(String adminAccount) {
		this.adminAccount = adminAccount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public AccountFormat getFormatter() {
		return formatter;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

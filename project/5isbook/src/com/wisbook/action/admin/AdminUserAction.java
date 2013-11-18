package com.wisbook.action.admin;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wisbook.model.user.User;
import com.wisbook.model.user.UserInfo;
import com.wisbook.service.user.UserService;
import com.wisbook.util.AccessErrorException;
import com.wisbook.util.PageBean;

/**
 * @author Ban 后台用户管理
 */
@Component("adminUser")
@Scope("prototype")
public class AdminUserAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	// 分页Bean
	private PageBean<User> pageBean;
	// 用户列表
	private List<User> list;
	// 分页列表
	private List<Integer> pageList;
	// 用户列表的当前页,默认第一页
	private int pageIndex = 1;
	// 用户列表的分页大小，默认为5
	private int pageSize = 10;
	// 接受从JSP传过来的需要操作的用户名
	private String userAccount;
	// 根据角色（管理员，超级管理员，普通用户） 查看不同的用户列表
	private String roleName;
	// 该次登录的管理员账号 
	private String adminAccount ;
	private Map<String, Object> session;

	/**
	 * 获取指定角色的所有用户信息，以分页的形式展示
	 * */
	public String gotoPage() {
		pageBean = userService.getUsersByRole(roleName, pageIndex, pageSize);
		if (pageBean != null) {
			pageList = pageBean.getPageNumberList();
			list = pageBean.getList();
		}
		return SUCCESS;
	}

	/**
	 * 已登录的管理员的个人中心
	 * 
	 * @throws AccessErrorException
	 * */
	public String myInfo() throws AccessErrorException {
		UserInfo adminUserInfo = userService.getUserInfoByAccount(adminAccount);
		User adminUser = userService.getUserByAccount(adminAccount,
				adminAccount);
		session.put("adminUser", adminUser);
		session.put("adminUserInfo", adminUserInfo);
		return "myInfo";
	}

	/**
	 * 激活或屏蔽 用户，视乎用户当前状态，更改后刷新页面，此处用AJAX处理更好 暂时用于测试
	 * */
	public void changeEnabled() throws AccessErrorException {
		System.out.println(ServletActionContext.getRequest().getParameter(
				"userAccount")
				+ "......................");
		userService.tiggleEnabled(adminAccount, userAccount);
	}

	/**
	 * 管理员更改自己密码
	 * */
	public String changePassword() throws AccessErrorException {
		String oldPassword = (String) ServletActionContext.getRequest()
				.getParameter("oldPassword");
		String newPassword = (String) ServletActionContext.getRequest()
				.getParameter("newPassword");
		boolean ok = userService.changePassword(userAccount, oldPassword,
				newPassword);
		ActionContext.getContext().getSession().put("ok", ok);
		return "changePassword";
	}

	@Resource(name = "userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}

	public PageBean<User> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<User> pageBean) {
		this.pageBean = pageBean;
	}
	 
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}

	public List<Integer> getPageList() {
		return pageList;
	}

	public void setPageList(List<Integer> pageList) {
		this.pageList = pageList;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getAdminAccount() {
		return adminAccount;
	}

	public void setAdminAccount(String adminAccount) {
		this.adminAccount = adminAccount;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		this.session = map;
		this.adminAccount=(String) session.get("ACEGI_SECURITY_LAST_USERNAME");
	}

	public Map<String, Object> getSession() {
		return session;
	}

}

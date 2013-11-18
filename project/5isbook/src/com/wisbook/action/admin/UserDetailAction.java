package com.wisbook.action.admin;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.wisbook.model.user.Address;
import com.wisbook.model.user.LoginInfo;
import com.wisbook.model.user.UserInfo;
import com.wisbook.service.user.UserService;
import com.wisbook.util.AccessErrorException;
import com.wisbook.util.PageBean;

/**
 * @author Ban 用户个人中心，展示用户详细信息 管理员才有此权限
 */
@Component("userDetail")
@Scope("prototype")
public class UserDetailAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	// 用户账号
	private String userAccount;
	// 该次登录的管理员账号
	private String adminAccount;
	// 用户登录信息列表当前页
	private int pageIndex = 1;
	// 用户登录信息列表分页大小,默认为10
	private int pageSize = 10;
	// 用户个人详细信息
	private UserInfo userInfo;
	// 用户地址信息
	private Set<Address> userAddress;
	// 登录信息列表
	private List<LoginInfo> list;
	// 登录信息列表链
	private List<Integer> pageList;
	private PageBean<LoginInfo> pageBean;
	// 用户的角色，是管理员还是普通会员
	private String roleName;
	private Map<String, Object> session;

	/**
	 * 获取指定用户的详细信息 包括个人信息和地址信息 根据role的不同，跳至不同页面
	 * */
	public String getInfo() throws AccessErrorException {
		// 获取个人信息
		userInfo = userService.getUserInfoByAccount(userAccount);
		// 获取地址信息
		userAddress = userService.getUserByAccount(adminAccount, userAccount)
				.getAddresses();
		if (roleName.equals("ROLE_ADMIN")
				|| roleName.equals("ROLE_SUPER_ADMIN"))
			return "adminDetailInfo";
		else
			return "commontUserDetailInfo";
	}

	/**
	 * 获取指定用户的登录信息，分页显示
	 * */
	public String getLoginInfo() {
		pageBean = userService.getLoginInfosByAccount(userAccount, pageIndex,
				pageSize);
		if (pageBean != null) {
			pageList = pageBean.getPageNumberList();
			list = pageBean.getList();
		}
		return "loginInfo";
	}

	@Resource(name = "userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getAdminAccount() {
		return adminAccount;
	}

	public void setAdminAccount(String adminAccount) {
		this.adminAccount = adminAccount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserAddress(Set<Address> userAddress) {
		this.userAddress = userAddress;
	}

	public Set<Address> getUserAddress() {
		return userAddress;
	}

	public List<LoginInfo> getList() {
		return list;
	}

	public void setList(List<LoginInfo> list) {
		this.list = list;
	}

	public List<Integer> getPageList() {
		return pageList;
	}

	public void setPageList(List<Integer> pageList) {
		this.pageList = pageList;
	}

	public PageBean<LoginInfo> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<LoginInfo> pageBean) {
		this.pageBean = pageBean;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		this.session = map;
		this.adminAccount = (String) session
				.get("ACEGI_SECURITY_LAST_USERNAME");
	}

	public Map<String, Object> getSession() {
		return session;
	}

}

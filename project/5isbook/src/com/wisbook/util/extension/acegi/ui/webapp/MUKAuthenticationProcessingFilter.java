package com.wisbook.util.extension.acegi.ui.webapp;

import java.util.List;  

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.ui.webapp.AuthenticationProcessingFilter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.wisbook.model.user.LoginInfo;
import com.wisbook.model.user.User;
import com.wisbook.model.user.UserInfo;
import com.wisbook.service.user.UserService;
import com.wisbook.util.extension.acegi.providers.UserKindComparisonAware;

/**
 * 该类继承了AuthenticationProcessingFilter，实现功能：
 * 在校验过后，根据当前请求的j_userkind重定向到其对应的链接上,链接在UserKindToPath类上声明
 * 同时，在这里进行登录登记
 * @author volador
 * 
 */
public class MUKAuthenticationProcessingFilter extends
		AuthenticationProcessingFilter implements UserKindComparisonAware,ApplicationContextAware{

	private List<UserKindToPath> userKindToPathList;
	private String currentUserKind = "1"; // default
	private UserService userService;

	public List<UserKindToPath> getUserKindToPathList() {
		return userKindToPathList;
	}

	/*
	 * 由spring进行注入
	 */
	public void setUserKindToPathList(List<UserKindToPath> userKindToPathList) {
		this.userKindToPathList = userKindToPathList;
		// 这两个参数初始话必须不能为空
		this.setDefaultTargetUrl("/_");
		this.setAuthenticationFailureUrl("/_");
	}

	@Override
	public void setExpectedUserKind(String expectedUserKind) {
	}

	@Override
	public void setCurrentUserKind(String currentUserKind) {
		// 如果确定了当前的userkind，则确定defaultTargetUrl与authenticationFailureUrl
		this.currentUserKind = currentUserKind;
		for (UserKindToPath u : this.userKindToPathList) {
			if (this.currentUserKind.equals(u.getUserKind())) {
				this.setDefaultTargetUrl(u.getDefaultTargetUrl());
				this.setAuthenticationFailureUrl(u.getAuthenticationFailureUrl());
				break;
			}
		}
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request)
			throws AuthenticationException { //还要做一下登录登记工作
		//记录登录信息，若没抛错，则登录登录信息
		Authentication auth = super.attemptAuthentication(request);
		putMessageToSession(auth,request);
		return auth;
	}
	/*
	 * 往session里面添加信息:
	 * 1.本次登录信息
	 * 2.上次登录信息
	 * 3.用户亲昵
	 */
	void putMessageToSession(Authentication auth,HttpServletRequest request){
		String currentIp = request.getRemoteAddr();
		String account = auth.getName();
		HttpSession session = request.getSession();
		session.setAttribute("last_login_info",userService.getTopLoginInfo(account));
		LoginInfo lf = this.userService.addLoginMessage(account, currentIp);
		session.setAttribute("login_info", lf);
		User user = lf.getUser();
		UserInfo ui = user.getUserInfo();
		String nickName = account;
		if(ui != null && ui.getNickName() != null){
			nickName = ui.getNickName();
		}
		session.setAttribute("NICK_NAME", nickName);
	}

	/**
	 * 重写父类的判断当前url是否应该进行拦截的规则，方便不同页面的使用 拦截规则： 若FilterProcessesUrl以‘/’开头，则匹配绝对路径
	 * 否则，匹配相对路径
	 */
	@Override
	protected boolean requiresAuthentication(HttpServletRequest request,
			HttpServletResponse response) {
		String uri = request.getServletPath();
		int pathParamIndex = uri.indexOf(';');
		// 去掉jsessionId
		if (pathParamIndex > 0) {
			uri = uri.substring(0, pathParamIndex);
		}
		boolean result = false;
		if (this.getFilterProcessesUrl().charAt(0) == '/') {
			result = uri.equals(this.getFilterProcessesUrl());
		} else {
			result = uri.endsWith(this.getFilterProcessesUrl());
		}
		return result;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.userService = (UserService) applicationContext.getBean("userService");
	}

}

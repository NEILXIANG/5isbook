package com.wisbook.util.extension.acegi.ui.webapp;
/**
 * 封装userkind所对应的defaultTargetUrl与authenticationFailureUrl的pojo
 * @author volador
 *
 */
public class UserKindToPath {
	private String userKind;
	private String defaultTargetUrl;
	private String authenticationFailureUrl;
	
	public String getUserKind() {
		return userKind;
	}
	public void setUserKind(String userKind) {
		this.userKind = userKind;
	}
	public String getDefaultTargetUrl() {
		return defaultTargetUrl;
	}
	public void setDefaultTargetUrl(String defaultTargetUrl) {
		this.defaultTargetUrl = defaultTargetUrl;
	}
	public String getAuthenticationFailureUrl() {
		return authenticationFailureUrl;
	}
	public void setAuthenticationFailureUrl(String authenticationFailureUrl) {
		this.authenticationFailureUrl = authenticationFailureUrl;
	}
	
}

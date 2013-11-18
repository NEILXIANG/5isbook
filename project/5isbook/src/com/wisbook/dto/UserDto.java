package com.wisbook.dto;

/**
 * @author root
 *
 */
public class UserDto {
	//for login
	private String account;
	private String password;
	private String rePassword; 
	
	
	
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

package com.wisbook.model.user;

import java.text.SimpleDateFormat;
import java.util.Date; 
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * 用户登录记录
 * @author volador
 *
 */
@Entity
@Table(name="login_info")
public class LoginInfo {
	
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	private int id;
	private User user;
	//登录ip
	private String ip;
	//登录时间
	private Date loginTime;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="user_account",nullable=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Column(length=15,nullable=false)
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="login_time")
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	public String formatLoginTime(){
		return new SimpleDateFormat(DATE_FORMAT).format(loginTime);
	}
}

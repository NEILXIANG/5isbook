package com.wisbook.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 用户地址,被用于用户订单上用户配送地址的选择
 * @author volador
 *
 */
@Entity
@Table(name="address")
public class Address {
	private int id;
	private User user;
	//真实姓名
	private String realName;
	//地址
	private String address;
	//联系方式(长号)
	private String longTel;
	//短号
	private String shortTel;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_account",nullable=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Column(name="real_name",length=23,nullable=false)
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	@Column(length=255,nullable=false,name="address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(length=32,name="long_tel")
	public String getLongTel() {
		return longTel;
	}
	public void setLongTel(String longTel) {
		this.longTel = longTel;
	}
	@Column(length=32,name="short_tel")
	public String getShortTel() {
		return shortTel;
	}
	public void setShortTel(String shortTel) {
		this.shortTel = shortTel;
	}
	
}

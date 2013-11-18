package com.wisbook.model.order;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 同一件商品里面的不同种类，比如一本书，有正版的，盗版的，复印的
 * @author volador
 *
 */
@Entity
@Table(name="goods_type")
public class GoodsType {
	private int id; //唯一id
	private String storageCode; //在库存中所用的标号
	private String name; //该种类的名字
	private String displayName; //该种类的别名
	private BigDecimal discount;  //该种种类的折扣 3.8
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="storage_code")
	public String getStorageCode() {
		return storageCode;
	}
	public void setStorageCode(String storageCode) {
		this.storageCode = storageCode;
	}
	@Column(name="name",nullable=false,length=32)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="display_name",nullable=false,length=255)
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	@Column(name="discount")
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
}

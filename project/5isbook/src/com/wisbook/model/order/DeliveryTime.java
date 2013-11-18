package com.wisbook.model.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 配送时间
 * @author volador
 *
 */
@Entity
@Table(name="delivery_time")
public class DeliveryTime {
	private int id;
	//时间，比如上午，下午，晚上
	private String time;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(length=16,nullable=false)
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		return time;
	}
}

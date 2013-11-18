package com.wisbook.model.order;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.wisbook.model.user.User;

/**
 * 某订单的状态
 * @author volador
 *
 */
@Entity
@Table(name="order_state_admin")
public class OrderStateAdmin {
	//此次状态是谁操作的
	private User admin;
	//操作时间
	private Date opTime;
	//id
	private OrderStateAdminPK orderStateAdminPK;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="time")
	public Date getOpTime() {
		return opTime;
	}
	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="account",nullable=false)
	public User getAdmin() {
		return admin;
	}
	public void setAdmin(User admin) {
		this.admin = admin;
	}
	@EmbeddedId
	public OrderStateAdminPK getOrderStateAdminPK() {
		return orderStateAdminPK;
	}
	public void setOrderStateAdminPK(OrderStateAdminPK orderStateAdminPK) {
		this.orderStateAdminPK = orderStateAdminPK;
	}
	
}

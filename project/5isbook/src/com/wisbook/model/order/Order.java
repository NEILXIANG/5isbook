package com.wisbook.model.order;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.wisbook.model.user.*;

/**
 * 订单
 * @author volador
 *
 */
@javax.persistence.Entity
@Table(name="order_")
public class Order {
	private int id;

	//配送时间
	private DeliveryTime deliveryTime;
	//订单产生时间
	private Date createTime;
	//订单配送价格
	private BigDecimal deliveryPrice;
	//产生订单的user
	private User user;
	//下单人真实姓名
	private String realName;
	//下单人地址
	private String address;
	//下单人长号
	private String longTel;
	//下单人短号
	private String shortTel;
	//订单子项
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();
	//当前订单在状态
	private OrderState orderState;
	//订单状态列表[管理员操作记录]
	private Set<OrderStateAdmin> orderStateAdmin = new HashSet<OrderStateAdmin>();
	
	//---------
	public BigDecimal totalPrice(){
		BigDecimal price = new BigDecimal("0");
		if(this.orderItems.size() >=1){
			for(OrderItem item:orderItems){
				price = price.add(item.calculatePrice());
			}
		}
		price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
		return price;
	}
	
	private static String DATE_FOEMAT = "yyyy-MM-dd HH:mm:ss";
	public String showCreateTime(){
		return new SimpleDateFormat(DATE_FOEMAT).format(createTime);
	}
	//---------
	
	
	@ManyToOne
	@JoinColumn(name="order_state_id",nullable=false)
	public OrderState getOrderState() {
		return orderState;
	}
	@Column(name="real_name",length=32)
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name="address",length=255)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name="long_tel",length=32)
	public String getLongTel() {
		return longTel;
	}

	public void setLongTel(String longTel) {
		this.longTel = longTel;
	}

	@Column(name="short_tel",length=32)
	public String getShortTel() {
		return shortTel;
	}

	public void setShortTel(String shortTel) {
		this.shortTel = shortTel;
	}

	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}
	
	@OneToMany(mappedBy="orderItemPK.order")
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="user_account",nullable=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="delivery_time_id",nullable=false)
	public DeliveryTime getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(DeliveryTime deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time",nullable=false)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="orderStateAdminPK.order")
	public Set<OrderStateAdmin> getOrderStateAdmin() {
		return orderStateAdmin;
	}
	
	public void setOrderStateAdmin(Set<OrderStateAdmin> orderStateAdmin) {
		this.orderStateAdmin = orderStateAdmin;
	}
	
	@Column(name="delivery_price",nullable=false)
	public BigDecimal getDeliveryPrice() {
		return deliveryPrice;
	}
	public void setDeliveryPrice(BigDecimal deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	
}

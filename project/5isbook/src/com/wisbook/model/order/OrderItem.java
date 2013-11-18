package com.wisbook.model.order;

import javax.persistence.Column;  
import javax.persistence.EmbeddedId;
import javax.persistence.Table;

import java.math.BigDecimal;

/**
 * 订单的子项，一个订单可以有很多订单子项，每个子项一本书
 * @author volador
 *
 */
@javax.persistence.Entity
@Table(name="order_item")
public class OrderItem {
	//id
	private OrderItemPK orderItemPK;
	//购买数量
	private int buyNum;
	//库存码项，仓库管理员可以根据这个东东找到图书
	private String stoCodeList;
	
	public BigDecimal calculatePrice(){
		BigDecimal entityPrice = this.orderItemPK.getEntity().getPrice();
		BigDecimal discount = this.getOrderItemPK().getEntityType().getEntityTypePK().getGoodsType().getDiscount();
		return entityPrice.multiply(discount).multiply(new BigDecimal(this.buyNum+"")).setScale(2,BigDecimal.ROUND_HALF_UP);
	}

	@Column(name="sto_code_list",length=256)
	public String getStoCodeList() {
		return stoCodeList;
	}

	public void setStoCodeList(String stoCodeList) {
		this.stoCodeList = stoCodeList;
	}



	@EmbeddedId
	public OrderItemPK getOrderItemPK() {
		return orderItemPK;
	}
	public void setOrderItemPK(OrderItemPK orderItemPK) {
		this.orderItemPK = orderItemPK;
	}
	@Column(name="buy_num" ,nullable=false)
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
}

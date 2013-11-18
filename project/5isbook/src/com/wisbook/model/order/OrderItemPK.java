package com.wisbook.model.order;

import java.io.Serializable; 

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;


import com.wisbook.model.cms.Entity;
/**
 * OrderItem的主键
 * @author volador
 *
 */
@Embeddable
public class OrderItemPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//从属于哪个订单
	private Order order;
	//从属于那件商品
	private Entity entity;
	//购买商品的种类
	private EntityType entityType;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	@ManyToOne
	@JoinColumn(name="entity_id")
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	@ManyToOne
	@JoinColumns(
			{ 
				@JoinColumn(name="entity_id_2",referencedColumnName="entity_id"),
				@JoinColumn(name="goods_type_id",referencedColumnName="goods_type_id")
			}
	)
	public EntityType getEntityType() {
		return entityType;
	}
	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + (order == null?0:order.hashCode());
		result = 37 * result + (entity == null?0:entity.hashCode());
		result = 37 * result + (entityType == null?0:entityType.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof OrderItemPK)) return false;
		OrderItemPK opk = (OrderItemPK)obj;
		return 
				entity.getId() == opk.getEntity().getId() && 
				order.getId() == opk.getOrder().getId() && 
				getEntityType().equals(opk.getEntityType());
	}
}

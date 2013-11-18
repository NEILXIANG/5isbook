package com.wisbook.model.order;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.wisbook.model.cms.Entity;

@Embeddable
public class EntityTypePK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GoodsType goodsType;
	private Entity entity;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="entity_id")
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="goods_type_id")
	public GoodsType getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(GoodsType goodsType) {
		this.goodsType = goodsType;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof EntityTypePK)) return false;
		EntityTypePK ot = (EntityTypePK)obj;
		return 
				entity.getId() == ot.getEntity().getId() &&
				this.goodsType.getId() == ot.getGoodsType().getId();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
        int result = 1;
        result = prime * result + ((entity == null) ? 0 : this.entity.hashCode());
        result = prime * result + ((goodsType == null) ? 0 : this.goodsType.hashCode());
        return result;

	}
}

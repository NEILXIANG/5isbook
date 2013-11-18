package com.wisbook.model.cms;

import javax.persistence.CascadeType; 
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * cms-实体内容
 * @author volador
 *
 */
@javax.persistence.Entity
@Table(name="entity_value")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class EntityValue {
	private int id;
	private Entity entity;
	private Attribute attribute;
	
	@Transient
	public abstract String getContent();
	public abstract void setContent(String content);
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="entity_id",nullable=false)
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="attribute_id",nullable=false)
	public Attribute getAttribute() {
		return attribute;
	}
	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}
	
	@Override
	public String toString() {
		return this.getContent();
	}
	
}

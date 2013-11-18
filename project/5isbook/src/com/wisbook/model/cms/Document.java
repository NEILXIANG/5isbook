package com.wisbook.model.cms;

import java.util.HashSet;
import java.util.Set; 

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * cms-文档
 * @author volador
 *
 */
@javax.persistence.Entity
@Table(name="document")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="com.wisbook.model.cms.document_type.cache")
public class Document {
	private int id;
	private String name;
	private String displayName;
	private Set<Attribute> attributes = new HashSet<Attribute>();
	private Set<Entity> entitys = new HashSet<Entity>();
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="document")
	public Set<Entity> getEntitys() {
		return entitys;
	}

	public void setEntitys(Set<Entity> entitys) {
		this.entitys = entitys;
	}

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="document")
	public Set<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<Attribute> attributes) {
		this.attributes = attributes;
	}

	@Column(name="display_name",length=32)
	public String getDisplayName() {
		return displayName;
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	@Column(length=32)
	public String getName() {
		return name;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

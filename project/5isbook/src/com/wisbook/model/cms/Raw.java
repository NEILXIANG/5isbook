package com.wisbook.model.cms;

import javax.persistence.Column;  
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * cms-元属性
 * @author volador
 *
 */
@javax.persistence.Entity
@Table(name="raw")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="com.wisbook.model.cms.document_type.cache")
public class Raw {
	private int id;
	private String name;
	private String displayName;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(length=32)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="display_name",length=32)
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}

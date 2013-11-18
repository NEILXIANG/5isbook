package com.wisbook.model.cms;

import java.math.BigDecimal;     
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
import javax.persistence.Transient;

import com.wisbook.dao.cms.EntityValueDao;
import com.wisbook.dao.order.EntityTypeDao;
import com.wisbook.hibernate3.aware.EntityTypeDaoAware;
import com.wisbook.hibernate3.aware.EntityValueDaoAware;
import com.wisbook.model.order.EntityType;

/**
 * cms-实体类
 * @author volador
 *
 */
@javax.persistence.Entity
@Table(name="entity")
public class Entity implements EntityValueDaoAware,EntityTypeDaoAware{
	private int id;
	private Document document;
	//商品类别
	private Category category;
	//商品价格
	private BigDecimal price;
	//所有字段的值
	private Set<EntityValue> entityValues = new HashSet<EntityValue>();
	//该商品下面的商品种类以及数量
	private Set<EntityType> entityTypes = new HashSet<EntityType>();
	
	//-----这些属性由hibernate listener进行注入,具体的注入行为看hibernate配置文件所配置的监听器-----
	@Transient
	private EntityValueDao entityValueDao;
	@Transient
	private EntityTypeDao entityTypeDao;
	
	@Override
	public void setEntityValueDao(EntityValueDao entityValueDao) {
		this.entityValueDao = entityValueDao;
	}
	
	@Override
	public void setEntityTypeDao(EntityTypeDao entityTypeDao) {
		this.entityTypeDao = entityTypeDao;
	}
	
	public EntityValue getEntityValue(String attrName){
		return this.entityValueDao.getEntityValue(this, attrName);
	}
	
	public EntityType getEntityType(String name){
		return this.entityTypeDao.getEntityType(this.id,name);
	}
	
	public BigDecimal getSinglePriceByEntityType(String entityTypeName) throws EntityTypeNotFoundException{
		EntityType et = getEntityType(entityTypeName);
		if(et == null) throw new EntityTypeNotFoundException("can not found any record of EntityType[name="+entityTypeName+"] in entity[id="+this.id+"]");
		return this.price.multiply(et.getEntityTypePK().getGoodsType().getDiscount()).setScale(2,BigDecimal.ROUND_HALF_UP);
	}
	
	//---------------
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="entityTypePK.entity")
	public Set<EntityType> getEntityTypes() {
		return entityTypes;
	}


	public void setEntityTypes(Set<EntityType> entityTypes) {
		this.entityTypes = entityTypes;
	}
	
	@Column(name="price",nullable=false)
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="entity")
	public Set<EntityValue> getEntityValues() {
		return entityValues;
	}
	public void setEntityValues(Set<EntityValue> entityValues) {
		this.entityValues = entityValues;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="category_id",nullable=true)
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
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
	@JoinColumn(name="document_id")
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}

}

package com.wisbook.model.cms;

import java.util.ArrayList; 
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 分类,这货load出来后会保存在servletcontext中
 * @author volador
 *
 */
@javax.persistence.Entity
@Table(name="category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Category{
	
	public static final String CATEGORY_SEPERATOR = "."; //库存码分隔符
	
	private int id; //分类id,其存在仅仅是为了维持树的结构
	
	private String basicCode;  //从顶点到该节点的库存码,意义在于唯一标示一个顶点,并且该标示不随外界变化而变化
	
	private String displayName; //分类别名
	//父亲
	private Category parent; 
	//所有的子节点
	private Set<Category> children = new HashSet<Category>();
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)  //eager:全部load出
	@JoinColumn(name="parent_id",nullable=true)
	public Category getParent() {
		return parent;
	}
	public void setParent(Category parent) {
		this.parent = parent;
	}
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="parent")
	public Set<Category> getChildren() {
		return children;
	}
	public void setChildren(Set<Category> children) {
		this.children = children;
	}

	@Column(name="display_name",length=128)
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	@Column(name="basic_code",length=128,nullable=false)
	public String getBasicCode() {
		return basicCode;
	}
	public void setBasicCode(String basicCode) {
		this.basicCode = basicCode;
	}
	
	//methods 以下是推荐的内容，就是说首页显示的时候，一些内容是应该被推荐的，目前只是临时放在这里~
	
	/**
	 * 拿到该分类下面的随机记录（用在主页分类条上）,用于某个主分类下面随机抽取几个叶子分类
	 * @param size 获取的记录条数
	 * @return 推荐列表
	 */
	public List<Category> recommendCategorys(int size){
		List<Category> list = this.allSubCategory();
		if(list.size() <= size) return list;
		Set<Integer> luckNumList = new HashSet<Integer>();
		Random random = new Random(System.currentTimeMillis());
		int maxSize = list.size();
		while(luckNumList.size() < size){
			int resultNum = Math.abs(random.nextInt())%maxSize;
			luckNumList.add(resultNum);
		}
		List<Category> resultList = new ArrayList<Category>();
		for(int luckNum:luckNumList){
			resultList.add(list.get(luckNum));
		}
		return resultList;
	}
	
	/**
	 * 拿到该某节点下面的所有叶子节点,保持有序
	 * @return 所有的叶子节点
	 */
	public List<Category> allSubCategory(){
		List<Category> list = new ArrayList<Category>();
		//用于排序
		class Cmp implements Comparator<Category>{
			@Override
			public int compare(Category o1, Category o2) {
				if(o1.getId() == o2.getId()) return 0;
				else if(o1.getId() > o2.getId()) return 1;
				else return 0;
			}
		}
		for(Category son:children){
			if(son.getChildren() != null && son.getChildren().size() > 0){
				for(Category subSon:son.getChildren()){
					list.add(subSon);
				}
			}
		}
		Collections.sort(list,new Cmp());
		return list;
	}
}

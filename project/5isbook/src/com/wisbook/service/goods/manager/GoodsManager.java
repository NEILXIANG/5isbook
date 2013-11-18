package com.wisbook.service.goods.manager;

import java.util.List;
import java.util.Map;  

import com.wisbook.model.cms.Category;
import com.wisbook.model.cms.Entity;
import com.wisbook.service.goods.detail.GoodsDetailProvider;
import com.wisbook.service.user.AccessErrorException;
import com.wisbook.util.PageBean;
/**
 * 商品管理的标准
 * @author volador
 *
 */
public interface GoodsManager{
	/**
	 * 商品存储
	 * @param provider：商品属性信息的提供者
	 * @return Map<String,Object> 其实在上层范围内，这个map装载的是添加商品后需要返回的信息，具体什么信息，什么样的结构，各个产品负责。
	 * 			在图书导入这个层面上，这个结构有点约定大于俗成的感觉<br>
	 * 			key:操作的isbn value:StoreInfo类<br>
	 * 			特殊的key:error_content value:List<String>变量，装载所有的错误isbn，注意，若整个过程没有错误isbn，map中没有该字段<br>
	 */
	AddResult addGoods(GoodsDetailProvider provider);
	/**
	 * 查看某件商品库存
	 * @param entityId:商品id
	 * @exception AccessException:商品不存在
	 * @return 商品库存值
	 */
	int checkStoreCount(int entityId) throws AccessErrorException;
	/**
	 * 查看某件商品下面的某个种类的数量
	 * @param entityId：商品id
	 * @param typeName：种类名字
	 * @return：数量
	 * @throws AccessErrorException：商品不存在
	 */
	int checkStoreCount(int entityId,String typeName) throws AccessErrorException;
	/**
	 * 清空该商品的库存
	 * @param entityId：商品的id
	 * @exception AccessException:商品不存在
	 * @return 被影响的商品条数
	*/
	int clearGoodsStoreCount(int entityId) throws AccessErrorException;
	/**
	 * 增加库存
	 * @param entityId：商品id
	 * @param count：增加的库存量
	 * @param typeName：商品种类
	 * @return true/false
	 */
	boolean increaseGoods(int entityId,String typeName,int count) throws AccessErrorException;
	/**
	 * 减少库存,假如需要减少的数量大于entity的数量，那么将entity的数量清0
	 * @param entityId：商品id
	 * @param count：减少的库存量
	 * @param typeName：商品种类
	 * @return true/false
	 */
	boolean decreaseGoods(int entityId,String typeName,int count) throws AccessErrorException;
	/**
	 * 商品列表
	 * @param condition：列表条件
	 * @param pageIndex：当前页
	 * @param pageSize：每页记录数
	 * @return 分页实体
	PageBean<Entity> fetchGoodsWithCondition(FetchCondition condition,int pageIndex,int pageSize);
	*/
	/**
	 * 拿到全部的商品，分页，无条件
	 * @param pageIndex：当前页
	 * @param pageSize：每页大小
	 * @return 分页实体
	 */
	PageBean<Entity> fetchAllGoods(int pageIndex,int pageSize);
	/**
	 * 按照类别来找商品
	 * @param catId: 类别名字
	 * @param pageIndex：当前页
	 * @param pageSize：每页大小
	 * @return 分页实体
	 */
	PageBean<Entity> fetchGoodsByCategory(int catId,int pageIndex,int pageSize);
	/**
	 * 通过entityid找到entity
	 * @param EntityId
	 * @exception AccessException:商品不存在
	 * @return 
	 */
	Entity getGoodsById(int EntityId) throws AccessErrorException;
	/**
	 * 按商品属性查找商品<br>
	 * 这里直接拼凑HQL语句：attribute='%content%',所以可以在content参数上使用正则来匹配
	 * 也可以直接精确匹配
	 * 
	 * @param attrName
	 *            ：属性名字
	 * @param content
	 *            ：属性的值
	 * @param pageIndex
	 *            ：当前页
	 * @param pageSize
	 *            ：每页大小
	 * @param byRegexp
	 * 			    ：是否模糊匹配
	 * @return 分页实体,当没记录时，返回list为空的pagebean实体
	 */
	PageBean<Entity> fetchGoodsByAttribute(String attrName,String content,int pageIndex,int pageSize,boolean byRegexp) throws NoSuchAttributeException;
	/**
	 * 按商品属性查找商品<br>
	 * 启用模糊搜索
	 * 
	 * @param attrName
	 *            ：属性名字
	 * @param content
	 *            ：属性的值
	 * @param pageIndex
	 *            ：当前页
	 * @param pageSize
	 *            ：每页大小
	 * @param byRegexp
	 * 			    ：是否模糊匹配
	 * @return 分页实体,当没记录时，返回list为空的pagebean实体
	 */
	
	PageBean<Entity> fetchGoodsByAttribute(String attrName,String content,int pageIndex,int pageSize) throws NoSuchAttributeException;
	/**
	 * 更新对象属性的值,内容有变化，则发出sql语句更新需要更新的部分。
	 * 	需要解决问题：如何区分字段是entity_value上面的属性字段还是entity上面的属性字段<br>
	 * 			（当entity_value获取不成功后用反射进行判断该属性是否是entity下面的字段）
	 * @param entityId：需要更新的entity
	 * @param contents 属性：值
	 * @return 更新后的实体
	 * @throws AccessErrorException entityId不存在
	 * @throws ParameterException 字段出现错误时，可以通过e.getMessage()拿到错误信息、
	 */
	Entity updateEntityValue(int entityId,Map<String,Object> contents) throws ParameterException, AccessErrorException;
	/**
	 * 拿到可以用来展示给用户的分类列表
	 * @return 分类列表
	 */
	List<Category> fetchListCategory();
	/**
	 * 通过category的id获取记录
	 * @param id category的id
	 * @return 返回记录实体，若果为空，返回null
	 */
	Category getCategoryById(int id);
	/**
	 * 拿到根分类
	 * @return
	 */
	Category getRootCategory();
	/**
	 * 拿到该类别下面的推荐的实体
	 * @param c 被操作类别
	 * @param size 获得记录的条数
	 * @return 实体集
	 */
	List<Entity> recommendEntitys(Category c,int size);
}

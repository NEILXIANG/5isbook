package com.wisbook.service.user;

import java.util.List;

import com.wisbook.model.user.*;
import com.wisbook.util.PageBean;

/**
 * user业务处理,用户登录以及权限管理已经交由acegi处理
 * 当涉及更改用户信息时记得异常
 * @author volador
 *
 */
public interface UserService{
	//针对后台：
	/**
	 * 拿到某角色的实体集
	 * @param roleName 角色名称
	 * @param pageIndex 开始页
	 * @param pageSize 每页大小
	 * @return 分页实体
	 */
	PageBean<User> getUsersByRole(String roleName,int pageIndex,int pageSize);
	/**
	 * 拿到用户的记录，勇于展示用户资料
	 * @param currentAccount：当前操作用户
	 * @param AccessAccount：需要被操作的用户
	 * @return user实体
	 * @throws AccessErrorException 当发生跨权操作时发生
	 */
	User getUserByAccount(String currentAccount,String accessAccount) throws AccessErrorException;
	/**
	 * 拿到用户下面的登录记录
	 * @param account：用户账号
	 * @param pageIndex：当前页
	 * @param pageSize：每页大小
	 * @return 登录记录分页实体
	 */
	PageBean<LoginInfo> getLoginInfosByAccount(String account,int pageIndex,int pageSize);
	/**
	 * 拿到该account的user info
	 * @param account:账号
	 * @return UserInfo实例
	 */
	UserInfo getUserInfoByAccount(String account);
	/**
	 * 启动或者不启动user[开关，基于user上一次的状态]
	 * @param currentAccount：当前登录的account
	 * @param AccessAccount：准备进行处理的account
	 * @exception AccessErrorException：account出现错误时抛出：<br>
	 * 						   1.跨权操作<br>
	 * 						   2.要访问的对象不存在
	 */
	void tiggleEnabled(String currentAccount,String accessAccount) throws AccessErrorException;
	/**
	 * 添加一名admin
	 * @param currentAccount：当前处理的账号
	 * @param user：新添加的admin
	 * @throws AccessErrorException：action出现错误时抛出：<br>
	 * 						1.跨权操作
	 */
	void registryAdmin(String currentAccount,String account,String password) throws AccessErrorException;
	/**
	 * 更改密码
	 * @param currentAccount：当前处理账号
	 * @param oldPass：旧密码
	 * @param newPass：新密码
	 * @return true/false 成功/旧密码错误
	 */
	boolean changePassword(String currentAccount,String oldPass,String newPass);
	/**
	 * 添加一条登录记录
	 * @param account 登录账号
	 * @param loginTime 登录时间
	 * @param ipAddress 登录ip地址
	 */
	LoginInfo addLoginMessage(String account,String ipAddress);
	
	//~前台
	/**
	 * 查看该account是否已经在系统中存在,用于注册
	 * @param account 账号
	 * @return
	 */
	boolean accountExist(String account);
	/**
	 * 注册用户
	 * @param user 用户account and password
	 * @return 成功/失败
	 */
	boolean registryUser(String account,String password);
	/**
	 * 拿到用户最近登录的记录
	 * @param account 用户账号
	 * @return 登录记录
	 */
	LoginInfo getTopLoginInfo(String account);
	
	
	
	//~个人中心
	/**
	 * 添加地址
	 * @param currentAccount 需要添加地址的用户【当前登录用户】
 	 * @param name 地址->真实名字
	 * @param address 地址->真实地址
	 * @param tel 地址->真实联系方式 
	 * @return 成功/失败
	 */
	boolean addAddress(String currentAccount,String name,String address,String longTel,String shortTel);
	/**
	 * 删除地址
	 * @param currentAccount 地址所属用户【当前登录用户】
	 * @param addressId 地址id
	 * @return 成功/失败
	 */
	boolean deleteAddress(String currentAccount,int addressId);
	/**
	 * 修改地址信息
	 * @param currentAccount currentAccount 地址所属用户【当前登录用户】
	 * @param addressId 地址id
	 * @param name 新名字
	 * @param address 新地址
	 * @param tel 新电话
	 * @return 成功/失败
	 */
	boolean modifyAddress(String currentAccount,int addressId,String name,String address,String longTel,String shortTel);
	/**
	 * 获取用户的全部地址
	 * @param currentAccount 当前登录用户
	 * @return 地址列表
	 */
	List<Address> getAllAddress(String currentAccount);
	/**
	 * 获取用户的地址记录
	 * @param currentAccount 地址所属用户
	 * @param id 地址ip
	 * @return 地址记录
	 */
	Address getAddress(String currentAccount,int id);
	/**
	 * 更新用户资料
	 * @param user 用户
	 * @return true/false
	 */
	boolean updateUser(User user);
}

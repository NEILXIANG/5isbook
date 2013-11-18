package com.wisbook.dao.user;

import java.util.List;

import com.wisbook.model.user.Address;

/**
 * 对address的dao操作
 * @author volador
 *
 */
public interface AddressDao {
	/**
	 * 获取某用户下面的某个具体的地址
	 * @param account 用户账号
	 * @param id 地址id
	 * @return 地址记录 若空 返回null
	 */
	Address getAddressByAccountAndId(String account,int id);
	/**
	 * 获取全部地址记录，不分页
	 * @param account 用户账号
	 * @return 地址记录列表
	 */
	List<Address> getAllAddress(String account);
}

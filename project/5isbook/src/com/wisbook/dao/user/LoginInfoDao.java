package com.wisbook.dao.user;

import com.wisbook.model.user.LoginInfo;

/**
 * 处理用户登录信息的dao
 * @author volador
 *
 */
public interface LoginInfoDao {
	/**
	 * 拿到该用户的最近一条登录记录
	 * @param account 用户账号
	 * @return 登录记录
	 */
	LoginInfo getTopRecord(String account);
}

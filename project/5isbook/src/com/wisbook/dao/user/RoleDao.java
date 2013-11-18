package com.wisbook.dao.user;

import com.wisbook.model.user.Role;

/**
 * role表的操作dao
 * @author volador
 *
 */
public interface RoleDao {

	/**
	 * 以load方式通过名字获得记录
	 * @param role_name
	 * @return
	 */
	Role loadByName(String name);
}

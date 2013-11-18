package com.wisbook.dao.user.impl;

import org.springframework.stereotype.Component;

import com.wisbook.dao.base.AbstractBaseDao;
import com.wisbook.dao.user.UserDao;
import com.wisbook.model.user.User;

@Component("userDao")
public class UserDaoImpl extends AbstractBaseDao<User,String> implements UserDao{
	
	@Override
	protected Class<User> getEntityClazz() {
		return User.class;
	}

}

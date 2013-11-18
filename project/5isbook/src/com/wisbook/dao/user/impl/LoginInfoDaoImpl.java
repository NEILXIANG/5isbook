package com.wisbook.dao.user.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.wisbook.dao.base.AbstractBaseDao;
import com.wisbook.dao.user.LoginInfoDao;
import com.wisbook.model.user.LoginInfo;
@Component("loginInfoDao")
public class LoginInfoDaoImpl extends AbstractBaseDao<LoginInfo,Integer> implements LoginInfoDao {

	@Override
	protected Class<LoginInfo> getEntityClazz() {
		return LoginInfo.class;
	}

	@Override
	public LoginInfo getTopRecord(String account) {
		List<?> list = this.getHibernateTemplate().createQuery("from LoginInfo lf where lf.user.account=:account order by lf.loginTime desc")
				.setString("account",account)
				.setFirstResult(0)
				.setMaxResults(1)
				.list();
		if(list == null || list.size() == 0) return null;
		return (LoginInfo) list.get(0);
	}

}

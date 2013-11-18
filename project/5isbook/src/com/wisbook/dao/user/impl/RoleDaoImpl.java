package com.wisbook.dao.user.impl;

import org.springframework.stereotype.Component;

import com.wisbook.dao.base.AbstractBaseDao; 
import com.wisbook.dao.user.RoleDao;
import com.wisbook.model.user.Role;
@Component("roleDao")
public class RoleDaoImpl extends AbstractBaseDao<Role,Integer> implements RoleDao{
	
	@Override
	public Role loadByName(final String name) {
		Role role = (Role) this.getHibernateTemplate().createQuery("from Role r where r.name = :name")
					.setString("name", name)
					.uniqueResult();
		return role;
	}

	@Override
	protected Class<Role> getEntityClazz() {
		return Role.class;
	}
}

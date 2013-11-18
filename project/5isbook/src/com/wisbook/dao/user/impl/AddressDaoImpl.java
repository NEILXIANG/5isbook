package com.wisbook.dao.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wisbook.dao.base.AbstractBaseDao; 
import com.wisbook.dao.user.AddressDao;
import com.wisbook.model.user.Address;

@Component("addressDao")
public class AddressDaoImpl extends AbstractBaseDao<Address,Integer> implements AddressDao{

	@Override
	protected Class<Address> getEntityClazz() {
		return Address.class;
	}

	@Override
	public Address getAddressByAccountAndId(String account, int id) {
		String HQL = "from Address a where a.user.account=:account and a.id=:id";
		Address address = (Address) getHibernateTemplate().createQuery(HQL)
							.setString("account", account)
							.setInteger("id", id)
							.uniqueResult();
		return address;
	}
	
	@Override
	public List<Address> getAllAddress(String account) {
		String HQL = "from Address a where a.user.account=:account";
		@SuppressWarnings("unchecked")
		List<Address> ads = getHibernateTemplate().createQuery(HQL)
							.setString("account", account)
							.list();
		if(ads == null) ads = new ArrayList<Address>();
		return ads;
	}
}

package com.wisbook.listener;

import com.wisbook.model.user.Role;
import com.wisbook.util.BasicTestCase;
import com.wisbook.util.HibernateTemplate;

public class SaveEvent extends BasicTestCase{
	private HibernateTemplate hibernateTemplate;
	

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate; 
	}


	public void testSaveEvent(){
		this.setDefaultRollback(false);
		Role role = new Role();
		role.setName("test1");
		role.setDisplayName("test1");
		role.setContent("test1");
		
		this.hibernateTemplate.save(role);
	}
	
}

package com.wisbook.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import com.wisbook.model.cms.Entity;
import com.wisbook.model.user.Address;
import com.wisbook.model.user.Role;
import com.wisbook.model.user.User;
import com.wisbook.util.BasicTestCase;
import com.wisbook.util.HibernateTemplate;

public class UserServiceImplTest extends BasicTestCase {

	private HibernateTemplate hibernateTemplate;

	//
	// private InjectorHolder injectorHolder;
	//
	// public void setInjectorHolder(InjectorHolder injectorHolder) {
	// this.injectorHolder = injectorHolder;
	// }

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void testGet() throws Exception {
		Entity e = (Entity) this.hibernateTemplate.load(Entity.class, 1);
		System.out.println(e.getEntityValue("isbn"));
	}

	public void testInjector() {
		Entity e = (Entity) this.hibernateTemplate.load(Entity.class, 1);
		System.out.println(e.getEntityType("copy_edition").getStorageCount());
	}

	public void testInsert() {

		this.setDefaultRollback(false);

		Role role1 = new Role();
		role1.setName("ROLE_USER");
		role1.setDisplayName("普通用户");

		/*Role role2 = new Role();
		role2.setName("ROLE_SUPER_ADMIN");
		role2.setDisplayName("超级管理员");*/

		hibernateTemplate.save(role1);
	//	hibernateTemplate.save(role2);

	/*	User user1 = new User();
		user1.setAccount("so.volador@gmail.com");
		user1.setEnabled(true);
		user1.setPassword("zhang6168190");
		user1.setRole(role2);*/

		/*User user2 = new User();
		user2.setAccount("464915253@qq.com");
		user2.setEnabled(true);
		user2.setPassword("zhang6168190");
		user2.setRole(role1);
*/
		//hibernateTemplate.save(user1);
		//hibernateTemplate.save(user2);

		for (int i = 1; i <= 15; i++) {
			User user = new User();
			user.setAccount(i + "user@qq.com");
			user.setEnabled(true);
			user.setPassword("thisPsw000" + i);
			user.setRole(role1);

			Set<Address> set = new HashSet<Address>();
			for (int j = 5; j != 0; j--) {
				Address address = new Address();
				address.setAddress("华山" + i + j);
				address.setRealName("Ban" + i + j);

				set.add(address);
				address.setUser(user);
				user.setAddresses(set);
			}
			hibernateTemplate.save(user);
		}
	}

	public void testCache() {
		Session session1 = this.hibernateTemplate.getSessionFactory()
				.openSession();
		session1.beginTransaction();
		User user1 = (User) session1.load(User.class, "so.volador@gmail.com");
		System.out.println(user1.getClass().getName());
		System.out.println(user1.getPassword());
		session1.getTransaction().commit();
		session1.close();

		Session session2 = this.hibernateTemplate.getSessionFactory()
				.openSession();
		session2.beginTransaction();
		User user2 = (User) session2.load(User.class, "so.volador@gmail.com");
		System.out.println(user2.getClass().getName());
		System.out.println(user2.isEnabled());
		session2.getTransaction().commit();
		session2.close();
	}

 
}
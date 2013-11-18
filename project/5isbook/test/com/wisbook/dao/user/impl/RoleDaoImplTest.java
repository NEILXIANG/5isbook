package com.wisbook.dao.user.impl;

import javax.annotation.Resource;

import junit.framework.Assert;

import com.wisbook.dao.base.BaseDao;
import com.wisbook.model.user.Role;
import com.wisbook.util.BasicTestCase;
import com.wisbook.util.PageBean;

public class RoleDaoImplTest extends BasicTestCase {
	private BaseDao<Role,Integer> baseDao;

	public BaseDao<Role, Integer> getBaseDao() {
		return baseDao;
	}
	
	@Resource(name="roleDao")
	public void setBaseDao(BaseDao<Role, Integer> baseDao) {
		this.baseDao = baseDao;
	}

	public void testLoadRole(){
		Role role = this.baseDao.getEnitytById(2);
		System.out.println(role.getName());
	}
	
	public void testAddRole(){
		Role role = new Role();
		role.setName("test");
		role.setDisplayName("for test");
		
		Integer id = (Integer) this.baseDao.saveEntity(role);
		Assert.assertTrue(id != 0);
		
		Role roleTest = this.baseDao.getEnitytById(id);
		Assert.assertTrue(roleTest != null);
		System.out.println(roleTest.getDisplayName());
	}
	
	public void testPage(){
		//插入10条记录
		for(int i = 1;i<= 10;i++){
			Role role = new Role();
			role.setName(i+"");
			role.setDisplayName(i+"");
			this.baseDao.saveEntity(role);
		}
		
		String hql = "from Role";
		
		PageBean<Role> pb = this.baseDao.fetchEntityByPageHQL(hql,3,5);
		System.out.println(pb);
		for(Role role:pb.getList()){
			System.out.println("role.name:"+role.getName()+"; role.displayName:"+role.getDisplayName()+";");	
		}
	}
}

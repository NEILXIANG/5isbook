package com.wisbook.action.configuration;

import javax.annotation.Resource;

import com.wisbook.util.BasicTestCase;

public class RoleTest extends BasicTestCase{
	private Role action;

	public Role getAction() {
		return action;
	}

	@Resource(name="role")
	public void setAction(Role action) {
		this.action = action;
	}



	public void testExecute() throws Exception{
		this.setDefaultRollback(false);
		System.out.println("action execute result:"+action.execute());
	}
	
}

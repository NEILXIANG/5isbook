package com.wisbook.action.configuration;

import javax.annotation.Resource;

import com.wisbook.util.BasicTestCase;

public class CategoryTest extends BasicTestCase{
	private Category action;
	public Category getAction() {
		return action;
	}

	@Resource(name="category")
	public void setAction(Category action) {
		this.action = action;
	}

	public void testExecute() throws Exception{
		this.setDefaultRollback(false);
		System.out.println("action execute result:"+action.execute());
	}
	
}

package com.wisbook.action.configuration;

import javax.annotation.Resource;

import com.wisbook.util.BasicTestCase;

public class DocumentTest extends BasicTestCase{
	private Document action;

	public Document getAction() {
		return action;
	}

	@Resource(name="document")
	public void setAction(Document action) {
		this.action = action;
	}
	
	public void testExecute() throws Exception{
		this.setDefaultRollback(false);
		System.out.println("action execute result:"+action.execute());
	}
	
}

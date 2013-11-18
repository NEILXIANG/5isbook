package com.wisbook.action.configuration;

import javax.annotation.Resource;

import com.wisbook.util.BasicTestCase;

public class GoodsTypeTest extends BasicTestCase{
	private GoodsType action;

	public GoodsType getAction() {
		return action;
	}

	@Resource(name="goodsType")
	public void setAction(GoodsType action) {
		this.action = action;
	}

	public void testExecute() throws Exception{
		this.setDefaultRollback(false);
		System.out.println("action execute result:"+action.execute());
	}
	
}

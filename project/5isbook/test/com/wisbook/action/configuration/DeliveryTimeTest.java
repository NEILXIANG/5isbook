package com.wisbook.action.configuration;

import javax.annotation.Resource;

import com.wisbook.util.BasicTestCase;

public class DeliveryTimeTest extends BasicTestCase{
	private DeliveryTime action;
	
	public DeliveryTime getAction() {
		return action;
	}
	@Resource(name="deliveryTime")
	public void setAction(DeliveryTime action) {
		this.action = action;
	}

	public void testExecute() throws Exception{
		this.setDefaultRollback(false);
		System.out.println("action execute result:"+action.execute());
	}
	
}

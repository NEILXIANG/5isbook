package com.wisbook.action.admin;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.wisbook.model.order.OrderStateEnum;
import com.wisbook.service.order.OrderManager;
/**
 * 首页
 * @author volador
 *
 */
@Component("com.wisbook.action.admin.indexAction")
@Scope("prototype")
public class IndexAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	private long all;
	private long committed;
	private long disposed;
	private long outStorage;
	private long received;
	private long cancel;
	
	private OrderManager manager;
	
	@Override
	public String execute() throws Exception {
		all = manager.getAllOrderCount();
		committed = manager.getOrderCountByState(OrderStateEnum.COMMITTED);
		disposed = manager.getOrderCountByState(OrderStateEnum.DISPOSED);
		outStorage = manager.getOrderCountByState(OrderStateEnum.OUT_STORAGE);
		received = manager.getOrderCountByState(OrderStateEnum.RECEIVED);
		cancel = manager.getOrderCountByState(OrderStateEnum.CANCEL);
		return Action.SUCCESS;
	}
	
	@Resource(name="orderManager")
	public void setManager(OrderManager manager) {
		this.manager = manager;
	}



	public long getAll() {
		return all;
	}

	public long getCommitted() {
		return committed;
	}

	public long getDisposed() {
		return disposed;
	}

	public long getOutStorage() {
		return outStorage;
	}

	public long getReceived() {
		return received;
	}

	public long getCancel() {
		return cancel;
	}
	
	
}

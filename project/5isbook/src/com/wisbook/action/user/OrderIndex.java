package com.wisbook.action.user;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.wisbook.model.order.Order;
import com.wisbook.model.order.OrderStateEnum;
import com.wisbook.service.order.OrderManager;
import com.wisbook.service.order.StateMark;
import com.wisbook.util.PageBean;

/**
 * 个人中心，订单首页
 * @author volador
 *
 */
@Component("com.wisbook.action.user.orderIndex")
@Scope("prototype")
public class OrderIndex extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	private static String REQ_ALL = "all"; //全部订单
	private static String REQ_PROCESS = "process"; //正在处理中的订单
	private static String REQ_DONE = "done"; //已经结束处理的订单
	private static int DEFAULT_PAGE_INDEX = 1;
	private static int DEFAULT_PAGE_SIZE = 5;
	
	private OrderManager orderManager;
	private Map<String,Object> session;
	
	private String req;
	private int index; //当前页
	private int size; //每页几条
	private PageBean<Order> orders;
	
	@Override
	public String execute() throws Exception {
		String account = (String) session.get("ACEGI_SECURITY_LAST_USERNAME");
		//准备好订单
		if(req.equalsIgnoreCase(REQ_ALL)){
			orders = orderManager.getAllOrdersByAccount(account, index, size);
		}else if(req.equalsIgnoreCase(REQ_PROCESS)){
			orders = orderManager.getOrdersByAccountAndState(account,StateMark.EQUALS, index, size,
																OrderStateEnum.COMMITTED,
																OrderStateEnum.DISPOSED,
																OrderStateEnum.OUT_STORAGE);
			
		}else{
			orders = orderManager.getOrdersByAccountAndState(account,StateMark.EQUALS,index,size,
																OrderStateEnum.RECEIVED,
																OrderStateEnum.CANCEL);
		}
		return Action.SUCCESS;
	}
	
	@Override
	public void validate() {
		boolean flag = 
				req == null || 
				(
						!REQ_ALL.equalsIgnoreCase(req) &&
						!REQ_PROCESS.equalsIgnoreCase(req) &&
						!REQ_DONE.equalsIgnoreCase(req)
				);
		req = flag?REQ_ALL:req; //默认
		index = index <= 0 ? DEFAULT_PAGE_INDEX : index;
		size = size <= 0 ? DEFAULT_PAGE_SIZE : size;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	@Resource(name="orderManager")
	public void setOrderManager(OrderManager orderManager) {
		this.orderManager = orderManager;
	}

	public String getReq() {
		return req;
	}

	public void setReq(String req) {
		this.req = req;
	}

	public PageBean<Order> getOrders() {
		return orders;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setSize(int size) {
		this.size = size;
	}
}

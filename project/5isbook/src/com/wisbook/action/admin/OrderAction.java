package com.wisbook.action.admin;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wisbook.model.order.Order;
import com.wisbook.model.order.OrderStateEnum;
import com.wisbook.service.order.OrderManager;
import com.wisbook.util.PageBean;

/**
 * @author Ban 后台订单管理
 */
@Component("adminOrder")
@Scope("prototype")
public class OrderAction extends ActionSupport implements ModelDriven<Order>,
		SessionAware {

	private static final long serialVersionUID = 1L;
	// 操作订单的管理员账号
	private String adminAccount;
	// 用于查看用户订单的用户账号
	private String userAccount;
	// 具体订单
	private Order order;
	// 订单号
	private int orderId;
	// 订单管理
	private OrderManager orderManager;
	// 订单列表
	private List<Order> list;
	// 列表的分页
	private List<Integer> pageNumberList;
	// 总共的页数
	private int totalPage;
	// 当前页
	private int pageIndex = 1;
	// 分页大小,默认为10
	private int pageSize = 10;
	// 订单状态
	private String status = null;
	// 更改订单状态是否成功
	private boolean success;
	// 更改订单后返回的页面的方法名
	private String back;
	private Map<String, Object> session;

	/**
	 * 列出特定状态的订单列表 如果该状态有误，则列出状态为提交的订单
	 * 
	 * @return SUCCESS
	 */
	public String selectByStatus() {
		PageBean<Order> page = orderManager.getOrdersByState(
				OrderStateEnum.valueOf(status.toUpperCase()), pageIndex,
				pageSize);
		if (page != null) {
			totalPage = page.getTotalPage();
			list = page.getList();
			pageNumberList = page.getPageNumberList();
		}
		return SUCCESS;
	}

	/**
	 * 把订单推进到下一个状态[正常情况],并记录下操作记录 然后返回到原来的页面
	 * 
	 * @return 是否更改成功
	 */
	public String processToNextStatus() {
		success = orderManager.processToNextState(adminAccount, orderId);
		if (back.equals("detail"))
			return detail();
		else if (back.equals("getUserOrders"))
			return getUserOrders();
		else if (back.equals("select"))
			return selectByStatus();
		else
			return getUserOrdersByStatus();
	}

	/**
	 * 管理员取消订单 然后返回到原来的页面
	 * 
	 * @return 是否更改成功
	 */
	public String cancel() {
		success = orderManager.cancelOrderByAdmin(adminAccount, orderId);
		if (back.equals("detail"))
			return detail();
		else if (back.equals("getUserOrders"))
			return getUserOrders();
		else if (back.equals("select"))
			return selectByStatus();
		else
			return getUserOrdersByStatus();
	}

	/**
	 * 获取某个用户的所有订单
	 * 
	 * @return
	 */
	public String getUserOrders() {
		PageBean<Order> page = orderManager.getAllOrdersByAccount(userAccount,
				pageIndex, pageSize);
		if (page != null) {
			totalPage = page.getTotalPage();
			list = page.getList();
			pageNumberList = page.getPageNumberList();
		}
		return "userOrders";
	}

	/**
	 * 获取某个用户的某个状态的所有订单
	 * 
	 * @return
	 */
	public String getUserOrdersByStatus() {
		PageBean<Order> page = orderManager.getOrdersByAccountAndState(
				userAccount, OrderStateEnum.valueOf(status.toUpperCase()),
				pageIndex, pageSize);
		if (page != null) {
			totalPage = page.getTotalPage();
			list = page.getList();
			pageNumberList = page.getPageNumberList();
		}
		return "userOrders";
	}

	/**
	 * 获取某管理员处理过的所有订单
	 * 
	 * @return
	 */
	public String getAdminOrders() {
		return "";
	}

	/**
	 * 订单详情，顺便计算订单总价
	 * 
	 * @return
	 */
	public String detail() {
		order = orderManager.getOrderById(orderId);
		return "detail";
	}

	@Resource(name = "orderManager")
	public void setOrderManager(OrderManager orderManager) {
		this.orderManager = orderManager;
	}

	@Override
	public Order getModel() {
		return this.order;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public List<Order> getList() {
		return list;
	}

	public void setList(List<Order> list) {
		this.list = list;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Integer> getPageNumberList() {
		return pageNumberList;
	}

	public void setPageNumberList(List<Integer> pageNumberList) {
		this.pageNumberList = pageNumberList;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getAdminAccount() {
		return adminAccount;
	}

	public void setAdminAccount(String adminAccount) {
		this.adminAccount = adminAccount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public String getBack() {
		return back;
	}

	public void setBack(String back) {
		this.back = back;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		this.session = map;
		this.adminAccount=(String) session.get("ACEGI_SECURITY_LAST_USERNAME");
	}

	public Map<String, Object> getSession() {
		return session;
	}

}

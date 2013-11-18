package com.wisbook.action.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.wisbook.service.cart.Cart;
/**
 * 增加或者减少购物车项中的数量
 * @author volador
 *
 */
@Component("com.wisbook.action.cart.increaseAndDecreaseAction")
@Scope("prototype")
public class IncreaseAndDecreaseAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;

	private int num; //变化数量
	private int id;  //购物车项id
	
	private Map<String,Object> session;
	
	/**
	 * 增加
	 */
	public void increase(){
		Cart cart = (Cart) session.get("cart");
		if(cart == null){
			responseStat(false);
		}
		else{
			responseStat(cart.increaseNumofCartItem(id, num));
		}
	}
	
	/**
	 * 减少
	 */
	public void decrease(){
		Cart cart = (Cart) session.get("cart");
		if(cart == null) responseStat(false);
		else responseStat(cart.decreaseNumOfCartItem(id, num));
	}
	
	public void setNum(int num) {
		this.num = num;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 返回状态
	 * @param state
	 */
	private void responseStat(boolean state){
		HttpServletResponse req = ServletActionContext.getResponse();
		try {
			PrintWriter w = req.getWriter();
			if(state) w.println("true");
			else w.println("false");
			w.flush();
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
}

package com.wisbook.action.user;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.wisbook.model.user.User;
import com.wisbook.model.user.UserInfo;
import com.wisbook.service.user.UserService;

/**
 * 用户基本信息
 * @author volador
 *
 */
@Component("com.wisbook.action.user.nomalInfoAction")
@Scope("prototype")
public class NomalInfoAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	private Map<String,Object> session;
	private UserService userService;
	
	private String formFlag; //标记是否是首次请求,如果有内容[1]，则不是首次请求
	
	private String nickName;
	private String gender;
	private String birthday;
	private String qq;
	
	private String message;
	
	@Override
	public String execute() throws Exception {
		String result = Action.SUCCESS;
		String account = (String) session.get("ACEGI_SECURITY_LAST_USERNAME");
		User user = userService.getUserByAccount(account, account);
		UserInfo info = user.getUserInfo();
		if(formFlag != null && formFlag.length() >= 0){ //表单提交来的
			message = "修改成功~";
			boolean newInfo = false;
			if(info == null){
				newInfo = true;
				info = new UserInfo();
			}
			info.setNickName(nickName);
			info.setGender(gender);
			info.setBirthday(birthday);
			info.setQq(qq);
			if(newInfo){ //新生成的info
				user.setUserInfo(info);
			}
			if(!userService.updateUser(user)){
				message = "修改失败~";
			}else{
				if(nickName != null && nickName.length() > 0)
				session.put("NICK_NAME",nickName); //更新显示亲昵
			}
		}else{  //首次访问，填充显示数据
			if(info != null){
				nickName = info.getNickName();
				gender = info.getGender();
				birthday = info.getBirthday();
				qq = info.getQq();
			}
			result = Action.INPUT;
		}
		return result;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setFormFlag(String formFlag) {
		this.formFlag = formFlag;
	}

	public String getMessage() {
		return message;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	
}

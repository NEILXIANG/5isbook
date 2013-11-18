package com.wisbook.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
/**
 * email类型的账户
 * @author volador
 *
 */
@Component("emailFormat")
public class EmailFormat implements AccountFormat{

	private String message;
	private String reg = "^(([0-9a-zA-Z]+)|([0-9a-zA-Z]+[_.0-9a-zA-Z-]*[0-9a-zA-Z]+))@([a-zA-Z0-9-]+[.])+([a-zA-Z]{2}|net|com|gov|mil|org|edu|int)$";
	
	/**
	 * 检测该账户是否是email的格式
	 */
	@Override
	public boolean testFormat(String account){
		Matcher m = Pattern.compile(this.reg).matcher(account);
		boolean flag = m.matches();
		if(!flag) this.message = "亲，账号要邮件格式.";
		return flag;
	}

	@Override
	public String getErrorMessage() {
		return this.message;
	}

}

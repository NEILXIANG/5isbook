package com.wisbook.util.extension.acegi.providers;

import org.acegisecurity.AuthenticationException; 
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.providers.dao.DaoAuthenticationProvider;
import org.acegisecurity.userdetails.UserDetails;
import com.wisbook.util.extension.acegi.BadUserKindException;
/**
 * 负责相对应的j_userkind的provider,如果是自己管理j_userkind请求过来的，就处理，否则放弃处理
 * @author volador
 *
 */
public class MUKDaoAuthenticationProvider extends DaoAuthenticationProvider implements UserKindComparisonAware{

	private String expectedUserKind;
	private String currentUserKind;
	
	
	public String getExpectedUserKind() {
		return expectedUserKind;
	}

	public String getCurrentUserKind() {
		return currentUserKind;
	}

	@Override
	public void setExpectedUserKind(String expectedUserKind) {
		this.expectedUserKind = expectedUserKind;
	}

	@Override
	public void setCurrentUserKind(String currentUserKind) {
		this.currentUserKind = currentUserKind;
	}
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		if(this.currentUserKind.equals(this.expectedUserKind)){
			super.additionalAuthenticationChecks(userDetails, authentication);
		}else
			throw new BadUserKindException("Flag UserKind does not match!");
	}
}

package com.wisbook.util;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;

public class UserServiceLoginImpl implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		System.out.println("UserServiceLoginImpl.loadUserByUsername run....");
		String name = "so.volador@gmail.com";
		String pass = "zhangyu";
		boolean enable = true;
		GrantedAuthority[] authorities = new GrantedAuthority[1];
		authorities[0] = new GrantedAuthorityImpl("ROLE_USER");
		UserDetails u = new UserLoginDetails(name,pass,enable,authorities);
		return u;
	}

}

package com.alisls.demo.springcloud.oauth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alisls.demo.springcloud.oauth.client.UserClient;
import com.alisls.demo.springcloud.oauth.dto.LoginAppUser;
import com.alisls.demo.springcloud.service.user.dto.UserDTO;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserClient userClient;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO userDTO = userClient.getUserByUsername(username);
		
		if (userDTO == null) {
			throw new AuthenticationCredentialsNotFoundException("用户名不存在！");
		}
		
		LoginAppUser loginAppUser = new LoginAppUser();
		loginAppUser.setId(userDTO.getId());
		loginAppUser.setUsername(userDTO.getUsername());				
		loginAppUser.setEnabled(true);
		
		if (!loginAppUser.isEnabled()) {
			throw new DisabledException("用户已经被禁用！");
		}
		
		return loginAppUser;
	}
	
}

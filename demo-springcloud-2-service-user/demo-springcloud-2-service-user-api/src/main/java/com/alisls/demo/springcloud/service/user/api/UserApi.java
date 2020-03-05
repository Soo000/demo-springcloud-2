package com.alisls.demo.springcloud.service.user.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alisls.demo.springcloud.service.user.dto.UserDTO;

@RequestMapping("/user")
public interface UserApi {

	@GetMapping("/getUserByUsername/{username}")
	UserDTO getUserByUsername(@PathVariable("username") String username);
	
}

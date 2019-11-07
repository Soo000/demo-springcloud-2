package com.alisls.demo.springcloud.provider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	//@Autowired
	//private UserService userService;

	/*@GetMapping("/findUserById/{userId}")
	public UserDTO findUserById(@PathVariable Integer userId) {
		UserDTO userDTO = userService.findByUserId(userId);
		return userDTO;
	}*/
	
}

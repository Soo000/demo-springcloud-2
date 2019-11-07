package com.alisls.demo.springcloud.provider.service.user.impl;

import org.springframework.stereotype.Service;

import com.alisls.demo.springcloud.provider.service.user.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	/*
	 * @Autowired private UserRepository userRepository;
	 */

	/*@Override
	public UserDTO findByUserId(Integer userId) {
		UserDO userDO = userRepository.findByUserId(userId);
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(userDO, userDTO);
		return userDTO;
	}*/

}

package com.alisls.demo.springcloud.service.user.service;

import com.alisls.demo.springcloud.service.user.dto.UserDTO;

/**
 * 用户Service
 *
 * @author Ke Wang
 */
public interface UserService {

    UserDTO getUser(Long id);

    UserDTO getUser(String username);

    UserDTO save(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

}

package com.alisls.demo.springcloud.service.user.service;

import com.alisls.demo.springcloud.service.user.dto.UserDTO;

/**
 * 用户服务
 *
 * @author Ke Wang
 */
public interface UserService {

    UserDTO getUser(Long id);

    UserDTO getUser(String username);

}

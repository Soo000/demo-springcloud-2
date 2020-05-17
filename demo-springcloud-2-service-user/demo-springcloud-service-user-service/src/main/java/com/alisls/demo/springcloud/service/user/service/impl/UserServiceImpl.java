package com.alisls.demo.springcloud.service.user.service.impl;

import com.alisls.demo.springcloud.service.user.dao.UserDAO;
import com.alisls.demo.springcloud.service.user.dto.UserDTO;
import com.alisls.demo.springcloud.service.user.entity.UserDO;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alisls.demo.springcloud.service.user.service.UserService;

import java.util.Optional;

/**
 * 用户Service实现
 *
 * @author Ke Wang
 */
@Service("userService")
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Override
    public UserDTO getUser(Long id) {
        Optional<UserDO> userDO = userDAO.findById(id);

        UserDTO userDTO = new UserDTO();
        userDO.ifPresent(u -> {
            BeanUtils.copyProperties(u, userDTO);
        });

        return userDTO;
    }

    @Override
    public UserDTO getUser(String username) {
        UserDO userDO = userDAO.findByUsername(username);

        UserDTO userDTO = new UserDTO();
        if (userDO != null) {
            BeanUtils.copyProperties(userDO, userDTO);
        }

        return userDTO;
    }

}

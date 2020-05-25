package com.alisls.demo.springcloud.service.user.service.impl;

import com.alisls.demo.springcloud.service.user.dao.UserDAO;
import com.alisls.demo.springcloud.service.user.dto.UserDTO;
import com.alisls.demo.springcloud.service.user.dto.UserPwdDTO;
import com.alisls.demo.springcloud.service.user.entity.UserDO;
import com.alisls.demo.springcloud.service.user.service.UserService;
import com.demo.springcloud.common.core.util.IdWorker;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 用户Service实现
 *
 * @author Ke Wang
 */
@Service("userService")
@AllArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {

    private final IdWorker idWorker;

    private final UserDAO userDAO;

    /**
     * 根据用户标识查询用户
     * @Cacheable声明方法是可缓存的(value = "缓存的名字", key = "缓存Key")
     *
     * @param id
     * @return
     */
    @Override
    @Cacheable(cacheNames = "sys_user", key = "#id")
    public UserDTO getUser(Long id) {
        Optional<UserDO> userDO = userDAO.findById(id);

        UserDTO userDTO = new UserDTO();
        userDO.ifPresent(u -> {
            BeanUtils.copyProperties(u, userDTO);
        });

        return userDTO;
    }

    /**
     * 根据用户名查询用户
     */
    @Override
    public UserDTO getUser(String username) {
        UserDO userDO = userDAO.findByUsername(username);

        UserDTO userDTO = new UserDTO();
        if (userDO != null) {
            BeanUtils.copyProperties(userDO, userDTO);
        }

        return userDTO;
    }

    /**
     * 根据用户名查询用户（用户结果带有密码字段）
     */
    @Override
    public UserDTO getUserWithPwd(String username) {
        UserDO userDO = userDAO.findByUsername(username);

        UserDTO userDTO = new UserPwdDTO();
        if (userDO != null) {
            BeanUtils.copyProperties(userDO, userDTO);
        }

        return userDTO;
    }

    /**
     * 保存用户
     * 注意：Jpa在保存用户前，会根据ID查询用户，之后再保存
     */
    @Override
    @CachePut(cacheNames = "sys_user", key = "#userDTO.id")
    public UserDTO save(UserDTO userDTO) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO, userDO);
        userDO.setId(idWorker.nextId());

        UserDO newUserDO = userDAO.save(userDO);
        userDTO.setId(newUserDO.getId());

        return userDTO;
    }

    /**
     * 修改用户
     * 注意：Jpa在修改人员之前会先进行查询，之后再进行删除
     */
    @Override
    @CachePut(cacheNames = "sys_user", key = "#userDTO.id")
    public UserDTO update(UserDTO userDTO) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO, userDO);

        UserDO newUserDO = userDAO.save(userDO);
        if (log.isDebugEnabled()) {
            log.debug("用户信息修改成功，新的用户数据为{}", newUserDO);
        }
        return userDTO;
    }

}

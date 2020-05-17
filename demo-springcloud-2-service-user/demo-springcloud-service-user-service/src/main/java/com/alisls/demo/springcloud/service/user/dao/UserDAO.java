package com.alisls.demo.springcloud.service.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.alisls.demo.springcloud.service.user.entity.UserDO;

/**
 * 用户DAO
 *
 * @author Ke Wang
 */
public interface UserDAO extends JpaRepository<UserDO, Long>, JpaSpecificationExecutor<UserDO> {

    /**
     * 根据用户名查询用户
     */
    UserDO findByUsername(String username);

}

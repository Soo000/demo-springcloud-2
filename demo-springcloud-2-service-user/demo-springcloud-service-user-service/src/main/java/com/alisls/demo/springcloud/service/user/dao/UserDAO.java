package com.alisls.demo.springcloud.service.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.alisls.demo.springcloud.service.user.entity.UserDO;

public interface UserDAO extends JpaRepository<UserDO, Long>, JpaSpecificationExecutor<UserDO> {

    UserDO findByUsername(String username);

}

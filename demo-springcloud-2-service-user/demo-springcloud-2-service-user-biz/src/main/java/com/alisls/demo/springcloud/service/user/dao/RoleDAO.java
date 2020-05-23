package com.alisls.demo.springcloud.service.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.alisls.demo.springcloud.service.user.entity.RoleDO;

/**
 * 角色DAO
 *
 * @author Ke Wang
 */
public interface RoleDAO extends JpaRepository<RoleDO, Long>, JpaSpecificationExecutor<RoleDO> {

}

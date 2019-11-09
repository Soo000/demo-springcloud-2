package com.alisls.demo.springcloud.provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alisls.demo.springcloud.provider.entity.UserDO;

public interface UserRepository extends JpaRepository<UserDO, Integer> {
	
}
package com.alisls.demo.springcloud.provider.repository;

import org.springframework.data.repository.CrudRepository;

import com.alisls.demo.springcloud.provider.entity.UserDO;

public interface UserRepository extends CrudRepository<UserDO, Integer> {

	public UserDO findByUserId(Integer userId);
	
}

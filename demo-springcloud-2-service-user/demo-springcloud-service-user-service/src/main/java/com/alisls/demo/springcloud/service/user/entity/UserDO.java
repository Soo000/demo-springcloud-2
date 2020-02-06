package com.alisls.demo.springcloud.service.user.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "sys_user")
@Data
public class UserDO {

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String username;
	
}

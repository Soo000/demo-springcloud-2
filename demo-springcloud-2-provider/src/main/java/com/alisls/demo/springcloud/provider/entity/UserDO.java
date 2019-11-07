package com.alisls.demo.springcloud.provider.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "my_user")
public class UserDO {

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "user_id")
	private Integer userId;
	private String username;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}

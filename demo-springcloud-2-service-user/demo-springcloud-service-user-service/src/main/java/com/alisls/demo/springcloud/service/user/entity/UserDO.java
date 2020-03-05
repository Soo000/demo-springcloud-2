package com.alisls.demo.springcloud.service.user.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.springcloud.common.model.entity.BaseDO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "sys_user")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDO extends BaseDO {

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String username;
	private String password;
	
}

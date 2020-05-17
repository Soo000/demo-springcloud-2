package com.alisls.demo.springcloud.service.user.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.springcloud.common.model.entity.BaseDO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色DO
 *
 * @author Ke Wang
 */
@Entity
@Table(name = "sys_role")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RoleDO extends BaseDO {

	@Id
	private Long id;

	private String roleCode;
	
	private String roleName;
	
}

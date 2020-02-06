package com.alisls.demo.springcloud.service.user.dto;

import java.io.Serializable;

import com.springcloud.common.model.dto.BaseDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoleDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -1541136050994621833L;
	
	private Long id;
	private String roleName;

}

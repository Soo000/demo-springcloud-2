package com.alisls.demo.springcloud.common.model.user.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1171647143049064655L;
	
	private Long id;
	private String username;
	
}

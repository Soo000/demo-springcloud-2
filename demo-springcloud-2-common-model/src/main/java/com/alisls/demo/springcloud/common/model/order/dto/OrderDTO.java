package com.alisls.demo.springcloud.common.model.order.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class OrderDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String orderName;

}

package com.alisls.demo.springcloud.service.order.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单DTO
 *
 * @author Ke Wang
 */
@ApiModel(description = "订单DTO")
@Data
public class OrderDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "订单标识")
	private Long id;

	@ApiModelProperty(value = "订单编号", required = true, example = "2020123159590001")
	private String orderNo;

    @ApiModelProperty(value = "订单名称", required = true, example = "我的订单")
	private String orderName;

}

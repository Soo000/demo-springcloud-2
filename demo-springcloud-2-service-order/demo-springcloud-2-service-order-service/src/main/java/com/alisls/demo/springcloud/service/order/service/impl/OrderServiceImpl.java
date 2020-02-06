package com.alisls.demo.springcloud.service.order.service.impl;

import org.springframework.stereotype.Service;

import com.alisls.demo.springcloud.service.order.dto.OrderDTO;
import com.alisls.demo.springcloud.service.order.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Override
	public OrderDTO getOrder(Long id) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setId(1L);
		orderDTO.setOrderName("测试订单");
		return orderDTO;
	}
	
}

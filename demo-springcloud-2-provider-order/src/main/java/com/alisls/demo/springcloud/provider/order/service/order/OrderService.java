package com.alisls.demo.springcloud.provider.order.service.order;

import org.springframework.stereotype.Service;

import com.alisls.demo.springcloud.common.model.order.dto.OrderDTO;

@Service("orderService")
public class OrderService {

	public OrderDTO getOrder(Long id) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setId(1L);
		orderDTO.setOrderName("测试订单");
		return orderDTO;
	}
	
}

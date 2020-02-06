package com.alisls.demo.springcloud.service.order.service;

import com.alisls.demo.springcloud.service.order.dto.OrderDTO;

public interface OrderService {

	OrderDTO getOrder(Long id);
	
}

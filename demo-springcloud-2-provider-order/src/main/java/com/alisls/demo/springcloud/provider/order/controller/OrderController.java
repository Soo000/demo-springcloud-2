package com.alisls.demo.springcloud.provider.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alisls.demo.springcloud.common.model.order.dto.OrderDTO;
import com.alisls.demo.springcloud.common.response.DataResult;
import com.alisls.demo.springcloud.common.response.Response;
import com.alisls.demo.springcloud.provider.order.service.order.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@GetMapping("/getOrderById/{id}")
	public Response getOrderById(@PathVariable Long id) {
		OrderDTO orderDTO = orderService.getOrder(id);
		/*
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
		return DataResult.ofSuccess(orderDTO);
	}
	
	@GetMapping("/getOrder/{id}")
	public OrderDTO getOrder(@PathVariable Long id) {
		/*
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
		return orderService.getOrder(id);
	}
	
}

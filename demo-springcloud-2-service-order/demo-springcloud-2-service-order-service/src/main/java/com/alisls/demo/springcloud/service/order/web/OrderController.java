package com.alisls.demo.springcloud.service.order.web;

import com.alisls.demo.springcloud.service.order.client.ProductClient;
import com.alisls.demo.springcloud.service.order.dto.OrderDTO;
import com.alisls.demo.springcloud.service.order.service.OrderService;
import com.alisls.demo.springcloud.service.product.dto.ProductDTO;
import com.springcloud.common.model.dto.DataResult;
import com.springcloud.common.model.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@Resource
	private ProductClient productClient;

	@GetMapping("/getOrderById/{id}")
	public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
		OrderDTO orderDTO = orderService.getOrder(id);
		/*
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
		return ResponseEntity.ok(orderDTO);
	}
	
	@GetMapping("/getOrder/{id}")
	public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
		/*
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
		return ResponseEntity.ok(null);
	}

	@GetMapping("/getOrderProducts")
	public ResponseEntity<Result> getOrderProducts() {
        DataResult<ProductDTO> dataResult = productClient.listProducts();
        return ResponseEntity.ok(dataResult);
    }
	
}

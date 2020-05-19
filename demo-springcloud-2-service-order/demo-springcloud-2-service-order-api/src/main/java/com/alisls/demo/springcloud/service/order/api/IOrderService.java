package com.alisls.demo.springcloud.service.order.api;

import com.alisls.demo.springcloud.service.order.dto.OrderDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 订单服务
 *
 * @author Ke Wang
 */
@RequestMapping("/order")
public interface IOrderService {

    @GetMapping("/getOrderById/{id}")
    OrderDTO getOrderById(@PathVariable("id") Long id);

}

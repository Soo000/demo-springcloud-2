package com.alisls.demo.springcloud.service.order.web;

import com.alisls.demo.springcloud.service.order.client.ProductClient;
import com.alisls.demo.springcloud.service.order.dto.OrderDTO;
import com.alisls.demo.springcloud.service.order.service.OrderService;
import com.alisls.demo.springcloud.service.product.dto.ProductDTO;
import com.springcloud.common.model.dto.DataResult;
import com.springcloud.common.model.dto.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 订单管理
 *
 * @author Ke Wang
 */
@Api(description = "订单管理")
@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    /**
     * 订单服务
     */
	private final OrderService orderService;

    /**
     * 商品客户端
     */
	private final ProductClient productClient;

    /**
     * 根据订单标识查询订单
     */
	@ApiOperation(value = "查询订单", notes = "根据订单标识查询订单")
    @ApiImplicitParam(
            name = "id",
            required = true,
            paramType = "path",
            dataType = "Long",
            example = "1234567890123456789"
    )
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

    /**
     * 根据订单标识查询订单商品
     */
    @ApiOperation(value = "查询订单商品", notes = "根据订单标识查询订单商品")
    @ApiImplicitParam(
            name = "orderId",
            required = true,
            paramType = "path",
            dataType = "Long",
            example = "1234567890123456789"
    )
	@GetMapping("/getOrderProducts/{orderId}")
	public ResponseEntity<Result> getOrderProducts(@PathVariable Long orderId) {
        DataResult<ProductDTO> dataResult = productClient.listProducts();
        return ResponseEntity.ok(dataResult);
    }
	
}

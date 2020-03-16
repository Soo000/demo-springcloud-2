package com.alisls.demo.springcloud.service.product.api;

import com.alisls.demo.springcloud.service.product.dto.ProductDTO;
import com.springcloud.common.model.dto.DataResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品服务
 *
 * @author Ke Wang
 */
@RequestMapping("/product")
public interface IProduct {

    @GetMapping("/listProducts")
    DataResult<ProductDTO> listProducts();

}

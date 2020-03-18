package com.alisls.demo.springcloud.service.product.web;

import com.alisls.demo.springcloud.service.product.dto.ProductDTO;
import com.springcloud.common.model.dto.DataResult;
import com.springcloud.common.model.dto.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品管理
 *
 * @author Ke Wang
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    /**
     * 查询商品列表
     * @return Result
     */
    @GetMapping("/listProducts")
    //@PreAuthorize("hasAuthority('product:listProducts')")
    public ResponseEntity<Result> listProducts() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1000L);
        productDTO.setProductName("WK的商品");
        return ResponseEntity.ok(DataResult.ofSuccess(productDTO));
    }

}

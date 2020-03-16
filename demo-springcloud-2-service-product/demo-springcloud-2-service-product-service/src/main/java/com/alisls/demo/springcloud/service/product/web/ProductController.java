package com.alisls.demo.springcloud.service.product.web;

import com.springcloud.common.model.dto.DataResult;
import com.springcloud.common.model.dto.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    public Result listProducts() {
        List<String> list = new ArrayList<>();
        list.add("眼睛");
        list.add("双肩包");
        return DataResult.ofSuccess(list);
    }

}

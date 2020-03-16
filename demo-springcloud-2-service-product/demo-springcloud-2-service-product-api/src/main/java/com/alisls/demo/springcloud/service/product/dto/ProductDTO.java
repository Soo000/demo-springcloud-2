package com.alisls.demo.springcloud.service.product.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * ProductDTO
 *
 * @author Ke Wang
 */
@Getter
@Setter
@ToString
public class ProductDTO implements Serializable {

    private Long id;
    private String productName;

}

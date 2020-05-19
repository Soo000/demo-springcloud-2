package com.alisls.demo.springcloud.service.user.vo;

import com.alisls.demo.springcloud.service.order.dto.OrderDTO;
import com.alisls.demo.springcloud.service.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户订单VO
 *
 * @author Ke Wang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderVO implements Serializable {

    private UserDTO userDTO;
    private OrderDTO orderDTO;

}

package com.alisls.demo.springcloud.service.user.service;

import com.alisls.demo.springcloud.service.user.dto.MyMsg;

/**
 * 接收消息Service
 *
 * @author Ke Wang
 */
public interface ReceiveService {

    void receive(MyMsg myMsg);

}

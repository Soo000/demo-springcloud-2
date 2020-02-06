package com.alisls.demo.springcloud.service.order.service;

public interface IMessageService {

    /**
     * 消息发送接口
     * @param message
     */
    void send(String message);

}

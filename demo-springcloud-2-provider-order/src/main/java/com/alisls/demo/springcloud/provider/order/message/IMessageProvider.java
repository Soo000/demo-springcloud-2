package com.alisls.demo.springcloud.provider.order.message;

public interface IMessageProvider {

    /**
     * 消息发送接口
     * @param message
     */
    void send(String message);

}

package com.alisls.demo.springcloud.provider.message;

public interface IMessageProvider {

    /**
     * 消息发送接口
     * @param message
     */
    void send(String message);

}

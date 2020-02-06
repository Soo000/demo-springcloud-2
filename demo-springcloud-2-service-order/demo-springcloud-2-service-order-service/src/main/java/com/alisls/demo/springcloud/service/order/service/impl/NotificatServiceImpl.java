package com.alisls.demo.springcloud.service.order.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.alisls.demo.springcloud.service.order.service.NotificatService;
import com.alisls.demo.springcloud.service.order.stream.MySource;

@Service("notificatService")
public class NotificatServiceImpl implements NotificatService {

    private static final Logger logger = LoggerFactory.getLogger(NotificatServiceImpl.class);

    @Resource
    private MySource mySource;

    @Override
    public boolean notify(String message) {
        logger.info("准备发送消息：{}", message);
        mySource.output().send(MessageBuilder.withPayload(message).build());
        logger.info("完成发送消息：{} ", message);
        return true;
    }

}

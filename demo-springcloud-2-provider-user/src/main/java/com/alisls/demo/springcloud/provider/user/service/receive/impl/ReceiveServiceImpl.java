package com.alisls.demo.springcloud.provider.user.service.receive.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import com.alisls.demo.springcloud.provider.user.dto.MyMsg;
import com.alisls.demo.springcloud.provider.user.service.receive.ReceiveService;
import com.alisls.demo.springcloud.provider.user.stream.MySink;

@Service("receiveService")
public class ReceiveServiceImpl implements ReceiveService {

    private static final Logger logger = LoggerFactory.getLogger(ReceiveServiceImpl.class);

    @StreamListener(MySink.MY_INPUT)
    @Override
    public void receive(MyMsg myMsg) {
        logger.info("接收到消息：{}", myMsg);
    }

}

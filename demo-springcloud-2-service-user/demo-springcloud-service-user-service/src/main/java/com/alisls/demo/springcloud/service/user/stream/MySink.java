package com.alisls.demo.springcloud.service.user.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MySink {

    String MY_INPUT = "myInput";

    @Input(MY_INPUT)
    SubscribableChannel input();

}

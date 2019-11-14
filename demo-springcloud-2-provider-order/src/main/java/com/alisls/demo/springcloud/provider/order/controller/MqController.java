package com.alisls.demo.springcloud.provider.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alisls.demo.springcloud.provider.order.service.notificat.NotificatService;

@RestController
@RequestMapping("/demo-springcloud-2/provider-order/mq")
public class MqController {

    @Autowired
    private NotificatService notificatService;

    @PostMapping("/send")
    public boolean sendMessage(@RequestBody String msg) {
        return notificatService.notify(msg);
    }

}
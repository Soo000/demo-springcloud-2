package com.alisls.demo.springcloud.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alisls.demo.springcloud.provider.service.notificat.NotificatService;

@RestController
@RequestMapping("/mq")
public class MqController {

    @Autowired
    private NotificatService notificatService;

    @PostMapping("/send")
    public boolean sendMessage(@RequestBody String msg) {
        return notificatService.notify(msg);
    }

}

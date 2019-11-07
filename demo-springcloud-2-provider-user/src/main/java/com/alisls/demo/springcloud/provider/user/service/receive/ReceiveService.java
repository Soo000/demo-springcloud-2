package com.alisls.demo.springcloud.provider.user.service.receive;

import com.alisls.demo.springcloud.provider.user.dto.MyMsg;

public interface ReceiveService {

    void receive(MyMsg myMsg);

}

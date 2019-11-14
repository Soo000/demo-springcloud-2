package com.alisls.demo.springcloud.common.response;

import java.io.Serializable;

public interface Response extends Serializable {

    boolean getStatus();

    int getCode();

    String getMessage();

}

package com.demo.springcloud.common.core.web;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.springcloud.common.core.exception.BizException;

public class BaseAdvice {

    /**
     * 捕获 BizException 异常
     * @return
     */
    @ExceptionHandler(BizException.class)
    public String bizException(BizException bizException) {
        return bizException.getMessage();
    }

    /**
     * 捕获所有异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public String exception(Exception e) {
        return "程序出现未知的错误！";
    }
	
}

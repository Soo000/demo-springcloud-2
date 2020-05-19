package com.demo.springcloud.common.core.advice;

import com.demo.springcloud.common.core.exception.BizException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 通用异常处理
 *
 * @author Ke Wang
 */
public class BaseAdvice {

    /**
     * 捕获TypeMismatchException异常
     * @param typeMismatchException
     */
    @ExceptionHandler(TypeMismatchException.class)
    public String argumentTypeMismatchException(TypeMismatchException typeMismatchException) {
        return "参数类型不匹配错误！";
    }

    /**
     * 捕获MethodArgumentNotValidException异常
     * Hibernate-Validator校验框架抛出的异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        return methodArgumentNotValidException.getMessage();
    }

    /**
     * 捕获BizException异常
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

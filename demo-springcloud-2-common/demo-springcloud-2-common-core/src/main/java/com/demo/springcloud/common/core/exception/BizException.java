package com.demo.springcloud.common.core.exception;

/**
 * 业务异常类
 *
 * @author Ke Wang
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = -1441309672446775606L;

	public BizException(String message) {
        super(message);
    }

}

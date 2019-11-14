package com.alisls.demo.springcloud.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Result implements Response, Serializable {

	private static final long serialVersionUID = 4285735231924292984L;
	
	private boolean status;
    private int code;
    private String message;

    public Result(ResultCode resultCode) {
        this.status = resultCode.getStatus();
        this.code = resultCode.getCode();
        this.message = resultCode.getMsg();
    }

    /**
     * 成功
     * @return
     */
    public static Result ofSuccess() {
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 失败
     * @return
     */
    public static Result ofFail() {
        return new Result(ResultCode.FAIL);
    }

    /**
     * 未认证
     * @return
     */
    public static Result ofUncertificated() {
        return new Result(ResultCode.UNCERTIFICATED);
    }

    /**
     * 未授权
     * @return
     */
    public static Result ofUnauthorized() {
        return new Result(ResultCode.UNAUTHORIZED);
    }

    /**
     * 认证失败
     * @return
     */
    public static Result ofAuthenticationFailed() {
        return new Result(ResultCode.AUTHENTICATION_FAILED);
    }

    /**
     * 根据 resultCode 返回
     * @param resultCode
     * @return
     */
    public static Result of(ResultCode resultCode) {
        return new Result(resultCode);
    }

    public boolean getStatus() {
        return this.status;
    }
}

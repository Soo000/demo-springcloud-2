package com.alisls.demo.springcloud.common.response;

public enum ResultCode {

    SUCCESS(true, 10000, "成功"),

    PARAM_ERROR(false, 20001, "参数错误"),
    NAME_CANNOT_BE_EMPTY(false, 20002, "用户名不能为空"),
    MOBILE_CANNOT_BE_EMPTY(false, 20003, "手机号码不能为空"),

    UNCERTIFICATED(false, 40001, "未认证"),
    UNAUTHORIZED(false, 40003, "权限不足"),
    AUTHENTICATION_FAILED(false, 40004, "用户名或密码错误"),

    USER_NOT_EXIST(false, 50001, "用户不存在"),
    USER_ALREADY_EXISTS(false, 50002, "用户已存在"),
    ILLEGAL_TOKEN(false, 50008, "非法的token"),
    OTHER_CLIENT_ALREADY_LOGGED(false, 50012, "其他客户端登录了"),
    TOKEN_EXPIRED(false, 50014, "Token 已过期"),

    FAIL(false, 99999, "fail");

    private boolean status;
    private int code;
    private String msg;

    ResultCode(boolean status, int code, String msg) {
        this.status = status;
        this.code = code;
        this.msg = msg;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}

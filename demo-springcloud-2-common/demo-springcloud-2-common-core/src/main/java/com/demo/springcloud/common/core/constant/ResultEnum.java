package com.demo.springcloud.common.core.constant;

import lombok.Getter;

@Getter
public enum ResultEnum {
    
    /*
     * 成功失败
     */
    SUCCESS(true, 10000, "操作成功！"),
    FAIL(false, 10001, "操作失败"),

    /**
     * 权限
     */
    UNAUTHENTICATED(false, 10002, "您还未登录"),
    UNAUTHORISE(false, 10003, "权限不足"),
    SERVER_ERROR(false, 99999, "抱歉，系统繁忙，请稍后重试！"),

    /*
     * 用户操作返回码
     */
    USERNAME_OR_PASSWORD(false, 20001, "用户名或密码错误");

    /*
     * 权限操作返回码
     */

    /*
     * 其他操作返回码
     */

    // 操作是否成功
    private boolean success;

    // 操作代码
    private int code;

    // 提示信息
    private String message;

    /**
     * 构造函数
     * @param success
     * @param code
     * @param message
     */
    ResultEnum(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

}

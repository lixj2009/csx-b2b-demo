package com.yh.csx.demo.core.enums;

/*
 * 报错编码定义
 */
public enum BizError {

    SUCCESS(200000, "成功"),
    COMMON_ERROR(400000, "业务异常"),
    SYSTEM_ERROR(500000, "系统异常"),

    NOT_EXISTS(400001, "操作数据不存在"),
    NOT_LOGIN(600201, "还未登录"),

    REGISTER_USER_FAILED(412505, "注册用户失败"),































    ;
    private int code;
    private String message;

    BizError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

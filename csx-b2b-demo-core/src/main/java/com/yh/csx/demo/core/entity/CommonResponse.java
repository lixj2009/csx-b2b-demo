package com.yh.csx.demo.core.entity;

import com.yh.csx.demo.core.enums.BizError;
import com.yh.csx.demo.core.exception.BusinessException;


public class CommonResponse<T> {
    private static int SUCCESS = BizError.SUCCESS.getCode();
    private static int ERROR = BizError.SYSTEM_ERROR.getCode();
    private static String SUCCESS_MSG = "success";
    private static String ERROR_MSG = "system error";

    public static <T> CommonResponse<T> success(String message, T data) {
        return new CommonResponse<>(SUCCESS, message, data);
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(SUCCESS, SUCCESS_MSG, data);
    }

    public static <T> CommonResponse<T> success() {
        return new CommonResponse<>(SUCCESS, SUCCESS_MSG, null);
    }

    public static <T> CommonResponse<T> error(int code, String message, T data) {
        return new CommonResponse<>(code, message, data);
    }

    public static <T> CommonResponse<T> error(int code, String message) {
        return new CommonResponse<>(code, message, null);
    }

    public static <T> CommonResponse<T> error(String message, T data) {
        return new CommonResponse<>(ERROR, message, data);
    }

    public static <T> CommonResponse<T> error(String message) {
        return new CommonResponse<>(ERROR, message, null);
    }

    public static <T> CommonResponse<T> error() {
        return new CommonResponse<>(ERROR, ERROR_MSG, null);
    }

    public static <T> CommonResponse<T> error(Throwable e) {
        if (e instanceof BusinessException) {
            BusinessException be = (BusinessException) e;
            return CommonResponse.error(be.getCode(), be.getMessage());
        } else {
            return CommonResponse.error();
        }
    }

    private int code;
    private String message;
    private T data;

    public CommonResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}

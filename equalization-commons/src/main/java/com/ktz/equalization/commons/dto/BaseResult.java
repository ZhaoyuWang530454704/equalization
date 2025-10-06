package com.ktz.equalization.commons.dto;

import java.io.Serializable;

/**
 * @author 曾泉明
 * @date 2021/1/3 下午2:17
 */
public class BaseResult<T> implements Serializable {
    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_FAIL = 500;

    /**
     * 状态码
     */
    private int status;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 响应返回数据
     */
    private T data;

    public static <T> BaseResult<T> success() {
        return BaseResult.createResult(STATUS_SUCCESS, "成功", null);
    }

    public static <T> BaseResult<T> success(String message) {
        return BaseResult.createResult(STATUS_SUCCESS, message, null);
    }

    public static <T> BaseResult<T> success(String message, T data) {
        return BaseResult.createResult(STATUS_SUCCESS, message, data);
    }

    public static <T> BaseResult<T> fail() {
        return BaseResult.createResult(STATUS_FAIL, "失败", null);
    }

    public static <T> BaseResult<T> fail(String message) {
        return BaseResult.createResult(STATUS_FAIL, message, null);
    }

    public static <T> BaseResult<T> fail(int status, String message) {
        return BaseResult.createResult(status, message, null);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private static <T> BaseResult<T> createResult(int status, String message, T data) {
        BaseResult<T> baseResult = new BaseResult<T>();
        baseResult.setStatus(status);
        baseResult.setMessage(message);
        baseResult.setData(data);
        return baseResult;
    }

}

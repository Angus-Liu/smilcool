package com.smilcool.server.core.pojo.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 统一接口交互对象
 */
@Data
@RequiredArgsConstructor
public class Result<T> {
    /**
     * 请求情况
     */
    private final Boolean success;

    /**
     * 时间戳
     */
    private final long timestamp = System.currentTimeMillis();

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 反馈消息
     */
    private final String msg;

    /**
     * 反馈数据
     */
    private final T data;

    private static Result<Empty> SUCCESS_EMPTY_RESULT = success(Empty.INSTANCE);

    public static <T> Result<T> success(T data) {
        return new Result<>(true, 200, "OK", data);
    }

    public static Result<Empty> success() {
        return SUCCESS_EMPTY_RESULT;
    }

    public static Result<Empty> error(Integer code, String msg) {
        return new Result<>(false, code, msg, Empty.INSTANCE);
    }

    public static Result<Empty> error(String msg) {
        return error(400, msg);
    }

    public static class Empty {
        private static final Empty INSTANCE = new Empty();
    }
}

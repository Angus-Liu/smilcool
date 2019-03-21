package com.smilcool.server.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Angus
 * @date 2019/3/20
 */
@Data
@AllArgsConstructor
public class Result<T> {
    /**
     * 业务请求情况
     */
    private Boolean success;

    /**
     * 业务状态码
     */
    private Integer code;

    /**
     * 反馈消息
     */
    private String msg;

    /**
     * 反馈数据
     */
    private T data;

    public static <T> Result success(T data) {
        return new Result<>(true, 200, null, data);
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(String msg) {
        return new Result<>(false, 500, msg, null);
    }

    public static Result error() {
        return error(null);
    }
}

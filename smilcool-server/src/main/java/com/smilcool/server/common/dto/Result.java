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
     * 响应业务请求情况
     */
    private Boolean success;

    /**
     * 响应业务状态
     */
    private Integer status;

    /**
     * 反馈消息
     */
    private String msg;

    /**
     * 反馈数据
     */
    private T data;

    public static <T> Result success(T data) {
        return new Result<>(true, 200, "SUCCESS", data);
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

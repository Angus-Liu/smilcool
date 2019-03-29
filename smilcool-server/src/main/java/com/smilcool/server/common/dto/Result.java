package com.smilcool.server.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Angus
 * @date 2019/3/20
 */
@Data
@AllArgsConstructor
@ApiModel("统一接口交互对象")
public class Result<T> {
    /**
     * 请求情况
     */
    @ApiModelProperty("请求情况")
    private Boolean success;

    /**
     * 时间戳
     */
    @ApiModelProperty("时间戳")
    private long timestamp = System.currentTimeMillis();

    /**
     * 状态码
     */
    @ApiModelProperty("状态码")
    private Integer code;

    /**
     * 反馈消息
     */
    @ApiModelProperty("反馈消息")
    private String msg;

    /**
     * 反馈数据
     */
    @ApiModelProperty("反馈数据")
    private T data;

    private Result(Boolean success, Integer code, String msg, T data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(true, 200, "OK", data);
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

package com.smilcool.server.core.pojo.form;

import lombok.Data;

@Data
public class MomentQueryForm {
    /**
     * 发布用户ID
     */
    private Integer userId;

    /**
     * 动态类别
     */
    private String momentCategory;

    /**
     * 通用查询字段
     */
    private String general;
}

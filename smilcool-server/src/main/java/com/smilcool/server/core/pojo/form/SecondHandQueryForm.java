package com.smilcool.server.core.pojo.form;

import lombok.Data;

/**
 * @author Angus
 * @date 2019/5/23
 */
@Data
public class SecondHandQueryForm {
    /**
     * 发布用户ID
     */
    private Integer userId;

    /**
     * 二手交易类别
     */
    private String secondHandCategory;

    /**
     * 通用查询字段
     */
    private String general;
}

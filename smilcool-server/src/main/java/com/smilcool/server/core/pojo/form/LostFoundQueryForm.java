package com.smilcool.server.core.pojo.form;

import lombok.Data;

/**
 * @author Angus
 * @date 2019/5/23
 */
@Data
public class LostFoundQueryForm {
    /**
     * 发布用户ID
     */
    private Integer userId;

    /**
     * 失物寻物类别
     */
    private String lostFoundCategory;

    /**
     * 通用查询字段
     */
    private String general;
}

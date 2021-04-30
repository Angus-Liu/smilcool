package com.smilcool.server.core.pojo.form;

import lombok.Data;

@Data
public class FileQueryForm {
    /**
     * 发布用户ID
     */
    Integer userId;

    /**
     * 文件类别
     */
    String fileCategory;

    /**
     * 通用查询字段
     */
    private String general;
}

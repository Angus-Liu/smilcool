package com.smilcool.server.core.pojo.form;

import lombok.Data;

/**
 * @author Angus
 * @date 2019/5/22
 */
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

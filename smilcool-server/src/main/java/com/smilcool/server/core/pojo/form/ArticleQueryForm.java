package com.smilcool.server.core.pojo.form;

import lombok.Data;

/**
 * @author Angus
 * @date 2019/5/21
 */
@Data
public class ArticleQueryForm {
    /**
     * 发布用户
     */
    private Integer userId;

    /**
     * 文章类别
     */
    private String articleCategory;

    /**
     * 通用查询字段
     */
    private String general;
}

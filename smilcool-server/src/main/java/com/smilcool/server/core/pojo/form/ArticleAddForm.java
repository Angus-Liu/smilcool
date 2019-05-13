package com.smilcool.server.core.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Angus
 * @date 2019/4/12
 */
@Data
public class ArticleAddForm {
    @NotBlank(message = "文章类别不能为空")
    private String articleCategory;

    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "简介不能为空")
    private String brief;

    private String cover;

    private String tags;

    private String markdownContent;

    private String htmlContent;
}

package com.smilcool.server.core.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

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

    @NotBlank(message = "Markdown 内容不能为空")
    private String markdownContent;

    @NotBlank(message = "HTML 内容不能为空")
    private String htmlContent;
}

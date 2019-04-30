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
    @NotNull(message = "发布用户ID不能为空")
    private Integer userId;

    @NotNull(message = "资源类型ID不能为空")
    private Integer resourceTypeId;

    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "简介不能为空")
    private String brief;

    private String tags;

    private String cover;

    private String remark;

    private String markdownContent;

    private String htmlContent;
}

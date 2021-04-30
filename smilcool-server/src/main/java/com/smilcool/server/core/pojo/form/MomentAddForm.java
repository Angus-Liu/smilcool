package com.smilcool.server.core.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MomentAddForm {
    @NotBlank(message = "动态类别不能为空")
    private String momentCategory;

    @NotBlank(message = "内容不能为空")
    private String content;

    private String images;
}

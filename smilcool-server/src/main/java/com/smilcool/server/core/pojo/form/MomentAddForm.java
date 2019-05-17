package com.smilcool.server.core.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Angus
 * @date 2019/5/17
 */
@Data
public class MomentAddForm {
    @NotBlank(message = "动态类别不能为空")
    private String momentCategory;

    @NotBlank(message = "内容不能为空")
    private String content;

    private String images;
}

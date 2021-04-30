package com.smilcool.server.core.pojo.form;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class ResourceAddForm {
    @NotNull(message = "发布用户ID不能为空")
    private Integer userId;

    @NotBlank(message = "资源所属字典类型不能为空")
    private String resourceDicType;

    @NotBlank(message = "资源所属字典项目不能为空")
    private String resourceDicItem;
}

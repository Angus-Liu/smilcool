package com.smilcool.server.core.pojo.form;

import com.smilcool.server.common.enums.PermissionType;
import com.smilcool.server.common.util.validation.EnumValidation;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PermissionAddForm {
    private Integer parentId;

    @NotBlank(message = "权限名不能为空")
    private String name;

    @NotBlank(message = "权限描述不能为空")
    private String description;

    @NotBlank(message = "请求地址不能为空")
    private String url;

    @EnumValidation(value = PermissionType.class, field = "type")
    private Integer type;

    private Integer seq;

    private String remark;
}

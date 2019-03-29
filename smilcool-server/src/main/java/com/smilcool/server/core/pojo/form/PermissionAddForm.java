package com.smilcool.server.core.pojo.form;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author Angus
 * @date 2019/3/29
 */
@Data
public class PermissionAddForm {
    private Integer parentId;

    @NotBlank(message = "权限名不能为空")
    private String name;

    @NotBlank(message = "权限描述不能为空")
    private String description;

    @NotBlank(message = "请求地址不能为空")
    private String url;

    @Min(value = 1, message = "类型取值为[1-菜单，2-按钮，3-其他]")
    @Max(value = 3, message = "类型取值为[1-菜单，2-按钮，3-其他]")
    private Integer type;

    private Integer seq;

    private String remark;
}

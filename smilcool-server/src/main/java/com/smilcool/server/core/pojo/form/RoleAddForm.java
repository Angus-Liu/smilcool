package com.smilcool.server.core.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 角色添加表单
 *
 * @author Angus
 * @date 2019/3/28
 */
@Data
public class RoleAddForm {
    /**
     * 角色名
     */
    @NotBlank(message = "角色名不能为空")
    String name;

    /**
     * 角色描述
     */
    @NotBlank(message = "角色描述不能为空")
    String description;

    /**
     * 备注
     */
    String remark;
}

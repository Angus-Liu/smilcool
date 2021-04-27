package com.smilcool.server.core.pojo.form;

import com.smilcool.server.common.enums.CommonState;
import com.smilcool.server.common.util.validation.EnumValidation;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RoleUpdateForm {

    @NotNull(message = "角色 ID 不能为空")
    Integer id;

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
     * 角色状态
     */
    @EnumValidation(value = CommonState.class, field = "state")
    Integer state;

    /**
     * 备注
     */
    String remark;
}

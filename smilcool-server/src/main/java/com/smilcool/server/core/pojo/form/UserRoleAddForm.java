package com.smilcool.server.core.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserRoleAddForm {
    @NotNull(message = "用户 ID 不能为空")
    Integer userId;
    @NotNull(message = "角色 ID 不能为空")
    Integer roleId;
}

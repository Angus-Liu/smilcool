package com.smilcool.server.core.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Angus
 * @date 2019/3/30
 */
@Data
public class UserRoleAddForm {
    @NotNull(message = "用户 id 不能为空")
    Integer userId;
    @NotNull(message = "角色 id 不能为空")
    Integer roleId;
}

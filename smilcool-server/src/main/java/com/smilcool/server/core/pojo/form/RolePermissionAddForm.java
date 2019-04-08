package com.smilcool.server.core.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * @author Angus
 * @date 2019/3/29
 */
@Data
public class RolePermissionAddForm {
    @NotNull(message = "角色 ID 不能为空")
    Integer roleId;
    @NotNull(message = "权限 ID 不能为空")
    Integer permissionId;
}

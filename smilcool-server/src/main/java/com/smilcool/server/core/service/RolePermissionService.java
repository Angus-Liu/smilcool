package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.vo.RolePermissionVO;

/**
 * @author Angus
 * @date 2019/3/29
 */
public interface RolePermissionService {
    RolePermissionVO getByRoleId(Integer roleId);
}

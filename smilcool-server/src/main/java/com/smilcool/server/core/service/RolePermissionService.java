package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.form.RolePermissionAddForm;
import com.smilcool.server.core.pojo.po.Permission;
import com.smilcool.server.core.pojo.vo.PermissionVO;
import com.smilcool.server.core.pojo.vo.RolePermissionVO;

import java.util.List;
import java.util.Set;

/**
 * @author Angus
 * @date 2019/3/29
 */
public interface RolePermissionService {
    RolePermissionVO add(RolePermissionAddForm form);

    List<RolePermissionVO> list();

    /**
     * 获取角色对应的权限信息
     *
     * @param roleId 角色 id
     * @return
     */
    List<Permission> getPermissionListByRoleId(Integer roleId);

    /**
     * 获取角色对应的权限信息
     *
     * @param roleId 角色 id
     * @return
     */
    List<PermissionVO> getPermissionVOListByRoleId(Integer roleId);


    List<Permission> getPermissionListByRoleIdList(List<Integer> roleIdList);

    Set<String> getPermissionNames(Integer userId);

    Set<String> getPermissionNames(String username);
}

package com.smilcool.server.core.dao;

import com.smilcool.server.core.pojo.po.Permission;
import com.smilcool.server.core.pojo.po.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);

    RolePermission selectByRoleIdAndPermissionId(@Param("roleId") Integer roleId,
                                                 @Param("permissionId") Integer permissionId);

    List<RolePermission> selectAll();

    List<Permission> selectPermissionListByRoleId(Integer roleId);

    List<Permission> selectPermissionListByRoleIdList(List<Integer> roleIdList);
}
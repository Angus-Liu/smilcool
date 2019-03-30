package com.smilcool.server.core.dao;

import com.smilcool.server.core.pojo.po.Role;
import com.smilcool.server.core.pojo.po.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    List<UserRole> selectAll();

    UserRole selectByUserIdAndRoleId(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    List<Role> selectRoleByUserId(Integer userId);
}
package com.smilcool.server.core.dao;

import com.smilcool.server.core.pojo.po.Role;
import com.smilcool.server.core.pojo.po.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    List<UserRole> select();

    UserRole selectByUserIdAndRoleId(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    List<Role> selectRoleByUserId(Integer userId);

    Set<String> selectRoleNameByUsername(String username);

    Set<String> selectRoleNameByUserId(Integer userId);

    Set<String> selectRoleDescriptionByUserId(Integer userId);
}
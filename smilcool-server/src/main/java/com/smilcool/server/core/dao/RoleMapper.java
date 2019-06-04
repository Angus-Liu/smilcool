package com.smilcool.server.core.dao;

import com.smilcool.server.core.pojo.po.Role;
import com.smilcool.server.core.pojo.vo.RoleVO;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /* 以下为自定义内容 */

    Role selectInitialRole();

    Role selectByName(String name);

    List<RoleVO> selectRoleVO();
}
package com.smilcool.server.core.service.impl;

import com.smilcool.server.core.dao.RolePermissionMapper;
import com.smilcool.server.core.pojo.vo.RolePermissionVO;
import com.smilcool.server.core.pojo.vo.RoleVO;
import com.smilcool.server.core.service.RolePermissionService;
import com.smilcool.server.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Angus
 * @date 2019/3/29
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public RolePermissionVO getByRoleId(Integer roleId) {
//        // 获取 role-permission 信息
//        List<Integer> permission =  rolePermissionMapper.selectPermissionIdByRoleId(roleId);
//        // 获取 role 信息
//        RoleVO role = roleService.getById(roleId);
//
//        return null;
        return null;
    }
}

package com.smilcool.server.core.service.impl;

import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.PermissionMapper;
import com.smilcool.server.core.dao.RoleMapper;
import com.smilcool.server.core.dao.RolePermissionMapper;
import com.smilcool.server.core.pojo.form.RolePermissionAddForm;
import com.smilcool.server.core.pojo.po.Permission;
import com.smilcool.server.core.pojo.po.Role;
import com.smilcool.server.core.pojo.po.RolePermission;
import com.smilcool.server.core.pojo.vo.PermissionVO;
import com.smilcool.server.core.pojo.vo.RolePermissionVO;
import com.smilcool.server.core.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Angus
 * @date 2019/3/29
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;


    private RolePermissionVO getById(Integer id) {
        RolePermission rolePermission = rolePermissionMapper.selectByPrimaryKey(id);
        return BeanUtil.copyProp(rolePermission, RolePermissionVO.class);
    }

    @Override
    public RolePermissionVO add(RolePermissionAddForm rolePermissionAddForm) {
        // 检查角色是否存在
        Role role = roleMapper.selectByPrimaryKey(rolePermissionAddForm.getRoleId());
        if (role == null) {
            throw new SmilcoolException("角色不存在");
        }
        // 检查权限是否存在
        Permission permission = permissionMapper.selectByPrimaryKey(rolePermissionAddForm.getPermissionId());
        if (permission == null) {
            throw new SmilcoolException("权限不存在");
        }
        // 检查角色是否已被赋予该权限
        RolePermission select = rolePermissionMapper.selectByRoleIdAndPermissionId(rolePermissionAddForm.getRoleId(),
                rolePermissionAddForm.getPermissionId());
        if (select != null) {
            throw new SmilcoolException("角色已被赋予该权限");
        }
        // 插入角色权限关联记录
        RolePermission rolePermission = BeanUtil.copyProp(rolePermissionAddForm, RolePermission.class);
        rolePermissionMapper.insertSelective(rolePermission);
        return getById(rolePermission.getId());
    }

    @Override
    public List<RolePermissionVO> list() {
        List<RolePermission> rolePermissionList = rolePermissionMapper.selectAll();
        return BeanUtil.copyProp(rolePermissionList, RolePermissionVO.class);
    }

    @Override
    public List<PermissionVO> getPermissionListByRoleId(Integer roleId) {
        List<Permission> permissionList = rolePermissionMapper.selectPermissionListByRoleId(roleId);
        return BeanUtil.copyProp(permissionList, PermissionVO.class);
    }
}

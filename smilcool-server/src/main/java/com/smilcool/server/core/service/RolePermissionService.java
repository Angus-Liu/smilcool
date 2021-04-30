package com.smilcool.server.core.service;

import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.RolePermissionMapper;
import com.smilcool.server.core.pojo.form.RolePermissionAddForm;
import com.smilcool.server.core.pojo.po.Permission;
import com.smilcool.server.core.pojo.po.RolePermission;
import com.smilcool.server.core.pojo.vo.PermissionVO;
import com.smilcool.server.core.pojo.vo.RolePermissionVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class RolePermissionService {

    private final RoleService roleService;
    private final PermissionService permissionService;
    private final RolePermissionMapper rolePermissionMapper;

    private RolePermissionVO getById(Integer id) {
        RolePermission rolePermission = rolePermissionMapper.selectByPrimaryKey(id);
        return BeanUtil.copyProp(rolePermission, RolePermissionVO.class);
    }

    public RolePermissionVO add(RolePermissionAddForm form
    ) {
        // 检查角色是否存在
        roleService.verify(form.getRoleId());
        // 检查权限是否存在
        permissionService.checkExist(form.getPermissionId());
        // 检查角色是否已被赋予该权限
        RolePermission rolePermission = rolePermissionMapper
                .selectByRoleIdAndPermissionId(form.getRoleId(), form.getPermissionId());
        if (rolePermission != null) {
            throw new SmilcoolException("角色已被赋予该权限");
        }
        // 插入角色权限记录
        rolePermission = BeanUtil.copyProp(form, RolePermission.class);
        rolePermissionMapper.insertSelective(rolePermission);
        return getById(rolePermission.getId());
    }

    public List<RolePermissionVO> list() {
        List<RolePermission> rolePermissionList = rolePermissionMapper.select();
        return BeanUtil.copyProp(rolePermissionList, RolePermissionVO.class);
    }

    /**
     * 获取角色对应的权限信息
     */
    public List<Permission> getPermissionListByRoleId(Integer roleId) {
        return rolePermissionMapper.selectPermissionListByRoleId(roleId);
    }

    /**
     * 获取角色对应的权限信息
     */
    public List<PermissionVO> getPermissionVOListByRoleId(Integer roleId) {
        List<Permission> permissionList = getPermissionListByRoleId(roleId);
        return BeanUtil.copyProp(permissionList, PermissionVO.class);
    }

    public List<Permission> getPermissionListByRoleIdList(List<Integer> roleIdList) {
        return rolePermissionMapper.selectPermissionListByRoleIdList(roleIdList);
    }

    public Set<String> getPermissionNames(Integer userId) {
        return rolePermissionMapper.selectPermissionNameByUserId(userId);
    }

    public Set<String> getPermissionNames(String username) {
        return rolePermissionMapper.selectPermissionNameByUsername(username);
    }
}

package com.smilcool.server.core.service.impl;

import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.PermissionMapper;
import com.smilcool.server.core.pojo.form.PermissionAddForm;
import com.smilcool.server.core.pojo.po.Permission;
import com.smilcool.server.core.pojo.vo.PermissionVO;
import com.smilcool.server.core.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Angus
 * @date 2019/3/29
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    private PermissionVO getById(Integer id) {
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        return BeanUtil.copyProp(permission, PermissionVO.class);
    }

    @Override
    public void checkExist(Integer id) {
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        if (permission == null) {
            throw new SmilcoolException("权限不存在");
        }
    }

    @Override
    public List<PermissionVO> getPermissionList(Integer parentId) {
        // 获取 parent_id 为 parentId 的列表，parentId 为空时获取所有列表
        List<Permission> permissions = permissionMapper.selectByParentId(parentId);
        List<PermissionVO> permissionList = BeanUtil.copyProp(permissions, PermissionVO.class);
        // 递归获取子列表
        permissionList.forEach(item -> item.setChildren(getPermissionList(item.getId())));
        return permissionList;
    }

    @Override
    public PermissionVO add(PermissionAddForm form) {
        Integer parentId = form.getParentId();
        if (parentId != null && permissionMapper.selectByPrimaryKey(parentId) == null) {
            throw new SmilcoolException("父级权限不存在");
        }
        Permission selected = permissionMapper.selectByName(form.getName());
        if (selected != null) {
            throw new SmilcoolException("权限已存在");
        }
        Permission permission = BeanUtil.copyProp(form, Permission.class);
        permissionMapper.insertSelective(permission);
        return getById(permission.getId());
    }
}

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
    public List<PermissionVO> list() {
        List<Permission> permissionList = permissionMapper.selectAll();
        return BeanUtil.copyProp(permissionList, PermissionVO.class);
    }

    @Override
    public PermissionVO add(PermissionAddForm permissionAddForm) {
        Permission selected = permissionMapper.selectByName(permissionAddForm.getName());
        if (selected != null) {
            throw new SmilcoolException("权限已存在");
        }
        Permission permission = BeanUtil.copyProp(permissionAddForm, Permission.class);
        permissionMapper.insertSelective(permission);
        return getById(permission.getId());
    }
}

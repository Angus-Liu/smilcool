package com.smilcool.server.core.service.impl;

import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.PermissionMapper;
import com.smilcool.server.core.dao.RoleMapper;
import com.smilcool.server.core.dao.RolePermissionMapper;
import com.smilcool.server.core.pojo.form.RoleAddForm;
import com.smilcool.server.core.pojo.po.Permission;
import com.smilcool.server.core.pojo.po.Role;
import com.smilcool.server.core.pojo.po.RolePermission;
import com.smilcool.server.core.pojo.vo.RoleVO;
import com.smilcool.server.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Angus
 * @date 2019/3/28
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    private RolePermissionMapper rolePermissionMapper;

    @Override
    public void checkExist(Integer id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        if (role == null) {
            throw new SmilcoolException("角色不存在");
        }
    }

    @Override
    public RoleVO getById(Integer id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        if (role == null) {
            throw new SmilcoolException("角色不存在");
        }
        return BeanUtil.copyProp(role, RoleVO.class);
    }


    @Transactional
    @Override
    public RoleVO add(RoleAddForm roleAddForm) {
        Role selected = roleMapper.selectByName(roleAddForm.getName());
        if (selected != null) {
            throw new SmilcoolException("角色已存在");
        }
        Role role = Role.builder()
                .name(roleAddForm.getName())
                .description(roleAddForm.getDescription())
                .remark(roleAddForm.getRemark())
                .build();
        roleMapper.insertSelective(role);
        return getById(role.getId());
    }

    @Override
    public List<RoleVO> list() {
        List<Role> roleList = roleMapper.selectAll();
        return BeanUtil.copyProp(roleList, RoleVO.class);
    }
}

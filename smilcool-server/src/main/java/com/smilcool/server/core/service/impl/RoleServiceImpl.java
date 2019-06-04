package com.smilcool.server.core.service.impl;

import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.RoleMapper;
import com.smilcool.server.core.pojo.form.RoleAddForm;
import com.smilcool.server.core.pojo.form.RoleUpdateForm;
import com.smilcool.server.core.pojo.po.Role;
import com.smilcool.server.core.pojo.vo.RoleVO;
import com.smilcool.server.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Angus
 * @date 2019/3/28
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void verify(Integer id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        if (role == null) {
            throw new SmilcoolException("角色不存在");
        }
    }

    @Override
    public Role getInitialRole() {
        return roleMapper.selectInitialRole();
    }

    @Override
    public RoleVO getRoleVO(Integer id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        if (role == null) {
            throw new SmilcoolException("角色不存在");
        }
        return BeanUtil.copyProp(role, RoleVO.class);
    }


    @Transactional
    @Override
    public RoleVO add(RoleAddForm form) {
        Role select = roleMapper.selectByName(form.getName());
        if (select != null) {
            throw new SmilcoolException("角色已存在");
        }
        Role role = BeanUtil.copyProp(form, Role.class);
        roleMapper.insertSelective(role);
        return getRoleVO(role.getId());
    }

    @Override
    public List<RoleVO> listRoleVO() {
        return  roleMapper.selectRoleVO();
    }

    @Override
    public RoleVO update(RoleUpdateForm form) {
        Role select = roleMapper.selectByName(form.getName());
        if (select != null && !select.getId().equals(form.getId())) {
            throw new SmilcoolException("角色已存在");
        }
        Role role = BeanUtil.copyProp(form, Role.class);
        roleMapper.updateByPrimaryKeySelective(role);
        return getRoleVO(role.getId());
    }
}

package com.smilcool.server.core.service;

import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.RoleMapper;
import com.smilcool.server.core.pojo.form.RoleAddForm;
import com.smilcool.server.core.pojo.form.RoleUpdateForm;
import com.smilcool.server.core.pojo.po.Role;
import com.smilcool.server.core.pojo.vo.RoleVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleMapper roleMapper;

    public void verify(Integer id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        if (role == null) {
            throw new SmilcoolException("角色不存在");
        }
    }

    /**
     * 获取默认角色
     */
    public Role getInitialRole() {
        return roleMapper.selectInitialRole();
    }

    public RoleVO getRoleVO(Integer id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        if (role == null) {
            throw new SmilcoolException("角色不存在");
        }
        return BeanUtil.copyProp(role, RoleVO.class);
    }


    @Transactional
    public RoleVO add(RoleAddForm form) {
        Role select = roleMapper.selectByName(form.getName());
        if (select != null) {
            throw new SmilcoolException("角色已存在");
        }
        Role role = BeanUtil.copyProp(form, Role.class);
        roleMapper.insertSelective(role);
        return getRoleVO(role.getId());
    }

    public List<RoleVO> listRoleVO() {
        return roleMapper.selectRoleVO();
    }

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

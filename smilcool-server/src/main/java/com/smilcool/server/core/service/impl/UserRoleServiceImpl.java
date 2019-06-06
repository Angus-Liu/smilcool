package com.smilcool.server.core.service.impl;

import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.UserRoleMapper;
import com.smilcool.server.core.pojo.form.UserRoleAddForm;
import com.smilcool.server.core.pojo.po.Role;
import com.smilcool.server.core.pojo.po.UserRole;
import com.smilcool.server.core.pojo.vo.UserRoleVO;
import com.smilcool.server.core.service.RoleService;
import com.smilcool.server.core.service.UserRoleService;
import com.smilcool.server.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author Angus
 * @date 2019/3/29
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleMapper userRoleMapper;

    private UserRoleVO getById(Integer id) {
        UserRole userRole = userRoleMapper.selectByPrimaryKey(id);
        return BeanUtil.copyProp(userRole, UserRoleVO.class);
    }

    @Override
    public List<Role> getRoles(Integer userId) {
        List<Role> roleList = userRoleMapper.selectRoleByUserId(userId);
        return roleList;
    }

    @Override
    public Set<String> getRoleNames(Integer userId) {
        return userRoleMapper.selectRoleNameByUserId(userId);
    }

    @Override
    public Set<String> getRoleNames(String username) {
        return userRoleMapper.selectRoleNameByUsername(username);
    }

    @Override
    public Set<String> getRoleDescriptions(Integer userId) {
        return userRoleMapper.selectRoleDescriptionByUserId(userId);
    }

    @Override
    public List<UserRoleVO> list() {
        List<UserRole> userRoleList = userRoleMapper.select();
        return BeanUtil.copyProp(userRoleList, UserRoleVO.class);
    }

    @Override
    public UserRoleVO add(UserRoleAddForm form) {
        // 检查用户是否存在
        userService.validate(form.getUserId());
        // 检查角色是否存在
        roleService.verify(form.getRoleId());
        // 检查用户是否已被赋予该角色
        UserRole userRole = userRoleMapper.selectByUserIdAndRoleId(form.getUserId(), form.getRoleId());
        if (userRole != null) {
            throw new SmilcoolException("用户已被赋予该角色");
        }
        // 插入用户角色记录
        userRole = BeanUtil.copyProp(form, UserRole.class);
        userRoleMapper.insertSelective(userRole);
        return getById(userRole.getId());
    }

    @Override
    public void addInitialRole(Integer userId) {
        // 获取默认角色
        Role role = roleService.getInitialRole();
        UserRole userRole = new UserRole(userId, role.getId());
        userRoleMapper.insertSelective(userRole);
    }
}

package com.smilcool.server.core.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.UserRoleMapper;
import com.smilcool.server.core.pojo.form.UserLoginForm;
import com.smilcool.server.core.pojo.form.UserRegisterForm;
import com.smilcool.server.core.pojo.form.UserSearchForm;
import com.smilcool.server.core.pojo.po.Permission;
import com.smilcool.server.core.pojo.po.Role;
import com.smilcool.server.core.pojo.po.UserRole;
import com.smilcool.server.core.pojo.vo.UserVO;
import com.smilcool.server.core.dao.UserMapper;
import com.smilcool.server.core.pojo.po.User;
import com.smilcool.server.core.service.RolePermissionService;
import com.smilcool.server.core.service.UserRoleService;
import com.smilcool.server.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Angus
 * @date 2019/3/20
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    @Override
    public UserVO getById(Integer id) {
        // 获取用户基础信息
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            throw new SmilcoolException("用户不存在");
        }
        // 获取用户角色信息
        List<Role> roleList = userRoleService.getRoleByUserId(id);
        List<String> roleNameList = new ArrayList<>();
        List<Integer> roleIdList = new ArrayList<>();
        roleList.forEach(role -> {
            roleNameList.add(role.getName());
            roleIdList.add(role.getId());
        });
        // 获取用户权限信息
        List<Permission> permissionList = rolePermissionService.getPermissionListByRoleIdList(roleIdList);
        List<String> permissionNameList = new ArrayList<>();
        permissionList.forEach(permission -> permissionNameList.add(permission.getName()));
        // 整合用户信息
        UserVO userVO = BeanUtil.copyProp(user, UserVO.class);
        userVO.setRoles(roleNameList);
        userVO.setPermissions(permissionNameList);
        return userVO;
    }

    @Override
    public void checkExist(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            throw new SmilcoolException("用户不存在");
        }
    }

    @Override
    public UserVO login(UserLoginForm userLoginForm) {
        User user = userMapper.selectByUsernameAndPassword(userLoginForm.getUsername(), userLoginForm.getPassword());
        if (user == null) {
            throw new SmilcoolException("用户名或密码错误！");
        }
        return getById(user.getId());
    }


    @Transactional
    @Override
    public UserVO register(UserRegisterForm userRegisterForm) {
        User selected = userMapper.selectByUsername(userRegisterForm.getUsername());
        if (selected != null) {
            throw new SmilcoolException("用户名已存在");
        }
        selected = userMapper.selectByEmail(userRegisterForm.getEmail());
        if (selected != null) {
            throw new SmilcoolException("邮箱已注册");
        }
        // 添加用户记录
        User user = BeanUtil.copyProp(userRegisterForm, User.class);
        userMapper.insertSelective(user);
        // 添加用户-角色记录（用户注册时角色默认为普通用户 - normal）
        // TODO: role 表添加默认状态字段，查询获取为默认状态的 role 为新注册用户赋值
        UserRole userRole = new UserRole(user.getId());
        userRoleMapper.insertSelective(userRole);
        // 返回用户信息
        return getById(user.getId());
    }

    @Override
    public Page<UserVO> getUsers() {
        Page page = new Page();
        Page<UserVO> userPage = BeanUtil.copyProp(userMapper.selectAll(page), UserVO.class);
        userPage.getRecords().forEach(user -> {
            // 获取用户角色信息（角色描述）
            List<String> roles = new ArrayList<>();
            userRoleService.getRoleByUserId(user.getId())
                    .forEach(role -> roles.add(role.getDescription()));
            user.setRoles(roles);
        });
        return userPage;
    }

    @Override
    public Page<UserVO> getUsersByCondition(Page page, UserSearchForm userSearchForm) {
        User condition = BeanUtil.copyProp(userSearchForm, User.class);
        Page<UserVO> userPage = BeanUtil.copyProp(userMapper.selectByCondition(page, condition), UserVO.class);
        userPage.getRecords().forEach(user -> {
            // 获取用户角色信息（角色描述）
            List<String> roles = new ArrayList<>();
            userRoleService.getRoleByUserId(user.getId())
                    .forEach(role -> roles.add(role.getDescription()));
            user.setRoles(roles);
        });
        return userPage;
    }
}

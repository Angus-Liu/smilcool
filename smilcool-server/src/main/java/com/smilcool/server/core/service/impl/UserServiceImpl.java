package com.smilcool.server.core.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.UserMapper;
import com.smilcool.server.core.pojo.form.UserLoginForm;
import com.smilcool.server.core.pojo.form.UserQueryForm;
import com.smilcool.server.core.pojo.form.UserRegisterForm;
import com.smilcool.server.core.pojo.po.User;
import com.smilcool.server.core.pojo.vo.UserSimpleInfo;
import com.smilcool.server.core.pojo.vo.UserDetailInfo;
import com.smilcool.server.core.service.RolePermissionService;
import com.smilcool.server.core.service.UserRoleService;
import com.smilcool.server.core.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author Angus
 * @date 2019/3/20
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;


    @Override
    public void checkExist(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            throw new SmilcoolException("用户不存在");
        }
    }

    @Override
    public User getUser(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User getUser(String username, String password) {
        return userMapper.selectByUsernameAndPassword(username, password);
    }

    @Override
    public UserSimpleInfo getUserSimpleInfo(Integer id) {
        // TODO BeanUtil 逐渐应减少使用
        User user = userMapper.selectByPrimaryKey(id);
        return BeanUtil.copyProp(user, UserSimpleInfo.class);
    }

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    @Override
    public UserDetailInfo getUserTotalInfo(Integer id) {
        // 获取用户基础信息
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            throw new SmilcoolException("用户不存在");
        }
        // TODO user 不应该包含角色和权限信息
        // 获取用户角色信息
        Set<String> roleNames = userRoleService.getRoleNames(user.getId());
        // 获取用户权限信息
        Set<String> permissionNames = rolePermissionService.getPermissionNames(user.getId());
        // 整合用户信息
        UserDetailInfo userDetailInfo = BeanUtil.copyProp(user, UserDetailInfo.class);
        userDetailInfo.setRoles(roleNames);
        userDetailInfo.setPermissions(permissionNames);
        return userDetailInfo;
    }

    @Override
    public UserDetailInfo login(UserLoginForm form) {
        // Shiro 身份认证
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(form.getUsername(), form.getPassword());
        currentUser.login(token);
        return getUserTotalInfo((Integer) currentUser.getPrincipal());
    }

    @Override
    public void logout() {
        // Shiro 身份注销
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
    }


    @Transactional
    @Override
    public UserDetailInfo register(UserRegisterForm form) {
        User selected = userMapper.selectByUsername(form.getUsername());
        if (selected != null) {
            throw new SmilcoolException("用户名已存在");
        }
        selected = userMapper.selectByEmail(form.getEmail());
        if (selected != null) {
            throw new SmilcoolException("邮箱已注册");
        }
        // 添加用户记录
        User user = BeanUtil.copyProp(form, User.class);
        userMapper.insertSelective(user);
        // 添加用户-角色记录（用户注册时角色默认为普通用户 - normal）
        // TODO: 2019/4/24  role 表添加默认状态字段，查询获取为默认状态的 role 为新注册用户赋值
        userRoleService.addDefault(user.getId());
        // 返回用户信息
        return getUserTotalInfo(user.getId());
    }

    @Override
    public List<UserDetailInfo> getUsers() {
        List<UserDetailInfo> userList = BeanUtil.copyProp(userMapper.selectAll(), UserDetailInfo.class);
        userList.forEach(user -> {
            // 获取用户角色信息（角色描述）
            Set<String> roleDescriptions = userRoleService.getRoleDescriptions(user.getId());
            user.setRoles(roleDescriptions);
        });
        return userList;
    }

    @Override
    public Page<UserDetailInfo> getUsers(Page page, UserQueryForm form) {
        User condition = BeanUtil.copyProp(form, User.class);
        Page<UserDetailInfo> userPage = BeanUtil.copyProp(userMapper.selectByCondition(page, condition), UserDetailInfo.class);
        userPage.getRecords().forEach(user -> {
            // 获取用户角色信息（角色描述）
            Set<String> roleDescriptions = userRoleService.getRoleDescriptions(user.getId());
            user.setRoles(roleDescriptions);
        });
        return userPage;
    }

    @Override
    public Set<String> getRoles(Integer id) {
        return userRoleService.getRoleNames(id);
    }

    @Override
    public Set<String> getRoles(String username) {
        return userRoleService.getRoleNames(username);
    }

    @Override
    public Set<String> getPermissions(Integer id) {
        return rolePermissionService.getPermissionNames(id);
    }

    @Override
    public Set<String> getPermissions(String username) {
        return rolePermissionService.getPermissionNames(username);
    }
}

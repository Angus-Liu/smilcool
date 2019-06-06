package com.smilcool.server.core.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.UserMapper;
import com.smilcool.server.core.pojo.form.UserLoginForm;
import com.smilcool.server.core.pojo.form.UserQueryForm;
import com.smilcool.server.core.pojo.form.UserLogonForm;
import com.smilcool.server.core.pojo.form.UserUpdateForm;
import com.smilcool.server.core.pojo.po.User;
import com.smilcool.server.core.pojo.vo.UserVO;
import com.smilcool.server.core.service.RolePermissionService;
import com.smilcool.server.core.service.UserRoleService;
import com.smilcool.server.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author Angus
 * @date 2019/3/20
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;


    @Override
    public void validate(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            throw new SmilcoolException("用户不存在");
        }
    }

    @Override
    public User getUser(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            throw new SmilcoolException("用户不存在");
        }
        return user;
    }

    @Override
    public User getUser(String username, String password) {
        return userMapper.selectByUsernameAndPassword(username, password);
    }

    @Override
    public Integer currentUserId() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            return (Integer) currentUser.getPrincipal();
        }
        throw new UnauthenticatedException("身份验证异常，请重新登录");
    }

    @Override
    public UserVO getCurrentUser() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            return getUserVO((Integer) currentUser.getPrincipal());
        }
        return null;
    }

    @Override
    public UserVO getUserVO(Integer id) {
        // 获取用户基础信息
        User user = getUser(id);
        return BeanUtil.copyProp(user, UserVO.class);
    }

    @Override
    public UserVO login(UserLoginForm form) {
        // Shiro 身份认证
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(form.getUsername(), form.getPassword());
        currentUser.login(token);
        return getUserVO((Integer) currentUser.getPrincipal());
    }

    @Override
    public void logout() {
        // Shiro 身份注销
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        // Netty channel 关闭
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserVO logon(UserLogonForm form) {
        User selected = userMapper.selectByUsername(form.getUsername());
        if (selected != null) {
            throw new SmilcoolException("用户名已存在");
        }
        selected = userMapper.selectByEmail(form.getEmail());
        if (selected != null) {
            throw new SmilcoolException("邮箱已注册");
        }
        // 密码加密
        form.setPassword(SecureUtil.md5(form.getPassword()));
        // 添加用户记录
        User user = BeanUtil.copyProp(form, User.class);
        userMapper.insertSelective(user);
        // 为用户添加默认角色
        userRoleService.addInitialRole(user.getId());
        // 返回用户信息
        return this.getUserVO(user.getId());
    }

    @Override
    public UserVO updateUser(UserUpdateForm form) {
        Integer currentUserId = currentUserId();
        userMapper.updateByPrimaryKeyAndForm(currentUserId, form);
        return getUserVO(currentUserId);
    }

    @Override
    public Page<UserVO> pageUserVO(Page page, UserQueryForm form) {
        return userMapper.selectUserVOByQueryForm(page, form);
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

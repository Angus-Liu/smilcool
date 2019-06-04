package com.smilcool.server.core.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.UserMapper;
import com.smilcool.server.core.pojo.form.UserLoginForm;
import com.smilcool.server.core.pojo.form.UserQueryForm;
import com.smilcool.server.core.pojo.form.UserRegisterForm;
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

import java.util.List;
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
    public Integer currentUserId() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            Integer userId = (Integer) currentUser.getPrincipal();
            log.info("已登录，userId: {}", userId);
            return userId;
        }
        Integer mockUserId = 1;
        log.info("未登录，mock user id: {}", mockUserId);
        return mockUserId;
    }

    @Override
    public User getCurrentUser() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            // TODO 从缓存中获取
            return getUser((Integer) currentUser.getPrincipal());
        } else {
            throw new UnauthenticatedException("用户未认证");
        }
    }

    // TODO 2019/5/4 改为 validate
    @Override
    public void checkExist(Integer id) {
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

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    @Override
    public UserVO getUserVO(Integer id) {
        // 获取用户基础信息
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            throw new SmilcoolException("用户不存在");
        }
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
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserVO register(UserRegisterForm form) {
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

package com.smilcool.server.core.service;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.UserMapper;
import com.smilcool.server.core.dao.UserRoleMapper;
import com.smilcool.server.core.pojo.form.*;
import com.smilcool.server.core.pojo.po.Role;
import com.smilcool.server.core.pojo.po.User;
import com.smilcool.server.core.pojo.po.UserRole;
import com.smilcool.server.core.pojo.vo.UserRoleVO;
import com.smilcool.server.core.pojo.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;
    private final RoleService roleService;
    private final RolePermissionService rolePermissionService;


    public void validate(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            throw new SmilcoolException("用户不存在");
        }
    }

    public User getUser(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            throw new SmilcoolException("用户不存在");
        }
        return user;
    }

    /**
     * 通过用户名和密码获取用户
     *
     * @param username 用户名
     * @param password 密码
     */
    public User getUser(String username, String password) {
        return userMapper.selectByUsernameAndPassword(username, password);
    }

    public Integer currentUserId() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            return (Integer) currentUser.getPrincipal();
        }
        throw new UnauthenticatedException("身份验证异常，请重新登录");
    }

    public UserVO getCurrentUser() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            return getUserVO((Integer) currentUser.getPrincipal());
        }
        return null;
    }

    public UserVO getUserVO(Integer id) {
        // 获取用户基础信息
        User user = getUser(id);
        return BeanUtil.copyProp(user, UserVO.class);
    }

    /**
     * 用户注册
     *
     * @param form 用户登录表单
     * @return 用户信息
     */
    public UserVO login(UserLoginForm form) {
        // Shiro 身份认证
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(form.getUsername(), form.getPassword());
        currentUser.login(token);
        return getUserVO((Integer) currentUser.getPrincipal());
    }

    /**
     * 用户注销
     */
    public void logout() {
        // Shiro 身份注销
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        // Netty channel 关闭
    }


    /**
     * 用户注册
     *
     * @param form 用户注册表单的那
     * @return 用户信息
     */
    @Transactional(rollbackFor = Exception.class)
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
        addInitialRole(user.getId());
        // 返回用户信息
        return this.getUserVO(user.getId());
    }

    public UserVO updateUser(UserUpdateForm form) {
        Integer currentUserId = currentUserId();
        userMapper.updateByPrimaryKeyAndForm(currentUserId, form);
        return getUserVO(currentUserId);
    }

    public Page<UserVO> pageUserVO(Page<?> page, UserQueryForm form) {
        return userMapper.selectUserVOByQueryForm(page, form);
    }

    public Set<String> getPermissions(Integer id) {
        return rolePermissionService.getPermissionNames(id);
    }

    private UserRoleVO getById(Integer id) {
        UserRole userRole = userRoleMapper.selectByPrimaryKey(id);
        return BeanUtil.copyProp(userRole, UserRoleVO.class);
    }

    /**
     * 获取角色
     *
     * @param userId 用户 id
     * @return 角色列表
     */
    public List<Role> getRoles(Integer userId) {
        return userRoleMapper.selectRoleByUserId(userId);
    }

    /**
     * 获取角色名
     *
     * @param userId 用户 ID
     * @return 角色名集合
     */
    public Set<String> getRoleNames(Integer userId) {
        return userRoleMapper.selectRoleNameByUserId(userId);
    }

    /**
     * 获取角色名
     *
     * @param username 用户名
     * @return 角色名集合
     */
    public Set<String> getRoleNames(String username) {
        return userRoleMapper.selectRoleNameByUsername(username);
    }

    /**
     * 获取角色描述
     *
     * @param userId 用户 id
     * @return 角色描述集合
     */
    public Set<String> getRoleDescriptions(Integer userId) {
        return userRoleMapper.selectRoleDescriptionByUserId(userId);
    }

    public List<UserRoleVO> list() {
        List<UserRole> userRoleList = userRoleMapper.select();
        return BeanUtil.copyProp(userRoleList, UserRoleVO.class);
    }

    public UserRoleVO add(UserRoleAddForm form) {
        // 检查用户是否存在
        validate(form.getUserId());
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

    /**
     * 为用户添加默认角色
     */
    public void addInitialRole(Integer userId) {
        // 获取默认角色
        Role role = roleService.getInitialRole();
        UserRole userRole = new UserRole(userId, role.getId());
        userRoleMapper.insertSelective(userRole);
    }
}

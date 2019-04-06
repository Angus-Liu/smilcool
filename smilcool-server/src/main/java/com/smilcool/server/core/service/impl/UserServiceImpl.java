package com.smilcool.server.core.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.UserMapper;
import com.smilcool.server.core.pojo.form.UserLoginForm;
import com.smilcool.server.core.pojo.form.UserRegisterForm;
import com.smilcool.server.core.pojo.form.UserSearchForm;
import com.smilcool.server.core.pojo.po.User;
import com.smilcool.server.core.pojo.vo.UserVO;
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
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    @Override
    public UserVO get(Integer id) {
        // 获取用户基础信息
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            throw new SmilcoolException("用户不存在");
        }
        // 获取用户角色信息
        Set<String> roleNames = userRoleService.getRoleNames(user.getId());
        // 获取用户权限信息
        Set<String> permissionNames = rolePermissionService.getPermissionNames(user.getId());
        // 整合用户信息
        UserVO userVO = BeanUtil.copyProp(user, UserVO.class);
        userVO.setRoles(roleNames);
        userVO.setPermissions(permissionNames);
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
        // Shiro 身份认证
        Subject currentUser = SecurityUtils.getSubject();
        String username = userLoginForm.getUsername();
        String password = userLoginForm.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        currentUser.login(token);
        return get((Integer) currentUser.getPrincipal());
    }

    @Override
    public User get(String username, String password) {
        return userMapper.selectByUsernameAndPassword(username, password);
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
        userRoleService.addDefault(user.getId());
        // 返回用户信息
        return get(user.getId());
    }

    @Override
    public List<UserVO> getUsers() {
        List<UserVO> userList = BeanUtil.copyProp(userMapper.selectAll(), UserVO.class);
        userList.forEach(user -> {
            // 获取用户角色信息（角色描述）
            Set<String> roleDescriptions = userRoleService.getRoleDescriptions(user.getId());
            user.setRoles(roleDescriptions);
        });
        return userList;
    }

    @Override
    public Page<UserVO> getUsers(Page page, UserSearchForm userSearchForm) {
        User condition = BeanUtil.copyProp(userSearchForm, User.class);
        Page<UserVO> userPage = BeanUtil.copyProp(userMapper.selectByCondition(page, condition), UserVO.class);
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

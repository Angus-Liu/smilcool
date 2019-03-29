package com.smilcool.server.core.service.impl;

import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.UserRoleMapper;
import com.smilcool.server.core.pojo.form.UserLoginForm;
import com.smilcool.server.core.pojo.form.UserRegisterForm;
import com.smilcool.server.core.pojo.po.UserRole;
import com.smilcool.server.core.pojo.vo.UserVO;
import com.smilcool.server.core.dao.UserMapper;
import com.smilcool.server.core.pojo.po.User;
import com.smilcool.server.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Angus
 * @date 2019/3/20
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 获取用户信息（包括角色和权限信息）
     *
     * @param id
     * @return
     */
    private UserVO getById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return BeanUtil.copyProp(user, UserVO.class);
    }

    @Override
    public UserVO login(UserLoginForm userLoginForm) {
        User user = userMapper.selectByUsernameAndPassword(userLoginForm.getUsername(), userLoginForm.getPassword());
        if (user == null) {
            throw new SmilcoolException("用户名或密码错误！");
        }
        return BeanUtil.copyProp(user, UserVO.class);
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
        UserRole userRole = new UserRole(user.getId());
        userRoleMapper.insertSelective(userRole);
        // 返回用户信息（包括角色和权限信息）
        return getById(user.getId());
    }

    @Override
    public List<UserVO> list() {
        List<User> userList = userMapper.selectAll();
        return BeanUtil.copyProp(userList, UserVO.class);
    }
}

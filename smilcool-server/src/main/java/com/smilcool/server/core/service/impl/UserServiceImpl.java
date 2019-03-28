package com.smilcool.server.core.service.impl;

import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.pojo.vo.UserVO;
import com.smilcool.server.core.dao.UserMapper;
import com.smilcool.server.core.pojo.po.User;
import com.smilcool.server.core.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Angus
 * @date 2019/3/20
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserVO login(String username, String password) {
        User user = userMapper.selectByUsernameAndPassword(username, password);
        if (user == null) {
            throw new SmilcoolException("用户不存在！");
        }
        return BeanUtil.copyProp(user, UserVO.class);
    }

    @Override
    public void register(UserVO userVO) {
        User selected = userMapper.selectByUsername(userVO.getUsername());
        if (selected != null) {
            throw new SmilcoolException("用户名已存在");
        }
        selected = userMapper.selectByEmail(userVO.getEmail());
        if (selected != null) {
            throw new SmilcoolException("邮箱已注册");
        }

        User user = BeanUtil.copyProp(userVO, User.class);
        userMapper.insertSelective(user);
    }
}

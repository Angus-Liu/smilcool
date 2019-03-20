package com.smilcool.server.core.service.impl;

import com.smilcool.server.core.dao.UserMapper;
import com.smilcool.server.core.model.User;
import com.smilcool.server.core.service.UserService;
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
    public User login(String identifier, String password) {
//        userMapper.
        return null;
    }
}

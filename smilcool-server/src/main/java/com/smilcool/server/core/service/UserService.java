package com.smilcool.server.core.service;

import com.smilcool.server.core.model.User;

/**
 * @author Angus
 * @date 2019/3/20
 */
public interface UserService {
    User login(String identifier, String password);
}

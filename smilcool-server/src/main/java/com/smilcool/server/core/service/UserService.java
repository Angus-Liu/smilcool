package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.vo.UserVO;

import java.util.List;

/**
 * @author Angus
 * @date 2019/3/20
 */
public interface UserService {
    UserVO login(String username, String password);

    UserVO register(UserVO userVO);

    List<UserVO> list();
}

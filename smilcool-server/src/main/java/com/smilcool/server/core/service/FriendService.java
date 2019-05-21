package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.po.User;

import java.util.List;

/**
 * @author Angus
 * @date 2019/5/20
 */
public interface FriendService {
    List<User> getFriendList();
}

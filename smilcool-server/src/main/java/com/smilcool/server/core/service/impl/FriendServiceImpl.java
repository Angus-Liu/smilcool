package com.smilcool.server.core.service.impl;

import com.smilcool.server.core.dao.FriendMapper;
import com.smilcool.server.core.pojo.po.User;
import com.smilcool.server.core.service.FriendService;
import com.smilcool.server.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Angus
 * @date 2019/5/20
 */
@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private UserService userService;

    @Override
    public List<User> getFriendList() {
        Integer currentUserId = userService.currentUserId();
        List<User> friendList = friendMapper.selectWithUserByUserId(currentUserId);
        return friendList;
    }
}

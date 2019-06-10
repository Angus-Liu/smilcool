package com.smilcool.server.core.service.impl;

import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.core.dao.FriendMapper;
import com.smilcool.server.core.pojo.form.FriendAddForm;
import com.smilcool.server.core.pojo.po.Friend;
import com.smilcool.server.core.pojo.po.User;
import com.smilcool.server.core.service.FriendService;
import com.smilcool.server.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addFriend(FriendAddForm form) {
        Integer userId = userService.currentUserId();
        Integer friendUserId = form.getFriendUserId();
        // 校验是否已经是好友
        Friend friend = friendMapper.selectByUserIdWithFriendUserId(userId, friendUserId);
        if (friend != null) {
            throw new SmilcoolException("已经是好友关系，无需重复添加");
        }
        friendMapper.insertSelective(new Friend(userId, friendUserId));
        friendMapper.insertSelective(new Friend(friendUserId, userId));
    }
}

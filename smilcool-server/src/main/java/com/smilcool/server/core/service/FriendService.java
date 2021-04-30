package com.smilcool.server.core.service;

import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.core.dao.FriendMapper;
import com.smilcool.server.core.pojo.form.FriendAddForm;
import com.smilcool.server.core.pojo.po.Friend;
import com.smilcool.server.core.pojo.po.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class FriendService {
    private final FriendMapper friendMapper;
    private final UserService userService;

    public List<User> getFriendList() {
        Integer currentUserId = userService.currentUserId();
        List<User> friendList = friendMapper.selectWithUserByUserId(currentUserId);
        return friendList;
    }

    @Transactional(rollbackFor = Exception.class)
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

package com.smilcool.server.core.service;

import com.smilcool.server.common.util.MockUtil;
import com.smilcool.server.core.dao.FriendMapper;
import com.smilcool.server.core.pojo.po.User;
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

    @Override
    public List<User> getFriendList() {
        Integer currentUserId = MockUtil.currentUserId();
        List<User> friendList = friendMapper.selectWithUserByUserId(currentUserId);
        return friendList;
    }
}

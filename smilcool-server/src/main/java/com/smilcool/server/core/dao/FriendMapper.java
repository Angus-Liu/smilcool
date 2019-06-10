package com.smilcool.server.core.dao;

import com.smilcool.server.core.pojo.po.Friend;
import com.smilcool.server.core.pojo.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface FriendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Friend record);

    int insertSelective(Friend record);

    Friend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Friend record);

    int updateByPrimaryKey(Friend record);

    List<User> selectWithUserByUserId(Integer currentUserId);

    Friend selectByUserIdWithFriendUserId(@Param("userId") Integer userId,
                                          @Param("friendUserId") Integer friendUserId);
}
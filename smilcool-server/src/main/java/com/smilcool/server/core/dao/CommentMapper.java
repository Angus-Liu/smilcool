package com.smilcool.server.core.dao;

import com.smilcool.server.core.pojo.po.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> selectByResourceId(Integer resourceId);

    List<Comment> selectByParentId(Integer parentId);

    List<Comment> select();
}
package com.smilcool.server.core.dao;

import com.smilcool.server.core.pojo.po.Resource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    List<Resource> selectAll();

    List<Resource> selectByCondition(Resource condition);

    @Update("UPDATE resource SET comment_count = comment_count + #{count} WHERE id = #{id}")
    int updateCommentCountByIdAndCount(@Param("id") Integer id, @Param("count") Integer count);
}
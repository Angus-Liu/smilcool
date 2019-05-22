package com.smilcool.server.core.dao;

import com.smilcool.server.core.pojo.po.Zan;
import org.apache.ibatis.annotations.Param;

public interface ZanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Zan record);

    int insertSelective(Zan record);

    Zan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Zan record);

    int updateByPrimaryKey(Zan record);

    /* 以下是自定义方法 */

    Zan selectByUserIdAndResourceId(@Param("userId") Integer userId, @Param("resourceId") Integer resourceId);
}
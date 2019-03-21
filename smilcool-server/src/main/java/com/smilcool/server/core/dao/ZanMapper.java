package com.smilcool.server.core.dao;

import com.smilcool.server.core.pojo.po.Zan;

public interface ZanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Zan record);

    int insertSelective(Zan record);

    Zan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Zan record);

    int updateByPrimaryKey(Zan record);
}
package com.smilcool.server.core.dao;

import com.smilcool.server.core.model.Resource;

import java.util.List;

public interface ResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    List<Resource> selectAll();
}
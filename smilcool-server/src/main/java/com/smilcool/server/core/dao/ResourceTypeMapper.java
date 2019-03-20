package com.smilcool.server.core.dao;

import com.smilcool.server.core.model.ResourceType;

public interface ResourceTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ResourceType record);

    int insertSelective(ResourceType record);

    ResourceType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResourceType record);

    int updateByPrimaryKey(ResourceType record);
}
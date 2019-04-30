package com.smilcool.server.core.dao;

import com.smilcool.server.core.pojo.po.ResourceType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ResourceType record);

    int insertSelective(ResourceType record);

    ResourceType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResourceType record);

    int updateByPrimaryKey(ResourceType record);

    List<ResourceType> selectByParentId(@Param("parentId") Integer parentId);

    ResourceType selectByName(String name);
}
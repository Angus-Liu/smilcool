package com.smilcool.server.core.dao;

import com.smilcool.server.core.pojo.po.DicType;

public interface DicTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DicType record);

    int insertSelective(DicType record);

    DicType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DicType record);

    int updateByPrimaryKey(DicType record);
}
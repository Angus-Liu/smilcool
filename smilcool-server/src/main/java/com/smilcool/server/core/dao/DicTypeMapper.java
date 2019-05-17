package com.smilcool.server.core.dao;

import com.smilcool.server.core.pojo.po.DicType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DicTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DicType record);

    int insertSelective(DicType record);

    DicType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DicType record);

    int updateByPrimaryKey(DicType record);

    List<DicType> select();

    DicType selectByCode(String code);

    int countByNameOrCode(@Param("name") String name, @Param("code") String code);
}
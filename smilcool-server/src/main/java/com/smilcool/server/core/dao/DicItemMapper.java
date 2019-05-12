package com.smilcool.server.core.dao;

import com.smilcool.server.core.pojo.po.DicItem;

public interface DicItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DicItem record);

    int insertSelective(DicItem record);

    DicItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DicItem record);

    int updateByPrimaryKey(DicItem record);
}
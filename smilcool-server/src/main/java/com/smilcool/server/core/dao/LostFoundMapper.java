package com.smilcool.server.core.dao;

import com.smilcool.server.core.model.LostFound;

public interface LostFoundMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LostFound record);

    int insertSelective(LostFound record);

    LostFound selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LostFound record);

    int updateByPrimaryKey(LostFound record);
}
package com.smilcool.server.core.dao;

import com.smilcool.server.core.model.SecondHand;

public interface SecondHandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SecondHand record);

    int insertSelective(SecondHand record);

    SecondHand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecondHand record);

    int updateByPrimaryKey(SecondHand record);
}
package com.smilcool.server.core.dao;

import com.smilcool.server.core.pojo.po.DicItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface DicItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DicItem record);

    int insertSelective(DicItem record);

    DicItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DicItem record);

    int updateByPrimaryKey(DicItem record);

    List<DicItem> selectByDicTypeCode(@Param("dicTypeCode") String dicTypeCode);

    DicItem selectByDicTypeCodeAndCode(@Param("dicTypeCode") String dicTypeCode, @Param("code") String code);

    int countByDicTypeCodeAndNameOrCode(@Param("dicTypeCode") String dicTypeCode,
                                        @Param("name") String name,
                                        @Param("code") String code);
}
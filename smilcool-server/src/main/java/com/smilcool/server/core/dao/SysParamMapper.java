package com.smilcool.server.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smilcool.server.core.pojo.po.SysParam;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface SysParamMapper extends BaseMapper<SysParam> {

    @Update("update sys_param set value = #{value} where name = #{name}")
    int updateValueByName(@Param("name") String name, @Param("value") String value);
}
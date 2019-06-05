package com.smilcool.server.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smilcool.server.common.enums.SysParamEnum;
import com.smilcool.server.core.dao.SysParamMapper;
import com.smilcool.server.core.pojo.po.SysParam;
import com.smilcool.server.core.service.SysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Angus
 * @date 2019/6/4
 */
@Service
public class SysParamServiceImpl implements SysParamService {

    @Autowired
    private SysParamMapper sysParamMapper;

    @Override
    public SysParam getSysParam(SysParamEnum paramEnum) {
        return getSysParam(paramEnum.name);
    }

    @Override
    public SysParam getSysParam(String name) {
        return sysParamMapper.selectOne(
                new QueryWrapper<SysParam>()
                        .eq("name", name));
    }

    @Override
    public int updateSysParam(SysParamEnum paramEnum, String value) {
        return updateSysParam(paramEnum.name, value);
    }

    @Override
    public int updateSysParam(String name, String value) {
        return sysParamMapper.updateValueByName(name, value);
    }
}

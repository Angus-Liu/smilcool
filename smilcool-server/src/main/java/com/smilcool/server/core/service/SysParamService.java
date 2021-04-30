package com.smilcool.server.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smilcool.server.common.enums.SysParamEnum;
import com.smilcool.server.core.dao.SysParamMapper;
import com.smilcool.server.core.pojo.po.SysParam;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SysParamService {

    private final SysParamMapper sysParamMapper;

    public SysParam getSysParam(SysParamEnum paramEnum) {
        return getSysParam(paramEnum.name);
    }

    public SysParam getSysParam(String name) {
        return sysParamMapper.selectOne(
                new QueryWrapper<SysParam>()
                        .eq("name", name));
    }

    public int updateSysParam(SysParamEnum paramEnum, String value) {
        return updateSysParam(paramEnum.name, value);
    }

    public int updateSysParam(String name, String value) {
        return sysParamMapper.updateValueByName(name, value);
    }
}

package com.smilcool.server.core.service;

import com.smilcool.server.common.enums.SysParamEnum;
import com.smilcool.server.core.pojo.po.SysParam;

/**
 * @author Angus
 * @date 2019/6/4
 */
public interface SysParamService {
    SysParam getSysParam(SysParamEnum paramEnum);

    SysParam getSysParam(String name);

    int updateSysParam(SysParamEnum paramEnum, String value);

    int updateSysParam(String name, String value);
}

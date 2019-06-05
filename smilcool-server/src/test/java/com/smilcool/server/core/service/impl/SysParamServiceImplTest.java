package com.smilcool.server.core.service.impl;

import com.smilcool.server.SmilcoolServerApplicationTests;
import com.smilcool.server.common.enums.SysParamEnum;
import com.smilcool.server.core.pojo.po.SysParam;
import com.smilcool.server.core.service.SysParamService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Angus
 * @date 2019/6/4
 */
public class SysParamServiceImplTest extends SmilcoolServerApplicationTests {

    @Autowired
    private SysParamService sysParamService;

    @Test
    public void getSysParam() {
        SysParam sysParam = sysParamService.getSysParam(SysParamEnum.LAST_SYNC_MYSQL_TO_ELASTICSEARCH_TIME);
        assert sysParam != null;
        System.out.println(sysParam);
    }
}
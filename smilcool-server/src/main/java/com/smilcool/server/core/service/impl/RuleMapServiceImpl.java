package com.smilcool.server.core.service.impl;

import com.smilcool.server.core.dao.RuleMapMapper;
import com.smilcool.server.core.pojo.po.RuleMap;
import com.smilcool.server.core.service.RuleMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Angus
 * @date 2019/4/8
 */
@Service
public class RuleMapServiceImpl implements RuleMapService {

    @Autowired
    private RuleMapMapper ruleMapMapper;

    @Override
    public List<RuleMap> getRuleMapList() {
        return ruleMapMapper.selectAll();
    }
}

package com.smilcool.server.core.service.impl;

import com.smilcool.server.base.config.shiro.ShiroConfig;
import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.RuleMapMapper;
import com.smilcool.server.core.pojo.form.RuleMapAddForm;
import com.smilcool.server.core.pojo.form.RuleMapUpdateForm;
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
        return ruleMapMapper.select();
    }

    @Override
    public RuleMap addRuleMap(RuleMapAddForm form) {
        RuleMap select = ruleMapMapper.selectByUrl(form.getUrl());
        if (select != null) {
            throw new SmilcoolException("已存在该请求地址对应的规则映射");
        }
        RuleMap ruleMap = BeanUtil.copyProp(form, RuleMap.class);
        ruleMapMapper.insertSelective(ruleMap);
        return ruleMapMapper.selectByPrimaryKey(ruleMap.getId());
    }

    @Override
    public void updateRuleMap(RuleMapUpdateForm form) {
        RuleMap select = ruleMapMapper.selectByUrl(form.getUrl());
        if (select != null && !select.getId().equals(form.getId())) {
            throw new SmilcoolException("请求地址对应的规则映射已存在");
        }
        RuleMap ruleMap = BeanUtil.copyProp(form, RuleMap.class);
        ruleMapMapper.updateByPrimaryKeySelective(ruleMap);
    }
}

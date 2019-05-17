package com.smilcool.server.core.service.impl;

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
    public RuleMap addRuleMap(RuleMapAddForm ruleMapAddForm) {
        RuleMap select = ruleMapMapper.selectByUrl(ruleMapAddForm.getUrl());
        if (select != null) {
            throw new SmilcoolException("已存在该请求地址对应的规则映射");
        }
        RuleMap ruleMap = BeanUtil.copyProp(ruleMapAddForm, RuleMap.class);
        ruleMapMapper.insertSelective(ruleMap);
        return ruleMapMapper.selectByPrimaryKey(ruleMap.getId());
    }

    @Override
    public RuleMap updateRuleMap(Integer id, RuleMapUpdateForm ruleMapUpdateForm) {
        RuleMap select = ruleMapMapper.selectByPrimaryKey(id);
        if (select == null) {
            throw new SmilcoolException("规则映射不存在");
        }
        select = ruleMapMapper.selectByUrl(ruleMapUpdateForm.getUrl());
        if (select != null && !select.getId().equals(id)) {
            throw new SmilcoolException("请求地址对应的规则映射已存在");
        }
        RuleMap ruleMap = BeanUtil.copyProp(ruleMapUpdateForm, RuleMap.class);
        ruleMap.setId(id);
        ruleMapMapper.updateByPrimaryKeySelective(ruleMap);
        return ruleMapMapper.selectByPrimaryKey(id);
    }
}

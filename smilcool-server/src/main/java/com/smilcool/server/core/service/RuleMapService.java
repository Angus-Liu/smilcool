package com.smilcool.server.core.service;

import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.RuleMapMapper;
import com.smilcool.server.core.pojo.form.RuleMapAddForm;
import com.smilcool.server.core.pojo.form.RuleMapUpdateForm;
import com.smilcool.server.core.pojo.po.RuleMap;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RuleMapService {

    private final RuleMapMapper ruleMapMapper;

    public List<RuleMap> getRuleMapList() {
        return ruleMapMapper.select();
    }

    public RuleMap addRuleMap(RuleMapAddForm form) {
        RuleMap select = ruleMapMapper.selectByUrl(form.getUrl());
        if (select != null) {
            throw new SmilcoolException("已存在该请求地址对应的规则映射");
        }
        RuleMap ruleMap = BeanUtil.copyProp(form, RuleMap.class);
        ruleMapMapper.insertSelective(ruleMap);
        return ruleMapMapper.selectByPrimaryKey(ruleMap.getId());
    }

    public void updateRuleMap(RuleMapUpdateForm form) {
        RuleMap select = ruleMapMapper.selectByUrl(form.getUrl());
        if (select != null && !select.getId().equals(form.getId())) {
            throw new SmilcoolException("请求地址对应的规则映射已存在");
        }
        RuleMap ruleMap = BeanUtil.copyProp(form, RuleMap.class);
        ruleMapMapper.updateByPrimaryKeySelective(ruleMap);
    }
}

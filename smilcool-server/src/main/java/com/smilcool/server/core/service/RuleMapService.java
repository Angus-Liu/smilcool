package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.form.RuleMapAddForm;
import com.smilcool.server.core.pojo.form.RuleMapUpdateForm;
import com.smilcool.server.core.pojo.po.RuleMap;

import java.util.List;

/**
 * @author Angus
 * @date 2019/4/8
 */
public interface RuleMapService {
    List<RuleMap> getRuleMapList();

    RuleMap addRuleMap(RuleMapAddForm form);

    void updateRuleMap(RuleMapUpdateForm form);
}

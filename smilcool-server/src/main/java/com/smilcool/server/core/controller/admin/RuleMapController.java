package com.smilcool.server.core.controller.admin;

import com.smilcool.server.base.config.shiro.ShiroFilterChainManager;
import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.dto.Result.Empty;
import com.smilcool.server.core.pojo.form.RuleMapAddForm;
import com.smilcool.server.core.pojo.form.RuleMapUpdateForm;
import com.smilcool.server.core.pojo.po.RuleMap;
import com.smilcool.server.core.service.RuleMapService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 规则映射接口
 */
@AllArgsConstructor
@RestController
public class RuleMapController {

    private final RuleMapService ruleMapService;

    private final ShiroFilterChainManager shiroFilterChainManager;


    /**
     * 规则映射列表
     */
    @GetMapping("/rule-map")
    public Result<List<RuleMap>> getRuleMapList() {
        List<RuleMap> ruleMapList = ruleMapService.getRuleMapList();
        return Result.success(ruleMapList);
    }

    /**
     * 添加规则映射
     */
    @PostMapping("/rule-map")
    public Result<RuleMap> addRuleMap(@RequestBody @Valid RuleMapAddForm ruleMapAddForm) {
        RuleMap ruleMap = ruleMapService.addRuleMap(ruleMapAddForm);
        return Result.success(ruleMap);
    }

    /**
     * 更新规则映射
     */
    @PutMapping("/rule-map")
    public Result<Empty> updateRuleMap(@RequestBody @Valid RuleMapUpdateForm ruleMapUpdateForm) {
        // 更新规则映射
        ruleMapService.updateRuleMap(ruleMapUpdateForm);
        // 刷新过滤器链
        shiroFilterChainManager.refreshFilterChains();
        return Result.success();
    }
}

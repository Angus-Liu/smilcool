package com.smilcool.server.core.controller.admin;

import com.smilcool.server.base.config.shiro.ShiroFilterChainManager;
import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.form.RuleMapAddForm;
import com.smilcool.server.core.pojo.form.RuleMapUpdateForm;
import com.smilcool.server.core.pojo.po.RuleMap;
import com.smilcool.server.core.service.RuleMapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Angus
 * @date 2019/4/8
 */
@Api(tags = "1.6", description = "规则映射接口")
@RestController
public class RuleMapController {

    @Autowired
    private RuleMapService ruleMapService;

    @Autowired
    private ShiroFilterChainManager shiroFilterChainManager;


    @ApiOperation("规则映射列表")
    @GetMapping("/rule-map")
    public Result<List<RuleMap>> getRuleMapList() {
        List<RuleMap> ruleMapList = ruleMapService.getRuleMapList();
        return Result.success(ruleMapList);
    }

    @ApiOperation("添加规则映射")
    @PostMapping("/rule-map")
    public Result<RuleMap> addRuleMap(@RequestBody @Valid RuleMapAddForm ruleMapAddForm) {
        RuleMap ruleMap = ruleMapService.addRuleMap(ruleMapAddForm);
        return Result.success(ruleMap);
    }

    @ApiOperation("更新规则映射")
    @PutMapping("/rule-map")
    public Result updateRuleMap(@RequestBody @Valid RuleMapUpdateForm ruleMapUpdateForm) {
        // 更新规则映射
        ruleMapService.updateRuleMap(ruleMapUpdateForm);
        // 刷新过滤器链
        shiroFilterChainManager.refreshFilterChains();
        return Result.success();
    }
}

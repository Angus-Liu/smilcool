package com.smilcool.server.core.controller.admin;

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
    @PutMapping("/rule-map/{id}")
    public Result<RuleMap> updateRuleMap(@PathVariable("id") Integer id,
                                         @RequestBody @Valid RuleMapUpdateForm ruleMapUpdateForm) {
        RuleMap ruleMap = ruleMapService.updateRuleMap(id, ruleMapUpdateForm);
        return Result.success(ruleMap);
    }

}

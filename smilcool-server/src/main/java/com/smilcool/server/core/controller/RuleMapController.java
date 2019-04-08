package com.smilcool.server.core.controller;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.core.pojo.po.RuleMap;
import com.smilcool.server.core.service.RuleMapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Angus
 * @date 2019/4/8
 */
@Api(description = "规则映射接口", tags = {"1.6"})
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

}

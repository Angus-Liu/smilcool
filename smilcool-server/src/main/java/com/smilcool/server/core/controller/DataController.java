package com.smilcool.server.core.controller;

import com.smilcool.server.common.dto.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Angus
 * @date 2019/4/1
 */
@Api(description = "资料接口", tags = {"2.6"})
@RestController
public class DataController {

    @ApiOperation("资料列表")
    @RequestMapping("/data")
    public Result list() {
        return Result.success();
    }
}

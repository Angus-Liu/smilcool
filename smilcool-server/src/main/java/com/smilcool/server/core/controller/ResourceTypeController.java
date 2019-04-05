package com.smilcool.server.core.controller;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.core.pojo.po.ResourceType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Angus
 * @date 2019/4/1
 */
@Api(description = "资源类型接口", tags = {"2.2"})
@RestController
public class ResourceTypeController {

    @ApiOperation("资源类型列表")
    @RequestMapping("/resource-type")
    public Result<ResourceType> list() {
        return Result.success();
    }
}

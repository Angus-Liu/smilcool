package com.smilcool.server.core.controller;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.core.pojo.po.Comment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Angus
 * @date 2019/4/1
 */
@Api(tags = "2.4", description = "失物寻物接口")
@RestController
public class LostFoundController {

    @ApiOperation("失物寻物列表")
    @GetMapping("/lost-found")
    public Result list() {
        return Result.success();
    }
}

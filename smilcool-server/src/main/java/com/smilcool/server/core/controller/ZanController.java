package com.smilcool.server.core.controller;

import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.po.Comment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Angus
 * @date 2019/4/1
 */
@Api(tags = "3.2", description = "点赞接口")
@RestController
public class ZanController {

    @ApiOperation("点赞列表")
    @RequestMapping("/zan")
    public Result<Comment> list() {
        return Result.success();
    }
}

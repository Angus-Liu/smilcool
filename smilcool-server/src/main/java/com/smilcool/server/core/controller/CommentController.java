package com.smilcool.server.core.controller;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.core.pojo.po.Comment;
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
@Api(tags = "3.1", description = "评论接口")
@RestController
public class CommentController {

    @ApiOperation("评论列表")
    @RequestMapping("/comment")
    public Result<Comment> list() {
        return Result.success();
    }
}

package com.smilcool.server.core.controller;

import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.form.ZanAddForm;
import com.smilcool.server.core.pojo.po.Zan;
import com.smilcool.server.core.service.ZanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Angus
 * @date 2019/4/1
 */
@Api(tags = "3.2", description = "点赞接口")
@RestController
@RequestMapping("/zan")
public class ZanController {

    @Autowired
    private ZanService zanService;

    @ApiOperation("点赞添加")
    @PostMapping
    public Result<Zan> addZan(@RequestBody @Valid ZanAddForm form) {
        Zan zan = zanService.addZan(form);
        return Result.success(zan);
    }
}

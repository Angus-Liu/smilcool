package com.smilcool.server.core.controller;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.core.pojo.po.Data;
import com.smilcool.server.core.service.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Angus
 * @date 2019/4/1
 */
@Api(tags = "2.6", description = "资料接口")
@RestController
public class DataController {

    @Autowired
    private DataService dataService;

    @ApiOperation("资料列表")
    @RequestMapping("/data")
    public Result<List<Data>> list() {
        return Result.success();
    }
}

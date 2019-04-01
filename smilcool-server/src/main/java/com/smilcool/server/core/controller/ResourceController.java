package com.smilcool.server.core.controller;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.core.pojo.vo.ResourceVO;
import com.smilcool.server.core.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Angus
 * @date 2019/3/20
 */
@Api(description = "资源接口", tags = {"2.1"})
@RestController
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @ApiOperation("资源列表")
    @GetMapping("/resource")
    public Result<List<ResourceVO>> list() {
        List<ResourceVO> resourceList = resourceService.list();
        return Result.success(resourceList);
    }

//    public Result<ResourceVO> add() {
//
//    }
}

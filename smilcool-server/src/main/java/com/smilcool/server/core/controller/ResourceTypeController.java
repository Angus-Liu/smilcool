package com.smilcool.server.core.controller;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.core.pojo.form.ResourceTypeAddForm;
import com.smilcool.server.core.pojo.po.ResourceType;
import com.smilcool.server.core.pojo.vo.ResourceTypeVO;
import com.smilcool.server.core.service.ResourceTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Angus
 * @date 2019/4/1
 */
@Api(tags = "2.0", description = "资源类型接口")
@RestController
public class ResourceTypeController {

    @Autowired
    private ResourceTypeService resourceTypeService;

    @ApiOperation("资源类型添加")
    @PostMapping("/resource-type")
    public Result<ResourceType> addResource(@RequestBody @Valid ResourceTypeAddForm resourceTypeAddForm) {
        ResourceType resourceType = resourceTypeService.addResourceType(resourceTypeAddForm);
        return Result.success(resourceType);
    }

    @ApiOperation("资源类型列表")
    @GetMapping("/resource-type")
    public Result<List<ResourceTypeVO>> getResourceTypeList(@RequestParam(required = false) Integer parentId) {
        List<ResourceTypeVO> resourceTypeList = resourceTypeService.getResourceTypeList(parentId);
        return Result.success(resourceTypeList);
    }

    @ApiOperation("资源类型信息")
    @GetMapping("/resource-type/{tag}")
    public Result<ResourceTypeVO> getResourceTypeListByTag(@PathVariable String tag) {
        ResourceTypeVO resourceType = resourceTypeService.getResourceType(tag);
        return Result.success(resourceType);
    }
}

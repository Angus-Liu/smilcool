package com.smilcool.server.core.controller;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.core.model.Resource;
import com.smilcool.server.core.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Angus
 * @date 2019/3/20
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/list")
    public Result list() {
        List<Resource> resourceList = resourceService.list();
        return Result.success(resourceList);
    }
}

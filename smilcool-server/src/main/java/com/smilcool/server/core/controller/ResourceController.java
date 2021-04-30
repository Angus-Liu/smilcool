package com.smilcool.server.core.controller;

import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.form.ResourceQueryForm;
import com.smilcool.server.core.pojo.vo.ResourceVO;
import com.smilcool.server.core.service.ResourceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 资源接口
 */
@AllArgsConstructor
@RestController
@RequestMapping("/resource")
public class ResourceController {

    private final ResourceService resourceService;

    /**
     * 资源信息
     */
    @GetMapping("/{id}")
    public Result<ResourceVO> getResource(@PathVariable Integer id) {
        ResourceVO resource = resourceService.getResourceVO(id);
        return Result.success(resource);
    }

    /**
     * 资源列表
     */
    @GetMapping
    public Result<List<ResourceVO>> getResourceList(ResourceQueryForm form) {
        List<ResourceVO> resourceList = resourceService.getResourceList(form);
        return Result.success(resourceList);
    }
}

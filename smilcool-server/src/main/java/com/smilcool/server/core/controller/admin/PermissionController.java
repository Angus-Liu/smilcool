package com.smilcool.server.core.controller.admin;

import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.form.PermissionAddForm;
import com.smilcool.server.core.pojo.vo.PermissionVO;
import com.smilcool.server.core.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Angus
 * @date 2019/3/29
 */
@Api(tags = "1.3", description = "权限接口")
@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation("权限列表")
    @GetMapping("/permission")
    public Result<List<PermissionVO>> getPermissionList(@RequestParam(required = false) Integer parentId) {
        List<PermissionVO> permissionList = permissionService.getPermissionList(parentId);
        return Result.success(permissionList);
    }

    @ApiOperation("权限添加")
    @PostMapping("/permission")
    public Result<PermissionVO> add(@RequestBody @Valid PermissionAddForm permissionAddForm) {
        PermissionVO permission = permissionService.add(permissionAddForm);
        return Result.success(permission);
    }
}

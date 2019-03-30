package com.smilcool.server.core.controller;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.common.util.BindingResultUtil;
import com.smilcool.server.core.pojo.form.RolePermissionAddForm;
import com.smilcool.server.core.pojo.vo.RolePermissionVO;
import com.smilcool.server.core.service.RolePermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Angus
 * @date 2019/3/29
 */
@Api(description = "角色权限接口", tags = {"1.5"})
@RestController
public class RolePermissionController {
    @Autowired
    private RolePermissionService rolePermissionService;

    @ApiOperation("角色权限添加")
    @PostMapping("/role-permission")
    public Result<RolePermissionVO> add(@RequestBody @Valid RolePermissionAddForm rolePermissionAddForm,
                                        BindingResult bindingResult) {
        BindingResultUtil.validate(bindingResult);
        RolePermissionVO rolePermission = rolePermissionService.add(rolePermissionAddForm);
        return Result.success(rolePermission);
    }

    @ApiOperation("角色权限列表")
    @GetMapping("/role-permission")
    public Result<List<RolePermissionVO>> list() {
        List<RolePermissionVO> rolePermissionList = rolePermissionService.list();
        return Result.success(rolePermissionList);
    }
}

package com.smilcool.server.core.controller;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.common.util.BindingResultUtil;
import com.smilcool.server.core.pojo.form.RoleAddForm;
import com.smilcool.server.core.pojo.vo.PermissionVO;
import com.smilcool.server.core.pojo.vo.RolePermissionVO;
import com.smilcool.server.core.pojo.vo.RoleVO;
import com.smilcool.server.core.service.RolePermissionService;
import com.smilcool.server.core.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Angus
 * @date 2019/3/28
 */
@Api(description = "角色接口", tags = {"1.2"})
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @ApiOperation("角色添加")
    @PostMapping("/role")
    public Result<RoleVO> add(@RequestBody @Valid RoleAddForm roleAddForm, BindingResult bindingResult) {
        BindingResultUtil.validate(bindingResult);
        RoleVO roleVO = roleService.add(roleAddForm);
        return Result.success(roleVO);
    }

    @ApiOperation("角色列表")
    @GetMapping("/role")
    public Result<List<RoleVO>> list() {
        List<RoleVO> roles = roleService.list();
        return Result.success(roles);
    }

    @ApiOperation(value = "角色信息", notes = "通过角色 id 获取角色信息")
    @GetMapping("/role/{id}")
    public Result<RoleVO> get(@PathVariable("id") Integer id) {
        RoleVO role = roleService.getById(id);
        return Result.success(role);
    }

    @ApiOperation(value = "角色权限信息", notes = "通过角色 id 获取角色对应权限信息")
    @GetMapping("/role/{id}/permission")
    public Result<List<PermissionVO>> getPermission(@PathVariable("id") Integer id) {
        List<PermissionVO> permissionList = rolePermissionService.getPermissionListByRoleId(id);
        return Result.success(permissionList);
    }
}

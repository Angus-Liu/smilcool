package com.smilcool.server.core.controller.admin;

import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.form.RolePermissionAddForm;
import com.smilcool.server.core.pojo.vo.PermissionVO;
import com.smilcool.server.core.pojo.vo.RolePermissionVO;
import com.smilcool.server.core.service.RolePermissionService;
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
@Api(tags = "1.5", description = "角色权限接口")
@RestController
public class RolePermissionController {
    @Autowired
    private RolePermissionService rolePermissionService;

    @ApiOperation("角色权限添加")
    @PostMapping("/role-permission")
    public Result<RolePermissionVO> add(@RequestBody @Valid RolePermissionAddForm rolePermissionAddForm) {
        RolePermissionVO rolePermission = rolePermissionService.add(rolePermissionAddForm);
        return Result.success(rolePermission);
    }

    @ApiOperation("角色权限列表")
    @GetMapping("/role-permission")
    public Result<List<RolePermissionVO>> list() {
        List<RolePermissionVO> rolePermissionList = rolePermissionService.list();
        return Result.success(rolePermissionList);
    }

    @ApiOperation(value = "角色权限信息", notes = "通过角色 id 获取角色对应权限信息")
    @GetMapping("/role/{id}/permission")
    public Result<List<PermissionVO>> getPermissionList(@PathVariable("id") Integer id) {
        List<PermissionVO> permissionList = rolePermissionService.getPermissionVOListByRoleId(id);
        return Result.success(permissionList);
    }
}

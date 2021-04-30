package com.smilcool.server.core.controller.admin;

import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.form.RolePermissionAddForm;
import com.smilcool.server.core.pojo.vo.PermissionVO;
import com.smilcool.server.core.pojo.vo.RolePermissionVO;
import com.smilcool.server.core.service.RolePermissionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 角色权限接口
 */
@AllArgsConstructor
@RestController
public class RolePermissionController {

    private final RolePermissionService rolePermissionService;

    /**
     * 角色权限添加
     */
    @PostMapping("/role-permission")
    public Result<RolePermissionVO> add(@RequestBody @Valid RolePermissionAddForm rolePermissionAddForm) {
        RolePermissionVO rolePermission = rolePermissionService.add(rolePermissionAddForm);
        return Result.success(rolePermission);
    }

    /**
     * 角色权限列表
     */
    @GetMapping("/role-permission")
    public Result<List<RolePermissionVO>> list() {
        List<RolePermissionVO> rolePermissionList = rolePermissionService.list();
        return Result.success(rolePermissionList);
    }

    /**
     * 通过角色 id 获取角色对应权限信息
     */
    @GetMapping("/role/{id}/permission")
    public Result<List<PermissionVO>> getPermissionList(@PathVariable("id") Integer id) {
        List<PermissionVO> permissionList = rolePermissionService.getPermissionVOListByRoleId(id);
        return Result.success(permissionList);
    }
}

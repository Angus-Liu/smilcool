package com.smilcool.server.core.controller.admin;

import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.form.RoleAddForm;
import com.smilcool.server.core.pojo.form.RoleUpdateForm;
import com.smilcool.server.core.pojo.vo.RoleVO;
import com.smilcool.server.core.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Angus
 * @date 2019/3/28
 */
@Api(tags = "1.2", description = "角色接口")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("角色添加")
    @PostMapping("/role")
    public Result<RoleVO> addRole(@RequestBody @Valid RoleAddForm form) {
        RoleVO roleVO = roleService.add(form);
        return Result.success(roleVO);
    }

    @ApiOperation("角色列表")
    @GetMapping("/role")
    public Result<List<RoleVO>> listRoleVO() {
        List<RoleVO> roles = roleService.listRoleVO();
        return Result.success(roles);
    }

    @ApiOperation(value = "角色信息", notes = "通过角色 id 获取角色信息")
    @GetMapping("/role/{id}")
    public Result<RoleVO> getRoleVO(@PathVariable("id") Integer id) {
        RoleVO role = roleService.getRoleVO(id);
        return Result.success(role);
    }

    @ApiOperation(value = "更新角色")
    @PutMapping("/role")
    public Result<RoleVO> updateRole(@RequestBody @Valid RoleUpdateForm form) {
        RoleVO role = roleService.update(form);
        return Result.success(role);
    }
}

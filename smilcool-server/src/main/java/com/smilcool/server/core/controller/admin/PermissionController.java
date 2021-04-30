package com.smilcool.server.core.controller.admin;

import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.form.PermissionAddForm;
import com.smilcool.server.core.pojo.vo.PermissionVO;
import com.smilcool.server.core.service.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 权限接口
 */
@AllArgsConstructor
@RestController
public class PermissionController {

    private final PermissionService permissionService;

    /**
     * 权限列表
     */
    @GetMapping("/permission")
    public Result<List<PermissionVO>> getPermissionList(@RequestParam(required = false) Integer parentId) {
        List<PermissionVO> permissionList = permissionService.getPermissionList(parentId);
        return Result.success(permissionList);
    }

    /**
     * 权限添加
     */
    @PostMapping("/permission")
    public Result<PermissionVO> add(@RequestBody @Valid PermissionAddForm permissionAddForm) {
        PermissionVO permission = permissionService.add(permissionAddForm);
        return Result.success(permission);
    }
}
